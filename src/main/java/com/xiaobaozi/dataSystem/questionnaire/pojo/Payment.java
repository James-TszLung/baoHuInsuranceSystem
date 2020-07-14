package com.xiaobaozi.dataSystem.questionnaire.pojo;

import java.io.Serializable;
import java.util.Date;

/*調查問卷:付款吗
 *
 */
public class Payment implements Serializable{

	private String questionnaireId;//调查问卷id
	private String orderId;//收款交易单号
	private Date payTime;//付款时间
	private String payMoney;//付款金额
	private String payImgId;//付款证明，图片id,关联xbz_pictureinfo.id
	private Date createTime;
	private Date updateTime;

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayImgId() {
		return payImgId;
	}

	public void setPayImgId(String payImgId) {
		this.payImgId = payImgId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
