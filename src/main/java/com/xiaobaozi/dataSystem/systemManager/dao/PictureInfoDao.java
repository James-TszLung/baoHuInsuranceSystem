package com.xiaobaozi.dataSystem.systemManager.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;

public interface PictureInfoDao extends GenericDao<PictureInfo>{
	
	//批量删除图片，根据图片id
	public void batchdeletePic(List<String> picLs);
	//根据数组查询多个对象
	public List<PictureInfo> findByIds(String[] array);
}