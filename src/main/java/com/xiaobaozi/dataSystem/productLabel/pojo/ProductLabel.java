package com.xiaobaozi.dataSystem.productLabel.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 产品标签管理
 * @author media
 *
 */
public class ProductLabel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7209444838269896330L;

	private String id;
	private String name;
	private int productcount;
	private Date updateTime;
	private List<ProductLabelRelation> productLabelRelationLs;
	
	
	
	public List<ProductLabelRelation> getProductLabelRelationLs() {
		return productLabelRelationLs;
	}
	public void setProductLabelRelationLs(List<ProductLabelRelation> productLabelRelationLs) {
		this.productLabelRelationLs = productLabelRelationLs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductcount() {
		return productcount;
	}
	public void setProductcount(int productcount) {
		this.productcount = productcount;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
