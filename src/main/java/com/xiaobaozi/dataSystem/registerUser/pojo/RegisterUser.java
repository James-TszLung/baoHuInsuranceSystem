package com.xiaobaozi.dataSystem.registerUser.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 注册用户
 * 
 * @author media
 * 
 */
public class RegisterUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4543492125137145931L;

	private String userId;// id
	private String name;// 昵称
	private String openid; // 小程序的openid
	private String phone;// 手机号
	private String pictureId;// 头像
	private Date registerTime;// 注册时间
	
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

}
