package com.xiaobaozi.dataSystem.customizePlan.service;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.customizePlan.pojo.CustomizePlan;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;

import java.util.List;

public interface CustomizePlanService extends GenericService<CustomizePlan> {
    public int getCustomizePlanCount(CustomizePlanSearch sc);

    public List<CustomizePlan> getCustomizePlanByPage(CustomizePlanSearch sc);

    public CustomizePlan getCustomizePlanById(String id);

    public int insertCustomizePlan(CustomizePlan sc) throws DaoException;

    public boolean updateCustomizePlan(CustomizePlan sc) throws DaoException;

    public int deleteCustomizePlanById(CustomizePlan sc) throws Exception;
}
