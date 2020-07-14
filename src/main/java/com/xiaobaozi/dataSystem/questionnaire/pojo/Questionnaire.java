package com.xiaobaozi.dataSystem.questionnaire.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 调查问卷
 * 
 * @author media
 * 
 */
public class Questionnaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4506955563430957412L;
	private String id;
	private String openid;// 用户的openid
	private String name; // 姓名
	private String phone;// 联系电话
	// 和家人常駐所在地和戶口
	private String city1; // 常住城市1
	private String city2; // 常住城市2
	private String residence; // 戶籍
	private double supportMoney;// 贍養支出
	private int supportNum; // 赡养人数
	private int pensions; // 养老金类型 1：无 2：最低 3：一般 4：等同于公务员
	private double expenditure; // 家庭开支
	private double reserve; // 家庭储备金
	private int second; // 是否有二胎需求 1：有 2：没有
	private int abroad; // 是否孩子有出国留学计划 1：有 2：没有

	private int demandCompany; // 对保险公司需求 1:产品性价比，保司品牌为辅 2 中大型品牌保险公司
	private String securityField; // 风险保障领域 多个用“,”隔开
	private double budget; // 保费预算
	private String healthy;// 健康情况 数字表示，多个用","隔开

	private int status; // 狀態 1：未付款 2：已付款
	private Date createTime; // 提交时间
	private Date updateTime; // 修改时间
	private String wxNumber;//微信号
	private String remark;
	
	private List<IncomeRLQnaire> incomeRLQnaireLs;  //收入情況
	private List<LiabilitiesRLQnaire> liabilitiesRLQnaireLs; //负债情况
	private List<MemberInformation> memberInformationLs; //成员信息

	
	public List<IncomeRLQnaire> getIncomeRLQnaireLs() {
		return incomeRLQnaireLs;
	}
	public void setIncomeRLQnaireLs(List<IncomeRLQnaire> incomeRLQnaireLs) {
		this.incomeRLQnaireLs = incomeRLQnaireLs;
	}
	public List<LiabilitiesRLQnaire> getLiabilitiesRLQnaireLs() {
		return liabilitiesRLQnaireLs;
	}
	public void setLiabilitiesRLQnaireLs(List<LiabilitiesRLQnaire> liabilitiesRLQnaireLs) {
		this.liabilitiesRLQnaireLs = liabilitiesRLQnaireLs;
	}
	public List<MemberInformation> getMemberInformationLs() {
		return memberInformationLs;
	}
	public void setMemberInformationLs(List<MemberInformation> memberInformationLs) {
		this.memberInformationLs = memberInformationLs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public double getSupportMoney() {
		return supportMoney;
	}
	public void setSupportMoney(double supportMoney) {
		this.supportMoney = supportMoney;
	}
	public int getSupportNum() {
		return supportNum;
	}
	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}
	public int getPensions() {
		return pensions;
	}
	public void setPensions(int pensions) {
		this.pensions = pensions;
	}
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public double getReserve() {
		return reserve;
	}
	public void setReserve(double reserve) {
		this.reserve = reserve;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getAbroad() {
		return abroad;
	}
	public void setAbroad(int abroad) {
		this.abroad = abroad;
	}
	public int getDemandCompany() {
		return demandCompany;
	}
	public void setDemandCompany(int demandCompany) {
		this.demandCompany = demandCompany;
	}
	public String getSecurityField() {
		return securityField;
	}
	public void setSecurityField(String securityField) {
		this.securityField = securityField;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public String getHealthy() {
		return healthy;
	}
	public void setHealthy(String healthy) {
		this.healthy = healthy;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getWxNumber() {
		return wxNumber;
	}
	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
