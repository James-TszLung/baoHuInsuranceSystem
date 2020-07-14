package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.OtherGuaranteeRelationDao;
import com.xiaobaozi.dataSystem.product.relation.OtherGuaranteeRelation;
import org.springframework.stereotype.Component;

@Component("otherGuaranteeRelationDao")
public class OtherGuaranteeRelationDaoImpl extends GenericDaoImpl<OtherGuaranteeRelation> implements OtherGuaranteeRelationDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
