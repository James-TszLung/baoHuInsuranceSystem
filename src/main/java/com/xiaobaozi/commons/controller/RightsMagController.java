package com.xiaobaozi.commons.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobaozi.client.util.UserUtil;
import com.xiaobaozi.commons.utils.MenuUtil;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.usermanage.service.FunctionService;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

/**
 * 用户权限控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("auth")
public class RightsMagController extends BaseController {

	protected static Logger log = Logger.getLogger(RightsMagController.class);
   
	@Resource
	private FunctionService functionService;

	/**
	 * 指向登录页面
	 */
	@RequestMapping("login.htm")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
		log.info("Received request to show login pag");
		return new ModelAndView("main/auth/loginpage");
	}

	/**
	 * 跳转到commonpage页面
	 * 
	 * @return
	 */
	@RequestMapping("getCommonPage.htm")
	public ModelAndView getCommonPage(HttpServletRequest req, HttpServletResponse res) {
		log.info("Received request to show common page");
		ModelAndView mv = new ModelAndView("main/welcome");
		return mv;
	}

	/**
	 * 检测用户是否在线 注： 1.此方法供需要单点登录系统调用； 2.如用户未登录或被踢出，是无法进入此方法的，会在用户验证阶段跳转到登陆界面；
	 * 3.如用户已登录，则方法返回成功提示的JSON。
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("isLogin.htm")
	public void isLogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		CommonUserDetails user = UserUtil.isSessionLogin(getSessionRegistry(), request.getParameter("ticket"));
		map.put("result", (user != null) + "");
		if (user != null)
			map.put("userid", user.getUsername());
		else
			map.put("userid", "");
		JsonUtil.ajaxReturn(response, map);
	}

	/**
	 * 退出登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("toLogout.htm")
	public ModelAndView toLogout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		String ticket = req.getParameter("ticket");
		SessionInformation si = null;
		if (StringUtils.isNotBlank(ticket))
			si = UserUtil.getSessionInformation(getSessionRegistry(), null, ticket);
		else
			si = getSessionRegistry().getSessionInformation(session.getId());
		if (si != null) {
			si.expireNow();
			try {
				Cookie c;
				c = new Cookie("msg", URLEncoder.encode("您已成功退出登录", "utf-8"));
				c.setMaxAge(3600);
				c.setPath("/");
				res.addCookie(c);
			} catch (UnsupportedEncodingException e) {
				log.error(e);
			}
		}
		log.info("Logout success! Received request to show login page");
		return new ModelAndView("main/auth/registryToTimeout");
	}

	/**
	 * 获取菜单
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("getMenu.htm")
	public ModelAndView getMenu(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", req.getParameter("userid"));
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		params.put("orderBy", props.getProperty("order.col.for.menu"));

		List<Function> menus = functionService.queryMenu(params);

		for (Function f : menus) {
			if (StringUtils.isNotBlank(f.getFunUrl()) && f.getFunUrl().length() > 0) {
				if (f.getFunUrl().indexOf("/") == 0)
					f.setFunUrl(req.getContextPath() + f.getFunUrl());
				else
					f.setFunUrl(f.getFunUrl() + "?ticket=" + user.getTicketKey());
			}
		}
		String menuJson = MenuUtil.parseMenuObjToJson(menus, false);
		res.setContentType("text/json;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(menuJson);
		} catch (IOException ex1) {
			ex1.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 跳转到session-timeout页面
	 * 
	 * @return
	 */
	@RequestMapping("getSessionTimeoutPage.htm")
	public ModelAndView getSessionTimeoutPage(HttpServletRequest req, HttpServletResponse res) {
		Object msgObj = req.getAttribute("msg");
		log.info("Received request to show timeout page");
		ModelAndView mv = new ModelAndView("main/auth/session-timeout");
		if (msgObj != null)
			mv.addObject("msg", (String) msgObj);
		return mv;
	}

	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	public String getDeniedPage() {

		log.info("Received request to show denied page");

		return "main/auth/deniedpage";

	}

	/**
	 * 跳转到adminpage页面
	 * 
	 * @return
	 */
	public String getAadminPage() {
		log.info("Received request to show admin page");
		return "main/main";

	}

	/**
	 * 跳转到测试页面
	 * 
	 * @return
	 */
	public String getTestPage() {
		log.info("Received request to show common page");
		return "main/main";
	}

}
