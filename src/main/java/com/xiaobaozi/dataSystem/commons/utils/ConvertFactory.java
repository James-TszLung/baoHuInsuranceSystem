package com.xiaobaozi.dataSystem.commons.utils;

import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 属性类型互转
 * @author xubiao
 *
 * 2015-3-18
 */
public class ConvertFactory {
	 private static Logger log = Logger.getLogger(ConvertFactory.class); 
	 public static <T> T convert(Class<T> clazz,Object val){  
		 ObjectConvert obj=new  ObjectConvert();
		 try{
		 if(clazz.getName().equals(Float.class.getName())){//浮点型
			  Object[] obj1={val}; 
			  Float[] f=obj.objectArrToFloaArrConvert(obj1);
			  if(f!=null){
				  return (T) f[0];
			  }
		 }else if(clazz.getName().equals(Integer.class.getName())){
			  Object[] obj1={val}; 
			  Integer[] f=obj.objectArrToIntArrConvert(obj1);
			  if(f!=null){
				  return (T) f[0];
			  }
		 }else if(clazz.getName().equals(Double.class.getName())){
			 Object[] obj1={val}; 
			 Double[] f=obj.objectArrToDoublArrConvert(obj1);
			  if(f!=null){
				  return (T) f[0];
			  }
		 }else if(clazz.getName().equals(Date.class.getName())){
			 Object[] obj1={val}; 
			 Date f=obj.objectToDateConvert(obj1);
			  if(f!=null){
				  return (T) f;
			  }
		 }else if(clazz.getName().equals(String.class.getName())){
			 Object[] obj1={val}; 
			 String[] f=obj.ObjectArrToStringArrConvert(obj1);
			  if(f!=null){
				  return (T) f[0];
			  }
		 }
		 }catch(Exception e){
			 e.getMessage();
			 log.error("进行数据转换出错："+e.getMessage());
		 }
        return null;
	 }
}
