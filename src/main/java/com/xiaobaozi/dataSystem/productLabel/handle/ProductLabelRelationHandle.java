package com.xiaobaozi.dataSystem.productLabel.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;

public interface ProductLabelRelationHandle extends GenericHandle<ProductLabelRelation> {
	public List<ProductLabelRelation> selectByProductLabelId(String productLabelId);

	public int delById(String id);

}
