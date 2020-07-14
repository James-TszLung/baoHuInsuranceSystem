package com.xiaobaozi.dataSystem.feedback.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.feedback.dao.FeedbackDao;
import com.xiaobaozi.dataSystem.feedback.handle.FeedbackHandle;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;

@Component("feedbackHandle")
public class FeedbackHandleImpl extends GenericHandleImpl<Feedback> implements FeedbackHandle {

	@Resource(name = "feedbackDao")
	private FeedbackDao feedbackDao;

	@Override
	protected void initHandle() {
		dao = feedbackDao;
	}

	@Override
	public int getFeedbackCount(FeedbackSearch sc) {
		return feedbackDao.getFeedbackCount(sc);
	}

	@Override
	public List<Feedback> getFeedbackByPage(FeedbackSearch sc) {
		return feedbackDao.getFeedbackByPage(sc);
	}

	@Override
	public List<Feedback> selectByUserId(String userId) {
		return feedbackDao.selectByUserId(userId);
	}

}
