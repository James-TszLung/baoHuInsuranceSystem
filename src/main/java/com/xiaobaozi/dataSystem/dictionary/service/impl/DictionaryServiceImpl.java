package com.xiaobaozi.dataSystem.dictionary.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.dictionary.handle.DictionaryContentHandle;
import com.xiaobaozi.dataSystem.dictionary.handle.DictionaryHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryService;

@Component("dictionaryService")
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary> implements DictionaryService {

	@Resource(name = "dictionaryHandle")
	private DictionaryHandle dictionaryHandle;
	@Resource
	private DictionaryContentHandle dictionaryContentHandle;

	@Override
	protected void initService() {
		handle = dictionaryHandle;
	}

	public int getDictionaryCount(DictionarySearch sc) {
		return dictionaryHandle.getDictionaryCount(sc);
	}

	public List<Dictionary> getDictionaryByPage(DictionarySearch sc) {
		return dictionaryHandle.getDictionaryByPage(sc);
	}

	public int insertDictionary(Dictionary sc) throws Exception {
		sc.setId(UUIDUtil.getUUID());
		if (sc != null && sc.getContentLs() != null && sc.getContentLs().size() > 0) {
			List<DictionaryContent> list = sc.getContentLs();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && StringUtils.isNotBlank(list.get(i).getContent())) {
					DictionaryContent content = new DictionaryContent();
					content.setId(UUIDUtil.getUUID());
					content.setDictionaryId(sc.getId());
					content.setContent(list.get(i).getContent());
					content.setSort(list.get(i).getSort());
					dictionaryContentHandle.insert(content);
				}
			}
		}

		return dictionaryHandle.insert(sc);
	}

	public boolean updateDictionary(Dictionary sc) throws Exception {
		if (sc != null && sc.getContentLs() != null && sc.getContentLs().size() > 0) {
			// 先删除
			List<DictionaryContent> contentLsAll = dictionaryContentHandle.selectBydictionaryId(sc.getId());
			List<DictionaryContent> list = sc.getContentLs();
			List<String> idLs = new ArrayList<String>();
			List<String> newidLs = new ArrayList<String>();
			if (contentLsAll != null && list != null) {
				for (int i = 0; i < contentLsAll.size(); i++) {
					idLs.add(contentLsAll.get(i).getId());
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j)!=null && StringUtils.isNotBlank(list.get(j).getId())) {
							if (contentLsAll.get(i).getId().equals(list.get(j).getId())) {
								newidLs.add(contentLsAll.get(i).getId());
							}
						}
					}
				}
				idLs.removeAll(newidLs);
				if (idLs != null && idLs.size() > 0) {
					for (int i = 0; i < idLs.size(); i++) {
						dictionaryContentHandle.delById(idLs.get(i));
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null && StringUtils.isNotBlank(list.get(i).getContent())) {
					// 如果id不为空，更新,否则新增
					if (StringUtils.isNotBlank(list.get(i).getId())) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", list.get(i).getId());
						map.put("content", list.get(i).getContent());
						map.put("sort", list.get(i).getSort());
						dictionaryContentHandle.update(map);
					} else {
						DictionaryContent content = new DictionaryContent();
						content.setId(UUIDUtil.getUUID());
						content.setDictionaryId(sc.getId());
						content.setContent(list.get(i).getContent());
						content.setSort(list.get(i).getSort());
						dictionaryContentHandle.insert(content);
					}
				}

			}
		}

		return dictionaryHandle.update(sc);
	}

	public List<DictionaryContent> selectBydictionaryId(String dictionaryId) {
		return dictionaryContentHandle.selectBydictionaryId(dictionaryId);
	}

}
