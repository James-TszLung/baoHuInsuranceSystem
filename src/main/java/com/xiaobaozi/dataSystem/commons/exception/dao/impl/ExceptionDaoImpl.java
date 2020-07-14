package com.xiaobaozi.dataSystem.commons.exception.dao.impl;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.commons.exception.dao.ExceptionDao;
import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;

public class ExceptionDaoImpl extends GenericDaoImpl<ExceptionVO> implements ExceptionDao {

	@Override
	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();	
	}

	@Override
	public List<ExceptionVO> getListByPage(ExceptionSearchCriteria dsc) {
		return selectList("getExceptionListByPage",dsc);
	}

	@Override
	public Integer getCount(ExceptionSearchCriteria dsc) {
		return (Integer) this.selectOne("countException", dsc);
	}


}
