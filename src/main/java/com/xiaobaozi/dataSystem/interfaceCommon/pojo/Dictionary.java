package com.xiaobaozi.dataSystem.interfaceCommon.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 数字字典
 * 
 * @author media
 * 
 */
public class Dictionary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 748517547321450764L;
	private String id;
	private String name; // 数字字典
	private String createName; // 创建人
	private Date createTime; // 创建时间
	private List<DictionaryContent> contentLs;
	
	
	

	public List<DictionaryContent> getContentLs() {
		return contentLs;
	}

	public void setContentLs(List<DictionaryContent> contentLs) {
		this.contentLs = contentLs;
	}

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
