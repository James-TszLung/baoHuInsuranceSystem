package com.xiaobaozi.dataSystem.customizePlan.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanConfigDao;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanConfigHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("planConfigHandle")
public class PlanConfigHandleImpl extends GenericHandleImpl<PlanConfig> implements PlanConfigHandle {

    @Resource(name = "planConfigDao")
    private PlanConfigDao planConfigDao;

    protected void initHandle() {
        dao = planConfigDao;
    }

    public int delePlanConfigByPlanId(String id) {
        return planConfigDao.delePlanConfigByPlanId(id);
    }
}
