package com.xiaobaozi.dataSystem.customizePlan.service;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanRisk;

public interface PlanRiskService extends GenericService<PlanRisk> {
    public int delePlanRiskByPlanId(String id);
}
