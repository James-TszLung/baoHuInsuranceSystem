package com.xiaobaozi.dataSystem.essay.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.essay.dao.EssayLeavingMsgDao;
import com.xiaobaozi.dataSystem.essay.handle.EssayLeavingMsgHandle;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;

@Component("essayLeavingMsgHandle")
public class EssayLeavingMsgHandleImpl extends GenericHandleImpl<EssayLeavingMsg> implements EssayLeavingMsgHandle {

	@Resource(name = "essayLeavingMsgDao")
	private EssayLeavingMsgDao essayLeavingMsgDao;

	@Override
	protected void initHandle() {
		dao = essayLeavingMsgDao;
	}

	@Override
	public int getEssayLeavingMsgCount(EssayLeavingMsgSearch sc) {
		return essayLeavingMsgDao.getEssayLeavingMsgCount(sc);
	}

	@Override
	public List<EssayLeavingMsg> getEssayLeavingMsgByPage(EssayLeavingMsgSearch sc) {
		return essayLeavingMsgDao.getEssayLeavingMsgByPage(sc);
	}

	


}
