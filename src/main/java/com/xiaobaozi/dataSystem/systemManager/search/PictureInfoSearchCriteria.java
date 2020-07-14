package com.xiaobaozi.dataSystem.systemManager.search;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;

/**
 * 店铺信息查询条件
 * 
 * @author fengya
 * 
 */
public class PictureInfoSearchCriteria extends SearchCriteria<PictureInfo> {

	private static final long serialVersionUID = 7933368605087316168L;
	private int id;
	private int firstPage; // 分页条件
	private int endPage;// 分页条件
	private String pictureName; // 图片名称
	private String productDir; // 图片路径
	private String remark; // 备注
	private int type; // 图片类型 1：兑币机盘点图片2：租金押金单 3：报销单 4：场地图片 5：其它图片 6：产品
	private int productId; // 关联外键
	private String areaName;
	private String areaId;
	private String[] areaGroup;

	public String[] getAreaGroup() {
		return areaGroup;
	}

	public void setAreaGroup(String[] areaGroup) {
		this.areaGroup = areaGroup;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}
