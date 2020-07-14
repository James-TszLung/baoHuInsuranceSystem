package com.xiaobaozi.dataSystem.customizePlan.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanRiskDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanRisk;
import org.springframework.stereotype.Component;

@Component("planRiskDao")
public class PlanRiskDaoImpl extends GenericDaoImpl<PlanRisk> implements PlanRiskDao{

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int delePlanRiskByPlanId(String id) {
        return this.deleteByMap("deleByPlanId", id);
    }
}