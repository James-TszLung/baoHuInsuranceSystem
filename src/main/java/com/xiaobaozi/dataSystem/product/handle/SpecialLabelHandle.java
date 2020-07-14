package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.SpecialLabel;

public interface SpecialLabelHandle extends GenericHandle<SpecialLabel> {
    public int deleByProductId(String id);
}
