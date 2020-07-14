package com.xiaobaozi.dataSystem.knowledge.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 知识库
 * @author media
 *
 */
public class KnowledgeBase implements Serializable {

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
	private Date createTime;
	
	private int essayCount; //收录推文数
	private List<KnowledgeRelationPushEssay> konwRelationLs;
	
	
	
	
	
	public int getEssayCount() {
		return essayCount;
	}
	public void setEssayCount(int essayCount) {
		this.essayCount = essayCount;
	}
	public List<KnowledgeRelationPushEssay> getKonwRelationLs() {
		return konwRelationLs;
	}
	public void setKonwRelationLs(List<KnowledgeRelationPushEssay> konwRelationLs) {
		this.konwRelationLs = konwRelationLs;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
