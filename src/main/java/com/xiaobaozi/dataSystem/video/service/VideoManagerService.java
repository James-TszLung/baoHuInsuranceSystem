package com.xiaobaozi.dataSystem.video.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;

public interface VideoManagerService extends GenericService<VideoManager> {

	public int getVideoManagerCount(VideoManagerSearch sc);

	public List<VideoManager> getVideoManagerByPage(VideoManagerSearch sc);

	public int insertRelationVideo(VideoRelationDictionary sc);

	public int deleById(String id) throws Exception;
	
	public int insertVideoManager(VideoManager sc) throws Exception;
  
	public boolean updateVideoManager(VideoManager sc) throws Exception;
}
