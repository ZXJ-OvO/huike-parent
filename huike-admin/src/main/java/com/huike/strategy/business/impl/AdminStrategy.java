package com.huike.strategy.business.impl;

import com.huike.domain.business.TbBusiness;
import com.huike.domain.clues.TbAssignRecord;
import com.huike.domain.system.SysUser;
import com.huike.mapper.SysUserMapper;
import com.huike.mapper.TbAssignRecordMapper;
import com.huike.strategy.business.Rule;
import com.huike.utils.DateUtils;
import com.huike.web.CurrentUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 * admin 处理策略
 * <p>
 * 由admin来处理所有的线索导入和转商机的数据
 * <p>
 * 全部导入到admin 统一由admin来处理所有的线索
 * exchange 转商机的时候统一转换到admin，再由admin来统一分片商机
 */
@ConditionalOnProperty(name = "rule.transfor", havingValue = "admin")
@Service("BusinessAdminStrategy")
public class AdminStrategy implements Rule {

    @Autowired
    private TbAssignRecordMapper assignRecordMapper;

    @Autowired
    private SysUserMapper userMapper;


    private static SysUser ADMIN = new SysUser();

    @PostConstruct
    public void init() {
        ADMIN = userMapper.selectUserByName("admin");
    }

    /**
     * 转商机时的方法
     *
     * @param business
     */
    @Override
    public Integer transforBusiness(TbBusiness business) {
        //默认分配给管理员
        TbAssignRecord tbAssignRecord = new TbAssignRecord();
        tbAssignRecord.setAssignId(business.getId());
        tbAssignRecord.setUserId(ADMIN.getUserId());
        tbAssignRecord.setUserName(ADMIN.getUserName());
        //这里是写死的admin所在部门的id
        SysUser admin = userMapper.selectUserByName(ADMIN.getUserName());
        tbAssignRecord.setDeptId(admin.getDeptId());
        tbAssignRecord.setCreateBy(CurrentUserHolder.getUserName());
        tbAssignRecord.setCreateTime(DateUtils.getNowDate());
        tbAssignRecord.setType(TbAssignRecord.RecordType.BUSNIESS.getValue());
        business.setNextTime(null);
        return assignRecordMapper.insertAssignRecord(tbAssignRecord);
    }
}
