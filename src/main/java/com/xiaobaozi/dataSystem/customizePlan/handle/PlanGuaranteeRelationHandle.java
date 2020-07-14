package com.xiaobaozi.dataSystem.customizePlan.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanGuaranteeRelation;

import java.util.List;
import java.util.Map;

public interface PlanGuaranteeRelationHandle extends GenericHandle<PlanGuaranteeRelation> {
    public PlanGuaranteeRelation selectBy(Map<String,Object> map);

    public List<PlanGuaranteeRelation> selectBySafetyId(String safetyId);

    public int deleBySafetyId(String safetyId);
}
