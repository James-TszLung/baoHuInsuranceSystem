package com.xiaobaozi.dataSystem.questionnaire.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.questionnaire.pojo.IncomeRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.LiabilitiesRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.MemberInformation;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Questionnaire;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;

public interface QuestionnaireHandle extends GenericHandle<Questionnaire> {
	public int getQuestionnaireCount(QuestionnaireSearch sc);

	public List<Questionnaire> getQuestionnaireByPage(QuestionnaireSearch sc);

	public Questionnaire getQuestionnaire(String id);

	public int deleIncomeByQuestionnaireId(String questionnaireId);

	public int deleLiabilitiesByQuestionnaireId(String questionnaireId);

	public int deleMemberByQuestionnaireId(String questionnaireId);

	public int insertIncomeRLQnaire(IncomeRLQnaire sc);

	public int insertLiabilitiesRLQnaire(LiabilitiesRLQnaire sc);

	public int insertMemberInformation(MemberInformation sc);

}
