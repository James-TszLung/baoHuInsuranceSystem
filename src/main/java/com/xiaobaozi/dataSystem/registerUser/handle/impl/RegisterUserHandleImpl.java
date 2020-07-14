package com.xiaobaozi.dataSystem.registerUser.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.registerUser.dao.RegisterUserDao;
import com.xiaobaozi.dataSystem.registerUser.handle.RegisterUserHandle;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.registerUser.search.RegisterUserSearch;

@Component("registerUserHandle")
public class RegisterUserHandleImpl extends GenericHandleImpl<RegisterUser> implements RegisterUserHandle {

	@Resource(name = "registerUserDao")
	private RegisterUserDao registerUserDao;

	@Override
	protected void initHandle() {
		dao = registerUserDao;
	}

	@Override
	public int getRegisterUserCount(RegisterUserSearch sc) {
		return registerUserDao.getRegisterUserCount(sc);
	}

	@Override
	public List<RegisterUser> getRegisterUserByPage(RegisterUserSearch sc) {
		return registerUserDao.getRegisterUserByPage(sc);
	}

	@Override
	public RegisterUser selectByUserId(String userId) {
		return registerUserDao.selectByUserId(userId);
	}

}
