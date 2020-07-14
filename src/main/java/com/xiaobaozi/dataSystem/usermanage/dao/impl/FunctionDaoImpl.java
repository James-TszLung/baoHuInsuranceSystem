package com.xiaobaozi.dataSystem.usermanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.FunctionDao;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

@Component("functionDao")
public class FunctionDaoImpl extends GenericDaoImpl<Function> implements FunctionDao {

	@Override
	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}
	
	@Override
	public List<Function> getFunctionList(FunctionSearchCriteria sc) {
		return  selectList("getFunctionListByPage", sc);
	}

	@Override
	public int getCountFunction(FunctionSearchCriteria sc) {
		return (Integer)this.selectOne("countFunction",sc);
	}

	@Override
	public List<Function> queryMenu(Map<String, Object> map) {
		
		return selectList("queryMenu", map);
	}

}
