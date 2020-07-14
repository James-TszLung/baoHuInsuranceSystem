package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.IncrementRelationDao;
import com.xiaobaozi.dataSystem.product.handle.IncrementRelationHandle;
import com.xiaobaozi.dataSystem.product.relation.IncrementRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("incrementRelationHandle")
public class IncrementRelationHandleImpl extends GenericHandleImpl<IncrementRelation> implements IncrementRelationHandle {

    @Resource(name = "incrementRelationDao")
    private IncrementRelationDao incrementRelationDao;

    protected void initHandle() {
        dao = incrementRelationDao;
    }

    public int deleByProductId(String id){
        return incrementRelationDao.deleByProductId(id);
    }
}
