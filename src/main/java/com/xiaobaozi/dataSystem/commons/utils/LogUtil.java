package com.xiaobaozi.dataSystem.commons.utils; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

  
/** 
 * @author pcj 
 */
public class LogUtil {
	protected static Logger log = Logger.getLogger(LogUtil.class);
	private long successNum=0;//导入成功数量
    public static Map readTxtFile(String filePath){ 
    	 Map error=new HashMap();
    	 long errNum=0;
    	 long sucNum=0;
         try { 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ //判断文件是否存在 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file));//考虑到编码格式 
                    BufferedReader bufferedReader = new BufferedReader(read); 
                    String lineTxt = null;
                    String newStr=null;
                    int i=1;
                    while((lineTxt = bufferedReader.readLine()) != null){ 
                    	if(lineTxt.indexOf("拒绝的逻辑记录总数:")!=-1){
                    		errNum=Long.valueOf(lineTxt.substring(lineTxt.indexOf("拒绝的逻辑记录总数:")+10).trim());
                    	}
                    	if(lineTxt.indexOf("读取的逻辑记录总数:")!=-1){
                    		sucNum=Long.valueOf(lineTxt.substring(lineTxt.indexOf("读取的逻辑记录总数:")+10).trim());
                    	}
                    } 
                    read.close(); 
        }else{ 
        	 log.error("找不到指定的文件"); 
        } 
        } catch (Exception e) { 
        	 log.error("读取文件内容出错"); 
            e.printStackTrace(); 
        } 
        error.put("errNum", errNum);
        error.put("sucNum", sucNum);
        if(Integer.valueOf(error.get("sucNum").toString())<=0){
        	error.put("uploadStatus", 2);
        }else{
        	error.put("uploadStatus", 1);
        }
        error.put("ototalcount",error.get("sucNum"));
        return error;
      
    } 
    public  boolean successLoad(String filePath){ 
    	boolean b=false;
    	long errNum=0;
        try { 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ //判断文件是否存在 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file));//考虑到编码格式 
                    BufferedReader bufferedReader = new BufferedReader(read); 
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){ 
                    	if(lineTxt.indexOf("拒绝的逻辑记录总数:")!=-1){
                    		errNum=Long.valueOf(lineTxt.substring(lineTxt.indexOf("拒绝的逻辑记录总数:")+10).trim());
                    	}
                    	if(lineTxt.indexOf("读取的逻辑记录总数:")!=-1){
                    		successNum=Long.valueOf(lineTxt.substring(lineTxt.indexOf("读取的逻辑记录总数:")+10).trim());
                    	}
                    } 
                    read.close(); 
        }else{ 
            log.error("找不到指定的文件"); 
        } 
        } catch (Exception e) { 
        	log.error("读取文件内容出错:"+e.getMessage()); 
            e.printStackTrace(); 
        }
        if(errNum==0&&successNum>0){
        	b=true;
        }
        return b;
      
    }  
	public long getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(long successNum) {
		this.successNum = successNum;
	}
	
      
      
  
} 
