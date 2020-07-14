package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.LabelRelationDao;
import com.xiaobaozi.dataSystem.product.relation.LabelRelation;
import org.springframework.stereotype.Component;

@Component("labelRelationDao")
public class LabelRelationDaoImpl extends GenericDaoImpl<LabelRelation> implements LabelRelationDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
