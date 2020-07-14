package com.xiaobaozi.dataSystem.systemManager.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;

/**
 * 系统设置维护
 * 
 */
public interface PictureInfoService extends GenericService<PictureInfo> {

	// 批量删除图片，根据图片id
	public void batchdeletePic(List<String> picLs);

	// 根据数组查询多个对象
	public List<PictureInfo> findByIds(String[] array);
}
