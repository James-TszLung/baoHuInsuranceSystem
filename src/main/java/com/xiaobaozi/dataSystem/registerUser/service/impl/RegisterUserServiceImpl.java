package com.xiaobaozi.dataSystem.registerUser.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.registerUser.handle.RegisterUserHandle;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.registerUser.search.RegisterUserSearch;
import com.xiaobaozi.dataSystem.registerUser.service.RegisterUserService;

@Component("registerUserService")
public class RegisterUserServiceImpl extends GenericServiceImpl<RegisterUser> implements RegisterUserService {

	@Resource(name = "registerUserHandle")
	private RegisterUserHandle registerUserHandle;

	@Override
	protected void initService() {
		handle = registerUserHandle;
	}

	@Override
	public int getRegisterUserCount(RegisterUserSearch sc) {
		return registerUserHandle.getRegisterUserCount(sc);
	}

	@Override
	public List<RegisterUser> getRegisterUserByPage(RegisterUserSearch sc) {
		return registerUserHandle.getRegisterUserByPage(sc);
	}

	@Override
	public RegisterUser selectByUserId(String userId) {
		return registerUserHandle.selectByUserId(userId);
	}

}
