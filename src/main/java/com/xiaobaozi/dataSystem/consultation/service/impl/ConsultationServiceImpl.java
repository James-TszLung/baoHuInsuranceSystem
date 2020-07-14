package com.xiaobaozi.dataSystem.consultation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.consultation.handle.ConsultationHandle;
import com.xiaobaozi.dataSystem.consultation.handle.ConsultationReplyHandle;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.pojo.ConsultationReply;
import com.xiaobaozi.dataSystem.consultation.pojo.ReplyRelationProduct;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;
import com.xiaobaozi.dataSystem.consultation.service.ConsultationService;

@Component("consultationService")
public class ConsultationServiceImpl extends GenericServiceImpl<Consultation> implements ConsultationService {

	@Resource(name = "consultationHandle")
	private ConsultationHandle consultationHandle;
	@Resource
	private ConsultationReplyHandle consultationReplyHandle;

	@Override
	protected void initService() {
		handle = consultationHandle;
	}

	@Override
	public int getConsultationCount(ConsultationSearch sc) {
		return consultationHandle.getConsultationCount(sc);
	}

	@Override
	public List<Consultation> getConsultationByPage(ConsultationSearch sc) {
		return consultationHandle.getConsultationByPage(sc);
	}

	@Override
	public List<Consultation> selectByUserId(String userId) {
		return consultationHandle.selectByUserId(userId);
	}

	/**
	 * 回復操作
	 */
	@Override
	public boolean replyConsultation(Consultation sc) throws Exception {
		sc.setStatus(2);
		List<ConsultationReply> replyLs = sc.getConsultationReplyLs();
		if (replyLs != null && replyLs.size() > 0) {
			for (int i = 0; i < replyLs.size(); i++) {
				ConsultationReply reply = replyLs.get(i);
				if (reply != null && StringUtils.isNotBlank(reply.getInsureName())) {
					reply.setId(UUIDUtil.getUUID());
					reply.setConsultationId(sc.getId());
					List<ReplyRelationProduct> relationProductLs = reply.getReplyRelationProductLs();
					if (relationProductLs != null && relationProductLs.size() > 0) {
						for (int j = 0; j < relationProductLs.size(); j++) {
							ReplyRelationProduct relationProduct = relationProductLs.get(j);
							if (StringUtils.isNotBlank(relationProduct.getProductId())) {
								relationProduct.setConsultationReplyId(reply.getId());
								consultationReplyHandle.insertReplyRelationProduct(relationProduct);
							}
						}
					}
					consultationReplyHandle.insert(reply);
				}
			}
		}
		return consultationHandle.update(sc);
	}

}
