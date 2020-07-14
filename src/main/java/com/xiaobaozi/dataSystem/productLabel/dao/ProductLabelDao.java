package com.xiaobaozi.dataSystem.productLabel.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;

public interface ProductLabelDao extends GenericDao<ProductLabel> {

	public int getProductLabelCount(ProductLabelSearch sc);

	public List<ProductLabel> getProductLabelByPage(ProductLabelSearch sc);

}

