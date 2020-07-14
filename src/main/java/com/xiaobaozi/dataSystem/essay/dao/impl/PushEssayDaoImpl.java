package com.xiaobaozi.dataSystem.essay.dao.impl;

import java.util.List;

import com.xiaobaozi.dataSystem.essay.pojo.PushProduct;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.essay.dao.PushEssayDao;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;

@Component("pushEssayDao")
public class PushEssayDaoImpl extends GenericDaoImpl<PushEssay> implements PushEssayDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public int getPushEssayCount(PushEssaySearch sc) {
		return (Integer) this.selectOne("countPushEssay", sc);
	}

	public List<PushEssay> getPushEssayByPage(PushEssaySearch sc) {
		return selectList("getListByPage", sc);
	}

	public int insertRelationEssay(EssayRelationDictionary sc) throws Exception{
		return this.insertByMap("insertRelationEssay", sc);
	}

	public List<EssayRelationDictionary> selectRelationByEssayId(String essayId) {
		return this.selectList("selectRelationByEssayId", essayId);
	}

	public int deleteRelationByEssayId(String essayId) throws Exception {
		return this.deleteByMap("deleteRelationByEssayId", essayId);
	}

	public int insertPushProduct(PushProduct sc) throws Exception {
		return  this.insertByMap("insertPushProduct", sc);
	}

	public int deletePushProductByEssayId(String pushEssayId) throws Exception {
		return this.deleteByMap("deletePushProductByEssayId", pushEssayId);
	}

}
