package com.xiaobaozi.dataSystem.company.search;

import java.util.Date;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

/**
 * 保险公司
 * 
 * @author media
 * 
 */
public class InsuranceCompanySearch extends SearchCriteria{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3446929206988440594L;
	private String id;
	private String companyName; // 公司名称
	private String phone; // 电话
	private String searchKey; // 搜索标签
	private String createName; // 创建人
	private Date createTime; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
