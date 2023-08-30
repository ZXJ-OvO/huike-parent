package com.huike.domain.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商机跟进记录对象 tb_business_track_record
 */
@Data
public class TbBusinessTrackRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private Long id;

    /**
     * 线索id
     */
    @Excel(name = "线索id")
    private Long businessId;

    /**
     * 沟通重点
     */
    @Excel(name = "沟通重点")
    private String keyItems;

    private List<String> keys = new ArrayList<>();

    /**
     * 沟通纪要
     */
    @Excel(name = "沟通纪要")
    private String record;

    /**
     * 跟进状态
     */
    @Excel(name = "跟进状态")
    private String trackStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date nextTime;

}
