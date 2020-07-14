package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 产品关联保费测算
 * @author media
 *
 */
public class PremiumCalculation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5392244679741705531L;
	private String id;  
	private String productId; //产品id
	private double insuredAmount; //保额（万元）
    //重疾险
    private int age; //年龄
    private int gender; //0；男  1：女
    private BigDecimal firstPremiums; //首年保费 (元）
    private String guaranteePeriod; //保障期间
    private String paymentPeriod; //交费期间
    private String totalPremium; //总保费
    //医疗险  
    private int socialSecurity; //是否有社保   0 无 1：；有
	private String flag;
	private int sort;
	private String guarantee;//保障


	public int getSocialSecurity() {
		return socialSecurity;
	}
	public void setSocialSecurity(int socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	public String getGuaranteePeriod() {
		return guaranteePeriod;
	}
	public void setGuaranteePeriod(String guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}
	public String getPaymentPeriod() {
		return paymentPeriod;
	}
	public void setPaymentPeriod(String paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public double getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	public BigDecimal getFirstPremiums() {
		return firstPremiums;
	}
	public void setFirstPremiums(BigDecimal firstPremiums) {
		this.firstPremiums = firstPremiums;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
}
