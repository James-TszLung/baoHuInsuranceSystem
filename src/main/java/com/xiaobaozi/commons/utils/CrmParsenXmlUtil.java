package com.xiaobaozi.commons.utils;
/*package com.skyecho.commons.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.skyecho.client.controller.reservation.domestic.Thread.InsCrmCus;
import com.skyecho.shenzhenairlines.commons.Constants;
import com.skyecho.shenzhenairlines.commons.helper.ConfigUtil;
import com.skyecho.shenzhenairlines.commons.utils.ZipUtil;

*//**
 * CRM常旅客和直销解析
 * 
 * @author xubiao
 * 
 *         2014-11-8
 *//*
public class CrmParsenXmlUtil {
	private static Logger log = Logger.getLogger(CrmParsenXmlUtil.class);

	// 通过协大客户得到常旅客
	public JSONObject getCustInfo(JSONObject json) {
		JSONObject returnObj = new JSONObject();
		String requestString = "";
		String returnInfo = "";
		requestString = this.installCustInfoXml(json);
		ConfigUtil configUtil = ConfigUtil.getInstance();
		String urlString = configUtil.getSzairlinesInterfacesUrl()
				+ Constants.INTERFACE_SZAIR_CRM;
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(urlString);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		try {
			httpClient.setTimeout(Integer.valueOf("60000"));// 超时1分钟
			log.info("接收到请求参数为" + requestString);
			// 进行BASE64位加密
			String signReturn = ZipUtil
					.gzip((configUtil.getSignKey() + "|" + requestString));
			NameValuePair[] datas = { new NameValuePair("sign", signReturn) };
			// 将表单的值放入postMethod中
			post.setRequestBody(datas);
			int statusCode = httpClient.executeMethod(post);
			if (statusCode == 200) {
				returnInfo = post.getResponseBodyAsString();
				log.info("通过客户协议号得到的返回数据为：" + returnInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("通过客户协议号得到的返回数据异常：" + e.getMessage());
		} finally {
			post.releaseConnection();// 释放链接
			returnObj = parsenCustInfoXml(returnInfo, json);
		}
		Thread thread = new Thread(new InsCrmCus(returnObj));
		thread.start();
		return returnObj;
	}

	// 协议客户查询接口
	public JSONObject findPassengerByCustNo(JSONObject json) {
		JSONObject returnObj = new JSONObject();
		String requestString = "";
		String returnInfo = "";
		if (json == null || StringUtils.isBlank(json.getString("custNo"))) {
			returnObj.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
			returnObj.put("message", "协议客户号必填!");
			return returnObj;
		} else {
			// 组装请求信息
			requestString = this.installPassengerXml(json);
		}
		ConfigUtil configUtil = ConfigUtil.getInstance();
		String urlString = configUtil.getSzairlinesInterfacesUrl()
				+ Constants.INTERFACE_SZAIR_CRMIN;
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(urlString);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		try {
			httpClient.setTimeout(Integer.valueOf("60000"));// 超时1分钟
			log.info("接收到请求参数为" + requestString);
			// 进行BASE64位加密
			String signReturn = ZipUtil
					.gzip((configUtil.getSignKey() + "|" + requestString));
			NameValuePair[] datas = { new NameValuePair("sign", signReturn) };
			// 将表单的值放入postMethod中
			post.setRequestBody(datas);
			int statusCode = httpClient.executeMethod(post);
			if (statusCode == 200) {
				returnInfo = post.getResponseBodyAsString();
				log.info("通过协议客户客户协议号得到的返回数据为：" + returnInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("通过协议客户客户协议号得到的返回数据异常：" + e.getMessage());
		} finally {
			post.releaseConnection();// 释放链接
			returnObj = parsenPassengerXml(returnInfo, json);
		}
		return returnObj;
	}

	// 解析解析协议客户传JSON对象
	public JSONObject parsenPassengerXml(String xml, JSONObject json) {
		JSONObject returnObj = new JSONObject();
		if (StringUtils.isNotBlank(xml)) {
			// 将XML解析为JSON
			Document document = null;
			try {
				document = DocumentHelper
						.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
								+ xml);
				Element root = document.getRootElement();
				if (root.element("status") != null) {
					String resultCode = root.element("status").getStringValue();
					if ("H0000".equals(resultCode)) {
						List<Element> passengers = root.element(
								"oftenPassengers").elements();
						returnObj.put("returnScode",
								Constants.CONF_DATA_TYPE_FLAG);
						JSONArray jsonarry = new JSONArray();
						for (Element e1 : passengers) {
							JSONObject obj = new JSONObject();
							obj.put("id", e1.element("id").getStringValue());
							obj.put("ot_name", e1.element("ot_name")
									.getStringValue());
							obj.put("ot_type", e1.element("ot_type")
									.getStringValue());
							obj.put("duties", e1.element("duties")
									.getStringValue());
							obj.put("telephone", e1.element("telephone")
									.getStringValue());
							obj.put("phone", e1.element("phone")
									.getStringValue());
							obj.put("certno", e1.element("certno")
									.getStringValue());
							obj.put("hobby", e1.element("hobby")
									.getStringValue());
							obj.put("remark", e1.element("remark")
									.getStringValue());
							obj.put("deptid", e1.element("deptid")
									.getStringValue());
							obj.put("registertime", e1.element("insertTime")
									.getStringValue());
							obj.put("certtype", e1.element("certtype")
									.getStringValue());
							obj.put("birthday", e1.element("birthday")
									.getStringValue());
							jsonarry.add(obj);
						}
						returnObj.put("passengers", jsonarry);
					} else {
						returnObj.put("returnScode",
								Constants.CONF_DATA_TYPE_FLAGFALSE);
						returnObj.put("message", "接口返回数据代码错误!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("解析返回数据出错：" + e.getMessage());
				returnObj
						.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
				returnObj.put("message", "解析返回数据出错!");
			}
		} else {
			returnObj.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
			returnObj.put("message", "接口返回信息为空!");
		}
		return returnObj;
	}

	// 解析XNL传JSON对象
	public JSONObject parsenCustInfoXml(String xml, JSONObject json) {
		JSONObject returnObj = new JSONObject();
		if (StringUtils.isNotBlank(xml)) {
			// 将XML解析为JSON
			Document document = null;
			try {
				document = DocumentHelper
						.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
								+ xml);
				Element root = document.getRootElement();
				if (root.element("resultCode") != null) {
					String resultCode = root.element("resultCode")
							.getStringValue();
					if ("R0001".equals(resultCode)) {
						returnObj.put("returnScode",
								Constants.CONF_DATA_TYPE_FLAG);
						List<Element> custInfoList = root.elements();
						JSONArray jsonarrycustInfo = new JSONArray();
						for (Element custInfo : custInfoList) {
							if ("custInfo".equals(custInfo.getName())) {
								JSONObject returnCust = new JSONObject();
								returnCust.put("custNo",
										custInfo.element("custNo")
												.getStringValue());
								returnCust.put("custName",
										custInfo.element("custName")
												.getStringValue());
								returnCust.put("zhCustNo",
										custInfo.element("zhCustNo")
												.getStringValue());
								returnCust.put("zhCustName",
										custInfo.element("zhCustName")
												.getStringValue());
								returnCust.put("networkName",
										custInfo.element("networkName")
												.getStringValue());
								returnCust.put("domeStartDate", custInfo
										.element("domeStartDate")
										.getStringValue());
								returnCust.put("domeEndDate",
										custInfo.element("domeEndDate")
												.getStringValue());
								returnCust.put("inteStartDate", custInfo
										.element("inteStartDate")
										.getStringValue());
								returnCust.put("inteEndDate",
										custInfo.element("inteEndDate")
												.getStringValue());
								returnCust.put("zhDomeStartDate", custInfo
										.element("zhDomeStartDate")
										.getStringValue());
								returnCust.put("zhDomeEndDate", custInfo
										.element("zhDomeEndDate")
										.getStringValue());
								returnCust.put("zhInteStartDate", custInfo
										.element("zhInteStartDate")
										.getStringValue());
								returnCust.put("zhInteEndDate", custInfo
										.element("zhInteEndDate")
										.getStringValue());
								returnCust.put("coopWayName",
										custInfo.element("coopWayName")
												.getStringValue());
								returnCust.put("setWayName",
										custInfo.element("setWayName")
												.getStringValue());
								returnCust.put("zhCoopWayName", custInfo
										.element("zhCoopWayName")
										.getStringValue());
								returnCust.put("guaranteeway", custInfo
										.element("guaranteeway")
										.getStringValue());
								returnCust.put("customertype", custInfo
										.element("customertype")
										.getStringValue());
								returnCust.put("isGroupName",
										custInfo.element("isGroupName")
												.getStringValue());
								returnCust.put("address",
										custInfo.element("address")
												.getStringValue());
								returnCust.put("custNote",
										custInfo.element("custNote")
												.getStringValue());
								returnCust.put("pricetotal",
										custInfo.element("pricetotal")
												.getStringValue());
								returnCust.put("noPayAmount",
										custInfo.element("noPayAmount")
												.getStringValue());
								returnCust.put("hasNormalCust", custInfo
										.element("hasNormalCust")
										.getStringValue());
								JSONArray jsonarry = new JSONArray();
								if ("1".equals((custInfo
										.element("hasNormalCust") == null ? ""
										: custInfo.element("hasNormalCust")
												.getStringValue()))) {
									// 有常旅客
									List<Element> list = custInfo.element(
											"passengers").elements();
									if (list != null && list.size() > 0) {
										if (list.get(0).elements() != null
												&& list.get(0).elements()
														.size() > 0) {
											for (Element e1 : list) {
												JSONObject obj = new JSONObject();
												obj.put("toName",
														e1.element("toName")
																.getStringValue());
												obj.put("pinyinName", e1
														.element("pinyinName")
														.getStringValue());
												obj.put("highTravType",
														e1.element(
																"highTravType")
																.getStringValue());
												obj.put("duties",
														e1.element("duties")
																.getStringValue());
												obj.put("certno",
														e1.element("certno")
																.getStringValue());
												obj.put("toPhone",
														e1.element("toPhone")
																.getStringValue());
												obj.put("telePhone", e1
														.element("telePhone")
														.getStringValue());
												obj.put("departmentName",
														e1.element(
																"departmentName")
																.getStringValue());
												obj.put("authortype", e1
														.element("authortype")
														.getStringValue());
												obj.put("remark",
														e1.element("remark")
																.getStringValue());
												obj.put("qq", e1.element("qq")
														.getStringValue());
												jsonarry.add(obj);
											}
										}
									}
								}
								returnCust.put("passengers", jsonarry);
								jsonarrycustInfo.add(returnCust);
							}
						}
						returnObj.put("custInfos", jsonarrycustInfo);
					} else {
						returnObj.put("returnScode",
								Constants.CONF_DATA_TYPE_FLAGFALSE);
						returnObj.put("message", "接口返回数据代码错误!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("解析返回数据出错：" + e.getMessage());
				returnObj
						.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
				returnObj.put("message", "解析返回数据出错!");
			}
		} else {
			returnObj.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
			returnObj.put("message", "接口返回信息为空!");
		}
		// 将大客户进行入库操作

		return returnObj;
	}

	// 组装客户信息XML
	public String installCustInfoXml(JSONObject json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<condition>"
				+ "<custNo>"
				+ json.getString("custNo")
				+ "</custNo>"
				+ "<custName>"
				+ json.getString("custName")
				+ "</custName>"
				+ "<zhCustNo>"
				+ json.getString("zhCustNo")
				+ "</zhCustNo>"
				+ "<selectStatus>"
				+ json.getString("selectStatus")
				+ "</selectStatus>"
				+ "<otName>"
				+ json.getString("otName")
				+ "</otName>"
				+ "<pinyinName>"
				+ json.getString("pinyinName")
				+ "</pinyinName>"
				+ "<phone>"
				+ json.getString("phone")
				+ "</phone>"
				+ "<cardCode>"
				+ json.getString("cardCode")
				+ "</cardCode>" + "</condition>";
	}

	*//**
	 * 获取净结点
	 * 
	 * @param json
	 * @return
	 * @author zhengbh
	 * @date Apr 17, 2015 10:41:31 AM
	 *//*
	public JSONObject getZhPercen(JSONObject json) {
		JSONObject returnObj = new JSONObject();
		String requestString = "";
		String returnInfo = "";
		requestString = this.installZhPercenXML(json);
		ConfigUtil configUtil = ConfigUtil.getInstance();
		String urlString = configUtil.getSzairlinesInterfacesUrl()
				+ Constants.INTERFACE_SZAIR_CRM_ZHPERCEN;
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(urlString);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		try {
			httpClient.setTimeout(Integer.valueOf("60000"));// 超时1分钟
			log.info("接收到请求参数为" + requestString);
			// 进行BASE64位加密
			String signReturn = ZipUtil
					.gzip((configUtil.getSignKey() + "|" + requestString));
			NameValuePair[] datas = { new NameValuePair("sign", signReturn) };
			// 将表单的值放入postMethod中
			post.setRequestBody(datas);
			int statusCode = httpClient.executeMethod(post);
			if (statusCode == 200) {
				returnInfo = post.getResponseBodyAsString();
				log.info("通过客户号得到的返回数据为：" + returnInfo);
			}
		} catch (Exception e) {
			log.error("通过客户号得到的返回数据异常：", e);
		} finally {
			post.releaseConnection();// 释放链接
			returnObj = parsenZhPercenInfoXml(returnInfo, json);
		}
		return returnObj;
	}

	*//**
	 * 解析净结点返回XML
	 * 
	 * @param xml
	 * @param json
	 * @return
	 * @author zhengbh
	 * @date Apr 17, 2015 10:45:12 AM
	 *//*
	public JSONObject parsenZhPercenInfoXml(String xml, JSONObject json) {
		JSONObject returnObj = new JSONObject();
		if (StringUtils.isNotBlank(xml)) {
			// 将XML解析为JSON
			Document document = null;
			try {
				document = DocumentHelper
						.parseText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
								+ xml);
				Element root = document.getRootElement();
				if (root.element("resultCode") != null) {
					String resultCode = root.element("resultCode")
							.getStringValue();
					if ("R0001".equals(resultCode)) {
						returnObj.put("returnScode",
								Constants.CONF_DATA_TYPE_FLAG);
						returnObj.put("ZhPercen", root.element("ZhPercen").getStringValue());
						
						JSONObject custResult = new JSONObject();
						Element rsElement = root.element("custResult");
						for(Element c:(List<Element>)rsElement.elements()){
							custResult.put(c.getName(), c.getStringValue());
						}
						
						returnObj.put("custResult", custResult);
					} else {
						returnObj.put("returnScode",
								Constants.CONF_DATA_TYPE_FLAGFALSE);
						returnObj.put("message", "接口返回数据代码错误!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("解析返回数据出错：" + e.getMessage());
				returnObj
						.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
				returnObj.put("message", "解析返回数据出错!");
			}
		} else {
			returnObj.put("returnScode", Constants.CONF_DATA_TYPE_FLAGFALSE);
			returnObj.put("message", "接口返回信息为空!");
		}
		// 将大客户进行入库操作

		return returnObj;
	}

	*//**
	 * 组装净结点请求XML
	 * 
	 * @param json
	 * @return
	 * @author zhengbh
	 * @date Apr 17, 2015 10:17:51 AM
	 *//*
	public String installZhPercenXML(JSONObject json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<conditions><condition>" + "<flagState>"
				+ json.getString("flagState")
				+ "</flagState>"
				+ "<custNo>"
				+ json.getString("custNo")
				+ "</custNo>"
				+ "<orderNo>"
				+ json.getString("orderNo")
				+ "</orderNo>"
				+ "<carrier>"
				+ json.getString("carrier")
				+ "</carrier>"
				+ "<ticketDate>"
				+ json.getString("ticketDate")
				+ "</ticketDate>"
				+ "<dep>"
				+ json.getString("dep")
				+ "</dep>"
				+ "<arr>"
				+ json.getString("arr")
				+ "</arr>"
				+ "<cabin>"
				+ json.getString("cabin")
				+ "</cabin>"
				+ "</condition></conditions>";
	}

	// 组装协议客户信息XML
	public String installPassengerXml(JSONObject json) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<data>"
				+ "<paramMap>" + "<custNo>" + json.getString("custNo")
				+ "</custNo>" + "</paramMap>" + "</data>";
	}

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><conditions>"
				+ "<condition>" + "<flagState>1</flagState>"
				+ "<custNo>GMSZ1260</custNo>" + "<orderNo></orderNo>"
				+ "<carrier></carrier>" + "<ticketDate></ticketDate>"
				+ "<dep>SZX</dep>" + "<arr>CTU</arr>" + "<cabin>G</cabin>"
				+ "</condition>" + "</conditions>";
		ConfigUtil configUtil = ConfigUtil.getInstance();
		String urlString = "http://58.67.197.84:8080/szAirlinesInterfaces/crmtravel/crmtravelInterface/queryPolicyInfoByConditions.htm";
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(urlString);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		try {
			httpClient.setTimeout(Integer.valueOf("60000"));// 超时1分钟
			log.info("接收到请求参数为" + xml);
			// 进行BASE64位加密
			String signReturn = ZipUtil
					.gzip(("sdfasdfii#$%$@^&@$#fgdsfg|" + xml));
			NameValuePair[] datas = { new NameValuePair("sign", signReturn) };
			// 将表单的值放入postMethod中
			post.setRequestBody(datas);
			int statusCode = httpClient.executeMethod(post);
			if (statusCode == 200) {
				String returnInfo = post.getResponseBodyAsString();
				log.info("通过客户协议号得到的返回数据为：" + returnInfo);
				CrmParsenXmlUtil util = new CrmParsenXmlUtil();
				System.out.println(util.parsenZhPercenInfoXml(returnInfo, null).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("通过客户协议号得到的返回数据异常：" + e);
		} finally {
			post.releaseConnection();// 释放链接
		}
	}
}
*/