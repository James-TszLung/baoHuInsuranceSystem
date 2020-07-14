package com.xiaobaozi.dataSystem.customizePlan.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanSafetyDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanSafety;
import org.springframework.stereotype.Component;

@Component("planSafetyDao")
public class PlanSafetyDaoImpl extends GenericDaoImpl<PlanSafety> implements PlanSafetyDao {
    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int delePlanSafetyByRiskId(String id) {
        return this.deleteByMap("deleByRiskId", id);
    }
}
