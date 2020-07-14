package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;

import java.util.List;

public interface GuaranteeRelationHandle extends GenericHandle<GuaranteeRelation> {
    public int deleByProductId(String id);
    public List<GuaranteeRelation> selectByProductId(String productId);
    public int deleById(String id);
}
