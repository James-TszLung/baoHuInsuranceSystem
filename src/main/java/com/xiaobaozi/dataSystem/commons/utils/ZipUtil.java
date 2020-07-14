package com.xiaobaozi.dataSystem.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

import com.xiaobaozi.dataSystem.commons.utils.StringUtil;

/**
 * 字符串压缩解压工具类
 * @author liuxb
 *
 */
public class ZipUtil {

	private static Log	log	= LogFactory.getLog(ZipUtil.class);
	private static final String DEFAULT_ENCODE = "utf-8";
	/**
	 * 使用gzip进行压缩
	 * @param primStr 压缩前的文本
	 * @return 返回压缩后的文本
	 * @throws Exception 
	 */
	public static String gzip(String primStr) throws Exception {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}
		primStr = convertChars(primStr,true);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes(DEFAULT_ENCODE));
		} catch (IOException e) {
			log.error("ZipUtil.gzip("+primStr+") throws:"+e);
			throw new Exception(e);
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String result = new BASE64Encoder().encode(out.toByteArray());
		//特殊字符处理
		if(null!=result){
			result = convertChars(result, true);
		}
		return result;
	}

	/**
	 * Description:使用gzip进行解压缩
	 * @param compressedStr 压缩后的文本
	 * @return 原文
	 * @throws Exception 
	 */
	public static String gunzip(String compressedStr) throws Exception {
		if (compressedStr == null) {
			return null;
		}
		String cstr = compressedStr;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			//特殊字符处理
			cstr = convertChars(cstr, false);
			compressed = new sun.misc.BASE64Decoder().decodeBuffer(cstr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			log.error("ZipUtil.gunzip("+compressedStr+") throws:"+e);
			throw new Exception(e);
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
				}
			}
		}
		decompressed = convertChars(decompressed,false);
		return decompressed;
	}
	
	/**
	 * 特殊字符处理
	 * @param str
	 * @param f
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String convertChars(String str, boolean f) throws UnsupportedEncodingException{
		if(null!= str){
			if(f){
				str =URLEncoder.encode(str,DEFAULT_ENCODE);
			}else{
				str = URLDecoder.decode(str, DEFAULT_ENCODE);
			}
		}
		return str;
	}

	/**
	 * 使用zip进行压缩
	 * 
	 * @param str 压缩前的文本
	 * @return 返回压缩后的文本
	 * @throws UnsupportedEncodingException 
	 */
	public static final String zip(String str) throws UnsupportedEncodingException {
		if (str == null || str.length() == 0) {
			return str;
		}
		str = convertChars(str,true);
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes(DEFAULT_ENCODE));
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
			if(null!=compressedStr){
				//过滤掉+号
				compressedStr = convertChars(compressedStr,true);
			}
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}

	/**
	 * 使用zip进行解压缩
	 * 
	 * @param compressed  压缩后的文本
	 * @return 解压后的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static final String unzip(String compressedStr) throws UnsupportedEncodingException {
		if (compressedStr == null) {
			return null;
		}
		
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			String str = convertChars(compressedStr,false);
			byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(str);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		decompressed = convertChars(decompressed,false);
		return decompressed;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String xml = "H4sIAAAAAAAAAHVSS07DMBC9ShQKu2A7SUlTTV1oWgQC0gpYoC6QUuqklpoEYoNA4gasWXIFWHEkEMdg0vQnPpKVzLw39rz5qHEcqXEs5UZts7Z7ubVb24iTsYqTR2jfp1PjThRK5lnLZNvUNNoczju9U3FzK5TmcCaSVGRaLS0O/SLhZ8MLIKUB3TJs0DsC0q0u6KjQ3UgLblPmWsy2aMNgfpNSICsOetn4Z5DtNOsYtGBgTxZTmQk%2BPACysGF/KpOJDnPu1xs2kKULQTSSGT8HUhmYbKGXrGoYREqJLMGC12xDP1yLlrlnIhZGqZj7QWjyz/enr7dnIDMc6UAUek6HhyZ3bZ9Sl/n4talHdxgqmsVw6MhCT3hJWRSr84BUCAYs8v6WcPiHhI/XFzz/SviRENvJLFqePxOS9Qb0r0URaZw8vooA/pflVi4MJjn2HGuzHHvH86nXQG6GwUk%2BklPBmUOduu04HkVqjsGxTKVeTZYZzG0ynGyF496sEndkEtwqnaelIhaEFPXPerUGQz%2BO5ZXAIePW1V18aAkAWdvVb/R7drHoAgAA";		
		/*String xml = "<?xml sdf?><dsa>asdfasdf</sdaf><asd>短发范德萨萨芬的</asfdd>";		
		String str;
		try {
			str = zip(xml);
			System.out.println("zipxml = "+str);
			String s = unzip(xml);
			System.out.println("xml = "+s);
//			String s = gunzip(xml);
//			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
