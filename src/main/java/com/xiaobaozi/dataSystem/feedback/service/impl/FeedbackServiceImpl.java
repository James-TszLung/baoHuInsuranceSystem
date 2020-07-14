package com.xiaobaozi.dataSystem.feedback.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.feedback.handle.FeedbackHandle;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;
import com.xiaobaozi.dataSystem.feedback.service.FeedbackService;

@Component("feedbackService")
public class FeedbackServiceImpl extends GenericServiceImpl<Feedback> implements FeedbackService {

	@Resource(name = "feedbackHandle")
	private FeedbackHandle feedbackHandle;

	@Override
	protected void initService() {
		handle = feedbackHandle;
	}

	@Override
	public int getFeedbackCount(FeedbackSearch sc) {
		return feedbackHandle.getFeedbackCount(sc);
	}

	@Override
	public List<Feedback> getFeedbackByPage(FeedbackSearch sc) {
		return feedbackHandle.getFeedbackByPage(sc);
	}

	@Override
	public List<Feedback> selectByUserId(String userId) {
		return feedbackHandle.selectByUserId(userId);
	}

}
