package com.xiaobaozi.dataSystem.product.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.product.dao.ClauseFileDao;
import com.xiaobaozi.dataSystem.product.relation.ClauseFile;
import org.springframework.stereotype.Component;

@Component("clauseFileDao")
public class ClauseFileDaoImpl extends GenericDaoImpl<ClauseFile> implements ClauseFileDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }

    public int deleByProductId(String id){
        return this.deleteByMap("deleByProductId", id);
    }
}
