package com.xiaobaozi.dataSystem.usermanage.search;

import java.util.Date;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.commons.utils.StringUtil;

/**
 * 用户信息查询类，用于传递普通/分页查询条件
 * 
 * @author Administrator
 * 
 */
public class UserSearchCriteria extends SearchCriteria {

	private static final long serialVersionUID = 2544982734340396443L;
	private String id; // 用户id
	private String account;// 账号
	private String password; // 密码
	private int status; // 状态 1正常 2禁用
	private String position; // 职务描述
	private String roleId;// varchar(32) null 角色
	private String mail; // varchar(32) null 邮箱地址
	private int modifyPwd; // 修改密码次数
	private String roleName;//角色名称

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

}
