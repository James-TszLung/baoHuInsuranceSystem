package com.xiaobaozi.dataSystem.registerUser.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.registerUser.search.RegisterUserSearch;

public interface RegisterUserDao extends GenericDao<RegisterUser> {

	public int getRegisterUserCount(RegisterUserSearch sc);

	public List<RegisterUser> getRegisterUserByPage(RegisterUserSearch sc);
	
	public RegisterUser selectByUserId(String userId);

}
