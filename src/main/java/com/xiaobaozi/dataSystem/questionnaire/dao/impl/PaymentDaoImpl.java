package com.xiaobaozi.dataSystem.questionnaire.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.questionnaire.dao.PaymentDao;
import com.xiaobaozi.dataSystem.questionnaire.pojo.*;
import org.springframework.stereotype.Component;

@Component("paymentDao")
public class PaymentDaoImpl extends GenericDaoImpl<Payment> implements PaymentDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public Payment selectByQuestionnaireId(String questionnaireId) {
		return (Payment) this.selectOne("selectByQuestionnaireId", questionnaireId);
	}
}
