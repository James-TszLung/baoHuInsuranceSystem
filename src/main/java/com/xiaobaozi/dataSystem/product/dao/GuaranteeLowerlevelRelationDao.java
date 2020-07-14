package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeLowerlevelRelation;

public interface GuaranteeLowerlevelRelationDao extends GenericDao<GuaranteeLowerlevelRelation> {

    public int deleByGuaranteeRelationId(String id);
}
