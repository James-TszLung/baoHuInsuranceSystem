package com.xiaobaozi.dataSystem.dictionary.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;

public interface DictionaryContentHandle extends GenericHandle<DictionaryContent> {

	public List<DictionaryContent> selectBydictionaryId(String dictionaryId);

	public int  delById(String id) throws Exception;


	public int getDictionaryContentCount(DictionaryContentSearch sc);

	public List<DictionaryContent> getContentByDictionaryId(DictionaryContentSearch sc);
}
