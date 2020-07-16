package com.xiaobaozi.dataSystem.record.pojo;

import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;

import java.io.Serializable;
import java.util.Date;

/**
 * 浏览记录
 * 
 * @author media
 * 
 */
public class BrowseRecord implements Serializable {

	private String id;
	private String openid;
	private int type; // 1 浏览产品 2：浏览文章 3:浏览视频
	private String content;
	private String productId;
	private String pushEssayId; // 推文id
	private String videoId; // 视频id
	private Date createTime; // 創建時間
	private int channel; // 渠道 1： 小程序 2：網站
	private Product product;
	private PushEssay pushEssay;
	private VideoManager videoManager;
	private RegisterUser registerUser;

	public String getTypeDesc(){
		return type==1 ? "产品" : type==2 ? "文章" : type==3 ? "视频" : "";
	}

	public String getChannelDesc(){
		return channel==1 ? "小程序" : channel==2 ? "网站" : "";
	}

	public String getContentDesc(){
		String contentDesc = content;
		if(type==1 && product !=null){
			contentDesc = product.getName();
		}else if(type==2 && pushEssay !=null){
			contentDesc = pushEssay.getTitle();
		}else if(type==2 && videoManager !=null){
			contentDesc = videoManager.getName();
		}
		return contentDesc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PushEssay getPushEssay() {
		return pushEssay;
	}

	public void setPushEssay(PushEssay pushEssay) {
		this.pushEssay = pushEssay;
	}

	public VideoManager getVideoManager() {
		return videoManager;
	}

	public void setVideoManager(VideoManager videoManager) {
		this.videoManager = videoManager;
	}

	public RegisterUser getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
}
