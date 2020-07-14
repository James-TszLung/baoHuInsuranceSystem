package com.xiaobaozi.dataSystem.dictionary.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface DictionaryDao extends GenericDao<Dictionary> {

	public int getDictionaryCount(DictionarySearch sc);

	public List<Dictionary> getDictionaryByPage(DictionarySearch sc);

}

