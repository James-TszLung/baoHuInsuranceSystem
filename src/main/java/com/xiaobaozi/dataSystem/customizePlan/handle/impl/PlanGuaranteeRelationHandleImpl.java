package com.xiaobaozi.dataSystem.customizePlan.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.PlanGuaranteeRelationDao;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanGuaranteeRelationHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanGuaranteeRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("planGuaranteeRelationHandle")
public class PlanGuaranteeRelationHandleImpl extends GenericHandleImpl<PlanGuaranteeRelation> implements PlanGuaranteeRelationHandle {
    @Resource(name="planGuaranteeRelationDao")
    private PlanGuaranteeRelationDao planGuaranteeRelationDao;

    protected void initHandle() {
      dao = planGuaranteeRelationDao;
    }


    public PlanGuaranteeRelation selectBy(Map<String, Object> map) {
        return planGuaranteeRelationDao.selectBy(map);
    }

    public List<PlanGuaranteeRelation> selectBySafetyId(String safetyId) {
        return planGuaranteeRelationDao.selectBySafetyId(safetyId);
    }

    public int deleBySafetyId(String safetyId) {
        return planGuaranteeRelationDao.deleBySafetyId(safetyId);
    }
}
