package com.xiaobaozi.dataSystem.commons.utils;

import java.io.Serializable;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class SendTemplateMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2985644788339263281L;
	private static Logger log = Logger.getLogger(SendTemplateMessage.class);

	/**
	 * 发送模板消息 appId 公众账号的唯一标识 appSecret 公众账号的密钥 openId 用户标识
	 * 
	 * @throws Exception
	 */
	public static String sendTemplateMessage(String jsonStr, String access_token) throws Exception {
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
		JSONObject json = JSONObject.fromObject(jsonStr);
		log.info("jsonString=" + json);
		String result = HttpUtil.httpPostWithJSON(url, json);
		return result;

	}

	public static void main(String[] args) throws Exception {
		String str = PropertiesUtil.getValue("/conf/properties/messageTemplateWx.properties", "gjTakeOrder.json");
		JSONObject json = JSONObject.fromObject(str);
		String result = HttpUtil
				.httpPostWithJSON(
						"https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=16_8d3uVAMJqatvi-XCndgEJ5iXgyF6XFQ-DTE8eV_r5puPxoW4yQrg99-GDJp-VuavJp7nI-Jm0wF2q35rRCCZqCijzn0fjfbWzSiqCq1kiOzaVuQHZM-2bAVDISFphRRH9lkfoC3uSiusjFS_IBBaAJARKK",
						json);
		System.out.println(result);
	}

}
