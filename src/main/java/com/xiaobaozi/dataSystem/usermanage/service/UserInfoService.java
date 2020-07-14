package com.xiaobaozi.dataSystem.usermanage.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

public interface UserInfoService extends GenericService<UserInfo>{

	/**
	 * 分页查询用户信息
	 * @param sc 分页查询时必须带有页码和每页数量
 	 * @return
	 */
	public List<UserInfo> getUserInfoListByPage(UserSearchCriteria sc);
	/**
	 * 不用内置分页
	 * @param sc
	 * @return
	 */
	public List<UserInfo> listByMapUserInfo(UserSearchCriteria sc);
	/**
	 * 根据条件查询符合的用户信息数量
	 * @param sc
	 * @return
	 */
	public int getUserInfoCount(UserSearchCriteria sc);
}
