package com.xiaobaozi.dataSystem.feedback.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;

public interface FeedbackService extends GenericService<Feedback> {
	public int getFeedbackCount(FeedbackSearch sc);

	public List<Feedback> getFeedbackByPage(FeedbackSearch sc);

	public List<Feedback> selectByUserId(String userId);

}
