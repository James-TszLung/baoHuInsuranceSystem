package com.xiaobaozi.dataSystem.usermanage.vo;

import com.xiaobaozi.dataSystem.commons.vo.BaseVO;

/**
 * 角色信息-实体类，对应T_Role表
 * 
 * @author Administrator
 * 
 */
public class Role extends BaseVO {

	private static final long serialVersionUID = -2983899114305008146L;
	private String roleId; // 角色id
	private String roleName; // 角色名称
	private String remark; // 说明描述

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
