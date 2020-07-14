package com.xiaobaozi.dataSystem.feedback.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;

public interface FeedbackHandle extends GenericHandle<Feedback> {
	public int getFeedbackCount(FeedbackSearch sc);

	public List<Feedback> getFeedbackByPage(FeedbackSearch sc);

	public List<Feedback> selectByUserId(String userId);

}
