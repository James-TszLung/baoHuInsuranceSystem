package com.xiaobaozi.dataSystem.indemnity.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.indemnity.dao.IndemnityDao;
import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.indemnity.search.IndemnitySearch;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("indemnityDao")
public class IndemnityDaoImpl extends GenericDaoImpl<Indemnity> implements IndemnityDao {
    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int getIndemnityCount(IndemnitySearch sc) {
        return (Integer) this.selectOne("countIndemnity", sc);
    }

    public List<Indemnity> getIndemnityByPage(IndemnitySearch sc) {
        return selectList("getListByPage", sc);
    }

    public List<Indemnity> getIndemnityByDictionaryId(String dictionaryContentId) {
        return selectList("selectByDictionaryContentId", dictionaryContentId);
    }

    public Indemnity selectBy(Map<String, Object> map) {
        return (Indemnity) this.selectOne("selectBy", map);
    }

    public int countRelation(String dictionaryContentId) {
        return (Integer) this.selectOne("countRelation", dictionaryContentId);
    }
}
