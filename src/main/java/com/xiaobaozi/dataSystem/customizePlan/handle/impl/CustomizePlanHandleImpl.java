package com.xiaobaozi.dataSystem.customizePlan.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.CustomizePlanDao;
import com.xiaobaozi.dataSystem.customizePlan.handle.CustomizePlanHandle;
import com.xiaobaozi.dataSystem.customizePlan.pojo.CustomizePlan;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("customizePlanHandle")
public class CustomizePlanHandleImpl extends GenericHandleImpl<CustomizePlan> implements CustomizePlanHandle {

    @Resource(name = "customizePlanDao")
    private CustomizePlanDao customizePlanDao;

    protected void initHandle() {
        dao = customizePlanDao;
    }

    public int getCustomizePlanCount(CustomizePlanSearch sc) {
        return customizePlanDao.getCustomizePlanCount(sc);
    }

    public List<CustomizePlan> getCustomizePlanByPage(CustomizePlanSearch sc) {
        return customizePlanDao.getCustomizePlanByPage(sc);
    }

    public CustomizePlan getCustomizePlanById(String id) {
        return customizePlanDao.getCustomizePlanById(id);
    }

    public int deleteCustomizePlanById(String id) {
        return customizePlanDao.deleteCustomizePlanById(id);
    }

}
