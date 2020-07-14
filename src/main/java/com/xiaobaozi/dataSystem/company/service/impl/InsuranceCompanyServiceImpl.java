package com.xiaobaozi.dataSystem.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.company.handle.InsuranceCompanyHandle;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.company.service.InsuranceCompanyService;

@Component("insuranceCompanyService")
public class InsuranceCompanyServiceImpl extends GenericServiceImpl<InsuranceCompany> implements InsuranceCompanyService {

	@Resource(name = "insuranceCompanyHandle")
	private InsuranceCompanyHandle insuranceCompanyHandle;

	@Override
	protected void initService() {
		handle = insuranceCompanyHandle;
	}

	@Override
	public int getInsuranceCompanyCount(InsuranceCompanySearch sc) {
		return insuranceCompanyHandle.getInsuranceCompanyCount(sc);
	}

	@Override
	public List<InsuranceCompany> getInsuranceCompanyByPage(InsuranceCompanySearch sc) {
		return insuranceCompanyHandle.getInsuranceCompanyByPage(sc);
	}

}
