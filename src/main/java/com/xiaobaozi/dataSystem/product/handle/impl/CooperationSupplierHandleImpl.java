package com.xiaobaozi.dataSystem.product.handle.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.product.dao.CooperationSupplierDao;
import com.xiaobaozi.dataSystem.product.handle.CooperationSupplierHandle;
import com.xiaobaozi.dataSystem.product.relation.CooperationSupplier;

@Component("cooperationSupplierHandle")
public class CooperationSupplierHandleImpl extends GenericHandleImpl<CooperationSupplier> implements CooperationSupplierHandle {

	@Resource(name = "cooperationSupplierDao")
	private CooperationSupplierDao cooperationSupplierDao;

	@Override
	protected void initHandle() {
		dao = cooperationSupplierDao;
	}

	public int deleByProductId(String id){
		return cooperationSupplierDao.deleByProductId(id);
	}

}
