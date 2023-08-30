package com.huike.domain.clues.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClueTrackRecordVo {

    /**
     * 线索id
     */
    private Long clueId;

    /**
     * 意向等级
     */
    private String subject;

    /**
     * 跟进记录
     */
    private String record;

    /**
     * 意向等级
     */
    private String level;

    /**
     * 0 正常跟进记录 1 伪线索
     */
    private String type;

    /**
     * 原因
     */
    private String falseReason;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 1 男 0 女
     */
    private String sex;

    /**
     * 微信
     */
    private String weixin;

    /**
     * qq
     */
    private String qq;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date nextTime;

}
