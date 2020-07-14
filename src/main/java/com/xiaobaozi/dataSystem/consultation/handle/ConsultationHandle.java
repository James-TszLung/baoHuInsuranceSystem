package com.xiaobaozi.dataSystem.consultation.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;

public interface ConsultationHandle extends GenericHandle<Consultation> {

	public List<Consultation> selectByUserId(String userId);

	public int getConsultationCount(ConsultationSearch sc);

	public List<Consultation> getConsultationByPage(ConsultationSearch sc);

}
