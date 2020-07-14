package com.xiaobaozi.dataSystem.interfaceCommon.search;

import java.util.Date;
import java.util.List;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.interfaceCommon.pojo.VideoRelationDictionary;

/**
 * 視頻 管理
 * 
 * @author media
 * 
 */
public class VideoManagerSearch extends SearchCriteria  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1828072545568765569L;
	/**
	 * 
	 */
	private String id;
	private String name; // 视频名称
	private String content; // 内容简介
	private String typeDictionaryId; // 类型 （数字字典）
	private String videoId; // 视频id
	private int xcxPlayCount; //小程序播放量
	private int websitePlayCount; //网站播放量
	private Date createTime;
	
	private List<VideoRelationDictionary> videoRelationDictionaryLs;
	
	
	public List<VideoRelationDictionary> getVideoRelationDictionaryLs() {
		return videoRelationDictionaryLs;
	}
	public void setVideoRelationDictionaryLs(List<VideoRelationDictionary> videoRelationDictionaryLs) {
		this.videoRelationDictionaryLs = videoRelationDictionaryLs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTypeDictionaryId() {
		return typeDictionaryId;
	}
	public void setTypeDictionaryId(String typeDictionaryId) {
		this.typeDictionaryId = typeDictionaryId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}


	public int getXcxPlayCount() {
		return xcxPlayCount;
	}
	public void setXcxPlayCount(int xcxPlayCount) {
		this.xcxPlayCount = xcxPlayCount;
	}
	public int getWebsitePlayCount() {
		return websitePlayCount;
	}
	public void setWebsitePlayCount(int websitePlayCount) {
		this.websitePlayCount = websitePlayCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
