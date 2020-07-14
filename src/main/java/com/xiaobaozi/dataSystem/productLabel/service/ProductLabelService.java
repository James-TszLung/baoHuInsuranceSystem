package com.xiaobaozi.dataSystem.productLabel.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;

public interface ProductLabelService extends GenericService<ProductLabel> {

	public int getProductLabelCount(ProductLabelSearch sc);

	public List<ProductLabel> getProductLabelByPage(ProductLabelSearch sc);
	
	public int insertProductLabel(ProductLabel sc) throws Exception;
	
	public boolean editProductLabel(ProductLabel sc) throws Exception;

	public boolean deleteProductLabelById(ProductLabel sc) throws Exception;

}
