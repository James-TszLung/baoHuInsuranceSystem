package com.xiaobaozi.dataSystem.commons.exception.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.exception.handle.ExceptionHandle;
import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.service.ExceptionService;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;
import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;

public class ExceptionServiceImpl extends GenericServiceImpl<ExceptionVO> implements ExceptionService {

	private ExceptionHandle exceptionHandle;
	
	public void setExceptionHandle(ExceptionHandle exceptionHandle) {
		this.exceptionHandle = exceptionHandle;
	}

	@Override
	protected void initService() {
		handle = exceptionHandle;		
	}

	@Override
	public List<ExceptionVO> getListByPage(ExceptionSearchCriteria dsc) {
		return exceptionHandle.getListByPage(dsc);
	}

	@Override
	public Integer getCount(ExceptionSearchCriteria dsc) {
		return exceptionHandle.getCount(dsc);
	}

	@Override
	public Map mulitedelete(String ids) {
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer succf = new StringBuffer();
		StringBuffer failf = new StringBuffer();
		
		if(StringUtils.isNotEmpty(ids)){
			String[] idarr = ids.split(",");
			for(String id : idarr){
				int cid  = Integer.valueOf(id);
				if(cid>0){
					ExceptionVO exc = new ExceptionVO();
					exc.setId(cid);
					try {
						this.delete(exc);
						succf.append(cid);
						succf.append(",");
					} catch (DaoException e) {
//						e.printStackTrace();
						failf.append(cid);
						failf.append(",");
					}
				}else{
					failf.append(id);
					failf.append(",");
				}
			}
		}
		
		if(succf.length()>1){
			map.put("succf", succf.substring(0, succf.length()-1));
		}
		
		if(failf.length()>1){
			map.put("failf", failf.substring(0, failf.length()-1));
		}
		
		return map;
	}
	

}
