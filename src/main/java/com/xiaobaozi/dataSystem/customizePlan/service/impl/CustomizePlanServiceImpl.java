package com.xiaobaozi.dataSystem.customizePlan.service.impl;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.customizePlan.handle.*;
import com.xiaobaozi.dataSystem.customizePlan.pojo.*;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;
import com.xiaobaozi.dataSystem.customizePlan.service.CustomizePlanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Component("customizePlanService")
public class CustomizePlanServiceImpl extends GenericServiceImpl<CustomizePlan> implements CustomizePlanService {

    @Resource(name = "customizePlanHandle")
    private CustomizePlanHandle customizePlanHandle;
    @Resource(name = "planConfigHandle")
    private PlanConfigHandle planConfigHandle;
    @Resource(name="planRiskHandle")
    private PlanRiskHandle planRiskHandle;
    @Resource(name="planSafetyHandle")
    private PlanSafetyHandle planSafetyHandle;
    @Resource(name="")
    private PlanGuaranteeRelationHandle planGuaranteeRelationHandle;

    protected void initService() {
        handle = customizePlanHandle;
    }

    public int getCustomizePlanCount(CustomizePlanSearch sc) {
        return customizePlanHandle.getCustomizePlanCount(sc);
    }

    public List<CustomizePlan> getCustomizePlanByPage(CustomizePlanSearch sc) {
        return customizePlanHandle.getCustomizePlanByPage(sc);
    }

    public CustomizePlan getCustomizePlanById(String id) {
        return customizePlanHandle.getCustomizePlanById(id);
    }

    public int insertCustomizePlan(CustomizePlan sc) throws DaoException{
        sc.setId(UUIDUtil.getUUID());
        insertData(sc);
        return customizePlanHandle.insert(sc);
    }

    public boolean updateCustomizePlan(CustomizePlan sc) throws DaoException{
        deleteData(sc);
        insertData(sc);
        return customizePlanHandle.update(sc);
    }

    public int deleteCustomizePlanById(CustomizePlan sc) throws Exception {
        deleteData(sc);
        return customizePlanHandle.deleteCustomizePlanById(sc.getId());
    }

    private void insertData(CustomizePlan sc) throws DaoException{
        List<PlanConfig> planConfigLs = sc.getPlanConfigLs();
        if(planConfigLs!=null && planConfigLs.size()>0){
            int userCount = 0;
            for(int i=0;i<planConfigLs.size();i++){
                PlanConfig planConfig = planConfigLs.get(i);
                if (planConfig != null && StringUtils.isNotEmpty(planConfig.getName())) {
                    planConfig.setId(UUIDUtil.getUUID());
                    planConfig.setPlanId(sc.getId());
                    planConfig.setSort(i);
                    planConfigHandle.insert(planConfig);
                    userCount++;
                }
            }
            sc.setUserCount(userCount);
        }
        List<PlanRisk> planRiskLs = sc.getPlanRiskLs();
        if(planRiskLs!=null && planRiskLs.size()>0){
            for(int i=0;i<planRiskLs.size();i++) {
                PlanRisk planRisk = planRiskLs.get(i);
                if (planRisk != null && StringUtils.isNotEmpty(planRisk.getTypeId())) {
                    planRisk.setId(UUIDUtil.getUUID());
                    planRisk.setPlanId(sc.getId());
                    planRisk.setSort(i);
                    List<PlanSafety> planSafetyLs = planRisk.getPlanSafetyLs();
                    if(planSafetyLs!=null && planSafetyLs.size()>0){
                        for(int j=0;j<planSafetyLs.size();j++) {
                            PlanSafety safety = planSafetyLs.get(j);
                            if (safety != null && StringUtils.isNotEmpty(safety.getProductId()) && StringUtils.isNotEmpty(safety.getName())) {
                                safety.setId(UUIDUtil.getUUID());
                                safety.setRiskId(planRisk.getId());
                                safety.setSort(j);
                                List<PlanGuaranteeRelation> planGuaranteeRelationLs = safety.getPlanGuaranteeRelationLs();
                                if(planGuaranteeRelationLs!=null && planGuaranteeRelationLs.size()>0){
                                    for(int k=0;k<planGuaranteeRelationLs.size();k++) {
                                        PlanGuaranteeRelation relation = planGuaranteeRelationLs.get(k);
                                        if (relation != null && StringUtils.isNotEmpty(relation.getProductId()) && StringUtils.isNotEmpty(relation.getGuaranteeRelationId())) {
                                            relation.setId(UUIDUtil.getUUID());
                                            relation.setSafetyId(safety.getId());
                                            relation.setSort(k);
                                            planGuaranteeRelationHandle.insert(relation);
                                        }
                                    }
                                }
                                planSafetyHandle.insert(safety);
                            }
                        }
                    }
                    planRiskHandle.insert(planRisk);
                }
            }
        }
    }

    private void deleteData(CustomizePlan sc){
        planConfigHandle.delePlanConfigByPlanId(sc.getId());
        List<PlanRisk> planRiskLs = sc.getPlanRiskLs();
        if(planRiskLs!=null && planRiskLs.size()>0){
            for(int i=0;i<planRiskLs.size();i++){
                PlanRisk planRisk = planRiskLs.get(i);
                List<PlanSafety> planSafetyLs = planRisk.getPlanSafetyLs();
                if(planSafetyLs!=null && planSafetyLs.size()>0) {
                    for (int j = 0; j < planSafetyLs.size(); j++) {
                        PlanSafety safety = planSafetyLs.get(j);
                        planGuaranteeRelationHandle.deleBySafetyId(safety.getId());
                    }
                }
                planSafetyHandle.delePlanSafetyByRiskId(planRisk.getId());
            }
        }
        planRiskHandle.delePlanRiskByPlanId(sc.getId());
    }

}
