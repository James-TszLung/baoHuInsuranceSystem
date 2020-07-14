package com.xiaobaozi.dataSystem.essay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.essay.handle.EssayLeavingMsgHandle;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;
import com.xiaobaozi.dataSystem.essay.service.EssayLeavingMsgService;

@Component("essayLeavingMsgService")
public class EssayLeavingMsgServiceImpl extends GenericServiceImpl<EssayLeavingMsg> implements EssayLeavingMsgService {

	@Resource(name = "essayLeavingMsgHandle")
	private EssayLeavingMsgHandle essayLeavingMsgHandle;

	@Override
	protected void initService() {
		handle = essayLeavingMsgHandle;
	}

	@Override
	public int getEssayLeavingMsgCount(EssayLeavingMsgSearch sc) {
		return essayLeavingMsgHandle.getEssayLeavingMsgCount(sc);
	}

	@Override
	public List<EssayLeavingMsg> getEssayLeavingMsgByPage(EssayLeavingMsgSearch sc) {
		return essayLeavingMsgHandle.getEssayLeavingMsgByPage(sc);
	}



}
