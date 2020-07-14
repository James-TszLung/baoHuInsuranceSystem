package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.CooperationSupplier;

public interface CooperationSupplierDao extends GenericDao<CooperationSupplier> {

	public int deleByProductId(String id);

}
