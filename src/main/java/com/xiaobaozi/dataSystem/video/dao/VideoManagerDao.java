package com.xiaobaozi.dataSystem.video.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;

public interface VideoManagerDao extends GenericDao<VideoManager> {

	public int getVideoManagerCount(VideoManagerSearch sc);

	public List<VideoManager> getVideoManagerByPage(VideoManagerSearch sc);

	public int insertRelationVideo(VideoRelationDictionary sc);

	public int deleById(String id) throws Exception;
	
	public int deleteRelationVideoId (String id) throws Exception;

}
