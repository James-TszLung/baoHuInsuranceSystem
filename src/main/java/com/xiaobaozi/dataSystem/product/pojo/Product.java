package com.xiaobaozi.dataSystem.product.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.product.relation.*;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;

/**
 * 保险产品
 * 
 * @author media
 * 
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4755096769883870916L;
	private String id;
	private String name; // 产品名称 -
	private String proid;
	private String rank; //  评级,评级字典id,关联dictionary_content表
	private int catagory; // 分类
	private String buyLink; // 购买链接
	private String pingLink;
	private BigDecimal price; // 产品保额
	private String description; // 產品描述，特性
	private int hasActivity; // 活动
	private int status; // 状态
	private String guaranteeTypeId; // 保障类型id (数字字典)
	private String insuranceTypeId; // 保险类型id (数字字典)
	private String companyId; // 保险公司id(数字字典)
	private int clauseOr; // 是否启动条款 1：否 2：是
	private String  fileid; //文件id
	private String notGuaranteedSituation; // 不保情况
	private String brightspot; // 亮点
	private String insufficient;// 不足
	private String attention; // 注意事項
	private String suitable; // 适合人群
	private String comment; // 综合点评
	private String premiumsExplain; // 保费说明
	private String similarLabelId; // 同类产品标签(数字字典)
	private String keyword; // 特色标签
	private int insType; // 分类id
	private String ageTypeId; // 年齡分段分類id
	private int sort;
	private String insuranceCompanyId; //实际公司id
	private Date createTime;
	private String createName;
	private String createById;
	private Date updateTime;
	private String updateName;
	private String updateById;
	private Date publishTime;

	private DictionaryContent dictionaryContent;//保险类型
	private List<CooperationSupplier> cooperationSupplierLs;  //合作供应商
	private List<GuaranteeRelation> guaranteeRelationLs; // 保什麼
	private List<IncrementRelation> incrementRelationLs;//增值服务
	private List<IncrementRelation> renewRelationLs;//续保规则
	private List<GuaranteedRule> guaranteedRuleLs;//投保规则
	private List<PremiumCalculation> premiumCalculationLs;//保险测算
	private List<LabelRelation> labelRelationLs;
	private List<SpecialLabel> specialLabelLs;//特色标签
	private List<ClauseFile> clauseFileLs;//条款附件
	private String[] specialLabelIds;//产品特色标签

	private InsuranceCompany insuranceCompany;//保险公司
	private CooperationSupplier recommendCooperation;//推荐的合作商
	private DictionaryContent rankDictionaryContent;//评级
	private ProductLabel similarDictionaryContent;//同类产品标签
	private DictionaryContent ageDictionaryContent;//年龄阶段


	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public List<GuaranteeRelation> getGuaranteeRelationLs() {
		return guaranteeRelationLs;
	}

	public void setGuaranteeRelationLs(List<GuaranteeRelation> guaranteeRelationLs) {
		this.guaranteeRelationLs = guaranteeRelationLs;
	}

	public String getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(String insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public String getAgeTypeId() {
		return ageTypeId;
	}

	public void setAgeTypeId(String ageTypeId) {
		this.ageTypeId = ageTypeId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<CooperationSupplier> getCooperationSupplierLs() {
		return cooperationSupplierLs;
	}

	public void setCooperationSupplierLs(List<CooperationSupplier> cooperationSupplierLs) {
		this.cooperationSupplierLs = cooperationSupplierLs;
	}

	public int getInsType() {
		return insType;
	}

	public void setInsType(int insType) {
		this.insType = insType;
	}

	public DictionaryContent getDictionaryContent() {
		return dictionaryContent;
	}

	public void setDictionaryContent(DictionaryContent dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getNotGuaranteedSituation() {
		return notGuaranteedSituation;
	}

	public void setNotGuaranteedSituation(String notGuaranteedSituation) {
		this.notGuaranteedSituation = notGuaranteedSituation;
	}

	public String getBrightspot() {
		return brightspot;
	}

	public void setBrightspot(String brightspot) {
		this.brightspot = brightspot;
	}

	public String getInsufficient() {
		return insufficient;
	}

	public void setInsufficient(String insufficient) {
		this.insufficient = insufficient;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getSuitable() {
		return suitable;
	}

	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPremiumsExplain() {
		return premiumsExplain;
	}

	public void setPremiumsExplain(String premiumsExplain) {
		this.premiumsExplain = premiumsExplain;
	}

	public String getSimilarLabelId() {
		return similarLabelId;
	}

	public void setSimilarLabelId(String similarLabelId) {
		this.similarLabelId = similarLabelId;
	}

	public String getGuaranteeTypeId() {
		return guaranteeTypeId;
	}

	public void setGuaranteeTypeId(String guaranteeTypeId) {
		this.guaranteeTypeId = guaranteeTypeId;
	}

	public String getInsuranceTypeId() {
		return insuranceTypeId;
	}

	public void setInsuranceTypeId(String insuranceTypeId) {
		this.insuranceTypeId = insuranceTypeId;
	}

	public int getClauseOr() {
		return clauseOr;
	}

	public void setClauseOr(int clauseOr) {
		this.clauseOr = clauseOr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getCatagory() {
		return catagory;
	}

	public void setCatagory(int catagory) {
		this.catagory = catagory;
	}

	public String getBuyLink() {
		return buyLink;
	}

	public void setBuyLink(String buyLink) {
		this.buyLink = buyLink;
	}

	public String getPingLink() {
		return pingLink;
	}

	public void setPingLink(String pingLink) {
		this.pingLink = pingLink;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHasActivity() {
		return hasActivity;
	}

	public void setHasActivity(int hasActivity) {
		this.hasActivity = hasActivity;
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

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateById() {
		return createById;
	}

	public void setCreateById(String createById) {
		this.createById = createById;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateById() {
		return updateById;
	}

	public void setUpdateById(String updateById) {
		this.updateById = updateById;
	}

	public List<IncrementRelation> getIncrementRelationLs() {
		return incrementRelationLs;
	}

	public void setIncrementRelationLs(List<IncrementRelation> incrementRelationLs) {
		this.incrementRelationLs = incrementRelationLs;
	}

	public List<LabelRelation> getLabelRelationLs() {
		return labelRelationLs;
	}

	public void setLabelRelationLs(List<LabelRelation> labelRelationLs) {
		this.labelRelationLs = labelRelationLs;
	}

	public List<SpecialLabel> getSpecialLabelLs() {
		return specialLabelLs;
	}

	public void setSpecialLabelLs(List<SpecialLabel> specialLabelLs) {
		this.specialLabelLs = specialLabelLs;
	}

	public List<ClauseFile> getClauseFileLs() {
		return clauseFileLs;
	}

	public void setClauseFileLs(List<ClauseFile> clauseFileLs) {
		this.clauseFileLs = clauseFileLs;
	}

	public List<IncrementRelation> getRenewRelationLs() {
		return renewRelationLs;
	}

	public void setRenewRelationLs(List<IncrementRelation> renewRelationLs) {
		this.renewRelationLs = renewRelationLs;
	}

	public List<GuaranteedRule> getGuaranteedRuleLs() {
		return guaranteedRuleLs;
	}

	public void setGuaranteedRuleLs(List<GuaranteedRule> guaranteedRuleLs) {
		this.guaranteedRuleLs = guaranteedRuleLs;
	}

	public List<PremiumCalculation> getPremiumCalculationLs() {
		return premiumCalculationLs;
	}

	public void setPremiumCalculationLs(List<PremiumCalculation> premiumCalculationLs) {
		this.premiumCalculationLs = premiumCalculationLs;
	}

	public String[] getSpecialLabelIds() {
		return specialLabelIds;
	}

	public void setSpecialLabelIds(String[] specialLabelIds) {
		this.specialLabelIds = specialLabelIds;
	}

	public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public CooperationSupplier getRecommendCooperation() {
		return recommendCooperation;
	}

	public void setRecommendCooperation(CooperationSupplier recommendCooperation) {
		this.recommendCooperation = recommendCooperation;
	}

	public DictionaryContent getRankDictionaryContent() {
		return rankDictionaryContent;
	}

	public void setRankDictionaryContent(DictionaryContent rankDictionaryContent) {
		this.rankDictionaryContent = rankDictionaryContent;
	}

	public ProductLabel getSimilarDictionaryContent() {
		return similarDictionaryContent;
	}

	public void setSimilarDictionaryContent(ProductLabel similarDictionaryContent) {
		this.similarDictionaryContent = similarDictionaryContent;
	}

	public DictionaryContent getAgeDictionaryContent() {
		return ageDictionaryContent;
	}

	public void setAgeDictionaryContent(DictionaryContent ageDictionaryContent) {
		this.ageDictionaryContent = ageDictionaryContent;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
