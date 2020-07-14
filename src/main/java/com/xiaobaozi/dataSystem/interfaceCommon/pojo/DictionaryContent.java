package com.xiaobaozi.dataSystem.interfaceCommon.pojo;

import java.io.Serializable;
/**
 * 字典內容 关联字典
 * @author media
 *
 */
public class DictionaryContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4574742655444319550L;
	private String id; 
	private String dictionaryId;  //关联的字典Id
	private String content; //內容
	private int sort; //排序
	
	
	private boolean flag;
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	

}
