package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.PremiumCalculation;

public interface PremiumCalculationDao extends GenericDao<PremiumCalculation> {
    public int deleByProductId(String id);
}
