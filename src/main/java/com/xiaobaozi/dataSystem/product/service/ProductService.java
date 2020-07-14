package com.xiaobaozi.dataSystem.product.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface ProductService extends GenericService<Product> {
	public int getProductCount(ProductSearch sc);

	public List<Product> getProductByPage(ProductSearch sc);

	public int getProductConsultationCount(ProductSearch sc);

	public List<Product> getProductConsultationByPage(ProductSearch sc);

	public int insertProduct(Product sc) throws Exception;

	public Product getProductById(String id);

	public boolean editProduct(Product sc) throws Exception;

	public int deleteProductById(Product sc) throws Exception;

}
