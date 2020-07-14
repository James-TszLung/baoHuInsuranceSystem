package com.xiaobaozi.dataSystem.indemnity.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.indemnity.dao.IndemnityDao;
import com.xiaobaozi.dataSystem.indemnity.handle.IndemnityHandle;
import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.indemnity.search.IndemnitySearch;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("indemnityHandle")
public class IndemnityHandleImpl extends GenericHandleImpl<Indemnity> implements IndemnityHandle {

    @Resource(name = "indemnityDao")
    private IndemnityDao indemnityDao;

    protected void initHandle() {
        dao = indemnityDao;
    }


    public int getIndemnityCount(IndemnitySearch sc) {
        return indemnityDao.getIndemnityCount(sc);
    }

    public List<Indemnity> getIndemnityByPage(IndemnitySearch sc) {
        return indemnityDao.getIndemnityByPage(sc);
    }

    public List<Indemnity> getIndemnityByDictionaryId(String dictionaryContentId) {
        return indemnityDao.getIndemnityByDictionaryId(dictionaryContentId);
    }

    public Indemnity selectBy(Map<String, Object> map) {
        return indemnityDao.selectBy(map);
    }

    public int countRelation(String dictionaryContentId) {
        return indemnityDao.countRelation(dictionaryContentId);
    }

}
