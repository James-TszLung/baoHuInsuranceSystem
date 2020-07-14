package com.xiaobaozi.dataSystem.commons.exception.service;

import com.xiaobaozi.dataSystem.commons.enums.ProductType;

/**
 * 操作日志支持。POJO实现此接口，提供自动记录操作日志的必要信息。
 * @author zhongsh
 */
public interface Logable {

	/**
	 * 为记录操作日志提供主键信息
	 * @return 把主键各字段转换成String，例如"mainID:123,subID:456"，最多64位长度
	 */
	public String getLogProductID();
	
	/**
	 * 为记录操作日志提供产品类型信息
	 * @return 取值参考ProductType枚举说明
	 * @see ProductType
	 */
	public ProductType getLogProductType();
	
	/**
	 * 此处可以自定义remark------------如不需自定义，此处自定义为空,自定义之后不能使用MAP类型
	 * 如为MAP类型，则需要自己填充日志数据
	 */
	public String getRemarkSelf();
}
