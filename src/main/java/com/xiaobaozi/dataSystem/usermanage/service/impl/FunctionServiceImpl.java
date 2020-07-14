package com.xiaobaozi.dataSystem.usermanage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.usermanage.handle.FunctionHandler;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.service.FunctionService;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

@Component("functionService")
public class FunctionServiceImpl extends GenericServiceImpl<Function> implements
		FunctionService {

	@Resource(name="functionHandler")
	private FunctionHandler functionHandler;

	@Override
	protected void initService() {
		handle = functionHandler;		
	}
	
	public List<Function> getFunctionListByPage(FunctionSearchCriteria sc){
		return functionHandler.getFunctionListByPage(sc);
	}
	
	public int getFunctionCount(FunctionSearchCriteria sc){
		return functionHandler.getFunctionCount(sc);
	}

	@Override
	public List<Function> queryMenu(Map<String, Object> map) {
		
		return functionHandler.queryMenu(map);
	}
}

