package com.xiaobaozi.dataSystem.dictionary.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;

import java.util.List;
import java.util.Map;

public interface InsuranceRelationHandle extends GenericHandle<InsuranceRelation> {

    public List<InsuranceRelation> getListByInsuranceId(Map map);

}
