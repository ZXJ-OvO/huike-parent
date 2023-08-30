package com.huike.strategy.clues;

import com.huike.domain.clues.TbClue;


/**
 * 规则策略
 * 根据rule.strategy配置的属性来判断使用何种方式进行分配
 * admin 选择adminStrategy作为策略的实现类
 * rule 选择RuleStrategy作为策略的实现类
 */
public interface Rule {

    /**
     * 单条线索添加的规则
     */
    public Boolean loadRule(TbClue clue);
}
