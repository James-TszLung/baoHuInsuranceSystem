package com.xiaobaozi.dataSystem.company.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 保险公司
 * 
 * @author media
 * 
 */
public class InsuranceCompany implements Serializable {

	

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
	
	private String registered_location;
	private String net_address; //官网地址
	private String full_name; //公司全名
	private String establish_time; //註冊時間
	private String company_id;// 公司id
	
	

	public String getRegistered_location() {
		return registered_location;
	}

	public void setRegistered_location(String registered_location) {
		this.registered_location = registered_location;
	}

	public String getNet_address() {
		return net_address;
	}

	public void setNet_address(String net_address) {
		this.net_address = net_address;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEstablish_time() {
		return establish_time;
	}

	public void setEstablish_time(String establish_time) {
		this.establish_time = establish_time;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
