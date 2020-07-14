package com.xiaobaozi.dataSystem.usermanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.usermanage.dao.UserInfoDao;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

@Component("userInfoDao")
public class UserInfoDaoImpl extends GenericDaoImpl<UserInfo> implements UserInfoDao {

	@Override
	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}
	
	public List<UserInfo> getUserInfoListByPage(UserSearchCriteria sc) {
		return selectList("getUserListByPage", sc);
	}

	public int getUserInfoCount(UserSearchCriteria sc) {
		return (Integer)this.selectOne("countUser",sc);
	}
	public List<UserInfo> listByMapUserInfo(UserSearchCriteria sc){
		return selectList("getUserListBy", sc);
	}
}
