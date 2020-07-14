package com.xiaobaozi.dataSystem.commons.enums;

/**
 * 用户类型
 * @author zhongsh
 */
public enum UserType {

	/**
	 * 操作员：O
	 */
	OPERATOR {
		public String getValue() {
			return "O";
		}
	},
	/**
	 * 会员：M
	 */
	MEMBER {
		public String getValue() {
			return "M";
		}
	},
	/**
	 * 配送员：Y
	 */
	COURIER {
		public String getValue() {
			return "Y";
		}
	};
	/**
	 * 取值
	 */
	public abstract String getValue();
	
}
