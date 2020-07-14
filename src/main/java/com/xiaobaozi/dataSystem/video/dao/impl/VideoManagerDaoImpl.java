package com.xiaobaozi.dataSystem.video.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.video.dao.VideoManagerDao;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;

@Component("videoManagerDao")
public class VideoManagerDaoImpl extends GenericDaoImpl<VideoManager> implements VideoManagerDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getVideoManagerCount(VideoManagerSearch sc) {
		return (Integer) this.selectOne("countVideoManager", sc);
	}

	@Override
	public List<VideoManager> getVideoManagerByPage(VideoManagerSearch sc) {
		return selectList("getListByPage", sc);
	}

	@Override
	public int insertRelationVideo(VideoRelationDictionary sc) {
		return this.insertByMap("insertRelationVideo", sc);
	}

	@Override
	public int deleById(String id) throws Exception {
		return this.deleteByMap("deleById", id);
	}

	@Override
	public int deleteRelationVideoId(String id) throws Exception {
		return this.deleteByMap("deleteRelationVideoId", id);
	}

}
