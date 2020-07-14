package com.xiaobaozi.dataSystem.knowledge.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;

public interface KnowledgeBaseDao extends GenericDao<KnowledgeBase> {

	public int getKnowledgeBaseCount(KnowledgeBaseSearch sc);

	public List<KnowledgeBase> getKnowledgeBaseByPage(KnowledgeBaseSearch sc);
	
	
	public int insertRelationPushEassy(KnowledgeRelationPushEssay sc);
	
	public int deleById(String id) throws Exception;
	
	public int deleteRelationByKnowledgeBaseId(String id); 
	
	
	public int deleteRelationByEssayId(String id); 
}

