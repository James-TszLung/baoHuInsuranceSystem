package com.xiaobaozi.dataSystem.systemManager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.systemManager.dao.PictureInfoDao;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;


@Component("pictureInfoDao")
public class PictureDaoImpl extends GenericDaoImpl<PictureInfo> implements PictureInfoDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public void batchdeletePic(List<String> list) 
	{
		this.deleteByMap("batchdeletePic", list);
	}
	@Override
	public List<PictureInfo> findByIds(String[] array) {
		return this.selectList("findByIds", array);
	}
	


}
