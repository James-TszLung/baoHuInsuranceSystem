package com.xiaobaozi.dataSystem.dictionary.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.dictionary.handle.InsuranceRelationHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;
import com.xiaobaozi.dataSystem.dictionary.service.InsuranceRelationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("insuranceRelationService")
public class InsuranceRelationServiceImpl extends GenericServiceImpl<InsuranceRelation> implements InsuranceRelationService {

    @Resource(name="insuranceRelationHandle")
    private InsuranceRelationHandle insuranceRelationHandle;

    protected void initService() {
        handle = insuranceRelationHandle;
    }

    public List<InsuranceRelation> getListByInsuranceId(Map map) {
        return insuranceRelationHandle.getListByInsuranceId(map);
    }


}
