package com.xiaobaozi.dataSystem.commons.exception.service;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;
import com.xiaobaozi.dataSystem.commons.service.GenericService;


public interface ExceptionService extends GenericService<ExceptionVO> {

	/**
	 * 分页查询
	 * @param dsc 查询条件实体类
	 * @return
	 */
	public List<ExceptionVO> getListByPage(ExceptionSearchCriteria dsc);
	/**
	 * 统计记录总数
	 * @param dsc 查询条件实体类
	 * @return
	 */
	public Integer getCount(ExceptionSearchCriteria dsc);
	
	/**
	 * 删除多个id记录
	 * @param ids
	 * @return
	 */
	public Map mulitedelete(String ids);
}
