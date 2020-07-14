package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.OtherGuaranteeRelation;

public interface OtherGuaranteeRelationDao extends GenericDao<OtherGuaranteeRelation> {
    public int deleByProductId(String id);
}
