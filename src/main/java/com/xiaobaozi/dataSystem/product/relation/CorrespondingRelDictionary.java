package com.xiaobaozi.dataSystem.product.relation;

import java.io.Serializable;

public class CorrespondingRelDictionary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8527075856529168406L;
	
	private String typeDictionaryId;
	private String guaranteeRelationId;
	private int sort;
	
	
	
	public String getTypeDictionaryId() {
		return typeDictionaryId;
	}
	public void setTypeDictionaryId(String typeDictionaryId) {
		this.typeDictionaryId = typeDictionaryId;
	}
	public String getGuaranteeRelationId() {
		return guaranteeRelationId;
	}
	public void setGuaranteeRelationId(String guaranteeRelationId) {
		this.guaranteeRelationId = guaranteeRelationId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
