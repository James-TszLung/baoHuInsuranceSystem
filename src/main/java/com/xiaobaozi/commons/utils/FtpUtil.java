package com.xiaobaozi.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;

public class FtpUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FtpUtil.class);

	/**
	 * 参考实例
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// File file = new File("D://36.txt");
	// FTPUtil ft = new FTPUtil();
	// Session s = ft.getSession(SFTPInfo.SFTP_REQ_HOST,
	// SFTPInfo.SFTP_DEFAULT_PORT, SFTPInfo.SFTP_REQ_USERNAME,
	// SFTPInfo.SFTP_REQ_PASSWORD);
	// Channel channel = ft.getChannel(s);
	// ChannelSftp sftp = (ChannelSftp)channel;
	// String upload = ft.uploadFile(sftp,"hot_Imgs",file);
	// System.out.println(upload);
	// ft.closeAll(sftp, channel, s); //关闭连接
	// }
	public Channel getChannel(Session session) {
		Channel channel = null;
		try {
			channel = session.openChannel("sftp");
			channel.connect();
			LOG.info("get Channel success!");
		} catch (JSchException e) {
			LOG.info("get Channel fail!", e);
		}
		return channel;
	}

	public Session getSession(String host, int port, String username, final String password) {
		Session session = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.connect();
			LOG.info("Session connected!");
		} catch (JSchException e) {
			LOG.info("get Channel failed!", e);
		}
		return session;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param sftp
	 * @param dir
	 *            文件夹名称
	 */
	public void mkdir(ChannelSftp sftp, String dir) {
		try {
			sftp.mkdir(dir);
			System.out.println("创建文件夹成功！");
		} catch (SftpException e) {
			System.out.println("创建文件夹失败！");
			e.printStackTrace();
		}
	}

	/**
	 * @param sftp
	 * @param dir
	 *            上传目录
	 * @param file
	 *            上传文件
	 * @return
	 */
	public String uploadFile(ChannelSftp sftp, String dir, InputStream input, String name) {
		String result = "";
		try {
			try {
				sftp.mkdir(dir);
			} catch (Exception e) {
			}
			sftp.cd(dir);
			// File file = new File("D://34.txt"); //要上传的本地文件
			if (input != null) {
				sftp.put(input, name);
				result = "上传成功！";
			} else {
				result = "文件为空！不能上传！";
			}
		} catch (Exception e) {
			LOG.info("上传失败！", e);
			result = "上传失败！";
		}
		return result;
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public InputStream download(String directory, String downloadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			return sftp.get(downloadFile);
		} catch (Exception e) {
//			LOG.info("下载失败！", e);
			return null;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public String delete(String directory, String deleteFile, ChannelSftp sftp) {
		String result = "";
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
			result = "删除成功！";
		} catch (Exception e) {
			result = "删除失败！";
			LOG.info("删除失败！", e);
		}
		return result;
	}

	private void closeChannel(Channel channel) {
		if (channel != null) {
			if (channel.isConnected()) {
				channel.disconnect();
			}
		}
	}

	private void closeSession(Session session) {
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	public void closeAll(ChannelSftp sftp, Channel channel, Session session) {
		try {
			closeChannel(sftp);
			closeChannel(channel);
			closeSession(session);
		} catch (Exception e) {
			LOG.info("closeAll", e);
		}
	}

	public static void main(String[] args) {
		// String ftpHost =
		// PropertiesUtil.getValue("/conf/properties/systemconf.properties",
		// "ftpHost");
		// String ftpUserName =
		// PropertiesUtil.getValue("/conf/properties/systemconf.properties",
		// "ftpUserName");
		// String ftpPassword =
		// PropertiesUtil.getValue("/conf/properties/systemconf.properties",
		// "ftpPassword");
		// int ftpPort =
		// Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties",
		// "ftpPort"));
		// String ftpPath = "/root/";
		// String localPath = "D:/tomcat/asdf.txt";
		//
		// FtpUtil ftpUtil = new FtpUtil();
		// Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName,
		// ftpPassword);
		// ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
		// ftpUtil.uploadFile(channel, ftpPath, new File(localPath));
		// ftpUtil.closeAll(channel, channel, session);

		// String uploadDir = System.getProperty("catalina.home") + "/" +
		// "images" + "/" + new SimpleDateFormat("yyyyMM").format(new Date());

		// 上传一个文件
		// try {
		// FileInputStream in = new FileInputStream(new File(localPath));
		// boolean test = FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword,
		// ftpPort, ftpPath, fileName, in);
		// System.out.println(test);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// System.out.println(e);
		// }
		//
		// // 在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
		// try {
		// InputStream input = new
		// ByteArrayInputStream("test ftp jyf".getBytes("GBK"));
		// boolean flag = FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword,
		// ftpPort, ftpPath, fileName, input);
		// System.out.println(flag);
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		//
		// // 下载一个文件
		// FtpUtil.downloadFtpFile(ftpHost, ftpUserName, ftpPassword, ftpPort,
		// ftpPath, localPath, fileName);
	}

}