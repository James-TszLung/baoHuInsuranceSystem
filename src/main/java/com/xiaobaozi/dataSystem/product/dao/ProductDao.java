package com.xiaobaozi.dataSystem.product.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface ProductDao extends GenericDao<Product> {

	public int getProductCount(ProductSearch sc);

	public List<Product> getProductByPage(ProductSearch sc);
	
	public int getProductConsultationCount(ProductSearch sc);

	public List<Product> getProductConsultationByPage(ProductSearch sc);

	public Product getProductById(String id);

	public int deleteProductById(String id);

}

