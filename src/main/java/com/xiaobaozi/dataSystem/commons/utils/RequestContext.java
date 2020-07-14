package com.xiaobaozi.dataSystem.commons.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RequestContext {
	//访问客户端COOKIES数据
	public static String getCookie(HttpServletRequest request,String cookieName)
    {
		String cookieStr ="";
		Cookie[] cookies = request.getCookies();
        for(Cookie cookie :cookies ){
        	if(cookie.getName().equals(cookieName)){
        		try {
					cookieStr = URLDecoder.decode(cookie.getValue(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
        	}
        }
        return cookieStr;
    }
	//转换对象数据成为json数据，并返回给页面
	public static void returnObjectToJSON(HttpServletResponse response, Object object){
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		JSON json = JSONObject.fromObject(object);
        PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json);
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//转换对象数据成为json 数组，并返回给页面
		public static void returnObjectToJSONArray(HttpServletResponse response, Object object){
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(object);
	        PrintWriter out;
			try {
				out = response.getWriter();
				out.print(jsonArray);
		        out.flush();
		        out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
