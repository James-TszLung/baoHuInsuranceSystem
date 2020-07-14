package com.xiaobaozi.dataSystem.customizePlan.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.customizePlan.dao.CustomizePlanDao;
import com.xiaobaozi.dataSystem.customizePlan.pojo.CustomizePlan;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("customizePlanDao")
public class CustomizePlanDaoImpl extends GenericDaoImpl<CustomizePlan> implements CustomizePlanDao {
    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int getCustomizePlanCount(CustomizePlanSearch sc) {
        return (Integer) this.selectOne("countCustomizePlan", sc);
    }

    public List<CustomizePlan> getCustomizePlanByPage(CustomizePlanSearch sc) {
        return selectList("getListByPage", sc);
    }

    public CustomizePlan getCustomizePlanById(String id) {
        return (CustomizePlan) this.selectOne("selectById2",id);
    }

    public int deleteCustomizePlanById(String id){
        return this.deleteByMap("deleteCustomizePlanById", id);
    }
}
