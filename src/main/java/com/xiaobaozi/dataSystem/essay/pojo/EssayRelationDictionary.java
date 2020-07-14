package com.xiaobaozi.dataSystem.essay.pojo;

import java.io.Serializable;
/**
 * 推文关联数字字典
 * @author media
 *
 */
public class EssayRelationDictionary implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5379517566086556849L;
	private String dictionaryId; //数字字典id
	private String essayId; //推文id
	private String content;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getEssayId() {
		return essayId;
	}
	public void setEssayId(String essayId) {
		this.essayId = essayId;
	}
	
	
	
	

}
