package com.xiaobaozi.commons.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xiaobaozi.commons.Constants;

public class StringUtil {

	private static Log log = LogFactory.getLog(StringUtil.class);
	private static final String STRING_ENCODE = "utf-8";

	private StringUtil() {

	}

	public static BigDecimal convertStringToDemical(String str) {
		if (StringUtils.isEmpty(str)) {
			str = "0";
		}
		BigDecimal bd = BigDecimal.ZERO;
		try {
			str = str.replaceAll("[a-zA-Z]", "");
			if (StringUtils.isEmpty(str)) {
				str = "0";
			}
			double d = Double.valueOf(str);
			DecimalFormat df = new DecimalFormat(
					Constants.BINDER_DECIMAL_FORMAT);
			str = df.format(d);
			bd = new BigDecimal(str);
		} catch (Exception e) {
			log.error("convertStringToDemical str=" + str + " error:"
					+ e.getMessage());
			e.printStackTrace();
		}
		return bd;
	}

	public static String MD5(String data) {
		String sign = "";
		MessageDigest messageDigest;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(data.getBytes());

			byte[] bytes = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();

			for (int i = 0; i < bytes.length; i++) {
				int v = bytes[i] & 0xff;

				if (v < 16)
					stringBuffer.append(0);
				stringBuffer.append(Integer.toHexString(v));
			}

			sign = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			log.error("MD5 " + data + " error:" + e.getMessage());
		}

		return sign;
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
}
