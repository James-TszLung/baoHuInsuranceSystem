package com.xiaobaozi.dataSystem.interfaceCommon.search;

import java.util.Date;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

/**
 * 数字字典
 * @author media
 *
 */
public class DictionarySearch extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 748517547321450764L;
	private String id;
	private String name;  //数字字典
	private String createName; //创建人
	private Date createTime; //创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
