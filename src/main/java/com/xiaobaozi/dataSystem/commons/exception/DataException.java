package com.xiaobaozi.dataSystem.commons.exception;

/**
 * DataException 数据异常类
 * @author liuxb
 *
 */
public class DataException extends Exception {
	private static final long serialVersionUID = 1L;


	public DataException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	

}
