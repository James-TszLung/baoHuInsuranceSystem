package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.CooperationSupplier;

public interface CooperationSupplierHandle extends GenericHandle<CooperationSupplier> {

	public int deleByProductId(String id);
}
