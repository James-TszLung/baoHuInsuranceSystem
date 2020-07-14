package com.xiaobaozi.dataSystem.customizePlan.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanSafety;

public interface PlanSafetyHandle extends GenericHandle<PlanSafety> {
    public int delePlanSafetyByRiskId(String id);
}
