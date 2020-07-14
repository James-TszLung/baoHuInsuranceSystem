package com.xiaobaozi.dataSystem.feedback.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言反馈
 * 
 * @author media
 * 
 */
public class Feedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 937624505118475163L;

	private String id;
	private String userId; //用戶id
	private String content;// 反馈内容
	private String contactWay; // 联系方式
	private int status; //状态：  1：待回复  2：已回复
	private String pictureId; //图片id
	private Date createTime; //提交时间
	private String reply;// 回复内容
	private Date replyTime; //回复时间
	
	//列表显示，数据库不用添加
	private int pictureNum; //图片数
	
	
	public int getPictureNum() {
		return pictureNum;
	}
	public void setPictureNum(int pictureNum) {
		this.pictureNum = pictureNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	
	
	
	
	
	
	

}
