package com.xiaobaozi.dataSystem.usermanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.usermanage.handle.LoginHandler;
import com.xiaobaozi.dataSystem.usermanage.service.LoginService;
import com.xiaobaozi.dataSystem.usermanage.vo.Login;

@Component("loginService")
public class LoginServiceImpl extends GenericServiceImpl<Login> implements
	LoginService {

	@Resource(name="loginHandler")
	private LoginHandler loginHandler;
	

	@Override
	protected void initService() {
		handle = loginHandler;		
	}
}

