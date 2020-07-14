package com.xiaobaozi.client.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.usermanage.search.RoleSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.service.RoleService;
import com.xiaobaozi.dataSystem.usermanage.vo.Role;
import com.xiaobaozi.dataSystem.usermanage.vo.UserInfo;

/**
 * 角色管理控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

	protected static Logger log = Logger.getLogger(RoleController.class);
	@Resource
	private RoleService roleService;


	/**
	 * 保存角色与权限的关联关系
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("saveConnection.htm")
	public ModelAndView saveConnection(HttpServletRequest request, HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String[] funIDs = request.getParameter("funIDs").split(",");
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (String funId : funIDs) {
			if (!"".equals(funId)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleId", roleId);
				map.put("functionId", funId);
				maps.add(map);
			}
		}
		boolean suc = roleService.connectRoleFunction(maps) > 0;
		getCustomSecurityMetadataSource().loadResourceDefine();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(suc);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 跳转到分配权限窗口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("connectRoleFun.htm")
	public ModelAndView connectRoleFun(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/user/role/roleFunedit");
		mv.addObject("roleId", request.getParameter("roleId"));
		return mv;
	}

	/**
	 * 根据条件查询用户
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("searchRole.htm")
	public ModelAndView searchRole(HttpServletRequest request, HttpServletResponse response) {
		RoleSearchCriteria csc = new RoleSearchCriteria();
		bind(request, csc);
		String roleName = csc.getRoleName().trim();
		csc.setRoleName(roleName);

		// sc.setPageSize(1);
		// csc.setRolePales(csc.getRolePale().split(","));
		Integer count = roleService.getRoleCount(csc);
		csc.setTotalCount(count); // 设置记录总数
		List<Role> evlist = roleService.getRoleListByPage(csc);
		/*
		 * Map map = ResponseUtil.getTableMap(evlist, sc); return new
		 * ModelAndView(new AjaxJsonView(), map);
		 */
		String data = JsonUtil.convertToArrayJson(evlist);
		ModelAndView mv = new ModelAndView("main/user/role/rolebody");
		mv.addObject("data", data);
		mv.addObject("sc", csc);
		return mv;
	}

	/**
	 * 根据id查询角色，并返回json数据
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("getRoleByID.htm")
	public ModelAndView getRoleByID(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", req.getParameter("roleID"));
		List<Role> list = roleService.listByMap(map);
		Role reqInfo = null;
		if (list.size() > 0)
			reqInfo = list.get(0);
		else
			reqInfo = new Role();

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

	/**
	 * 根据groupid查询角色，并返回数据
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("getAllRole.htm")
	public ModelAndView getAllRole(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> roleMap = new HashMap<String, Object>();
		// roleMap.put("pale", user.getCorpLevel());
		// roleMap.put("status", "Y");
		List<Role> roleList = roleService.listAll();

		// 处理后得到符合当前用户组适用等级的角色
		// String[] gpales = req.getParameter("groupPale").split(",");

		String listJSON = JSONArray.fromObject(roleList).toString();
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.println(listJSON);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 跳转角色信息页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("rolePage.htm")
	public ModelAndView rolePage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/user/role/role");
	}

	/**
	 * 新增角色信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("addRole.htm")
	public ModelAndView addRole(HttpServletRequest req, HttpServletResponse res) {
		Role ci = new Role();
		bind(req, ci);
		ci.setRoleId(UUIDUtil.getUUID());
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		ci.setCreateName(user.getFullname());
		boolean sucOrFail = true;
		try {
			if (roleService.insert(ci) == 1) {
				sucOrFail = true;
			} else {
				sucOrFail = false;
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
	 * 修改角色
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("editRole.htm")
	public ModelAndView editRole(HttpServletRequest req, HttpServletResponse res) {
		Role ci = new Role();
		bind(req, ci);
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		ci.setCreateName(user.getUsername());
		boolean sucOrFail = true;
		try {
			sucOrFail = roleService.update(ci);
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

	@SuppressWarnings("unused")
	@RequestMapping("delRole.htm")
	public ModelAndView delRole(HttpServletRequest req, HttpServletResponse res) {
		Role ci = new Role();
		bind(req, ci);
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		boolean sucOrFail = true;
		try {
			sucOrFail = roleService.delete(ci);
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
	 * 停用/启用 角色
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("setRoleStatus.htm")
	public ModelAndView setRoleStatus(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String status = req.getParameter("newStatus");
		String ids = req.getParameter("roleIDs");
		String idArr[] = {};
		boolean suc = true;
		if (ids != null)
			idArr = ids.split(",");
		for (int i = 0; i < idArr.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			map.put("roleID", idArr[i]);
			suc = roleService.updateByMap(map);
		}
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

	@RequestMapping("viewRole.htm")
	public ModelAndView viewRole(HttpServletRequest req, HttpServletResponse res) {
		Role csc = new Role();
		bind(req, csc);
		Role evList = roleService.findById(csc.getRoleId());
		ModelAndView mv = new ModelAndView("/main/user/role/viewRole");
		mv.addObject("data", evList);
		return mv;
	}

	/**
	 * 跳转增加角色页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("addView.htm")
	public ModelAndView addView(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/user/role/addRole");
	}
}
