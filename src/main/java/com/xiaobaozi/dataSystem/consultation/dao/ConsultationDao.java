package com.xiaobaozi.dataSystem.consultation.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;

public interface ConsultationDao extends GenericDao<Consultation> {


	public List<Consultation> selectByUserId(String userId);

	public int getConsultationCount(ConsultationSearch sc);

	public List<Consultation> getConsultationByPage(ConsultationSearch sc);

}
