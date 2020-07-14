package com.xiaobaozi.dataSystem.interfaceCommon.search;

import java.util.Date;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;


/**
 * 预约咨询
 * @author media
 *
 */
public class ConsultationSearch extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1856315320401883082L;
	
	private String id;
	private String userId; //用户ID
	private int call; //称呼 1：男士 2：女士
	private String phone; //联系电话
	private String guaranteeDemand; //保障需求 多个用,隔开
	private String forWhom; //为谁投保
	private Date createTime; //创建时间
	private int status;  // 1:待回復  2：已回復
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCall() {
		return call;
	}
	public void setCall(int call) {
		this.call = call;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGuaranteeDemand() {
		return guaranteeDemand;
	}
	public void setGuaranteeDemand(String guaranteeDemand) {
		this.guaranteeDemand = guaranteeDemand;
	}
	public String getForWhom() {
		return forWhom;
	}
	public void setForWhom(String forWhom) {
		this.forWhom = forWhom;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	

}
