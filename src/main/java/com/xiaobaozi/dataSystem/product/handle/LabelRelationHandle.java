package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.LabelRelation;

public interface LabelRelationHandle extends GenericHandle<LabelRelation> {
    public int deleByProductId(String id);
}
