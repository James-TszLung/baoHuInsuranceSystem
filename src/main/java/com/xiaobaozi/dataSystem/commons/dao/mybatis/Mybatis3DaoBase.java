package com.xiaobaozi.dataSystem.commons.dao.mybatis;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Mybatis3DaoBase {
	/**
	 * baseinfo database session
	 */
	@Resource
	private SqlSessionTemplate baseinfosqlSessionTemplate;
	@Resource
	private JdbcTemplate baseinfojdbcTemplate;
	/**
	 * otherpro datasource session
	 *//*
	private SqlSessionTemplate otherprosqlSessionTemplate;
	private JdbcTemplate otherprojdbcTemplate;
	*//**
	 * policy datasource session
	 *//*
	private SqlSessionTemplate policysqlSessionTemplate;
	private JdbcTemplate policyjdbcTemplate;
	*//**
	 * air datasource session
	 *//*
	private SqlSessionTemplate airsqlSessionTemplate;
	private JdbcTemplate airjdbcTemplate;
	
	*//**
	 * loginfo datasource session
	 *//*
	private SqlSessionTemplate loginfosqlSessionTemplate;
	private JdbcTemplate loginfojdbcTemplate;
	
	*//**
	 * orcl datasource session
	 *//*
//	private SqlSessionTemplate orclsqlSessionTemplate;
//	private JdbcTemplate orcljdbcTemplate;
	
//	public SqlSessionTemplate getOrclsqlSessionTemplate() {
//		return orclsqlSessionTemplate;
//	}
//	public void setOrclsqlSessionTemplate(SqlSessionTemplate orclsqlSessionTemplate) {
//		this.orclsqlSessionTemplate = orclsqlSessionTemplate;
//	}
//	public JdbcTemplate getOrcljdbcTemplate() {
//		return orcljdbcTemplate;
//	}
//	public void setOrcljdbcTemplate(JdbcTemplate orcljdbcTemplate) {
//		this.orcljdbcTemplate = orcljdbcTemplate;
//	}
	public SqlSessionTemplate getBaseinfosqlSessionTemplate() {
		return baseinfosqlSessionTemplate;
	}
	public void setBaseinfosqlSessionTemplate(
			SqlSessionTemplate baseinfosqlSessionTemplate) {
		this.baseinfosqlSessionTemplate = baseinfosqlSessionTemplate;
	}
	public JdbcTemplate getBaseinfojdbcTemplate() {
		return baseinfojdbcTemplate;
	}
	public void setBaseinfojdbcTemplate(JdbcTemplate baseinfojdbcTemplate) {
		this.baseinfojdbcTemplate = baseinfojdbcTemplate;
	}
	public SqlSessionTemplate getOtherprosqlSessionTemplate() {
		return otherprosqlSessionTemplate;
	}
	public void setOtherprosqlSessionTemplate(
			SqlSessionTemplate otherprosqlSessionTemplate) {
		this.otherprosqlSessionTemplate = otherprosqlSessionTemplate;
	}
	public JdbcTemplate getOtherprojdbcTemplate() {
		return otherprojdbcTemplate;
	}
	public void setOtherprojdbcTemplate(JdbcTemplate otherprojdbcTemplate) {
		this.otherprojdbcTemplate = otherprojdbcTemplate;
	}
	public SqlSessionTemplate getPolicysqlSessionTemplate() {
		return policysqlSessionTemplate;
	}
	public void setPolicysqlSessionTemplate(SqlSessionTemplate policysqlSessionTemplate) {
		this.policysqlSessionTemplate = policysqlSessionTemplate;
	}
	public JdbcTemplate getPolicyjdbcTemplate() {
		return policyjdbcTemplate;
	}
	public void setPolicyjdbcTemplate(JdbcTemplate policyjdbcTemplate) {
		this.policyjdbcTemplate = policyjdbcTemplate;
	}
	public SqlSessionTemplate getLoginfosqlSessionTemplate() {
		return loginfosqlSessionTemplate;
	}
	public void setLoginfosqlSessionTemplate(
			SqlSessionTemplate loginfosqlSessionTemplate) {
		this.loginfosqlSessionTemplate = loginfosqlSessionTemplate;
	}
	public JdbcTemplate getLoginfojdbcTemplate() {
		return loginfojdbcTemplate;
	}
	public void setLoginfojdbcTemplate(JdbcTemplate loginfojdbcTemplate) {
		this.loginfojdbcTemplate = loginfojdbcTemplate;
	}
	public SqlSessionTemplate getAirsqlSessionTemplate() {
		return airsqlSessionTemplate;
	}
	public void setAirsqlSessionTemplate(SqlSessionTemplate airsqlSessionTemplate) {
		this.airsqlSessionTemplate = airsqlSessionTemplate;
	}
	public JdbcTemplate getAirjdbcTemplate() {
		return airjdbcTemplate;
	}
	public void setAirjdbcTemplate(JdbcTemplate airjdbcTemplate) {
		this.airjdbcTemplate = airjdbcTemplate;
	}*/
	public SqlSessionTemplate getBaseinfosqlSessionTemplate() {
		return baseinfosqlSessionTemplate;
	}
	public void setBaseinfosqlSessionTemplate(SqlSessionTemplate baseinfosqlSessionTemplate) {
		this.baseinfosqlSessionTemplate = baseinfosqlSessionTemplate;
	}
	public JdbcTemplate getBaseinfojdbcTemplate() {
		return baseinfojdbcTemplate;
	}
	public void setBaseinfojdbcTemplate(JdbcTemplate baseinfojdbcTemplate) {
		this.baseinfojdbcTemplate = baseinfojdbcTemplate;
	}
	
}
