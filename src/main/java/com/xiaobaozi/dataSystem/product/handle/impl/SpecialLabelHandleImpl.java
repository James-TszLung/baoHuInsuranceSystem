package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.SpecialLabelDao;
import com.xiaobaozi.dataSystem.product.handle.SpecialLabelHandle;
import com.xiaobaozi.dataSystem.product.relation.SpecialLabel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("specialLabelHandle")
public class SpecialLabelHandleImpl extends GenericHandleImpl<SpecialLabel> implements SpecialLabelHandle {

    @Resource(name = "specialLabelDao")
    private SpecialLabelDao specialLabelDao;

    protected void initHandle() {
        dao = specialLabelDao;
    }

    public int deleByProductId(String id){
        return specialLabelDao.deleByProductId(id);
    }
}
