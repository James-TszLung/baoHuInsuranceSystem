package com.xiaobaozi.dataSystem.customizePlan.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanConfigDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanConfig;
import org.springframework.stereotype.Component;

@Component("planConfigDao")
public class PlanConfigDaoImpl extends GenericDaoImpl<PlanConfig> implements PlanConfigDao {
    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int delePlanConfigByPlanId(String id) {
        return this.deleteByMap("deleByPlanId", id);
    }
}
