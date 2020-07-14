package com.xiaobaozi.dataSystem.product.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.GuaranteedRuleDao;
import com.xiaobaozi.dataSystem.product.handle.GuaranteedRuleHandle;
import com.xiaobaozi.dataSystem.product.relation.GuaranteedRule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("guaranteedRuleHandle")
public class GuaranteedRuleHandleImpl extends GenericHandleImpl<GuaranteedRule> implements GuaranteedRuleHandle {
    @Resource(name = "guaranteedRuleDao")
    private GuaranteedRuleDao guaranteedRuleDao;
    protected void initHandle() {
        dao = guaranteedRuleDao;
    }

    public int deleByProductId(String id){
        return guaranteedRuleDao.deleByProductId(id);
    }
}
