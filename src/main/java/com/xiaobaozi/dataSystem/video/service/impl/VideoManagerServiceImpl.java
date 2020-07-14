package com.xiaobaozi.dataSystem.video.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.video.handle.VideoManagerHandle;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;
import com.xiaobaozi.dataSystem.video.service.VideoManagerService;

@Component("videoManagerService")
public class VideoManagerServiceImpl extends GenericServiceImpl<VideoManager> implements VideoManagerService {

	@Resource(name = "videoManagerHandle")
	private VideoManagerHandle videoManagerHandle;

	@Override
	protected void initService() {
		handle = videoManagerHandle;
	}

	@Override
	public int getVideoManagerCount(VideoManagerSearch sc) {
		return videoManagerHandle.getVideoManagerCount(sc);
	}

	@Override
	public List<VideoManager> getVideoManagerByPage(VideoManagerSearch sc) {
		return videoManagerHandle.getVideoManagerByPage(sc);
	}

	@Override
	public int deleById(String id) throws Exception {
		videoManagerHandle.deleteRelationVideoId(id);
		return videoManagerHandle.deleById(id);
	}

	@Override
	public int insertVideoManager(VideoManager sc) throws Exception {
		sc.setId(UUIDUtil.getUUID());
		if (sc.getVideoRelationDictionaryLs() != null && sc.getVideoRelationDictionaryLs().size() > 0) {
			List<VideoRelationDictionary> relationLs = sc.getVideoRelationDictionaryLs();
			for (int i = 0; i < relationLs.size(); i++) {
				relationLs.get(i).setVideoManagerId(sc.getId());
				videoManagerHandle.insertRelationVideo(relationLs.get(i));
			}
		}
		return videoManagerHandle.insert(sc);
	}

	@Override
	public boolean updateVideoManager(VideoManager sc) throws Exception {
		videoManagerHandle.deleteRelationVideoId(sc.getId());
		if (sc.getVideoRelationDictionaryLs() != null && sc.getVideoRelationDictionaryLs().size() > 0) {
			List<VideoRelationDictionary> relationLs = sc.getVideoRelationDictionaryLs();
			for (int i = 0; i < relationLs.size(); i++) {
				relationLs.get(i).setVideoManagerId(sc.getId());
				videoManagerHandle.insertRelationVideo(relationLs.get(i));
			}
		}
		return videoManagerHandle.update(sc);
	}

	@Override
	public int insertRelationVideo(VideoRelationDictionary sc) {
		return videoManagerHandle.insertRelationVideo(sc);
	}

}
