package com.xiaobaozi.dataSystem.product.relation;

import com.xiaobaozi.dataSystem.dictionary.pojo.DictionaryContent;

import java.io.Serializable;
/**
 * 投保規則 关联产品
 * @author media
 *
 */
public class GuaranteedRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1867639605576650178L;
	private String id;
	private String productId; //产品id
	private String dictionaryContentId; // 数字字典保障类型id
	private String content; //规则内容
	private DictionaryContent dictionaryContent;
	private int sort;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getDictionaryContentId() {
		return dictionaryContentId;
	}
	public void setDictionaryContentId(String dictionaryContentId) {
		this.dictionaryContentId = dictionaryContentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
