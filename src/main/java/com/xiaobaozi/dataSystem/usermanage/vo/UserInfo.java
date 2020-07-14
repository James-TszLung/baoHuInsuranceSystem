package com.xiaobaozi.dataSystem.usermanage.vo;

import java.util.Date;

import com.xiaobaozi.dataSystem.commons.vo.BaseVO;

/**
 * 用户信息-实体类，对应T_UserInfo表
 * 
 * @author zhengbh
 * 
 */
public class UserInfo extends BaseVO {

	private static final long serialVersionUID = -4709081869974167634L;
	private String id; // 用户id
	private String account;// 账号
	private String password; // 密码
	private int status; // 状态 1正常 2禁用
	private String position; // 职务描述
	private String roleId;// varchar(32) null 角色
	private String mail; // varchar(32) null 邮箱地址
	private int modifyPwd; // 修改密码次数
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getModifyPwd() {
		return modifyPwd;
	}

	public void setModifyPwd(int modifyPwd) {
		this.modifyPwd = modifyPwd;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
