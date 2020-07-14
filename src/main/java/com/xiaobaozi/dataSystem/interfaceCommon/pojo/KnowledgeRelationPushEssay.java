package com.xiaobaozi.dataSystem.interfaceCommon.pojo;

import java.io.Serializable;

/**
 * 知识库关联推文
 * @author media
 *
 */
public class KnowledgeRelationPushEssay implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5379517566086556849L;
	private String KnowledgeBaseId; //知识库id
	private String essayId; //推文id
	private PushEssay pushEssay;
	
	
	
	public PushEssay getPushEssay() {
		return pushEssay;
	}
	public void setPushEssay(PushEssay pushEssay) {
		this.pushEssay = pushEssay;
	}
	public String getKnowledgeBaseId() {
		return KnowledgeBaseId;
	}
	public void setKnowledgeBaseId(String knowledgeBaseId) {
		KnowledgeBaseId = knowledgeBaseId;
	}
	public String getEssayId() {
		return essayId;
	}
	public void setEssayId(String essayId) {
		this.essayId = essayId;
	}
	
	
	
	

}
