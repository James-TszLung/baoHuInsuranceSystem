package com.xiaobaozi.dataSystem.usermanage.handle.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.RoleDao;
import com.xiaobaozi.dataSystem.usermanage.handle.RoleHandler;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;
@Component("roleHandler")
public class RoleHandlerImpl extends GenericHandleImpl<Role> implements
		RoleHandler {

	@Resource(name="roleDao")
	private RoleDao roleDao;

	@Override
	public List<Role> getRoleListByPage(RoleSearchCriteria sc) {
		return roleDao.getRoleList(sc);
	}

	@Override
	public int getRoleCount(RoleSearchCriteria sc) {
		return roleDao.getCountRole(sc);
	}

	@Override
	protected void initHandle() {
		dao = roleDao;
	}

	@Override
	public int connectRoleFunction(Map<String, Object> map) {
		return roleDao.connectRoleFunction(map);
	}

	@Override
	public int deconnectRoleFunction(Map<String, Object> map) {
		return roleDao.deconnectRoleFunction(map);
	}

}
