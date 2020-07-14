package com.xiaobaozi.dataSystem.customizePlan.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanConfig;

public interface PlanConfigHandle extends GenericHandle<PlanConfig> {

    public int delePlanConfigByPlanId(String id);
}
