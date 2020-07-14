package com.xiaobaozi.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 实体类映射日期类型转换
 * @author xubiao--2014-10-28
 *
 */
public class TransType {
	protected static Logger log = Logger.getLogger(TransType.class);
	
	public static Object typeReflect(Object model,Object obj) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组  
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
                String name = field[j].getName();//获取属性的名字  
                name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString();    //获取属性的类型
                if(type.equals("class java.util.Date")){//类型为日期类型的进行转换
                    Method m = model.getClass().getMethod("get"+name);                    
                    Date value = (Date) m.invoke(model);
                    if(value != null){
                    //进行数据转换
                    String dateString=DateUtil.convertDateToString(value);
                    //进行转换赋值
                    Method setValue=obj.getClass().getMethod("set"+name,new Class[] { String.class });
                    setValue.invoke(obj, dateString);
                    }
                }                
            }
        return obj;
    }
}
