package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;

/**
 * 增值服务关联产品
 * @author media
 *
 */
public class IncrementRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932880055019864434L;
	private String productId; //产品id
	private String title; //标题
	private String content; //内容
	private int type;  //1：增值服务 2：续保规则
	private int sort;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
