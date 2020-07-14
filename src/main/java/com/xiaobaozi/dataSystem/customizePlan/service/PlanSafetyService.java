package com.xiaobaozi.dataSystem.customizePlan.service;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanSafety;

public interface PlanSafetyService extends GenericService<PlanSafety>{
    public int delePlanSafetyByRiskId(String id);
}
