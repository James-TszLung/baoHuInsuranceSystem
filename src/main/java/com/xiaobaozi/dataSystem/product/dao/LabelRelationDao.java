package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.LabelRelation;

public interface LabelRelationDao extends GenericDao<LabelRelation> {
    public int deleByProductId(String id);
}
