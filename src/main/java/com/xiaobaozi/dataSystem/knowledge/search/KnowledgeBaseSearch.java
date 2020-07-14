package com.xiaobaozi.dataSystem.knowledge.search;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

/**
 * 知识库
 * @author media
 *
 */
public class KnowledgeBaseSearch extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1828072545568765569L;
	/**
	 * 
	 */
	private String id; 
	private String name; //知识模块名称
	private String describe; //描述
	private int showLocation;//展示位置  1:位置1  。。  如此推
	private String covermapId; // 封面图id
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getShowLocation() {
		return showLocation;
	}
	public void setShowLocation(int showLocation) {
		this.showLocation = showLocation;
	}
	public String getCovermapId() {
		return covermapId;
	}
	public void setCovermapId(String covermapId) {
		this.covermapId = covermapId;
	}
	
	
	
	
	

}
