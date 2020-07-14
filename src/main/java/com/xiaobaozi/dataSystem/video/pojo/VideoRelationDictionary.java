package com.xiaobaozi.dataSystem.video.pojo;

import java.io.Serializable;

/**
 * 知识库关联推文
 * 
 * @author media
 * 
 */
public class VideoRelationDictionary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5379517566086556849L;
	private String videoManagerId; // 视频管理的id
	private String dictionaryId; // 数字字典id
	
	private String content;
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideoManagerId() {
		return videoManagerId;
	}

	public void setVideoManagerId(String videoManagerId) {
		this.videoManagerId = videoManagerId;
	}

	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

}
