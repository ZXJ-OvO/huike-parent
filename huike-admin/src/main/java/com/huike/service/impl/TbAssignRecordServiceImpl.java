package com.huike.service.impl;

import com.huike.domain.clues.TbAssignRecord;
import com.huike.mapper.TbAssignRecordMapper;
import com.huike.service.ITbAssignRecordService;
import com.huike.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 线索分配记录Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-19
 */
@Service
public class TbAssignRecordServiceImpl implements ITbAssignRecordService {
    @Autowired
    private TbAssignRecordMapper tbAssignRecordMapper;

    /**
     * 查询线索分配记录
     *
     * @param id 线索分配记录ID
     * @return 线索分配记录
     */
    @Override
    public TbAssignRecord selectAssignRecordById(Long id) {
        return tbAssignRecordMapper.selectAssignRecordById(id);
    }

    /**
     * 查询线索分配记录列表
     *
     * @param tbAssignRecord 线索分配记录
     * @return 线索分配记录
     */
    @Override
    public List<TbAssignRecord> selectAssignRecordList(TbAssignRecord tbAssignRecord) {
        return tbAssignRecordMapper.selectAssignRecordList(tbAssignRecord);
    }

    /**
     * 新增线索分配记录
     *
     * @param tbAssignRecord 线索分配记录
     * @return 结果
     */
    @Override
    public int insertAssignRecord(TbAssignRecord tbAssignRecord) {
        tbAssignRecord.setCreateTime(DateUtils.getNowDate());
        return tbAssignRecordMapper.insertAssignRecord(tbAssignRecord);
    }

    /**
     * 修改线索分配记录
     *
     * @param tbAssignRecord 线索分配记录
     * @return 结果
     */
    @Override
    public int updateTbClueAssignRecord(TbAssignRecord tbAssignRecord) {
        return tbAssignRecordMapper.updateAssignRecord(tbAssignRecord);
    }

    /**
     * 批量删除线索分配记录
     *
     * @param ids 需要删除的线索分配记录ID
     * @return 结果
     */
    @Override
    public int deleteAssignRecordByIds(Long[] ids) {
        return tbAssignRecordMapper.deleteAssignRecordByIds(ids);
    }

    /**
     * 删除线索分配记录信息
     *
     * @param id 线索分配记录ID
     * @return 结果
     */
    @Override
    public int deleteAssignRecordById(Long id) {
        return tbAssignRecordMapper.deleteAssignRecordById(id);
    }
}
