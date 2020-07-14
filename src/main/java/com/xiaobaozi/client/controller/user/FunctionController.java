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

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.utils.MenuUtil;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.usermanage.search.FunctionSearchCriteria;
import com.xiaobaozi.dataSystem.usermanage.service.FunctionService;
import com.xiaobaozi.dataSystem.usermanage.vo.Function;

/**
 * 模块管理控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("function")
public class FunctionController extends BaseController {

	protected static Logger log = Logger.getLogger(FunctionController.class);
    
	@Resource
	private FunctionService functionService;


	/**
	 * 读取所有模块，转换成JSON数组，用于分配权限界面读取
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("queryAllFun.htm")
	public ModelAndView queryAllFun(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderBy", props.getProperty("order.col.for.menu"));
		params.put("roleId", req.getParameter("roleId"));
		params.put("menu", "all");
		if (StringUtils.isNotBlank(req.getParameter("type")))
			params.put("typeID", req.getParameter("type"));
		List<Function> menus = functionService.listByMap(params);
		String menuJson = "";
		String mod = req.getParameter("mode");
		if (mod != null && mod.equalsIgnoreCase("all")) {
//			Function root = new Function();
//			root.setId("0");
//			root.setFunName("根节点");
//			menus.add(root);
			menuJson = MenuUtil.parseMenuObjToJson(menus,true);
		}else{
			menuJson = MenuUtil.parseMenuObjToJson(menus,false);
		}
		menuJson = menuJson.replaceAll("id", "id").replaceAll("funName", "text").replaceAll("childs", "children")
				.replace("\"true\"", "true").replace("\"false\"", "false");

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
	 * 根据条件查询模块
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("searchFunction.htm")
	public ModelAndView searchFunction(HttpServletRequest request, HttpServletResponse response) {
		FunctionSearchCriteria csc = new FunctionSearchCriteria();
		bind(request, csc);

		String functionName = csc.getFunName().trim();
		csc.setFunName(functionName);
		
		// 设置排序字段
		csc.setOrderBy(props.getProperty("order.col.for.menu"));
		Integer count = functionService.getFunctionCount(csc);
		csc.setTotalCount(count); // 设置记录总数
		List<Function> evlist = functionService.getFunctionListByPage(csc);
		/*
		 * Map map = ResponseUtil.getTableMap(evlist, sc); return new
		 * ModelAndView(new AjaxJsonView(), map);
		 */
		String data = JsonUtil.convertToArrayJson(evlist);
		ModelAndView mv = new ModelAndView("main/user/function/functionbody");
		mv.addObject("data", data);
		mv.addObject("sc", csc);
		return mv;
	}

	/**
	 * 根据id查询模块，并返回json数据
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("getFunctionByID.htm")
	public ModelAndView getFunctionByID(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("functionid", req.getParameter("functionID"));
		List<Function> list = functionService.listByMap(map);
		Function reqInfo = null;
		if (list.size() > 0)
			reqInfo = list.get(0);
		else
			reqInfo = new Function();

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
	 * 跳转模块信息页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("functionPage.htm")
	public ModelAndView functionPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/user/function/function");
	}
	
	/**
	 * 跳转编辑信息页面
	 */
	@RequestMapping("viewpage.htm")
	public ModelAndView viewpage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/user/function/other/viewFun");
	}

	/**
	 * 跳转请求权限管理页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("otherFunPage.htm")
	public ModelAndView otherFunPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/user/function/other/otherFun");
	}

	/**
	 * 跳转到新增菜单页面
	 */
	@RequestMapping("addView.htm")
	public ModelAndView addView(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("main/user/function/addFun");
	}

	/**
	 * 新增模块信息
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("addFunction.htm")
	public ModelAndView addFunction(HttpServletRequest req, HttpServletResponse res) {
		Function ci = new Function();
		bind(req, ci);
		ci.setType(1);
		ci.setId(UUIDUtil.getUUID());
		boolean sucOrFail = true;
		try {
			if (functionService.insert(ci) == 1) {
				sucOrFail = true;
			} else {
				sucOrFail = false;
			}
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
	 * 修改模块
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("editFunction.htm")
	public ModelAndView editFunction(HttpServletRequest req, HttpServletResponse res) {
		Function ci = new Function();
		bind(req, ci);
		boolean sucOrFail = true;
		try {
			sucOrFail = functionService.update(ci);
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
	 * 删除
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("delFunction.htm")
	public ModelAndView delFunction(HttpServletRequest req, HttpServletResponse res) {
		Function ci = new Function();
		bind(req, ci);
		String id = req.getParameter("id");
		boolean sucOrFail = true;
		try {
			sucOrFail = functionService.delete(ci);
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
	 * 编辑
	 */
	@RequestMapping("viewFunction.htm")
	public ModelAndView viewFunction(HttpServletRequest request, HttpServletResponse response) {
		FunctionSearchCriteria csc = new FunctionSearchCriteria();
		bind(request, csc);
		Function evlist = functionService.findById(csc.getId());
//		String data = JsonUtil.convertToArrayJson(evlist);
		ModelAndView mv = new ModelAndView("main/user/function/other/viewFun");
		mv.addObject("data", evlist);
		return mv;
	}
	
}
