package com.xiaobaozi.commons.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 发送http请求
 * 
 * @author liuxh
 * 
 */
public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);

	/**
	 * 获取请求的IP地址
	 * 
	 * @param request
	 * @author zhengbh
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 发送http请求
	 * 
	 * @param url
	 * @param paramStr
	 * @return
	 */
	public static String http(String url, String paramStr) {

		BufferedReader reader = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			String responseMessage = "";
			@SuppressWarnings("unused")
			StringBuffer response = new StringBuffer();
			HttpURLConnection httpConnection = null;
			DataOutputStream out = null;

			try {
				URL urlPost = new URL(url);
				httpConnection = (HttpURLConnection) urlPost.openConnection();
				httpConnection.setConnectTimeout(100000); // 设置连接主机超时
				httpConnection.setReadTimeout(100000); // 设置从主机读取数据超时
				httpConnection.setDoOutput(true);
				httpConnection.setDoInput(true);
				// 参数长度太大，不能用get方式
				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("contentType", "UTF-8");
				// 不使用缓存
				httpConnection.setUseCaches(false);
				// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
				httpConnection.setInstanceFollowRedirects(true);
				/**
				 * 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
				 * 意思是正文是urlencoded编码过的form参数
				 */
				httpConnection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				/**
				 * 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
				 * 要注意的是connection.getOutputStream会隐含的进行connect。
				 * 实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
				 */
				httpConnection.connect();
				out = new DataOutputStream(httpConnection.getOutputStream());
				/**
				 * The URL-encoded contend 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
				 * 写入参数,DataOutputStream
				 * .writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
				 */
				out.writeBytes(paramStr);
				out.flush();

				reader = new BufferedReader(new InputStreamReader(
						httpConnection.getInputStream(), "UTF-8"));
				String result = reader.readLine();
				StringBuffer sb = new StringBuffer(result);
				while (result != null) {
					result = reader.readLine();
					if (result != null) {
						sb.append(result);
					}
				}
				// 将该url的配置信息缓存起来
				return sb.toString();
			} catch (IOException e) {
				log.info("接口调用失败,url:" + url, e);
				return "";
			} finally {
				try {
					if (null != out) {
						out.close();
					}
					if (null != reader) {
						reader.close();
					}
					if (null != httpConnection) {
						httpConnection.disconnect();
					}
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}

		} catch (Exception e) {
			log.info("接口调用失败,url:" + url);
			return "";
		}
	}

	/**
	 * 自动作废
	 * 
	 * @param order
	 * @return
	 */
	/*public static String autoRefundUtil(AirBookVO order) {

		StringBuffer sign = new StringBuffer();
		StringBuffer input = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		input.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		input.append("<Request>");
		input.append("<OfficeNo>");
		input.append(order.getOfficeID());
		input.append("</OfficeNo>");
		if (order != null && order.getAirBookLines().size() > 0) {
			int size1 = order.getAirBookLines().size();
			for (int i = 0; i < size1; i++) {
				AirBookLineVO airBookLineVO = order.getAirBookLines().get(i);
				if (airBookLineVO != null
						&& airBookLineVO.getAirBookDetails().size() > 0) {
					int size2 = airBookLineVO.getAirBookDetails().size();
					for (int j = 0; j < size2; j++) {
						input.append("<Parm>");
						input.append("<Pnr>");
						input.append(order.getBigPnr() != null ? order
								.getBigPnr() : "");
						input.append("</Pnr>");
						input.append("<TicketNo>");
						input.append(airBookLineVO.getAirBookDetails().get(j)
								.getAirCode());
						input.append(airBookLineVO.getAirBookDetails().get(j)
								.getTicketID());
						input.append("</TicketNo>");
						input.append("</Parm>");
					}
				}
			}
		}
		input.append("</Request>");

		// 签名方式sign=密钥+请求参数+yyyy-MM-dd reqXml=请求参数
		ConfigUtil configUtil = ConfigUtil.getInstance();
		log.info(configUtil.getSignKey());
		sign.append(configUtil.getSignKey());
		sign.append(input);
		sign.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		sb.append("sign=");
		sb.append(MD5.md5(sign.toString()));
		sb.append("&reqXml=");
		sb.append(input);

		log.info("【自动废票】接口请求参数：" + sb.toString());
		String url = ConfigUtil.getInstance().getSzairlinesInterfacesUrl();
		url = url + "autoRefund/autoRefund.htm";
		// url =
		// "http://127.0.0.1:8080/szAirlinesInterfaces/autoRefund/autoRefund.htm";
		String xml = http(url, sb.toString());
		log.info("自动作废接口返回：" + xml);
		return xml;
	}

	*//**
	 * 检验票证状态
	 * 
	 * @param order
	 * @return
	 *//*
	public static Map<String, Object> checkTicket(AirBookVO order) {

		// 调用接口查询客票状态
		Map<String, Object> map = new HashMap<String, Object>();
		if (order.getAirBookLines() != null
				&& order.getAirBookLines().size() > 0) {

			String path = ConfigUtil.getInstance().getSzairlinesInterfacesUrl(); // 从数据库获得接口地址
			// http://localhost:8081/szAirlinesInterfaces/
			// String url = "http://localhost:8080/szAirlinesInterfaces/"
			String url = path + "orderReservationInterface/detrNoOption.htm"; // 接口请求地址
			for (int i = 0; i < order.getAirBookLines().size(); i++) {
				if (order.getAirBookLines().get(i).getAirBookDetails() != null
						&& order.getAirBookLines().get(i).getAirBookDetails()
								.size() > 0) {
					for (int j = 0; j < order.getAirBookLines().get(i)
							.getAirBookDetails().size(); j++) {

						AirBookDetailVO airBookDetailVO = order
								.getAirBookLines().get(i).getAirBookDetails()
								.get(j);
						StringBuffer requestXml = new StringBuffer();
						long ticketNoStr = airBookDetailVO.getTicketID();
						String airCode = airBookDetailVO.getAirCode();
						String sign = MD5
								.md5(new SimpleDateFormat("yyyy-MM-dd")
										.format(new Date())
										+ airCode
										+ ticketNoStr);
						requestXml.append("sign=");
						requestXml.append(sign);
						requestXml.append("&&ticketNoStr=");
						requestXml.append(airCode);
						requestXml.append(ticketNoStr);

						String result = HttpUtil.http(url,
								requestXml.toString());
						Document document = null;
						try {
							document = DocumentHelper.parseText(result
									.toString());
							map.put("msg", " 票号信息异常  ");
							map.put("code", "002");
							Element root = document.getRootElement();
							String resultCode = root.element("ResultCode")
									.getStringValue();
							if ("I00006".equals(resultCode)) {

								String ResultMsg = root.element("ResultMsg")
										.getStringValue();
								map.put("msg", ResultMsg);
								map.put("code", "002");
								return map;
							} else if ("000000".equals(resultCode)) {
								List<Element> detrList = root.elements("Detr");
								if (detrList != null && detrList.size() > 0) {
									for (int k = 0; k < detrList.size(); k++) {

										Element detrSegment = detrList.get(k)
												.element("DetrSegment");
										List<Element> segmentList = detrSegment
												.elements("Segment");
										if (segmentList != null
												&& segmentList.size() > 0) {
											for (int l = 0; l < segmentList
													.size(); l++) {

												Element segment = segmentList
														.get(l);
												String ticketStatus = segment
														.element("TicketStatus")
														.getStringValue();
												if (ticketStatus != null
														&& "REFUNDED"
																.equalsIgnoreCase(ticketStatus
																		.trim())) {
													break;
												} else {
													return map;
												}
											}
										} else {
											return map;
										}
									}
								} else {
									return map;
								}
							} else {
								log.info("票号信息异常  ");
								return map;
							}
						} catch (Exception e) {
							log.error("解析异常", e);
							map.put("msg", "接口异常");
							map.put("code", "002");
							return map;
						}
					}
				}
			}
			map.put("msg", " 票号状态REFUNDED ");
			map.put("code", "000");
			return map;
		}
		map.put("msg", "航段为空");
		map.put("code", "002");
		return map;
	}*/
}
