package com.xiaobaozi.dataSystem.usermanage.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

public interface UserInfoDao extends GenericDao<UserInfo>{

	public List<UserInfo> getUserInfoListByPage(UserSearchCriteria sc);
	public int getUserInfoCount(UserSearchCriteria sc);
	public List<UserInfo> listByMapUserInfo(UserSearchCriteria sc);
}
