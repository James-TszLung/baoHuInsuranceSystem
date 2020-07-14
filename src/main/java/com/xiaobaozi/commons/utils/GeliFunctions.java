package com.xiaobaozi.commons.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by Xiaoxiang on 2020/6/10.
 */
public class GeliFunctions {
    public GeliFunctions() {
    }

    public static String toJSONString(Object source) {
        return toJSONString(source, (String[])null);
    }

    public static String toJSONString(Object source, String[] props) {
        if(props != null && props.length != 0) {
            if(!(source instanceof Iterable)) {
                return JSON.toJSONString(toMap(source, props));
            } else {
                List<Map> list = new ArrayList();
                Iterator var3 = ((List)source).iterator();

                while(var3.hasNext()) {
                    Object o = var3.next();
                    list.add(toMap(o, props));
                }

                return JSON.toJSONString(list);
            }
        } else {
            return JSON.toJSONString(source);
        }
    }

    public static Map toMap(Object source, String[] props) {
        HashMap r = new HashMap();

        try {
            if(source instanceof Iterable) {
                return r;
            }

            Object data = source;
            String[] var4 = props;
            int var5 = props.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String name = var4[var6];
                String[] _names = new String[]{name, name};
                if(name.contains(">")) {
                    _names = name.split(">", 2);
                }

                Object value = getterValue(data, _names[0]);
                if(value != null) {
                    r.put(_names[1], value);
                }
            }
        } catch (Exception var10) {
            ;
        }

        return r;
    }

    private static Object getterValue(Object obj, String fieldName) {
        if(fieldName != null && fieldName.trim().length() != 0) {
            String tmpName = fieldName.trim();
            if(obj instanceof Map) {
                return ((Map)obj).get(tmpName);
            } else if(tmpName.indexOf(".") == -1) {
                Method getmethod = getterMethod(obj.getClass(), tmpName);
                if(getmethod == null) {
                    return null;
                } else {
                    try {
                        Object v = getmethod.invoke(obj, new Object[0]);
                        return v == null?null:(v instanceof Object[]? StringUtils.join((Object[])((Object[])v), "\n"):(v instanceof Collection?null:v));
                    } catch (Exception var7) {
                        return null;
                    }
                }
            } else {
                String firstName = tmpName.substring(0, tmpName.indexOf("."));
                String lastName = tmpName.substring(tmpName.indexOf(".") + 1);
                Method getmethod = getterMethod(obj.getClass(), firstName);
                if(getmethod == null) {
                    return null;
                } else {
                    try {
                        Object v = getmethod.invoke(obj, new Object[0]);
                        return v == null?null:(v instanceof Object[]?StringUtils.join((Object[])((Object[])v), "\n"):getterValue(v, lastName));
                    } catch (Exception var8) {
                        return null;
                    }
                }
            }
        } else {
            return null;
        }
    }

    private static Method getterMethod(Class type, String fieldName) {
        PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(type, fieldName);
        if(sourcePd != null && sourcePd.getReadMethod() != null) {
            Method readMethod = sourcePd.getReadMethod();
            if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                readMethod.setAccessible(true);
            }

            return readMethod;
        } else {
            return null;
        }
    }


}
