package com.xiaobaozi.dataSystem.product.dao.impl;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.CooperationSupplierDao;
import com.xiaobaozi.dataSystem.product.relation.CooperationSupplier;

@Component("cooperationSupplierDao")
public class CooperationSupplierDaoImpl extends GenericDaoImpl<CooperationSupplier> implements CooperationSupplierDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public int deleByProductId(String id){
		return this.deleteByMap("deleByProductId", id);
	}

}
