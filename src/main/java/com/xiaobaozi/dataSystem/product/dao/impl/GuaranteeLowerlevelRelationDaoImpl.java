package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.GuaranteeLowerlevelRelationDao;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeLowerlevelRelation;
import org.springframework.stereotype.Component;

@Component("guaranteeLowerlevelRelationDao")
public class GuaranteeLowerlevelRelationDaoImpl extends GenericDaoImpl<GuaranteeLowerlevelRelation> implements GuaranteeLowerlevelRelationDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public int deleByGuaranteeRelationId(String id){
        return this.deleteByMap("deleByGuaranteeRelationId", id);
    }
}
