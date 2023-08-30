package com.huike.service;

import com.huike.domain.business.TbBusiness;
import com.huike.domain.business.TbBusinessTrackRecord;

import java.util.List;

/**
 * 商机跟进记录Service接口
 *
 * @date 2021-04-28
 */
public interface ITbBusinessTrackRecordService {


    /**
     * 新增商机跟进记录
     *
     * @param tbBusiness
     * @param tbBusinessTrackRecord
     * @return
     */
    public int insertTbBusinessTrackRecord(TbBusiness tbBusiness, TbBusinessTrackRecord tbBusinessTrackRecord);


    /**
     * 跟进商机id查询商机跟进记录
     *
     * @param id
     * @return
     */
    public List<TbBusinessTrackRecord> selectTbBusinessTrackRecordList(Long id);
}
