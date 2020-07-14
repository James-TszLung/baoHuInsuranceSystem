package com.xiaobaozi.dataSystem.dictionary.pojo;

import java.io.Serializable;

public class InsuranceRelation implements Serializable {

    private String insuranceId;  //保险类型id,关联dictionary_content表id
    private String dictionaryContentId;  //关联dictionary_content表id
    private int type; //1：保险类型，2：投保规则，3：保费测算
    private int sort;  //排序
    private String expand;//保障类型子项目扩展，以分号隔开

    private DictionaryContent insuranceContent;
    private DictionaryContent guaranteeContent;

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public DictionaryContent getInsuranceContent() {
        return insuranceContent;
    }

    public void setInsuranceContent(DictionaryContent insuranceContent) {
        this.insuranceContent = insuranceContent;
    }

    public String getDictionaryContentId() {
        return dictionaryContentId;
    }

    public void setDictionaryContentId(String dictionaryContentId) {
        this.dictionaryContentId = dictionaryContentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DictionaryContent getGuaranteeContent() {
        return guaranteeContent;
    }

    public void setGuaranteeContent(DictionaryContent guaranteeContent) {
        this.guaranteeContent = guaranteeContent;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }
}
