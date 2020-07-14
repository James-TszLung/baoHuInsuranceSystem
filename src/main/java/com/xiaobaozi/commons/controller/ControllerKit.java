package com.xiaobaozi.commons.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.enums.UserType;
import com.xiaobaozi.dataSystem.commons.exception.UploadException;
import com.xiaobaozi.dataSystem.usermanage.service.UserInfoService;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

/**
 * 封装控制器用到的公共方法
 * 
 * @author zhongsh
 */
public class ControllerKit {

	private static Logger log = Logger.getLogger(ControllerKit.class);
	private BaseController controller;
	private UserInfoService userInfoService;

	/**
	 * 根据userId获取用户
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoById(String userId) {
		return userInfoService.findById(userId);
	}

	/**
	 * 根据用户类型获取用户信息
	 * 
	 * @param type
	 *            用户类型
	 * @return
	 */
	public List<UserInfo> getUserInfo(UserType type, String corpId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null) {
			map.put("userType", type.getValue());
			// 2015/6/16 限制本单位配送人员
			map.put("corpId", corpId);
		}
		List<UserInfo> list = userInfoService.listByMap(map);
		return list;
	}

	/**
	 * 取得当前登录用户信息
	 * 
	 * @return
	 */
	public CommonUserDetails getLoginUser() {
		Object principal = controller.getAuthentication().getPrincipal();
		if (principal instanceof CommonUserDetails) {
			return (CommonUserDetails) principal;
		} else {
			log.info("没有取到用户登录信息");
			return null;
		}
	}

	/**
	 * 从request获取文件上传输入流
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param inputName
	 *            input输入框的name属性
	 * @return InputStream
	 * @throws UploadException
	 *             出错信息
	 */
	public InputStream getStreamFromRequest(HttpServletRequest request, String inputName) throws UploadException {
		return getStreamFromRequest(request, inputName, null);
	}

	/**
	 * 从request获取文件上传输入流
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param inputName
	 *            input输入框的name属性
	 * @param fileTypes
	 *            限制文件类型，不区分大小写，如["jpg","gif"]，传null则不限制
	 * @return InputStream
	 * @throws UploadException
	 *             出错信息
	 */
	public InputStream getStreamFromRequest(HttpServletRequest request, String inputName, String[] fileTypes)
			throws UploadException {
		MultipartFile multipartFile = getMultipartFile(request, inputName);
		return getStreamFromMultipartFile(multipartFile, fileTypes);
	}

	/**
	 * 转换成流，并检查文件类型
	 */
	public InputStream getStreamFromMultipartFile(MultipartFile multipartFile, String[] fileTypes)
			throws UploadException {
		if (multipartFile == null) {
			return null;
		}
		String filename = multipartFile.getOriginalFilename();
		if (StringUtils.isEmpty(filename)) {
			return null;
		}
		// 截取文件类型
		String filetype = "";
		int index = filename.lastIndexOf(".");
		if (index >= 0) {
			filetype = filename.substring(index + 1);
		}
		// 检查文件类型
		if (fileTypes != null) {
			if ("".equals(filetype)) {
				throw new UploadException("文件类型不支持");
			}
			boolean flag = false;
			for (String type : fileTypes) {
				if (filetype.equalsIgnoreCase(type)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new UploadException("文件类型不支持");
			}
		}
		try {
			InputStream inputStream = multipartFile.getInputStream();
			return inputStream;
		} catch (IOException e) {
			log.error("上传文件出错", e);
			throw new UploadException("上传文件出错");
		}
	}

	/**
	 * 从request取得文件
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param inputName
	 *            input输入框的name属性
	 * @return
	 */
	public MultipartFile getMultipartFile(HttpServletRequest request, String inputName) {
		MultipartHttpServletRequest multipartRequest = null;
		if (request instanceof MultipartHttpServletRequest) {
			multipartRequest = (MultipartHttpServletRequest) request;
		} else {
			log.error("文件上传文件失败，request不是MultipartHttpServletRequest类型");
			return null;
		}
		MultipartFile multipartFile = multipartRequest.getFile(inputName);
		if (multipartFile == null) {
			return null;
		}
		return multipartFile;
	}

	/**
	 * 从response获取.xlsx文件类型的输出流
	 * 
	 * @param response
	 * @param excelName
	 *            Excel文件名（不含后缀）
	 * @return
	 * @throws IOException
	 */
	public ServletOutputStream getExcelOutputStream(HttpServletResponse response, String excelName) throws IOException {
		// 设置response，返回.xlsx文件
		response.setHeader("content-disposition", "attachment;filename="
				+ new String(excelName.getBytes("gb2312"), "ISO-8859-1") + ".xlsx");
		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream outputStream = response.getOutputStream();
		return outputStream;
	}

	public void setController(BaseController baseController) {
		this.controller = baseController;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
