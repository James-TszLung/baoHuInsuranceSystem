package com.xiaobaozi.dataSystem.product.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.ProductDao;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

@Component("productDao")
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public int getProductCount(ProductSearch sc) {
		return (Integer) this.selectOne("countProduct", sc);
	}

	public List<Product> getProductByPage(ProductSearch sc) {
		return selectList("getListByPage", sc);
	}

	public int getProductConsultationCount(ProductSearch sc) {
		return (Integer) this.selectOne("countProductConsultation", sc);
	}

	public List<Product> getProductConsultationByPage(ProductSearch sc) {
		return selectList("getConsultationListByPage", sc);
	}

	public Product getProductById(String id) {
		return (Product) this.selectOne("selectById3",id);
	}

	public int deleteProductById(String id){
		return this.deleteByMap("deleteProductById", id);
	}

}
