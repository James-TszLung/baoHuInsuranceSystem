package com.xiaobaozi.dataSystem.productLabel.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;

public interface ProductLabelHandle extends GenericHandle<ProductLabel> {
	public int getProductLabelCount(ProductLabelSearch sc);

	public List<ProductLabel> getProductLabelByPage(ProductLabelSearch sc);

}
