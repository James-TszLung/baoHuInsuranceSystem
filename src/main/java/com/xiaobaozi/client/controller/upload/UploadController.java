package com.xiaobaozi.client.controller.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.utils.FtpUtil;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;
import com.xiaobaozi.dataSystem.systemManager.service.PictureInfoService;

/**
 * 图片上传
 * 
 * @author liuxh
 * 
 */
@Controller
@RequestMapping("uploadFile")
public class UploadController extends BaseController {
	@Resource
	private PictureInfoService pictureInfoService;

	/**
	 * 跳转到图片列表
	 */
	@RequestMapping("list.htm")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("main/uploadFile/list");
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadFile.htm")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo p = new PictureInfo();

		String uploadDir = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
				+ new SimpleDateFormat("yyyyMM").format(new Date());
		uploadDir = uploadDir.replace("\\", "/");

		String filePath = uploadDir;
		String fileName = UUIDUtil.getUUID().toString() + ".png";

		// 参数序列化
		String image = request.getParameter("images"); // 拿到字符串格式的图片
		String picId = request.getParameter("picid"); // 图片是否为修改
		String picNamess = request.getParameter("picName"); // 图片名称

		// 只允许image
		String header = "data:image";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的

			// 去掉头部
			image = imageArr[1];
			// image = image.substring(header.length());

			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes
				String imgFilePath = filePath + "/" + fileName; // 指定图片要存放的位置
				File targetFile = new File(filePath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}

				InputStream input = new ByteArrayInputStream(decodedBytes);

				String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
				String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
				String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
				String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
						+ new SimpleDateFormat("yyyyMM").format(new Date());
				int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));

				FtpUtil ftpUtil = new FtpUtil();
				Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
				ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
				ftpUtil.uploadFile(channel, ftpPath, input, fileName);
				ftpUtil.closeAll(channel, channel, session);

				// FileOutputStream out = new FileOutputStream(imgFilePath); //
				// 新建一个文件输出器，并为它指定输出位置imgFilePath
				// out.write(decodedBytes); // 利用文件输出器将二进制格式decodedBytes输出
				// out.close(); // 关闭文件输出器
				System.out.println("上传文件成功！");
				String getImagesUrl = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "getImagesUrl");
				if (picId != null && !"".equals(picId)) {
					File file = new File(filePath + "/" + picNamess);
					if (file.exists()) {
						file.delete();
					}
					p.setId(picId);
					p.setUpdateTime(new Date());
					p.setPictureName(fileName);
					p.setProductDir(filePath);
					p.setUrl(getImagesUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
					pictureInfoService.update(p);

				} else {
					p.setId(UUIDUtil.getUUID().toString());
					p.setCreateTime(new Date());
					p.setPictureName(fileName);
					p.setProductDir(filePath);
					p.setSort(0);
					p.setUrl(getImagesUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
					pictureInfoService.insert(p);

				}

				str.put("state", true);
				str.put("picName", p.getPictureName());
				str.put("pictureid", p.getId());

				JsonUtil.ajaxReturnByHTML(response, str);

			} catch (Exception e) {
				str.put("state", false);
				JsonUtil.ajaxReturnByHTML(response, str);
			}
		}
	}

	@RequestMapping("uploadFile2.htm")
	public void uploadFile2(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo p = new PictureInfo();

		String uploadDir = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
				+ new SimpleDateFormat("yyyyMM").format(new Date());
		uploadDir = uploadDir.replace("\\", "/");

		String filePath = uploadDir;
		String fileName = UUIDUtil.getUUID().toString() + ".png";

		// 参数序列化
		String image = request.getParameter("images2"); // 拿到字符串格式的图片
		String picId = request.getParameter("picid2"); // 图片是否为修改
		String picNamess = request.getParameter("picName2"); // 图片名称

		String PicName = fileName;
		System.out.println(PicName);

		// 只允许image
		String header = "data:image";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的

			// 去掉头部
			image = imageArr[1];
			// image = image.substring(header.length());

			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes
				String imgFilePath = filePath + "/" + PicName; // 指定图片要存放的位置

				InputStream input = new ByteArrayInputStream(decodedBytes);

				String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
				String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
				String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
				String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
						+ new SimpleDateFormat("yyyyMM").format(new Date());
				int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));

				FtpUtil ftpUtil = new FtpUtil();
				Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
				ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
				ftpUtil.uploadFile(channel, ftpPath, input, fileName);
				ftpUtil.closeAll(channel, channel, session);
				//
				// File targetFile = new File(filePath);
				// if (!targetFile.exists()) {
				// targetFile.mkdirs();
				// }
				//
				// FileOutputStream out = new FileOutputStream(imgFilePath); //
				// 新建一个文件输出器，并为它指定输出位置imgFilePath
				// out.write(decodedBytes); // 利用文件输出器将二进制格式decodedBytes输出
				// out.close(); // 关闭文件输出器
				System.out.println("上传文件成功！");

				if (picId != null && !"".equals(picId)) {
					File file = new File(filePath + "/" + picNamess);
					if (file.exists()) {
						file.delete();
					}
					p.setId(picId);
					p.setUpdateTime(new Date());
					p.setPictureName(fileName);
					p.setProductDir(filePath);
					pictureInfoService.update(p);

				} else {
					p.setId(UUIDUtil.getUUID().toString());
					p.setCreateTime(new Date());
					p.setPictureName(fileName);
					p.setProductDir(filePath);
					p.setSort(0);
					pictureInfoService.insert(p);

				}

				str.put("state", true);
				str.put("picName", p.getPictureName());
				str.put("pictureid", p.getId());

				JsonUtil.ajaxReturnByHTML(response, str);

			} catch (Exception e) {
				str.put("state", false);
				JsonUtil.ajaxReturnByHTML(response, str);
			}
		}
	}

	@RequestMapping("uploadFileActivity.htm")
	public void uploadFileActivity(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo p = new PictureInfo();

		String uploadDir = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
				+ new SimpleDateFormat("yyyyMM").format(new Date());
		uploadDir = uploadDir.replace("\\", "/");

		String filePath = uploadDir;
		String fileName = UUIDUtil.getUUID().toString() + ".png";

		// 参数序列化
		String image = request.getParameter("imagesActivity"); // 拿到字符串格式的图片
		String picId = request.getParameter("picid2"); // 图片是否为修改
		String picNamess = request.getParameter("picName2"); // 图片名称

		String PicName = fileName;
		System.out.println(PicName);

		// 只允许image
		String header = "data:image";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的

			// 去掉头部
			image = imageArr[1];
			// image = image.substring(header.length());

			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes
				String imgFilePath = filePath + "/" + PicName; // 指定图片要存放的位置

				InputStream input = new ByteArrayInputStream(decodedBytes);

				String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
				String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
				String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
				String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
						+ new SimpleDateFormat("yyyyMM").format(new Date());
				int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));

				FtpUtil ftpUtil = new FtpUtil();
				Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
				ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
				ftpUtil.uploadFile(channel, ftpPath, input, fileName);
				ftpUtil.closeAll(channel, channel, session);
				//
				// File targetFile = new File(filePath);
				// if (!targetFile.exists()) {
				// targetFile.mkdirs();
				// }
				//
				// FileOutputStream out = new FileOutputStream(imgFilePath); //
				// 新建一个文件输出器，并为它指定输出位置imgFilePath
				// out.write(decodedBytes); // 利用文件输出器将二进制格式decodedBytes输出
				// out.close(); // 关闭文件输出器
				System.out.println("上传文件成功！");
				String getImagesUrl = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "getImagesUrl");
				if (picId != null && !"".equals(picId)) {
					File file = new File(filePath + "/" + picNamess);
					if (file.exists()) {
						file.delete();
					}
					p.setId(picId);
					p.setUpdateTime(new Date());
					p.setPictureName(fileName);
					p.setProductDir(filePath);
					p.setUrl(getImagesUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
					pictureInfoService.update(p);

				} else {
					p.setId(UUIDUtil.getUUID().toString());
					p.setCreateTime(new Date());
					p.setPictureName(fileName);
					p.setProductDir(filePath);
					p.setSort(0);
					p.setUrl(getImagesUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
					pictureInfoService.insert(p);

				}

				str.put("state", true);
				str.put("picName", p.getPictureName());
				str.put("pictureid", p.getId());

				JsonUtil.ajaxReturnByHTML(response, str);

			} catch (Exception e) {
				str.put("state", false);
				JsonUtil.ajaxReturnByHTML(response, str);
			}
		}
	}

	/**
	 * 
	 * 图片集，分开写
	 */
	@RequestMapping("uploadFilels.htm")
	public void uploadFilels(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo p = new PictureInfo();

		String uploadDir = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
				+ new SimpleDateFormat("yyyyMM").format(new Date());
		uploadDir = uploadDir.replace("\\", "/");
		String filePath = uploadDir;
		String fileName = UUIDUtil.getUUID().toString() + ".png";

		// 参数序列化
		String image = request.getParameter("imagels"); // 拿到字符串格式的图片

		String PicName = fileName;
		System.out.println(PicName);

		// 只允许image
		String header = "data:image";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的

			// 去掉头部
			image = imageArr[1];
			// image = image.substring(header.length());

			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes
				InputStream input = new ByteArrayInputStream(decodedBytes);

				String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
				String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
				String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
				String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPath") + "/"
						+ new SimpleDateFormat("yyyyMM").format(new Date());
				int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));

				FtpUtil ftpUtil = new FtpUtil();
				Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
				ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
				ftpUtil.uploadFile(channel, ftpPath, input, fileName);
				ftpUtil.closeAll(channel, channel, session);
				// String imgFilePath = filePath + "/" + PicName; // 指定图片要存放的位置
				// File targetFile = new File(filePath);
				// if (!targetFile.exists()) {
				// targetFile.mkdirs();
				// }
				//
				// FileOutputStream out = new FileOutputStream(imgFilePath); //
				// 新建一个文件输出器，并为它指定输出位置imgFilePath
				// out.write(decodedBytes); // 利用文件输出器将二进制格式decodedBytes输出
				// out.close(); // 关闭文件输出器
				System.out.println("上传文件成功！");

				// 保存图片的操作
				p.setId(UUIDUtil.getUUID().toString());
				p.setCreateTime(new Date());
				p.setPictureName(fileName);
				p.setProductDir(filePath);
				p.setSort(0);
				pictureInfoService.insert(p);

				str.put("state", true);
				str.put("pictureid", p.getId());
				str.put("picName", p.getPictureName());
				JsonUtil.ajaxReturnByHTML(response, str);

			} catch (Exception e) {
				str.put("state", false);
				JsonUtil.ajaxReturnByHTML(response, str);
			}
		}
	}
	@RequestMapping("uploadFileItlsProduct.htm")
	public void uploadFileItlsProduct(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo p = new PictureInfo();

		String uploadDir = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathFile") + "/"
				+ new SimpleDateFormat("yyyyMM").format(new Date());
		uploadDir = uploadDir.replace("\\", "/");
		String filePath = uploadDir;
		String fileName = UUIDUtil.getUUID().toString() + ".pdf";

		// 参数序列化
		String image = request.getParameter("imageItls"); // 拿到字符串格式的图片

		String PicName = fileName;
		System.out.println(PicName);

		// 只允许image
		String header = "application/pdf";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的

			// 去掉头部
			image = imageArr[1];
			// image = image.substring(header.length());

			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes

				InputStream input = new ByteArrayInputStream(decodedBytes);

				String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
				String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
				String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
				String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathFile") + "/"
						+ new SimpleDateFormat("yyyyMM").format(new Date());
				int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));
				String getFileUrl = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "getFileUrl");
				FtpUtil ftpUtil = new FtpUtil();
				Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
				ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
				ftpUtil.uploadFile(channel, ftpPath, input, fileName);
				ftpUtil.closeAll(channel, channel, session);
				// String imgFilePath = filePath + "/" + PicName; // 指定图片要存放的位置
				// File targetFile = new File(filePath);
				// if (!targetFile.exists()) {
				// targetFile.mkdirs();
				// }
				//
				// FileOutputStream out = new FileOutputStream(imgFilePath); //
				// 新建一个文件输出器，并为它指定输出位置imgFilePath
				// out.write(decodedBytes); // 利用文件输出器将二进制格式decodedBytes输出
				// out.close(); // 关闭文件输出器
				System.out.println("上传文件成功！");

				// 保存图片的操作
				p.setId(UUIDUtil.getUUID().toString());
				p.setCreateTime(new Date());
				p.setPictureName(fileName);
				p.setProductDir(filePath);
				p.setUrl(getFileUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
				p.setSort(0);
				pictureInfoService.insert(p);

				str.put("state", true);
				str.put("pictureid", p.getId());
				str.put("picName", p.getPictureName());
				str.put("Url", p.getUrl());
				JsonUtil.ajaxReturnByHTML(response, str);

			} catch (Exception e) {
				str.put("state", false);
				JsonUtil.ajaxReturnByHTML(response, str);
			}
		}
	}

	/**
	 * 查看图片
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("pictureView.htm")
	public void pictureView(HttpServletRequest request, HttpServletResponse response) {

		String prictureId = request.getParameter("id");
		PictureInfo pictureInfo = pictureInfoService.findById(prictureId);
		if (pictureInfo == null) {
			return;
		}
		InputStream inputStream = null;
		String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
		String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
		String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
		int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));

		FtpUtil ftpUtil = new FtpUtil();
		Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
		ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);

		try {
			inputStream = ftpUtil.download(pictureInfo.getProductDir(), pictureInfo.getPictureName(), channel);
		} catch (Exception e) {
			return;
		}

		BufferedInputStream bis = new BufferedInputStream(inputStream);
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		byte[] buffer = new byte[1024];
		int length = 0;

		try {
			while ((length = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
				ftpUtil.closeAll(channel, channel, session);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传视频
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadFileVideoItls.htm")
	public void uploadFileVideoItls(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("okok");
		Map<String, Object> str = new HashMap<String, Object>();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		PictureInfo p = new PictureInfo();
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				try {
					if (file != null) {

						InputStream input = new ByteArrayInputStream(file.getBytes());
						String name = file.getOriginalFilename();
						String suffix = name.substring(name.lastIndexOf(".") + 1);
						String fileName = UUIDUtil.getUUID().toString() + "." + suffix;
						String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
						String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
						String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
						String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathVideo") + "/"
								+ new SimpleDateFormat("yyyyMM").format(new Date());
						int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));
						System.out.println("file==" + file);
						FtpUtil ftpUtil = new FtpUtil();
						Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
						ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
						ftpUtil.uploadFile(channel, ftpPath, input, fileName);
						ftpUtil.closeAll(channel, channel, session);
						String getVideoUrl = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "getVideoUrl");
						// 保存视频的操作
						p.setId(UUIDUtil.getUUID().toString());
						p.setCreateTime(new Date());
						p.setPictureName(fileName);
						p.setName(name);
						p.setProductDir(new SimpleDateFormat("yyyyMM").format(new Date()));
						p.setSort(0);
						p.setUrl(getVideoUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
						pictureInfoService.insert(p);
						str.put("state", true);
						str.put("videoId", p.getId());
						str.put("videoName", name);
						JsonUtil.ajaxReturnByHTML(response, str);
					}
				} catch (Exception e) {
					e.printStackTrace();
					str.put("state", false);
					JsonUtil.ajaxReturnByHTML(response, str);
				}
			}
		}
	}
	
	
	/**
	 * 上传视频
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadFilelsByProduct.htm")
	public void uploadFilelsByProduct(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("okok");
		Map<String, Object> str = new HashMap<String, Object>();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		PictureInfo p = new PictureInfo();
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				try {
					if (file != null) {

						InputStream input = new ByteArrayInputStream(file.getBytes());
						String name = file.getOriginalFilename();
						String suffix = name.substring(name.lastIndexOf(".") + 1);
						String fileName = UUIDUtil.getUUID().toString() + "." + suffix;
						String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
						String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
						String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
						String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathFile") + "/"
								+ new SimpleDateFormat("yyyyMM").format(new Date());
						int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));
						System.out.println("file==" + file);
						FtpUtil ftpUtil = new FtpUtil();
						Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
						ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
						ftpUtil.uploadFile(channel, ftpPath, input, fileName);
						ftpUtil.closeAll(channel, channel, session);
						String getVideoUrl = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "getFileUrl");
						// 保存视频的操作
						p.setId(UUIDUtil.getUUID().toString());
						p.setCreateTime(new Date());
						p.setPictureName(fileName);
						p.setName(name);
						p.setProductDir(new SimpleDateFormat("yyyyMM").format(new Date()));
						p.setSort(0);
						p.setUrl(getVideoUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + fileName);
						pictureInfoService.insert(p);
						str.put("state", true);
						str.put("videoId", p.getId());
						str.put("videoName", name);
						JsonUtil.ajaxReturnByHTML(response, str);
					}
				} catch (Exception e) {
					e.printStackTrace();
					str.put("state", false);
					JsonUtil.ajaxReturnByHTML(response, str);
				}
			}
		}
	}

	@RequestMapping("deleteFileVideoItls.htm")
	public void deleteFileVideoItls(HttpServletRequest request, HttpServletResponse response) {
		String videoId = request.getParameter("videoId");

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo pictureInfo = pictureInfoService.findById(videoId);
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		if (pictureInfo != null) {
			String videoSaveSRC = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "xcx.mp3SaveSRC");
			String fileName = videoSaveSRC + pictureInfo.getProductDir() + "/" + pictureInfo.getPictureName();
			String filePath = videoSaveSRC + pictureInfo.getProductDir();
			// File file = new File(fileName);
			// if (!file.exists()) {
			// str.put("state", false);
			// JsonUtil.ajaxReturnByHTML(response, str);
			// } else {
			// if (file.isFile()) {
			if (deleteFile(filePath, fileName)) {
				try {
					if (pictureInfoService.delete(pictureInfo)) {
						if (StringUtils.isNotBlank(id)) {
							Map<String, Object> updateMap = new HashMap<String, Object>();
							updateMap.put("id", id);
							if ("1".equals(type)) {
								updateMap.put("chineseVoice", " ");
							} else {
								updateMap.put("englishVoice", " ");
							}

							// navigationService.updateByMap(updateMap);
						}
						str.put("state", true);
						JsonUtil.ajaxReturnByHTML(response, str);
					}
				} catch (DaoException e) {
					str.put("state", false);
					JsonUtil.ajaxReturnByHTML(response, str);
					e.printStackTrace();
				}
				// } else {
				// str.put("state", false);
				// JsonUtil.ajaxReturnByHTML(response, str);
				// }
				// } else {
				// str.put("state", false);
				// JsonUtil.ajaxReturnByHTML(response, str);
				// }
			}
		} else {
			str.put("state", false);
			JsonUtil.ajaxReturnByHTML(response, str);
		}
	}

	@RequestMapping("deleteFile.htm")
	public static boolean deleteFile(String filePath, String fileName) {

		String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
		String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
		String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
		int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));

		FtpUtil ftpUtil = new FtpUtil();
		Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
		ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
		ftpUtil.delete(filePath, fileName, channel);
		ftpUtil.closeAll(channel, channel, session);
		return true;
		// File file = new File(fileName);
		// // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		// if (file.exists() && file.isFile()) {
		// if (file.delete()) {
		// System.out.println("删除单个文件" + fileName + "成功！");
		// return true;
		// } else {
		// System.out.println("删除单个文件" + fileName + "失败！");
		// return false;
		// }
		// } else {
		// System.out.println("删除单个文件失败：" + fileName + "不存在！");
		// return false;
		// }
	}

	@RequestMapping("springUpload.htm")
	public void springUpload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo p = new PictureInfo();
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				try {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String ftpPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathVideo") + "/"
								+ new SimpleDateFormat("yyyyMM").format(new Date());
						File fileDir = new File(ftpPath);
						System.out.println("ftpPath：" + ftpPath);
						if (!fileDir.exists()) {// 如果文件夹不存在
							fileDir.mkdir();// 创建文件夹
						}
						String name = file.getOriginalFilename();
						String suffix = name.substring(name.lastIndexOf(".") + 1);
						String fileName = UUIDUtil.getUUID().toString() + "." + suffix;
						String path = ftpPath + "/" + fileName;
						System.out.println("path：" + path);
						// 上传
						file.transferTo(new File(path));

						// 保存视频的操作
						p.setId(UUIDUtil.getUUID().toString());
						p.setCreateTime(new Date());
						p.setPictureName(fileName);
						p.setName(name);
						p.setProductDir(new SimpleDateFormat("yyyyMM").format(new Date()));
						p.setSort(0);
						int flag;

						flag = pictureInfoService.insert(p);
						System.out.println(flag);
						str.put("state", true);
						str.put("videoId", p.getId());
						str.put("videoName", name);
						JsonUtil.ajaxReturnByHTML(response, str);
					}
				} catch (DaoException e) {
					e.printStackTrace();
					str.put("state", false);
					JsonUtil.ajaxReturnByHTML(response, str);
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
	}

	@RequestMapping("deleteFileVideoItls2.htm")
	public void deleteFileVideoItls2(HttpServletRequest request, HttpServletResponse response) {
		String videoId = request.getParameter("videoId");

		Map<String, Object> str = new HashMap<String, Object>();
		PictureInfo pictureInfo = pictureInfoService.findById(videoId);
		String id = request.getParameter("id");
		if (pictureInfo != null) {
			String videoSaveSRC = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathVideo");
			String fileName = videoSaveSRC + "/" + pictureInfo.getProductDir() + "/" + pictureInfo.getPictureName();
			String filePath = videoSaveSRC + "/" + pictureInfo.getProductDir();

			if (deleteFile(filePath, fileName)) {
				try {
					if (pictureInfoService.delete(pictureInfo)) {
						if (StringUtils.isNotBlank(id)) {
							Map<String, Object> updateMap = new HashMap<String, Object>();
							updateMap.put("id", id);
							// navigationService.updateByMap(updateMap);
						}
						str.put("state", true);
						JsonUtil.ajaxReturnByHTML(response, str);
					}
				} catch (DaoException e) {
					str.put("state", false);
					JsonUtil.ajaxReturnByHTML(response, str);
					e.printStackTrace();
				}

			}
		} else {
			str.put("state", false);
			JsonUtil.ajaxReturnByHTML(response, str);
		}
	}

}
