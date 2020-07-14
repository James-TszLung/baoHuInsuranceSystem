package com.xiaobaozi.dataSystem.productLabel.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.productLabel.dao.ProductLabelDao;
import com.xiaobaozi.dataSystem.productLabel.handle.ProductLabelHandle;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;

@Component("productLabelHandle")
public class ProductLabelHandleImpl extends GenericHandleImpl<ProductLabel> implements ProductLabelHandle {

	@Resource(name = "productLabelDao")
	private ProductLabelDao productLabelDao;

	@Override
	protected void initHandle() {
		dao = productLabelDao;
	}

	@Override
	public int getProductLabelCount(ProductLabelSearch sc) {
		return productLabelDao.getProductLabelCount(sc);
	}

	@Override
	public List<ProductLabel> getProductLabelByPage(ProductLabelSearch sc) {
		return productLabelDao.getProductLabelByPage(sc);
	}

}
