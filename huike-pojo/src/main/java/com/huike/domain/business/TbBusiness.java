package com.huike.domain.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.common.annotation.Excel;
import com.huike.domain.clues.TbClue;
import lombok.Data;

import java.util.Date;


/**
 * 线索对象 tb_business
 */
@Data
public class TbBusiness extends TbClue {

    private static final long serialVersionUID = 1L;

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

    private String region;

    /**
     * 课程
     */
    @Excel(name = "课程")
    private Long courseId;

    /**
     * 职业
     */
    @Excel(name = "职业")
    private String occupation;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private String education;

    /**
     * 在职情况
     */
    @Excel(name = "在职情况")
    private String job;

    /**
     * 薪资
     */
    @Excel(name = "薪资")
    private String salary;

    /**
     * 专业
     */
    @Excel(name = "专业")
    private String major;

    /**
     * 希望薪资
     */
    @Excel(name = "希望薪资")
    private String expectedSalary;

    /**
     * 学习原因
     */
    @Excel(name = "学习原因")
    private String reasons;

    /**
     * 职业计划
     */
    @Excel(name = "职业计划")
    private String plan;

    /**
     * 计划时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "计划时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planTime;

    /**
     * 其他意向
     */
    @Excel(name = "其他意向")
    private String otherIntention;

    /**
     * 下次跟进时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "下次跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextTime;

    /**
     * 状态(已分配1  进行中2  回收3)
     */
    @Excel(name = "状态(已分配1  进行中2  回收3 完成4)")
    private String status;

    //转换使用
    private Long clueId;

//    //开始时间
//    private String beginCreateTime;
//
//    //结束时间
//    private String endCreateTime;

}
