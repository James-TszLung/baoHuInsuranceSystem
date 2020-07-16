package com.xiaobaozi.dataSystem.record.service;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;

import java.util.List;

public interface BrowseRecordService extends GenericService<BrowseRecord> {

    public int getBrowseRecordCount(BrowseRecordSearch sc);

    public List<BrowseRecord> getBrowseRecordByPage(BrowseRecordSearch sc);

}
