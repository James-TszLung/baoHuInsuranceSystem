package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.IncrementRelation;

public interface IncrementRelationHandle extends GenericHandle<IncrementRelation> {
    public int deleByProductId(String id);
}
