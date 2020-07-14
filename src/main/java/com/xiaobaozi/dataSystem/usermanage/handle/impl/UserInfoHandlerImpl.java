package com.xiaobaozi.dataSystem.usermanage.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.UserInfoDao;
import com.xiaobaozi.dataSystem.usermanage.handle.UserInfoHandler;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;
@Component("userInfoHandler")
public class UserInfoHandlerImpl extends GenericHandleImpl<UserInfo> implements
		UserInfoHandler {

	@Resource(name="userInfoDao")
	private UserInfoDao userInfoDao;
	

	@Override
	public List<UserInfo> getUserInfoListByPage(UserSearchCriteria sc) {
		return userInfoDao.getUserInfoListByPage(sc);
	}

	@Override
	public int getUserInfoCount(UserSearchCriteria sc) {
		return userInfoDao.getUserInfoCount(sc);
	}
	public List<UserInfo> listByMapUserInfo(UserSearchCriteria sc){
		return userInfoDao.listByMapUserInfo(sc);
	}
	@Override
	protected void initHandle() {
		dao = userInfoDao;
	}

}
