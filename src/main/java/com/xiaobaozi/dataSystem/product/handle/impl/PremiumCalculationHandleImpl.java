package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.PremiumCalculationDao;
import com.xiaobaozi.dataSystem.product.handle.PremiumCalculationHandle;
import com.xiaobaozi.dataSystem.product.relation.PremiumCalculation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("premiumCalculationHandle")
public class PremiumCalculationHandleImpl extends GenericHandleImpl<PremiumCalculation> implements PremiumCalculationHandle {

    @Resource(name = "premiumCalculationDao")
    private PremiumCalculationDao premiumCalculationDao;

    protected void initHandle() {
        dao = premiumCalculationDao;
    }

    public int deleByProductId(String id){
        return premiumCalculationDao.deleByProductId(id);
    }
}
