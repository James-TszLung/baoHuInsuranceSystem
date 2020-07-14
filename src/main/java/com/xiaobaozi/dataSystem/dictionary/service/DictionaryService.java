package com.xiaobaozi.dataSystem.dictionary.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;

public interface DictionaryService extends GenericService<Dictionary> {

	public int getDictionaryCount(DictionarySearch sc);

	public List<Dictionary> getDictionaryByPage(DictionarySearch sc);
	
	public int insertDictionary(Dictionary sc) throws Exception;
	
	public boolean updateDictionary(Dictionary sc) throws Exception;
	
	public List<DictionaryContent> selectBydictionaryId(String dictionaryId);

}
