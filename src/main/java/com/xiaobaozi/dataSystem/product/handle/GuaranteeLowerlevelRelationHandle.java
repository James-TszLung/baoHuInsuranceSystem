package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeLowerlevelRelation;

public interface GuaranteeLowerlevelRelationHandle extends GenericHandle<GuaranteeLowerlevelRelation> {
    public int deleByGuaranteeRelationId(String id);
}
