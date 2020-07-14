package com.xiaobaozi.dataSystem.video.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;

public interface VideoManagerHandle extends GenericHandle<VideoManager> {

	public int getVideoManagerCount(VideoManagerSearch sc);

	public List<VideoManager> getVideoManagerByPage(VideoManagerSearch sc);

	public int insertRelationVideo(VideoRelationDictionary sc);

	public int deleById(String id) throws Exception;
	
	public int deleteRelationVideoId (String id) throws Exception;
}
