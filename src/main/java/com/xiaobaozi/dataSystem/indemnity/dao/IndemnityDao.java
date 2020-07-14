package com.xiaobaozi.dataSystem.indemnity.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.indemnity.search.IndemnitySearch;

import java.util.List;
import java.util.Map;

public interface IndemnityDao extends GenericDao<Indemnity> {

    public int getIndemnityCount(IndemnitySearch sc);

    public List<Indemnity> getIndemnityByPage(IndemnitySearch sc);

    public List<Indemnity> getIndemnityByDictionaryId(String dictionaryContentId);

    public Indemnity selectBy(Map<String,Object> map);

    public int countRelation(String dictionaryContentId);


}
