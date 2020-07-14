package com.xiaobaozi.dataSystem.product.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.product.relation.ClauseFile;

public interface ClauseFileDao extends GenericDao<ClauseFile> {
    public int deleByProductId(String id);
}
