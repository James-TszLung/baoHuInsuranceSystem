package com.xiaobaozi.dataSystem.essay.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;

public interface EssayLeavingMsgService extends GenericService<EssayLeavingMsg> {
	public int getEssayLeavingMsgCount(EssayLeavingMsgSearch sc);

	public List<EssayLeavingMsg> getEssayLeavingMsgByPage(EssayLeavingMsgSearch sc);
	
}
