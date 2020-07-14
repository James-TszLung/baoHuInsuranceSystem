package com.xiaobaozi.dataSystem.questionnaire.pojo;

import java.io.Serializable;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
/**
 * 成员信息
 * @author media
 *
 */
public class MemberInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7874226755515632314L;
	 private String questionnaireId;
	 private String name; //姓名
	 private int sex; //性别 1：男 2：女
	 private int age; //年龄
	 private String relationshipId; //家庭关系数据字典Id
	 private String profession; //從事職業
	 
	 private String companyName; //公司名称
	 private String productName;//产品名称
	 private String situation; //保障情况
	 private int years; //续费期限
	 private double payMoney; //交费金额
	private int guaranteeYears;
	private int sort;
	 
	 
	 
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private DictionaryContent dictionaryContent; //產品分類

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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public DictionaryContent getDictionaryContent() {
		return dictionaryContent;
	}

	public void setDictionaryContent(DictionaryContent dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getGuaranteeYears() {
		return guaranteeYears;
	}

	public void setGuaranteeYears(int guaranteeYears) {
		this.guaranteeYears = guaranteeYears;
	}
}
