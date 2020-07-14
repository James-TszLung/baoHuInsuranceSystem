package com.xiaobaozi.dataSystem.feedback.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;

public interface FeedbackDao extends GenericDao<Feedback> {

	public int getFeedbackCount(FeedbackSearch sc);

	public List<Feedback> getFeedbackByPage(FeedbackSearch sc);

	public List<Feedback> selectByUserId(String userId);

}
