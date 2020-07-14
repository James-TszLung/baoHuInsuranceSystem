package com.xiaobaozi.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.xiaobaozi.commons.vo.SftpDown;

/**
 * 
 * @author xub ---------2014-09-29
 *
 */
public class DownFtp{
	private  Logger logger = Logger.getLogger(DownFtp.class);
	private static SimpleDateFormat simple1=new SimpleDateFormat("yyyyMMdd");
	private ChannelSftp sftp = null; 
   /**
    * FTP下载----------下载文件
    * @return
    */
	public void downFileFromFtp(HttpServletResponse response,String ftpIp,String port,String ftpUserName,String ftpPassword) {
		FTPClient ftp =null;
		OutputStream os=null;
		try {
			int ch;
			ftp= new FTPClient();
            int reply;     
            ftp.connect(ftpIp,Integer.valueOf(port)); 
            ftp.login(ftpUserName, ftpPassword);
            reply = ftp.getReplyCode();     
         if (FTPReply.isPositiveCompletion(reply)) {     
            FTPFile[] fs = ftp.listFiles(); //遍历文件  
            logger.info("file size:"+fs.length);
            if(fs.length>0){
            for(FTPFile ff:fs){//将整个目录下载到文件中
            	//记录文件名
                if(ff.getName().equals("epcisahs-hbslb"+simple1.format(new Date()))){     
                	os=response.getOutputStream();
                	response.setContentType("text/plain;charset=GBK");
                	response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(ff.getName(), "UTF-8"));
                    ftp.retrieveFile(ff.getName(), os);
                }     
            }   
            }else{
            	ftp.disconnect();
            	PrintWriter pw=response.getWriter();
  			    pw.write("此目录下无文件,请联系系统管理员!");	
            }
            ftp.logout(); //注销登录 
         }else{
            	ftp.disconnect();
            	PrintWriter pw=response.getWriter();
  			    pw.write("无法登陆服务器,请联系系统管理员!");
            }
		} catch (Exception e) {
			logger.error("下载文件:失败.原因：", e);
			PrintWriter pw=null;
			try {
				pw = response.getWriter();
				pw.write("下载文件失败!");
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error("写文件失败");
			}
		}finally{
		  if(ftp!=null&&ftp.isConnected()){
			  try{
			  ftp.logout();
			  ftp.disconnect();
			  }catch(Exception e){
				  e.printStackTrace();
				  logger.info("断开连接："+e.getMessage());
			  }
		  }	
		  if(os!=null){
			  try{
			  os.flush();
			  os.close();
			  }catch(Exception e){
				  e.printStackTrace();
				  logger.info("流关闭出错："+e.getMessage());  
			  }
		  }
		}
	}
	
	//SFTP文件下载-----------连接
	 public void connect(String username,String password,String host,int port) {  
	        try {  
	            if(sftp != null){  
	            	logger.info("sftp is not null");  
	            }  
	            JSch jsch = new JSch();  
	            jsch.getSession(username, host, port);
	            Session sshSession = jsch.getSession(username, host, port);  
	            logger.info("Session created.---------------");  
	            sshSession.setPassword(password);  
	            Properties sshConfig = new Properties();  
	            sshConfig.put("StrictHostKeyChecking", "no");  
	            sshSession.setConfig(sshConfig);  
	            sshSession.connect();  
	            logger.info("Session connected.");  
	            logger.info("Opening Channel.");  
	            Channel channel = sshSession.openChannel("sftp");  
	            channel.connect();  
	            sftp = (ChannelSftp) channel;  
	            logger.info("Connected to " + host + ":主机连接上");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            logger.error("SFPT连接不成功-------异常");
	        }  
	    }  
	 /** 
     * Disconnect with server ------------SFTP断开连接
     */  
    public void disconnect() {  
        if(this.sftp != null){  
            if(this.sftp.isConnected()){  
                this.sftp.disconnect();  
            }else if(this.sftp.isClosed()){  
                System.out.println("sftp is closed already");  
            }  
        }  
    }  
    /**
     * 删除文件
     * @param sftpdown
     */
    public void deleteFile(SftpDown sftpdown){
        try {  
        	if(StringUtils.isNotBlank(sftpdown.getDownDir())){//带有目录则打开目录
        		//可以切换多个目录
        		String[] str=sftpdown.getDownDir().split("\\,");
        		for(int i=0;i<str.length;i++){
        			sftp.cd("/");
        			sftp.cd(str[i]);
        			Vector<LsEntry> finName=sftp.ls("*");
        			for(LsEntry file : finName){
        				String[] strDelete=sftpdown.getFileName().split("\\,");
        				for(int j=0;j<strDelete.length;j++){
	        				if(file.getFilename().equals(strDelete[j])){
	        					//删除文件
	        					sftp.rm(file.getFilename());
	        				}
        				}
        			}
        		}
        	}
        }catch(Exception e){
        	e.getMessage();
        	logger.error("删除文件出错："+e.getMessage());
        }
    }
    /** 
     * upload all the files to the server------寻找目录文件并进行下载 
     */  
    public void upload(HttpServletResponse response,SftpDown sftpdown) {  
    	OutputStream os=null;
    	boolean b=true;
        try {  
        	if(StringUtils.isNotBlank(sftpdown.getDownDir())){//带有目录则打开目录
        		sftp.cd(sftpdown.getDownDir());
        	}
        	Vector<LsEntry> finName=sftp.ls("*");
        	for(LsEntry file : finName){//得到此目录下的文件
        		//logger.info("获取的文件名为："+file.getFilename());
        		if(file.getFilename().equals(sftpdown.getFileName())){
        			os=response.getOutputStream();
        			//依据文件后缀进行判断下载何种类型文件
                	response.setContentType("application/vnd.ms-excel");
                	response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getFilename(), "UTF-8"));
                	sftp.get(file.getFilename(), os);
                	b=false;
        		}
        	}
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("SFTP出现异常："+e.getMessage());
        }  finally{
        	if(os!=null){
        		try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IO流关闭错误："+e.getMessage());
				}
        		
        	}
        	if(b){
        		PrintWriter pw=null;
        		try{
        		pw=response.getWriter();
        		response.setContentType("text/html--HTML;charset=GBK");
            	response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("excpetion.html", "GBK"));
            	pw.write("无此文件,请联系系统管理员！");
        		}catch(Exception e){
        			e.printStackTrace();
        			logger.error("写异常文件出错："+e.getMessage());
        		}finally{
        			pw.flush();
        			pw.close();
        		}
    		}
        }
    }   
    /** 
     * down all the files to the server------寻找目录文件并进行上传 
     */  
    public void updown(InputStream instream,SftpDown sftpdown) {  
    	OutputStream outstream=null;
        try {  
        	//得到文件流
        	if(StringUtils.isNotBlank(sftpdown.getDownDir())){//带有目录则打开目录
        		String [] strDir=sftpdown.getDownDir().split("\\/");
        			for(int i=0;i<strDir.length;i++){
        				Vector<LsEntry> finName=sftp.ls("*");//得到当前目录的文件列表
        				boolean b=false;
        				for(LsEntry file : finName){//存在就打开，不存在就建立
        					if(strDir[i].equals(file.getFilename())){//存在
        						b=true;
        						break;
        					}
        				}
        				if(!b){
        					sftp.mkdir(strDir[i]);
        				}
        				sftp.cd(strDir[i]);
        			}
        	}
        	outstream= sftp.put(sftpdown.getFileName());//将文件写入指定文件
        	 byte b[] = new byte[1024];
             int n;
             while ((n = instream.read(b)) != -1) {
                 outstream.write(b, 0, n);
             } 
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("SFTP出现异常："+e.getMessage());
            // 抛出异常通知调用程序上传文件失败
            throw new RuntimeException(e);
        }  finally{
        	if(outstream!=null){
        		try {
        			outstream.flush();
        			outstream.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IO流关闭错误："+e.getMessage());
				}
        	}	
			 if(instream!=null){
	        		try {
	        			instream.close();
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("IO流关闭错误："+e.getMessage());
					}
        	}
        }  
    }   
    
    public void downFileFromSftp(HttpServletResponse response,SftpDown sftpdown){
    	this.connect(sftpdown.getUserName(), sftpdown.getPasswd(), sftpdown.getFtpIp(),Integer.valueOf(sftpdown.getPort()));
        if(sftp!=null){
           this.upload(response,sftpdown); 
        }else{
           logger.error("无法获得连接！");
           PrintWriter pw=null;
       	 try{
       		pw=response.getWriter();
       		response.setContentType("text/html--HTML;charset=GBK");
           	response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("excpetion.html", "GBK"));
           	pw.write("无法连接文件服务器,请联系系统管理员！");
       		}catch(Exception e){
       			e.printStackTrace();
       			logger.error("写异常文件出错："+e.getMessage());
       		}finally{
       			pw.flush();
       			pw.close();
       		}
        }
        this.disconnect();  
    }
    public void upFileFromSftp(InputStream instream,SftpDown sftpdown) {
        this.connect(sftpdown.getUserName(), sftpdown.getPasswd(), sftpdown.getFtpIp(),Integer.valueOf(sftpdown.getPort()));
        if(sftp!=null){
        this.updown(instream,sftpdown); 
        }else{
     	  logger.error("无法获得连接！"); 
        }
        this.disconnect();  
 }
    /**
     * 删除文件
     * @param sftpdown
     */
    public void deleteFileFromSftp(SftpDown sftpdown){
    	this.connect(sftpdown.getUserName(), sftpdown.getPasswd(), sftpdown.getFtpIp(),Integer.valueOf(sftpdown.getPort()));
        if(sftp!=null){
        this.deleteFile(sftpdown); 
        }else{
     	  logger.error("无法获得连接！"); 
        }
        this.disconnect();  
    }
   /* public static void main(String[] args) {
         try {
			InputStream instream = new FileInputStream(new File("d:/dddd.txt"));
			DownFtp df=new DownFtp();
			String ftpIP="192.168.195.23";
			String ftpPort="22";
			String ftpUserName="testFtp1";
			String ftpPassword="xubiao";
			SftpDown sftpdown=new SftpDown();
			sftpdown.setFtpIp(ftpIP);
			sftpdown.setPort(ftpPort);
			sftpdown.setUserName(ftpUserName);
			sftpdown.setPasswd(ftpPassword);
			sftpdown.setDownDir("upload1/xubiao");
			sftpdown.setFileName("dddd.txt");
			df.upFileFromSftp(instream, sftpdown);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/
}
