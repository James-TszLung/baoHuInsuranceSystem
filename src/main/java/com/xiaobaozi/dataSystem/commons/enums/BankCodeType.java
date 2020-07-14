package com.xiaobaozi.dataSystem.commons.enums;

/**
 * 银行代码转换
 * 
 * @author zhongsh
 */
public enum BankCodeType {

	工商银行, 农业银行, 中国银行, 建设银行, 招商银行, 邮储银行, 交通银行, 浦发银行, 民生银行, 兴业银行, 平安银行, 中信银行, 华夏银行, 广发银行, 光大银行, 北京银行, 宁波银行;
	public static BankCodeType getValue(int value) {
		if (value == 1002) {
			return 工商银行;
		}else if(value==1005){
			return 农业银行;
		}else if(value==1026){
			return 中国银行;
		}else if(value==1003){
			return 建设银行;
		}else if(value==1001){
			return 招商银行;
		}else if(value==1066){
			return 邮储银行;
		}else if(value==1020){
			return 交通银行;
		}else if(value==1004){
			return 浦发银行;
		}else if(value==1006){
			return 民生银行;
		}else if(value==1009){
			return 兴业银行;
		}else if(value==1010){
			return 平安银行;
		}else if(value==1021){
			return 中信银行;
		}else if(value==1025){
			return 华夏银行;
		}else if(value==1027){
			return 广发银行;
		}else if(value==1022){
			return 光大银行;
		}else if(value==1032){
			return 北京银行;
		}else if(value==1056){
			return 宁波银行;
		}
		return null;
	}
}
