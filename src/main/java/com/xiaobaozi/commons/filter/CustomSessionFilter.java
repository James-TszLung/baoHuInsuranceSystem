package com.xiaobaozi.commons.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.xiaobaozi.commons.vo.CommonUserDetails;

/**
 * <b>用户session过滤器</b>
 * <br>用于判断用户session的当前状态并做出响应动作，如提示用户已登录、已踢出、已超时等
 * @author zhengbh
 *
 */
public class CustomSessionFilter extends ConcurrentSessionFilter {
	
	// 固态变量
	private static final String LOGIN_ANOTHER_MESSAGE = "您的帐号已在别处登录！";
	private static final String SESSION_TIMEOUT_MESSAGE = "您的帐号已经超时退出！";
	private static final String DO_YOU_WANT_TO_SHOTOFF_MESSAGE = "您的帐号正在使用中,或未正常退出，是否将其踢出后登录？";
	private static String[] excludeUrls;
	private static String[] excludeUrlParameSplit;
	
	private SessionRegistry sessionRegistry;
	private String expiredUrl;
	private LogoutHandler[] handlers = new LogoutHandler[] { new SecurityContextLogoutHandler() };
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	// 默认地址
	private String defaultLoginPage;
	private String defaultIndexUrl;
	private String defaultLoginUrl;
	private String defaultRedirectUrl;
	private String excludeUrl;
	private String excludeUrlParame;//判断带参是否的判断
	/**
	 * @deprecated
	 */
	public CustomSessionFilter() {
	}

	public CustomSessionFilter(SessionRegistry sessionRegistry) {
		this(sessionRegistry, null);
	}

	@SuppressWarnings("deprecation")
	public CustomSessionFilter(SessionRegistry sessionRegistry,
			String expiredUrl) {
		this.sessionRegistry = sessionRegistry;
		this.expiredUrl = expiredUrl;
	}

	public void afterPropertiesSet() {
		Assert.notNull(this.sessionRegistry, "SessionRegistry required");
		Assert.isTrue(
				((this.expiredUrl == null) || (UrlUtils
						.isValidRedirectUrl(this.expiredUrl))),
				this.expiredUrl + " 不是一个有效的重定向链接地址!");
	}

	/**
	 * 请求拦截判断
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		String reqURL = request.getServletPath();
		String queryString = request.getQueryString();
		String loginUser = req.getParameter("j_username");
		Object loginedUser = StringUtils.isNotBlank(loginUser)?isLogined(loginUser):null;
		if (session != null) {
			SessionInformation info = this.sessionRegistry
					.getSessionInformation(session.getId());
			
			//sessioninfo不为空说明该sessionID登录过
			if (info != null) {
				//如果sessioninfo被标识expired=true则表示被提出或已过期，出现在被踢出和正常退出登录时
				if (info.isExpired()) {
					doLogout(request, response);//退出登录

					CommonUserDetails lastLoginUser = (CommonUserDetails)info.getPrincipal();
					loginedUser = isLogined(lastLoginUser.getUsername());
					if(loginedUser!=null){// 已被踢出的时候
						Cookie cookie = new Cookie("msg",URLEncoder.encode(LOGIN_ANOTHER_MESSAGE,"utf-8"));
						cookie.setMaxAge(3600);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					String targetUrl = determineExpiredUrl(request, info);
					if (targetUrl != null) {
						this.redirectStrategy.sendRedirect(request, response,
								targetUrl);
						return;
					}
				} else {
					resetMsgCookie(request, response);
					this.sessionRegistry.refreshLastRequest(info.getSessionId());//如果仍在有效期内，则刷新session
					if(excludeUrls==null)
						excludeUrls = excludeUrl.split(",");
					if(excludeUrlParameSplit==null)
						excludeUrlParameSplit = excludeUrlParame.split(",");
					boolean notExclude = true;
					for(int i=0; i<excludeUrls.length; i++){
						// 如果不是排除列表中的URL，则会进行判断是否合法，并做相应动作
						if(excludeUrls[i].equals(reqURL)){
							notExclude = false;
							break;
						}
					}
					//判断是否有需要带参与否的判断
					for(int i=0; i<excludeUrlParameSplit.length; i++){
						// 如果不是排除列表中的URL，则会进行判断是否合法，并做相应动作
						if(excludeUrlParameSplit[i].equals(reqURL)){
							//判断是否带参
							if(StringUtils.isNotBlank(queryString)){
								notExclude = false;
								break;
							}
						}
					}
					if(notExclude){
						if("/".equals(reqURL)
								|| defaultLoginUrl.equals(reqURL)
								|| defaultLoginPage.equals(reqURL)){
							this.redirectStrategy.sendRedirect(request, response, defaultIndexUrl);
							return;
						} else if(linkStealing(request, response, reqURL, true))
							return;
					}
				}
			}else{//本次请求的session未登录过的话，先判断别处是否有重名用户在使用
				// 防止用户在浏览器上直接输入登录URL请求出错
				if(defaultLoginUrl.equals(reqURL)){
					if(request.getMethod().equalsIgnoreCase("post")){
						if(checkAnotherLogined(req, response, loginedUser))
							return;
					} else {
						this.redirectStrategy.sendRedirect(request, response, defaultLoginPage);
						return;
					}
				}
				if("/".equals(reqURL)
						|| defaultIndexUrl.equals(reqURL)) {
					resetMsgCookie(request, response);
					this.redirectStrategy.sendRedirect(request, response, defaultLoginPage);
					return;
				} else if(expiredUrl.equals(reqURL)) {
					request.setAttribute("msg", SESSION_TIMEOUT_MESSAGE);
				} else if(linkStealing(request, response, reqURL, true)){
					return;
				} else if(!defaultLoginUrl.equals(reqURL)) {
					this.redirectStrategy.sendRedirect(request, response, defaultRedirectUrl);
					return;
				}
			}
		} else {
			if("/".equals(reqURL)
					|| defaultIndexUrl.equals(reqURL)){
				resetMsgCookie(request, response);
				this.redirectStrategy.sendRedirect(request, response, defaultLoginPage);
				return;
			} else if(defaultLoginUrl.equals(reqURL)) {
				// 预防用户在没有session的情况下直接请求登录地址出错的情况
				if(checkAnotherLogined(req, response, loginedUser))
					return;
			} else if(linkStealing(request, response, reqURL, true))
				return;
		}

		chain.doFilter(request, response);
	}
	
	/**
	 * 防盗链请求
	 * @param request
	 * @param response
	 * @param reqURL
	 * @param isOnline
	 * @return
	 * @throws IOException
	 */
	protected boolean linkStealing(HttpServletRequest request, HttpServletResponse response,
			String reqURL, boolean isOnline) throws IOException{
		String refer = request.getHeader("referer");//浏览器地址栏请求时，此值为空
		if(StringUtils.isBlank(refer)){
			if(!expiredUrl.equals(reqURL)
					&& !defaultLoginPage.equals(reqURL)
					&& !defaultIndexUrl.equals(reqURL)){
				this.redirectStrategy.sendRedirect(request, response,
						isOnline?defaultIndexUrl:defaultLoginPage);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否有其他同名用户登录，需要给当前登录者选择是否踢出
	 * @param req
	 * @param response
	 * @param loginedUser
	 * @throws IOException
	 */
	protected boolean checkAnotherLogined(ServletRequest req, 
			HttpServletResponse response, Object loginedUser)
					throws IOException{
		boolean isExpiredAnother = Boolean.parseBoolean(req.getParameter("expired_another"));
		if(loginedUser!=null && loginedUser!=null){
			//获取当前遍历用户的所有session，第二个参数表示是否取单点登录时，超出限制而被弹出用户
			List<SessionInformation> ilist = sessionRegistry.getAllSessions(loginedUser, false);
			if(ilist.size()>0){//如果该用户在别处正在使用
				if(isExpiredAnother){//确认踢出，并登录
					for(SessionInformation si : ilist){
						si.expireNow();
					}
				}else{//否则提示正在被使用
					response.getWriter()
					.print("{result:\"false\",message:\"" + DO_YOU_WANT_TO_SHOTOFF_MESSAGE + "\"}");
					response.flushBuffer();
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 清除message信息的cookies
	 * @param request
	 * @param response
	 */
	protected void resetMsgCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		if(cookies!=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("msg")){
					Cookie c = new Cookie("msg", null);
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}
			}
		}
	}
	
	/**
	 * 判断用户是否在别处登录过
	 * @param username
	 * @return
	 */
	protected Object isLogined(String username){
		if(username!=null){
			List<Object> sList = sessionRegistry.getAllPrincipals();//获取所有登录用户
			for(Object obj:sList){
				CommonUserDetails user = (CommonUserDetails)obj;
				if(user.getUsername().equalsIgnoreCase(username)){
					List<SessionInformation> ilist = sessionRegistry.getAllSessions(obj, false);
					if(ilist.size()>0){
						for(SessionInformation si : ilist){
							if(si.isExpired())
								return null;
						}
						return obj;
					}
					return null;
				}
			}
		}
		return null;
	}

	protected String determineExpiredUrl(HttpServletRequest request,
			SessionInformation info) {
		return this.expiredUrl;
	}

	/**
	 * 退出登录
	 * @param request
	 * @param response
	 */
	private void doLogout(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		LogoutHandler[] arr$ = this.handlers;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			LogoutHandler handler = arr$[i$];
			handler.logout(request, response, auth);
		}
	}

	@Deprecated
	public void setExpiredUrl(String expiredUrl) {
		this.expiredUrl = expiredUrl;
	}

	@Deprecated
	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public void setLogoutHandlers(LogoutHandler[] handlers) {
		Assert.notNull(handlers);
		this.handlers = handlers;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public void setDefaultLoginPage(String defaultLoginPage) {
		this.defaultLoginPage = defaultLoginPage;
	}

	public void setDefaultIndexUrl(String defaultIndexUrl) {
		this.defaultIndexUrl = defaultIndexUrl;
	}

	public void setDefaultLoginUrl(String defaultLoginUrl) {
		this.defaultLoginUrl = defaultLoginUrl;
	}

	public void setDefaultRedirectUrl(String defaultRedirectUrl) {
		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	public String getExcludeUrl() {
		return excludeUrl;
	}

	public void setExcludeUrl(String excludeUrl) {
		this.excludeUrl = excludeUrl;
	}

	public String getExcludeUrlParame() {
		return excludeUrlParame;
	}

	public void setExcludeUrlParame(String excludeUrlParame) {
		this.excludeUrlParame = excludeUrlParame;
	}
}
