package com.xiaobaozi.dataSystem.customizePlan.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanGuaranteeRelation;

import java.util.List;
import java.util.Map;

public interface PlanGuaranteeRelationDao extends GenericDao<PlanGuaranteeRelation> {

    public PlanGuaranteeRelation selectBy(Map<String,Object> map);

    public List<PlanGuaranteeRelation> selectBySafetyId(String safetyId);

    public int deleBySafetyId(String safetyId);
}
