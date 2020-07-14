package com.xiaobaozi.dataSystem.essay.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.essay.pojo.PushProduct;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.essay.dao.PushEssayDao;
import com.xiaobaozi.dataSystem.essay.handle.PushEssayHandle;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;

@Component("pushEssayHandle")
public class PushEssayHandleImpl extends GenericHandleImpl<PushEssay> implements PushEssayHandle {

	@Resource(name = "pushEssayDao")
	private PushEssayDao pushEssayDao;

	@Override
	protected void initHandle() {
		dao = pushEssayDao;
	}

	public int getPushEssayCount(PushEssaySearch sc) {
		return pushEssayDao.getPushEssayCount(sc);
	}

	public List<PushEssay> getPushEssayByPage(PushEssaySearch sc) {
		return pushEssayDao.getPushEssayByPage(sc);
	}

	public int insertRelationEssay(EssayRelationDictionary sc) throws Exception {
		return pushEssayDao.insertRelationEssay(sc);
	}

	public List<EssayRelationDictionary> selectRelationByEssayId(String essayId) {
		return pushEssayDao.selectRelationByEssayId(essayId);
	}

	public int deleteRelationByEssayId(String essayId) throws Exception {
		return pushEssayDao.deleteRelationByEssayId(essayId);
	}

	public int insertPushProduct(PushProduct sc) throws Exception {
		return pushEssayDao.insertPushProduct(sc);
	}

	public int deletePushProductByEssayId(String pushEssayId) throws Exception {
		return pushEssayDao.deletePushProductByEssayId(pushEssayId);
	}


}
