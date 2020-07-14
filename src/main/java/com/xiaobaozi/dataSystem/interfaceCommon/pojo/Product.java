package com.xiaobaozi.dataSystem.interfaceCommon.pojo;

import java.io.Serializable;
import java.math.BigDecimal;


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
	private int rank; // 评级
	private int catagory; // 分类
	private String buyLink; // 购买链接
	private String pingLink;
	private BigDecimal price; // 产品保额
	private String description; // 產品描述，特性
	private int hasActivity; // 活动
	private int status; // 状态

	private String guaranteeTypeId; // 保障类型id (数字字典)
	private String insuranceTypeId; // 保险类型id (数字字典)
	private String cpmpanyId; // 保险公司id(数字字典)

	private int clauseOr; // 是否启动条款 1：否 2：是
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
	
	private DictionaryContent dictionaryContent;
	

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

	public String getCpmpanyId() {
		return cpmpanyId;
	}

	public void setCpmpanyId(String cpmpanyId) {
		this.cpmpanyId = cpmpanyId;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
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

}
