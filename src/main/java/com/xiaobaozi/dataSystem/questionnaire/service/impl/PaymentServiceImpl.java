package com.xiaobaozi.dataSystem.questionnaire.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.questionnaire.handle.PaymentHandle;
import com.xiaobaozi.dataSystem.questionnaire.pojo.*;
import com.xiaobaozi.dataSystem.questionnaire.service.PaymentService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component("paymentService")
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService {

	@Resource(name = "paymentHandle")
	private PaymentHandle paymentHandle;

	@Override
	protected void initService() {
		handle = paymentHandle;
	}


	public Payment selectByQuestionnaireId(String questionnaireId) {
		return paymentHandle.selectByQuestionnaireId(questionnaireId);
	}
}
