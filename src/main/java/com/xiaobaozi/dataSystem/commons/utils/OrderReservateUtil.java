package com.xiaobaozi.dataSystem.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 封装调用中转接口的方法，返回xml
 * 
 * @author jinpeng
 * 
 */
public class OrderReservateUtil {
	private static final Logger log = Logger
			.getLogger(OrderReservateUtil.class);

	/**
	 * 查询航班
	 * 
	 * @param dep出发城市三字码
	 *            ，例如SZX
	 * @param arr导单城市三字码
	 *            ，例如PEK
	 * @param fromDate航班日期
	 *            ，例如2012-05-23
	 * @param airlines航空公司二字码
	 *            ，如果需要查询所有航空公司的航班，则用All，例如All
	 * @param direct是否只查询直飞航班
	 *            ，目前固定填写TRUE，例如TRUE
	 * @sign 接口密匙（测试：abc）
	 * @url 接口访问地址http://localhost:8080/szAirlinesInterfaces/
	 *      orderReservationInterface/getFlight.htm
	 * @return
	 */
	public String getFlight(String dep, String arr, String fromDate,
			String airlines, String direct, String sign, String url) {
		String result = "";
		if (StringUtils.isBlank(direct)) {
			direct = "TRUE";
		}
		if (StringUtils.isBlank(dep)) {
			return "【调用中转接口-查询航班】【警告】【出发城市三字码为空】";
		}
		if (StringUtils.isBlank(arr)) {
			return "【调用中转接口-查询航班】【警告】【抵达城市三字码为空】";
		}
		if (StringUtils.isBlank(fromDate)) {
			return "【调用中转接口-查询航班】【警告】【航班日期为空】";
		}
		if (StringUtils.isBlank(airlines)) {
			return "【调用中转接口-查询航班】【警告】【航空公司二字码为空】";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sf.parse(fromDate);
		} catch (Exception e) {
			return "【调用中转接口-查询航班】【警告】【航班日期格式错误，航班日期格式必须为yyyy-MM-dd】";
		}
		JSONObject json = new JSONObject();
		json.put("dep", dep);
		json.put("arr", arr);
		json.put("fromDate", fromDate);
		json.put("airlines", airlines);
		json.put("direct", direct);
		HttpURLConnection conn = null;
		OutputStream os = null;
		InputStream input = null;
		BufferedReader reader = null;
		try {
			URL urlTemp = new URL(url);
			conn = (HttpURLConnection) urlTemp.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			os = conn.getOutputStream();
			os.write(("json=" + json.toString() + "&sign=" + sign)
					.getBytes("UTF-8"));
			os.flush();
			os.close();
			input = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String b = reader.readLine();
			StringBuffer sb = new StringBuffer(b);
			while (b != null) {
				b = reader.readLine();
				if (b != null) {
					sb.append(b);
				}
			}
			log.info("【调用中转接口-查询航班】报文：" + sb.toString());
			result = sb.toString();
			reader.close();
			input.close();
			conn.disconnect();
		} catch (Exception e) {
			log.info("【调用中转接口-查询航班error】" + e.getMessage());
			return "【调用中转接口-查询航班error】" + e.getMessage();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (input != null) {
					input.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 通过航段组执行PAT操作
	 * 
	 * @param Org
	 *            始发机场三字码SZX
	 * @param Dest
	 *            抵达机场三字码CSX
	 * @param StartDate
	 *            航班日期2013-12-25
	 * @param Airline
	 *            航线（航司码）CZ
	 * @param FlightNo
	 *            航班号6890
	 * @param Cabin
	 *            舱位L
	 * @param PlaneModes
	 *            机型757
	 * @param ActionCode
	 *            此节点默认为空
	 * @param PassengerType
	 *            乘机人类型 （成人：ADT 儿童：CHD 婴儿：INF）
	 * @param Location
	 *            默认SZX
	 * @param Office
	 *            默认SZX540
	 * @param sign
	 *            接口密匙
	 * @param url
	 *            http://localhost:8080/szAirlinesInterfaces/
	 *            orderReservationInterface/doPatNoPnrByXml.htm
	 * @return
	 */
	public String doPatNoPnrByXml(String Org, String Dest, String StartDate,
			String Airline, String FlightNo, String Cabin, String PlaneModes,
			String ActionCode, String PassengerType, String Location,
			String Office, String sign, String url) {
		String result = "";
		if (StringUtils.isBlank(Org)) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【出发城市三字码为空】";
		}
		if (StringUtils.isBlank(Dest)) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【抵达城市三字码为空】";
		}
		if (StringUtils.isBlank(StartDate)) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【航班日期为空】";
		}
		if (StringUtils.isBlank(Airline)) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【航空公司二字码为空】";
		}
		if (StringUtils.isBlank(FlightNo)) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【航班号为空】";
		}
		if (StringUtils.isBlank(Cabin)) {
			return "【调用中转接口-查询航班】【警告】【舱位为空】";
		}
		if (StringUtils.isBlank(PassengerType)) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【乘客类型为空】";
		}
		if (StringUtils.isBlank(PlaneModes)) {
			PlaneModes = "";
		}
		if (StringUtils.isBlank(Location)) {
			Location = "SZX";
		}
		if (StringUtils.isBlank(Office)) {
			Office = "SZX540";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sf.parse(StartDate);
		} catch (Exception e) {
			return "【调用中转接口-通过航段组执行PAT操作】【警告】【航班日期格式错误，航班日期格式必须为yyyy-MM-dd】";
		}
		JSONObject segment = new JSONObject();
		segment.put("Org", Org);
		segment.put("Dest", Dest);
		segment.put("StartDate", StartDate);
		segment.put("Airline", Airline);
		segment.put("FlightNo", FlightNo);
		segment.put("Cabin", Cabin);
		segment.put("PlaneModes", PlaneModes);
		segment.put("ActionCode", ActionCode);
		JSONArray segments = new JSONArray();
		segments.add(segment);
		JSONObject json = new JSONObject();
		json.put("Segments", segments);
		json.put("PassengerType", PassengerType);
		json.put("Location", PassengerType);
		json.put("Office", Office);
		HttpURLConnection conn = null;
		OutputStream os = null;
		InputStream input = null;
		BufferedReader reader = null;
		try {
			URL urlTemp = new URL(url);
			conn = (HttpURLConnection) urlTemp.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(100000);
			conn.setReadTimeout(100000);
			os = conn.getOutputStream();
			os.write(("json=" + json.toString() + "&sign=" + sign)
					.getBytes("UTF-8"));
			os.flush();
			os.close();
			input = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String b = reader.readLine();
			StringBuffer sb = new StringBuffer(b);
			while (b != null) {
				b = reader.readLine();
				if (b != null) {
					sb.append(b);
				}
			}
			log.info("【调用中转接口-通过航段组执行PAT操作】报文：" + sb.toString());
			result = sb.toString();
			reader.close();
			input.close();
			conn.disconnect();
		} catch (Exception e) {
			log.info("【调用中转接口-通过航段组执行PAT操作error】" + e.getMessage());
			return "【调用中转接口-通过航段组执行PAT操作error】" + e.getMessage();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (input != null) {
					input.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据航段组和客户号进行pat操作
	 * 
	 * @param Airline
	 *            航线
	 * @param FlightNo
	 *            航班号
	 * @param Org
	 *            出发地
	 * @param Dest
	 *            到达地
	 * @param StartDate起飞日期
	 * @param Cabin
	 *            舱位
	 * @param ActionCode行动代码
	 * @param PassengerType乘客类型
	 *            ：A成人、C小孩、I婴儿
	 * @param CustNo协议客户号
	 *            ，不传默认为空
	 * @param sign
	 *            接口密匙
	 * @param url
	 *            http://localhost:8080/szAirlinesInterfaces/
	 *            orderReservationInterface/doPatBySegmentAndCustNoXml.htm
	 * @return
	 */
	public String doPatBySegmentAndCustNoXml(String Airline, String FlightNo,
			String Org, String Dest, String StartDate, String Cabin,
			String ActionCode, String PassengerType, String CustNo,
			String sign, String url) {

		// json =
		// "{'Segment':{'Airline':'ZH','FlightNo':'SZX','Org':'SZX','Dest':'PEK',
		// 'StartDate':'2012-12-31','Cabin':'Y','ActionCode':'LL'},'PassengerType':'A',
		// 'CustNo':'1CN006'}";
		// sign = "abc";
		if (StringUtils.isBlank(Airline)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【航线 为空】";
		}
		if (StringUtils.isBlank(FlightNo)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【航班号为空】";
		}
		if (StringUtils.isBlank(Org)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【出发地为空】";
		}
		if (StringUtils.isBlank(Dest)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【 到达地为空】";
		}
		if (StringUtils.isBlank(StartDate)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【起飞日期为空】";
		}
		if (StringUtils.isBlank(Cabin)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【舱位为空】";
		}
		if (StringUtils.isBlank(ActionCode)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【行动代码为空】";
		}
		if (StringUtils.isBlank(PassengerType)) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【乘客类型为空】";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sf.parse(StartDate);
		} catch (Exception e) {
			return "【调用中转接口-根据航段组和客户号来进行PAT操作】【警告】【航班日期格式错误，航班日期格式必须为yyyy-MM-dd】";
		}

		// 构建请求参数
		JSONObject json = new JSONObject();
		JSONObject segement = new JSONObject();

		segement.put("Airline", Airline);
		segement.put("FlightNo", FlightNo);
		segement.put("Org", Org);
		segement.put("Dest", Dest);
		segement.put("StartDate", StartDate);
		segement.put("Cabin", Cabin);
		segement.put("ActionCode", ActionCode);
		json.put("PassengerType", PassengerType);
		json.put("CustNo", CustNo);
		json.put("Segment", segement);

		StringBuffer param = new StringBuffer();
		param.append("json=");
		param.append(json);
		param.append("&");
		param.append("sign=");
		param.append(sign);

		// 返回结果
		String result = "";
		try {
			result = HttpUtil.post(url, param.toString());
		} catch (Exception e) {
			log.info("【调用中转接口-根据航段组和客户号来进行PAT操作error】" + e.getMessage());
			return "【调用中转接口-根据航段组和客户号来进行PAT操作error】" + e.getMessage();
		}
		log.info("【调用中转接口-根据航段组和客户号来进行PAT操作】报文：" + result);
		return result;
	}

	/**
	 * 根据pnr和客户号进行pat操作
	 * 
	 * @param pnrNo pnr信息
	 * @param passengerType  乘客类型（成人 A，儿童C，婴儿 I）
	 * @param custNo 协议客户号（可以为空），如：沃尔玛 1CN006
	 * @param sign 暂不要求
	 * @param url
	 *            http://localhost:8080/szAirlinesInterfaces/
	 *            orderReservationInterface/doPatByPnrAndCustNoXml.htm
	 * @return
	 */
	public static String doPatByPnrAndCustNoXml(String pnrNo, String passengerType,
			String custNo, String sign, String url) {

		if (StringUtils.isBlank(pnrNo)) {
			return "【调用中转接口-根据PNR和客户号来进行PAT操作】【警告】【pnr 为空】";
		}
		if (StringUtils.isBlank(passengerType)) {
			return "【调用中转接口-根据PNR和客户号来进行PAT操作】【警告】【乘客类型为空】";
		}

		// 构建请求参数
		JSONObject json = new JSONObject();

		json.put("pnrNo", pnrNo);
		json.put("passengerType", passengerType);
		json.put("custNo", custNo);

		StringBuffer param = new StringBuffer();
		param.append("json=");
		param.append(json);
		param.append("&");
		param.append("sign=");
		param.append(sign);

		// 返回结果
		String result = "";
		try {
			result = HttpUtil.post(url, param.toString());
		} catch (Exception e) {
			log.info("【调用中转接口-根据PNR和客户号来进行PAT操作error】" + e.getMessage());
			return "【调用中转接口-根据PNR和客户号来进行PAT操作error】" + e.getMessage();
		}
		log.info("【调用中转接口-根据PNR和客户号来进行PAT操作】报文：" + result);
		return result;
	}

}
