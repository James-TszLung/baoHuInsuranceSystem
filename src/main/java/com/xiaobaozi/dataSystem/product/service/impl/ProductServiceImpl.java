package com.xiaobaozi.dataSystem.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.dictionary.handle.DictionaryContentHandle;
import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;
import com.xiaobaozi.dataSystem.product.handle.*;
import com.xiaobaozi.dataSystem.product.relation.*;
import com.xiaobaozi.dataSystem.productLabel.handle.ProductLabelRelationHandle;
import com.xiaobaozi.dataSystem.productLabel.pojo.ProductLabelRelation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.product.pojo.Product;
import com.xiaobaozi.dataSystem.product.search.ProductSearch;
import com.xiaobaozi.dataSystem.product.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Component("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService {

	@Resource(name = "productHandle")
	private ProductHandle productHandle;
	@Resource
	private ClauseFileHandle clauseFileHandle;
	@Resource
	private CooperationSupplierHandle cooperationSupplierHandle;
	@Resource
	private GuaranteeRelationHandle guaranteeRelationHandle;
	@Resource
	private GuaranteeLowerlevelRelationHandle guaranteeLowerlevelRelationHandle;
	@Resource
	private IncrementRelationHandle incrementRelationHandle;
	@Resource
	GuaranteedRuleHandle guaranteedRuleHandle;
	@Resource
	PremiumCalculationHandle premiumCalculationHandle;
	@Resource
	DictionaryContentHandle dictionaryContentHandle;
	@Resource
	LabelRelationHandle labelRelationHandle;
	@Resource
	SpecialLabelHandle specialLabelHandle;
	@Resource
	ProductLabelRelationHandle productLabelRelationHandle;

	@Override
	protected void initService() {
		handle = productHandle;
	}

	public int getProductCount(ProductSearch sc) {
		return productHandle.getProductCount(sc);
	}

	public List<Product> getProductByPage(ProductSearch sc) {
		return productHandle.getProductByPage(sc);
	}

	public int getProductConsultationCount(ProductSearch sc) {
		return productHandle.getProductConsultationCount(sc);
	}

	public List<Product> getProductConsultationByPage(ProductSearch sc) {
		return productHandle.getProductConsultationByPage(sc);
	}

	public Product getProductById(String id) {
		return productHandle.getProductById(id);
	}

	public int insertProduct(Product sc) throws Exception{
		sc.setId(UUIDUtil.getUUID());
		sc.setStatus(-1);
		insertRelationData(sc,"add");
		return productHandle.insert(sc);
	}

	public boolean editProduct(Product sc) throws Exception{
		deleteRelationData(sc,"edit");
        insertRelationData(sc,"edit");
		return productHandle.update(sc);
	}

	public int deleteProductById(Product sc){
		deleteRelationData(sc,"delete");
		return productHandle.deleteProductById(sc.getId());
	}

	public void insertRelationData(Product sc,String method)  throws Exception{
		//条款附件
		List<ClauseFile> clauseFileLs = sc.getClauseFileLs();
		if (clauseFileLs != null && clauseFileLs.size() > 0) {
			for (int i = 0; i < clauseFileLs.size(); i++) {
				ClauseFile file = clauseFileLs.get(i);
				if (file != null && StringUtils.isNotEmpty(file.getFileAddress()) && StringUtils.isNotEmpty(file.getFileName())) {
					file.setType("pdf");
					file.setProductId(sc.getId());
					file.setSort(i);
					clauseFileHandle.insert(file);
				}
			}
		}
		// 保存合作供应商
		List<CooperationSupplier> supplierLs = sc.getCooperationSupplierLs();
		if (supplierLs != null && supplierLs.size() > 0) {
			for (int i = 0; i < supplierLs.size(); i++) {
				CooperationSupplier supplier = supplierLs.get(i);
				if (supplier != null && StringUtils.isNotEmpty(supplier.getDictionaryContentId())
						&& StringUtils.isNotEmpty(supplier.getInsureLink())
						&& StringUtils.isNotEmpty(supplier.getClauseLink())) {
					if(supplier.getRecommendStatus()==0){
						supplier.setRecommendStatus(1);
					}
					supplier.setId(UUIDUtil.getUUID());
					supplier.setProductId(sc.getId());
					supplier.setSort(i);
					cooperationSupplierHandle.insert(supplier);
				}
			}
		}

		//保什么
		List<GuaranteeRelation> guaranteeRelationLs = sc.getGuaranteeRelationLs();
		List<String> guaranteeRelationId = new ArrayList<String>();
		if (guaranteeRelationLs != null && guaranteeRelationLs.size() > 0) {
			for (int i = 0; i < guaranteeRelationLs.size(); i++) {
				GuaranteeRelation guarantee = guaranteeRelationLs.get(i);
				if (guarantee != null && StringUtils.isNotEmpty(guarantee.getDictionaryContentId())
						&& StringUtils.isNotEmpty(guarantee.getGuaranteeDetail())
						&& StringUtils.isNotEmpty(guarantee.getGuaranteeIntroduction())) {
					guarantee.setProductId(sc.getId());
					guarantee.setSort(i+1);
					if(method.equals("edit") && guarantee.getId()!=null){
						guaranteeRelationHandle.update(guarantee);
					}else{
						guarantee.setId(UUIDUtil.getUUID());
						guaranteeRelationHandle.insert(guarantee);
					}
					guaranteeRelationId.add(guarantee.getId());
					//保什么子项目
					List<GuaranteeLowerlevelRelation> guaranteeLowerlevelRelationLS = guarantee.getGuaranteeLowerLevelRelationLs();
					if (guaranteeLowerlevelRelationLS != null && guaranteeLowerlevelRelationLS.size() > 0) {
						for (int j = 0; j < guaranteeLowerlevelRelationLS.size(); j++) {
							GuaranteeLowerlevelRelation levelRelation = guaranteeLowerlevelRelationLS.get(j);
							if (levelRelation != null && StringUtils.isNotEmpty(levelRelation.getTitle())
									&& StringUtils.isNotEmpty(levelRelation.getContent())) {
								levelRelation.setGuaranteeRelationId(guarantee.getId());
								levelRelation.setFlag(i);
								guaranteeLowerlevelRelationHandle.insert(levelRelation);
							}

						}
					}
				}
			}
		}
		if(method.equals("edit") && guaranteeRelationId.size()>0){
			List<GuaranteeRelation> guaranteeRelationList = guaranteeRelationHandle.selectByProductId(sc.getId());
			for(GuaranteeRelation relation : guaranteeRelationList){
				if(!guaranteeRelationId.contains(relation.getId())){
					guaranteeLowerlevelRelationHandle.deleByGuaranteeRelationId(relation.getId());
					guaranteeRelationHandle.deleById(relation.getId());
				}
			}
		}
		//增值服务
		List<IncrementRelation> incrementRelationLs = sc.getIncrementRelationLs();
		if (incrementRelationLs != null && incrementRelationLs.size() > 0) {
			for (int i = 0; i < incrementRelationLs.size(); i++) {
				IncrementRelation incrementRelation = incrementRelationLs.get(i);
				if (incrementRelation != null && StringUtils.isNotEmpty(incrementRelation.getTitle())
						&& StringUtils.isNotEmpty(incrementRelation.getContent())) {
					incrementRelation.setProductId(sc.getId());
					incrementRelation.setType(1);
					incrementRelation.setSort(i);
					incrementRelationHandle.insert(incrementRelation);
				}
			}
		}
		//续保规则
		List<IncrementRelation> renewRelationLs = sc.getRenewRelationLs();
		if (renewRelationLs != null && renewRelationLs.size() > 0) {
			for (int i = 0; i < renewRelationLs.size(); i++) {
				IncrementRelation renewRelation = renewRelationLs.get(i);
				if (renewRelation != null && StringUtils.isNotEmpty(renewRelation.getTitle())
						&& StringUtils.isNotEmpty(renewRelation.getContent())) {
					renewRelation.setProductId(sc.getId());
					renewRelation.setType(2);
					renewRelation.setSort(i);
					incrementRelationHandle.insert(renewRelation);
				}
			}
		}
		//投保规则
		List<GuaranteedRule> guaranteedRuleLs = sc.getGuaranteedRuleLs();
		if (guaranteedRuleLs != null && guaranteedRuleLs.size() > 0) {
			for (int i = 0; i < guaranteedRuleLs.size(); i++) {
				GuaranteedRule rule = guaranteedRuleLs.get(i);
				if (rule != null && StringUtils.isNotEmpty(rule.getDictionaryContentId())
						&& StringUtils.isNotEmpty(rule.getContent())) {
					rule.setId(UUIDUtil.getUUID());
					rule.setProductId(sc.getId());
					rule.setSort(i);
					guaranteedRuleHandle.insert(rule);
				}
			}
		}
		//保费测算
		List<PremiumCalculation> premiumCalculationLs = sc.getPremiumCalculationLs();
		if (premiumCalculationLs != null && premiumCalculationLs.size() > 0) {
			for (int i = 0; i < premiumCalculationLs.size(); i++) {
				PremiumCalculation premiumCalculation = premiumCalculationLs.get(i);
				if (premiumCalculation != null && StringUtils.isNotEmpty(premiumCalculation.getFlag())) {
					premiumCalculation.setId(UUIDUtil.getUUID());
					premiumCalculation.setProductId(sc.getId());
					premiumCalculation.setSort(i);
					premiumCalculationHandle.insert(premiumCalculation);
				}
			}
		}
		String keyword = "";
		//特色标签
		if(sc.getSpecialLabelIds()!=null && sc.getSpecialLabelIds().length>0){
			for(int i=0;i<sc.getSpecialLabelIds().length;i++){
				String specialLabelId = sc.getSpecialLabelIds()[i];
				SpecialLabel specialLabel = new SpecialLabel();
				specialLabel.setProductId(sc.getId());
				specialLabel.setSpecialLabelId(specialLabelId);
				specialLabel.setSort(i);
				specialLabelHandle.insert(specialLabel);
				DictionaryContent special = dictionaryContentHandle.findById(specialLabelId);
				LabelRelation relation = new LabelRelation();
				relation.setProductId(sc.getId());
				relation.setLabelId(specialLabelId);
				relation.setLabelValue(special.getContent());
				relation.setSort(i);
				labelRelationHandle.insert(relation);
				keyword += special.getContent()+",";
			}
		}
		if(keyword.length()>0){
			keyword= keyword.substring(0,keyword.length()-1);
		}
		sc.setKeyword(keyword);
		//同类产品比标签
        if(StringUtils.isNotEmpty(sc.getSimilarLabelId())){
			ProductLabelRelation productLabelRelation = new ProductLabelRelation();
			productLabelRelation.setProductLabelId(sc.getSimilarLabelId());
			productLabelRelation.setProductId(sc.getId());
			productLabelRelation.setSort(0);
			productLabelRelationHandle.insert(productLabelRelation);
		}

	}

	public void deleteRelationData(Product sc,String method){
		clauseFileHandle.deleByProductId(sc.getId());
		cooperationSupplierHandle.deleByProductId(sc.getId());
		List<GuaranteeRelation> guaranteeRelationLs = sc.getGuaranteeRelationLs();
		if (guaranteeRelationLs != null && guaranteeRelationLs.size() > 0) {
			for (GuaranteeRelation relation : guaranteeRelationLs) {
				guaranteeLowerlevelRelationHandle.deleByGuaranteeRelationId(relation.getId());
			}
		}
		if(method.equals("delete")){
			guaranteeRelationHandle.deleByProductId(sc.getId());
			productLabelRelationHandle.delById(sc.getSimilarLabelId());
		}
		incrementRelationHandle.deleByProductId(sc.getId());
		guaranteedRuleHandle.deleByProductId(sc.getId());
		premiumCalculationHandle.deleByProductId(sc.getId());
		labelRelationHandle.deleByProductId(sc.getId());
		specialLabelHandle.deleByProductId(sc.getId());
	}

}
