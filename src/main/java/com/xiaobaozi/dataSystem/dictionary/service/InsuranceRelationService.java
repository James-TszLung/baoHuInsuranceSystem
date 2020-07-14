package com.xiaobaozi.dataSystem.dictionary.service;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;

import java.util.List;
import java.util.Map;

public interface InsuranceRelationService extends GenericService<InsuranceRelation> {
    public List<InsuranceRelation> getListByInsuranceId(Map map);
}
