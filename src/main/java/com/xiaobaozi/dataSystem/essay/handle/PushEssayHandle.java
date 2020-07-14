package com.xiaobaozi.dataSystem.essay.handle;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.pojo.PushProduct;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;

public interface PushEssayHandle extends GenericHandle<PushEssay> {

	public int getPushEssayCount(PushEssaySearch sc);

	public List<PushEssay> getPushEssayByPage(PushEssaySearch sc);
	
	public int insertRelationEssay(EssayRelationDictionary sc) throws Exception;
	
	public List<EssayRelationDictionary> selectRelationByEssayId(String essayId);
	
	public  int deleteRelationByEssayId(String essayId) throws Exception;

	public int insertPushProduct(PushProduct sc) throws Exception;

	public  int deletePushProductByEssayId(String pushEssayId) throws Exception;

}
