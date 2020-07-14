package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.GuaranteeRelationDao;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("guaranteeRelationDao")
public class GuaranteeRelationDaoImpl extends GenericDaoImpl<GuaranteeRelation> implements GuaranteeRelationDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }

    public List<GuaranteeRelation> selectByProductId(String productId) {
        return selectList("selectByProductId",productId);
    }

    public int deleById(String id) {
        return this.deleteByMap("deleteById", id);
    }
}
