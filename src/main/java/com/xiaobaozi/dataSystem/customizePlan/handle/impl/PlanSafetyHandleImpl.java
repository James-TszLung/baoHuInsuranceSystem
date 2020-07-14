package com.xiaobaozi.dataSystem.customizePlan.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanSafetyDao;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanSafetyHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanSafety;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("planSafetyHandle")
public class PlanSafetyHandleImpl extends GenericHandleImpl<PlanSafety> implements PlanSafetyHandle {
    @Resource(name="planSafetyDao")
    private PlanSafetyDao planSafetyDao;

    protected void initHandle() {
      dao = planSafetyDao;
    }

    public int delePlanSafetyByRiskId(String id) {
        return planSafetyDao.delePlanSafetyByRiskId(id);
    }
}
