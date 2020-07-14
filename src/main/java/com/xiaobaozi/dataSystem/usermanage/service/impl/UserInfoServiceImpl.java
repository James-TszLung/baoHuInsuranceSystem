package com.xiaobaozi.dataSystem.usermanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.usermanage.handle.UserInfoHandler;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.service.UserInfoService;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

@Component("userInfoService")
public class UserInfoServiceImpl extends GenericServiceImpl<UserInfo> implements UserInfoService {

	@Resource
	private UserInfoHandler userInfoHandler;

	public void setUserInfoHandler(UserInfoHandler userInfoHandler) {
		this.userInfoHandler = userInfoHandler;
	}

	@Override
	protected void initService() {
		handle = userInfoHandler;
	}

	public List<UserInfo> getUserInfoListByPage(UserSearchCriteria sc) {
		return userInfoHandler.getUserInfoListByPage(sc);
	}

	public List<UserInfo> listByMapUserInfo(UserSearchCriteria sc) {
		return userInfoHandler.listByMapUserInfo(sc);
	}

	public int getUserInfoCount(UserSearchCriteria sc) {
		return userInfoHandler.getUserInfoCount(sc);
	}
}
