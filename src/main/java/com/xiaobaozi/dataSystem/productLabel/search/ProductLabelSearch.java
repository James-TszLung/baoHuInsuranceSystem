package com.xiaobaozi.dataSystem.productLabel.search;

import java.util.Date;
import java.util.List;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
/**
 * 产品标签管理
 * @author media
 *
 */
public class ProductLabelSearch extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7209444838269896330L;

	private String id;
	private String name;
	private int productcount;
	private Date updateTime;
	private List<String> serviceIds; // id集和


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
	public List<String> getServiceIds() {
		return serviceIds;
	}
	public void setServiceIds(List<String> serviceIds) {
		this.serviceIds = serviceIds;
	}
}
