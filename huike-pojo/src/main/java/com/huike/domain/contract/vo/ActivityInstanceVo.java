package com.huike.domain.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityInstanceVo {

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String status = "2";

    private String rejectionReasons;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rejectionTime;

    public enum ActivityType {
        STEP1("销售专员添加合同", "1"),

        STEP2("销售主管审核", "2"),

        STEP3("总经理审批", "3"),

        STEP4("财务审批", "4");

        private String name;
        private String dictType;

        private ActivityType(String name, String dictType) {
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
