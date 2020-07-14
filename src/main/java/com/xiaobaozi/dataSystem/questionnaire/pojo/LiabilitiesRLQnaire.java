package com.xiaobaozi.dataSystem.questionnaire.pojo;

import java.io.Serializable;

/**
 * 调查问卷：负债情况
 * @author media
 *
 */
public class LiabilitiesRLQnaire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4916894311216750897L;
	 private String questionnaireId;
     private int loanType;    //贷款类型，固定 1 房 2车  3其他
     private int repaymentType; //还款方式  1：等额本息 2：等额本金
     private double loanMoney; //贷款金额 单位：万元
     private int years;//贷款年限
     private int surplus; //剩余还款年限
     private double interestRate; //贷款利率
     private double supply; //月供
	private int sort;
     
     
     
     
	public int getSurplus() {
		return surplus;
	}
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public int getLoanType() {
		return loanType;
	}
	public void setLoanType(int loanType) {
		this.loanType = loanType;
	}
	public int getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(int repaymentType) {
		this.repaymentType = repaymentType;
	}
	public double getLoanMoney() {
		return loanMoney;
	}
	public void setLoanMoney(double loanMoney) {
		this.loanMoney = loanMoney;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getSupply() {
		return supply;
	}
	public void setSupply(double supply) {
		this.supply = supply;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
