package com.xiaobaozi.dataSystem.consultation.handle.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.consultation.dao.ConsultationReplyDao;
import com.xiaobaozi.dataSystem.consultation.handle.ConsultationReplyHandle;
import com.xiaobaozi.dataSystem.consultation.pojo.ConsultationReply;
import com.xiaobaozi.dataSystem.consultation.pojo.ReplyRelationProduct;

@Component("consultationReplyHandle")
public class ConsultationReplyHandleImpl extends GenericHandleImpl<ConsultationReply> implements ConsultationReplyHandle {

	@Resource(name = "consultationReplyDao")
	private ConsultationReplyDao consultationReplyDao;

	@Override
	protected void initHandle() {
		dao = consultationReplyDao;
	}

	@Override
	public int insertReplyRelationProduct(ReplyRelationProduct sc) throws Exception {
		return consultationReplyDao.insertReplyRelationProduct(sc);
	}

	
}
