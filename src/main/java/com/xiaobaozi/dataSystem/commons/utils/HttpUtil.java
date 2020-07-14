package com.xiaobaozi.dataSystem.commons.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 发送http请求
 * 
 * @author liuxh
 * 
 */
public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);
	private static int count = 0; // 指定发送请求次数计数器

	/**
	 * post请求接口
	 * 
	 * @param url
	 *            接口地址
	 * @param param
	 *            参数
	 * @return
	 */
	public static String post(String url, String param) {

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
			httpConnection.setConnectTimeout(120000); // 设置连接主机超时
			httpConnection.setReadTimeout(120000); // 设置从主机读取数据超时
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			// 参数长度太大，不能用get方式
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("contentType", "UTF-8");
			httpConnection.setUseCaches(false);// 不使用缓存
			httpConnection.setInstanceFollowRedirects(true);// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			httpConnection.setRequestProperty("Content-Type",// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数
					"application/x-www-form-urlencoded");

			// 实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
			httpConnection.connect();
			out = new DataOutputStream(httpConnection.getOutputStream());

			// 写入参数,DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(param);
			out.flush();// flush and close

			reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
			while ((responseMessage = reader.readLine()) != null) {
				xml.append(responseMessage);
			}
			if (!"failure".equals(response.toString())) {
				log.info("success send to JMX");
			}
			log.info("获取数据url = " + url + "?" + param + " ,返回结果：" + xml);
			return xml.toString();
		} catch (IOException e) {
			log.error("连接失败,url={" + url + "?" + param + "}");
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

	/**
	 * httpGet请求
	 * 
	 * @param url
	 *            接口地址
	 * @param param
	 *            参数
	 * @return
	 */
	public static String httpGet(String url) {

		// 用HttpClient发送请求，分为五步
		// 第一步：创建HttpClient对象
		HttpClient httpCient = new DefaultHttpClient();
		// 第二步：创建代表请求的对象,参数是访问的服务器地址
		HttpGet httpGet = new HttpGet(url);
		String response = "";
		try {
			// 第三步：执行请求，获取服务器发还的相应对象
			HttpResponse httpResponse = httpCient.execute(httpGet);
			// 第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 第五步：从相应对象当中取出数据，放到entity当中
				HttpEntity entity = httpResponse.getEntity();
				response = EntityUtils.toString(entity, "utf-8");// 将entity当中的数据转换为字符串
				System.out.println("接口返回结果：" + response);
				log.info("HttpUtil.httpGet.response="+response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * httpsGet请求
	 * 
	 * @param url
	 *            接口地址
	 * @param param
	 *            参数
	 * @return
	 */
	public static String httpsGet(String url) {

		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;

		try {
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);

			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "UTF-8");
					log.info("HttpUtil.httpsGet:"+result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
          
		return result;
	}

	/**
	 * post请求接口
	 * 
	 * @param url
	 *            接口地址
	 * @param param
	 *            参数
	 * @return
	 */
	public static String post(String url, String param, boolean b) {

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
			if (b) {
				httpConnection.setConnectTimeout(120000); // 设置连接主机超时
				httpConnection.setReadTimeout(120000); // 设置从主机读取数据超时
			}
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);

			// 参数长度太大，不能用get方式
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("contentType", "UTF-8");
			httpConnection.setUseCaches(false);// 不使用缓存
			httpConnection.setInstanceFollowRedirects(true);// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			httpConnection.setRequestProperty("Content-Type",// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数
					"application/x-www-form-urlencoded");

			// 实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
			httpConnection.connect();
			out = new DataOutputStream(httpConnection.getOutputStream());

			// 写入参数,DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(param);
			out.flush();// flush and close

			reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
			while ((responseMessage = reader.readLine()) != null) {
				xml.append(responseMessage);
			}
			if (!"failure".equals(response.toString())) {
				log.info("success send to JMX");
			}
			log.info("获取数据url = " + url + "?" + param + " ,返回结果：" + xml);
			return xml.toString();
		} catch (IOException e) {
			log.error("连接失败,url={" + url + "?" + param + "}");
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

	public static String httpPostWithJSON(String url, JSONObject jsonParam) throws Exception {

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;

		// json方式
		// JSONObject jsonParam = new JSONObject();
		// jsonParam.put("name", "admin");
		// jsonParam.put("pass", "123456");
		StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		// 表单方式
		// List<BasicNameValuePair> pairList = new
		// ArrayList<BasicNameValuePair>();
		// pairList.add(new BasicNameValuePair("name", "admin"));
		// pairList.add(new BasicNameValuePair("pass", "123456"));
		// httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));

		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		return respContent;
	}

	/**
	 * httpCliet4 发送https请求 数据格式xml
	 * 
	 * @param url
	 * @param soapContent
	 * @return
	 * @throws Exception
	 */
	public static String httpsPost(String url, String soapContent,String apiclientCert,String mchId) throws Exception {

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(apiclientCert));
		try {
			keyStore.load(instream, mchId.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			instream.close();
		}

		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,mchId.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		HttpPost httpPost = null;
		BufferedReader reader = null;

		try {
			httpclient = new SSLClient();
			httpPost = new HttpPost(url);
			StringEntity myEntity = new StringEntity(soapContent, "UTF-8");
			httpPost.addHeader("Content-Type", "text/xml; charset=UTF-8");
			httpPost.setEntity(myEntity);
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\r\n");
				}
				return sb.toString();
			}
		} catch (Exception e) {
		} finally {
			if (httpPost != null) {
				httpPost.abort();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return "none";
	}

	public static void main(String[] args) throws Exception {
		JSONObject json = JSONObject
				.fromObject("{\"data\":{\"remark\":{\"color\":\"#000000\",\"value\":\"***备注说明***\"},\"keyword1\":{\"color\":\"#000000\",\"value\":\"另一行内人\"},\"keyword2\":{\"color\":\"#000000\",\"value\":\"N行\"},\"first\":{\"color\":\"#000000\",\"value\":\"这里填写您要发送的模板信息\"},\"keyword3\":{\"color\":\"#990033\",\"value\":\"**666666\"}},\"touser\":\"o3a9Lt6JcWEbAWYxJNQcihc8zDfU\",\"template_id\":\"g44Ihmi5KKmaJnpBl9hnUjxYT-3js6EIU3UbFtZGWwU\",\"topcolor\":\"#000000\",\"url\":\"https://www.baidu.com\"}");
		String result = httpPostWithJSON(
				"https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=Wr52F314dXkPxaxT2G6c_5gKC87xbGn_rQeBNxat5jTabEc3tmMK_ffoyH88QbVnK3KmSZeYbpKVBY1RDRqCTxQv3yw7eBP7pQXSRk2-X-NTqfwEV50sg6tfRlcrVI7fUILdAIAIWZ",
				json);
		System.out.println(result);
	}
}

class SSLClient extends DefaultHttpClient {
	public SSLClient() throws Exception {
		super();
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = this.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
	}
}