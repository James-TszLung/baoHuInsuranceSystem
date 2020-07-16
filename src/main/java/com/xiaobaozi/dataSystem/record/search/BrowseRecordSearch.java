package com.xiaobaozi.dataSystem.record.search;


import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import java.util.Date;

/**
 * 浏览记录
 * 
 * @author media
 * 
 */
public class BrowseRecordSearch extends SearchCriteria {

	private String openid;
	private int type; // 1 浏览产品 2：浏览文章
	private String content;
	private String productId;
	private String pushEssayId; // 推文id
	private String videoId; // 视频id
	private Date createTime; // 創建時間
	private int channel; // 渠道 1： 小程序 2：網站  3：视频
	private Date startTime;
	private Date endTime;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPushEssayId() {
		return pushEssayId;
	}

	public void setPushEssayId(String pushEssayId) {
		this.pushEssayId = pushEssayId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
