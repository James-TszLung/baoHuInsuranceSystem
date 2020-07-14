package com.xiaobaozi.dataSystem.systemManager.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.systemManager.dao.PictureInfoDao;
import com.xiaobaozi.dataSystem.systemManager.handle.PictureInfoHandler;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;

@Component("pictureInfoHandler")
public class PictureInfoHandlerImpl extends GenericHandleImpl<PictureInfo> implements PictureInfoHandler {

	@Resource(name = "pictureInfoDao")
	PictureInfoDao pictureInfoDao;

	@Override
	protected void initHandle() {
		dao = pictureInfoDao;
	}

	@Override
	public void batchdeletePic(List<String> picLs) {
		pictureInfoDao.batchdeletePic(picLs);

	}

	@Override
	public List<PictureInfo> findByIds(String[] array) {
		return pictureInfoDao.findByIds(array);
	}

}
