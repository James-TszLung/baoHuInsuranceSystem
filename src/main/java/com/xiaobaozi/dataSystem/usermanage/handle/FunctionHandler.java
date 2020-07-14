package com.xiaobaozi.dataSystem.usermanage.handle;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

public interface FunctionHandler extends GenericHandle<Function>{

	public List<Function> getFunctionListByPage(FunctionSearchCriteria sc);
	public int getFunctionCount(FunctionSearchCriteria sc);
	
	public List<Function> queryMenu(Map<String, Object> map);
}
