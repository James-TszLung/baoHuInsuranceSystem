package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;
/**
 * 标签关联产品
 * @author media
 *
 */
public class LabelRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1016980764018007260L;
	private String productId; //产品id
	private String labelValue; //标签
	private String proid;
	private String labelId;
	private int sort;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getLabelValue() {
		return labelValue;
	}
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
