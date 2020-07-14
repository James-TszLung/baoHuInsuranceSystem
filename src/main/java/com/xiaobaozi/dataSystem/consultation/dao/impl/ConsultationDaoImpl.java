package com.xiaobaozi.dataSystem.consultation.dao.impl;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.consultation.dao.ConsultationReplyDao;
import com.xiaobaozi.dataSystem.consultation.pojo.ConsultationReply;
import com.xiaobaozi.dataSystem.consultation.pojo.ReplyRelationProduct;

@Component("consultationReplyDao")
public class ConsultationDaoImpl extends GenericDaoImpl<ConsultationReply> implements ConsultationReplyDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int insertReplyRelationProduct(ReplyRelationProduct sc) throws Exception {
		return this.insertByMap("insertReplyRelationProduct", sc);
	}

}
