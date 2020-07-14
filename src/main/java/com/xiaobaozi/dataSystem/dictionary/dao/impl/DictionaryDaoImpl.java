package com.xiaobaozi.dataSystem.dictionary.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.dictionary.dao.DictionaryDao;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;

@Component("dictionaryDao")
public class DictionaryDaoImpl extends GenericDaoImpl<Dictionary> implements DictionaryDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getDictionaryCount(DictionarySearch sc) {
		return (Integer) this.selectOne("countDictionary", sc);
	}

	@Override
	public List<Dictionary> getDictionaryByPage(DictionarySearch sc) {
		return selectList("getListByPage", sc);
	}

}
