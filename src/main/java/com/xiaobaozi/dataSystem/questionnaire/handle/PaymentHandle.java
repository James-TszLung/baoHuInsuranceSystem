package com.xiaobaozi.dataSystem.questionnaire.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.questionnaire.pojo.*;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;

import java.util.List;

public interface PaymentHandle extends GenericHandle<Payment> {
	public Payment selectByQuestionnaireId(String questionnaireId);
}
