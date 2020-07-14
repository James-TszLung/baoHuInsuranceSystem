package com.xiaobaozi.dataSystem.systemManager.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片信息
 * 
 * @author fengya
 * 
 */
public class PictureInfo implements Serializable {

	
	private static final long serialVersionUID = 6267546174934766754L;
	private String id;// id
	private String pictureName;// 图片标题
	private String productDir;// 图片完整路径
	private int sort;// 排序
	private String url;// 图片点击跳转地址
	private String remark;// 备注
	private Date updateTime;// 修改时间
	private Date createTime;// 创建时间
	private String name;  //图片名称
	private String fileType;//文件类型
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getProductDir() {
		return productDir;
	}
	public void setProductDir(String productDir) {
		this.productDir = productDir;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
