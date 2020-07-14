package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.SpecialLabel;

public interface SpecialLabelDao extends GenericDao<SpecialLabel> {
    public int deleByProductId(String id);
}
