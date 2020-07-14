package com.xiaobaozi.dataSystem.systemManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.systemManager.handle.PictureInfoHandler;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;
import com.xiaobaozi.dataSystem.systemManager.service.PictureInfoService;

/**
 * 系统设置维护
 * 
 * 
 */
@Component("pictureInfoService")
public class PictureInfoServiceImpl extends GenericServiceImpl<PictureInfo> implements PictureInfoService {

	@Resource(name="pictureInfoHandler")
	private PictureInfoHandler pictureInfoHandler;

	@Override
	protected void initService() {
		handle = pictureInfoHandler;
	}

	@Override
	public void batchdeletePic(List<String> picLs) {
		
		pictureInfoHandler.batchdeletePic(picLs);
	}

	@Override
	public List<PictureInfo> findByIds(String[] array) {
		return pictureInfoHandler.findByIds(array);
	}

}
