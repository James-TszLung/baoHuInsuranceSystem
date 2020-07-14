package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;
/**
 * 条款附件关联产品实类
 * @author media
 *
 */
public class ClauseFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6227694230160439189L;
	private String productId; //产品id
	private String fileName; //文件名称
	private String type; //文件类型
	private String  fileAddress; //文件地址
	private String fileId;//关联xbz_pictureinfo
	private int sort;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
