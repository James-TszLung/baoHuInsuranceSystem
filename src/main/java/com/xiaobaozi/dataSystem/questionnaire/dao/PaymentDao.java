package com.xiaobaozi.dataSystem.questionnaire.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Payment;


public interface PaymentDao extends GenericDao<Payment> {

	public Payment selectByQuestionnaireId(String questionnaireId);

}

