package com.xiaobaozi.dataSystem.usermanage.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

public interface UserInfoHandler extends GenericHandle<UserInfo>{

	public List<UserInfo> getUserInfoListByPage(UserSearchCriteria sc);
	public int getUserInfoCount(UserSearchCriteria sc);
	public List<UserInfo> listByMapUserInfo(UserSearchCriteria sc);
}
