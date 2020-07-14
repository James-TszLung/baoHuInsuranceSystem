package com.xiaobaozi.dataSystem.usermanage.handle.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.LoginDao;
import com.xiaobaozi.dataSystem.usermanage.handle.LoginHandler;
import com.xiaobaozi.dataSystem.usermanage.vo.Login;
@Component("loginHandler")
public class LoginHandlerImpl extends GenericHandleImpl<Login> implements
		LoginHandler {

	@Resource(name="loginDao")
	private LoginDao loginDao;

	@Override
	protected void initHandle() {
		dao = loginDao;
	}
}
