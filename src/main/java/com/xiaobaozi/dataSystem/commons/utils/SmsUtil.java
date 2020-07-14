package com.xiaobaozi.dataSystem.commons.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信工具类
 * 
 * @author media
 * 
 */
public class SmsUtil {

	public static Map<String, Object> map = new HashMap<String, Object>();

	public static void sendSms(String smsMob, String smsText) {
		String smsUrl = PropertiesUtil.getValue("/conf/properties/smsTemplate.properties", "sms.url");
		String smsUid = PropertiesUtil.getValue("/conf/properties/smsTemplate.properties", "sms.uid");
		String smsKey = PropertiesUtil.getValue("/conf/properties/smsTemplate.properties", "sms.key");
		HttpUtil.httpGet(smsUrl + "?Uid=" + smsUid + "&Key=" + smsKey + "&smsText=" + smsText + "&smsMob=" + smsMob);
	}

	public static Map<String, Object> checkSms(String phone, String authCode) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// retMap.put("success", "1");
		// retMap.put("mes", "验证成功");
		// return retMap;
		if (phone != null && authCode.equals(String.valueOf(map.get(phone)))) {
			long time = DateUtil.subtractionMinute(new Date(Long.parseLong(String.valueOf(map.get(phone + "Time")))),
					new Date());
			if (time > 10) {
				retMap.put("success", "3");
				retMap.put("mes", "短信验证码已过期，请重新获取");
				return retMap;
			} else {
				retMap.put("success", "1");
				retMap.put("mes", "验证成功");
				return retMap;
			}
		} else {
			retMap.put("success", "5");
			retMap.put("mes", "验证码不匹配");
			return retMap;
		}
	}
}
