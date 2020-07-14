package com.xiaobaozi.commons.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @author xubiao---2014-10-23
 * 下载模板公用类
 *
 */
public class DownModuleExcel {
	protected static Logger log = Logger.getLogger(DownModuleExcel.class);
	/**
	 * 投保模板下载
	 * @param request
	 * @param response
	 */
	public void downModule(HttpServletRequest request,HttpServletResponse response,String downFile,String fileName){
		response.setContentType("text/html;charset=UTF-8");   
	    BufferedInputStream in = null;  
	    BufferedOutputStream out = null;  
	    try {  
	    	request.setCharacterEncoding("UTF-8");  
	        File f = new File(downFile); //window路径下
	        response.setContentType("application/vnd.ms-excel"); 
	        try {
	        	 fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
				 response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx"); 
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				log.error(fileName+"模板下载转换格式错误:"+e1.getMessage());
			}  
	        response.setHeader("Content-Length",String.valueOf(f.length()));  
	        in = new BufferedInputStream(new FileInputStream(f));  
	        out = new BufferedOutputStream(response.getOutputStream()); 
	        byte[] b=new byte[1024];
	        while (-1 != (in.read(b,0,b.length))) {  
	            out.write(b);  
	            out.flush();
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();
	        log.error("无模板下载:"+e.getMessage());
	    } finally{
	    	//将流刷新
	    	 try {
	    		 if(in!=null){
	    			 in.close();
	    		 }
	    		 if(out!=null){
	    		 out.flush();
	    		 out.close(); 
	    		 }
			} catch (IOException e) {
				e.printStackTrace();
				log.error("写EXCEL失败:"+e.getMessage());
			}     	
	    }
	  }
	
}
