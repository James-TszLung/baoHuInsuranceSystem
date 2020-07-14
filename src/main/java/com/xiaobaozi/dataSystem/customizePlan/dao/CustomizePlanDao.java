package com.xiaobaozi.dataSystem.customizePlan.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.CustomizePlan;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;

import java.util.List;

public interface CustomizePlanDao extends GenericDao<CustomizePlan> {

    public int getCustomizePlanCount(CustomizePlanSearch sc);

    public List<CustomizePlan> getCustomizePlanByPage(CustomizePlanSearch sc);

    public CustomizePlan getCustomizePlanById(String id);

    public int deleteCustomizePlanById(String id);
}
