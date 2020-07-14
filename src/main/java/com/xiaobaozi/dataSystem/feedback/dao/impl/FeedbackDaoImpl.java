package com.xiaobaozi.dataSystem.feedback.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.feedback.dao.FeedbackDao;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;

@Component("feedbackDao")
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getFeedbackCount(FeedbackSearch sc) {
		return (Integer) this.selectOne("countFeedback", sc);
	}

	@Override
	public List<Feedback> getFeedbackByPage(FeedbackSearch sc) {
		return selectList("getListByPage", sc);
	}

	@Override
	public List<Feedback> selectByUserId(String userId) {
		return selectList("selectByUserId", userId);
	}

}
