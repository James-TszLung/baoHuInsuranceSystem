package com.xiaobaozi.dataSystem.customizePlan.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanConfigHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanConfig;
import com.xiaobaozi.dataSystem.customizePlan.service.PlanConfigService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("planConfigService")
public class PlanConfigServiceImpl extends GenericServiceImpl<PlanConfig> implements PlanConfigService {

    @Resource(name = "planConfigHandle")
    private PlanConfigHandle planConfigHandle;

    protected void initService() {
        handle = planConfigHandle;
    }

    public int delePlanConfigByPlanId(String id) {
        return planConfigHandle.delePlanConfigByPlanId(id);
    }
}
