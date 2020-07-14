package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 其他保障关联产品
 * @author media
 *
 */
public class OtherGuaranteeRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5804158087678422061L;
	
	private String productId; //产品id
	private String title; // 数字字典保障类型id
	private BigDecimal price; // 保额
	private String guaranteeIntroduction; // 保障简介
	private String guaranteeDetail; //保障详情
	private int sort;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
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
}
