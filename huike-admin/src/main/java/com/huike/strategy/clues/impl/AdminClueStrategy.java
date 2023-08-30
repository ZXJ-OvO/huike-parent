package com.huike.strategy.clues.impl;

import com.huike.domain.clues.TbAssignRecord;
import com.huike.domain.clues.TbClue;
import com.huike.domain.system.SysUser;
import com.huike.mapper.SysUserMapper;
import com.huike.mapper.TbAssignRecordMapper;
import com.huike.strategy.clues.Rule;
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
@ConditionalOnProperty(name = "rule.clue.import", havingValue = "admin")
@Service("ClueAdminStrategy")
public class AdminClueStrategy implements Rule {

    @Autowired
    private TbAssignRecordMapper assignRecordMapper;

    @Autowired
    private SysUserMapper userMapper;

    private static SysUser ADMIN = new SysUser();

    @PostConstruct
    public void init() {
        ADMIN = userMapper.selectUserByName("admin");
    }

    @Override
    public Boolean loadRule(TbClue clue) {
        try {
            TbAssignRecord tbAssignRecord = new TbAssignRecord();
            tbAssignRecord.setAssignId(clue.getId());
            tbAssignRecord.setUserId(CurrentUserHolder.getAdmin());
            tbAssignRecord.setUserName(ADMIN.getUserName());
            tbAssignRecord.setDeptId(ADMIN.getDeptId());
            tbAssignRecord.setCreateBy(CurrentUserHolder.getUserName());
            tbAssignRecord.setCreateTime(clue.getCreateTime());
            assignRecordMapper.insertAssignRecord(tbAssignRecord);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

