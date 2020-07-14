package com.xiaobaozi.dataSystem.productLabel.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;

public interface ProductLabelRelationDao extends GenericDao<ProductLabelRelation> {
	
	public List<ProductLabelRelation> selectByProductLabelId(String productLabelId);

	public int  delById(String id);



}

