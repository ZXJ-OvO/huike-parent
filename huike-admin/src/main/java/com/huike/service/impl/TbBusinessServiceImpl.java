package com.huike.service.impl;

import com.huike.common.annotation.DataScope;
import com.huike.common.constant.Constants;
import com.huike.common.exception.CustomException;
import com.huike.domain.business.TbBusiness;
import com.huike.domain.business.TbBusinessTrackRecord;
import com.huike.domain.clues.TbAssignRecord;
import com.huike.domain.clues.TbClue;
import com.huike.domain.clues.TbRulePool;
import com.huike.domain.system.SysUser;
import com.huike.mapper.*;
import com.huike.service.ITbBusinessService;
import com.huike.service.ITbClueService;
import com.huike.service.ITbRulePoolService;
import com.huike.strategy.business.Rule;
import com.huike.utils.DateUtils;
import com.huike.utils.HuiKeCrmDateUtils;
import com.huike.utils.JobUtils;
import com.huike.web.CurrentUserHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 商机Service业务层处理
 *
 * @date 2021-04-25
 */
@Service
public class TbBusinessServiceImpl implements ITbBusinessService {

    @Autowired
    private Rule rule;

    @Autowired
    private TbBusinessMapper tbBusinessMapper;

    @Autowired
    private TbAssignRecordMapper tbAssignRecordMapper;

    @Autowired
    private TbBusinessTrackRecordMapper tbBusinessTrackRecordMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    ITbRulePoolService rulePoolService;

    @Autowired
    TbClueMapper tbClueMapper;

    @Autowired
    private ITbClueService tbClueService;

    @Autowired
    TbAssignRecordMapper assignRecordMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 查询商机
     *
     * @param id 商机ID
     * @return 商机
     */
    @Override
    public TbBusiness selectTbBusinessById(Long id) {
        return tbBusinessMapper.selectTbBusinessById(id);
    }

    /**
     * 查询商机列表
     *
     * @param tbBusiness 商机
     * @return 商机
     */
    @Override
    @DataScope(deptAlias = "r", userAlias = "r")
    public List<TbBusiness> selectTbBusinessList(TbBusiness tbBusiness) {
        return tbBusinessMapper.selectTbBusinessList(tbBusiness);
    }


    @Override
    public List<TbBusiness> selectTbBusinessPool(TbBusiness tbBusiness) {
        return tbBusinessMapper.selectTbBusinessPool(tbBusiness);
    }

    /**
     * 校验商机手机号是否存在
     */
    @Override
    public boolean checkBusinessPhoneExis(String phone) {
        // 验证是否存在这个用户
        TbBusiness tbBusiness = tbBusinessMapper.selectTbBusinessByPhone(phone);
        if (tbBusiness == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 新增商机
     *
     * @param tbBusiness 商机
     * @return 结果
     */
    @Override
    public int insertTbBusiness(TbBusiness tbBusiness) {
        //1. 添加商机
        tbBusiness.setCreateBy(CurrentUserHolder.getUserName());
        tbBusiness.setCreateTime(DateUtils.getNowDate());
        tbBusinessMapper.insertTbBusiness(tbBusiness);

        //2. 添加商机分配记录
        TbAssignRecord tbBusinessAssignRecord = new TbAssignRecord();
        tbBusinessAssignRecord.setAssignId(tbBusiness.getId());
        tbBusinessAssignRecord.setUserId(CurrentUserHolder.getUserId());
        tbBusinessAssignRecord.setUserName(CurrentUserHolder.getUserName());
        tbBusinessAssignRecord.setDeptId(CurrentUserHolder.getDeptId());
        tbBusinessAssignRecord.setCreateBy(CurrentUserHolder.getUserName());
        tbBusinessAssignRecord.setCreateTime(DateUtils.getNowDate());
        tbBusinessAssignRecord.setType(Constants.rule_type_business);
        int rows = tbAssignRecordMapper.insertAssignRecord(tbBusinessAssignRecord);

        //3. 更新商机的结束时间
        //修改结束时间
        TbRulePool rulePool = rulePoolService.selectTbRulePoolByType(tbBusinessAssignRecord.getType());
        Date endTime = HuiKeCrmDateUtils.getEndDateByRule(tbBusinessAssignRecord, rulePool);
        tbBusinessMapper.updateBusinessEndTimeById(tbBusiness.getId(), endTime);

        return rows;
    }

    /**
     * 修改商机
     *
     * @param tbBusiness 商机
     * @return 结果
     */
    @Override
    public int updateTbBusiness(TbBusiness tbBusiness) {
        return tbBusinessMapper.updateTbBusiness(tbBusiness);
    }

    /**
     * 批量删除商机
     *
     * @param ids 需要删除的商机ID
     * @return 结果
     */
    @Override
    public int deleteTbBusinessByIds(Long[] ids) {
        return tbBusinessMapper.deleteTbBusinessByIds(ids);
    }

    /**
     * 删除商机信息
     *
     * @param id 商机ID
     * @return 结果
     */
    @Override
    public int deleteTbBusinessById(Long id) {
        return tbBusinessMapper.deleteTbBusinessById(id);
    }

    @Override
    @Transactional
    public String assign(Long[] businessIds, Long userId) {
        TbRulePool rulePool = rulePoolService.selectTbRulePoolByType(TbRulePool.RuleType.BUSINESS.getValue());
        //统计当前分配人所有线索
        int assignRecords = tbAssignRecordMapper.countAssignBusinessByUser(userId);
        if (assignRecords >= rulePool.getMaxNunmber()) {
            return "分配失败！保有量达到上线，最多选择" + rulePool.getMaxNunmber() + "条线索";
        }
        for (int i = 0; i < businessIds.length; i++) {
            Long businessId = businessIds[i];

            //超过最大保有量
            if (assignRecords + i >= rulePool.getMaxNunmber()) {
                return "超过当前用户最大保有量，部分分配成功";
            }
            updateStatus(businessId, TbClue.StatusType.UNFOLLOWED.getValue());
            //从新分配
            TbAssignRecord record = addNewRecord(businessId, userId);

            //修改结束时间
            TbRulePool pool = rulePoolService.selectTbRulePoolByType(record.getType());
            Date endTime = HuiKeCrmDateUtils.getEndDateByRule(record, pool);
            tbBusinessMapper.updateBusinessEndTimeById(businessId, endTime);
        }
        return "全部分配";
    }

    public TbAssignRecord addNewRecord(Long id, Long userId) {
        //保留上一条分配记录
        assignRecordMapper.updateLatest(id, TbAssignRecord.RecordType.BUSNIESS.getValue());
        //新建分配记录
        TbAssignRecord tbAssignRecord = new TbAssignRecord();
        tbAssignRecord.setAssignId(id);
        SysUser sysUser = userMapper.selectUserById(userId);
        tbAssignRecord.setUserId(userId);
        tbAssignRecord.setDeptId(sysUser.getDeptId());
        tbAssignRecord.setUserName(sysUser.getUserName());
        Date now = DateUtils.getNowDate();
        tbAssignRecord.setCreateTime(now);
        tbAssignRecord.setCreateBy(CurrentUserHolder.getUserName());
        tbAssignRecord.setType(TbAssignRecord.RecordType.BUSNIESS.getValue());
        assignRecordMapper.insertAssignRecord(tbAssignRecord);
        return tbAssignRecord;
    }

    /**
     * 捞取
     *
     * @param businessIds
     * @param userId
     * @return
     */
    @Override
    public String gain(Long[] businessIds, Long userId) {
        boolean isBatch = businessIds.length > 1 ? true : false;
        TbRulePool rulePool = rulePoolService.selectTbRulePoolByType(TbRulePool.RuleType.BUSINESS.getValue());
        // 统计当前分配人所有线索
        int assignRecords = tbAssignRecordMapper.countAssignBusinessByUser(userId);
        if (assignRecords >= rulePool.getMaxNunmber()) {
            throw new CustomException("捞取失败！最大保有量(" + rulePool.getMaxNunmber() + ")，剩余可以捞取" + (rulePool.getMaxNunmber() - assignRecords) + "条线索");
        }
        for (int i = 0; i < businessIds.length; i++) {
            Long businessId = businessIds[i];

            //超过最大保有量
            if (assignRecords + i >= rulePool.getMaxNunmber()) {
                throw new CustomException("超过最大保有量，部分捞取成功，成功数目" + (i + 1) + "条");
            }
            //重复捞取时间限制
            TbAssignRecord businessAssignRecord = tbAssignRecordMapper.selectAssignRecordByAssignId(businessId, TbAssignRecord.RecordType.BUSNIESS.getValue());
            if (businessAssignRecord != null && businessAssignRecord.getUserId().equals(userId)) {
                Date repeatGetTime = JobUtils.getDate(rulePool.getRepeatGetTime().intValue(),
                        rulePool.getRepeatType(), businessAssignRecord.getCreateTime());
                //捞取限制时间内，不让捞取
                if (DateUtils.getNowDate().before(repeatGetTime)) {
                    //批量捞取跳过
                    if (isBatch) {
                        continue;
                    } else {
                        throw new CustomException("捞取失败！需要在 " + DateUtils.dateTime(repeatGetTime) + " 后捞取");
                    }
                }
            }
            updateStatus(businessId, TbClue.StatusType.UNFOLLOWED.getValue());
            TbAssignRecord tbAssignRecord = addNewRecord(businessId, userId);

            //修改结束时间
            TbRulePool pool = rulePoolService.selectTbRulePoolByType(tbAssignRecord.getType());
            Date endTime = HuiKeCrmDateUtils.getEndDateByRule(tbAssignRecord, pool);
            tbBusinessMapper.updateBusinessEndTimeById(businessId, endTime);
        }
        return "全部捞取成功";
    }

    @Override
    public int backPool(Long busniessId, String backReason) {
        TbBusiness business = selectTbBusinessById(busniessId);

        // 退回公海原因
        TbBusinessTrackRecord trackRecord = new TbBusinessTrackRecord();
        trackRecord.setCreateBy(CurrentUserHolder.getUserName());
        trackRecord.setRecord(dictDataMapper.selectDictLabel("reasons_for_business_reporting", backReason));
        trackRecord.setBusinessId(busniessId);
        trackRecord.setCreateTime(DateUtils.getNowDate());
        tbBusinessTrackRecordMapper.insertTbBusinessTrackRecord(trackRecord);
        return updateStatus(business.getId(), TbClue.StatusType.FALSE.getValue());
    }

    /**
     * 转商机的方法
     *
     * @param clueId
     * @return
     */
    @Override
    public int changeBusiness(Long clueId) {
        //查询出线索对应的数据
        TbClue tbClue = tbClueMapper.selectTbClueById(clueId);
        //重置状态为转商机
        tbClueMapper.resetNextTimeAndStatus(clueId, TbClue.StatusType.TOBUSINESS.getValue());

        //构建商机对象
        TbBusiness tbBusiness = new TbBusiness();
        BeanUtils.copyProperties(tbClue, tbBusiness);
        tbBusiness.setStatus(TbBusiness.StatusType.UNFOLLOWED.getValue());
        tbBusiness.setClueId(clueId);
        tbBusiness.setNextTime(null);
        tbBusiness.setCreateBy(CurrentUserHolder.getUserName());
        tbBusiness.setCreateTime(DateUtils.getNowDate());
        //添加商机数据
        int rows = tbBusinessMapper.insertTbBusiness(tbBusiness);

        //基于规则来进行分配
        Integer transForBusiness = rule.transforBusiness(tbBusiness);
        if (transForBusiness != 0) {
            return transForBusiness;
        } else {
            return rows;
        }
    }


    @Override
    public int updateStatus(Long clueId, String status) {
        return tbBusinessMapper.resetNextTimeAndStatus(clueId, status);
    }
}
