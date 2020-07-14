package com.xiaobaozi.dataSystem.consultation.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.consultation.pojo.ConsultationReply;
import com.xiaobaozi.dataSystem.consultation.pojo.ReplyRelationProduct;

public interface ConsultationReplyHandle extends GenericHandle<ConsultationReply> {

	public int insertReplyRelationProduct(ReplyRelationProduct sc) throws Exception;

}
