package com.xiaobaozi.dataSystem.indemnity.pojo;

import java.io.Serializable;
import java.util.Date;


public class IndemnitySub implements Serializable {

    private String id;
    private String name;//名称
    private String indemnityId;// 保障类型表,关联indemnity.id
    private int sort;//排序
    private Date createTime;//创建时间
    private String createName;//创建者
    private String createById;//创建者id
    private Date updateTime;//更新时间
    private String updateName;//更新者
    private String updateById;//更新者id


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

    public String getIndemnityId() {
        return indemnityId;
    }

    public void setIndemnityId(String indemnityId) {
        this.indemnityId = indemnityId;
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

}
