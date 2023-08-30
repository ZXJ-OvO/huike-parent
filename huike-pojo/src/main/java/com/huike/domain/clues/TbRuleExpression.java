package com.huike.domain.clues;

import com.huike.common.annotation.Excel;
import com.huike.domain.common.BaseEntity;
import lombok.Data;

/**
 * 规则达式对象 tb_rule_expression
 */
@Data
public class TbRuleExpression extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 规则表达式id
     */
    private Long id;

    /**
     * 规则key
     */
    @Excel(name = "规则key")
    private String ruleKey;

    /**
     * 表达式（=，!=, 包含，不包含)
     */
    @Excel(name = "表达式", readConverterExp = "表达式（=，!=, 包含，不包含) ")
    private String expression;

    /**
     * 规则值
     */
    @Excel(name = "规则值")
    private String ruleValue;

    /**
     * 顺序
     */
    @Excel(name = "顺序")
    private int number;

    /**
     * 规则id
     */
    @Excel(name = "规则id")
    private Long ruleId;

}
