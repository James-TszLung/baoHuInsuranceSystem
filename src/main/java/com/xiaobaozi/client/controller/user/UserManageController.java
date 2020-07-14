package com.xiaobaozi.client.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobaozi.client.util.UserUtil;
import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.MD5;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.usermanage.search.UserSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.service.UserInfoService;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

/**
 * 用户权限管理控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("usermanage")
public class UserManageController extends BaseController {

	protected static Logger log = Logger.getLogger(UserManageController.class);
   @Resource
	private UserInfoService userInfoService;


	/**
	 * 根据条件查询用户
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("searchUser.htm")
	public ModelAndView searchUser(HttpServletRequest request, HttpServletResponse response) {
		UserSearchCriteria csc = new UserSearchCriteria();
		bind(request, csc);
		String userAccount = csc.getAccount().trim();
		String userRole = csc.getRoleName().trim();
		csc.setAccount(userAccount);
		csc.setRoleName(userRole);

		Integer count = userInfoService.getUserInfoCount(csc);
		csc.setTotalCount(count); // 设置记录总数
		List<UserInfo> evlist = userInfoService.getUserInfoListByPage(csc);
		String data = JsonUtil.convertToArrayJson(evlist);
		ModelAndView mv = new ModelAndView("main/user/usermgr/userbody");
		mv.addObject("data", data);
		mv.addObject("sc", csc);
		return mv;
	}

	/**
	 * 跳转用户信息页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("userPage.htm")
	public ModelAndView userPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/user/usermgr/usermanage");
	}

	/**
	 * 跳转用户新增修改页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("editUserPage.htm")
	public ModelAndView editUserPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("main/user/usermgr/useredit");
		if (req.getParameter("userId") != null) {
			mav.addObject("userId", req.getParameter("userId"));
			mav.addObject("userName", req.getParameter("userName"));
		} else {
			CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
			mav.addObject("loginUserName", user.getFullname());
			mav.addObject("loginUserId", user.getUsername());
		}
		return mav;
	}

	/**
	 * 新增用户信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("addUser.htm")
	public ModelAndView addUser(HttpServletRequest req, HttpServletResponse res) {
		UserInfo ci = new UserInfo();
		bind(req, ci);
		UserSearchCriteria sc = new UserSearchCriteria();
		sc.setAccount(ci.getAccount());
		int sucOrFail = 0;
		ci.setPassword(MD5.md5(ci.getPassword()));
		ci.setId(UUIDUtil.getUUID());
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		ci.setCreateName(user.getFullname());
		ci.setStatus(1);
		try {
			if (userInfoService.insert(ci) == 1) {
				sucOrFail = 1;
			} else {
				sucOrFail = 2;
			}
		} catch (DaoException e) {
			log.error(e);
			return null;
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(sucOrFail);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 根据id查询用户，并返回json数据---主要用于获取单个用户的信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("getUserByID.htm")
	public ModelAndView getUserByID(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", req.getParameter("userId"));
		List<UserInfo> list = userInfoService.listByMap(map);
		UserInfo reqInfo = null;
		if (list.size() > 0)
			reqInfo = list.get(0);
		else
			reqInfo = new UserInfo();

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(JSONObject.fromObject(reqInfo).toString());
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	// 检验注册的用户名是否存在重复
	@RequestMapping("isRepeat.htm")
	public void isRepeat(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;

		String uName = request.getParameter("uName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", uName);
		List<UserInfo> list = userInfoService.listByMap(map);
		System.out.println(list.size());

		try {
			out = response.getWriter();
			if (list.size() > 0) {
				out.println(true);
			} else {
				out.print(false);
			}

		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
	}

	/**
	 * 检测用户名是否重复，但要保证修改时自己原本账号不被提示
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("checkUsername.htm")
	public void checkUsername(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> rs = new HashMap<String, Object>();
		String editingUser = req.getParameter("userName");
		String userName = controllerKit.getLoginUser().getFullname();
		System.out.println(userName);
		if (userName.equals(editingUser)) {
			rs.put("success", true);
		} else {
			if ("".equals(editingUser)) {
				rs.put("success", false);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userName", req.getParameter("userName"));
				List<UserInfo> list = userInfoService.listByMap(map);
				rs.put("success", list.size() == 0);
			}
		}
		try {
			out = res.getWriter();
			out.println(JSONObject.fromObject(rs).toString());
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
	}

	/**
	 * 修改用户
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("editUser.htm")
	public ModelAndView editUser(HttpServletRequest req, HttpServletResponse res) {
		UserInfo ci = new UserInfo();
		bind(req, ci);
		// 判断是否更改过密码
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ci.getId());
		List<UserInfo> list = userInfoService.listByMap(map);
		// if (ci.getValidCode() > 0) {
		// // 当进行定时修改密码时，需要校验验证码
		// if (ci.getValidCode() != list.get(0).getValidCode()) {
		// map.clear();
		// map.put("result", false);
		// JsonUtil.ajaxReturnByHTML(res, map);
		// return null;
		// }
		// }
		// 如果前端更改了密码，则需要将密码MD5后再入库
		if (!list.get(0).getPassword().equalsIgnoreCase(ci.getPassword())) {
			ci.setPassword(MD5.md5(ci.getPassword()));
			// ci.setValidCode(0);
		}
		// ci.setuserId(ci.getuserId().toUpperCase());
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		// if (StringUtils.isNotBlank(ci.getStatus())) {
		// if (ci.getStatus().equals("Y") || ci.getStatus().equals("N")) {
		// ci.setErrorTimes(0);
		// ci.setLastErrorDate(null);
		// }
		// }
		ci.setCreateName(user.getUsername());
		boolean sucOrFail = true;
		try {
			sucOrFail = userInfoService.update(ci);
		} catch (DaoException e) {
			log.error(e);
			return null;
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(sucOrFail);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 停用/启用 用户
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("setUserStatus.htm")
	public ModelAndView setUserStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String status = req.getParameter("newStatus");
		String ids = req.getParameter("userIds");
		// String idArr[] = {};
		boolean suc = true;
		// if (ids != null)
		// idArr = ids.split(",");
		// for (int i = 0; i < idArr.length; i++) {
		UserInfo ui = new UserInfo();
		ui.setStatus(Integer.parseInt(status));
		ui.setId(ids);
		// ui.setErrorTimes(0);
		// ui.setLastErrorDate(null);
		// ui.setValidCode(0);
		// ui.setLastValidDate(null);
		suc = userInfoService.update(ui);
		// }
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(suc);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 查询用户信息，修改密码 ------liuzj
	 */
	@RequestMapping("editUserInfo.htm")
	public void editUserInfo(HttpServletRequest request, HttpServletResponse response) {
		UserSearchCriteria csc = new UserSearchCriteria();
		bind(request, csc);
		UserInfo userInfo = controllerKit.getUserInfoById(csc.getId());
		// 根据用户组id查询用户组名字
		// List<UserGroup> ugList = null;
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// ugList = userGroupService.listByMap(hashMap);
		// if (ugList != null && ugList.size() > 0) {
		// userInfo.setGroupName(ugList.get(0).getGroupName());//
		// 将用户组名字赋值进userinfo
		// // 中
		// }
		// CorpInfo corpInfo = corpInfoService.findById(userInfo.getCorpID());
		hashMap.put("userInfo", userInfo);
		// hashMap.put("corpInfo", corpInfo);
		JsonUtil.ajaxReturn(response, hashMap);
	}

	/**
	 * 更新用户信息，修改密码 ------liuzj
	 */
	@RequestMapping("updateUserInfo.htm")
	public ModelAndView updateUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String flag = "Y";
		UserInfo ci = new UserInfo();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		bind(request, ci);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ci.getId());
		// 查询原始数据
		List<UserInfo> list = userInfoService.listByMap(map);
		// 如果前端更改了密码，则需要将密码MD5后再入库
		String oldpassword = request.getParameter("oldpassword");
		String checknewpwd = request.getParameter("checknewpwd");
		if (checkNewPwd(ci.getPassword()) && oldpassword != null && !"".equals(oldpassword) && checkNewPwd(checknewpwd)) {
			if (list.get(0).getPassword().equals(MD5.md5(oldpassword))) {// 如果密码正确
				if (MD5.md5(ci.getPassword()).equals(list.get(0).getPassword())) {// 如果新密码和旧密码相同，提示用户，且不进行更新操作
					flag = "S";
					hashMap.put("flag", flag);
					return getModelAndAjaxJsonView(hashMap);
				} else if (!checknewpwd.equals(ci.getPassword())) {// 如果两次新密码不一致
					flag = "T";
					hashMap.put("flag", flag);
					return getModelAndAjaxJsonView(hashMap);
				} else {
					ci.setPassword(MD5.md5(ci.getPassword()));
				}
			} else {// 旧密码错误
				flag = "E";
				hashMap.put("flag", flag);
				return getModelAndAjaxJsonView(hashMap);
			}
		}
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		ci.setCreateName(user.getUsername());
		boolean sucOrFail = true;
		try {
			sucOrFail = userInfoService.update(ci);
		} catch (DaoException e) {
			flag = "N";
			log.error(e);
		}
		if (!sucOrFail) {
			flag = "N";
		}
		hashMap.put("flag", flag);
		return getModelAndAjaxJsonView(hashMap);
	}

	/**
	 * 校验密码 ------liuzj
	 * 
	 * @param pwd
	 * @return boolean
	 */
	@RequestMapping("checkNewPwd.htm")
	private boolean checkNewPwd(String pwd) {
		String reg = "^[0-9a-zA-Z]{6,12}";
		if (pwd != null && !"".equals(pwd)) {
			return pwd.matches(reg);
		} else {
			return false;
		}
	}

	/**
	 * yangsong
	 * 
	 * 个人信息修改
	 */
	@RequestMapping("editUserMessage.htm")
	public ModelAndView editUserMessage(HttpServletRequest request, HttpServletResponse response) {
		String userName = controllerKit.getLoginUser().getFullname();
		UserInfo userInfo = controllerKit.getUserInfoById(userName);
		// 根据用户组id查询用户组名字
		// List<UserGroup> ugList = null;
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// hashMap.put("groupid", userInfo.getGroupID());
		// ugList = userGroupService.listByMap(hashMap);
		// if (ugList != null && ugList.size() > 0) {
		// userInfo.setGroupName(ugList.get(0).getGroupName());//
		// 将用户组名字赋值进userinfo
		// // 中
		// }
		// CorpInfo corpInfo = corpInfoService.findById(userInfo.getCorpID());
		ModelAndView mv = new ModelAndView("/main/user/editUserMessage");
		mv.addObject("userInfo", userInfo);
		// mv.addObject("corpInfo", corpInfo);
		return mv;
	}

	/**
	 * yangsong 修改密码
	 */
	@RequestMapping("updateUserMessage.htm")
	public ModelAndView updateUserMessage(HttpServletRequest request, HttpServletResponse response) {
		String flag = "Y";
		UserInfo ci = new UserInfo();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		bind(request, ci);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ci.getId());
		// 查询原始数据
		List<UserInfo> list = userInfoService.listByMap(map);
		// 如果前端更改了密码，则需要将密码MD5后再入库
		String oldpassword = request.getParameter("oldword");
		String checknewpwd = request.getParameter("checknewpwd");
		if (checkNewPwd(ci.getPassword()) && oldpassword != null && !"".equals(oldpassword) && checkNewPwd(checknewpwd)) {
			if (list.get(0).getPassword().equals(MD5.md5(oldpassword))) {// 如果密码正确
				if (MD5.md5(ci.getPassword()).equals(list.get(0).getPassword())) {// 如果新密码和旧密码相同，提示用户，且不进行更新操作
					flag = "S";
					hashMap.put("flag", flag);
					return getModelAndAjaxJsonView(hashMap);
				} else if (!checknewpwd.equals(ci.getPassword())) {// 如果两次新密码不一致
					flag = "T";
					hashMap.put("flag", flag);
					return getModelAndAjaxJsonView(hashMap);
				} else {
					ci.setPassword(MD5.md5(ci.getPassword()));
				}
			} else {// 旧密码错误
				flag = "E";
				hashMap.put("flag", flag);
				return getModelAndAjaxJsonView(hashMap);
			}
		}
		boolean sucOrFail = true;
		try {
			sucOrFail = userInfoService.update(ci);
		} catch (DaoException e) {
			flag = "N";
			log.error(e);
		}
		if (!sucOrFail) {
			flag = "N";
		}
		hashMap.put("flag", flag);
		return getModelAndAjaxJsonView(hashMap);
	}

	/**
	 * 获取在线用户session
	 * 
	 * @param reqeust
	 * @param response
	 * @return
	 */
	@RequestMapping("getOnlineUserSession.htm")
	public ModelAndView getOnlineUserSession(HttpServletRequest request, HttpServletResponse response) {
		String sessionKey = request.getParameter("sessionKey");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = null;
			JSONObject json = new JSONObject();
			CommonUserDetails user;
			if (StringUtils.isNotBlank(sessionKey)) {
				user = UserUtil.isSessionLogin(getSessionRegistry(), sessionKey);
				if (user != null) {
					json.put("userId", user.getUsername());
					// json.put("loginIP", user.getLoginIP());
					json.put("ticketKey", user.getTicketKey());
				} else {
					json.put("userId", "");
					json.put("loginIP", "");
					json.put("ticketKey", sessionKey);
				}

			} else {
				json.put("userId", "");
				json.put("loginIP", "");
				json.put("ticketKey", sessionKey);
				log.error("获取在线用户session出错,sessionKey为空,sessionId=" + sessionKey);
			}
			out = response.getWriter();
			out.write(json.toString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("获取在线用户session出错,sessionId=" + sessionKey, e);
		}
		return null;
	}

	/**
	 * 修改
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("setUserManage.htm")
	public ModelAndView setUserManage(HttpServletRequest req, HttpServletResponse res) {
		UserInfo ci = new UserInfo();
		bind(req, ci);

		boolean sucOrFail = true;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ci.getId());
		// if (!list.get(0).getPassword().equalsIgnoreCase(ci.getPassword())) {
		// ci.setPassword(MD5.md5(ci.getPassword()));
		// }
		try {
			sucOrFail = userInfoService.update(ci);
			getCustomSecurityMetadataSource().loadResourceDefine();
		} catch (DaoException e) {
			log.error(e);
			return null;
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(sucOrFail);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("updatePwd.htm")
	public ModelAndView updatePwd(HttpServletRequest req, HttpServletResponse res) {
		UserInfo ci = new UserInfo();
		bind(req, ci);

		boolean sucOrFail = true;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ci.getId());
		map.put("password", ci.getPassword());
		try {
			sucOrFail = userInfoService.updateByMap(map);
		} catch (DaoException e) {
			log.error(e);
			return null;
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(sucOrFail);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}
	@RequestMapping("delUserManage.htm")
	public ModelAndView delUserManage(HttpServletRequest req, HttpServletResponse res) {
		UserInfo ci = new UserInfo();
		bind(req, ci);
		UserSearchCriteria sc = new UserSearchCriteria();
		sc.setAccount(ci.getAccount());
		boolean sucOrFail = true;
		try {
			sucOrFail = userInfoService.delete(ci);
			getCustomSecurityMetadataSource().loadResourceDefine();
		} catch (DaoException e) {
			log.error(e);
			return null;
		}
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(sucOrFail);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}
	@RequestMapping("viewUserManage.htm")
	public ModelAndView viewUserManage(HttpServletRequest req, HttpServletResponse res) {
		UserInfo csc = new UserInfo();
		bind(req, csc);
		UserInfo evList = userInfoService.findById(csc.getId());
		ModelAndView mv = new ModelAndView("/main/user/usermgr/viewUser");
		mv.addObject("data", evList);
		return mv;
	}
}
