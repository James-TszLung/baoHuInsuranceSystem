package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.LabelRelationDao;
import com.xiaobaozi.dataSystem.product.handle.LabelRelationHandle;
import com.xiaobaozi.dataSystem.product.relation.LabelRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("labelRelationHandle")
public class LabelRelationHandleImpl extends GenericHandleImpl<LabelRelation> implements LabelRelationHandle {

    @Resource(name = "labelRelationDao")
    private LabelRelationDao labelRelationDao;

    protected void initHandle() {
        dao = labelRelationDao;
    }

    public int deleByProductId(String id){
        return labelRelationDao.deleByProductId(id);
    }
}
