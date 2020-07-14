package com.xiaobaozi.commons.utils;

 

public class DoGet {
//	public static Logger log = Logger.getLogger(DoGet.class);
//	public static String ARG = "DOGet";
//
//	public static String doGet(String url, StringBuffer param) {
//		String uriAPI = url + "?" + param;
//		System.out.println(uriAPI);
//		log.info("请求连接地址：" + uriAPI);
//		String result = "";
//		// HttpGet httpRequst = new HttpGet(URI uri);
//		// HttpGet httpRequst = new HttpGet(String uri);
//		// 创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
//		HttpGet httpRequst = new HttpGet(uriAPI);
//
//		// new DefaultHttpClient().execute(HttpUriRequst requst);
//		try {
//			// 使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
//			HttpResponse httpResponse = new DefaultHttpClient()
//					.execute(httpRequst);// 其中HttpGet是HttpUriRequst的子类
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				HttpEntity httpEntity = httpResponse.getEntity();
//				result = EntityUtils.toString(httpEntity);// 取出应答字符串
//				// 一般来说都要删除多余的字符
//				result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
//			} else
//				httpRequst.abort();
//		} catch (ClientProtocolException e) {
//			result = e.getMessage().toString();
//			log.error(ARG, e);
//		} catch (IOException e) {
//			log.error(ARG, e);
//			e.printStackTrace();
//			result = e.getMessage().toString();
//		}
//		log.info(ARG+":"+ result);
//		return result;
//	}

}
