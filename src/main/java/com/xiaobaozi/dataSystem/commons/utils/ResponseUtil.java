package com.xiaobaozi.dataSystem.commons.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

import com.xiaobaozi.dataSystem.commons.JsonDateValueProcessor;
import com.xiaobaozi.dataSystem.commons.exception.search.ExceptionSearchCriteria;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;
import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

public class ResponseUtil {

	protected final Logger logger = Logger.getLogger(getClass());
	
	private ResponseUtil() {
	
	}
	
	/**
	 * 组装页面表格的map数据，供后面转json格式数据页面插件使用
	 * @param list
	 * @param sc
	 * @return
	 */
	public static Map getTableMap(List list, Integer pageSize, Integer totalCount){
		Map map = new HashMap();
		map.put("total", totalCount);
		map.put("pageSize", pageSize);
		map.put("rows", list);
		return map;
	}

	/**
	 * 组装页面表格的map数据，供后面转json格式数据页面插件使用
	 * @param list
	 * @param sc
	 * @return
	 */
	public static Map getTableMap(List list, Object sc) {
		SearchCriteria s = (SearchCriteria)sc;
		Map map = new HashMap();
		map.put("total", s.getTotalCount());
		map.put("pageSize", s.getPageSize());
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 将对象数据转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String convertObjectToJson(Object obj){
		return JsonUtil.convertObjectToJson(obj);
	}
	
    
}
