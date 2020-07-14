package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.GuaranteedRule;

public interface GuaranteedRuleHandle extends GenericHandle<GuaranteedRule> {
    public int deleByProductId(String id);
}
