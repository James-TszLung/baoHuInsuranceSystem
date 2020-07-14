package com.xiaobaozi.dataSystem.customizePlan.service;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanConfig;

public interface PlanConfigService extends GenericService<PlanConfig> {
    public int delePlanConfigByPlanId(String id);
}
