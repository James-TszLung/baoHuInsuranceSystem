package com.xiaobaozi.dataSystem.registerUser.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.registerUser.dao.RegisterUserDao;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.registerUser.search.RegisterUserSearch;

@Component("registerUserDao")
public class RegisterUserDaoImpl extends GenericDaoImpl<RegisterUser> implements RegisterUserDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getRegisterUserCount(RegisterUserSearch sc) {
		return (Integer) this.selectOne("countRegisterUser", sc);
	}

	@Override
	public List<RegisterUser> getRegisterUserByPage(RegisterUserSearch sc) {
		return selectList("getListByPage", sc);
	}

	@Override
	public RegisterUser selectByUserId(String userId) {
		return (RegisterUser)this.selectOne("selectByUserId", userId);
	}

}
