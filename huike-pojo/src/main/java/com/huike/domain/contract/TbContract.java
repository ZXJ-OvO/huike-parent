package com.huike.domain.contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 合同对象 tb_contract
 */
@Data
public class TbContract extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    private String id;

    /**
     * 合同编号
     */
    @Excel(name = "合同编号")
    private String contractNo;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String name;

    /**
     * 意向学科
     */
    @Excel(name = "学科")
    private String subject;

    private String channel; //渠道

    /**
     * 活动信息
     */
    @Excel(name = "活动信息")
    private Long activityId;

    /**
     * 课程id
     */
    @Excel(name = "课程id")
    private Long courseId;

    /**
     * 状态(待审核0，已完成1，已驳回2)
     */
    @Excel(name = "状态(待审核0，已完成1，已驳回2)")
    private String status;


    /**
     * 文件名称
     */
    @Excel(name = "文件名称")
    private String fileName;


    //课程价格
    private float coursePrice;

    //活动折扣类型
    private String discountType;

    private float order;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    private Long businessId;

    private Long deptId;


    public enum AssigneeUser {
        Assignee1(137L, "xszgtest123", "销售主管"),

        Assignee2(152L, "zjltest", "总经理"),

        Assignee3(153L, "caiwuzhuguan", "财务主管");


        private Long id;
        private String name;
        private String desc;

        public String getDesc() {
            return desc;
        }

        private AssigneeUser(Long id, String name, String desc) {
            this.id = id;
            this.name = name;
            this.desc = desc;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}
