package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.OtherGuaranteeRelationDao;
import com.xiaobaozi.dataSystem.product.handle.OtherGuaranteeRelationHandle;
import com.xiaobaozi.dataSystem.product.relation.OtherGuaranteeRelation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("otherGuaranteeRelationHandle")
public class OtherGuaranteeRelationHandleImpl extends GenericHandleImpl<OtherGuaranteeRelation> implements OtherGuaranteeRelationHandle {

    @Resource(name = "otherGuaranteeRelationDao")
    private OtherGuaranteeRelationDao otherGuaranteeRelationDao;

    protected void initHandle() {
        dao = otherGuaranteeRelationDao;
    }

    public int deleByProductId(String id){
        return otherGuaranteeRelationDao.deleByProductId(id);
    }
}
