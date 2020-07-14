package com.xiaobaozi.dataSystem.customizePlan.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanRisk;

public interface PlanRiskHandle extends GenericHandle<PlanRisk> {
    public int delePlanRiskByPlanId(String id);
}
