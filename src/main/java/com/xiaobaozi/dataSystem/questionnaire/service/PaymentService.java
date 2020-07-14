package com.xiaobaozi.dataSystem.questionnaire.service;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Payment;

public interface PaymentService extends GenericService<Payment> {
	public Payment selectByQuestionnaireId(String questionnaireId);
}
