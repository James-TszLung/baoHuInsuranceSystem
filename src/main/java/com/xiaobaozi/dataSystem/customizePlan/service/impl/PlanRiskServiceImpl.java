package com.xiaobaozi.dataSystem.customizePlan.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanRiskHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanRisk;
import com.xiaobaozi.dataSystem.customizePlan.service.PlanRiskService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("planRiskService")
public class PlanRiskServiceImpl extends GenericServiceImpl<PlanRisk> implements PlanRiskService {

    @Resource(name = "planRiskHandle")
    private PlanRiskHandle planRiskHandle;
    protected void initService() {
        handle = planRiskHandle;
    }

    public int delePlanRiskByPlanId(String id) {
        return planRiskHandle.delePlanRiskByPlanId(id);
    }
}
