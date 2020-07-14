package com.xiaobaozi.client.controller.configuration;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryContentService;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统配置微服务模块控制器
 * 
 * @author media
 * 
 */
@Controller
@RequestMapping("dictionarycontent")
public class DictionaryContentController extends BaseController {

	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private DictionaryContentService dictionaryContentService;

	//方案概述模板
	@RequestMapping("serachplancontent.htm")
	public ModelAndView serachCompany(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/dictionarycontent/dictionaryContentBody");
		DictionaryContentSearch sc = new DictionaryContentSearch();
		bind(request, sc);
		String tempId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "plan.template.dictionaryid");
		sc.setDictionaryId(tempId);
		int count = dictionaryContentService.getDictionaryContentCount(sc);
		sc.setTotalCount(count);
		List<DictionaryContent> list = dictionaryContentService.getContentByDictionaryId(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}
}
