package com.xiaobaozi.dataSystem.essay.service;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface PushEssayService extends GenericService<PushEssay> {
	public int getPushEssayCount(PushEssaySearch sc);

	public List<PushEssay> getPushEssayByPage(PushEssaySearch sc);
	
	public int insertPushEssay(PushEssay sc) throws Exception;
	
	public boolean updatePushEssay(PushEssay sc) throws Exception;
	
	public boolean deletePushEssayById(PushEssay sc)throws Exception;
	
}
