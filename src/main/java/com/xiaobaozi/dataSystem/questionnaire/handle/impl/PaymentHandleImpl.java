package com.xiaobaozi.dataSystem.questionnaire.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.questionnaire.dao.PaymentDao;
import com.xiaobaozi.dataSystem.questionnaire.handle.PaymentHandle;
import com.xiaobaozi.dataSystem.questionnaire.pojo.*;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component("paymentHandle")
public class PaymentHandleImpl extends GenericHandleImpl<Payment> implements PaymentHandle {

	@Resource(name = "paymentDao")
	private PaymentDao paymentDao;

	@Override
	protected void initHandle() {
		dao = paymentDao;
	}

	public Payment selectByQuestionnaireId(String questionnaireId) {
		return paymentDao.selectByQuestionnaireId(questionnaireId);
	}
}
