package com.xiaobaozi.dataSystem.dictionary.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.dictionary.dao.DictionaryDao;
import com.xiaobaozi.dataSystem.dictionary.handle.DictionaryHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;

@Component("dictionaryHandle")
public class DictionaryHandleImpl extends GenericHandleImpl<Dictionary> implements DictionaryHandle {

	@Resource(name = "dictionaryDao")
	private DictionaryDao dictionaryDao;

	@Override
	protected void initHandle() {
		dao = dictionaryDao;
	}

	@Override
	public int getDictionaryCount(DictionarySearch sc) {
		return dictionaryDao.getDictionaryCount(sc);
	}

	@Override
	public List<Dictionary> getDictionaryByPage(DictionarySearch sc) {
		return dictionaryDao.getDictionaryByPage(sc);
	}

}
