package com.xiaobaozi.dataSystem.product.relation;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

import java.io.Serializable;
/**
 *特色产品标签关联产品
 * @author media
 *
 */
public class SpecialLabel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5709301135744843549L;
	
	private String productId; //产品id
	private String specialLabelId; //特色产品标签（数字字典）
	private DictionaryContent dictionaryContent;
	private int sort;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSpecialLabelId() {
		return specialLabelId;
	}

	public void setSpecialLabelId(String specialLabelId) {
		this.specialLabelId = specialLabelId;
	}

	public DictionaryContent getDictionaryContent() {
		return dictionaryContent;
	}

	public void setDictionaryContent(DictionaryContent dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
