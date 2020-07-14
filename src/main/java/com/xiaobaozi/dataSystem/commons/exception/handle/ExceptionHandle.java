package com.xiaobaozi.dataSystem.commons.exception.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;
import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;

public interface ExceptionHandle extends GenericHandle<ExceptionVO> {

	
	public List<ExceptionVO> getListByPage(ExceptionSearchCriteria dsc);
	public Integer getCount(ExceptionSearchCriteria dsc);

}
