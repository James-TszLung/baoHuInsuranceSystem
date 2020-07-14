package com.xiaobaozi.dataSystem.customizePlan.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanRiskDao;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanRiskHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanRisk;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("planRiskHandle")
public class PlanRiskHandleImpl extends GenericHandleImpl<PlanRisk> implements PlanRiskHandle {

    @Resource(name = "planRiskDao")
    private PlanRiskDao planRiskDao;

    protected void initHandle() {
       dao = planRiskDao;
    }

    public int delePlanRiskByPlanId(String id) {
        return planRiskDao.delePlanRiskByPlanId(id);
    }
}
