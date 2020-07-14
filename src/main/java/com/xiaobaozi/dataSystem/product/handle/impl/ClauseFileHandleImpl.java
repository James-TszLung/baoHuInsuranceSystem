package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.ClauseFileDao;
import com.xiaobaozi.dataSystem.product.handle.ClauseFileHandle;
import com.xiaobaozi.dataSystem.product.relation.ClauseFile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("clauseFileHandle")
public class ClauseFileHandleImpl extends GenericHandleImpl<ClauseFile> implements ClauseFileHandle {

    @Resource(name = "clauseFileDao")
    private ClauseFileDao clauseFileDao;

    protected void initHandle() {
        dao = clauseFileDao;
    }

    public int deleByProductId(String id){
        return clauseFileDao.deleByProductId(id);
    }
}
