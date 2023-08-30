package com.huike.domain.clues;

import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

/**
 * 线索分配记录对象 tb_assign_record
 */
@Data
public class TbAssignRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * id
     */
    @Excel(name = "assign_id")
    private Long assignId;

    /**
     * 所属人用户id
     */
    @Excel(name = "所属人用户id")
    private Long userId;

    /**
     * 所属人名称
     */
    @Excel(name = "所属人名称")
    private String userName;

    /**
     * 所属人所属组织
     */
    @Excel(name = "所属人所属组织")
    private Long deptId;

    /**
     * 是否当前最新分配人
     */
    @Excel(name = "是否当前最新分配人")
    private String latest;

    @Excel(name = "类型0 线索 1 商机")
    private String type = "0";

    // private String status;

    public enum RecordType {
        /**
         * 线索
         */
        CLUES("0"),

        /**
         * 商机
         */
        BUSNIESS("1");


        private String value;

        private RecordType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
