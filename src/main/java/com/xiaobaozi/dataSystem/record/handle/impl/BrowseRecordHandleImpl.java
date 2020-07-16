package com.xiaobaozi.dataSystem.record.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.record.dao.BrowseRecordDao;
import com.xiaobaozi.dataSystem.record.handle.BrowseRecordHandle;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("browseRecordHandle")
public class BrowseRecordHandleImpl extends GenericHandleImpl<BrowseRecord> implements BrowseRecordHandle {

	@Resource(name = "browseRecordDao")
	private BrowseRecordDao browseRecordDao;

	@Override
	protected void initHandle() {
		dao = browseRecordDao;
	}


	public int getBrowseRecordCount(BrowseRecordSearch sc) {
		return browseRecordDao.getBrowseRecordCount(sc);
	}

	public List<BrowseRecord> getBrowseRecordByPage(BrowseRecordSearch sc) {
		return browseRecordDao.getBrowseRecordByPage(sc);
	}
}
