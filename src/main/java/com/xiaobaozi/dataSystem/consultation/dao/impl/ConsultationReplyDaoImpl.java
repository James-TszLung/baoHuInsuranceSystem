package com.xiaobaozi.dataSystem.consultation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.consultation.dao.ConsultationDao;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;

@Component("consultationDao")
public class ConsultationReplyDaoImpl extends GenericDaoImpl<Consultation> implements ConsultationDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getConsultationCount(ConsultationSearch sc) {
		return (Integer) this.selectOne("countConsultation", sc);
	}

	@Override
	public List<Consultation> getConsultationByPage(ConsultationSearch sc) {
		return selectList("getListByPage", sc);
	}

	@Override
	public List<Consultation> selectByUserId(String userId) {
		return this.selectList("selectByUserId", userId);
	}

}
