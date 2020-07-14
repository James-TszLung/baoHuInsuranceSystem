package com.xiaobaozi.dataSystem.knowledge.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface KnowledgeBaseService extends GenericService<KnowledgeBase> {

	public int getKnowledgeBaseCount(KnowledgeBaseSearch sc);

	public List<KnowledgeBase> getKnowledgeBaseByPage(KnowledgeBaseSearch sc);
	
	
	public int insertRelationPushEassy(KnowledgeRelationPushEssay sc);
	
	public int deleById(String id) throws Exception;
	
	public int insertKnowledgeBase(KnowledgeBase sc) throws Exception;
	
	public boolean updateKnowledgeBase(KnowledgeBase sc) throws Exception;

}
