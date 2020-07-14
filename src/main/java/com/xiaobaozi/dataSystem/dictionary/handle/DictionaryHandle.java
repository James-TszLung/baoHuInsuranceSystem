package com.xiaobaozi.dataSystem.dictionary.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface DictionaryHandle extends GenericHandle<Dictionary> {
	public int getDictionaryCount(DictionarySearch sc);

	public List<Dictionary> getDictionaryByPage(DictionarySearch sc);

}
