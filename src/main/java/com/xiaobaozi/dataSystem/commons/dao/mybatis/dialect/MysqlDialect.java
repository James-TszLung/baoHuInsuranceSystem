package com.xiaobaozi.dataSystem.commons.dao.mybatis.dialect;

import com.xiaobaozi.dataSystem.commons.dao.dialect.Dialect;

public class MysqlDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		sql = sql.trim();  
        StringBuffer pagingSelect = new StringBuffer(sql.length() +  100 );
        pagingSelect.append(sql);
        pagingSelect.append("  limit "+skipResults+" ,"+ maxResults);
        
        return pagingSelect.toString();
	}

}
