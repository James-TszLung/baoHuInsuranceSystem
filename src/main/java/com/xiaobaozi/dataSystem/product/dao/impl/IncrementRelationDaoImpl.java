package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.IncrementRelationDao;
import com.xiaobaozi.dataSystem.product.relation.IncrementRelation;
import org.springframework.stereotype.Component;

@Component("incrementRelationDao")
public class IncrementRelationDaoImpl extends GenericDaoImpl<IncrementRelation> implements IncrementRelationDao{

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
