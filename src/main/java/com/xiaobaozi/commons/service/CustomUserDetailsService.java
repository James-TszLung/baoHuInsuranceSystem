package com.xiaobaozi.commons.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xiaobaozi.commons.utils.MD5;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.usermanage.service.FunctionService;
import com.xiaobaozi.dataSystem.usermanage.service.RoleService;
import com.xiaobaozi.dataSystem.usermanage.service.UserInfoService;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

/**
 * 一个自定义的service用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService
 * 
 * @author zhengbh
 * 
 */
public class CustomUserDetailsService implements UserDetailsService {

	// 该类是security的国际化文件读取类，程序启动时自动读取指定properties
	private MessageSource messageSource;

	protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);

	private UserInfoService userInfoService;
	private RoleService roleService;
	private FunctionService functionService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		CommonUserDetails user = null;

		try {
			Map<String, Object> sqlParams = new HashMap<String, Object>();
			sqlParams.put("userName", username);
			// 搜索数据库以匹配用户登录名.
			List<UserInfo> users = userInfoService.listByMap(sqlParams);

			if (users.size() > 0) {
				UserInfo loginUser = users.get(0);
				if (loginUser.getPassword().length() != 32){// 防止旧明文密码验证错误，先要转MD5
					loginUser.setPassword(MD5.md5(loginUser.getPassword()));}
				boolean enabledUser = true;
				boolean changePwd = false;
//				if (loginUser.getValidCode() > 0) {
					enabledUser = changePwd = true;
//				} else {
//					enabledUser = loginUser.getStatus().equalsIgnoreCase("y");// 如果用户被停用，则不能登录
//				}
				boolean accountNonExpired = true;// 账号是否过期
				boolean credentialsNonExpired = true;// 用户凭证是否过期
				boolean accountNonLocked = true;// 账号是否锁定
				// 获取角色集合
				Collection<GrantedAuthority> userAuths = getAuthorities(loginUser.getId());
				// 获取按钮权限
				List<Function> btnFuns = new ArrayList<Function>();
				for (GrantedAuthority ga : userAuths) {
					try {
						//int roleid = Integer.parseInt(ga.getAuthority());
						sqlParams.clear();
						sqlParams.put("funtype", 1);
						sqlParams.put("roleid", ga.getAuthority());
						btnFuns.addAll(functionService.listByMap(sqlParams));
					} catch (NumberFormatException e) {
						continue;
					}
				}
				// 获取IP
//				loginUser.setLoginIP(HttpUtil.getIpAddr(request));
				user = new CommonUserDetails(loginUser, enabledUser, accountNonExpired, credentialsNonExpired, accountNonLocked,
						userAuths, btnFuns);

				// 如果账号状态正常，则保存登录记录
				if (enabledUser & accountNonExpired & credentialsNonExpired & accountNonLocked) {
					// 记录凭证MD5，用于单点登录
					String ticketKey = MD5.md5(loginUser.getId() + new Date().getTime());
					user.setTicketKey(ticketKey);
					user.setChangePwd(changePwd);

					// logger.info("【用户登录记录保存】"+l.toString());
//					try {
////						loginService.insert(l);
//					} catch (DaoException e) {
//						logger.error("【用户登录记录保存失败！】" + e);
//					}
				}
			}

		} catch (Exception e) {
			logger.error("登录失败！");
			throw new UsernameNotFoundException(messageSource.getMessage("DigestProcessingFilter.usernameNotFound",
					new Object[] { username }, null));
		}

		return user;
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(String userId) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<Role> roles = roleService.listByMap(params);

		for (Role r : roles) {
			authList.add(new GrantedAuthorityImpl(r.getRoleId() + ""));
		}
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));

		return authList;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
}
