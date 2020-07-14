package com.xiaobaozi.dataSystem.usermanage.search;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

/**
 * 用户信息查询类，用于传递普通/分页查询条件
 * 
 * @author Administrator
 * 
 */
public class RoleSearchCriteria extends SearchCriteria {

	private static final long serialVersionUID = -3168142604970861136L;
	private String rolePale;
	private String[] rolePales;
	private String roleName; // 角色名称

	public String[] getRolePales() {
		return rolePales;
	}

	public void setRolePales(String[] rolePales) {
		this.rolePales = rolePales;
	}

	public String getRolePale() {
		return rolePale;
	}

	public void setRolePale(String rolePale) {
		this.rolePale = rolePale;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
