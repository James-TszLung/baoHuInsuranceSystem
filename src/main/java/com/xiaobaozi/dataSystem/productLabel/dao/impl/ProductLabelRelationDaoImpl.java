package com.xiaobaozi.dataSystem.productLabel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.productLabel.dao.ProductLabelRelationDao;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;

@Component("productLabelRelationDao")
public class ProductLabelRelationDaoImpl extends GenericDaoImpl<ProductLabelRelation> implements ProductLabelRelationDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public List<ProductLabelRelation> selectByProductLabelId(String dictionaryId) {
		return this.selectList("selectByProductLabelId", dictionaryId);
	}

	public int delById(String id){
		return this.deleteByMap("delById", id);
	}

}
