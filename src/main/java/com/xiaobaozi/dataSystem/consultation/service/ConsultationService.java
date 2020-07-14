package com.xiaobaozi.dataSystem.consultation.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;

public interface ConsultationService extends GenericService<Consultation> {
	public List<Consultation> selectByUserId(String userId);

	public int getConsultationCount(ConsultationSearch sc);

	public List<Consultation> getConsultationByPage(ConsultationSearch sc);
	
	public boolean replyConsultation(Consultation sc)throws Exception;

}
