package com.xiaobaozi.dataSystem.dictionary.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.dictionary.dao.InsuranceRelationDao;
import com.xiaobaozi.dataSystem.dictionary.handle.InsuranceRelationHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("insuranceRelationHandle")
public class InsuranceRelationHandleImpl extends GenericHandleImpl<InsuranceRelation> implements InsuranceRelationHandle {

    @Resource(name="insuranceRelationDao")
    private InsuranceRelationDao insuranceRelationDao;

    protected void initHandle() {
        dao = insuranceRelationDao;
    }

    public List<InsuranceRelation> getListByInsuranceId(Map map) {
        return insuranceRelationDao.getListByInsuranceId(map);
    }

}
