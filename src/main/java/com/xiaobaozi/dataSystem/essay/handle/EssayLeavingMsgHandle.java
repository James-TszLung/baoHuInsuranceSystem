package com.xiaobaozi.dataSystem.essay.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;

public interface EssayLeavingMsgHandle extends GenericHandle<EssayLeavingMsg> {

	public int getEssayLeavingMsgCount(EssayLeavingMsgSearch sc);

	public List<EssayLeavingMsg> getEssayLeavingMsgByPage(EssayLeavingMsgSearch sc);

}
