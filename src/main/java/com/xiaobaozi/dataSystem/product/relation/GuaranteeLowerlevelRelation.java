package com.xiaobaozi.dataSystem.product.relation;

import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;

import java.io.Serializable;
/**
 * 保什么的下级关联
 * @author media
 *
 */
public class GuaranteeLowerlevelRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282712550059209883L;
	
	private String guaranteeRelationId; //保什麼的id
	private String indemnitySubId; //子项目id，关联indemnity_sub.id
	private String title;// 标题
	private String content;// 内容
	private int flag;
	private IndemnitySub indemnitySub;
	
	
	public String getGuaranteeRelationId() {
		return guaranteeRelationId;
	}
	public void setGuaranteeRelationId(String guaranteeRelationId) {
		this.guaranteeRelationId = guaranteeRelationId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getIndemnitySubId() {
		return indemnitySubId;
	}

	public void setIndemnitySubId(String indemnitySubId) {
		this.indemnitySubId = indemnitySubId;
	}

	public IndemnitySub getIndemnitySub() {
		return indemnitySub;
	}

	public void setIndemnitySub(IndemnitySub indemnitySub) {
		this.indemnitySub = indemnitySub;
	}
}
