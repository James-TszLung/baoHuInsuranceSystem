package com.xiaobaozi.dataSystem.dictionary.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.dictionary.dao.InsuranceRelationDao;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("insuranceRelationDao")
public class InsuranceRelationDaoImpl extends GenericDaoImpl<InsuranceRelation> implements  InsuranceRelationDao{
    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public List<InsuranceRelation> getListByInsuranceId(Map map) {
        return selectList("getListByInsuranceId",map);
    }
}
