package com.xiaobaozi.dataSystem.company.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;

public interface InsuranceCompanyDao extends GenericDao<InsuranceCompany> {

	public int getInsuranceCompanyCount(InsuranceCompanySearch sc);

	public List<InsuranceCompany> getInsuranceCompanyByPage(InsuranceCompanySearch sc);

}

