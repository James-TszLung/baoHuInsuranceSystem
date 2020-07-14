package com.xiaobaozi.dataSystem.essay.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;

public interface EssayLeavingMsgDao extends GenericDao<EssayLeavingMsg> {

	public int getEssayLeavingMsgCount(EssayLeavingMsgSearch sc);

	public List<EssayLeavingMsg> getEssayLeavingMsgByPage(EssayLeavingMsgSearch sc);
	

}

