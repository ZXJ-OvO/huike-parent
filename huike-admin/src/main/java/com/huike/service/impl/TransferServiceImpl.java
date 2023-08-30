package com.huike.service.impl;

import com.huike.common.constant.Constants;
import com.huike.domain.clues.TbAssignRecord;
import com.huike.domain.clues.TbClue;
import com.huike.domain.clues.TbRulePool;
import com.huike.domain.contract.vo.TransferVo;
import com.huike.domain.system.SysUser;
import com.huike.mapper.SysUserMapper;
import com.huike.mapper.TbAssignRecordMapper;
import com.huike.mapper.TbBusinessMapper;
import com.huike.mapper.TbClueMapper;
import com.huike.service.ISysUserService;
import com.huike.service.ITbRulePoolService;
import com.huike.service.ITransferService;
import com.huike.utils.DateUtils;
import com.huike.utils.HuiKeCrmDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TbAssignRecordMapper assignRecordMapper;

    @Autowired
    private ITbRulePoolService rulePoolService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TbClueMapper clueMapper;


    @Autowired
    private TbBusinessMapper businessMapper;

//    @Autowired
//    private SysUserMapper sysUserMapper;

    @Override
    public List<TransferVo> selectTransferList(SysUser user) {
        List<TransferVo> transfervoList = new ArrayList<>();
        List<SysUser> list = userService.selectUserList(user);
        for (SysUser sysUser : list) {
            TransferVo transfervo = new TransferVo();
            int clueNum = assignRecordMapper.countAssignCluesByUser(sysUser.getUserId());
            int businessNum = assignRecordMapper.countAssignBusinessByUser(sysUser.getUserId());
            if (clueNum + businessNum > 0) {
                transfervo.setUserName(sysUser.getUserName());
                transfervo.setClueNum(clueNum);
                transfervo.setBusinessNum(businessNum);
                transfervo.setUserId(sysUser.getUserId());
                transfervo.setPhonenumber(sysUser.getPhonenumber());
                transfervo.setCreateTime(sysUser.getCreateTime());
                transfervoList.add(transfervo);
            }
        }
        return transfervoList;
    }

    @Override
    public Map<String, Object> assignment(String type, Long userId, Long transferUserId) {
        Map<String, Object> result = new HashMap<>();
        if (TbAssignRecord.RecordType.CLUES.getValue().equals(type)) {
            int clueNum = assignRecordMapper.countAssignCluesByUser(userId);
            if (clueNum >= 0) {
                TbRulePool rulePool = rulePoolService.selectTbRulePoolByType(Constants.rule_type_clue);
                int transferUserClueNum = assignRecordMapper.countAssignCluesByUser(transferUserId);
                // 被转派人保有量达到最大值
                if (transferUserClueNum >= rulePool.getMaxNunmber()) {
                    result.put("flag", false);
                    result.put("msg", "线索转换失败！已经达到被转派人最大保有量");
                } else {
                    TbAssignRecord assignRecord = new TbAssignRecord();
                    assignRecord.setUserId(userId);
                    assignRecord.setLatest("1");
                    assignRecord.setType(TbAssignRecord.RecordType.CLUES.getValue());
                    List<TbAssignRecord> list = assignRecordMapper.selectAssignRecordList(assignRecord);
                    for (int i = 0; i < list.size(); i++) {
                        TbAssignRecord tbAssignRecord = list.get(i);
                        SysUser sysUser = sysUserMapper.selectUserById(transferUserId);
                        tbAssignRecord.setUserId(transferUserId);
                        tbAssignRecord.setUserName(sysUser.getUserName());
                        tbAssignRecord.setDeptId(sysUser.getDeptId());
                        tbAssignRecord.setCreateTime(DateUtils.getNowDate());
                        assignRecordMapper.updateAssignRecord(tbAssignRecord);
                        clueMapper.setTransfer(tbAssignRecord.getAssignId(), TbClue.StatusType.UNFOLLOWED.getValue());

                        TbRulePool rulePool1 = rulePoolService.selectTbRulePoolByType(tbAssignRecord.getType());
                        Date endDate = HuiKeCrmDateUtils.getEndDateByRule(tbAssignRecord, rulePool1);
                        clueMapper.updateClueEndTimeById(tbAssignRecord.getAssignId(), endDate);
                        if (transferUserClueNum + i >= rulePool.getMaxNunmber()) {
                            result.put("flag", false);
                            result.put("msg", "线索转换失败！已经分配" + i + " 线索");
                            break;
                        }
                    }
                }
            }
        }
        if (TbAssignRecord.RecordType.BUSNIESS.getValue().equals(type)) {
            int busniessNum = assignRecordMapper.countAssignBusinessByUser(userId);
            if (busniessNum >= 0) {
                TbRulePool rulePool = rulePoolService.selectTbRulePoolByType(Constants.rule_type_business);
                int transferUserBusinessNum = assignRecordMapper.countAssignBusinessByUser(transferUserId);
                //被转派人保有量达到最大值
                if (transferUserBusinessNum >= rulePool.getMaxNunmber()) {
                    result.put("flag", false);
                    result.put("msg", "线索转换失败！已经达到被转派人最大保有量");
                } else {
                    TbAssignRecord assignRecord = new TbAssignRecord();
                    assignRecord.setUserId(userId);
                    assignRecord.setLatest("1");
                    assignRecord.setType(TbAssignRecord.RecordType.BUSNIESS.getValue());
                    List<TbAssignRecord> list = assignRecordMapper.selectAssignRecordList(assignRecord);
                    for (int i = 0; i < list.size(); i++) {
                        TbAssignRecord tbAssignRecord = list.get(i);
                        SysUser sysUser = sysUserMapper.selectUserById(transferUserId);
                        tbAssignRecord.setUserId(transferUserId);
                        tbAssignRecord.setUserName(sysUser.getUserName());
                        tbAssignRecord.setDeptId(sysUser.getDeptId());
                        tbAssignRecord.setCreateTime(DateUtils.getNowDate());
                        assignRecordMapper.updateAssignRecord(tbAssignRecord);

                        TbRulePool rulePool1 = rulePoolService.selectTbRulePoolByType(tbAssignRecord.getType());
                        Date endDate = HuiKeCrmDateUtils.getEndDateByRule(tbAssignRecord, rulePool1);
                        businessMapper.updateBusinessEndTimeById(tbAssignRecord.getAssignId(), endDate);
                        if (transferUserBusinessNum + i >= rulePool.getMaxNunmber()) {
                            result.put("flag", false);
                            result.put("msg", "商机转换失败！已经转派" + i + " 商机");
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

}
