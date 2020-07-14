package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.GuaranteedRule;

public interface GuaranteedRuleDao extends GenericDao<GuaranteedRule> {
    public int deleByProductId(String id);
}
