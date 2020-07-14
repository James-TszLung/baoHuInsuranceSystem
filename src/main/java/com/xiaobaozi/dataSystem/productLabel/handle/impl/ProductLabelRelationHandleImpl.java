package com.xiaobaozi.dataSystem.productLabel.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.productLabel.dao.ProductLabelRelationDao;
import com.xiaobaozi.dataSystem.productLabel.handle.ProductLabelRelationHandle;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;

@Component("productLabelRelationHandle")
public class ProductLabelRelationHandleImpl extends GenericHandleImpl<ProductLabelRelation> implements ProductLabelRelationHandle {

	@Resource(name = "productLabelRelationDao")
	private ProductLabelRelationDao productLabelRelationDao;

	@Override
	protected void initHandle() {
		dao = productLabelRelationDao;
	}

	public List<ProductLabelRelation> selectByProductLabelId(String productLabelId) {
		return productLabelRelationDao.selectByProductLabelId(productLabelId);
	}

	public int delById(String id){
		return productLabelRelationDao.delById(id);
	}

}
