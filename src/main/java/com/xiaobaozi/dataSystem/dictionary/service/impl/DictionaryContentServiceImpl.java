package com.xiaobaozi.dataSystem.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.dictionary.handle.DictionaryContentHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryContentService;

@Component("dictionaryContentService")
public class DictionaryContentServiceImpl extends GenericServiceImpl<DictionaryContent> implements DictionaryContentService {

	@Resource(name = "dictionaryContentHandle")
	private DictionaryContentHandle dictionaryContentHandle;

	@Override
	protected void initService() {
		handle = dictionaryContentHandle;
	}

	public List<DictionaryContent> selectBydictionaryId(String dictionaryId) {
		return dictionaryContentHandle.selectBydictionaryId(dictionaryId);
	}

	public int getDictionaryContentCount(DictionaryContentSearch sc) {
		return dictionaryContentHandle.getDictionaryContentCount(sc);
	}

	public List<DictionaryContent> getContentByDictionaryId(DictionaryContentSearch sc) {
		return dictionaryContentHandle.getContentByDictionaryId(sc);
	}

}
