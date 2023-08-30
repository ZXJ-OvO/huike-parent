package com.huike.domain.clues.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClueReportVo {

    private Long id;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 活动
     */
    private String code;

    /**
     * 活动详情
     */
    private String info;

    /**
     * 所属人
     */
    private String owner;

    /**
     * 所属部门
     */
    private String deptId;

    /**
     * 线索状态
     */
    private String clueStatus;

    /**
     * 线索创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date clueCreateTime;

    /**
     * 转换商机时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date businessCreateTime;

    /**
     * 商机状态
     */
    private String businessStatus;

    /**
     * 合同成交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contractCreateTime;

    /**
     * 订单金额
     */
    private Double order;

}
