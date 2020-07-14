package com.xiaobaozi.client.controller.custom;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.utils.DateUtil;
import com.xiaobaozi.commons.utils.ExcelUtil;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.customizePlan.pojo.CustomizePlan;
import com.xiaobaozi.dataSystem.customizePlan.search.CustomizePlanSearch;
import com.xiaobaozi.dataSystem.customizePlan.service.CustomizePlanService;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryContentService;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("customizeplan")
public class CustomizePlanController extends BaseController {

	@Resource
	private CustomizePlanService customizePlanService;
	@Resource
	private DictionaryContentService dictionaryContentService;

	private static Logger log = Logger.getLogger(CustomizePlanController.class);

	/**
	 * 定制方案
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("customizePlanPage.htm")
	public ModelAndView customizePlanPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/custom/customizePlan");
	}

	@RequestMapping("serachCustomizePlan.htm")
	public ModelAndView serachCustomizePlan(HttpServletRequest request, HttpServletResponse response) {
		CustomizePlanSearch sc = new CustomizePlanSearch();
		ModelAndView mv = new ModelAndView("main/custom/customizePlanBody");
		bind(request, sc);
		int count = customizePlanService.getCustomizePlanCount(sc);
		sc.setTotalCount(count);
		List<CustomizePlan> list = customizePlanService.getCustomizePlanByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}


	/**
	 * 定制方案：新增页面
	 */
	@RequestMapping("goAddCustomizePlanPage.htm")
	public ModelAndView goAddCustomizePlanPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/custom/addCustomizePlan");
		//风险类别
		String riskTypeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "risk.type.dictionaryid");
		List<DictionaryContent> riskTypeList = dictionaryContentService.selectBydictionaryId(riskTypeId);
		String riskTypeData = JsonUtil.convertToArrayJson(riskTypeList);
		mv.addObject("riskTypeList", riskTypeList);
		mv.addObject("riskTypeData", riskTypeData);
        //保险期限/缴费期限
        String payTermId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pay.term.dictionaryid");
        List<DictionaryContent> payTermList = dictionaryContentService.selectBydictionaryId(payTermId);
        mv.addObject("payTermList", payTermList);
        //推荐原因
        String recommentCauseId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "recomment.cause.dictionaryid");
        List<DictionaryContent> recommentCauseList = dictionaryContentService.selectBydictionaryId(recommentCauseId);
        mv.addObject("recommentCauseList", recommentCauseList);
		return mv;
	}

	/**
	 * 定制方案新增保存
	 */
	@RequestMapping("saveCustomizePlan.htm")
	public void saveCustomizePlan(HttpServletRequest request, HttpServletResponse response) {
		CustomizePlan sc = new CustomizePlan();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
			sc.setCreateName(user.getFullname());
			sc.setCreateById(user.getUsername());
			sc.setUpdateName(user.getFullname());
			sc.setUpdateById(user.getUsername());
			customizePlanService.insertCustomizePlan(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存");
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存异常");
			log.error("定制方案新增异常", e);
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 定制方案：修改页面
	 */
	@RequestMapping("goEditCustomizePlanPage.htm")
	public ModelAndView goEditCustomizePlanPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/custom/editCustomizePlan");
		String id = req.getParameter("id");
		CustomizePlan customizePlan = customizePlanService.getCustomizePlanById(id);
		mv.addObject("sc", customizePlan);
		//风险类别
		String riskTypeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "risk.type.dictionaryid");
		List<DictionaryContent> riskTypeList = dictionaryContentService.selectBydictionaryId(riskTypeId);
		String riskTypeData = JsonUtil.convertToArrayJson(riskTypeList);
		mv.addObject("riskTypeList", riskTypeList);
		mv.addObject("riskTypeData", riskTypeData);
		//保险期限/缴费期限
		String payTermId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pay.term.dictionaryid");
		List<DictionaryContent> payTermList = dictionaryContentService.selectBydictionaryId(payTermId);
		mv.addObject("payTermList", payTermList);
		//推荐原因
		String recommentCauseId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "recomment.cause.dictionaryid");
		List<DictionaryContent> recommentCauseList = dictionaryContentService.selectBydictionaryId(recommentCauseId);
		mv.addObject("recommentCauseList", recommentCauseList);
		return mv;
	}

	/**
	 * 定制方案修改保存
	 */
	@RequestMapping("editCustomizePlan.htm")
	public void editCustomizePlan(HttpServletRequest request, HttpServletResponse response) {
		CustomizePlan sc = new CustomizePlan();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
			sc.setUpdateName(user.getFullname());
			sc.setUpdateById(user.getUsername());
			customizePlanService.updateCustomizePlan(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存");
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存异常");
			log.error("定制方案新增异常", e);
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 定制方案：删除
	 */
	@RequestMapping("deletecustomizeplanbyid.htm")
	public void deleteKnowledgeBaseById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			CustomizePlan customizePlan = customizePlanService.getCustomizePlanById(id);
			customizePlanService.deleteCustomizePlanById(customizePlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 导出定制方案excel
	 *
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("exportExcel.htm")
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		CustomizePlanSearch sc = new CustomizePlanSearch();
		bind(req, sc);
		Integer count = customizePlanService.getCustomizePlanCount(sc);
		sc.setTotalCount(count);
		if(sc.getPageSize()>1000){
			sc.setPageSize(1000);
		}
		List<CustomizePlan> list = customizePlanService.getCustomizePlanByPage(sc);
		List<Object[]> objectList = new ArrayList<Object[]>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CustomizePlan customizePlan = list.get(i);
				Object[] object = new Object[5];
				object[0] = customizePlan.getTitle();
				object[1] = customizePlan.getName();
				object[2] = customizePlan.getPhone();
				object[3] = customizePlan.getUserCount();
				object[4] = DateUtil.getDate(customizePlan.getCreateTime(),"yyyy-MM-dd HH-mm-ss");
				objectList.add(object);
			}
		}
		// 设置响应
		res.setContentType("application/force-download");
		try {
			res.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("保乎笔记定制方案数据_", "UTF-8")
					+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}// 设置文件名;
		OutputStream out = null;
		try {
			out = res.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExcelUtil excelUtil = new ExcelUtil();
		String[] header = new String[] { "方案标题", "客户姓名", "联系电话", "定制对象人数", "定制时间"};
		excelUtil.exportExcel("保乎笔记定制方案数据_" + new SimpleDateFormat("yyyyMMdd").format(new Date()), header, objectList, out, null);
		return null;
	}
}
