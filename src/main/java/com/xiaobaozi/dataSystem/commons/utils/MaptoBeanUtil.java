package com.xiaobaozi.dataSystem.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 将反射为对应的实体类型
 * 
 * @author xubiao
 * 
 *         2015-3-18
 */
public class MaptoBeanUtil {
	private static Logger log = Logger.getLogger(MaptoBeanUtil.class);

	/**
	 * 将属性之间的值进行互相填充
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static Object convertObject(Class<?> from, Class<?> to) {
		try {
			if (from != null && to == null) {
				Field[] field1 = from.getDeclaredFields();
				Field[] field2 = to.getDeclaredFields();
				for (int i = 0; i < field1.length; i++) {
					String method = field1[i].getName();
					for (int j = 0; j < field2.length; j++) {
						String method1 = field1[i].getName();
						if (method.equals(method1)) {// 相同则进行赋值操作
							Method method2 = from.getMethod("get" + method.substring(0, 1).toUpperCase() + method.substring(1),
									field1[i].getType());
							Object obj = method2.invoke(from);
							if (obj != null) {// 不是空节点则可以进行赋值，
								from.getMethod("set" + method1.substring(0, 1).toUpperCase() + method1.substring(1),
										field2[j].getType()).invoke(to, obj.toString());
								break;
							}
						}
					}
				}
			} else {
				log.warn("此方法不许对象之间一个为空!");
				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			log.error("进行数据转换出错：" + e.getMessage());
			return null;
		}
		return to;
	}

	public static <T> T convert(Map valueMap, Class<T> entityClass) {
		Map<String, ConvertEntryItem> convertItemCache = new HashMap<String, ConvertEntryItem>();
		ConvertEntryItem convertItem = convertItemCache.get(entityClass.getName());
		if (convertItem == null) {
			convertItem = ConvertEntryItem.createConvertItem(entityClass);
			convertItemCache.put(entityClass.getName(), convertItem);// 将属性赋予此类名
		}
		List<String> fieldNameList = convertItem.getFieldNameList();// 得到字段名
		Map<String, Method> fieldSetMethodMap = convertItem.getFieldSetMethodMap();// 得到set方法
		T entity = null;
		try {
			entity = entityClass.newInstance();
		} catch (Exception e) {
			log.error("创建实体类出错：" + e.getMessage());
			return null;
		}
		Object fieldValue = null;
		Method m;
		Class<?>[] parameterTypes;
		Object targetValue;
		// 遍历字段列表，分别调用每个字段的set方法
		for (String fieldName : fieldNameList) {
			fieldValue = valueMap.get(fieldName); // 得到对应字段的值
			if (fieldValue == null) {
				continue;
			}
			m = fieldSetMethodMap.get(fieldName);// 得到对应的set方法
			if (m == null) {
				continue;
			}
			// set方法的参数类型
			parameterTypes = m.getParameterTypes();
			if (parameterTypes.length != 1) {
				continue;
			}// 只支持单个参数的set方法
				// 如果第一个参数类型和当前类型相同，则直接使用
			if (parameterTypes[0].isAssignableFrom(fieldValue.getClass())) {
				targetValue = fieldValue;
			} else {
				// 转换当前的值为目标参数类型
				targetValue = ConvertFactory.convert(parameterTypes[0], fieldValue);
			}
			if (targetValue != null) {
				try {
					// 调用set方法进行赋值
					m.invoke(entity, targetValue);
				} catch (Exception e) {
					log.error("赋值错误:{method=" + m.getName() + ",value=" + targetValue + "}", e);
				}
			}
		}
		return entity;
	}

	static class ConvertEntryItem {
		private List<String> fieldNameList = new ArrayList<String>();
		private Map<String, Method> fieldSetMethodMap = new HashMap<String, Method>();

		private ConvertEntryItem() {
		}

		public List<String> getFieldNameList() {
			return fieldNameList;
		}

		public Map<String, Method> getFieldSetMethodMap() {
			return fieldSetMethodMap;
		}

		private void parseEntry(Class<?> cls) {
			List<Field> fieldList = getAllField(cls);
			Method m = null;
			String methodName;
			if (fieldList != null) {
				for (Field f : fieldList) {
					methodName = f.getName();
					methodName = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
					try {
						m = cls.getMethod(methodName, f.getType());
						if (m != null) {
							fieldNameList.add(f.getName());
							fieldSetMethodMap.put(f.getName(), m);
						}
					} catch (SecurityException e) {
						log.error("获取属性出错：" + e.getMessage());
					} catch (NoSuchMethodException e) {
						log.info("没有匹配的方法" + cls.getName() + ": " + methodName);
					}
				}
			}

		}

		/**
		 * 得到实体类的属性
		 * 
		 * @param cls
		 * @return
		 */
		public static ConvertEntryItem createConvertItem(Class<?> cls) {
			ConvertEntryItem ci = new ConvertEntryItem();
			ci.parseEntry(cls);
			return ci;
		}
	}

	/**
	 * 得到所有超类的属性
	 * 
	 * @return
	 */
	public static List<Field> getAllField(Class classed) {
		List<Field> list = new ArrayList<Field>();
		try {
			while (classed != null) {
				Collections.addAll(list, classed.getDeclaredFields());
				classed = classed.getSuperclass();
			}
		} catch (Exception e) {
			e.getMessage();
			log.error("得到属性出错：" + e.getMessage());
		}
		return list;
	}
}