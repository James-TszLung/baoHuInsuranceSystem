package com.xiaobaozi.dataSystem.usermanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.RoleDao;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;

@Component("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	@Override
	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}
	
	@Override
	public List<Role> getRoleList(RoleSearchCriteria sc) {
		return selectList("getRoleListByPage", sc);
	}

	@Override
	public int getCountRole(RoleSearchCriteria sc) {
		return (Integer)this.selectOne("countRole",sc);
	}

	@Override
	public int connectRoleFunction(Map<String, Object> map){
		return insertByMap("connectRoleFunction", map);
	}

	@Override
	public int deconnectRoleFunction(Map<String, Object> map) {
		return deleteByMap("deconnectRoleFunction", map);
	}
}
