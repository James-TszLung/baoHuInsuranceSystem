package com.xiaobaozi.dataSystem.company.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface InsuranceCompanyService extends GenericService<InsuranceCompany> {
	public int getInsuranceCompanyCount(InsuranceCompanySearch sc);

	public List<InsuranceCompany> getInsuranceCompanyByPage(InsuranceCompanySearch sc);

}
