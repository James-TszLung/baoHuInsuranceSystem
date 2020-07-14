package com.xiaobaozi.dataSystem.consultation.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.consultation.dao.ConsultationDao;
import com.xiaobaozi.dataSystem.consultation.handle.ConsultationHandle;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;

@Component("consultationHandle")
public class ConsultationHandleImpl extends GenericHandleImpl<Consultation> implements ConsultationHandle {

	@Resource(name = "consultationDao")
	private ConsultationDao consultationDao;

	@Override
	protected void initHandle() {
		dao = consultationDao;
	}

	@Override
	public int getConsultationCount(ConsultationSearch sc) {
		return consultationDao.getConsultationCount(sc);
	}

	@Override
	public List<Consultation> getConsultationByPage(ConsultationSearch sc) {
		return consultationDao.getConsultationByPage(sc);
	}

	@Override
	public List<Consultation> selectByUserId(String userId) {
		return consultationDao.selectByUserId(userId);
	}

}
