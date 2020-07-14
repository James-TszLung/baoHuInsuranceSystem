package com.xiaobaozi.dataSystem.knowledge.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.knowledge.dao.KnowledgeBaseDao;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;

@Component("knowledgeBaseDao")
public class KnowledgeBaseDaoImpl extends GenericDaoImpl<KnowledgeBase> implements KnowledgeBaseDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getKnowledgeBaseCount(KnowledgeBaseSearch sc) {
		return (Integer) this.selectOne("countKnowledgeBase", sc);
	}

	@Override
	public List<KnowledgeBase> getKnowledgeBaseByPage(KnowledgeBaseSearch sc) {
		return selectList("getListByPage", sc);
	}

	@Override
	public int insertRelationPushEassy(KnowledgeRelationPushEssay sc) {
		return this.insertByMap("insertRelationPushEassy", sc);
	}

	@Override
	public int deleById(String id)throws Exception {
		return this.deleteByMap("deleById", id);
	}

	@Override
	public int deleteRelationByKnowledgeBaseId(String id) {
		return this.deleteByMap("deleteRelationByKnowledgeBaseId", id);
	}

	@Override
	public int deleteRelationByEssayId(String id) {
		return this.deleteByMap("deleteRelationByEssayId", id);
	}

}
