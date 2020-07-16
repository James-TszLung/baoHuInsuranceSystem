package com.xiaobaozi.dataSystem.record.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;

import java.util.List;

public interface BrowseRecordDao extends GenericDao<BrowseRecord> {

    public int getBrowseRecordCount(BrowseRecordSearch sc);

    public List<BrowseRecord> getBrowseRecordByPage(BrowseRecordSearch sc);

}
