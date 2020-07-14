package com.xiaobaozi.dataSystem.essay.pojo;

import java.io.Serializable;
import java.util.Date;

import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;

/**
 * 推文的留言
 * 
 * @author media
 * 
 */
public class EssayLeavingMsg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7099219474980826499L;
	private String id;
	private String mediaId; // 推文文章id
	private String mediaName; // 推文文章名称
	private String userId; // 用戶id
	private String content; // 留言內容
	private Date createTime;
	private int praiseNum; // 点赞数
	private String reply; // 回復
	private String replyTime; // 回复时间
	private int status; //状态 1；待審核  2:審核通過 3：審核不通過  4：已回复
	
   	private RegisterUser user;
   	
	
	

	public RegisterUser getUser() {
		return user;
	}

	public void setUser(RegisterUser user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

}
