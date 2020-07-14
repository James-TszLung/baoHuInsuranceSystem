package com.xiaobaozi.client.controller.operate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.xiaobaozi.commons.utils.FtpUtil;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryContentService;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Payment;
import com.xiaobaozi.dataSystem.questionnaire.service.PaymentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.consultation.pojo.Consultation;
import com.xiaobaozi.dataSystem.consultation.pojo.ConsultationReply;
import com.xiaobaozi.dataSystem.consultation.search.ConsultationSearch;
import com.xiaobaozi.dataSystem.consultation.service.ConsultationService;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryService;
import com.xiaobaozi.dataSystem.feedback.pojo.Feedback;
import com.xiaobaozi.dataSystem.feedback.search.FeedbackSearch;
import com.xiaobaozi.dataSystem.feedback.service.FeedbackService;
import com.xiaobaozi.dataSystem.questionnaire.pojo.Questionnaire;
import com.xiaobaozi.dataSystem.questionnaire.search.QuestionnaireSearch;
import com.xiaobaozi.dataSystem.questionnaire.service.QuestionnaireService;
import com.xiaobaozi.dataSystem.registerUser.pojo.RegisterUser;
import com.xiaobaozi.dataSystem.registerUser.search.RegisterUserSearch;
import com.xiaobaozi.dataSystem.registerUser.service.RegisterUserService;
import com.xiaobaozi.dataSystem.systemManager.pojo.PictureInfo;
import com.xiaobaozi.dataSystem.systemManager.service.PictureInfoService;
import sun.misc.BASE64Decoder;

/**
 * 反馈建议
 * 
 * @author media
 * 
 */
@Controller
@RequestMapping("operate")
public class BaohuOperateController extends BaseController {

	@Resource
	RestTemplate restTemplate;
	@Resource
	private PictureInfoService pictureInfoService;
	@Resource
	private FeedbackService feedbackService;
	@Resource
	private RegisterUserService registerUserService;
	@Resource
	private ConsultationService consultationService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private QuestionnaireService questionnaireService;
	@Resource
	private DictionaryContentService dictionaryContentService;
	@Resource
	private PaymentService paymentService;

	/*
	 * 反馈建议
	 */
	@RequestMapping("feedbackPage.htm")
	public ModelAndView navigationPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/feedback/feedback");
	}

	@RequestMapping("serachFeedback.htm")
	public ModelAndView serachNavigation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/feedback/feedbackBody");
		FeedbackSearch sc = new FeedbackSearch();
		bind(request, sc);
		int count = feedbackService.getFeedbackCount(sc);
		sc.setTotalCount(count);
		List<Feedback> list = feedbackService.getFeedbackByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 跳转回复页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("replyPage.htm")
	public ModelAndView replyPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/feedback/detailFeedback");
		String id = req.getParameter("id");
		Feedback sc = feedbackService.findById(id);
		// 图片集的处理
		List<PictureInfo> pLs = new ArrayList<PictureInfo>();
		String str = sc.getPictureId(); // 图片集字段数属性
		if (StringUtils.isNotBlank(str)) {
			String[] ss = str.split(",");
			if (ss != null) {
				for (int i = 0; i < ss.length; i++) {
					if (StringUtils.isNotEmpty(ss[i]))
						pLs.add(pictureInfoService.findById(ss[i]));
				}
			}
		}
		mv.addObject("pLs", pLs);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 回复操作
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("replyFeedback.htm")
	public void replyFeedback(HttpServletRequest req, HttpServletResponse res) {
		String reply = req.getParameter("reply");
		String id = req.getParameter("id");
		System.out.println(reply);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("reply", reply);
		map.put("status", 2);
		map.put("replyTime", new Date());
		try {
			feedbackService.updateByMap(map);
			map.put("success", 1);
		} catch (DaoException e) {
			e.printStackTrace();
			map.put("success", 2);
		}
		JsonUtil.ajaxReturn(res, map);
	}

	/**
	 * 注册用户
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("registerUserPage.htm")
	public ModelAndView registerUserPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/registerUser/registerUser");
	}

	@RequestMapping("serachRegisterUser.htm")
	public ModelAndView serachRegisterUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/registerUser/registerUserBody");
		RegisterUserSearch sc = new RegisterUserSearch();
		bind(request, sc);
		int count = registerUserService.getRegisterUserCount(sc);
		sc.setTotalCount(count);
		List<RegisterUser> list = registerUserService.getRegisterUserByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 咨询回复
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("consultationPage.htm")
	public ModelAndView consultationPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/consultation/consultation");
	}

	@RequestMapping("serachConsultation.htm")
	public ModelAndView serachConsultation(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/consultation/consultationBody");
		ConsultationSearch sc = new ConsultationSearch();
		bind(request, sc);
		int count = consultationService.getConsultationCount(sc);
		sc.setTotalCount(count);
		List<Consultation> list = consultationService.getConsultationByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		System.out.println(data);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("replyConsultationPage.htm")
	public ModelAndView replyConsultationPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/consultation/detailConsultation");
		String id = req.getParameter("id");
		Consultation sc = consultationService.findById(id);
		mv.addObject("sc", sc);
		// 推荐理由
		String labelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "consultation.reason.dictionaryid");
		List<DictionaryContent> list = dictionaryService.selectBydictionaryId(labelId);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("goReplyConsultationDetail.htm")
	public ModelAndView goReplyConsultationDetail(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/consultation/detailConsultation");
		String id = req.getParameter("id");
		Consultation sc = consultationService.findById(id);
		List<ConsultationReply> replyLs = sc.getConsultationReplyLs();
		mv.addObject("replyLs", replyLs);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("saveConsultation.htm")
	public void saveConsultation(HttpServletRequest request, HttpServletResponse response) {
		Consultation sc = new Consultation();
		bind(request, sc);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			consultationService.replyConsultation(sc);
			map.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", 2);
		}
		JsonUtil.ajaxReturn(response, map);
	}

	/**
	 * 问卷调查
	 */
	@RequestMapping("questionnairePage.htm")
	public ModelAndView questionnairePage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/questionnaire/questionnaire");
	}

	@RequestMapping("serachQuestionnaire.htm")
	public ModelAndView serachQuestionnaire(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/questionnaire/questionnaireBody");
		QuestionnaireSearch sc = new QuestionnaireSearch();
		bind(request, sc);
		int count = questionnaireService.getQuestionnaireCount(sc);
		sc.setTotalCount(count);
		List<Questionnaire> list = questionnaireService.getQuestionnaireByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 问卷调查修改
	 */
	@RequestMapping("goEditQuestionnaire.htm")
	public ModelAndView goEditQuestionnaire(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/questionnaire/editQuestionnaire");
		String id = request.getParameter("id");
		Questionnaire sc = questionnaireService.getQuestionnaire(id);
		mv.addObject("sc", sc);
		//家庭关系
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "family.relationship.dictionaryid");
		List<DictionaryContent> familyTypeLs = dictionaryContentService.selectBydictionaryId(typeId);
		mv.addObject("familyTypeLs", familyTypeLs);
		return mv;
	}

	@RequestMapping("editQuestionnaire.htm")
	public void editQuestionnaire(HttpServletRequest request, HttpServletResponse response) {
		Questionnaire questionnaire = new Questionnaire();
		bind(request, questionnaire);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			questionnaireService.editQuestionnaire(questionnaire);
			retMap.put("success", "1");
			retMap.put("mes", "保存成功");
		}catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "异常");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}


	/**
	 * 付款码
	 */
	@RequestMapping("goEditPayment.htm")
	public ModelAndView goEditPayment(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/questionnaire/editPayment");
		String id = request.getParameter("id");
		Payment sc = paymentService.selectByQuestionnaireId(id);
		mv.addObject("sc", sc);
		mv.addObject("questionnaireId", id);
		return mv;
	}

	@RequestMapping("editPayment.htm")
	public void editPayment(HttpServletRequest request, HttpServletResponse response) {
		Payment payment = new Payment();
		bind(request, payment);
		Map<String, Object> retMap = new HashMap<String, Object>();
		String image = request.getParameter("image");
		if(StringUtils.isNotBlank(image)){//图片上传
		  String payImgId = _isMultipart(request,image,payment.getQuestionnaireId());
			payment.setPayImgId(payImgId);
		}
		try {
			String payTimeStr = request.getParameter("payTime");
			if(StringUtils.isNotBlank(payTimeStr)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date payTime = df.parse(payTimeStr);
				payment.setPayTime(payTime);
			}
			paymentService.insert(payment);
			retMap.put("success", "1");
			retMap.put("mes", "保存成功");
		}catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存异常");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	public String _isMultipart(HttpServletRequest request, String image, String paymentId){
		PictureInfo p = null;
		if(StringUtils.isNotBlank(paymentId)){
			Payment payment = paymentService.selectByQuestionnaireId(paymentId);
			if(payment!=null){
				p = pictureInfoService.findById(payment.getPayImgId());
			}
		}
		String fileUploadPath = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPathFile") + "/" + new SimpleDateFormat("yyyyMM").format(new Date());
		String fileType = request.getParameter("fileType");
		if(StringUtils.isBlank(fileType)){
			fileType = ".png";
		}
		String pictureName = UUIDUtil.getUUID() + fileType;
		String fileName = request.getParameter("fileName");
		if(StringUtils.isBlank(fileName)){
			fileName = pictureName;
		}
		String header = "data:image";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的
			// 去掉头部
			image = imageArr[1];
			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes
				File targetFile = new File(fileUploadPath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				InputStream input = new ByteArrayInputStream(decodedBytes);
				String ftpHost = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpHost");
				String ftpUserName = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpUserName");
				String ftpPassword = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPassword");
				int ftpPort = Integer.parseInt(PropertiesUtil.getValue("/conf/properties/systemconf.properties", "ftpPort"));
				FtpUtil ftpUtil = new FtpUtil();
				Session session = ftpUtil.getSession(ftpHost, ftpPort, ftpUserName, ftpPassword);
				ChannelSftp channel = (ChannelSftp) ftpUtil.getChannel(session);
				ftpUtil.uploadFile(channel, fileUploadPath, input, pictureName);
				ftpUtil.closeAll(channel, channel, session);

				String getImagesUrl = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "getFileUrl");
				if(p !=null){//修改
					File file = new File(p.getProductDir() + "/" + p.getPictureName());
					if (file.exists()) {
						file.delete();
					}
				}else{
					p = new PictureInfo();
				}
				p.setFileType(fileType);
				p.setName(fileName);
				p.setPictureName(pictureName);
				p.setProductDir(fileUploadPath);
				p.setSort(0);
				p.setUpdateTime(new Date());
                p.setUrl(getImagesUrl + new SimpleDateFormat("yyyyMM").format(new Date()) + "/" + pictureName);
				if(p.getId()!=null){
					pictureInfoService.update(p);
				}else{
					p.setId(UUIDUtil.getUUID());
					p.setCreateTime(new Date());
					pictureInfoService.insert(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
       return p.getId();

	}

}
