package com.xiaobaozi.dataSystem.commons.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
/**
 * 通过配置文件获取对应接口地址
 * @author liaojiewei
 *
 */
public class PropertiesUtil{
	public static String key;
	public static String value;
	public static final Logger log = Logger.getLogger(PropertiesUtil.class);
	private static HashMap<String, Properties> propertiesMap = new HashMap();
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		PropertiesUtil.key = key;
	}
	public static String getValue() {
		return value;
	}
	public static void setValue(String value) {
		PropertiesUtil.value = value;
	}
	public void PropertiesUtil(){}
	public void PropertiesUtil(String key,String value){
		this.key = key;
		this.value = value;
	}
	public static Properties getProperties(String filePath){
		ClassPathResource  cr = new ClassPathResource(filePath);
		Properties properties = new Properties();
		try{
			properties.load(cr.getInputStream());
		}catch(IOException e){
			e.printStackTrace();
			log.error("Parse the properties failed.Please check it.");
		}
		return properties;
	}
	/**
	 * 根据pskey获得接口地址
	 * @param pskey 
	 * @return
	 */
	public synchronized static String getValue(String filePath,String pskey){
		if(!propertiesMap.containsKey(filePath)){
			Properties properties = new Properties();
			properties = getProperties(filePath);
			propertiesMap.put(filePath, properties);
		}
		String value = propertiesMap.get(filePath).getProperty(pskey);
		return value;
	}
	/**
	 * IO流获取外部文件字符
	 * @return
	 */
	public synchronized static String getFileTextValue(String filePath,String key){
		String value = "";
		Properties ps = new Properties();
		try{
			if(!propertiesMap.containsKey(filePath)){
				InputStream input = new BufferedInputStream(new FileInputStream(filePath));
				ps.load(input);
				propertiesMap.put(filePath, ps);
			}
			value = propertiesMap.get(filePath).getProperty(key);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("获取外部properties文件出错:",e);
		}
		
		return value;
	}
}
