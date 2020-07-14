package com.xiaobaozi.dataSystem.customizePlan.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanRisk;

public interface PlanRiskDao extends GenericDao<PlanRisk> {
    public int delePlanRiskByPlanId(String id);
}
