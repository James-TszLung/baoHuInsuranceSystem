package com.xiaobaozi.dataSystem.customizePlan.service;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanGuaranteeRelation;

import java.util.List;
import java.util.Map;

public interface PlanGuaranteeRelationService extends GenericService<PlanGuaranteeRelation>{

    public PlanGuaranteeRelation selectBy(Map<String,Object> map);

    public List<PlanGuaranteeRelation> selectBySafetyId(String safetyId);

    public int deleBySafetyId(String safetyId);
}
