package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.IncrementRelation;

public interface IncrementRelationDao extends GenericDao<IncrementRelation> {
    public int deleByProductId(String id);
}
