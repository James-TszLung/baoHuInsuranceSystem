package com.xiaobaozi.dataSystem.product.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.ProductDao;
import com.xiaobaozi.dataSystem.product.handle.ProductHandle;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

@Component("productHandle")
public class ProductHandleImpl extends GenericHandleImpl<Product> implements ProductHandle {

	@Resource(name = "productDao")
	private ProductDao productDao;

	@Override
	protected void initHandle() {
		dao = productDao;
	}

	public int getProductCount(ProductSearch sc) {
		return productDao.getProductCount(sc);
	}

	public List<Product> getProductByPage(ProductSearch sc) {
		return productDao.getProductByPage(sc);
	}

	public int getProductConsultationCount(ProductSearch sc) {
		return productDao.getProductConsultationCount(sc);
	}

	public List<Product> getProductConsultationByPage(ProductSearch sc) {
		return productDao.getProductConsultationByPage(sc);
	}

	public Product getProductById(String id) {
		return productDao.getProductById(id);
	}

	public int deleteProductById(String id){
		return productDao.deleteProductById(id);
	}

}
