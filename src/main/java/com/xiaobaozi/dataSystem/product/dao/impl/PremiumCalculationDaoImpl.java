package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.PremiumCalculationDao;
import com.xiaobaozi.dataSystem.product.relation.PremiumCalculation;
import org.springframework.stereotype.Component;

@Component("premiumCalculationDao")
public class PremiumCalculationDaoImpl extends GenericDaoImpl<PremiumCalculation> implements PremiumCalculationDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
