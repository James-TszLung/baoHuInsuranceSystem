package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;

import java.util.List;

public interface GuaranteeRelationDao extends GenericDao<GuaranteeRelation> {
    public int deleByProductId(String id);

    public List<GuaranteeRelation> selectByProductId(String productId);

    public int deleById(String id);
}
