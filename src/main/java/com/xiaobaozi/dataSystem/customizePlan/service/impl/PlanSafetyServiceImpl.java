package com.xiaobaozi.dataSystem.customizePlan.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanSafetyHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanSafety;
import com.xiaobaozi.dataSystem.customizePlan.service.PlanSafetyService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("planSafetyService")
public class PlanSafetyServiceImpl extends GenericServiceImpl<PlanSafety> implements PlanSafetyService {

    @Resource(name="planSafetyHandle")
    private PlanSafetyHandle planSafetyHandle;

    protected void initService() {
        handle = planSafetyHandle;
    }

    public int delePlanSafetyByRiskId(String id) {
        return planSafetyHandle.delePlanSafetyByRiskId(id);
    }
}
