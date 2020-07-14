package com.xiaobaozi.client.controller;

import com.alibaba.fastjson.JSONArray;
import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.commons.utils.GeliFunctions;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryContentService;
import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;
import com.xiaobaozi.dataSystem.indemnity.search.IndemnitySearch;
import com.xiaobaozi.dataSystem.indemnity.service.IndemnityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("indemnity")
public class IndemnityController extends BaseController {

	@Resource
	private IndemnityService indemnityService;
	@Resource
	DictionaryContentService dictionaryContentService;

	/**
	 * 保障类型
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("indemnityPage.htm")
	public ModelAndView indemnityPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/indemnity/indemnity");
		//保险类型
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "product.type.dictionaryid");
		List<DictionaryContent> list = dictionaryContentService.selectBydictionaryId(typeId);
		mv.addObject("typeLs", list);
		return mv;
	}

	@RequestMapping("serachIndemnity.htm")
	public ModelAndView serachIndemnity(HttpServletRequest request, HttpServletResponse response) {
		IndemnitySearch sc = new IndemnitySearch();
		ModelAndView mv = new ModelAndView("main/indemnity/indemnityBody");
		bind(request, sc);
		int count = indemnityService.getIndemnityCount(sc);
		sc.setTotalCount(count);
		List<Indemnity> list = indemnityService.getIndemnityByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}


	/**
	 * 保障类型：新增页面
	 */
	@RequestMapping("goAddIndemnityPage.htm")
	public ModelAndView goAddIndemnityPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/indemnity/addIndemnity");
		//保险类型
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "product.type.dictionaryid");
		List<DictionaryContent> list = dictionaryContentService.selectBydictionaryId(typeId);
		mv.addObject("typeLs", list);
		return mv;
	}

	/**
	 * 保障类型新增保存
	 */
	@RequestMapping("saveIndemnity.htm")
	public void saveIndemnity(HttpServletRequest request, HttpServletResponse response) {
		Indemnity sc = new Indemnity();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("dictionaryContentId",sc.getDictionaryContentId());
			map.put("content",sc.getContent());
			Indemnity indemnity = indemnityService.selectBy(map);
			if(indemnity!=null){
				retMap.put("success", "2");
				retMap.put("mes", "保存失败，产品类型["+indemnity.getDictionaryContent().getContent()+"]：保障类型["+indemnity.getContent()+"]已存在");
			}else{
				CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
				sc.setCreateName(user.getFullname());
				sc.setCreateById(user.getUsername());
				sc.setUpdateName(user.getFullname());
				sc.setUpdateById(user.getUsername());
				indemnityService.insertIndemnity(sc);
				retMap.put("success", "1");
				retMap.put("mes", "保存成功");
			}

		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存失败");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 保障类型：修改页面
	 */
	@RequestMapping("goEditIndemnityPage.htm")
	public ModelAndView goEditIndemnityPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/indemnity/editIndemnity");
		//保险类型
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "product.type.dictionaryid");
		List<DictionaryContent> list = dictionaryContentService.selectBydictionaryId(typeId);
		mv.addObject("typeLs", list);
		String id = req.getParameter("id");
		Indemnity indemnity = indemnityService.findById(id);
		mv.addObject("sc",indemnity);
		return mv;
	}

	/**
	 * 保障类型修改保存
	 */
	@RequestMapping("editIndemnity.htm")
	public void editIndemnity(HttpServletRequest request, HttpServletResponse response) {
		Indemnity sc = new Indemnity();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("dictionaryContentId",sc.getDictionaryContentId());
			map.put("content",sc.getContent());
			map.put("id",sc.getId());
			Indemnity indemnity = indemnityService.selectBy(map);
			if(indemnity!=null){
				retMap.put("success", "2");
				retMap.put("mes", "保存失败，产品类型["+indemnity.getDictionaryContent().getContent()+"]：保障类型["+indemnity.getContent()+"]已存在");
			}else{
				CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
				sc.setUpdateName(user.getFullname());
				sc.setUpdateById(user.getUsername());
				indemnityService.updateIndemnity(sc);
				retMap.put("success", "1");
				retMap.put("mes", "保存成功");
			}
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存异常");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}


	//保障类型选择 acbox
	@RequestMapping("selectIndemnity.htm")
	public void selectIndemnity(HttpServletRequest req, HttpServletResponse res) {
		IndemnitySearch sc = new IndemnitySearch();
		String qWord = req.getParameter("q_word");
		int numPerPage = StringUtils.isNotBlank(req.getParameter("numPerPage")) ? Integer.parseInt(req.getParameter("numPerPage")) : 25 ;
		bind(req, sc);
		String insuranceTypeId = req.getParameter("insuranceTypeId");
		sc.setDictionaryContentId(insuranceTypeId);
		sc.setContent(qWord);
		int count = indemnityService.getIndemnityCount(sc);
		sc.setTotalCount(count);
		sc.setPageSize(numPerPage);
		List<Indemnity> list = indemnityService.getIndemnityByPage(sc);
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray jsonArray = JSONArray.parseArray(GeliFunctions.toJSONString(list, new String[]{"id","content>name"}));
		map.put("result", jsonArray);
		map.put("acbox", "y");
		map.put("total", count);
		JsonUtil.ajaxReturn(res, map);
	}

	//根据保障类型id获取子项目
	@RequestMapping("selectIndemnitySub.htm")
	public void selectIndemnitySub(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		Indemnity indemnity = indemnityService.findById(id);
		List<IndemnitySub> subs = indemnity.getIndemnitySubLs();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subLs", subs);
		JsonUtil.ajaxReturn(res, map);
	}

	/**
	 * 保障类型：复制
	 */
	@RequestMapping("copyIndemnity.htm")
	public void copyIndemnity(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Indemnity oldIndemnity = indemnityService.findById(id);
			String content = "【可选】" + oldIndemnity.getContent();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("dictionaryContentId",oldIndemnity.getDictionaryContentId());
			map.put("content",content);
			Indemnity indemnity = indemnityService.selectBy(map);
			if(indemnity!=null){
				retMap.put("success", "2");
				retMap.put("mes", "保存失败，产品类型["+indemnity.getDictionaryContent().getContent()+"]：保障类型【"+content+"】已存在");
			}else{
				Indemnity newIndemnity = new Indemnity();
				newIndemnity.setContent(content);
				newIndemnity.setDictionaryContentId(oldIndemnity.getDictionaryContentId());
				newIndemnity.setSort(oldIndemnity.getSort()+1);
				newIndemnity.setIndemnitySubLs(oldIndemnity.getIndemnitySubLs());
				CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
				newIndemnity.setCreateName(user.getFullname());
				newIndemnity.setCreateById(user.getUsername());
				newIndemnity.setUpdateName(user.getFullname());
				newIndemnity.setUpdateById(user.getUsername());
				indemnityService.insertIndemnity(newIndemnity);
				retMap.put("success", "1");
				retMap.put("mes", "复制成功");
			}
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "复制失败");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 保障类型：删除
	 */
	@RequestMapping("deleteIndemnity.htm")
	public void deleteIndemnity(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Indemnity indemnity = indemnityService.findById(id);
			int countRelation = indemnityService.countRelation(indemnity.getId());
			if(countRelation>0){
				retMap.put("success", "2");
				retMap.put("mes", "无法删除，保障类型和产品已存在关联关系");
			}else{
				indemnityService.deleteIndemnityById(indemnity);
				retMap.put("success", "1");
				retMap.put("mes", "删除成功");
			}
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "删除失败");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}


}
