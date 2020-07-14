package com.xiaobaozi.dataSystem.dictionary.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;

import java.util.List;
import java.util.Map;

public interface InsuranceRelationDao extends GenericDao<InsuranceRelation> {

    public List<InsuranceRelation> getListByInsuranceId(Map map);

}
