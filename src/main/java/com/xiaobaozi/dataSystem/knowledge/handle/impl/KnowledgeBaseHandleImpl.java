package com.xiaobaozi.dataSystem.knowledge.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.knowledge.dao.KnowledgeBaseDao;
import com.xiaobaozi.dataSystem.knowledge.handle.KnowledgeBaseHandle;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;

@Component("knowledgeBaseHandle")
public class KnowledgeBaseHandleImpl extends GenericHandleImpl<KnowledgeBase> implements KnowledgeBaseHandle {

	@Resource(name = "knowledgeBaseDao")
	private KnowledgeBaseDao knowledgeBaseDao;

	@Override
	protected void initHandle() {
		dao = knowledgeBaseDao;
	}

	@Override
	public int getKnowledgeBaseCount(KnowledgeBaseSearch sc) {
		return knowledgeBaseDao.getKnowledgeBaseCount(sc);
	}

	@Override
	public List<KnowledgeBase> getKnowledgeBaseByPage(KnowledgeBaseSearch sc) {
		return knowledgeBaseDao.getKnowledgeBaseByPage(sc);
	}

	@Override
	public int insertRelationPushEassy(KnowledgeRelationPushEssay sc) {
		return knowledgeBaseDao.insertRelationPushEassy(sc);
	}

	@Override
	public int deleById(String id) throws Exception {
		return knowledgeBaseDao.deleById(id);
	}

	@Override
	public int deleteRelationByKnowledgeBaseId(String id) {
		return knowledgeBaseDao.deleteRelationByKnowledgeBaseId(id);
	}

	@Override
	public int deleteRelationByEssayId(String id) {
		return knowledgeBaseDao.deleteRelationByEssayId(id);
	}

}
