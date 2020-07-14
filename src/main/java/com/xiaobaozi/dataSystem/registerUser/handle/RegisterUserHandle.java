package com.xiaobaozi.dataSystem.registerUser.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.registerUser.search.RegisterUserSearch;

public interface RegisterUserHandle extends GenericHandle<RegisterUser> {

	public int getRegisterUserCount(RegisterUserSearch sc);

	public List<RegisterUser> getRegisterUserByPage(RegisterUserSearch sc);
	
	public RegisterUser selectByUserId(String userId);

}
