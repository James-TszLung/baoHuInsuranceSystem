package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;

/**
 * 同类产品关联产品
 * @author media
 *
 */
public class SimilarProductsRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1505133303017280485L;
    private String productId; //产品id 
    private String similarProductId; //同类产品id
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSimilarProductId() {
		return similarProductId;
	}
	public void setSimilarProductId(String similarProductId) {
		this.similarProductId = similarProductId;
	}
    
    
}
