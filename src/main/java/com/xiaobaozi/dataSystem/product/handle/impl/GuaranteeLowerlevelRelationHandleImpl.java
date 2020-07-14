package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.GuaranteeLowerlevelRelationDao;
import com.xiaobaozi.dataSystem.product.handle.GuaranteeLowerlevelRelationHandle;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeLowerlevelRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("guaranteeLowerlevelRelationHandle")
public class GuaranteeLowerlevelRelationHandleImpl extends GenericHandleImpl<GuaranteeLowerlevelRelation> implements GuaranteeLowerlevelRelationHandle {

    @Resource(name = "guaranteeLowerlevelRelationDao")
    private GuaranteeLowerlevelRelationDao guaranteeLowerlevelRelationDao;

    protected void initHandle() {
        dao = guaranteeLowerlevelRelationDao;
    }

    public int deleByGuaranteeRelationId(String id){
        return guaranteeLowerlevelRelationDao.deleByGuaranteeRelationId(id);
    }
}
