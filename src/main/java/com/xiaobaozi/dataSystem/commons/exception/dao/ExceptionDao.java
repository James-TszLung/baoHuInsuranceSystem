package com.xiaobaozi.dataSystem.commons.exception.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;

public interface ExceptionDao extends GenericDao<ExceptionVO> {

	public List<ExceptionVO> getListByPage(ExceptionSearchCriteria dsc);
	public Integer getCount(ExceptionSearchCriteria dsc);
}
