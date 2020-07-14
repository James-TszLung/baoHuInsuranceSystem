package com.xiaobaozi.dataSystem.customizePlan.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.CustomizePlan;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;

import java.util.List;

public interface CustomizePlanHandle extends GenericHandle<CustomizePlan> {
    public int getCustomizePlanCount(CustomizePlanSearch sc);

    public List<CustomizePlan> getCustomizePlanByPage(CustomizePlanSearch sc);

    public CustomizePlan getCustomizePlanById(String id);

    public int deleteCustomizePlanById(String id);
}
