package com.xiaobaozi.dataSystem.commons.exception;

/**
 * 异常基础类
 * @author bing
 *
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 错误编码
	 */
	private String  errorCode;
	
	/**
	 * 扩展属性集合
	 */
	private Object[] args;

	public BaseException(String message, Throwable cause, String errorCode, Object[] args) {
		super(message, cause);
		this.errorCode = errorCode;
		this.args = args;
	}

	public BaseException(String message, String errorCode, Object[] args) {
		super(message);
		this.errorCode = errorCode;
		this.args = args;
	}

	public BaseException(Throwable cause, String errorCode, Object[] args) {
		super(cause);
		this.errorCode = errorCode;
		this.args = args;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

	
	
	
	
	
}
