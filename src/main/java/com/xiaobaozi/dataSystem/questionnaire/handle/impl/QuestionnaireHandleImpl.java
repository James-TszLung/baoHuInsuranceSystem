package com.xiaobaozi.dataSystem.questionnaire.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.questionnaire.pojo.IncomeRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.LiabilitiesRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.MemberInformation;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.questionnaire.dao.QuestionnaireDao;
import com.xiaobaozi.dataSystem.questionnaire.handle.QuestionnaireHandle;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Questionnaire;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;

@Component("questionnaireHandle")
public class QuestionnaireHandleImpl extends GenericHandleImpl<Questionnaire> implements QuestionnaireHandle {

	@Resource(name = "questionnaireDao")
	private QuestionnaireDao questionnaireDao;

	@Override
	protected void initHandle() {
		dao = questionnaireDao;
	}

	public int getQuestionnaireCount(QuestionnaireSearch sc) {
		return questionnaireDao.getQuestionnaireCount(sc);
	}

	public List<Questionnaire> getQuestionnaireByPage(QuestionnaireSearch sc) {
		return questionnaireDao.getQuestionnaireByPage(sc);
	}

	public Questionnaire getQuestionnaire(String id) {
		return questionnaireDao.getQuestionnaire(id);
	}

	public int deleIncomeByQuestionnaireId(String questionnaireId) {
		return questionnaireDao.deleIncomeByQuestionnaireId(questionnaireId);
	}

	public int deleLiabilitiesByQuestionnaireId(String questionnaireId) {
		return questionnaireDao.deleLiabilitiesByQuestionnaireId(questionnaireId);
	}

	public int deleMemberByQuestionnaireId(String questionnaireId) {
		return questionnaireDao.deleMemberByQuestionnaireId(questionnaireId);
	}

	public int insertIncomeRLQnaire(IncomeRLQnaire sc){
		return questionnaireDao.insertIncomeRLQnaire(sc);
	}

	public int insertLiabilitiesRLQnaire(LiabilitiesRLQnaire sc){
		return questionnaireDao.insertLiabilitiesRLQnaire(sc);
	}

	public int insertMemberInformation(MemberInformation sc){
		return questionnaireDao.insertMemberInformation(sc);
	}

}
