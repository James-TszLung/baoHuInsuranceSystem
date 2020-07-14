package com.xiaobaozi.dataSystem.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密工具类
 * 
 * @author hesx
 * @2014-12-29 @上午10:02:32
 */
public class MD5Util {

	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int t;
		for (int i = 0; i < 16; i++) {
			t = bytes[i];
			if (t < 0)
				t += 256;
			sb.append(hexDigits[(t >>> 4)]);
			sb.append(hexDigits[(t % 16)]);
		}
		return sb.toString();
	}

	/**
	 * 32位md5加密，采用utf-8编码
	 * @author hesx
	 * @param str
	 * @return
	 * @2014-12-29 @上午10:17:29
	 */
	public static String md5For32Bit(String str) {
		String md5Str = "";
		try {
			md5Str = code(str, 32, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}
	
	/**
	 * 32位md5加密，可自定义字符编码
	 * @author hesx
	 * @param str
	 * @param encode
	 * @return
	 * @2014-12-29 @上午10:41:03
	 */
	public static String md5For32Bit(String str, String encode) {
		String md5Str = "";
		try {
			md5Str = code(str, 32, encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	/**
	 * 16位md5加密，采用utf-8编码
	 * @author hesx
	 * @param str
	 * @return
	 * @2014-12-29 @上午10:18:37
	 */
	public static String md5For16Bit(String str) {
		String md5Str = "";
		try {
			md5Str = code(str, 16, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	/**
	 * 16位md5加密，可自定义字符编码
	 * @author hesx
	 * @param str
	 * @param encode
	 * @return
	 * @2014-12-29 @上午10:42:13
	 */
	public static String md5For16Bit(String str, String encode) {
		String md5Str = "";
		try {
			md5Str = code(str, 16, encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	public static String code(String str, int bit, String encode) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (bit == 16)
				return bytesToHex(md.digest(str.getBytes(encode))).substring(8, 24);
			return bytesToHex(md.digest(str.getBytes(encode)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Could not found MD5 algorithm.", e);
		}
	}
}
