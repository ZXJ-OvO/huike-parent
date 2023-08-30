package com.huike.strategy.business;

import com.huike.domain.business.TbBusiness;

/**
 * 线索转商机使用的规则
 */
public interface Rule {

    Integer transforBusiness(TbBusiness tbBusiness);
}
