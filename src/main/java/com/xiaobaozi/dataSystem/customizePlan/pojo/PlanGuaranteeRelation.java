package com.xiaobaozi.dataSystem.customizePlan.pojo;

import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;

import java.io.Serializable;
import java.util.Date;

public class PlanGuaranteeRelation implements Serializable {
    private String id;
    private String safetyId;//保险产品方案id,关联plan_safety.id
    private String guaranteeRelationId;//关联guarantee_relation.id
    private String productId;//产品id
    private String dictionaryContentId;//分项，数字字典保障类型id
    private String guaranteeIntroduction;//保障简介
    private String oldPrice;//原保额
    private String price;//保额 (万元）
    private int sort;//排序
    private Date createTime;//创建时间

    private Indemnity dictionaryContent;
    private GuaranteeRelation guaranteeRelation;
    private Product product;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(String safetyId) {
        this.safetyId = safetyId;
    }

    public String getGuaranteeRelationId() {
        return guaranteeRelationId;
    }

    public void setGuaranteeRelationId(String guaranteeRelationId) {
        this.guaranteeRelationId = guaranteeRelationId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDictionaryContentId() {
        return dictionaryContentId;
    }

    public void setDictionaryContentId(String dictionaryContentId) {
        this.dictionaryContentId = dictionaryContentId;
    }

    public String getGuaranteeIntroduction() {
        return guaranteeIntroduction;
    }

    public void setGuaranteeIntroduction(String guaranteeIntroduction) {
        this.guaranteeIntroduction = guaranteeIntroduction;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Indemnity getDictionaryContent() {
        return dictionaryContent;
    }

    public void setDictionaryContent(Indemnity dictionaryContent) {
        this.dictionaryContent = dictionaryContent;
    }

    public GuaranteeRelation getGuaranteeRelation() {
        return guaranteeRelation;
    }

    public void setGuaranteeRelation(GuaranteeRelation guaranteeRelation) {
        this.guaranteeRelation = guaranteeRelation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}