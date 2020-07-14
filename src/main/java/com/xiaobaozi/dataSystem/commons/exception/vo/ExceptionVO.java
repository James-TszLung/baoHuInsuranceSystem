package com.xiaobaozi.dataSystem.commons.exception.vo;

import com.xiaobaozi.dataSystem.commons.vo.BaseVO;

public class ExceptionVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String  type;
	private String  msg;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ExceptionVO [Id=" + Id + ", type=" + type + ", msg=" + msg
				+ ", createTime=" + createTime + "]";
	}
	
}
