package com.xiaobaozi.dataSystem.essay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.essay.pojo.PushProduct;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.essay.handle.PushEssayHandle;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;
import com.xiaobaozi.dataSystem.essay.service.PushEssayService;
import com.xiaobaozi.dataSystem.knowledge.handle.KnowledgeBaseHandle;

@Component("pushEssayService")
public class PushEssayServiceImpl extends GenericServiceImpl<PushEssay> implements PushEssayService {

	@Resource(name = "pushEssayHandle")
	private PushEssayHandle pushEssayHandle;
	@Resource
	private KnowledgeBaseHandle knowledgeBaseHandle;

	@Override
	protected void initService() {
		handle = pushEssayHandle;
	}

	public int getPushEssayCount(PushEssaySearch sc) {
		return pushEssayHandle.getPushEssayCount(sc);
	}

	public List<PushEssay> getPushEssayByPage(PushEssaySearch sc) {
		return pushEssayHandle.getPushEssayByPage(sc);
	}

	public int insertPushEssay(PushEssay sc) throws Exception {
		sc.setId(UUIDUtil.getUUID());
		if (sc.getEssayRelationDictionaryLs() != null && sc.getEssayRelationDictionaryLs().size() > 0) {
			List<EssayRelationDictionary> list = sc.getEssayRelationDictionaryLs();
			for (int i = 0; i < list.size(); i++) {
				EssayRelationDictionary relationDictionary = new EssayRelationDictionary();
				relationDictionary.setEssayId(sc.getId());
				relationDictionary.setDictionaryId(list.get(i).getDictionaryId());
				pushEssayHandle.insertRelationEssay(relationDictionary);
			}
		}
		if(sc.getProductIds()!=null && sc.getProductIds().length>0){
			for(int i=0;i<sc.getProductIds().length;i++){
				String productId = sc.getProductIds()[i];
				PushProduct pushProduct = new PushProduct();
				pushProduct.setPushEssayId(sc.getId());
				pushProduct.setProductId(productId);
				pushProduct.setSort(i);
				pushEssayHandle.insertPushProduct(pushProduct);
			}
		}
		return pushEssayHandle.insert(sc);
	}

	public boolean updatePushEssay(PushEssay sc) throws Exception {
		pushEssayHandle.deleteRelationByEssayId(sc.getId());
		pushEssayHandle.deletePushProductByEssayId(sc.getId());
		if (sc.getEssayRelationDictionaryLs() != null && sc.getEssayRelationDictionaryLs().size() > 0) {
			List<EssayRelationDictionary> list = sc.getEssayRelationDictionaryLs();
			for (int i = 0; i < list.size(); i++) {
				EssayRelationDictionary relationDictionary = new EssayRelationDictionary();
				relationDictionary.setEssayId(sc.getId());
				relationDictionary.setDictionaryId(list.get(i).getDictionaryId());
				pushEssayHandle.insertRelationEssay(relationDictionary);
			}
		}
		if(sc.getProductIds()!=null && sc.getProductIds().length>0){
			for(int i=0;i<sc.getProductIds().length;i++){
				String productId = sc.getProductIds()[i];
				PushProduct pushProduct = new PushProduct();
				pushProduct.setPushEssayId(sc.getId());
				pushProduct.setProductId(productId);
				pushProduct.setSort(i);
				pushEssayHandle.insertPushProduct(pushProduct);
			}
		}
		return pushEssayHandle.update(sc);
	}

	public boolean deletePushEssayById(PushEssay sc) throws Exception {
		knowledgeBaseHandle.deleteRelationByEssayId(sc.getId());
		pushEssayHandle.deletePushProductByEssayId(sc.getId());
		return pushEssayHandle.delete(sc);
	}
}
