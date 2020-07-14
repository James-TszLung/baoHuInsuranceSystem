package com.xiaobaozi.dataSystem.commons.utils;
import java.security.Key;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 * <pre>
 * 		使用方式说明：
 * 	0.设定密钥字符串：密钥=“商定内容”
 * 	1.加密：DESUtil util = new DESUtil(密钥);
 * 		  String plainText = "需要加密的内容";
 * 		  String cipherText = util.encrypt(plainText);//得到密文
 * 	2.解密：DESUtil util = new DESUtil(密钥);
 * 		  String cipherText = "XXXXXXXXX密文内容XXXXXXXXXXX";
 * 		  String plainText = util.decrypt(cipherText);
 * </pre>
 * @author
 * @version 1.0
 * @since 2014-5-30
 */
public class DESUtil {

	/**
	 * DEFAULT_KEY_STR：默认密钥
	 */
	private static final String DEFAULT_KEY_STR = "航旅天空travelsky%*##*%ykslevart空天旅航";

	private Cipher encryptCipher = null;

	private Cipher decryptCipher = null;

	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 */
	public DESUtil() {
		this(DEFAULT_KEY_STR);
	}

	/**
	 * 指定密钥构造方法
	 * 
	 * @param keyString 指定的密钥
	 * @throws Exception
	 */
	public DESUtil(String keyString) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key;
		try {
			key = getKey(keyString.getBytes());
			encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密字节数组
	 */
	private byte[] encrypt(byte[] arrB) {
		byte[] s = null;
		try {
			s = encryptCipher.doFinal(arrB);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 解密字节数组
	 */
	private byte[] decrypt(byte[] arrB) {
		byte[] s = null;
		try {
			s = decryptCipher.doFinal(arrB);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 将启动码strIn加密，返回加密后的启动码
	 */
	public String encrypt(String plainText) {
		return byteArr2HexStr(encrypt(plainText.getBytes()));
	}

	/**
	 * 解密 strIn
	 */
	public String decrypt(String cipherText) {
		return new String(decrypt(hexStr2ByteArr(cipherText)));
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串，
	 * 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB 需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception 本方法不处理任何异常，所有异常全部抛出
	 */
	private static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 */
	private static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp 构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws java.lang.Exception
	 */
	private Key getKey(byte[] arrBTmp) {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	public static void main(String args[]) {
		// 使用方式：
		String string = "bcdefghijklmnopqrst`1234567890-=+_)(*&^%$#@!~ 	\n?;";
		DESUtil des = new DESUtil(); // 自定义密钥
		System.out.println("加密前的字符：" + string);
		String en = des.encrypt(string);
		System.out.println("加密后的字符：" + en);
		String de = des.decrypt(en);
		System.out.println("解密后的字符：" + de);
	}
}