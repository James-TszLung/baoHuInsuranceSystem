package com.xiaobaozi.dataSystem.customizePlan.pojo;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlanSafety implements Serializable {
    private String id;
    private String riskId;//风险保障id,关联plan_risk.id
    private String productId;//产品表id,关联product.id
    private String name;//产品名称
    private String premiums;//年缴保费 (元）
    private String amount;//保额(万元）
    private String insuredTerm;//保险期限（数字字典）,关联dictionary_content.id
    private String payTerm;//缴费期限（数字字典）,关联dictionary_content.id
    private String channelId;//渠道(数据字典),关联dictionary_content.id
    private String recommentId;//推荐原因(数据字典),关联dictionary_content.id
    private int sort;//排序
    private Date createTime;//创建时间

    private List<PlanGuaranteeRelation> planGuaranteeRelationLs;
    private DictionaryContent insuredContent;
    private DictionaryContent payContent;
    private DictionaryContent channelContent;
    private DictionaryContent recommentContent;
    private List<GuaranteeRelation> guaranteeRelationLs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPremiums() {
        return premiums;
    }

    public void setPremiums(String premiums) {
        this.premiums = premiums;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInsuredTerm() {
        return insuredTerm;
    }

    public void setInsuredTerm(String insuredTerm) {
        this.insuredTerm = insuredTerm;
    }

    public String getPayTerm() {
        return payTerm;
    }

    public void setPayTerm(String payTerm) {
        this.payTerm = payTerm;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRecommentId() {
        return recommentId;
    }

    public void setRecommentId(String recommentId) {
        this.recommentId = recommentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<PlanGuaranteeRelation> getPlanGuaranteeRelationLs() {
        return planGuaranteeRelationLs;
    }

    public void setPlanGuaranteeRelationLs(List<PlanGuaranteeRelation> planGuaranteeRelationLs) {
        this.planGuaranteeRelationLs = planGuaranteeRelationLs;
    }

    public DictionaryContent getInsuredContent() {
        return insuredContent;
    }

    public void setInsuredContent(DictionaryContent insuredContent) {
        this.insuredContent = insuredContent;
    }

    public DictionaryContent getPayContent() {
        return payContent;
    }

    public void setPayContent(DictionaryContent payContent) {
        this.payContent = payContent;
    }

    public DictionaryContent getChannelContent() {
        return channelContent;
    }

    public void setChannelContent(DictionaryContent channelContent) {
        this.channelContent = channelContent;
    }

    public DictionaryContent getRecommentContent() {
        return recommentContent;
    }

    public void setRecommentContent(DictionaryContent recommentContent) {
        this.recommentContent = recommentContent;
    }

    public List<GuaranteeRelation> getGuaranteeRelationLs() {
        return guaranteeRelationLs;
    }

    public void setGuaranteeRelationLs(List<GuaranteeRelation> guaranteeRelationLs) {
        this.guaranteeRelationLs = guaranteeRelationLs;
    }
}