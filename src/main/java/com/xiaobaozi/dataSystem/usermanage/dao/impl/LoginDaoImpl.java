package com.xiaobaozi.dataSystem.usermanage.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.LoginDao;
import com.xiaobaozi.dataSystem.usermanage.vo.Login;

@Component("loginDao")
public class LoginDaoImpl extends GenericDaoImpl<Login> implements LoginDao {

	@Override
	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}
}
