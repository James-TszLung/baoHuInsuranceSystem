package com.xiaobaozi.client.controller.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryService;
import com.xiaobaozi.dataSystem.essay.pojo.EssayRelationDictionary;
import com.xiaobaozi.dataSystem.essay.pojo.PushEssay;
import com.xiaobaozi.dataSystem.essay.search.PushEssaySearch;
import com.xiaobaozi.dataSystem.essay.service.PushEssayService;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeBase;
import com.xiaobaozi.dataSystem.knowledge.pojo.KnowledgeRelationPushEssay;
import com.xiaobaozi.dataSystem.knowledge.search.KnowledgeBaseSearch;
import com.xiaobaozi.dataSystem.knowledge.service.KnowledgeBaseService;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;
import com.xiaobaozi.dataSystem.systemManager.service.PictureInfoService;
import com.xiaobaozi.dataSystem.video.pojo.VideoManager;
import com.xiaobaozi.dataSystem.video.pojo.VideoRelationDictionary;
import com.xiaobaozi.dataSystem.video.search.VideoManagerSearch;
import com.xiaobaozi.dataSystem.video.service.VideoManagerService;

/**
 * 內容维护
 * 
 * @author media
 * 
 */
@Controller
@RequestMapping("content")
public class BaohuContentController extends BaseController {

	@Resource
	RestTemplate restTemplate;
	@Resource
	private PictureInfoService pictureInfoService;
	@Resource
	private PushEssayService pushEssayService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private KnowledgeBaseService knowledgeBaseService;
	@Resource
	private VideoManagerService videoManagerService;

	@RequestMapping("contenttest.htm")
	public void contenttest(HttpServletRequest request, HttpServletResponse response) {

		String forObject = restTemplate.getForObject("http://localhost:8763/product/Hello/name=" + "missu", String.class);
		System.out.println(forObject);
		System.out.println("ok");

	}

	/*
	 * 推文
	 */
	@RequestMapping("pushEssayPage.htm")
	public ModelAndView navigationPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv =new ModelAndView("main/pushEssay/pushEssay");
		String id = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pushEssay.type.id");
		List<DictionaryContent> list = dictionaryService.selectBydictionaryId(id);
		mv.addObject("typeLs", list);
		return mv;
	}

	@RequestMapping("serachPushEssay.htm")
	public ModelAndView serachNavigation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/pushEssay/pushEssayBody");
		PushEssaySearch sc = new PushEssaySearch();
		bind(request, sc);
		int count = pushEssayService.getPushEssayCount(sc);
		sc.setTotalCount(count);
		List<PushEssay> list = pushEssayService.getPushEssayByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("goSavepushEssayPage.htm")
	public ModelAndView goSavepushEssayPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/pushEssay/savePushEssay");
		String id = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pushEssay.type.id");
		String labelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pushEssay.label.id");
		List<DictionaryContent> list = dictionaryService.selectBydictionaryId(id);
		List<DictionaryContent> listLabel = dictionaryService.selectBydictionaryId(labelId);
		mv.addObject("list", list);
		mv.addObject("listLabel", listLabel);
		return mv;
	}

	@RequestMapping("dosavepushEssay.htm")
	public void dosaveCompany(HttpServletRequest request, HttpServletResponse response) {
		PushEssay sc = new PushEssay();
		bind(request, sc);
		if (sc.getHomePageXcxflag() == 0) {
			sc.setActivityImage1("");
		} else if (sc.getColumnXcxflag() == 0) {
			sc.setActivityImage2("");
		} else if (sc.getWebsiteflag() == 0) {
			sc.setActivityImage3("");
		}
		try {
			pushEssayService.insertPushEssay(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("goEditpushEssayPage.htm")
	public ModelAndView goEditpushEssayPage(HttpServletRequest req, HttpServletResponse res) {
		String pushEssayId = req.getParameter("id");
		ModelAndView mv = new ModelAndView("main/pushEssay/editPushEssay");
		String id = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pushEssay.type.id");
		String labelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "pushEssay.label.id");
		List<DictionaryContent> list = dictionaryService.selectBydictionaryId(id);
		List<DictionaryContent> listLabel = dictionaryService.selectBydictionaryId(labelId);
		mv.addObject("list", list);
		mv.addObject("listLabel", listLabel);
		PushEssay sc = pushEssayService.findById(pushEssayId);
		if (sc.getEssayRelationDictionaryLs() != null && sc.getEssayRelationDictionaryLs().size() > 0) {
			List<EssayRelationDictionary> relationDictionaryLs = sc.getEssayRelationDictionaryLs();
			for (int i = 0; i < relationDictionaryLs.size(); i++) {
				for (int j = 0; j < listLabel.size(); j++) {
					if (relationDictionaryLs.get(i).getDictionaryId().equals(listLabel.get(j).getId())) {
						relationDictionaryLs.get(i).setContent(listLabel.get(j).getContent());
						listLabel.get(j).setFlag(true);
					}
				}
			}
			String jsonString = JSON.toJSONString(sc.getEssayRelationDictionaryLs());
			mv.addObject("data", jsonString);
		}
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("updateCompany.htm")
	public void updateCompany(HttpServletRequest request, HttpServletResponse response) {
		PushEssay sc = new PushEssay();
		bind(request, sc);
		if (sc.getHomePageXcxflag() == 0) {
			sc.setActivityImage1("");
		} else if (sc.getColumnXcxflag() == 0) {
			sc.setActivityImage2("");
		} else if (sc.getWebsiteflag() == 0) {
			sc.setActivityImage3("");
		}
		try {
			pushEssayService.updatePushEssay(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping("updateSort.htm")
	public void updateSort(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("ids");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			if(ids!=null && ids.length>0){
                for(int i=0;i<ids.length;i++){
					PushEssay pushEssay = pushEssayService.findById(ids[i]);
					if(pushEssay!=null){
						String sortStr = request.getParameter("sort"+pushEssay.getId());
						int sort = 0 ;
						if(StringUtils.isNotBlank(sortStr)){
							sort = Integer.valueOf(sortStr);
						}
						pushEssay.setSort(sort);
						pushEssayService.update(pushEssay);
					}
				}
			}
			retMap.put("success", "1");
			retMap.put("mes", "保存成功");
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存失败");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response,retMap);
	}


	/**
	 * 知识库
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("pushknowledgeBasePage.htm")
	public ModelAndView pushknowledgeBasePage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/KnowledgeBase/KnowledgeBase");
	}

	@RequestMapping("serachKnowledgeBase.htm")
	public ModelAndView serachKnowledgeBase(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/KnowledgeBase/KnowledgeBaseBody");
		KnowledgeBaseSearch sc = new KnowledgeBaseSearch();
		bind(request, sc);
		int count = knowledgeBaseService.getKnowledgeBaseCount(sc);
		sc.setTotalCount(count);
		List<KnowledgeBase> list = knowledgeBaseService.getKnowledgeBaseByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("goSaveKnowledgeBasePage.htm")
	public ModelAndView goSaveKnowledgeBasePage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/KnowledgeBase/saveKnowledgeBase");
		return mv;
	}

	/**
	 * 推文数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("serachPushEssay2.htm")
	public ModelAndView serachPushEssay(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/main/KnowledgeBase/pushEssayRelationBody");
		PushEssaySearch sc = new PushEssaySearch();
		bind(request, sc);
		String title = sc.getTitle().trim();
		sc.setTitle(title);
		// 服务项目id
		String proIds = request.getParameter("serviceProIds");
		String[] serviceProIds = proIds.split(",");
		List<String> idList = new ArrayList<String>();
		if (serviceProIds.length > 0) {
			for (int i = 0; i < serviceProIds.length; i++) {
				idList.add(serviceProIds[i]);
			}
		}
		if (idList != null && idList.size() > 0) {
			sc.setSingleServiceIds(idList);
		}
		int count = pushEssayService.getPushEssayCount(sc);
		sc.setTotalCount(count);
		List<PushEssay> list = pushEssayService.getPushEssayByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("dosaveKnowledgeBase.htm")
	public void dosaveKnowledgeBase(HttpServletRequest request, HttpServletResponse response) {
		KnowledgeBase sc = new KnowledgeBase();
		bind(request, sc);
		try {
			knowledgeBaseService.insertKnowledgeBase(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("goEditKnowledgeBasePage.htm")
	public ModelAndView goEditKnowledgeBasePage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/KnowledgeBase/editKnowledgeBase");
		String id = req.getParameter("id");
		KnowledgeBase sc = knowledgeBaseService.findById(id);
		List<KnowledgeRelationPushEssay> list = sc.getKonwRelationLs();
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("doUpdateKnowledgeBase.htm")
	public void doUpdateKnowledgeBase(HttpServletRequest request, HttpServletResponse response) {
		KnowledgeBase sc = new KnowledgeBase();
		bind(request, sc);
		try {
			knowledgeBaseService.updateKnowledgeBase(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("deleteKnowledgeBaseById.htm")
	public void deleteKnowledgeBaseById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			knowledgeBaseService.deleById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 视频管理
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("videoManagerPage.htm")
	public ModelAndView pushVideoManagerPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/videoManager/videoManager");
	}

	@RequestMapping("serachVideoManager.htm")
	public ModelAndView serachVideoManager(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/videoManager/videoManagerBody");
		VideoManagerSearch sc = new VideoManagerSearch();
		bind(request, sc);
		int count = videoManagerService.getVideoManagerCount(sc);
		sc.setTotalCount(count);
		List<VideoManager> list = videoManagerService.getVideoManagerByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("goSaveVideoManagerPage.htm")
	public ModelAndView goSaveVideoManagerPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/videoManager/saveVideoManager");
		String id = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "video.type.dictionaryid");
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "video.category.dictionaryid");
		List<DictionaryContent> listLabel = dictionaryService.selectBydictionaryId(id);
		List<DictionaryContent> list = dictionaryService.selectBydictionaryId(typeId);
		mv.addObject("listLabel", listLabel);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("dosaveVideoManager.htm")
	public void dosaveVideoManager(HttpServletRequest request, HttpServletResponse response) {
		VideoManager sc = new VideoManager();
		bind(request, sc);
		try {
			videoManagerService.insertVideoManager(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("goEditVideoManagerPage.htm")
	public ModelAndView goEditVideoManagerPage(HttpServletRequest req, HttpServletResponse res) {
		String pushEssayId = req.getParameter("id");
		ModelAndView mv = new ModelAndView("main/videoManager/editVideoManager");
		String id = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "video.category.dictionaryid");
		String labelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "video.type.dictionaryid");
		List<DictionaryContent> list = dictionaryService.selectBydictionaryId(id);
		List<DictionaryContent> listLabel = dictionaryService.selectBydictionaryId(labelId);
		mv.addObject("list", list);
		mv.addObject("listLabel", listLabel);
		VideoManager sc = videoManagerService.findById(pushEssayId);
		if (sc.getVideoRelationDictionaryLs() != null && sc.getVideoRelationDictionaryLs().size() > 0) {
			List<VideoRelationDictionary> relationDictionaryLs = sc.getVideoRelationDictionaryLs();
			for (int i = 0; i < relationDictionaryLs.size(); i++) {
				for (int j = 0; j < listLabel.size(); j++) {
					if (relationDictionaryLs.get(i).getDictionaryId().equals(listLabel.get(j).getId())) {
						relationDictionaryLs.get(i).setContent(listLabel.get(j).getContent());
						listLabel.get(j).setFlag(true);
					}
				}
			}
			String jsonString = JSON.toJSONString(sc.getVideoRelationDictionaryLs());
			mv.addObject("data", jsonString);
		}
		if (StringUtils.isNotBlank(sc.getVideoId())) {
			PictureInfo videoP = pictureInfoService.findById(sc.getVideoId());
			mv.addObject("videoP", videoP);
		}
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("updateVideoManager.htm")
	public void updateVideoManager(HttpServletRequest request, HttpServletResponse response) {
		VideoManager sc = new VideoManager();
		bind(request, sc);
		try {
			videoManagerService.updateVideoManager(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("deleteVideoManagerById.htm")
	public void deleteVideoManagerById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			videoManagerService.deleById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("deletePushEssayById.htm")
	public void deletePushEssayById(HttpServletRequest request, HttpServletResponse response) {
		PushEssay sc = new PushEssay();
		bind(request, sc);
		try {
			pushEssayService.deletePushEssayById(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
