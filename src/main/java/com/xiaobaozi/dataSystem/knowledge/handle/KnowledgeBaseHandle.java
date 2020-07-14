package com.xiaobaozi.dataSystem.knowledge.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface KnowledgeBaseHandle extends GenericHandle<KnowledgeBase> {

	public int getKnowledgeBaseCount(KnowledgeBaseSearch sc);

	public List<KnowledgeBase> getKnowledgeBaseByPage(KnowledgeBaseSearch sc);
	
	
	public int insertRelationPushEassy(KnowledgeRelationPushEssay sc);
	
	public int deleById(String id) throws Exception;

	public int deleteRelationByKnowledgeBaseId(String id);
	
	public int deleteRelationByEssayId(String id); 
}
