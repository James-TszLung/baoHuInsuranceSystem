package com.xiaobaozi.dataSystem.commons.exception;

import com.xiaobaozi.dataSystem.commons.Constants;
import com.xiaobaozi.dataSystem.commons.utils.I18nUtil;
/**
 * DaoException dao异常类
 * @author bing
 *
 */
public class DaoException extends BaseException {
	private static final long serialVersionUID = 1L;

	public DaoException(String message, String errorCode, Object[] args) {
		super(message, errorCode, args);
	}

	public DaoException(String message, Throwable cause, String errorCode, Object[] args) {
		super(message, cause, errorCode, args);
	}


	@Override
	public String getMessage() {
		if(Constants.EXCEPTION_DAOEXCEPTION_INSERT_CODE.equals(this.getErrorCode())){
			return I18nUtil.getInstance().getMessage("exception.dao.insert.error");
		}else if(Constants.EXCEPTION_DAOEXCEPTION_DELETE_CODE.equals(this.getErrorCode())){
			return I18nUtil.getInstance().getMessage("exception.dao.delete.error");
		}else{
			return I18nUtil.getInstance().getMessage("exception.dao.update.error");
		}
	}
	
	

}
