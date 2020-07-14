package com.xiaobaozi.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.Constants;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;

public class PlatformUtil {
	
	private static Map<String, String> ptMap = new HashMap<String, String>();
	private static String qunar;
	private static String taobao;
	private static String xiecheng;
	private static String kuXun;
	private PlatformUtil() {
	
	}
	
	static {
		// 去哪儿
		qunar = PropertiesUtil.getValue("conf/properties/systemconf.properties", "qunar.platform.ptInfo.ptCode");
		ptMap.put(qunar, Constants.PLATFORM_TYPE_QUNAR);
		// 淘宝
		taobao = PropertiesUtil.getValue("conf/properties/systemconf.properties", "taobao.platform.ptInfo.ptCode");
		ptMap.put(taobao, Constants.PLATFORM_TYPE_TAOBAO);
		// 携程
		xiecheng = PropertiesUtil.getValue("conf/properties/systemconf.properties", "xiecheng.platform.ptInfo.ptCode");
		ptMap.put(xiecheng, Constants.PLATFORM_TYPE_XIECHENG);
		// 酷讯
		kuXun = PropertiesUtil.getValue("conf/properties/systemconf.properties", "kuXun.platform.ptInfo.ptCode");
		ptMap.put(kuXun, Constants.PLATFORM_TYPE_KUXUN);
	}
	
	/**
	 * 将平台ID转化成单个字母
	 * @param ptid
	 * @return
	 */
	public static String transPtcode(String ptid) {
		return ptMap.get(ptid);
	}
	
	/**
	 * 获取平台中文名称
	 * @param ptid
	 * @return
	 */
	public static String getPtName(String ptid) {
		String code = transPtcode(ptid);
		if (Constants.PLATFORM_TYPE_QUNAR.equals(code)) {
			return "去哪儿";
		}
		if (Constants.PLATFORM_TYPE_TAOBAO.equals(code)) {
			return "淘宝";
		}
		if (Constants.PLATFORM_TYPE_XIECHENG.equals(code)) {
			return "携程";
		}
		if (Constants.PLATFORM_TYPE_KUXUN.equals(code)) {
			return "酷讯";
		}
		return "";
	}
	
	public static String getPtid(String ptcode) {
		if (Constants.PLATFORM_TYPE_QUNAR.equals(ptcode)) {
			return qunar;
		} else if (Constants.PLATFORM_TYPE_TAOBAO.equals(ptcode)) {
			return taobao;
		} else if (Constants.PLATFORM_TYPE_XIECHENG.equals(ptcode)) {
			return xiecheng;
		} else if (Constants.PLATFORM_TYPE_KUXUN.equals(ptcode)) {
			return kuXun;
		} else {
			return "";
		}
	}
	
	/**
	 * 获取平台列表
	 * @return
	 */
	public static List<String> getPtList() {
		return new ArrayList<String>(ptMap.keySet());
	}

}
