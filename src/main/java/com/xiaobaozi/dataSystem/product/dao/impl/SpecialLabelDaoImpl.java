package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.SpecialLabelDao;
import com.xiaobaozi.dataSystem.product.relation.SpecialLabel;
import org.springframework.stereotype.Component;

@Component("specialLabelDao")
public class SpecialLabelDaoImpl extends GenericDaoImpl<SpecialLabel> implements SpecialLabelDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
