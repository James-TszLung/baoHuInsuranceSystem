package com.xiaobaozi.dataSystem.usermanage.dao;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

public interface FunctionDao extends GenericDao<Function>{

	public List<Function> getFunctionList(FunctionSearchCriteria us);
	public int getCountFunction(FunctionSearchCriteria us);
	
	public List<Function> queryMenu(Map<String, Object> map);
}
