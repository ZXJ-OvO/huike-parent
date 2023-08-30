package com.huike.domain.clues;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 线索跟进记录对象 tb_clue_track_record
 */
@Data
public class TbClueTrackRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private Long id;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date nextTime;

}
