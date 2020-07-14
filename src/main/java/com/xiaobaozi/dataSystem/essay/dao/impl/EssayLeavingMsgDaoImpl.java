package com.xiaobaozi.dataSystem.essay.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.essay.dao.EssayLeavingMsgDao;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;

@Component("essayLeavingMsgDao")
public class EssayLeavingMsgDaoImpl extends GenericDaoImpl<EssayLeavingMsg> implements EssayLeavingMsgDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getEssayLeavingMsgCount(EssayLeavingMsgSearch sc) {
		return (Integer) this.selectOne("countEssayLeavingMsg", sc);
	}

	@Override
	public List<EssayLeavingMsg> getEssayLeavingMsgByPage(EssayLeavingMsgSearch sc) {
		return selectList("getListByPage", sc);
	}


}
