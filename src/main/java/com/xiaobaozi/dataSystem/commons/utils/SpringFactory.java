package com.xiaobaozi.dataSystem.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 得到服务BEAN
 * @author xubiao
 *
 * 2014-11-23
 */
public class SpringFactory implements ApplicationContextAware{
	public static ApplicationContext context;
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		context = arg0;
	}

	public static Object getBean(String beanId){
		Object object = null;
		object = context.getBean(beanId);
		return object;
	}
}
