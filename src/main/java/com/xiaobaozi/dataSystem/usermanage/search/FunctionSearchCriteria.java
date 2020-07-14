package com.xiaobaozi.dataSystem.usermanage.search;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.commons.utils.StringUtil;

/**
 * 用户信息查询类，用于传递普通/分页查询条件
 * 
 * @author Administrator
 * 
 */
public class FunctionSearchCriteria extends SearchCriteria {

	private static final long serialVersionUID = 7760326704498454447L;
	private String id;// 模块id
	private String funName;// 模块名称
	private String funUrl;// 模块路径
	private int sort;// 菜单排序
	private int parentMenu;// 父级菜单
	private int level;// 当前级别
	private int type;// 权限类型(如：菜单、功能等)

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

	public int getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(int parentMenu) {
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
