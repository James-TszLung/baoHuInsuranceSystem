package com.xiaobaozi.dataSystem.customizePlan.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanConfig;

public interface PlanConfigDao extends GenericDao<PlanConfig> {

    public int delePlanConfigByPlanId(String id);

}
