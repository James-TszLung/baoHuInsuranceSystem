package com.xiaobaozi.dataSystem.productLabel.pojo;

import java.io.Serializable;

import com.xiaobaozi.dataSystem.product.pojo.Product;
/**
 * 产品标签关联产品
 * @author media
 *
 */
public class ProductLabelRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8526778112779489012L;
	
	private String productLabelId; //产品标签id
	private String productId; //产品id
	private int sort; //排序
	
	private Product product;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProductLabelId() {
		return productLabelId;
	}
	public void setProductLabelId(String productLabelId) {
		this.productLabelId = productLabelId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
	
	
 
	
}
