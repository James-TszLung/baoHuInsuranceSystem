package com.xiaobaozi.dataSystem.company.handle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.company.dao.InsuranceCompanyDao;
import com.xiaobaozi.dataSystem.company.handle.InsuranceCompanyHandle;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;

@Component("insuranceCompanyHandle")
public class InsuranceCompanyHandleImpl extends GenericHandleImpl<InsuranceCompany> implements InsuranceCompanyHandle {

	@Resource(name = "insuranceCompanyDao")
	private InsuranceCompanyDao insuranceCompanyDao;

	@Override
	protected void initHandle() {
		dao = insuranceCompanyDao;
	}

	@Override
	public int getInsuranceCompanyCount(InsuranceCompanySearch sc) {
		return insuranceCompanyDao.getInsuranceCompanyCount(sc);
	}

	@Override
	public List<InsuranceCompany> getInsuranceCompanyByPage(InsuranceCompanySearch sc) {
		return insuranceCompanyDao.getInsuranceCompanyByPage(sc);
	}

}
