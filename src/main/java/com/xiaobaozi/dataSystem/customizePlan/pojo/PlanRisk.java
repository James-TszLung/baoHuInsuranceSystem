package com.xiaobaozi.dataSystem.customizePlan.pojo;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlanRisk implements Serializable {
    private String id;
    private String planId;//定制方案id,关联customize_plan.id
    private String typeId;//风险类别(数字字典),关联dictionary_content.id
    private String used;//产品作用
    private String risk;//风险缺口
    private String insuredContent;//建议保额内容
    private String insuredDesc;//建议保额描述
    private String deadlineContent;//保障期限内容
    private String deadlineDesc;//保障期限描述
    private String suggest;//核保建议
    private int sort;//排序
    private Date createTime;//创建时间

    private List<PlanSafety> planSafetyLs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private DictionaryContent dictionaryContent;//风险类别

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getInsuredContent() {
        return insuredContent;
    }

    public void setInsuredContent(String insuredContent) {
        this.insuredContent = insuredContent;
    }

    public String getInsuredDesc() {
        return insuredDesc;
    }

    public void setInsuredDesc(String insuredDesc) {
        this.insuredDesc = insuredDesc;
    }

    public String getDeadlineContent() {
        return deadlineContent;
    }

    public void setDeadlineContent(String deadlineContent) {
        this.deadlineContent = deadlineContent;
    }

    public String getDeadlineDesc() {
        return deadlineDesc;
    }

    public void setDeadlineDesc(String deadlineDesc) {
        this.deadlineDesc = deadlineDesc;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
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

    public DictionaryContent getDictionaryContent() {
        return dictionaryContent;
    }

    public void setDictionaryContent(DictionaryContent dictionaryContent) {
        this.dictionaryContent = dictionaryContent;
    }

    public List<PlanSafety> getPlanSafetyLs() {
        return planSafetyLs;
    }

    public void setPlanSafetyLs(List<PlanSafety> planSafetyLs) {
        this.planSafetyLs = planSafetyLs;
    }
}
