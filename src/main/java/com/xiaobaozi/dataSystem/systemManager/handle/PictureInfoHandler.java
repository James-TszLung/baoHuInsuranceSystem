package com.xiaobaozi.dataSystem.systemManager.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;

public interface PictureInfoHandler extends GenericHandle<PictureInfo> {

	//批量删除图片，根据图片id
	public void batchdeletePic(List<String> picLs);
	//根据数组查询多个对象
	public List<PictureInfo> findByIds(String[] array);
}
