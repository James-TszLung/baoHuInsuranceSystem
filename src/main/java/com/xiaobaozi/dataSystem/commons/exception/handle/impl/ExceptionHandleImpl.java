package com.xiaobaozi.dataSystem.commons.exception.handle.impl;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.exception.dao.ExceptionDao;
import com.xiaobaozi.dataSystem.commons.exception.handle.ExceptionHandle;
import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;
import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;

public class ExceptionHandleImpl extends GenericHandleImpl<ExceptionVO> implements ExceptionHandle {

	private ExceptionDao exceptionDao;
	
	public void setExceptionDao(ExceptionDao exceptionDao) {
		this.exceptionDao = exceptionDao;
	}

	@Override
	protected void initHandle() {
		dao = exceptionDao;
	}

	@Override
	public List<ExceptionVO> getListByPage(ExceptionSearchCriteria dsc) {
		return exceptionDao.getListByPage(dsc);
	}

	@Override
	public Integer getCount(ExceptionSearchCriteria dsc) {
		return exceptionDao.getCount(dsc);
	}
	

}
