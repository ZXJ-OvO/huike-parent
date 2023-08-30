package com.huike.domain.clues;

import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

/**
 * 课程管理对象 tb_course
 */
@Data
public class TbCourse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    private Long id;

    /**
     * 课程名称
     */
    @Excel(name = "课程名称")
    private String name;

    /**
     * 课程编码
     */
    private String code;

    /**
     * 课程学科
     */
    @Excel(name = "课程学科")
    private String subject;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private Integer price;

    /**
     * 适用人群
     */
    @Excel(name = "适用人群")
    private String applicablePerson;

    /**
     * 课程描述信息
     */
    @Excel(name = "课程描述信息")
    private String info;

}
