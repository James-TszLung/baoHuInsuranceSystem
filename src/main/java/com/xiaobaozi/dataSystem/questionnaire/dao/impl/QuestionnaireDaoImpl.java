package com.xiaobaozi.dataSystem.questionnaire.dao.impl;

import java.util.List;

import com.xiaobaozi.dataSystem.questionnaire.pojo.IncomeRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.LiabilitiesRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.MemberInformation;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.questionnaire.dao.QuestionnaireDao;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Questionnaire;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;

@Component("questionnaireDao")
public class QuestionnaireDaoImpl extends GenericDaoImpl<Questionnaire> implements QuestionnaireDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public int getQuestionnaireCount(QuestionnaireSearch sc) {
		return (Integer) this.selectOne("countQuestionnaire", sc);
	}

	public List<Questionnaire> getQuestionnaireByPage(QuestionnaireSearch sc) {
		return selectList("getListByPage", sc);
	}

	public Questionnaire getQuestionnaire(String id) {
		return (Questionnaire) this.selectOne("selectById2", id);
	}

	public int deleIncomeByQuestionnaireId(String questionnaireId) {
		return this.deleteByMap("deleIncomeByQuestionnaireId", questionnaireId);
	}

	public int deleLiabilitiesByQuestionnaireId(String questionnaireId) {
		return this.deleteByMap("deleLiabilitiesByQuestionnaireId", questionnaireId);
	}

	public int deleMemberByQuestionnaireId(String questionnaireId) {
		return this.deleteByMap("deleMemberByQuestionnaireId", questionnaireId);
	}

	public int insertIncomeRLQnaire(IncomeRLQnaire sc) {
		return this.insertByMap("insertIncomeRLQnaire", sc);
	}

	public int insertLiabilitiesRLQnaire(LiabilitiesRLQnaire sc) {
		return this.insertByMap("insertLiabilitiesRLQnaire", sc);
	}

	public int insertMemberInformation(MemberInformation sc) {
		return this.insertByMap("insertMemberInformation", sc);
	}

}
