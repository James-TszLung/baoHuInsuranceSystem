package com.xiaobaozi.dataSystem.customizePlan.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomizePlan implements Serializable {

    private String id;
    private String name;//联系人姓名
    private String phone;//联系电话
    private String title;//标题
    private int userCount;//定制人数
    private Date createTime;//创建时间
    private String createName;//创建者
    private String createById;//创建者id
    private Date updateTime;//更新时间
    private String updateName;//更新者
    private String updateById;//更新者id

    private List<PlanConfig> planConfigLs;//方案配置
    private List<PlanRisk> planRiskLs;//风险保障

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
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

    public List<PlanConfig> getPlanConfigLs() {
        return planConfigLs;
    }

    public void setPlanConfigLs(List<PlanConfig> planConfigLs) {
        this.planConfigLs = planConfigLs;
    }

    public List<PlanRisk> getPlanRiskLs() {
        return planRiskLs;
    }

    public void setPlanRiskLs(List<PlanRisk> planRiskLs) {
        this.planRiskLs = planRiskLs;
    }
}
