package com.xiaobaozi.dataSystem.usermanage.service;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;

public interface RoleService extends GenericService<Role>{

	/**
	 * 分页查询角色
	 * @param sc 分页查询时必须带有页码和每页数量
 	 * @return
	 */
	public List<Role> getRoleListByPage(RoleSearchCriteria sc);
	/**
	 * 根据条件查询符合的角色数量
	 * @param sc
	 * @return
	 */
	public int getRoleCount(RoleSearchCriteria sc);
	
	/**
	 * 绑定角色和模块
	 * @param map
	 * @return
	 */
	public int connectRoleFunction(List<Map<String, Object>> maps);
}
