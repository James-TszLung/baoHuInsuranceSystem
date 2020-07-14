package com.xiaobaozi.dataSystem.customizePlan.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanGuaranteeRelationDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanGuaranteeRelation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("planGuaranteeRelationDao")
public class PlanGuaranteeRelationDaoImpl extends GenericDaoImpl<PlanGuaranteeRelation> implements PlanGuaranteeRelationDao {
    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public PlanGuaranteeRelation selectBy(Map<String, Object> map) {
        return (PlanGuaranteeRelation) this.selectOne("selectBy",map);
    }

    public List<PlanGuaranteeRelation> selectBySafetyId(String safetyId) {
        return selectList("selectBySafetyId", safetyId);
    }

    public int deleBySafetyId(String safetyId) {
        return this.deleteByMap("deleBySafetyId", safetyId);
    }
}
