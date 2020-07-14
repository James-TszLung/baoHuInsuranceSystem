package com.xiaobaozi.dataSystem.indemnity.pojo;


import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Indemnity implements Serializable {

    private String id;
    private String content;//名称
    private String dictionaryContentId;//保险类型，关联dictionary_content.id
    private int sort;//排序
    private Date createTime;//创建时间
    private String createName;//创建者
    private String createById;//创建者id
    private Date updateTime;//更新时间
    private String updateName;//更新者
    private String updateById;//更新者id

    private List<IndemnitySub> indemnitySubLs;//子项目
    private DictionaryContent dictionaryContent;//保险类型


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDictionaryContentId() {
        return dictionaryContentId;
    }

    public void setDictionaryContentId(String dictionaryContentId) {
        this.dictionaryContentId = dictionaryContentId;
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

    public List<IndemnitySub> getIndemnitySubLs() {
        return indemnitySubLs;
    }

    public void setIndemnitySubLs(List<IndemnitySub> indemnitySubLs) {
        this.indemnitySubLs = indemnitySubLs;
    }

    public DictionaryContent getDictionaryContent() {
        return dictionaryContent;
    }

    public void setDictionaryContent(DictionaryContent dictionaryContent) {
        this.dictionaryContent = dictionaryContent;
    }
}
