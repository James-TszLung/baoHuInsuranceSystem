package com.xiaobaozi.dataSystem.usermanage.dao;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;

public interface RoleDao extends GenericDao<Role>{

	public List<Role> getRoleList(RoleSearchCriteria us);
	public int getCountRole(RoleSearchCriteria us);
	public int connectRoleFunction(Map<String, Object> map);
	public int deconnectRoleFunction(Map<String, Object> map);
}
