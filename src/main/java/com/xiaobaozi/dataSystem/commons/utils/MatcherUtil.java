package com.xiaobaozi.dataSystem.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/**
 * 验证工具类
 * @author liuxb
 *
 */
public class MatcherUtil {

	protected static Logger log = Logger.getLogger(MatcherUtil.class);
	
	private MatcherUtil() {
	
	}
	
	/**
	 * 验证匹配航空公司二字码
	 * @param string
	 * @return
	 */
	public static boolean compileAirCode(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[0-9A-Z]{2}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配航班号
	 * @param string
	 * @return
	 */
	public static boolean compileFlightNo(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("\\w{1,10}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配仓位
	 * @param string
	 * @return
	 */
	public static boolean compileCabin(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[A-Z]{1}\\d?");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配机场三字码
	 * @param string
	 * @return
	 */
	public static boolean compileAirPortCode(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[A-Z]{3}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配航班时间 
	 * @param string 00:00-24:00
	 * @return
	 */
	public static boolean compileFlightTime(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("([0-1]{1}[\\d]{1}:[0-5]{1}[\\d]{1})|(2[0-3]{1}:[0-5]{1}[\\d]{1})|24:00");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配旅乘客姓名：2-10中文或者英文
	 * @param string 
	 * @return
	 */
	public static boolean compilePassengerName(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[\u4E00-\u9FA5a-zA-Z/]{2,10}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配旅客类型:A-成人B-婴儿I-儿童
	 * @param string 
	 * @return
	 */
	public static boolean compilePassengerType(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[ACI]{1}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配是否携带婴儿 Y/N
	 * @param string 
	 * @return
	 */
	public static boolean compileHavingBaby(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[YN]{1}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配证件类型
	 * NI-身份证,PP-护照,ID-军官证,DD-通行证,SD-士兵证,OD-其它,TB-台胞证
	 * @param string 
	 * @return
	 */
	public static boolean compileCardType(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("NI|PP|ID|DD|SD|OD|TB");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配证件号码
	 * @param string 
	 * @return
	 */
	public static boolean compileCardID(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9]{5,20}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配出生日期
	 * @param string 2014-11-20
	 * @return
	 */
	public static boolean compileBirth(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?" +"[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +"|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +"(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12" +"35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?[" +"1-9])|(1[0-9])|(2[0-8]))))))");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配舱位折扣
	 * @param string 
	 * @return
	 */
	public static boolean compileAirDiscount(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("\\d{1}(\\.\\d{1,2})?");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配手机号
	 * @param string 
	 * @return
	 */
	public static boolean compileMobile(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("\\d{5,13}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配价格金额
	 * @param string 
	 * @return
	 */
	public static boolean compilePrice(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("-?\\d{1,9}(.\\d{1,2})?");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	/**
	 * 验证匹配抵达+/-天数(时差)
	 * @param string 
	 * @return
	 */
	public static boolean compileArriveDate(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[\\-\\+]\\d{1}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证匹配OfficeID
	 * @param string 
	 * @return
	 */
	public static boolean compileOfficeID(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9]{6}");
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}
	    
}
