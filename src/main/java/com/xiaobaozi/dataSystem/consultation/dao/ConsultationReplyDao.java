package com.xiaobaozi.dataSystem.consultation.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.consultation.pojo.ConsultationReply;
import com.xiaobaozi.dataSystem.consultation.pojo.ReplyRelationProduct;

public interface ConsultationReplyDao extends GenericDao<ConsultationReply> {

	public int insertReplyRelationProduct(ReplyRelationProduct sc) throws Exception;

}
