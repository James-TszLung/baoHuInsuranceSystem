package com.xiaobaozi.dataSystem.dictionary.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;

public interface DictionaryContentService extends GenericService<DictionaryContent> {

	public List<DictionaryContent> selectBydictionaryId(String dictionaryId);

	public int getDictionaryContentCount(DictionaryContentSearch sc);

	public List<DictionaryContent> getContentByDictionaryId(DictionaryContentSearch sc);

}
