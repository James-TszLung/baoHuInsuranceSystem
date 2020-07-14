package com.xiaobaozi.dataSystem.customizePlan.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.customizePlan.handle.PlanGuaranteeRelationHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.PlanGuaranteeRelation;
import com.xiaobaozi.dataSystem.customizePlan.service.PlanGuaranteeRelationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("planGuaranteeRelationService")
public class PlanGuaranteeRelationServiceImpl extends GenericServiceImpl<PlanGuaranteeRelation> implements PlanGuaranteeRelationService {

    @Resource(name="planGuaranteeRelationHandle")
    private PlanGuaranteeRelationHandle planGuaranteeRelationHandle;

    protected void initService() {
        handle = planGuaranteeRelationHandle;
    }


    public PlanGuaranteeRelation selectBy(Map<String, Object> map) {
        return planGuaranteeRelationHandle.selectBy(map);
    }

    public List<PlanGuaranteeRelation> selectBySafetyId(String safetyId) {
        return planGuaranteeRelationHandle.selectBySafetyId(safetyId);
    }

    public int deleBySafetyId(String safetyId) {
        return planGuaranteeRelationHandle.deleBySafetyId(safetyId);
    }
}
