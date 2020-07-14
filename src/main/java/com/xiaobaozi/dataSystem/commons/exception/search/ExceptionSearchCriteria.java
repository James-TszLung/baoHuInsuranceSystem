package com.xiaobaozi.dataSystem.commons.exception.search;

import java.util.Date;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.commons.utils.StringUtil;

public class ExceptionSearchCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date startTime;
	private Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSearchKey(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getName());
		sb.append(id);
		sb.append(startTime);
		sb.append(endTime);
		sb.append(super.getPageNo());
		sb.append(super.getPageSize());
		sb.append(super.getOrderBy());
		return  StringUtil.MD5(sb.toString());		
	}
	
}
