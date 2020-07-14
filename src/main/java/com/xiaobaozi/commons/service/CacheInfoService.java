package com.xiaobaozi.commons.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.xiaobaozi.commons.Constants;
import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.utils.PropertiesUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 用反射机制实现的配置型缓存数据类<br>
 * 通过配置中的设置，以热插拔的形式调用hessian服务缓存数据库数据。<br>
 * 目前只缓存listByMap方式查询的数据，如需其他查询方式，请自行参照现有方法去扩展实现。
 * 此反射调用方法可用在各种数据库操作，此类只供缓存使用，不做其他。
 * @author zhengbh
 *
 */
public class CacheInfoService extends BaseController{

	Logger log = Logger.getLogger(CacheInfoService.class);
	// 缓存-properties文件中的配置
	private static Map<String, String> confMap = new HashMap<String, String>();

	// 通用参数区
	private static final String DATABASE_SERVICE_CLASSNAME_PARAM = ".serclass";
	private static final String DATABASE_SERVICE_METHOD_PARAM = ".methods";
	// listByMap参数区
	private static final String DATABASE_SERVICE_LISTBYMAP_PARAM = ".listparams";
	
	public CacheInfoService(){
	}

	/**
	 * 缓存定时器初始方法
	 */
	public void initCache(){
		log.info("【CacheInfoService】信息缓存初始化-开始！");
		confMap = PropertiesUtil.iterateProp("/conf/properties/cacheconf.properties");
		cacheAllListByMap();
		log.info("【CacheInfoService】信息缓存初始化-结束！");
	}
	
	/**
	 * 缓存所有使用listByMap查询的结果
	 * @param propMap
	 */
	public void cacheAllListByMap(){
		List<String> hasCacheKey = new ArrayList<String>();
		Iterator<Entry<String, String>> it = confMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			if(key.indexOf(DATABASE_SERVICE_METHOD_PARAM) >= 0){
				if(entry.getValue().indexOf("listByMap") >= 0){
					// 需要调用listByMap，开始调用方法
					String voClsName = key.substring(0,key.indexOf(DATABASE_SERVICE_METHOD_PARAM));
					if(!hasCacheKey.contains(voClsName)){
						cacheListByMap(voClsName);
						hasCacheKey.add(voClsName);
					}
				}
			}
		}
	}
	
	/**
	 * 根据调用者提供的class类型匹对配置，
	 * 然后通过反射机制调用listByMap方法查询，
	 * 并按照Map<String, List<Object>>的形式在constants类中缓存结果，
	 * 可通过map.get(vo类全名)的方式获取数据。
	 * @param cls
	 */
	@SuppressWarnings("unchecked")
	public void cacheListByMap(String voClsName){
		String serClassStr = confMap.get(voClsName+DATABASE_SERVICE_CLASSNAME_PARAM);
		String paramsStr = confMap.get(voClsName+DATABASE_SERVICE_LISTBYMAP_PARAM);
		Map<String, String> params = StringUtils.isNotBlank(paramsStr)?(Map<String, String>)JSONObject.fromObject(paramsStr):new HashMap<String, String>();
		try {
			// 获取指定hessian服务bean对象
			Object serviceObj = getApplicationContext("/conf/spring/spring-hessian.xml").getBean(serClassStr);
			// 转换成service对象
			Class<?> service = serviceObj.getClass();
			// 定义方法
			Method method = service.getDeclaredMethod("listByMap", Map.class);
			// 调用方法
			List<Object> rsList = (List<Object>)method.invoke(serviceObj, params);
			Constants.cacheListByMap.put(voClsName, rsList);
		} catch (SecurityException e) {
			log.error(e);
		} catch (NoSuchMethodException e) {
			log.error("【CacheInfoService】没有找到listByMap方法！");
		} catch (IllegalArgumentException e) {
			log.error("【CacheInfoService】listByMap方法调用出错，请检查传递的参数！");
		} catch (IllegalAccessException e) {
			log.error("【CacheInfoService】listByMap方法调用出错，请检查方法是否不可调用！");
		} catch (InvocationTargetException e) {
			log.error("【CacheInfoService】listByMap方法内部出错，请检查该方法！");
		}
	}
}
