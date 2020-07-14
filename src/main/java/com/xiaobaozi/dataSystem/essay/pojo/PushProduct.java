package com.xiaobaozi.dataSystem.essay.pojo;

import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;

import java.io.Serializable;
import java.util.Date;

/**
 * 推文关联产品表
 * 
 * @author media
 * 
 */
public class PushProduct implements Serializable {


	private String pushEssayId;//推文管理id,关联push_essay.id
	private String productId; // 产品id,关联product.id
	private Date createTime;//创建时间
	private int sort; //排序

	private Product product;


	public String getPushEssayId() {
		return pushEssayId;
	}

	public void setPushEssayId(String pushEssayId) {
		this.pushEssayId = pushEssayId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
