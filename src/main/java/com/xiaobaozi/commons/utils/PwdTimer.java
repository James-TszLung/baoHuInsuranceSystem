package com.xiaobaozi.commons.utils;
/*package com.skyecho.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;

import com.skyecho.shenzhenairlines.commons.exception.DaoException;
import com.skyecho.shenzhenairlines.commons.helper.ConfigUtil;
import com.skyecho.shenzhenairlines.usermanage.service.LoginService;
import com.skyecho.shenzhenairlines.usermanage.service.UserInfoService;
import com.skyecho.shenzhenairlines.usermanage.vo.UserInfo;

public class PwdTimer{

	private static Logger logger = Logger.getLogger(PwdTimer.class);
	private UserInfoService userInfoService;
	private LoginService loginService;
	private ConfigUtil configUtil;
	
	*//**
	 * 每日定时任务起始方法
	 * @throws DaoException 
	 *//*
	public void run() throws DaoException {
		configUtil = ConfigUtil.getInstance();
		
		checkUserLogin();
		checkPwdEveryDay();
	}
	
	*//**
	 * 检测用户是否长期未登录，对于N个月未登录用户进行暂停
	 *//*
	private void checkUserLogin(){
		int monthLimit = configUtil.getUserMaxNoLoginMonth();// 后面将配置到系统设置中
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		logger.info(DateUtil.getNowStr()+"定时检查用户登录情况任务启动！开始检测"+monthLimit+"个月未登录的用户...");
		try {
			Calendar cal=Calendar.getInstance();
//			cal.set(Calendar.DATE,cal.get(Calendar.DATE)-monthLimit*30);
			cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-monthLimit);// 获取monthLimit个月前的日期
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lastlogin", sf.format(cal.getTime()));
			
			// 查询出未登录过（登录过）同时修改时间（登录时间）小于lastlogin日期的所有用户
			List<UserInfo> users = userInfoService.listByMap(map);
			// 将这些用户状态设为暂停N
			for(UserInfo u : users){
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", "N");
				m.put("userID", u.getUserID());
				userInfoService.updateByMap(m);
			}
		} catch (Exception e) {
			logger.error("定时检查用户登录情况报错："+e);
		}
	}
	
	*//**
	 * 每日定时检查用户密码是否长期未更改
	 *//*
	private void checkPwdEveryDay(){
		logger.info(DateUtil.getNowStr()+"定时检查用户密码任务启动！开始检测到期需更改密码的用户...");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			//一、
			//获取距离上次更改密码有1个月的账号，且没有发送过验证码
			map.put("interval", PropertiesUtil.getValue("/conf/properties/systemconf.properties"
					, "quartz.changepwd.interval.day"));
			List<UserInfo> users = userInfoService.listByMap(map);
			for(UserInfo ui : users){
				Random r = new Random();
				int code = r.nextInt(10000);
				ui.setValidCode(code);
				//数据库更新用户信息表中的验证码
				userInfoService.update(ui);
			}
			
			//二、
			//获取有验证码，但是过了2个月的账号
			map.clear();
			map.put("limit", PropertiesUtil.getValue("/conf/properties/systemconf.properties"
					, "quartz.changepwd.limit.day"));
			List<UserInfo> unChangePwdUsers = userInfoService.listByMap(map);
			//停用这些账号
			for(UserInfo ui : unChangePwdUsers){
				ui.setValidCode(0);
				ui.setStatus("N");
				userInfoService.update(ui);
			}
		} catch (Exception e) {
			logger.error("定时检查用户密码任务报错："+e);
		}
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
}
*/