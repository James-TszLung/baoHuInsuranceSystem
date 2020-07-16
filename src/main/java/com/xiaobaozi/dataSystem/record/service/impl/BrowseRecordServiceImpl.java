package com.xiaobaozi.dataSystem.record.service.impl;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.record.handle.BrowseRecordHandle;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;
import com.xiaobaozi.dataSystem.record.service.BrowseRecordService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("browseRecordService")
public class BrowseRecordServiceImpl extends GenericServiceImpl<BrowseRecord> implements BrowseRecordService {

	@Resource(name = "browseRecordHandle")
	private BrowseRecordHandle browseRecordHandle;

	@Override
	protected void initService() {
		handle = browseRecordHandle;
	}


	public int getBrowseRecordCount(BrowseRecordSearch sc) {
		return browseRecordHandle.getBrowseRecordCount(sc);
	}

	public List<BrowseRecord> getBrowseRecordByPage(BrowseRecordSearch sc) {
		return browseRecordHandle.getBrowseRecordByPage(sc);
	}
}
