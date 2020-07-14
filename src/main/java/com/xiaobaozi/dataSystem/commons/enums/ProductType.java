package com.xiaobaozi.dataSystem.commons.enums;

/**
 * 产品类型
 * @author zhongsh
 */
public enum ProductType {

	/**
	 * 产品类型：机票-A
	 */
	AIR {
		public String getValue() {
			return "A";
		}
	},
	/**
	 * 产品类型：基础信息-B
	 */
	BASE {
		public String getValue() {
			return "B";
		}
	},
	/**
	 * 产品类型：租车-C
	 */
	CAR {
		public String getValue() {
			return "C";
		}
	},
	/**
	 * 产品类型：酒店-H
	 */
	HOTEL {
		public String getValue() {
			return "H";
		}
	},
	/**
	 * 产品类型：保险-I
	 */
	INSURANCE {
		public String getValue() {
			return "I";
		}
	},
	/**
	 * 产品类型：系统管理-S
	 */
	SYSTEM {
		public String getValue() {
			return "S";
		}
	},
	/**
	 * 充值短信
	 */
	MESSAGE {
		public String getValue() {
			return "M";
		}
	},
	/**
	 * 其他
	 */
	OTHER {
		public String getValue() {
			return "Z";
		}
	};
	/**
	 * 取值
	 */
	public abstract String getValue();
	
}
