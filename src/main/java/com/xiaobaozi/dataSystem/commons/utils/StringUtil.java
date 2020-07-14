package com.xiaobaozi.dataSystem.commons.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xiaobaozi.dataSystem.commons.Constants;


public class StringUtil {

	private static Log	log	= LogFactory.getLog(StringUtil.class);
	private static final String STRING_ENCODE = "utf-8";
	private StringUtil() {
	
	}
	
	public static BigDecimal convertStringToDemical(String str){
		if(StringUtils.isEmpty(str)){
			str="0";
		}
		BigDecimal bd = BigDecimal.ZERO;
		try {
			str = str.replaceAll("[a-zA-Z]", "");
			if(StringUtils.isEmpty(str)){
				str = "0";
			}
			double d = Double.valueOf(str);
			DecimalFormat df = new DecimalFormat(Constants.BINDER_DECIMAL_FORMAT);
			str = df.format(d);
			bd = new BigDecimal(str);
		} catch (Exception e) {
			log.error("convertStringToDemical str="+str+" error:"+e.getMessage());
			e.printStackTrace();
		}
		return bd;
	}
	
	public static String MD5(String data){
		String sign = "";
		MessageDigest messageDigest;
		
		try{
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(data.getBytes());
			
			byte[] bytes = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			
			for(int i=0; i<bytes.length; i++){
				int v = bytes[i] & 0xff;
				
				if(v < 16)
					stringBuffer.append(0);
				stringBuffer.append(Integer.toHexString(v));
			}
			
			sign = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e){
//			e.printStackTrace();
			log.error("MD5 "+data+" error:"+e.getMessage());
		}
		
		return sign;
	}
	
	public final static String[] toArray(String str) {
		return toArrayByDel(str, ";");
	}

	public final static String[] toArrayByDel(String str, String del) {
		String[] array = null;
		if (str != null && str.length() > 0 && del != null && del.length() > 0) {
			StringTokenizer st = new StringTokenizer(str, del);
			if (st != null && st.countTokens() > 0) {
				array = new String[st.countTokens()];
				int i = 0;
				while (st.hasMoreTokens()) {
					array[i++] = st.nextToken();
				}
			}
		}

		return array;
	}
    
	public final static String arrayToString(String [] array,String separatorStr){
		StringBuffer result =  new StringBuffer("");
		for(String c:array){
			if(StringUtils.isNotBlank(c)&&StringUtils.isNotBlank(separatorStr)){
				result.append(c+separatorStr);
			}
		}
		if(result.length()>0){
			result.substring(0,(result.length()-1));
		}
		return result.toString();
	}
	
	public final static String arrayToString(List<String> array,String separatorStr){
		StringBuffer result =  new StringBuffer("");
		for(String c:array){
			if(StringUtils.isNotBlank(c)&&StringUtils.isNotBlank(separatorStr)){
				result.append(c.trim()+separatorStr.trim());
			}
		}
		if(result.length()>0){
			return result.substring(0,(result.length()-1));
		}
		return result.toString();
	}
    
	/**
	 * 获取字符串的编码格式，用于转换编码时解码用
	 */
	public static String getDecodeStr(String str) {
		if (str == null || str.equals(""))
			return null;

		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = new String(str.getBytes(encode), STRING_ENCODE);
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = new String(str.getBytes(encode), STRING_ENCODE);
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = new String(str.getBytes(encode), STRING_ENCODE);
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = new String(str.getBytes(encode), STRING_ENCODE);
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
	
	/**
	 * 针对oracle 10g的CLOB字段，如果长度为1000<length<=2000时补足长度到2001
	 * @return
	 */
	public static String rightPadClob(String str) {
		// 判断是否需要补长度
		if (str != null && str.length() > 1000 && str.length() <= 2000) {
			String oracleVersion = PropertiesUtil.getValue(
					"conf/properties/systemconf.properties", "oracle.version");
			if (StringUtils.isBlank(oracleVersion)) {
				oracleVersion = "11g";	// 不配置oracle版本则默认为11g
			}
			if (oracleVersion.equalsIgnoreCase("10g")) {
				// 10g版本写入CLOB存在bug：当字符串长度length 1000<length<=2000时报错
				return StringUtils.rightPad(str, 2001);
			}
		}
		return str;
	}
	
	/**
	 * 判断数组是否包含空值
	 * @param array
	 * @return
	 */
	public static boolean isArrayContainNull(String[] array){
		if(null==array){
			return true;
		}
		for(String val : array){
			if(StringUtils.isEmpty(val)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 得到文件后缀名
	 * @param dataFile
	 * @return
	 */
	public static String reSep(String dataFile){
		String reSep="";
		if(StringUtils.isNotBlank(dataFile)){
			int i=dataFile.lastIndexOf(".");
			if(i>-1){
				reSep=dataFile.substring(i+1);
			}
		}
		return reSep;
	}
}
