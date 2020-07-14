package com.xiaobaozi.dataSystem.company.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface InsuranceCompanyHandle extends GenericHandle<InsuranceCompany> {
	public int getInsuranceCompanyCount(InsuranceCompanySearch sc);

	public List<InsuranceCompany> getInsuranceCompanyByPage(InsuranceCompanySearch sc);

}
