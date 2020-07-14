package com.xiaobaozi.commons.vo;

public class SftpDown {
	private String ftpIp;//IP
	private String port;//端口
	private String userName;//用户名
	private String passwd;//密码
	private String downDir;//目录
	private String fileName;//文件名
	public String getFtpIp() {
		return ftpIp;
	}
	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getDownDir() {
		return downDir;
	}
	public void setDownDir(String downDir) {
		this.downDir = downDir;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
