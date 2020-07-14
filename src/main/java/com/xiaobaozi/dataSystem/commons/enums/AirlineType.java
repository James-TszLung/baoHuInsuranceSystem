package com.xiaobaozi.dataSystem.commons.enums;

/**
 * 航空公司类型
 * @author zhongsh
 */
public enum AirlineType {
	
	/**
	 * 国内航空公司
	 */
	DOMESTIC {
		public String getName() {
			return "国内";
		}
		
		public String getValue() {
			return "1";
		}
	},
	/**
	 * 国际航空公司
	 */
	INTERNATIONAL {
		public String getName() {
			return "国际";
		}
		
		public String getValue() {
			return "2";
		}
	};
	
	/**
	 * 中文描述
	 */
	public abstract String getName();
	
	/**
	 * 取值
	 */
	public abstract String getValue();
}
