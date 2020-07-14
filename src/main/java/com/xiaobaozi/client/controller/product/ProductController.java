package com.xiaobaozi.client.controller.product;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobaozi.commons.utils.ExcelUtil;
import com.xiaobaozi.commons.utils.GeliFunctions;
import com.xiaobaozi.commons.vo.CommonUserDetails;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.company.search.InsuranceCompanySearch;
import com.xiaobaozi.dataSystem.dictionary.pojo.InsuranceRelation;
import com.xiaobaozi.dataSystem.dictionary.search.DictionaryContentSearch;
import com.xiaobaozi.dataSystem.dictionary.service.InsuranceRelationService;
import com.xiaobaozi.dataSystem.product.relation.CooperationSupplier;
import com.xiaobaozi.dataSystem.product.relation.GuaranteeRelation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobaozi.commons.controller.BaseController;
import com.xiaobaozi.dataSystem.commons.utils.JsonUtil;
import com.xiaobaozi.dataSystem.commons.utils.PropertiesUtil;
import com.xiaobaozi.dataSystem.company.pojo.InsuranceCompany;
import com.xiaobaozi.dataSystem.company.service.InsuranceCompanyService;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.dictionary.service.DictionaryContentService;
import com.xiaobaozi.dataSystem.essay.pojo.EssayLeavingMsg;
import com.xiaobaozi.dataSystem.essay.search.EssayLeavingMsgSearch;
import com.xiaobaozi.dataSystem.essay.service.EssayLeavingMsgService;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;
import com.xiaobaozi.dataSystem.product.service.ProductService;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabel;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;
import com.xiaobaozi.dataSystem.productLabel.search.ProductLabelSearch;
import com.xiaobaozi.dataSystem.productLabel.service.ProductLabelService;

/**
 * 系统配置微服务模块控制器
 * 
 * @author media
 * 
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController {

	@Resource
	private ProductService productService;
	@Resource
	private ProductLabelService productLabelService;
	@Resource
	private EssayLeavingMsgService essayLeavingMsgService;
	@Resource
	private DictionaryContentService dictionaryContentService;
	@Resource
	private InsuranceCompanyService insuranceCompanyService;
	@Resource
	private InsuranceRelationService insuranceRelationService;

	private static Logger log = Logger.getLogger(ProductController.class);

	/**
	 * 产品管理
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("productPage.htm")
	public ModelAndView productPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/product/product");
		// 所属类别
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "product.type.dictionaryid");
		List<DictionaryContent> list = dictionaryContentService.selectBydictionaryId(typeId);
		mv.addObject("typeLs", list);
		// 销售渠道
		String saleChannelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "saleChannel.type.dictionaryid");
		List<DictionaryContent> saleChannelLSlist = dictionaryContentService.selectBydictionaryId(saleChannelId);
		mv.addObject("saleChannellist", saleChannelLSlist);
        // 产品公司
		List<InsuranceCompany> compayls = insuranceCompanyService.listAll();
		mv.addObject("compayls", compayls);
		return mv;
	}

	@RequestMapping("serachProduct.htm")
	public ModelAndView serachProduct(HttpServletRequest request, HttpServletResponse response) {
		ProductSearch sc = new ProductSearch();
		ModelAndView mv = new ModelAndView("main/product/productBody");
		bind(request, sc);
		int count = productService.getProductCount(sc);
		sc.setTotalCount(count);
		List<Product> list = productService.getProductByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}


	/**
	 * 产品管理：新增页面
	 */
	@RequestMapping("goAddProductPage.htm")
	public ModelAndView goAddProductPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/product/addProduct");
		// 产品分类
		JSONArray typeLs =getTypeLs();
		mv.addObject("typeLs", typeLs);
		// 销售渠道
		String saleChannelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "saleChannel.type.dictionaryid");
		List<DictionaryContent> saleChannelLSlist = dictionaryContentService.selectBydictionaryId(saleChannelId);
		String data = JsonUtil.convertToArrayJson(saleChannelLSlist);
		mv.addObject("saleChannelLSlist", saleChannelLSlist);
		mv.addObject("saleChannelLSlistdata", data);
		//年龄阶段
		String rankId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "suitable.type.dictionaryid");
		List<DictionaryContent> suitableLs = dictionaryContentService.selectBydictionaryId(rankId);
		mv.addObject("suitableLs", suitableLs);
		return mv;
	}

	//产品分类
	public JSONArray getTypeLs(){
		String typeId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "product.type.dictionaryid");
		List<DictionaryContent> list = dictionaryContentService.selectBydictionaryId(typeId);
		JSONArray typeLs = new JSONArray();
		if(list!=null && list.size()>0){
			for(DictionaryContent content: list){
				JSONObject object = new JSONObject();
				object.put("id",content.getId());
				object.put("content",content.getContent());
				Map<String,Object> dataMap = new HashMap<String, Object>();
				dataMap.put("insuranceId",content.getId());
				dataMap.put("type",2);
				List<InsuranceRelation> ruleList = insuranceRelationService.getListByInsuranceId(dataMap);//投保规则
				String rules = GeliFunctions.toJSONString(ruleList, new String[]{"guaranteeContent.id","guaranteeContent.content"});
				object.put("rules",rules);

				dataMap.put("type",3);
				List<InsuranceRelation> calculationList = insuranceRelationService.getListByInsuranceId(dataMap);//测算规则
				String calculations = GeliFunctions.toJSONString(calculationList, new String[]{"expand","guaranteeContent.content"});
				object.put("calculations",calculations);

				typeLs.add(object);
			}
		}
		return typeLs;
	}

	/**
	 * 产品新增保存
	 */
	@RequestMapping("saveProduct.htm")
	public void saveProduct(HttpServletRequest request, HttpServletResponse response) {
		Product sc = new Product();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
			sc.setCreateName(user.getFullname());
			sc.setCreateById(user.getUsername());
			sc.setUpdateName(user.getFullname());
			sc.setUpdateById(user.getUsername());
            productService.insertProduct(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存");
		}catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "保存异常");
			log.error("产品新增异常", e);
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 产品管理：修改页面
	 */
	@RequestMapping("goEditProductPage.htm")
	public ModelAndView goEditProductPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/product/editProduct");
		String id = req.getParameter("id");
		Product product = productService.getProductById(id);
		mv.addObject("sc", product);
		// 产品分类
		JSONArray typeLs =getTypeLs();
		mv.addObject("typeLs", typeLs);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("insuranceId",product.getInsuranceTypeId());
		dataMap.put("type",3);
		List<InsuranceRelation> calculationList = insuranceRelationService.getListByInsuranceId(dataMap);//测算规则
		mv.addObject("calculations",calculationList);
		// 销售渠道
		String saleChannelId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "saleChannel.type.dictionaryid");
		List<DictionaryContent> saleChannelLSlist = dictionaryContentService.selectBydictionaryId(saleChannelId);
		String data = JsonUtil.convertToArrayJson(saleChannelLSlist);
		mv.addObject("saleChannelLSlist", saleChannelLSlist);
		mv.addObject("saleChannelLSlistdata", data);
		//年龄阶段
		String rankId = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "suitable.type.dictionaryid");
		List<DictionaryContent> suitableLs = dictionaryContentService.selectBydictionaryId(rankId);
		mv.addObject("suitableLs", suitableLs);
		return mv;
	}

	/**
	 * 产品修改保存
	 */
	@RequestMapping("editProduct.htm")
	public void editProduct(HttpServletRequest request, HttpServletResponse response) {
		Product sc = new Product();
		bind(request, sc);
		String publishTimeStr = request.getParameter("publishTime");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
			sc.setUpdateName(user.getFullname());
			sc.setUpdateById(user.getUsername());
			if(StringUtils.isNotBlank(publishTimeStr)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date publishTime = df.parse(publishTimeStr);
				sc.setPublishTime(publishTime);
			}
			productService.editProduct(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存");
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "异常");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 产品发布/产品下线
	 */
	@RequestMapping("editStatusProduct.htm")
	public ModelAndView editStatusProduct(HttpServletRequest request, HttpServletResponse response) {
		Product sc = new Product();
		bind(request, sc);
		boolean sucOrFail = true;
		try {
			CommonUserDetails user = (CommonUserDetails) getAuthentication().getPrincipal();
			sc.setUpdateName(user.getFullname());
			sc.setUpdateById(user.getUsername());
			sucOrFail =productService.update(sc);
		} catch (DaoException e) {
			log.error(e);
			return null;
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(sucOrFail);
		} catch (IOException ex1) {
			log.error(ex1);
		} finally {
			out.close();
		}
        return null;
	}

	/**
	 * 导出产品excel
	 *
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("exportExcel.htm")
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		ProductSearch sc = new ProductSearch();
		bind(req, sc);
		Integer count = productService.getProductCount(sc);
		sc.setTotalCount(count);
		if(sc.getPageSize()>1000){
			sc.setPageSize(1000);
		}
		List<Product> list = productService.getProductByPage(sc);
		List<Object[]> objectList = new ArrayList<Object[]>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Product product = list.get(i);
				Object[] object = new Object[5];
				object[0] = product.getName();
				object[1] = product.getDictionaryContent()!=null ? product.getDictionaryContent().getContent() : "";
				object[2] = (product.getRecommendCooperation()!=null && product.getRecommendCooperation().getDictionaryContent()!=null)
						? product.getRecommendCooperation().getDictionaryContent().getContent() : "";
				object[3] = product.getInsuranceCompany()!=null ? product.getInsuranceCompany().getCompanyName() : "";
				object[4] = product.getStatus()==1 ? "已发布" : product.getStatus()==2 ? "已下线" : "未发布";
				objectList.add(object);
			}
		}
		// 设置响应
		res.setContentType("application/force-download");
		try {
			res.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("保乎笔记产品数据_", "UTF-8")
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
		String[] header = new String[] { "产品名称", "所属类别", "销售渠道", "承保公司", "状态"};
		excelUtil.exportExcel("保乎笔记产品数据_" + new SimpleDateFormat("yyyyMMdd").format(new Date()), header, objectList, out, null);
		return null;
	}

	/**
	 * 产品管理：详情页面
	 */
	@RequestMapping("goDetailProductPage.htm")
	public ModelAndView goDetailProductPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/product/detailProduct");
		String id = req.getParameter("id");
		Product product = productService.getProductById(id);
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("insuranceId",product.getInsuranceTypeId());
		dataMap.put("type",3);
		List<InsuranceRelation> calculationList = insuranceRelationService.getListByInsuranceId(dataMap);//测算规则
		List<String> expands = new ArrayList<String>();
		List<String> expandName = new ArrayList<String>();
		for(InsuranceRelation insuranceRelation : calculationList){
			expands.add(insuranceRelation.getExpand());
			expandName.add(insuranceRelation.getGuaranteeContent().getContent());
		}
		mv.addObject("sc", product);
		mv.addObject("expands", expands);
		mv.addObject("expandName", expandName);
		return mv;
	}

	/**
	 * 产品管理：删除
	 */
	@RequestMapping("deleteproductbyid.htm")
	public void deleteKnowledgeBaseById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			Product product = productService.getProductById(id);
			productService.deleteProductById(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 公司列表数据
	 */
	@RequestMapping("serachCompany.htm")
	public ModelAndView serachCompany(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/product/companyBody");
		InsuranceCompanySearch sc = new InsuranceCompanySearch();
		bind(request, sc);
		int count = insuranceCompanyService.getInsuranceCompanyCount(sc);
		sc.setTotalCount(count);
		List<InsuranceCompany> list = insuranceCompanyService.getInsuranceCompanyByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("companyData", data);
		mv.addObject("companySC", sc);
		return mv;
	}



	/**
	 * 同类产品标签列表数据
	 */
	@RequestMapping("serachSimilarLabel.htm")
	public ModelAndView serachSimilarLabel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/product/similarLabelBody");
		ProductLabelSearch sc = new ProductLabelSearch();
		bind(request, sc);
		String dicIds = request.getParameter("serviceDicIds");
		String[] serviceDicIds = dicIds.split(",");
		List<String> idList = new ArrayList<String>();
		if (serviceDicIds.length > 0) {
			for (int i = 0; i < serviceDicIds.length; i++) {
				idList.add(serviceDicIds[i]);
			}
		}
		if (idList != null && idList.size() > 0) {
			sc.setServiceIds(idList);
		}

		int count = productLabelService.getProductLabelCount(sc);
		sc.setTotalCount(count);
		List<ProductLabel> list = productLabelService.getProductLabelByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("similarLabelData", data);
		mv.addObject("similarLabelSC", sc);
		return mv;
	}

	/**
	 * 特色标签列表数据
	 */
	@RequestMapping("serachSpecialLabel.htm")
	public ModelAndView serachSpecialLabel(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("main/product/specialLabelBody");
		DictionaryContentSearch sc = new DictionaryContentSearch();
		bind(request, sc);
		String specialLabel = PropertiesUtil.getValue("/conf/properties/systemconf.properties", "product.special.label.dictionaryid");
		sc.setDictionaryId(specialLabel);
		String dicIds = request.getParameter("serviceDicIds");
		String[] serviceDicIds = dicIds.split(",");
		List<String> idList = new ArrayList<String>();
		if (serviceDicIds.length > 0) {
			for (int i = 0; i < serviceDicIds.length; i++) {
				idList.add(serviceDicIds[i]);
			}
		}
		if (idList != null && idList.size() > 0) {
			sc.setServiceIds(idList);
		}
		int count = dictionaryContentService.getDictionaryContentCount(sc);
		sc.setTotalCount(count);
		List<DictionaryContent> list = dictionaryContentService.getContentByDictionaryId(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("specialLabelData", data);
		mv.addObject("specialLabelSC", sc);
		return mv;
	}




	@RequestMapping("serachProductConsultation.htm")
	public ModelAndView serachProductConsultation(HttpServletRequest request, HttpServletResponse response) {
		ProductSearch sc = new ProductSearch();
		ModelAndView mv = new ModelAndView("main/consultation/productSearchBody");
		bind(request, sc);
		String proIds = request.getParameter("serviceProIds");
		String[] serviceProIds = proIds.split(",");
		List<String> idList = new ArrayList<String>();
		if (serviceProIds.length > 0) {
			for (int i = 0; i < serviceProIds.length; i++) {
				idList.add(serviceProIds[i]);
			}
		}
		if (idList != null && idList.size() > 0) {
			sc.setServiceProjectIds(idList);
		}
		int count = productService.getProductConsultationCount(sc);
		sc.setTotalCount(count);
		List<Product> list = productService.getProductConsultationByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		mv.addObject("showLabel",request.getParameter("showLabel"));
		return mv;
	}

	/**
	 * 产品标签
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("productLabelPage.htm")
	public ModelAndView productLabelPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/productLabel/productLabel");
	}

	@RequestMapping("serachProductLabel.htm")
	public ModelAndView serachProductLabel(HttpServletRequest request, HttpServletResponse response) {
		ProductLabelSearch sc = new ProductLabelSearch();
		ModelAndView mv = new ModelAndView("main/productLabel/productLabelBody");
		bind(request, sc);
		int count = productLabelService.getProductLabelCount(sc);
		sc.setTotalCount(count);
		List<ProductLabel> list = productLabelService.getProductLabelByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("goAddProductLabelView.htm")
	public ModelAndView goAddProductLabelView(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/productLabel/addProductLabel");
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveProductLabel.htm")
	public void saveProductLabel(HttpServletRequest request, HttpServletResponse response) {
		ProductLabel sc = new ProductLabel();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			productLabelService.insertProductLabel(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("success", "2");
			retMap.put("mes", "保存失败");
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 跳转编辑页面
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("goEditProductLabel.htm")
	public ModelAndView goEditProductLabel(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/productLabel/editProductLabel");
		String id = req.getParameter("id");
		ProductLabel sc = productLabelService.findById(id);
		List<ProductLabelRelation> list = sc.getProductLabelRelationLs();
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("editProductLabel.htm")
	public void editProductLabel(HttpServletRequest request, HttpServletResponse response) {
		ProductLabel sc = new ProductLabel();
		bind(request, sc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			productLabelService.editProductLabel(sc);
			retMap.put("success", "1");
			retMap.put("mes", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("success", "2");
			retMap.put("mes", "保存失败");
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	/**
	 * 产品标签：删除
	 */
	@RequestMapping("deleteProductLabel.htm")
	public void deleteProductLabel(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			ProductLabel sc = productLabelService.findById(id);
			productLabelService.deleteProductLabelById(sc);
			retMap.put("success", "1");
			retMap.put("mes", "删除成功");
		} catch (Exception e) {
			retMap.put("success", "2");
			retMap.put("mes", "删除失败");
			e.printStackTrace();
		}
		JsonUtil.ajaxReturn(response, retMap);
	}

	@RequestMapping("essayLeavingMsgPage.htm")
	public ModelAndView essayLeavingMsgPage(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("main/essayLeavingMsg/essayLeavingMsg");
	}

	@RequestMapping("serachEssayLeavingMsg.htm")
	public ModelAndView serachEssayLeavingMsg(HttpServletRequest request, HttpServletResponse response) {
		EssayLeavingMsgSearch sc = new EssayLeavingMsgSearch();
		ModelAndView mv = new ModelAndView("main/essayLeavingMsg/essayLeavingMsgBody");
		bind(request, sc);
		int count = essayLeavingMsgService.getEssayLeavingMsgCount(sc);
		sc.setTotalCount(count);
		List<EssayLeavingMsg> list = essayLeavingMsgService.getEssayLeavingMsgByPage(sc);
		String data = JsonUtil.convertToArrayJson(list);
		mv.addObject("data", data);
		mv.addObject("sc", sc);
		return mv;
	}

	@RequestMapping("detailEssayLeavingMsgPage.htm")
	public ModelAndView detailEssayLeavingMsgPage(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("main/essayLeavingMsg/essayLeavingMsgDetail");
		String id = req.getParameter("id");
		EssayLeavingMsg sc = essayLeavingMsgService.findById(id);
		mv.addObject("sc", sc);
		return mv;
	}

	/**
	 * 审核操作
	 */
	@RequestMapping("doAudit.htm")
	public void doAudit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		String replyVal = request.getParameter("replyVal");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", status);
		if (StringUtils.isNotBlank(replyVal)) {
			map.put("reply", replyVal);
			map.put("replyTime", new Date());
		}
		try {
			essayLeavingMsgService.updateByMap(map);
			map.clear();
			map.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", 2);
		}
		JsonUtil.ajaxReturn(response, map);
	}

	//产品选择 acbox
	@RequestMapping("select.htm")
	public void select(HttpServletRequest req, HttpServletResponse res) {
		ProductSearch sc = new ProductSearch();
		String qWord = req.getParameter("q_word");
		int numPerPage = StringUtils.isNotBlank(req.getParameter("numPerPage")) ? Integer.parseInt(req.getParameter("numPerPage")) : 25 ;
		bind(req, sc);
		sc.setName(qWord);
		int count = productService.getProductCount(sc);
		sc.setTotalCount(count);
		sc.setPageSize(numPerPage);
		List<Product> list = productService.getProductByPage(sc);
		Map<String, Object> map = new HashMap<String, Object>();
		com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(GeliFunctions.toJSONString(list, new String[]{"id","name"}));
		map.put("result", jsonArray);
		map.put("acbox", "y");
		map.put("total", count);
		JsonUtil.ajaxReturn(res, map);
	}

	@RequestMapping("getsalechennel.htm")
	public void getSaleChennel(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		Product product = productService.getProductById(id);
		//渠道
		List<CooperationSupplier> cooperationSupplierLs = product.getCooperationSupplierLs();
		List<DictionaryContent> channelList = new ArrayList<DictionaryContent>();
		for(CooperationSupplier cooperationSupplier : cooperationSupplierLs){
			channelList.add(cooperationSupplier.getDictionaryContent());
		}
		//产品分项
		List<GuaranteeRelation> guaranteeRelationLs = product.getGuaranteeRelationLs();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelList", channelList);
		map.put("guaranteeRelationLs", guaranteeRelationLs);
		JsonUtil.ajaxReturn(res, map);
	}

}
