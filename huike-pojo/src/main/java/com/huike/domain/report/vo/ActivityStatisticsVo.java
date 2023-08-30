package com.huike.domain.report.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityStatisticsVo {

    private String channel; //渠道
    private String name;  //活动名称
    private String info; //活动简介
    private String code; //活动编码
    private Integer cluesNum = 0;  //线索总数
    private Integer falseCluesNum = 0;  //伪线索数目
    private Integer businessNum = 0;  //转换商机数目
    private Integer customersNum = 0;  //转换客户数目
    private Double amount; //成交金额
    private Double cost; //活动成本
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //创建时间

}
