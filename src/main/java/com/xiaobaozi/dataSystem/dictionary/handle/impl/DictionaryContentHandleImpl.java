package com.xiaobaozi.dataSystem.dictionary.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.dictionary.dao.DictionaryContentDao;
import com.xiaobaozi.dataSystem.dictionary.handle.DictionaryContentHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

@Component("dictionaryContentHandle")
public class DictionaryContentHandleImpl extends GenericHandleImpl<DictionaryContent> implements DictionaryContentHandle {

	@Resource(name = "dictionaryContentDao")
	private DictionaryContentDao dictionaryContentDao;

	@Override
	protected void initHandle() {
		dao = dictionaryContentDao;
	}

	public List<DictionaryContent> selectBydictionaryId(String dictionaryId) {
		return dictionaryContentDao.selectBydictionaryId(dictionaryId);
	}

	public int delById(String id) throws Exception {
		return dictionaryContentDao.delById(id);
	}

	public int getDictionaryContentCount(DictionaryContentSearch sc) {
		return dictionaryContentDao.getDictionaryContentCount(sc);
	}

	public List<DictionaryContent> getContentByDictionaryId(DictionaryContentSearch sc) {
		return dictionaryContentDao.getContentByDictionaryId(sc);
	}

}
