package com.xiaobaozi.dataSystem.commons.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
/**
 * 复制对象类
 * @author xubiao
 *
 * 2015-1-12
 */
public class BeantoBean {
	protected static Logger log = Logger.getLogger(BeantoBean.class);
	public static Object convertBean2Bean(Object from, Object to) throws Exception{   
	     try {   
	        BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());   
		    PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();   
		          for (PropertyDescriptor p : ps) {   
	                  Method getMethod = p.getReadMethod();   
	                  Method setMethod = p.getWriteMethod();   
		              if (getMethod != null && setMethod != null) {   
		                  try {   
		                    Object result = getMethod.invoke(from);   
		                    setMethod.invoke(to, result);   
		                   } catch (Exception e) {   
			                   e.getMessage();
			                   log.error("复制对象出错");
		                     continue;   
		                  }   
			               }   
			           }   
		       } catch (Exception e) {   
			          e.printStackTrace();   
		       }   
		 
		       return to;   
	    }   
}
