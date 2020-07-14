package com.xiaobaozi.dataSystem.indemnity.service;



import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.indemnity.search.IndemnitySearch;

import java.util.List;
import java.util.Map;

public interface IndemnityService extends GenericService<Indemnity> {

    public int getIndemnityCount(IndemnitySearch sc);

    public List<Indemnity> getIndemnityByPage(IndemnitySearch sc);

    public List<Indemnity> getIndemnityByDictionaryId(String dictionaryContentId);

    public int insertIndemnity(Indemnity indemnity) throws Exception;

    public boolean updateIndemnity(Indemnity indemnity) throws Exception;

    public Indemnity selectBy(Map<String,Object> map);

    public boolean deleteIndemnityById(Indemnity indemnity) throws Exception;

    public int countRelation(String dictionaryContentId);

}
