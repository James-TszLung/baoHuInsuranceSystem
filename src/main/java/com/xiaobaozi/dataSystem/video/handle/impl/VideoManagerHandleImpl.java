 package com.xiaobaozi.dataSystem.video.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.video.dao.VideoManagerDao;
import com.xiaobaozi.dataSystem.video.handle.VideoManagerHandle;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;

@Component("videoManagerHandle")
public class VideoManagerHandleImpl extends GenericHandleImpl<VideoManager> implements VideoManagerHandle {

	@Resource(name = "videoManagerDao")
	private VideoManagerDao videoManagerDao;

	@Override
	protected void initHandle() {
		dao = videoManagerDao;
	}

	@Override
	public int getVideoManagerCount(VideoManagerSearch sc) {
		return videoManagerDao.getVideoManagerCount(sc);
	}

	@Override
	public List<VideoManager> getVideoManagerByPage(VideoManagerSearch sc) {
		return videoManagerDao.getVideoManagerByPage(sc);
	}

	@Override
	public int insertRelationVideo(VideoRelationDictionary sc) {
		return videoManagerDao.insertRelationVideo(sc);
	}

	@Override
	public int deleById(String id) throws Exception {
		return videoManagerDao.deleById(id);
	}

	@Override
	public int deleteRelationVideoId(String id) throws Exception {
		return videoManagerDao.deleteRelationVideoId(id);
	}



}
