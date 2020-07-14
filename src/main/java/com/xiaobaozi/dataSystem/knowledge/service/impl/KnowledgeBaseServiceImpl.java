package com.xiaobaozi.dataSystem.knowledge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.knowledge.handle.KnowledgeBaseHandle;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;
import com.xiaobaozi.dataSystem.knowledge.service.KnowledgeBaseService;

@Component("knowledgeBaseService")
public class KnowledgeBaseServiceImpl extends GenericServiceImpl<KnowledgeBase> implements KnowledgeBaseService {

	@Resource(name = "knowledgeBaseHandle")
	private KnowledgeBaseHandle knowledgeBaseHandle;

	@Override
	protected void initService() {
		handle = knowledgeBaseHandle;
	}

	@Override
	public int getKnowledgeBaseCount(KnowledgeBaseSearch sc) {
		return knowledgeBaseHandle.getKnowledgeBaseCount(sc);
	}

	@Override
	public List<KnowledgeBase> getKnowledgeBaseByPage(KnowledgeBaseSearch sc) {
		return knowledgeBaseHandle.getKnowledgeBaseByPage(sc);
	}

	@Override
	public int insertRelationPushEassy(KnowledgeRelationPushEssay sc) {
		return knowledgeBaseHandle.insertRelationPushEassy(sc);
	}

	@Override
	public int deleById(String id) throws Exception {
		return knowledgeBaseHandle.deleById(id);
	}

	@Override
	public int insertKnowledgeBase(com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase sc) throws Exception {
		sc.setId(UUIDUtil.getUUID());
		if (sc.getKonwRelationLs() != null && sc.getKonwRelationLs().size() > 0) {
			List<KnowledgeRelationPushEssay> relationLs = sc.getKonwRelationLs();
			for (int i = 0; i < relationLs.size(); i++) {
				relationLs.get(i).setKnowledgeBaseId(sc.getId());
				knowledgeBaseHandle.insertRelationPushEassy(relationLs.get(i));
			}
		}
		return	knowledgeBaseHandle.insert(sc);
	}

	@Override
	public boolean updateKnowledgeBase(KnowledgeBase sc) throws Exception {
		knowledgeBaseHandle.deleteRelationByKnowledgeBaseId(sc.getId());
		if (sc.getKonwRelationLs() != null && sc.getKonwRelationLs().size() > 0) {
			List<KnowledgeRelationPushEssay> relationLs = sc.getKonwRelationLs();
			for (int i = 0; i < relationLs.size(); i++) {
				relationLs.get(i).setKnowledgeBaseId(sc.getId());
				knowledgeBaseHandle.insertRelationPushEassy(relationLs.get(i));
			}
		}
		return knowledgeBaseHandle.update(sc);
	}

}
