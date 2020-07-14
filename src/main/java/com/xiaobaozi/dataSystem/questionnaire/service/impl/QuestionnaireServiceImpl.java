package com.xiaobaozi.dataSystem.questionnaire.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.questionnaire.pojo.IncomeRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.LiabilitiesRLQnaire;
import com.xiaobaozi.dataSystem.questionnaire.pojo.MemberInformation;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.questionnaire.handle.QuestionnaireHandle;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Questionnaire;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;
import com.xiaobaozi.dataSystem.questionnaire.service.QuestionnaireService;

@Component("questionnaireService")
public class QuestionnaireServiceImpl extends GenericServiceImpl<Questionnaire> implements QuestionnaireService {

	@Resource(name = "questionnaireHandle")
	private QuestionnaireHandle questionnaireHandle;

	@Override
	protected void initService() {
		handle = questionnaireHandle;
	}

	public int getQuestionnaireCount(QuestionnaireSearch sc) {
		return questionnaireHandle.getQuestionnaireCount(sc);
	}

	public List<Questionnaire> getQuestionnaireByPage(QuestionnaireSearch sc) {
		return questionnaireHandle.getQuestionnaireByPage(sc);
	}

	public Questionnaire getQuestionnaire(String id) {
		return questionnaireHandle.getQuestionnaire(id);
	}

	public boolean editQuestionnaire(Questionnaire sc) throws Exception{
		questionnaireHandle.deleIncomeByQuestionnaireId(sc.getId());
		questionnaireHandle.deleLiabilitiesByQuestionnaireId(sc.getId());
		questionnaireHandle.deleMemberByQuestionnaireId(sc.getId());
		List<IncomeRLQnaire> incomeRLQnaireLs = sc.getIncomeRLQnaireLs();
		if(incomeRLQnaireLs!=null && incomeRLQnaireLs.size()>0){
			for(int i=0;i<incomeRLQnaireLs.size();i++){
				IncomeRLQnaire incomeRLQnaire = incomeRLQnaireLs.get(i);
				if(incomeRLQnaire!=null){
					incomeRLQnaire.setQuestionnaireId(sc.getId());
					incomeRLQnaire.setSort(i);
					questionnaireHandle.insertIncomeRLQnaire(incomeRLQnaire);
				}
			}
		}
		List<LiabilitiesRLQnaire> liabilitiesRLQnaireLs = sc.getLiabilitiesRLQnaireLs();
		if(liabilitiesRLQnaireLs!=null && liabilitiesRLQnaireLs.size()>0){
			for(int i=0;i<liabilitiesRLQnaireLs.size();i++){
				LiabilitiesRLQnaire liabilitiesRLQnaire = liabilitiesRLQnaireLs.get(i);
				if(liabilitiesRLQnaire!=null){
					liabilitiesRLQnaire.setQuestionnaireId(sc.getId());
					liabilitiesRLQnaire.setSort(i);
					questionnaireHandle.insertLiabilitiesRLQnaire(liabilitiesRLQnaire);
				}
			}
		}
		List<MemberInformation> memberInformationLs = sc.getMemberInformationLs();
		if(memberInformationLs!=null && memberInformationLs.size()>0){
			for(int i=0;i<memberInformationLs.size();i++){
				MemberInformation memberInformation = memberInformationLs.get(i);
				if(memberInformation!=null){
					memberInformation.setQuestionnaireId(sc.getId());
					memberInformation.setSort(i);
					questionnaireHandle.insertMemberInformation(memberInformation);
				}
			}
		}
		return questionnaireHandle.update(sc);
	}

}
