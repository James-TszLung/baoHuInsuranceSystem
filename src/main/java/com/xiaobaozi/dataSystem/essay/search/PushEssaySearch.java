package com.xiaobaozi.dataSystem.essay.search;

import java.util.Date;
import java.util.List;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

/**
 * 推文
 * @author media
 *
 */
public class PushEssaySearch extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7529989877471336949L;
	private String id; 
	private String title; //标题
	private String introduction; //内容简介
	private String linkAddress; //链接地址
	private String typeDictionaryId; //类型 （数字字典）
	private String covermapId; // 封面图id
	private int homePageXcxflag; //是否推送小程序首页  1:是 2：否
	private int columnXcxflag; //是否推送小程序栏目精选  1:是 2：否
	private int websiteflag;//是否推送网站首页 1:是 2：否
	private String activityImage1; //小程序首页图id
	private String activityImage2; //栏目精选图
	private String activityImage3; //网站首页图id
	private int WebsiteHits; //网站点击量
	private int xcxHits; //小程序点击 量
	private Date createTime; //創建時間
	private String sort; //标题
	private int pushFlag;
	
	private  List<String> singleServiceIds;  //id集和
	
	
	
	public List<String> getSingleServiceIds() {
		return singleServiceIds;
	}
	public void setSingleServiceIds(List<String> singleServiceIds) {
		this.singleServiceIds = singleServiceIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getTypeDictionaryId() {
		return typeDictionaryId;
	}
	public void setTypeDictionaryId(String typeDictionaryId) {
		this.typeDictionaryId = typeDictionaryId;
	}
	public String getCovermapId() {
		return covermapId;
	}
	public void setCovermapId(String covermapId) {
		this.covermapId = covermapId;
	}
	public int getHomePageXcxflag() {
		return homePageXcxflag;
	}
	public void setHomePageXcxflag(int homePageXcxflag) {
		this.homePageXcxflag = homePageXcxflag;
	}
	public int getColumnXcxflag() {
		return columnXcxflag;
	}
	public void setColumnXcxflag(int columnXcxflag) {
		this.columnXcxflag = columnXcxflag;
	}
	public int getWebsiteflag() {
		return websiteflag;
	}
	public void setWebsiteflag(int websiteflag) {
		this.websiteflag = websiteflag;
	}
	
	public String getActivityImage1() {
		return activityImage1;
	}
	public void setActivityImage1(String activityImage1) {
		this.activityImage1 = activityImage1;
	}
	public String getActivityImage2() {
		return activityImage2;
	}
	public void setActivityImage2(String activityImage2) {
		this.activityImage2 = activityImage2;
	}
	public String getActivityImage3() {
		return activityImage3;
	}
	public void setActivityImage3(String activityImage3) {
		this.activityImage3 = activityImage3;
	}
	public int getWebsiteHits() {
		return WebsiteHits;
	}
	public void setWebsiteHits(int websiteHits) {
		WebsiteHits = websiteHits;
	}
	public int getXcxHits() {
		return xcxHits;
	}
	public void setXcxHits(int xcxHits) {
		this.xcxHits = xcxHits;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPushFlag() {
		return pushFlag;
	}

	public void setPushFlag(int pushFlag) {
		this.pushFlag = pushFlag;
	}
}
