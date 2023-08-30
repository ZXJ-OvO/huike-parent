package com.huike.domain.clues;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 线索管理对象 tb_clue
 */
@Data
public class TbClue extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 线索id
     */
    private Long id;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名")
    private String name;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 渠道
     */
    @Excel(name = "渠道")
    private String channel;

    /**
     * 活动id
     */
    @Excel(name = "活动id")
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动名称
     */
    private String activityInfo;

    /**
     * 1 男 0 女
     */
    @Excel(name = "1 男 0 女")
    private String sex;

    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private Integer age;

    /**
     * 微信
     */
    @Excel(name = "微信")
    private String weixin;

    /**
     * qq
     */
    @Excel(name = "qq")
    private String qq;

    /**
     * 意向等级
     */
    @Excel(name = "意向等级")
    private String level;

    /**
     * 意向学科
     */
    @Excel(name = "意向学科")
    private String subject;

    /**
     * 状态(已分配1  进行中2  回收3  伪线索4)
     */
    @Excel(name = "状态(已分配1  进行中2  回收3  伪线索4)")
    private String status;

    /**
     * 分配人
     */
    @Excel(name = "分配人")
    private String assignBy;

    /**
     * 分配时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date assignTime;

    /**
     * 所属人
     */
    @Excel(name = "所属人")
    private String owner;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ownerTime;


    /**
     * 伪线索失败次数(最大数3次)
     */
    @Excel(name = "伪线索失败次数(最大数3次)")
    private int falseCount;

    /**
     * 下次跟进时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date nextTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    /**
     * 创建人id
     */
    @Excel(name = "创建人id")
    private Long userId;

    @JsonIgnore
    private String createTimeStr;

    private String transfer; //是否转派 默认0


    public enum StatusType {
        UNFOLLOWED("待跟进", "1"),
        FOLLOWING("跟进中", "2"),
        RECOVERY("回收", "3"),
        FALSE("伪线索/踢回公海", "4"),
        DELETED("删除", "5"),
        TOBUSINESS("转换商机", "6"),
        TOCUSTOMER("转换客户", "7");

        private String name;
        private String value;

        private StatusType(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ImportDictType {

        CHANNEL("渠道来源", "clues_item"),
        SUBJECT("课程学科", "course_subject"),
        LEVEL("线索意向级别", "clues_level"),
        SEX("性别", "sys_user_sex");


        private String name;
        private String dictType;

        private ImportDictType(String name, String dictType) {
            this.name = name;
            this.dictType = dictType;
        }

        public String getName() {
            return name;
        }

        public String getDictType() {
            return dictType;
        }
    }


}


