package com.xiaobaozi.commons.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.MD5;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.usermanage.service.UserInfoService;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

/**
 * 带验证码校验功能的用户名、密码认证过滤器
 * 
 * 支持不输入验证码；支持验证码忽略大小写。
 * 
 * @author Long
 * 
 */
public class ValidateCodeFilter extends UsernamePasswordAuthenticationFilter {

	private boolean postOnly = false;
	private boolean allowEmptyValidateCode = false;
	private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
	private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	// session中保存的验证码
	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "code_session";
	// 输入的验证码
	public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code_input";
    @Resource
	private UserInfoService userInfoService;
	// 在线用户的信息集合
	private SessionRegistry sessionRegistry;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("用户认证方法不支持: " + request.getMethod() + " 请求方式");
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		} 

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		// 验证用户输入的验证码
		// 检测密码是否正确，是否超过指定错误次数
		try {
			boolean checkLoginUser = Boolean.parseBoolean(request.getParameter("checkLoginUser"));
			if (!checkLoginUser) {
				checkUserInputPwd(username, password);
			}
			if (true) {
				checkValidateCode(request);
			}
		} catch (DaoException e) {
			throw new AuthenticationServiceException("后台验证出错，请联系管理员！");
		}

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 用来验证用户输入的密码是否错误，并记录比较错误次数与时间
	 * 
	 * @param username
	 * @param password
	 * @throws DaoException
	 */
	protected void checkUserInputPwd(String username, String password) throws DaoException {
		int limitlockTime = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties",
				"security.login.user.pwderror.locktime"));
		int limitPwdErrorNum = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties",
				"security.login.user.pwderror.num"));
		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.put("userName", username);
		List<UserInfo> uis = userInfoService.listByMap(sqlParams);
		if (uis.size() == 0)
			throw new AuthenticationServiceException("此用户不存在！");
		else {
			UserInfo ui = uis.get(0);
			// Map<String, Object> ciSqlParams = new HashMap<String, Object>();
			// ciSqlParams.put("corpid", ui.getCorpID());
			// List<CorpInfo> corps = corpInfoService.listByMap(ciSqlParams);
			// if (corps.size() > 0) {
			// CorpInfo ci = corps.get(0);
			// if (ci.getStatus().equalsIgnoreCase("N"))
			// throw new AuthenticationServiceException("您账号所属单位已经被停用，不允许登录！");
			// } else {
			// throw new AuthenticationServiceException("您的账号未绑定任何单位，不允许登录！");
			// }
			if (ui.getPassword().length() != 32)
				ui.setPassword(MD5.md5(ui.getPassword()));
			long nowTime = new Date().getTime();
			if (ui.getStatus()==1) {// 停用账号不算在内
				if (ui.getPassword().equals(MD5.md5(password))) {// 密码输入正确的情况
//					if (ui.getErrorTimes() < limitPwdErrorNum) {
//						ui.setErrorTimes(0);
//						userInfoService.update(ui);
//					} else {
//						long lastTime = ui.getLastErrorDate().getTime();
//						if (nowTime - lastTime < 600000) {
//							throw new AuthenticationServiceException("因密码输入错误达5次，用户已锁定！剩余：" + (600 - (nowTime - lastTime) / 1000)
//									+ "秒");
//						} else {
							// 锁定时间超过10分钟，自动解除锁定状态
//							ui.setErrorTimes(0);
//							ui.setLastErrorDate(null);
//							ui.setStatus("Y");
//							userInfoService.update(ui);
//						}
//					}
				} else {// 密码输错的情况
//					if (ui.getErrorTimes() < limitPwdErrorNum) {
//						if (ui.getErrorTimes() > 0) {
//							ui.setErrorTimes(ui.getErrorTimes() + 1);
//							if (ui.getErrorTimes() == limitPwdErrorNum) {// 达到了限制的错误次数，锁定！
//								ui.setLastErrorDate(new Date());
//								ui.setStatus("L");// L：锁定状态
//								userInfoService.update(ui);
//								throw new AuthenticationServiceException("密码输入错误达" + ui.getErrorTimes() + "次，用户锁定"
//										+ limitlockTime + "分钟！");
//							} else {
//								// 第N次输错，记录
//								userInfoService.update(ui);
//								throw new AuthenticationServiceException("密码输入错误" + ui.getErrorTimes() + "次，" + limitPwdErrorNum
//										+ "次之后用户将被锁定" + limitlockTime + "分钟！");
//							}
//						} else {
							// 第一次输错，记录
//							ui.setErrorTimes(1);
//							userInfoService.update(ui);
//							throw new AuthenticationServiceException("密码输入错误1次，" + limitPwdErrorNum + "次之后用户将被锁定" + limitlockTime
//									+ "分钟！");
//						}
//					} else {
//						long lastTime = ui.getLastErrorDate().getTime();
//						if (nowTime - lastTime < 600000) {
//							throw new AuthenticationServiceException("因密码输入错误达" + limitPwdErrorNum + "次，用户已锁定！剩余："
//									+ (600 - (nowTime - lastTime) / 1000) + "秒");
//						} else {
//							// 锁定时间超过10分钟，但还是输错密码，则计数从1开始，先解除锁定
//							ui.setErrorTimes(1);
//							ui.setLastErrorDate(null);
//							ui.setStatus("Y");
//							userInfoService.update(ui);
//							throw new AuthenticationServiceException("密码输入错误" + ui.getErrorTimes() + "次，" + limitPwdErrorNum
//									+ "次之后用户将被锁定" + limitlockTime + "分钟！");
//						}
//					}
					throw new AuthenticationServiceException("密码输入错误!");
				}
			} else {
//				if (ui.getValidCode() == 0)
					throw new AuthenticationServiceException("您的账号已经被停用，不允许登录！");
			}
		}
	}

	/**
	 * 
	 * <li>比较session中的验证码和用户输入的验证码是否相等</li>
	 * 
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		return request.getParameter(validateCodeParameter);
	}

	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(sessionvalidateCodeField);
		return null == obj ? "" : obj.toString();
	}

	public boolean isPostOnly() {
		return postOnly;
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getValidateCodeName() {
		return sessionvalidateCodeField;
	}

	public void setValidateCodeName(String validateCodeName) {
		this.sessionvalidateCodeField = validateCodeName;
	}

	public boolean isAllowEmptyValidateCode() {
		return allowEmptyValidateCode;
	}

	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
		this.allowEmptyValidateCode = allowEmptyValidateCode;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

}
