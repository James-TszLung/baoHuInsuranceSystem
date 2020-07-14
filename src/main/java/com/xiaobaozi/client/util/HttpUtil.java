package com.xiaobaozi.client.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	 * HTTP协议请求接口
	 * 
	 * @param url
	 *            接口地址
	 * @param sb
	 *            参数
	 * @return
	 */
	public String http(String url, StringBuffer sb) {

		BufferedReader reader = null;
		StringBuffer xml = new StringBuffer("");

		log.info("开始获取信息,地址" + url);
		String responseMessage = "";
		StringBuffer response = new StringBuffer();
		HttpURLConnection httpConnection = null;
		DataOutputStream out = null;

		try {
			URL urlPost = new URL(url);
			httpConnection = (HttpURLConnection) urlPost.openConnection();
			httpConnection.setConnectTimeout(30000); // 设置连接主机超时
			httpConnection.setReadTimeout(30000); // 设置从主机读取数据超时
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			// 参数长度太大，不能用get方式
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("contentType", "GBK");
			httpConnection.setUseCaches(false);// 不使用缓存
			httpConnection.setInstanceFollowRedirects(true);// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			httpConnection.setRequestProperty("Content-Type",// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数
					"application/x-www-form-urlencoded");

			// 实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
			httpConnection.connect();
			out = new DataOutputStream(httpConnection.getOutputStream());

			// 写入参数,DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(sb.toString());
			out.flush();// flush and close

			reader = new BufferedReader(new InputStreamReader(
					httpConnection.getInputStream(), "UTF-8"));
			while ((responseMessage = reader.readLine()) != null) {
				xml.append(responseMessage);
			}
			if (!"failure".equals(response.toString())) {
				log.info("success send to JMX");
			}
			return xml.toString();
		} catch (IOException e) {
			log.error("连接失败,url={" + url + "}");
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
				log.error("http connection close error:{}" + e2);
			}
		}

	}
}
