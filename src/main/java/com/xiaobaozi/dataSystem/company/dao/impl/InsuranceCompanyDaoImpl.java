package com.xiaobaozi.dataSystem.company.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.dao.mybatis.impl.GenericDaoImpl;
import com.xiaobaozi.dataSystem.company.dao.InsuranceCompanyDao;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;

@Component("insuranceCompanyDao")
public class InsuranceCompanyDaoImpl extends GenericDaoImpl<InsuranceCompany> implements InsuranceCompanyDao {

	protected void initDao() {
		this.jdbcTemplate = super.getBaseinfojdbcTemplate();
		this.sqlSessionTemplate = super.getBaseinfosqlSessionTemplate();
	}

	@Override
	public int getInsuranceCompanyCount(InsuranceCompanySearch sc) {
		return (Integer) this.selectOne("countInsuranceCompany", sc);
	}

	@Override
	public List<InsuranceCompany> getInsuranceCompanyByPage(InsuranceCompanySearch sc) {
		return selectList("getListByPage", sc);
	}

}
