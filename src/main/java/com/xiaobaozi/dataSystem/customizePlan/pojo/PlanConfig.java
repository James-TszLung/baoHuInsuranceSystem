package com.xiaobaozi.dataSystem.customizePlan.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlanConfig implements Serializable {
    private String id;
    private String planId;//定制方案id,关联customize_plan.id
    private String name;//姓名
    private int gender;//性别,1；男 2：女
    private String birth;//出生年月
    private String health;//健康状况
    private String insured;//已有保险
    private String content;//方案概述
    private String remark;//方案备注
    private int sort;//排序
    private Date createTime;//创建时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String genderDesc(){
        return gender==1 ? "男" : gender==2 ? "女" : "";
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
