package com.xiaobaozi.dataSystem.indemnity.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.indemnity.dao.IndemnitySubDao;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("indemnitySubDao")
public class IndemnitySubDaoImpl extends GenericDaoImpl<IndemnitySub> implements IndemnitySubDao {

    protected void initDao() {
        this.jdbcTemplate = super.getBaseinfojdbcTemplate();
        this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
    }
    public List<IndemnitySub> selectByIndemnityId(String indemnityId) {
        return selectList("selectByIndemnityId", indemnityId);
    }

    public int deleteById(String id) {
        return this.deleteByMap("deleteById", id);
    }

    public int deleteByIndemnityId(String indemnityId) {
        return this.deleteByMap("deleteByIndemnityId", indemnityId);
    }
}
