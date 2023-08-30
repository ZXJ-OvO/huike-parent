package com.huike.service.impl;

import com.huike.common.constant.MessageConstants;
import com.huike.common.exception.CustomException;
import com.huike.domain.clues.TbActivity;
import com.huike.mapper.TbActivityMapper;
import com.huike.service.ITbActivityService;
import com.huike.utils.DateUtils;
import com.huike.utils.redis.RedisCache;
import com.huike.utils.uuid.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动管理Service业务层处理
 */
@Service
public class TbActivityServiceImpl implements ITbActivityService {
    @Autowired
    private TbActivityMapper tbActivityMapper;

    @Autowired
    private RedisCache redisCache;


    /**
     * 查询活动管理
     *
     * @param id 活动管理ID
     * @return 活动管理
     */
    @Override
    public TbActivity selectTbActivityById(Long id) {
        return tbActivityMapper.selectTbActivityById(id);
    }


//    public void loadAllActivityCode() {
//        List<String> codeList= tbActivityMapper.selectAllCode();
//        Set<String> set= new HashSet<>(codeList);
//        redisCache.setCacheSet(Constants.ACT_CODE_KEY, set);
//    }

    @Override
    public TbActivity selectTbActivityByCode(String code) {
        return tbActivityMapper.selectTbActivityByCode(code);
    }

    /**
     * 查询活动管理列表
     *
     * @param tbActivity 活动管理
     * @return 活动管理
     */
    @Override
    public List<TbActivity> selectTbActivityList(TbActivity tbActivity) {
        return tbActivityMapper.selectTbActivityList(tbActivity);
    }

    /**
     * 新增活动管理
     *
     * @param tbActivity 活动管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTbActivity(TbActivity tbActivity) {
        tbActivity.setCreateTime(DateUtils.getNowDate());
        tbActivity.setCode(UUIDUtils.getUUID());
        int rows = tbActivityMapper.insertTbActivity(tbActivity);
        return rows;
    }


//    private String getCode(){
//        //随机8位编码
////        String code= StringUtils.getRandom(8);
//        String code = UUIDUtils.getUUID();
//        //店铺校验
//        Set<String> codeSets =  redisCache.getCacheSet(Constants.ACT_CODE_KEY);
//        if(codeSets.contains(code)){
//            getCode();
//        }
//        return code;
//    }


    /**
     * 修改活动管理
     *
     * @param tbActivity 活动管理
     * @return 结果
     */
    @Override
    public int updateTbActivity(TbActivity tbActivity) {
        TbActivity dbActivity = tbActivityMapper.selectTbActivityById(tbActivity.getId());
        //如果活动已经开始, 则不能修改活动
        if (dbActivity.getBeginTime() != null && tbActivity.getBeginTime().before(DateUtils.getNowDate())) {
            throw new CustomException(MessageConstants.ACTIVITY_HAS_BEGIN_ERROR);
        }

        return tbActivityMapper.updateTbActivity(tbActivity);
    }

    /**
     * 批量删除活动管理
     *
     * @param ids 需要删除的活动管理ID
     * @return 结果
     */
    @Override
    public int deleteTbActivityByIds(Long[] ids) {
        return tbActivityMapper.deleteTbActivityByIds(ids);
    }

    /**
     * 删除活动管理信息
     *
     * @param id 活动管理ID
     * @return 结果
     */
    @Override
    public int deleteTbActivityById(Long id) {
        TbActivity tbActivity = tbActivityMapper.selectTbActivityById(id);
        int rows = tbActivityMapper.deleteTbActivityById(id);
        //TODO 添加任务
//        sysJobService.deleteJob(tbActivity.getName());
        return rows;
    }

}
