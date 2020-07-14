package com.xiaobaozi.dataSystem.interfaceCommon.pojo;
/**
 * 咨询回复关联产品：关联咨询回复表
 * @author media
 *
 */
public class ReplyRelationProduct {
	
	private String consultationReplyId; //回复Id
	private String productId; //产品id
	private String channelDictionaryId;// 渠道
	public String getConsultationReplyId() {
		return consultationReplyId;
	}
	public void setConsultationReplyId(String consultationReplyId) {
		this.consultationReplyId = consultationReplyId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getChannelDictionaryId() {
		return channelDictionaryId;
	}
	public void setChannelDictionaryId(String channelDictionaryId) {
		this.channelDictionaryId = channelDictionaryId;
	}

	
	

}
