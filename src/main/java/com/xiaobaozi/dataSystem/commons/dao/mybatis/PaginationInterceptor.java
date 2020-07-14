package com.xiaobaozi.dataSystem.commons.dao.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaobaozi.dataSystem.commons.dao.dialect.Dialect;
import com.xiaobaozi.dataSystem.commons.dao.mybatis.dialect.MysqlDialect;
import com.xiaobaozi.dataSystem.commons.dao.mybatis.dialect.OracleDialect;
import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;


@Intercepts({ @Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
public class PaginationInterceptor implements Interceptor {

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	protected static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		BoundSql boundSql2 = statementHandler.getBoundSql();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		String pageSqlId = configuration.getVariables().getProperty("pageSqlId"); 
		if(null==pageSqlId || "".equals(pageSqlId)){
			return invocation.proceed();
		}
		MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
		
		if(mappedStatement.getId().matches(pageSqlId)){
			SearchCriteria  sc = (SearchCriteria) boundSql2.getParameterObject();		
			if (sc == null || !sc.isFlag()) {
				return invocation.proceed();
			}
			RowBounds rowBounds = new RowBounds((sc.getPageNo()-1)*sc.getPageSize(),sc.getPageSize());
			String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
			
			Dialect.Type databaseType = null;
			try {
				databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
			} catch (Exception e) {
				log.error("mybatis-config.xml中未设置数据库类型");
			}
			if (databaseType == null) {
				throw new RuntimeException(
						"the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
			}
			Dialect dialect = null;
			switch (databaseType) {
				case ORACLE: // oracle 分页
					dialect = new OracleDialect();
					break;
				case MYSQL: // MySQL分页
					dialect = new MysqlDialect();
					break;
			}
			metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
			metaStatementHandler.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);
			metaStatementHandler.setValue("delegate.rowBounds.limit",RowBounds.NO_ROW_LIMIT);
			if (log.isDebugEnabled()) {
				BoundSql boundSql = statementHandler.getBoundSql();
				log.debug(" 生成分页SQL : " + boundSql.getSql());
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		 return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}
