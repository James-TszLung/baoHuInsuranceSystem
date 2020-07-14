package com.xiaobaozi.dataSystem.productLabel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.productLabel.handle.ProductLabelHandle;
import com.xiaobaozi.dataSystem.productLabel.handle.ProductLabelRelationHandle;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;
import com.xiaobaozi.dataSystem.productLabel.service.ProductLabelService;

@Component("productLabelService")
public class ProductLabelServiceImpl extends GenericServiceImpl<ProductLabel> implements ProductLabelService {

	@Resource(name = "productLabelHandle")
	private ProductLabelHandle productLabelHandle;
	@Resource
	private ProductLabelRelationHandle productLabelRelationHandle;

	@Override
	protected void initService() {
		handle = productLabelHandle;
	}

	public int getProductLabelCount(ProductLabelSearch sc) {
		return productLabelHandle.getProductLabelCount(sc);
	}

	public List<ProductLabel> getProductLabelByPage(ProductLabelSearch sc) {
		return productLabelHandle.getProductLabelByPage(sc);
	}

	public int insertProductLabel(ProductLabel sc) throws Exception {
		sc.setId(UUIDUtil.getUUID());
		List<ProductLabelRelation> list = sc.getProductLabelRelationLs();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ProductLabelRelation relation = list.get(i);
				relation.setProductLabelId(sc.getId());
				productLabelRelationHandle.insert(relation);
			}

		}else{
			sc.setProductcount(0);
		}

		return productLabelHandle.insert(sc);
	}

	public boolean editProductLabel(ProductLabel sc) throws Exception {
		productLabelRelationHandle.delById(sc.getId());
		List<ProductLabelRelation> list = sc.getProductLabelRelationLs();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ProductLabelRelation relation = list.get(i);
				relation.setProductLabelId(sc.getId());
				productLabelRelationHandle.insert(relation);
			}
			sc.setProductcount(list.size());
		}else{
			sc.setProductcount(0);
		}
		return productLabelHandle.update(sc);
	}

	public boolean deleteProductLabelById(ProductLabel sc) throws Exception {
		productLabelRelationHandle.delById(sc.getId());
		return productLabelHandle.delete(sc);
	}

}
