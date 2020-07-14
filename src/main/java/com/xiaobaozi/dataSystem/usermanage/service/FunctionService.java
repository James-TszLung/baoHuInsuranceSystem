package com.xiaobaozi.dataSystem.usermanage.service;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

public interface FunctionService extends GenericService<Function>{

	/**
	 * 分页查询模块
	 * @param sc 分页查询时必须带有页码和每页数量
 	 * @return
	 */
	public List<Function> getFunctionListByPage(FunctionSearchCriteria sc);
	/**
	 * 根据条件查询符合的模块数量
	 * @param sc
	 * @return
	 */
	public int getFunctionCount(FunctionSearchCriteria sc);
	
	public List<Function> queryMenu(Map<String, Object> map);
}
