package com.huike.service.impl;

import com.huike.domain.business.TbBusiness;
import com.huike.domain.business.TbBusinessTrackRecord;
import com.huike.mapper.TbBusinessMapper;
import com.huike.mapper.TbBusinessTrackRecordMapper;
import com.huike.service.ITbBusinessTrackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商机跟进记录Service业务层处理
 */
@Service
public class TbBusinessTrackRecordServiceImpl implements ITbBusinessTrackRecordService {
    @Autowired
    private TbBusinessTrackRecordMapper tbBusinessTrackRecordMapper;

    @Autowired
    private TbBusinessMapper tbBusinessMapper;


    @Override
    public int insertTbBusinessTrackRecord(TbBusiness tbBusiness, TbBusinessTrackRecord tbBusinessTrackRecord) {
        tbBusinessMapper.updateTbBusiness(tbBusiness);
        return tbBusinessTrackRecordMapper.insertTbBusinessTrackRecord(tbBusinessTrackRecord);
    }

    /**
     * 跟进商机id查询商机跟进记录
     */
    @Override
    public List<TbBusinessTrackRecord> selectTbBusinessTrackRecordList(Long id) {
        return tbBusinessTrackRecordMapper.selectTbBusinessTrackRecordListByBusinessId(id);
    }
}
