package com.xiaobaozi.dataSystem.commons.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 类型具体实现
 * @author xubiao
 *
 * 2015-3-18
 */
public class ObjectConvert<S,T>{
	 private static Logger log = Logger.getLogger(ObjectConvert.class); 
	 /**
	  * 转double类型
	  * @param source
	  * @return
	  */
	 public Double[] objectArrToDoublArrConvert(Object[] source) {  
	        if(source == null) return null;  
	        Double[] res = new Double[source.length];  
	        for(int i=0;i<source.length;i++){  
	            try {  
	                res[i] = Double.parseDouble(source[i].toString());  
	            } catch (NumberFormatException e) {  
	            	log.error("进行数据转换出错："+e.getMessage());
	                return null;  
	            }  
	        }  
	        return res;  
	 }  
	 /**
	  * 转BigDecimal类型
	  * @param source
	  * @return
	  */
	 public BigDecimal[] objectArrToBigDeConvert(Object[] source) {  
	        if(source == null) return null;  
	        BigDecimal[] res = new BigDecimal[source.length];  
	        for(int i=0;i<source.length;i++){  
	            try { 
	                res[i] = new BigDecimal(source[i].toString());  
	            } catch (NumberFormatException e) {  
	            	log.error("进行数据转换出错："+e.getMessage());
	                return null;  
	            }  
	        }  
	        return res;  
	 }  
	 /**
	  * 转float类型
	  * @param source
	  * @return
	  */
	 public Float[] objectArrToFloaArrConvert(Object[] source) {  
	        if(source == null) return null;  
	        Float[] res = new Float[source.length];  
	        for(int i=0;i<source.length;i++){  
	            try {  
	            	 res[i] = Float.parseFloat(source[i].toString()); 
	            } catch (NumberFormatException e) {  
	            	log.error("进行数据转换出错："+e.getMessage());
	                return null;  
	            }  
	        }  
	        return res;  
	 }  
	 /**
	  * 转Integer类型
	  * @param source
	  * @return
	  */
	 public Integer[] objectArrToIntArrConvert(Object[] source) {  
	        if(source == null) return null;  
	        Integer[] res = new Integer[source.length];  
	        for(int i=0;i<source.length;i++){  
	            try {  
	            	res[i] = Integer.parseInt(source[i].toString());   
	            } catch (NumberFormatException e) {  
	            	log.error("记性数据转换出错："+e.getMessage());
	                return null;  
	            }  
	        }  
	        return res;  
	 }  
	 /**
	  * 转字符串类型类型
	  * @param source
	  * @return
	  */
	 public String[] ObjectArrToStringArrConvert(Object[] source) {  
	        if(source == null) return null;  
	        String[] res = new String[source.length];  
	        for(int i=0;i<source.length;i++){  
	            try {  
	            	res[i] = source[i].toString();   
	            } catch (Exception e) {  
	            	log.error("记性数据转换出错："+e.getMessage());
	                return null;  
	            }  
	        }  
	        return res;  
	 }  
	 /**
	  * 转Date类型
	  * @param source
	  * @return
	  */
	 public Date objectToDateConvert(Object[] source) {  
	        Object[][] patterns = {  
	                {Pattern.compile("^\\d+-\\d+-\\d+$"),"yyyy-MM-dd"},  
	                {Pattern.compile("^\\d+-\\d+-\\d+ \\d+:\\d+$"),"yyyy-MM-dd HH:mm"},  
	                {Pattern.compile("^\\d+-\\d+-\\d+ \\d+:\\d+:\\d+$"),"yyyy-MM-dd HH:mm:ss"},  
	                {Pattern.compile("^\\d+/\\d+/\\d+$"),"yyyy/MM/dd"},  
	                {Pattern.compile("^\\d+/\\d+/\\d+ \\d+:\\d+$"),"yyyy/MM/dd HH:mm"},  
	                {Pattern.compile("^\\d+/\\d+/\\d+ \\d+:\\d+:\\d+$"),"yyyy/MM/dd HH:mm:ss"}  
	        };  
	        if(source == null) return null;  
	        String val = source.toString();  
	        val = val.trim();  
	        String format = null;  
	        Pattern p;  
	        for(Object[] item:patterns){  
	            p = (Pattern)item[0];  
	            if(p.matcher(val).matches()){  
	                format = item[1].toString();  
	                break;  
	            }  
	        }  
	          
	        if(format == null) {  
	        	log.error("记性数据转换出错,值为空");
	            return null;  
	        }  
	        try {  
	            return new SimpleDateFormat(format).parse(val);  
	        } catch (Exception e) {  
	        	log.error("记性数据转换出错,值为空");  
	            return null;  
	        }   
	 }  
	
}
