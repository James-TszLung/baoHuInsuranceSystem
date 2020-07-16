package com.xiaobaozi.dataSystem.record.dao.impl;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.record.dao.BrowseRecordDao;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("browseRecordDao")
public class ProductLabelDaoImpl extends GenericDaoImpl<BrowseRecord> implements BrowseRecordDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	public int getBrowseRecordCount(BrowseRecordSearch sc) {
		return (Integer) this.selectOne("countBrowseRecord", sc);
	}

	public List<BrowseRecord> getBrowseRecordByPage(BrowseRecordSearch sc) {
		return selectList("getListByPage", sc);
	}

}
