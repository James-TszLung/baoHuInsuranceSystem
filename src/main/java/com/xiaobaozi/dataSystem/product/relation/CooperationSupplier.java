package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

/**
 * 合作供应商关联产品
 * @author media
 *
 */
public class CooperationSupplier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4836532558740536208L;
	
	private String id;
	private String productId; //产品id
	private int recommendStatus; //是否推荐   1：否  2：是
	private String dictionaryContentId; //銷售渠道id
	private String insureLink;//投保链接
	private String clauseLink; //条款链接
	private DictionaryContent dictionaryContent;
	private int sort;
	
	
	
	
	
	
	
	public DictionaryContent getDictionaryContent() {
		return dictionaryContent;
	}
	public void setDictionaryContent(DictionaryContent dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}
	public String getDictionaryContentId() {
		return dictionaryContentId;
	}
	public void setDictionaryContentId(String dictionaryContentId) {
		this.dictionaryContentId = dictionaryContentId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRecommendStatus() {
		return recommendStatus;
	}
	public void setRecommendStatus(int recommendStatus) {
		this.recommendStatus = recommendStatus;
	}
	
	public String getInsureLink() {
		return insureLink;
	}
	public void setInsureLink(String insureLink) {
		this.insureLink = insureLink;
	}
	public String getClauseLink() {
		return clauseLink;
	}
	public void setClauseLink(String clauseLink) {
		this.clauseLink = clauseLink;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
