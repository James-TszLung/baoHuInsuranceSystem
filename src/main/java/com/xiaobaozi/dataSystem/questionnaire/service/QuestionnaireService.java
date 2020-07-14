package com.xiaobaozi.dataSystem.questionnaire.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Questionnaire;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;

public interface QuestionnaireService extends GenericService<Questionnaire> {

	public int getQuestionnaireCount(QuestionnaireSearch sc);

	public List<Questionnaire> getQuestionnaireByPage(QuestionnaireSearch sc);

	public Questionnaire getQuestionnaire(String id);

	public boolean editQuestionnaire(Questionnaire sc) throws Exception;

}
