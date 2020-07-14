package com.xiaobaozi.dataSystem.commons.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;

import com.xiaobaozi.dataSystem.commons.JsonDateValueProcessor;

public class JsonUtil {

	protected static Logger log = Logger.getLogger(JsonUtil.class);

	private JsonUtil() {

	}

	/**
	 * 将对象数据转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertObjectToJson(Object obj) {
		JsonConfig jsonConfig = new JsonConfig(); // JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jsonMap = JSONObject.fromObject(obj, jsonConfig);
		return null != jsonMap ? jsonMap.toString() : "";
	}

	/**
	 * 将对象数据(list, array)转换成arrayjson字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertToArrayJson(Object obj) {
		JsonConfig jsonConfig = new JsonConfig(); // JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONArray jsonMap = JSONArray.fromObject(obj, jsonConfig);
		return null != jsonMap ? jsonMap.toString() : "";
	}

	/**
	 * 将json数组转成指定实体类对象集合 author hesx
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @2014-11-16 @下午1:49:46
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Object> jsonStringToList(String jsonStr, Class clazz) {
		List<Object> list = null;
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		list = (List<Object>) JSONArray.toCollection(jsonArray, clazz);
		return list;
	}

	/**
	 * 异步调用返回
	 * 
	 * @param response
	 * @param map
	 *            - 需要传递到前端的键值对
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void ajaxReturn(HttpServletResponse response, Map map) {
		JSONObject jarr = JSONObject.fromObject(map);
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(jarr.toString());
		} catch (IOException e) {
			log.error("写出数据失败", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 异步调用返回
	 * 
	 * @param response
	 * @param objs
	 *            - 需要传递到前端的所有字段
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void ajaxReturn(HttpServletResponse response, Object... objs) {
		JSONArray jarr = new JSONArray();
		for (Object obj : objs) {
			if (obj instanceof Collection) {
				jarr.addAll((Collection) obj);
			} else {
				jarr.add(obj);
			}
		}
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(jarr.toString());
		} catch (IOException e) {
			log.error("数据写出失败！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 异步调用返回，用HTML的方式
	 * 
	 * @param response
	 * @param map
	 *            - 需要传递到前端的键值对
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void ajaxReturnByHTML(HttpServletResponse response, Map map) {
		JSONObject jarr = JSONObject.fromObject(map);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(jarr.toString());
		} catch (IOException e) {
			log.error("写出数据失败", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 异步调用返回，用HTML的方式
	 * 
	 * @param response
	 * @param map
	 *            - 需要传递到前端的键值对
	 * @throws IOException
	 */
	public static void ajaxReturnByHTML(HttpServletResponse response, JSONObject jarr) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(jarr.toString());
		} catch (IOException e) {
			log.error("写出数据失败", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将json对象转成bean
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @2014-11-16 @下午1:49:46
	 */
	public static Object jsonStringToObject(String jsonStr, Class clazz) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		Object obj = (Object) JSONObject.toBean(jsonObject, clazz);
		return obj;
	}
}
