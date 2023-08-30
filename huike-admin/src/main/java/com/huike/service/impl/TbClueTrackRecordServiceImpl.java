package com.huike.service.impl;

import com.huike.domain.clues.TbClue;
import com.huike.domain.clues.TbClueTrackRecord;
import com.huike.mapper.TbClueMapper;
import com.huike.mapper.TbClueTrackRecordMapper;
import com.huike.service.ITbClueTrackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 线索跟进记录Service业务层处理
 *
 * @date 2021-04-22
 */
@Service
public class TbClueTrackRecordServiceImpl implements ITbClueTrackRecordService {
    @Autowired
    private TbClueTrackRecordMapper tbClueTrackRecordMapper;

    @Autowired
    private TbClueMapper tbClueMapper;


    /**
     * 添加线索跟进记录
     *
     * @param tbClue
     * @param tbClueTrackRecord
     * @return
     */
    @Override
    @Transactional
    public int insertTbClueTrackRecord(TbClue tbClue, TbClueTrackRecord tbClueTrackRecord) {
        tbClueMapper.updateTbClue(tbClue);
        return tbClueTrackRecordMapper.insertTbClueTrackRecord(tbClueTrackRecord);
    }

    /**
     * 根据线索id查询线索跟进记录
     */
    @Override
    public List<TbClueTrackRecord> selectTbClueTrackRecordList(Long clueId) {
        return tbClueTrackRecordMapper.selectTbClueTrackRecordListByClueId(clueId);
    }
}
