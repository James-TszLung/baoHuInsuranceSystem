package com.xiaobaozi.dataSystem.dictionary.dao.impl;

import java.util.List;

import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.dictionary.dao.DictionaryContentDao;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

@Component("dictionaryContentDao")
public class DictionaryContentDaoImpl extends GenericDaoImpl<DictionaryContent> implements DictionaryContentDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public List<DictionaryContent> selectBydictionaryId(String dictionaryId) {
		return this.selectList("selectBydictionaryId", dictionaryId);
	}

	public int delById(String id) throws Exception {
		return this.deleteByMap("delById", id);
	}

	public int getDictionaryContentCount(DictionaryContentSearch sc) {
		return (Integer) this.selectOne("countContentByDictionaryId", sc);
	}

	public List<DictionaryContent> getContentByDictionaryId(DictionaryContentSearch sc) {
		return selectList("selectContentByDictionaryId", sc);
	}

}
