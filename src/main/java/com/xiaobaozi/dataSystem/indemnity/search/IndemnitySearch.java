package com.xiaobaozi.dataSystem.indemnity.search;


import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;

import java.util.Date;
import java.util.List;

public class IndemnitySearch extends SearchCriteria {

    private String id;
    private String content;//名称
    private String dictionaryContentId;//保险类型，关联dictionary_content.id
    private int sort;//排序
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public DictionaryContent getDictionaryContent() {
        return dictionaryContent;
    }

    public void setDictionaryContent(DictionaryContent dictionaryContent) {
        this.dictionaryContent = dictionaryContent;
    }
}
