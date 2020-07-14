package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.GuaranteeRelationDao;
import com.xiaobaozi.dataSystem.product.handle.GuaranteeRelationHandle;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("guaranteeRelationHandle")
public class GuaranteeRelationHandleImpl extends GenericHandleImpl<GuaranteeRelation> implements GuaranteeRelationHandle {

    @Resource(name = "guaranteeRelationDao")
    private GuaranteeRelationDao guaranteeRelationDao;

    protected void initHandle() {
        dao = guaranteeRelationDao;
    }

    public int deleByProductId(String id){
        return guaranteeRelationDao.deleByProductId(id);
    }

    public List<GuaranteeRelation> selectByProductId(String productId) {
        return guaranteeRelationDao.selectByProductId(productId);
    }

    public int deleById(String id) {
        return guaranteeRelationDao.deleById(id);
    }
}
