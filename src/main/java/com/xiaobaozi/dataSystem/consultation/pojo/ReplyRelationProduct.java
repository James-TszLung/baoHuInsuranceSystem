package com.xiaobaozi.dataSystem.consultation.pojo;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.product.pojo.Product;

/**
 * 咨询回复关联产品：关联咨询回复表
 * @author media
 *
 */
public class ReplyRelationProduct {
	
	private String consultationReplyId; //回复Id
	private String productId; //产品id
	private String channelDictionaryId;// 渠道
	
	private Product product;
	private DictionaryContent dictionaryContent;
	
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public DictionaryContent getDictionaryContent() {
		return dictionaryContent;
	}
	public void setDictionaryContent(DictionaryContent dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}
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
