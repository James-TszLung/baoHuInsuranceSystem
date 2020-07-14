package com.xiaobaozi.dataSystem.usermanage.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户信息-实体类，对应T_Login表
 * @author zhengbh
 *
 */
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userID;//用户代码-不为空
	private long serialNo;//登录序列号
	private String corpNum;//中心
	private Date loginTime;//登录时间
	private String loginIP;//登录ip
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}
	public String getCorpNum() {
		return corpNum;
	}
	public void setCorpNum(String corpNum) {
		this.corpNum = corpNum;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = "账号："+this.userID+
						"登录序列号："+this.serialNo+
						"中心："+this.corpNum+
						"登录时间："+this.loginTime==null?"入库时以数据库时间为准":sf.format(this.loginTime)+
						"登录IP："+this.loginIP;
		return str;
	}
}
