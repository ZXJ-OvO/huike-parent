package com.huike.domain.clues;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 活动管理对象 tb_activity
 */
@Data
public class TbActivity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    //编码
    private String code;

    private String name;

    /**
     * 渠道来源
     */
    @Excel(name = "渠道来源")
    private String channel;

    /**
     * 活动简介
     */
    @Excel(name = "活动简介")
    private String info;

    /**
     * 活动类型
     */
    @Excel(name = "活动类型")
    private String type;

    /**
     * 课程折扣
     */
    @Excel(name = "课程折扣")
    private Float discount;

    /**
     * 课程代金券
     */
    @Excel(name = "课程代金券")
    private Integer vouchers;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

}
