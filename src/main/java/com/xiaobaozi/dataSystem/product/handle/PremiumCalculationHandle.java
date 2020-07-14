package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.PremiumCalculation;

public interface PremiumCalculationHandle extends GenericHandle<PremiumCalculation> {
    public int deleByProductId(String id);
}
