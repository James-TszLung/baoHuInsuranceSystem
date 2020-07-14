package com.xiaobaozi.dataSystem.questionnaire.pojo;

import java.io.Serializable;
/*調查問卷:收入情況
 * 
 * @author media
 *
 */
public class IncomeRLQnaire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764873774401869769L;
	 private String questionnaireId;
	 private String name; 
	 private double money;  //税后收入  万元
	 private String source; //收入来源
	private int sort;
	 
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
