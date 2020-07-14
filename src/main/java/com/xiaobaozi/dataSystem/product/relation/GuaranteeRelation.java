package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;
import java.util.List;

import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;

/**
 * 保什么 保 障类型关联产品
 * 
 * @author media
 * 
 */
public class GuaranteeRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 723033911481117692L;

	private String id;
	private String productId; // 产品id
	private String dictionaryContentId; // 数字字典保障类型id
	private String price; // 保额
	private String guaranteeIntroduction; // 保障简介
	private String guaranteeDetail; // 保障详情
	private int type; // 是否推荐 1：是 2：否
	private int sort;

	private Indemnity dictionaryContent;
	private List<GuaranteeLowerlevelRelation> guaranteeLowerLevelRelationLs; //子项目


	private String newPrice;


	public List<GuaranteeLowerlevelRelation> getGuaranteeLowerLevelRelationLs() {
		return guaranteeLowerLevelRelationLs;
	}

	public void setGuaranteeLowerLevelRelationLs(List<GuaranteeLowerlevelRelation> guaranteeLowerLevelRelationLs) {
		this.guaranteeLowerLevelRelationLs = guaranteeLowerLevelRelationLs;
	}

	public Indemnity getDictionaryContent() {
		return dictionaryContent;
	}

	public void setDictionaryContent(Indemnity dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictionaryContentId() {
		return dictionaryContentId;
	}

	public void setDictionaryContentId(String dictionaryContentId) {
		this.dictionaryContentId = dictionaryContentId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGuaranteeIntroduction() {
		return guaranteeIntroduction;
	}

	public void setGuaranteeIntroduction(String guaranteeIntroduction) {
		this.guaranteeIntroduction = guaranteeIntroduction;
	}

	public String getGuaranteeDetail() {
		return guaranteeDetail;
	}

	public void setGuaranteeDetail(String guaranteeDetail) {
		this.guaranteeDetail = guaranteeDetail;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(String newPrice) {
		this.newPrice = newPrice;
	}
}
