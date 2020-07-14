package com.xiaobaozi.dataSystem.customizePlan.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanSafety;

public interface PlanSafetyDao extends GenericDao<PlanSafety> {
    public int delePlanSafetyByRiskId(String id);
}
