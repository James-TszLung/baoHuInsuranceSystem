package com.xiaobaozi.dataSystem.usermanage.handle.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.FunctionDao;
import com.xiaobaozi.dataSystem.usermanage.handle.FunctionHandler;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;
@Component("functionHandler")
public class FunctionHandlerImpl extends GenericHandleImpl<Function> implements
		FunctionHandler {

	@Resource(name="functionDao")
	private FunctionDao functionDao;

	@Override
	public List<Function> getFunctionListByPage(FunctionSearchCriteria sc) {
		return functionDao.getFunctionList(sc);
	}

	@Override
	public int getFunctionCount(FunctionSearchCriteria sc) {
		return functionDao.getCountFunction(sc);
	}

	@Override
	protected void initHandle() {
		dao = functionDao;
	}

	@Override
	public List<Function> queryMenu(Map<String, Object> map) {
		
		return functionDao.queryMenu(map);
	}

}
