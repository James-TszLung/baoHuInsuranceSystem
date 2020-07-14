package com.xiaobaozi.client.controller.configuration;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.company.service.InsuranceCompanyService;
import com.xiaobaozi.dataSystem.dictionary.pojo.Dictionary;
import com.xiaobaozi.dataSystem.dictionary.search.DictionarySearch;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryService;

/**
 * 系统配置微服务模块控制器
 * 
 * @author media
 * 
 */
@Controller
@RequestMapping("configuration")
public class ConfigurationController extends BaseController {

	@Resource
	RestTemplate restTemplate;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private InsuranceCompanyService insuranceCompanyService;

	/**
	 * 保险公司
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("companyPage.htm")
	public ModelAndView companyPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/company/company");
	}

	@RequestMapping("serachCompany.htm")
	public ModelAndView serachCompany(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/company/companyBody");
		InsuranceCompanySearch sc = new InsuranceCompanySearch();
		bind(request, sc);
		int count = insuranceCompanyService.getInsuranceCompanyCount(sc);
		sc.setTotalCount(count);
		List<InsuranceCompany> list = insuranceCompanyService.getInsuranceCompanyByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 保险公司
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("goAddCompanyView.htm")
	public ModelAndView goAddCompanyView(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/company/saveCompany");
	}

	@RequestMapping("dosaveCompany.htm")
	public void dosaveCompany(HttpServletRequest request, HttpServletResponse response) {
		InsuranceCompany sc = new InsuranceCompany();
		bind(request, sc);
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		sc.setCreateName(user.getFullname());
		sc.setId(UUIDUtil.getUUID());
		try {
			insuranceCompanyService.insert(sc);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保险公司:修改页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("goEditCompanyView.htm")
	public ModelAndView goEditCompanyView(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/company/editCompany");
		String id = req.getParameter("id");
		InsuranceCompany sc = insuranceCompanyService.findById(id);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 保险公司修改
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("doEditCompany.htm")
	public void doEditCompany(HttpServletRequest request, HttpServletResponse response) {
		InsuranceCompany sc = new InsuranceCompany();
		bind(request, sc);
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		sc.setCreateName(user.getFullname());
		try {
			insuranceCompanyService.update(sc);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数字字典
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("dictionaryPage.htm")
	public ModelAndView dictionaryPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/dictionary/dictionary");
	}

	@RequestMapping("serachDictionary.htm")
	public ModelAndView serachDictionary(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/dictionary/dictionaryBody");
		DictionarySearch sc = new DictionarySearch();
		bind(request, sc);
		int count = dictionaryService.getDictionaryCount(sc);
		sc.setTotalCount(count);
		List<Dictionary> list = dictionaryService.getDictionaryByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 保险公司
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("goAddDictionaryView.htm")
	public ModelAndView goAddDictionaryView(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/dictionary/saveDictionary");
	}

	/**
	 * 数字字典的保存
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("dosaveDictionary.htm")
	public void dosaveDictionary(HttpServletRequest request, HttpServletResponse response) {
		Dictionary sc = new Dictionary();
		bind(request, sc);
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		sc.setCreateName(user.getFullname());
		sc.setCreateById(user.getUsername());
		try {
			dictionaryService.insertDictionary(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数字字典修改页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("goEditDictionary.htm")
	public ModelAndView goEditDictionary(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/dictionary/editDictionary");
		String id = req.getParameter("id");
		Dictionary sc = dictionaryService.findById(id);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("doEditDictionary.htm")
	public void doEditDictionary(HttpServletRequest request, HttpServletResponse response) {
		Dictionary sc = new Dictionary();
		bind(request, sc);
		CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
		sc.setCreateName(user.getFullname());
		sc.setCreateById(user.getUsername());
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			dictionaryService.updateDictionary(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存");
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("success", "2");
			retMap.put("mes", "保存异常");
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

}
