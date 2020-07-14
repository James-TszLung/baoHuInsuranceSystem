package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.OtherGuaranteeRelation;

public interface OtherGuaranteeRelationHandle extends GenericHandle<OtherGuaranteeRelation> {
    public int deleByProductId(String id);
}
