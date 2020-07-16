package com.xiaobaozi.dataSystem.record.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;

import java.util.List;

public interface BrowseRecordHandle extends GenericHandle<BrowseRecord> {

    public int getBrowseRecordCount(BrowseRecordSearch sc);

    public List<BrowseRecord> getBrowseRecordByPage(BrowseRecordSearch sc);

}
