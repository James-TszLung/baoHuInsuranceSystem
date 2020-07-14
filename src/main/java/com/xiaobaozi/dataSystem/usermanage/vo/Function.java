package com.xiaobaozi.dataSystem.usermanage.vo;

import java.util.ArrayList;
import java.util.List;

import com.xiaobaozi.dataSystem.commons.vo.BaseVO;

/**
 * 模块信息-实体类，对应T_Function表
 * 
 * @author zhengbh
 * 
 */
public class Function extends BaseVO {

	private static final long serialVersionUID = 3891967677084145889L;
	private String id;// 模块id
	private String funName;// 模块名称
	private String funUrl;// 模块路径
	private int sort;// 菜单排序
	private String parentMenu;// 父级菜单
	private int level;// 当前级别
	private int type;// 权限类型(如：菜单、功能等)
	// 非数据库字段
	private List<Function> childs = new ArrayList<Function>();// 下级菜单
	private String checked;// 是否已经
	private String roleId;// 关联角色id
	private String remark;
	private String icon; //菜单图标

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Function> getChilds() {
		return childs;
	}

	public void setChilds(List<Function> childs) {
		this.childs = childs;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
