package com.xiaobaozi.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.usermanage.vo.Function;

public class Constants {

	/**
	 * 缓存所有菜单对象
	 */
	public static List<Function> cacheFunList;
	/**
	 * 缓存listByMap获取的数据，通过cacheListByMap.get(vo的类全名)获取，如：cacheListByMap.get(Airlines.class.getName())
	 */
	public static Map<String, List<Object>> cacheListByMap = new HashMap<String, List<Object>>();
	/**
	 * 表单绑定decimal格式
	 */
	public final static String BINDER_DECIMAL_FORMAT = "#.00";
	
	/**
	 * 文件下载目录
	 */
	public final static String DOWNLOAD_DIR = "download";
	/**
	 * 文件下载目录
	 */
	public final static String UPLOAD_DIR = "module";
	
	/**
	 * 系统文件夹分隔符
	 */
	public final static String SYSTEM_DIR_FLITER = System.getProperty("file.separator");
	
	/**
	 * excel文件后缀
	 */
	public final static String EXCEL_FILE_FLITER = "xls";
	
	/**
	 * 乘客类型：1—成人 2—儿童 3—婴儿
	 */
	public final static String FLIGHT_PASSENGER_ADULT = "1";
	/**
	 * 乘客类型：1—成人 2—儿童 3—婴儿
	 */
	public final static String FLIGHT_PASSENGER_CHILD = "2";
	/**
	 * 乘客类型：1—成人 2—儿童 3—婴儿
	 */
	public final static String FLIGHT_PASSENGER_BABY = "3";
	
	/**
	 * 错误信息参数
	 */
	public final static String ERROR_MSG_PARAM = "errormsg";
	/**
	 * 发短信时的订单来源
	 */
	public final static String SENDSMS_ORDERFROM_AIR="A";//机票
	public final static String SENDSMS_ORDERFROM_HOTEL="H";//酒店
	
}
