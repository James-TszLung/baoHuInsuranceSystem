package com.xiaobaozi.dataSystem.essay.dao;

import java.util.List;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.pojo.PushProduct;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;

public interface PushEssayDao extends GenericDao<PushEssay> {

	public int getPushEssayCount(PushEssaySearch sc);

	public List<PushEssay> getPushEssayByPage(PushEssaySearch sc);
	
	public int insertRelationEssay(EssayRelationDictionary sc) throws Exception;
	
	public List<EssayRelationDictionary> selectRelationByEssayId(String essayId);
	
	public  int deleteRelationByEssayId(String essayId) throws Exception;


	public int insertPushProduct(PushProduct sc) throws Exception;

	public  int deletePushProductByEssayId(String pushEssayId) throws Exception;
	

}

