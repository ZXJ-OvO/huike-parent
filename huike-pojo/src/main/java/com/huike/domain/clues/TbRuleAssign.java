package com.huike.domain.clues;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则分配对象 tb_rule_assign
 */
@Data
public class TbRuleAssign extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 线索id
     */
    private Long id;

    /**
     * 规则集合
     */
    @Excel(name = "规则集合")
    private String ruleCondition;

    /**
     * 规则表达式
     */
    @Excel(name = "规则表达式")
    @JsonIgnore
    private String ruleExpression;

    /**
     * 分配对象
     */
    @Excel(name = "分配对象")
    private String assignObject;

    private List<String> assignObjectNames = new ArrayList<>();

    /**
     * 分配类型(本人,角色，组织)
     */
    @Excel(name = "分配类型(本人,角色，组织)")
    private String assignType;

    /**
     * 规则顺序
     */
    @Excel(name = "规则顺序")
    private Long ruleOrder;

    private String type;

    private List<TbRuleExpression> rulesList;

    public enum AssignType {
        /**
         * 小时
         */
        USER("0"),

        /**
         * 天
         */
        POST("1"),

        /**
         * 部门
         */
        DEPARTMENT("2"),

        /**
         * 角色
         */
        ROLE("3");


        private String value;

        private AssignType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
