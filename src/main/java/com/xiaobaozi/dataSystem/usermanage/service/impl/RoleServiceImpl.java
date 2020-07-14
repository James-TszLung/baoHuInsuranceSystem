package com.xiaobaozi.dataSystem.usermanage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.usermanage.handle.RoleHandler;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.service.RoleService;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;

/**
 * 角色服务接口
 * @author zhengbh
 *
 */
@Component("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role> implements
		RoleService {

	@Resource(name="roleHandler")
	private RoleHandler roleHandler;
	
	@Override
	protected void initService() {
		handle = roleHandler;		
	}
	
	public List<Role> getRoleListByPage(RoleSearchCriteria sc){
		return roleHandler.getRoleListByPage(sc);
	}
	
	public int getRoleCount(RoleSearchCriteria sc){
		return roleHandler.getRoleCount(sc);
	}

	@Override
	public int connectRoleFunction(List<Map<String, Object>> maps) {
		//先清除原有关系
		roleHandler.deconnectRoleFunction(maps.get(0));
		int sucNum = 0;
		for(Map<String, Object> map:maps){
			sucNum += roleHandler.connectRoleFunction(map);
		}
		return sucNum;
	}
}

