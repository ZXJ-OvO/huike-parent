package com.huike.domain.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessTrackVo {

    private Long businessId;

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
    private Long channelId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 省
     */
    @Excel(name = "省")
    private String provinces;

    /**
     * 区
     */
    @Excel(name = "区")
    private String city;

    /**
     * 男或者女
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 微信
     */
    private String weixin;

    /**
     * qq
     */
    private String qq;

    /**
     * 意向等级
     */
    private String level;

    /**
     * 意向学科
     */
    private String subject;

    /**
     * 课程
     */
    private Long courseId;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 学历
     */
    private String education;

    /**
     * 在职情况
     */
    private String job;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 专业
     */
    private String major;

    /**
     * 希望薪资
     */
    private String expectedSalary;

    /**
     * 学习原因
     */
    private String reasons;

    /**
     * 职业计划
     */
    private String plan;

    /**
     * 计划时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planTime;

    /**
     * 其他意向
     */
    @Excel(name = "其他意向")
    private String otherIntention;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date nextTime;

    //沟通备注
    private String remark;

    //沟通重点
    private String keyItems;


    /**
     * 沟通纪要
     */
    private String record;

    /**
     * 跟进状态
     */
    private String trackStatus;

}
