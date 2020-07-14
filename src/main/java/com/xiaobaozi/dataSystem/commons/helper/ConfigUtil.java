package com.xiaobaozi.dataSystem.commons.helper;
/*package com.skyecho.shenzhenairlines.commons.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.skyecho.shenzhenairlines.commons.utils.StringUtil;

*//**
 * 提供系统配置的统一的使用接口。Java程序内直接调用各个API。
 * @author liuxb
 * 2014-10-24
 *//*
public class ConfigUtil {
	private  static ConfigUtil configUtil = new ConfigUtil();

	private static final Logger logger = Logger.getLogger(ConfigUtil.class);


	public static ConfigUtil getInstance() {
		if(configUtil==null){
			configUtil=new ConfigUtil();
		}
		return configUtil;
	}

	

	protected final boolean getConfigAsBool(String key, boolean defValue) {
		return Boolean.parseBoolean(getConfig(key, "" + defValue));
	}

	protected final double getConfigAsDouble(String key, double defValue) {
		return Double.parseDouble(getConfig(key, "" + defValue));
	}

	protected final int getConfigAsInt(String key, int defValue) {
		return Integer.parseInt(getConfig(key, "" + defValue));
	}

	*//**
	 * 上传媒体文件时候的根目录
	 * @return
	 *//*
	public String getMediaUploadPath() {
		return getConfig("ftprootdoc","upload");
	}

	*//**
	 * 取得上传媒体文件目录全路径
	 * @param path 文件目录地址，需带/，如"/image/logo"
	 * @return
	 *//*
	public String getMediaUploadPathFull(String path) {
		String mediaCategoryStr = getMediaUploadPath();
		mediaCategoryStr += path;
		return mediaCategoryStr;
	}

	*//**
	 * 图片服务器部署地址
	 *//*
	public String getImageUrl() {
		return getConfig("imageurl");
	}

	
	*//**
	 * 默认日期显示格式，带时分秒的日期格式
	 * @return
	 *//*
	public String getDateTimePattern() {
		return getConfig("global.dateTimePattern", "yyyy-MM-dd HH:mm:ss");
	}
	
	*//**
	 * 默认日期显示格式，不带时分秒的日期格式
	 * @return
	 *//*
	public String getDatePattern() {
		return getConfig("global.datePattern", "yyyy-MM-dd");
	}
	
	*//**
	 * 获取ftp服务器ip
	 * @return
	 *//*
	public String getFtpServerIp(){
		return getConfig("ftpserverip");
	}
	
	*//**
	 * 获取ftp服务器端口号
	 * @return
	 *//*
	public String getFtpServerPort(){
		return getConfig("ftpserverport");
	}
	
	*//**
	 * 获取ftp服务器账号
	 * @return
	 *//*
	public String getFtpUserName(){
		return getConfig("ftpusername");
	}
	
	*//**
	 * 获取ftp服务器密码
	 * @return
	 *//*
	public String getFtpPassword(){
		return getConfig("ftppassword");
	}
	
	*//**
	 * 获取下载媒体文件的根目录
	 * @return
	 *//*
	public String getMediaDownloadPath(){
		return getConfig("ftpdowndoc");
	}
	
	*//**
	 *取得下载媒体文件目录全路径
	 * @param path 文件目录地址，需带/，如"/module"
	 * @return
	 *//*
	public String getMediaDownloadPathFull(String path){
		String mediaCategoryStr = getMediaDownloadPath();
		mediaCategoryStr += path;
		return mediaCategoryStr;
	}
	
	*//**
	 * 获取机票接口启用类型
	 * @return
	 *//*
	public int getTicketInterfaceType(){
		return getConfig("ticketinterfacetype",3);
	}
	*//**
	 * 获取机票接口地址
	 * @param type
	 * @return
	 *//*
	public String getTicketInterfaceUrl(int type){
		String key = "tickectinterfacetype"+type+"url";
		return getConfig(key);
	}
	*//**
	 * 获取机票接口密匙
	 * @param type
	 * @return
	 *//*
	public String getTicketInterfaceSign(int type){
		String key = "tickectinterfacetype"+type+"key";
		return getConfig(key);
	}
	
	*//**
	 *取得机票接口配置（接口类型type地址url及密匙sign）
	 * @return map
	 *//*
	public Map<String,String> getTicketInterfaceConfig(){
		int type = getTicketInterfaceType();
		String url = getTicketInterfaceUrl(type);
		String sign = getTicketInterfaceSign(type);
		Map<String,String> co = new HashMap<String,String>();
		co.put("type", String.valueOf(type));
		co.put("url", url);
		co.put("sign", sign);		
		return co;
	}
	
	*//**
	 * 获取租车接口地址
	 * @author dengzq
	 * @return
	 *//*
	public String getCarrentalInterfaceUrl(){
		return getConfig("carrentalinterface");
	}
	*//**
	 * 获取租车接口DES密匙
	 * @author dengzq
	 * @return
	 *//*
	public String getCarrentalInterfaceSign(){
		return getConfig("carrentalinterfacesign");
	}
	
	*//**
	 * 取得租车接口和密钥配置(地址url密钥sign)
	 * @author dengzq
	 * @return map
	 *//*
	public Map<String,String> getCarrentalInterfaceConfig(){
		String url = getCarrentalInterfaceUrl();
		String sign = getCarrentalInterfaceSign();
		Map<String,String> co = new HashMap<String,String>();
		co.put("url", url);
		co.put("sign", sign);		
		return co;
	}
	
	*//**
	 * 获取CRM passengerWS 地址
	 * @return
	 *//*
	public String getCrmPassengerWsUrl(){
		return getConfig("crminterfapassengerwsurl");
	}
	
	*//**
	 * 获取CRM CustWs 地址
	 * @return
	 *//*
	public String getCrmCustWsUrl(){
		return getConfig("crminterfacecustwsurl");
	}
	
	*//**
	 * 获取通信加密密匙
	 * @return
	 *//*
	public String getSignKey(){
		return getConfig("signkey");
	}
	
	*//**
	 * 获取接口项目部署地址
	 * @return
	 *//*
	public String getSzairlinesInterfacesUrl(){
		return getConfig("szairlinesinterfacesurl");
	}
	
	*//**
	 * 获取黑屏接口地址
	 * @return
	 *//*
	public String getEtermInterfacesUrl(){
		return getConfig("eterminterfaceurl");
	}
	*//**
	 * 获取后台项目部署地址
	 * @return
	 *//*
	public String getSzAirlinesSystemUrl(){
		return getConfig("szairlinessystemurl");
	}
	*//**
	 * 获取前台项目部署地址
	 * @return
	 *//*
	public String getSzAirlinesWebSystemUrl(){
		return getConfig("szairlineswebsystemurl");
	}
	
	*//**
	 * 获取支付接口项目部署地址
	 * @return
	 *//*
	public String getPayInterfaceServerUrl(){
		return getConfig("payinterfaceserverurl");
	}
	
	*//**
	 * 获取采购平台项目部署地址
	 * @return
	 *//*
	public String getPerchaseInterfaceServerUrl(){
		return getConfig("purchasesystemurl");
	}
	
	*//**
	 * 获取默认机票配送机构
	 * @return
	 *//*
	public String getDefaultTicketSendCorp(){
		return getConfig("defaultsendticketcorp");
	}
	
	*//**
	 * 获取默认机票支付出票机构
	 * @return
	 *//*
	public String getDefaultTicketCorp(){
		return getConfig("defaultticketcorp");
	}
	
	*//**
	 * 获取默认机票结算价折扣点数：如2代表2个点，即折扣为0.98
	 * @return
	 *//*
	public Integer getDefaultInPriceDiscount(){
		return getConfig("defaultinpricediscount",2);
	}
	*//**
	 * 获取SQLLDR的配置
	 * 
	 *//*
	public String getSqlldr(){
		return getConfig("sqlldrUrl");
	}
	*//**
	 * 获取默认查询时间间隔
	 * @return
	 *//*
	public Integer getDefaultTimeInterval(){
		return getConfig("defaulttimeinterval",2);
	}
	
	*//**
	 * 获取默认查询时间间隔单位
	 * 1-月
	 * 2-天
	 * @return
	 *//*
	public Integer getDefaultTimeIntervalUnit(){
		return getConfig("defaulttimeintervalunit",1);
	}
	
	
	*//**
	 * 获取mas短信配置
	 * @return
	 *//*
	public Map<String,String> getSMSConfig(){
		//设置应用ID或插件的ID
		int applicationid = getConfigAsInt("smsapplicationid",0);
		//设置由该应用填写的内部扩展号码。MAS服务器需自动补充为此业务分配的长服务号码
		String extendcode = getConfig("smsextendcode");
		//服务端分配的账号
		String username = getConfig("smsusername");
		// 服务端分配的密码（明文）
		String password = getConfig("smspassword");
		// 通道选择 0 普通通道（100条/秒) 1 VIP通道（200条/秒)
		String passageway = getConfig("smspassageway");
		// 是否必须走此通道 0 如果流量达到上限直接返回报错 1 流量达到上限也必须走此通道
		String ismain = getConfig("smsismain");
		//短信请求返回地址
		String callbackurl = getConfig("smscallbackurl");
		//所属部门编号
		String deptno = getConfig("smsdeptno");
		//发送员工编号
		String empno = getConfig("smsempno");
		//请求标识
		String requestidentifier = getConfig("smsrequestidentifier");
		Map<String,String> co = new HashMap<String,String>();
		co.put("applicationid", String.valueOf(applicationid));
		co.put("extendcode", extendcode);
		co.put("username", username);		
		co.put("password", password);		
		co.put("passageway", passageway);		
		co.put("ismain", ismain);		
		co.put("callbackurl", callbackurl);		
		co.put("deptno", deptno);		
		co.put("empno", empno);		
		co.put("requestidentifier", requestidentifier);		
		return co;
	}
	
	*//**
	 * 获取快乐传媒短信企业id
	 * @return
	 * @author hesx 2015-5-13 上午11:24:26
	 *//*
	public String getMediasmsUserId() {
		String value = getConfig("mediasmsuserid");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信扩展子号
	 * @return
	 * @author hesx 2015-5-13 上午11:26:28
	 *//*
	public String getMediasmsExtno() {
		String value = getConfig("mediasmsextno");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信账号
	 * @return
	 * @author hesx 2015-5-13 上午11:27:22
	 *//*
	public String getMediasmsAccount() {
		String value = getConfig("mediasmsaccount");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信密码
	 * @return
	 * @author hesx 2015-5-13 上午11:29:00
	 *//*
	public String getMediasmsPassword() {
		String value = getConfig("mediasmspassword");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信定时发送时间
	 * @return
	 * @author hesx 2015-5-13 上午11:29:44
	 *//*
	public String getMediasmsSendTime() {
		String value = getConfig("mediasmssendtime");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信接口地址
	 * @return
	 * @author hesx 2015-5-13 上午11:31:42
	 *//*
	public String getMediasmsInterUrl() {
		String value = getConfig("mediasmsinterurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信状态查询接口地址
	 * @return
	 * @author hesx 2015-5-13 上午11:34:13
	 *//*
	public String getMediasmsQueryStatusInterUrl() {
		String value = getConfig("mediasmsquerystatusurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取快乐传媒短信上行接口地址
	 * @return
	 * @author hesx 2015-5-13 上午11:37:31
	 *//*
	public String getMediasmsCallapiInterUrl() {
		String value = getConfig("mediasmscallapiurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	
	*//**
	 * 获取话费充值账号
	 * @return
	 * @author hesx 2015-5-13 上午10:03:05
	 *//*
	public String getPhoneFillAccount() {
		String value = getConfig("phoneFillAccount");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取话费充值密钥
	 * @return
	 * @author hesx 2015-5-13 上午10:04:15
	 *//*
	public String getPhoneFillMerchantkey() {
		String value = getConfig("phoneFillCheckKey");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取充值下单接口url地址
	 * @return
	 * @author hesx 2015-5-13 上午10:05:54
	 *//*
	public String getPhoneFillInterUrl() {
		String value = getConfig("phonefillinterurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取充值产口查询接口url地址
	 * @return
	 * @author hesx 2015-5-13 上午10:07:19
	 *//*
	public String getQueryPhoneFillProductInterUrl() {
		String value = getConfig("queryphonefillproductinterurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取充值订单结果查询接口url地址
	 * @return
	 * @author hesx 2015-5-13 上午10:09:06
	 *//*
	public String getQueryPhoneFillResultInterUrl() {
		String value = getConfig("queryphonefillresultinterurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取号段查询接口url地址
	 * @return
	 * @author hesx 2015-5-13 上午10:10:12
	 *//*
	public String getQuerySegmentInterUrl() {
		String value = getConfig("querysegmentinterurl");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取用户最多连续不登陆月数
	 * 默认1个月
	 * @return
	 *//*
	public Integer getUserMaxNoLoginMonth(){
		return getConfig("usermaxnologinmonth",1);
	}
	
	*//**
	 * 根据平台id查询平台配置信息
	 * @param platformId
	 * @return
	 * @author hesx 2015-3-2 下午09:50:56
	 *//*
	public Map<String,Object> getPlatformConfigInfo(String platformId) {
		*//**
		 *  平台配置信息configKey拼装规则:
		 *  平台名称：platformId+"provisionid"
		 *  平台内部ip:platformId+"provisionip"
		 *  平台端口号：platformId+"provisionport"
		 *  平台接口地址：platformId+"provisionoutinterface"
		 *  	接口地址拼装规则：1|http://www.baidu.com|11,2|http://www.hao123.com|22,3|http://www.so.com|33
		 *//*
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("provisionid", getConfig(platformId+"provisionid"));
		map.put("provisionip", getConfig(platformId+"provisionip"));
		map.put("provisionport", getConfig(platformId+"provisionport"));
		Map<String,String> interfaceInfo = new HashMap<String,String>();
		String interfaces = getConfig(platformId+"provisionoutinterface");
		for(String s : interfaces.split(",")) {
			String[] infos = s.split("\\|");
			interfaceInfo.put(infos[0], infos[1]);
		}
		map.put("interfaceInfo", interfaceInfo);
		return map;
	}
	
	*//**
	 * 获取自动出票开关状态
	 * @return boolean true:开启	false:关闭
	 * @author hesx 2015-4-16 上午10:21:04
	 *//*
	public boolean isAutoTicketOpen() {
		String value = getConfig("autotickettag");
		if("1".equals(value)) {
			return true;
		}
		return false;
	}
	
	*//**
	 * 获取航意险开关状态
	 * @return boolean true:开启	false:关闭
	 * @author yangsong 2015-5-18 上午09:38:04
	 *//*
	public boolean aviationAccidentOpen() {
		String value = getConfig("aviationAccident");
		if("1".equals(value)) {
			return true;
		}
		return false;
	}
	*//**
	 * 获取生成pnr接口office来源(1:指定office/2:订单office)
	 * @author hesx
	 * @return
	 * 2015-4-24 下午3:13:01
	 *//*
	public String getCreatePnrOfficeSrc() {
		String value = getConfig("createpnrofficesrc");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	
	*//**
	 * 获取系统指定生成pnr的office
	 * @author hesx
	 * @return
	 * 2015-4-24 下午3:21:47
	 *//*
	public String getCreatePnrOfficeDefault() {
		String value = getConfig("defaultcreatepnroffice");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	
	*//**
	 * 获取酒店用户
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getHotelUser() {
		String value = getConfig("appUser");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取酒店密码
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getHotelappKey() {
		String value = getConfig("appKey");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取酒店密钥
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getHotelappSecret() {
		String value = getConfig("appSecret");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取酒店版本
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getHotelVersion() {
		String value = getConfig("version");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取酒店国家
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getHotelLocale() {
		String value = getConfig("locale");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取酒店地址
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getHotelServerHost() {
		String value = getConfig("serverHost");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取bsp自动出票接口url地址
	 * @return
	 * @author hesx 2015-5-12 下午1:40:39
	 *//*
	public String getBSPAutoticketInterUrl() {
		String value = getConfig("autoticketInter");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	*//**
	 * 获取深航排除自动出票订单类型
	 * @return String
	 * @exception
	 * @author hesx
	 *//*
	public String getZhnotautotype() {
		String value = getConfig("zhnotautotype");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
	
	*//**
	 * 根据订单来源查询平台出票方式
	 * @param resFrom
	 * @return String Y:BSP/B:B2B
	 * @exception
	 * @author hesx 2015-6-14 上午9:50:12
	 *//*
	public String getOutTikcetWayByResFrom(int resFrom) {
		String value = getConfig(resFrom+"platformoutticketway");
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return "";
	}
}
*/