package com.xiaobaozi.client.controller;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.record.pojo.BrowseRecord;
import com.xiaobaozi.dataSystem.record.search.BrowseRecordSearch;
import com.xiaobaozi.dataSystem.record.service.BrowseRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("record")
public class BrowseRecordController extends BaseController {

	@Resource
	private BrowseRecordService browseRecordService;

	/**
	 * 保障类型
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("browseRecordPage.htm")
	public ModelAndView browseRecordPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/record/browseRecord");
		return mv;
	}

	@RequestMapping("serachBrowseRecord.htm")
	public ModelAndView serachBrowseRecord(HttpServletRequest request, HttpServletResponse response) {
		BrowseRecordSearch sc = new BrowseRecordSearch();
		ModelAndView mv = new ModelAndView("main/record/browseRecordBody");
		bind(request, sc);
		int count = browseRecordService.getBrowseRecordCount(sc);
		sc.setTotalCount(count);
		List<BrowseRecord> list = browseRecordService.getBrowseRecordByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

}
