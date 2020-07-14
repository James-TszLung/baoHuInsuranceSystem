package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.GuaranteedRuleDao;
import com.xiaobaozi.dataSystem.product.relation.GuaranteedRule;
import org.springframework.stereotype.Component;

@Component("guaranteedRuleDao")
public class GuaranteedRuleDaoImpl extends GenericDaoImpl<GuaranteedRule> implements GuaranteedRuleDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
