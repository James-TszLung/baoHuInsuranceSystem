package com.xiaobaozi.dataSystem.productLabel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.productLabel.dao.ProductLabelDao;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;

@Component("productLabelDao")
public class ProductLabelDaoImpl extends GenericDaoImpl<ProductLabel> implements ProductLabelDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getProductLabelCount(ProductLabelSearch sc) {
		return (Integer) this.selectOne("countProductLabel", sc);
	}

	@Override
	public List<ProductLabel> getProductLabelByPage(ProductLabelSearch sc) {
		return selectList("getListByPage", sc);
	}

}
