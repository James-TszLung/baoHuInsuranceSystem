package com.xiaobaozi.dataSystem.usermanage.handle;

import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;

public interface RoleHandler extends GenericHandle<Role>{

	public List<Role> getRoleListByPage(RoleSearchCriteria sc);
	public int getRoleCount(RoleSearchCriteria sc);
	public int connectRoleFunction(Map<String, Object> map);
	public int deconnectRoleFunction(Map<String, Object> map);
}
