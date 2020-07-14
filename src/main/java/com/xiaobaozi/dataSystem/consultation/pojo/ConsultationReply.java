package com.xiaobaozi.dataSystem.consultation.pojo;

import java.io.Serializable;
import java.util.List;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

/**
 * 预约咨询的回复-关联咨询
 * @author media
 *
 */
public class ConsultationReply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4969435583159098115L;
	private String id; 
	private String consultationId; //咨询Id
	private String insureName; //投保人
	private String reasonDictionaryId; //关联字典的推荐理由
	private String reasonContent; //推荐理由内容
	private DictionaryContent dictionaryContent;
	
	private List<ReplyRelationProduct> ReplyRelationProductLs;
	
	
	
	
	
	public DictionaryContent getDictionaryContent() {
		return dictionaryContent;
	}
	public void setDictionaryContent(DictionaryContent dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}
	public List<ReplyRelationProduct> getReplyRelationProductLs() {
		return ReplyRelationProductLs;
	}
	public void setReplyRelationProductLs(List<ReplyRelationProduct> replyRelationProductLs) {
		ReplyRelationProductLs = replyRelationProductLs;
	}
	public String getInsureName() {
		return insureName;
	}
	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConsultationId() {
		return consultationId;
	}
	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}
	public String getReasonDictionaryId() {
		return reasonDictionaryId;
	}
	public void setReasonDictionaryId(String reasonDictionaryId) {
		this.reasonDictionaryId = reasonDictionaryId;
	}
	public String getReasonContent() {
		return reasonContent;
	}
	public void setReasonContent(String reasonContent) {
		this.reasonContent = reasonContent;
	}
	
	
	
	

}
