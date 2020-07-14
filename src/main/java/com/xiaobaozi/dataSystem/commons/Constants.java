package com.xiaobaozi.dataSystem.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

	/**
	 * Default locale used in this application, define in database, cache here
	 * only.
	 */
	public static String SYSTEM_LOCALE_CODE = "zh_CN";

	/**
	 * Default locale dateTime format
	 */
	public static String SYSTEM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Default locale date day format
	 */
	public static String SYSTEM_DATE_DAY_PATTERN = "yyyy-MM-dd";

	/**
	 * Default locale flight dateTime format
	 */
	public static String SYSTEM_FLIGHTTIME_PATTERN = "yyyy-MM-dd HH:mm";

	/**
	 * 表单绑定decimal格式
	 */
	public final static String BINDER_DECIMAL_FORMAT = "#.00";

	/**
	 * 文件下载目录
	 */
	public final static String DOWNLOAD_DIR = "download";
	/**
	 * 文件下载目录
	 */
	public final static String UPLOAD_DIR = "module";
	/**
	 * 系统文件夹分隔符
	 */
	public final static String SYSTEM_DIR_FLITER = System.getProperty("file.separator");

	/**
	 * excel文件后缀
	 */
	public final static String EXCEL_FILE_FLITER = "xls";

	/**
	 * 报表字段前缀
	 */
	public final static String REPORT_CLOUMN_PRE_FILTER = "c";

	/**
	 * 外购票证性质
	 */
	public final static String REPORT_PURCHASED_OUTTKT_PLAT = "P";

	/**
	 * DAO增加记录异常编码
	 */
	public final static String EXCEPTION_DAOEXCEPTION_INSERT_CODE = "DB_ERROR.INSERT";

	/**
	 * DAO更新记录异常编码
	 */
	public final static String EXCEPTION_DAOEXCEPTION_UPDATE_CODE = "DB_ERROR.UPDATE";

	/**
	 * DAO删除记录异常编码
	 */
	public final static String EXCEPTION_DAOEXCEPTION_DELETE_CODE = "DB_ERROR.DELETE";

	/**
	 * 舱位状态：Y-启用 ACTIVE，N-停用 FORBIDDEN
	 */
	public final static String CLASSAGIO_STATUS_ACTIVE = "Y";

	/**
	 * 舱位状态：Y-启用 ACTIVE，N-停用 FORBIDDEN
	 */
	public final static String CLASSAGIO_STATUS_FORBIDDEN = "N";

	/**
	 * 系统设置数据类型 1-String,2-integer,3-boolean
	 */
	public final static Integer CONF_DATA_TYPE_STRING = 1;
	/**
	 * 系统设置数据类型 1-String,2-integer,3-boolean
	 */
	public final static Integer CONF_DATA_TYPE_NUMBER = 2;
	/**
	 * 系统设置数据类型 1-String,2-integer,3-boolean
	 */
	public final static Integer CONF_DATA_TYPE_BOOLEAN = 3;

	/**
	 * 政策状态：Y-启用 ACTIVE
	 */
	public final static String POLICY_STATUS_ACTIVE = "Y";

	/**
	 * 政策状态：N-停用 FORBIDDEN
	 */
	public final static String POLICY_STATUS_FORBIDDEN = "N";

	/**
	 * 产品政策航程类型：S-单程
	 */
	public final static String WAYTYPE_ONEWAY = "S";

	/**
	 * 产品政策航程类型：B-往返程
	 */
	public final static String WAYTYPE_ROUND = "B";

	/**
	 * 产品政策附加文件存放地址
	 */
	public final static String POLICY_PRODUCT_DIR = "/policy/product/filepage";

	/**
	 * 国际特价政策附加文件存放地址
	 */
	public final static String POLICY_INTERSPECIAL_DIR = "/policy/interspecial/filepage";

	/**
	 * 国际政策通知通告文件存放地址
	 */
	public final static String POLICY_INTERNOTICE_DIR = "/policy/internotice/filepage";

	/**
	 * 国际特价文件存放地址
	 */
	public final static String POLICY_INTERFILE_DIR = "/policy/interfile/filepage";

	/**
	 * 舱位-航程类型：N-单程
	 */
	public final static String CLASSAGIO_ISDUP_ONEWAY = "N";

	/**
	 * 舱位-航程类型：Y-往返
	 */
	public final static String CLASSAGIO_ISDUP_ROUND = "Y";

	/**
	 * 舱位-航程类型：M-单程及往返
	 */
	public final static String CLASSAGIO_ISDUP_BOTH = "M";
	/**
	 * 特价-指定舱优惠政策视图类型 1=编辑 2=查看
	 */
	public final static String APPLYPOLICY_VIEW_TYPE_EDIT = "1";
	public final static String APPLYPOLICY_VIEW_TYPE_VIEW = "2";

	/**
	 * 特价-指定舱优惠政策保存类型 0=新增 1=编辑
	 */
	public final static String APPLYPOLICY_SAVE_TYPE_ADD = "0";
	public final static String APPLYPOLICY_SAVE_TYPE_UPDATE = "1";

	/**
	 * 政策类型：1-直减运价政策
	 */
	public final static String APPLYTYPE_LAPSEFREIGHT = "1";

	/**
	 * 政策类型：2-指定舱优惠政策
	 */
	public final static String APPLYTYPE_APPOINTCABIN = "2";

	/**
	 * 政策类型：3-见低仓位申请政策
	 */
	public final static String APPLYTYPE_CABINLOW = "3";

	/**
	 * 产品政策模板文件名
	 */
	public final static String TEMPLATE_PRODUCT = "ProductPolicyTemplate.xlsx";

	/**
	 * 供应商政策模板文件名
	 */
	public final static String TEMPLATE_SUPPLY = "SupplyPolicyTemplate.xlsx";

	/**
	 * 散充政策模板文件名
	 */
	public final static String TEMPLATE_CASUAL = "CasualPolicyTemplate.xlsx";

	/**
	 * 直减运价政策模板文件名
	 */
	public final static String TEMPLATE_LAPSEFREIGHT = "LapseFreightTemplate.xlsx";

	/**
	 * 指定舱优惠政策模板文件名
	 */
	public final static String TEMPLATE_APPOINTCABIN = "AppointCabinPolicyTemplate.xlsx";

	/**
	 * 见低仓位申请政策模板文件名
	 */
	public final static String TEMPLATE_CABINLOW = "CabinLowPolicyTemplate.xlsx";

	/**
	 * 国际特价政策模板文件名
	 */
	public final static String TEMPLATE_INTERSPECIAL = "InterSpecialTemplate.xlsx";

	/**
	 * 国际返点政策模板文件名
	 */
	public final static String TEMPLATE_INTERRETURN = "InterReturnTemplate.xlsx";

	/**
	 * 操作日志-Map键值：OPLOG
	 */
	public final static String OPLOG_MAPKEY = "OPLOG";

	/**
	 * 操作日志-操作类型：1 增加/2修改/3删除/4上传政策
	 */
	public final static String OPLOG_TYPE_INSERT = "1";

	/**
	 * 操作日志-操作类型：1 增加/2修改/3删除/4上传政策
	 */
	public final static String OPLOG_TYPE_UPDATE = "2";

	/**
	 * 操作日志-操作类型：1 增加/2修改/3删除/4上传政策
	 */
	public final static String OPLOG_TYPE_DELETE = "3";

	/**
	 * 操作日志-操作类型：1 增加/2修改/3删除/4上传政策
	 */
	public final static String OPLOG_TYPE_UPLOAD = "4";
	/**
	 * 操作日志-操作类型：1 增加/2修改/3删除/4上传政策/5上传报表
	 */
	public final static String OPLOG_REPORT_UPLOAD = "5";

	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_AIRTICKET = "A";
	public final static String PRODUCT_AIRTICKET_CN = "机票";

	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_BASEINFO = "B";

	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_CAR = "C";

	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_HOTEL = "H";

	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_INSURANCE = "I";

	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_SYSTEM = "S";
	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信
	 */
	public final static String PRODUCT_SMSCASH = "M";
	/**
	 * 产品类型：A机票/B基础信息/C租车/H酒店/I保险/S系统管理/M充值短信/Z其他
	 */
	public final static String PRODUCT_OTHER = "Z";
	/**
	 * 调用接口类型：1，易思凯
	 */
	public final static String CONF_DATA_TYPE_AIR = "1";
	/**
	 * 打印状态：0-未打印，1-已打印
	 */
	public final static Integer PRINT_STATUS_DONTPRINT = 0;
	/**
	 * 打印状态：0-未打印，1-已打印
	 */
	public final static Integer PRINT_STATUS_PRINTED = 1;

	/**
	 * 配送状态：0-已取消，1-未配送，2-已配送
	 */
	public final static Integer RESSEND_STATUS_CANCELED = 0;
	/**
	 * 配送状态：0-已取消，1-未配送，2-已配送
	 */
	public final static Integer RESSEND_STATUS_DONTSEND = 1;
	/**
	 * 配送状态：0-已取消，1-未配送，2-已配送
	 */
	public final static Integer RESSEND_STATUS_SENDED = 2;
	/**
	 * 订单预定接口-返回正确状态TRUE
	 */
	public final static String ORDER_RESERVATION_STATUS_TRUE = "TRUE";
	/**
	 * 订单预定接口-返回失败状态FALSE
	 */
	public final static String ORDER_RESERVATION_STATUS_FALSE = "FALSE";
	/**
	 * 订单预定接口-航班查询-易思凯接口返回成功的响应状态
	 */
	public final static String ORDER_RESERVATION_YSK_SUCCESS = "000000";
	/**
	 * 调用接口返回成功失败信息
	 */
	public final static String CONF_DATA_TYPE_FLAG = "success";
	/**
	 * 调用接口返回成功失败信息
	 */
	public final static String CONF_DATA_TYPE_FLAGFALSE = "false";
	/**
	 * 调用接口返回标志为婴儿
	 */
	public final static String INTERFACE_SZAIR_BAY = "INFANT";
	/**
	 * 调用接口返回成功失败信息
	 */
	public final static Integer CONF_DATA_TYPE_BIT = 64;
	/**
	 * 国际政策类型 1：国际特价 2：国际返点
	 */
	public final static Integer INTERSPECPOLICYDATE_POLICYTYPE_INTERSP = 1;
	public final static Integer INTERSPECPOLICYDATE_POLICYTYPE_INTERRP = 2;
	/**
	 * 国际政策日期类型 1：旅行日期 2：开票日期
	 */
	public final static Integer INTERSPECPOLICYDATE_DATECYTYPE_TRIPDATE = 1;
	public final static Integer INTERSPECPOLICYDATE_DATECYTYPE_OPENDATE = 2;

	/**
	 * 航线类型 类型 1.国内/2国外
	 */
	public final static String AIRLINES_TYPE_DOMESTIC = "1";
	public final static String AIRLINES_TYPE_INTERNATIONAL = "2";
	/**
	 * 上传logo图片类型
	 */
	public final static String[] LOGO_UPLOAD_FILETYPE = { "jpg", "gif", "png", "bmp" };

	/**
	 * 航司logo图片存放路径
	 */
	public final static String LOGO_DIR_AIRLINES = "/image/airlineslogo";
	/**
	 * CRM大客户接口调用地址
	 */
	public final static String INTERFACE_SZAIR_CRM = "crmtravel/crmtravelInterface/queryCustInfoByCondition.htm";
	/**
	 * CRM协议客户调用地址
	 */
	public final static String INTERFACE_SZAIR_CRMIN = "crmtravel/crmtravelInterface/findPassengerByCustNo.htm";
	/**
	 * CRM净结点调用地址
	 */
	public final static String INTERFACE_SZAIR_CRM_ZHPERCEN = "crmtravel/crmtravelInterface/queryPolicyInfoByConditions.htm";
	/**
	 * /** 支付接口地址
	 */
	public final static String ONLINE_PAY = "pay/gatewaypay.htm";
	/**
	 * 在线支付退款接口地址
	 */
	public final static String ONLINE_REFUND = "pay/gatewayrefund.htm";
	/**
	 * 无卡支付退款接口地址
	 */
	public final static String ONLINE_CARD = "pay/pay.htm";
	/**
	 * 无卡支付退款接口地址
	 */
	public final static String ONLINE_CARD_REFUND = "pay/refund.htm";
	/**
	 * 验证码获取
	 */
	public final static String ONLINE_CARD_CODE = "pay/geteposverifycode.htm";
	/**
	 * 信用卡验证码获取
	 */
	public final static String ONLINE_CARD_CREDIT = "pay/creditcardverifypay.htm";
	/**
	 * 接口生成订单成功
	 */
	public final static Integer CREATE_ORDER_STATUS_SUCCESS = 1;
	/**
	 * 接口生成订单失败
	 */
	public final static Integer CREATE_ORDER_STATUS_FAIL = 0;
	/**
	 * 出票通知接口成功
	 */
	public final static String MESSAGE_ORDER_STATUS_SUCCESS = "0";
	/**
	 * 出票通知接口失败
	 */
	public final static String MESSAGE_ORDER_STATUS_FAIL = "1";
	/**
	 * 接口取消订单成功
	 */
	public final static Integer CANCEL_ORDER_STATUS_SUCCESS = 1;
	/**
	 * 接口取消订单失败
	 */
	public final static Integer CANCEL_ORDER_STATUS_FAIL = 0;
	/**
	 * 接口退/废票请求成功
	 */
	public final static Integer REFUNDTICKET_REQUEST_STATUS_SUCCESS = 1;
	/**
	 * 接口退/废票请求失败
	 */
	public final static Integer REFUNDTICKET_REQUEST_STATUS_FAIL = 0;

	/**
	 * 生成pnr编码接口地址
	 */
	public final static String ORDER_CREATE_PNR_INTERFACE = "orderReservationInterface/createPnr.htm";
	/**
	 * 取消pnr编码接口地址
	 */
	public final static String ORDER_CANCEL_PNR_INTERFACE = "orderReservationInterface/deletePnrByXml.htm";
	/**
	 * 授权PNR接口地址
	 */
	public final static String PNR_AUTHORIZE_INTERFACE = "orderReservationInterface/addOfficeNo.htm";
	/**
	 * 获取票号信息接口地址
	 */
	public final static String DETR_NO_INTERFACE = "orderReservationInterface/detrNoOption.htm";

	/**
	 * 产品订单类型前缀 1-国内 DOMESTIC 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE 4-保险
	 * INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_DOMESTIC_TYPE_PREFLITER = 1;
	/**
	 * 产品订单类型前缀 2-国际 INTERNATIONAL 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE
	 * 4-保险 INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_INTERNATIONAL_TYPE_PREFLITER = 2;
	/**
	 * 产品订单类型前缀 3-酒店 HOTE 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE 4-保险
	 * INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_HOTEL_TYPE_PREFLITER = 3;
	/**
	 * 产品订单类型前缀 4-保险 INSURANCE 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE 4-保险
	 * INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_INSURANCE_TYPE_PREFLITER = 4;
	/**
	 * 产品订单类型前缀 5-租车 CARRENTAL 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE 4-保险
	 * INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_CARRENTAL_TYPE_PREFLITER = 5;
	/**
	 * 产品订单类型前缀 6-其它 OTHER 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE 4-保险
	 * INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_OTHER_DOMESTIC_PREFLITER = 6;
	/**
	 * 产品订单类型前缀 9-关联订单 RELATION 1-国内 DOMESTIC 2-国际 INTERNATIONAL 3-酒店 HOTE 4-保险
	 * INSURANCE 5-租车 CARRENTAL 6-其它 OTHER 9-关联订单 RELATION
	 */
	public final static Integer ORDER_RELATION_TYPE_PREFLITER = 9;

	/**
	 * 到达时间加一天（时差）
	 */
	public final static String AIRBOOKLINE_ARRIVEDATE_MORE = "+";
	/**
	 * 到达时间减一天（时差）
	 */
	public final static String AIRBOOKLINE_ARRIVEDATE_LESS = "-";
	/**
	 * 乘客姓名，type固定填写“CN” //生成pnr接口使用
	 */
	public final static String AIRBOOK_PASSENGER_DEFAULT_NAMETYPE = "CN";
	/**
	 * 证件类型: NI-身份证 (NI-身份证,PP-护照,ID-军官证,DD-通行证,SD-士兵证,OD-其它,TB-台胞证)
	 */
	public final static String PASSENGER_CARD_TYPE_IDCARD = "NI";
	/**
	 * 证件类型:PP-护照
	 */
	public final static String PASSENGER_CARD_TYPE_PASSPORTCARD = "PP";

	/**
	 * pnr默认出票限制时间（单位：分钟，pnr生成接口最少仅支持航班时间前3小时即180分钟）
	 */
	public final static Integer PNR_TICKET_TIME_DEFAULT_LIMIT = 180;

	/**
	 * 检查pnr是否存在，默认检查订票时间间隔为3天
	 */
	public final static Integer PNR_TICKET_TIME_CHECK_DAY = 3;

	/**
	 * 订单类型：0正常单/1退票单/2废票单/3变更单（升舱改期单）/4调账单
	 */
	public final static Integer AIRBOOK_ALERTSTATUS_COMMON = 0;
	/**
	 * 订单类型：0正常单/1退票单/2废票单/3变更单（升舱改期单）/4调账单
	 */
	public final static Integer AIRBOOK_ALERTSTATUS_RETURN = 1;
	/**
	 * 订单类型：0正常单/1退票单/2废票单/3变更单（升舱改期单）/4调账单
	 */
	public final static Integer AIRBOOK_ALERTSTATUS_VOID = 2;
	/**
	 * 订单类型：0正常单/1退票单/2废票单/3变更单（升舱改期单）/4调账单
	 */
	public final static Integer AIRBOOK_ALERTSTATUS_CHANGE = 3;
	/**
	 * 订单类型：0正常单/1退票单/2废票单/3变更单（升舱改期单）/4调账单
	 */
	public final static Integer AIRBOOK_ALERTSTATUS_ADJUST = 4;

	/**
	 * 票证类型 Y-BSP/B-B2B/A-本票/C-B2C /W-外购
	 */
	public final static String AIRBOOK_ISET_BSP = "Y";
	public final static String AIRBOOK_ISET_B2B = "B";
	public final static String AIRBOOK_ISET_OWER = "A";
	public final static String AIRBOOK_ISET_B2C = "C";
	public final static String AIRBOOK_ISET_OUT = "W";
	/**
	 * 订单状态：新订单
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_NEWORDER = 10;
	/**
	 * 订单状态：已确认
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_CONFIRMED = 20;
	/**
	 * 订单状态：已出票
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_TICKETED = 30;

	/**
	 * 订单状态：出票失败
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_TICKETEDFAILURE = 28;

	/**
	 * 出票中
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_TICKETLOADING = 25;
	/**
	 * 订单状态：已配送
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_SENDED = 40;
	/**
	 * 订单状态：已收银
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_CASHED = 50;
	/**
	 * 订单状态：退废单（新订单）
	 */
	// public final static Integer AIRBOOK_BOOKSTATUS_REFUND_NEWORDER=60;
	public final static Integer AIRBOOK_BOOKSTATUS_REFUND_NEWORDER = 10;
	/**
	 * 订单状态：退废已审核
	 */
	// public final static Integer AIRBOOK_BOOKSTATUS_REFUND_CONFIRMED=70;
	public final static Integer AIRBOOK_BOOKSTATUS_REFUND_CONFIRMED = 20;
	/**
	 * 订单状态：退废办理中
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUND_HANDING = 25;
	/**
	 * 订单状态：与客人办理已完成
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_GUEST_FINISHED = 80;
	/**
	 * 订单状态：与出票方办理已完成
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_SUPPLIER_FINISHED = 85;
	/**
	 * 订单状态：退款申请
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUNDCASH_APPLY = 90;
	/**
	 * 订单状态：退款审核
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUNDCASH_CONFIRMED = 91;
	/**
	 * 订单状态：已退款
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUNDCASH_REFUNDED = 92;
	/**
	 * 订单状态：订单已取消
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_ORDER_CANCELED = 0;
	/**
	 * 订单状态：订单和PNR已取消
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_ORDER_PNR_CANCLED = 1;
	/**
	 * 订单状态：退废单已取消
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUND_CANCELED = 2;
	/**
	 * 订单状态：拒绝出票
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUSE_TICKET = 5;
	/**
	 * 订单状态：拒绝退废
	 */
	public final static Integer AIRBOOK_BOOKSTATUS_REFUSE_REFUND = 6;

	/**
	 * 订单操作类型：1-产生订单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_NEWORDER = 1;
	/**
	 * 订单操作类型：10-生成pnr
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_CREATPNR = 10;
	/**
	 * 订单操作类型：20-修改订单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_EDIT = 20;

	/**
	 * 订单操作类型：30-订单支付
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_PAY = 30;

	/**
	 * 订单操作类型：40-取消订单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_CANCEL = 40;

	/**
	 * 订单操作类型：50-确认出票
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_CONFIRMTICKET = 50;

	/**
	 * 订单操作类型：60-订单锁定
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_CLOCK = 60;
	/**
	 * 订单操作类型：61-订单解锁
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_UNCLOCK = 61;

	/**
	 * 订单操作类型：70-手动出票
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_HANDTICKET = 70;

	/**
	 * 订单操作类型：71-自动出票
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUTOTICKET = 71;
	/**
	 * 订单操作类型：73-BSP自动出票
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_BSPTICKET = 73;
	/**
	 * 订单操作类型：72-代扣
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUTOPAY = 72;

	/**
	 * 订单操作类型：80-财务收银
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_FINANCIALCASHIER = 80;
	/**
	 * 订单操作类型：90-升舱改期
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_UPGRADESRESCHEDULED = 90;
	/**
	 * 订单操作类型：100-订单调账
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_TRANSFERACCOUNT = 100;
	/**
	 * 订单操作类型：110-申请退废
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_APPLYREFUND = 110;
	/**
	 * 订单操作类型：120-关联订单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_RELATEORDER = 120;
	/**
	 * 订单操作类型：130-发送短信
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_SENDSMS = 130;

	/**
	 * 订单操作类型：130-发送订单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_SENDORDER = 184;

	/**
	 * 订单操作类型：185-安排配送
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_ARRANGESEND = 185;
	/**
	 * 订单操作类型：140-打印配送单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_PRINTSEND = 140;
	/**
	 * 订单操作类型：150-拒绝出票
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_REFUSETICKET = 150;
	/**
	 * 订单操作类型：160-挂起
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_HANGEUP = 160;
	/**
	 * 订单操作类型：165-解挂
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_UNHANGEUP = 165;

	/**
	 * 订单操作类型：200-手工出票通知
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_HANDCRAFTOUTTICKETINFORM = 200;

	/**
	 * 订单操作类型：210-自动出票通知
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUTOOUTTICKETINFORM = 210;

	/**
	 * 订单操作类型：220-拒票通知
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_REFUSETICKETINFORM = 220;

	/**
	 * 订单操作类型：230-申请退票通知
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_REFUNDTICKETINFORM = 230;

	/**
	 * 订单操作类型：240-申请废票通知
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_VALIDTICKETINFORM = 240;

	/**
	 * 订单操作类型：241-手工生成退废单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_MANUALGENERATEREFORDER = 241;

	/**
	 * 订单操作类型：242-接口申请退废
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_INTERAPPLYORDER = 242;

	/**
	 * 订单操作类型：243-修改退废
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_EDITREFUND = 243;

	/**
	 * 订单操作类型：244-回传退票费
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_COMMONBACKCANCELLATIONFEE = 244;

	/**
	 * 订单操作类型：245-审核接口订单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUDITINTERORDER = 245;

	/**
	 * 订单操作类型：246-审核退废单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUDITREFUNDORDER = 246;

	/**
	 * 订单操作类型：247-自动作废
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUTOINVALID = 247;

	/**
	 * 订单操作类型：248-拒绝退废
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_REFUSEREFUND = 248;

	/**
	 * 订单操作类型：249-与客人办理
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_GUESTHANDLE = 249;
	/**
	 * 订单操作类型：259-立即审核
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_DIAWERHANDLE = 259;

	/**
	 * 订单操作类型：250-网点经理审核
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_BRANCHMANAGEAUDIT = 250;

	/**
	 * 订单操作类型：251-结算审核
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_SETTLEMENTAUDIT = 251;

	/**
	 * 订单操作类型：252-出纳审核
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_CASHIERAUDIT = 252;

	/**
	 * 订单操作类型：253-上传退票附件
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_UPLOADREFUNDFILE = 253;

	/**
	 * 订单操作类型：254-取消退废单
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_CANCELREFUND = 254;
	/**
	 * 订单操作类型：255-退废订单锁定
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_ISLOCK = 255;
	/**
	 * 订单操作类型：256-退废订单解锁
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_UNLOCK = 256;
	/**
	 * 订单操作类型：257-发送短信
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_SENDSMSREFUND = 257;
	/**
	 * 订单操作类型：258-自动符合
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_AUTOMATIC = 258;

	/**
	 * 订单操作类型：269-退废财务收银
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_FINANCIALCASHIERREFUND = 269;

	/**
	 * 订单操作类型：270-退废处理
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_REFUNDDETAIL = 270;

	/**
	 * 订单操作类型：271-释放VT
	 */
	public final static Integer AIRBOOK_RESOP_TYPE_RELEASEVT = 271;
	/**
	 * 退废票文件存放地址
	 */
	public final static String AIRBOOK_RESOP_TYPE_REFUNDFILEDIR = "/refundFile";

	/**
	 * 退废票文件下载指定地址
	 */
	public final static String AIRBOOK_RESOP_TYPE_REFUNDDOWNFILEDIR = "/upload/refundFile";

	/**
	 * 操作类初始状态，默认为0
	 */
	public final static Integer OP_STATUS_DEFAULT = 0;

	/**
	 * 机票订单操作类型定义:1-订单入库
	 */
	public final static Integer AIRBOOK_OP_INSERT = 1;

	/**
	 * 机票预订短信模板id
	 */
	public final static Integer AIRBOOK_CREATE_SMS_TEMPLATEID = 88;
	/**
	 * 系统默认操作员userID
	 */
	public final static String AIRBOOK_RESOP_DEFAULT_SYSTEMOPER = "system";
	/**
	 * 系统默认操作员corpID
	 */
	public final static String AIRBOOK_RESOP_DEFAULT_SYSTEMOPER_CORPID = "100001";

	/**
	 * 机票政策类型-散冲
	 */
	public final static Integer AIRBOOK_ISTEAM_CASUAL = 4;
	/**
	 * 机票订单默认officeID
	 */
	public final static String AIRBOOK_OFFICEID_DEFAULT = "SZX540";

	// ///电商通 [Start]
	// 平台类型
	public final static String PLATFORM_TYPE_TAOBAO = "4"; // 淘宝
	public final static String PLATFORM_TYPE_QUNAR = "3"; // 去哪儿
	public final static String PLATFORM_TYPE_XIECHENG = "5"; // 携程
	public final static String PLATFORM_TYPE_KUXUN = "7"; // 酷讯
	// 政策航程类型
	public final static String AIRTYPE_S = "S"; // 单程
	public final static String AIRTYPE_B = "B"; // 往返
	// 政策状态类型
	public static String POLICY_STATE = "0"; // 正常
	public static String POLICY_STATE_D = "1"; // 删除
	// 政策投放状态
	public static String UPLOAD_STATE_N = "0"; // 0未投放
	public static String UPLOAD_STATE_S = "1"; // 1投放成功
	public static String UPLOAD_STATE_O = "2"; // 2正在投放
	public static String UPLOAD_STATE_F = "3"; // 3投放失败
	public static String UPLOAD_STATE_C = "4"; // 4任务取消

	// ///电商通 [End]

	/**
	 * 订单缓存状态-正在导入W
	 */
	public final static String ORDERCACHE_STATUS_W = "W";
	/**
	 * 订单缓存状态-导入成功S
	 */
	public final static String ORDERCACHE_STATUS_S = "S";
	/**
	 * 订单缓存状态-导入失败F
	 */
	public final static String ORDERCACHE_STATUS_F = "F";
	/**
	 * 订单缓存状态-放弃导入A
	 */
	public final static String ORDERCACHE_STATUS_A = "A";
	/**
	 * 图片的后缀名
	 */
	public final static String[] FILETYPES = { "jpg", "bmp", "jpeg", "png", "gif" };
	public final static String BANKLOGO = "/image/payBank";
	/**
	 * 订单类型定义
	 */
	public final static String PAYNAME = "支付";
	public final static String PAYREFUND = "退款";
	public final static Integer OPTYPE = 2;// 操作类型
	/**
	 * 收款方式
	 */
	public final static Integer PAYTYPE1 = 1;// 收款
	public final static Integer PAYTYPE2 = 2;// 付款
	public final static Integer PAYTYPE3 = 3;// 退款
	/**
	 * 定义启用的银行
	 */
	public final static String BANKCODE = "Y";// 在用银行

	/**
	 * 短信模块发送状态
	 */
	public final static String SMS_STATUS_DELIVERED = "Delivered"; // 短消息已成功递交 。
	public final static String SMS_STATUS_DELIVERYUNCERTAIN = "DeliveryUncertain"; // 递交状态未知，例如：因为短消息被发送到另外一个网络
	public final static String SMS_STATUS_DELIVERYIMPOSSIBLE = "DeliveryImpossible"; // 无法成功发送，短消息在超时前无法被递交。
	public final static String SMS_STATUS_MESSAGEWAITING = "MessageWaiting"; // 消息仍在排队等待递交，这是一个临时状态，等待转换为前述的状态之一
	public final static String SMS_STATUS_DELIVERYTOTERMINAL = "DeliveryToTerminal"; // 短消息已发给终端
																						// 。
	public final static String SMS_STATUS_DELIVERYNOTIFICATIONNOTSUPPORTED = "DeliveryNotificationNotSupported"; // 不支持短消息提交通知
																													// 。
	public final static String SMS_STATUS_KEYWORDFILTERFAILED = "KeyWordFilterFailed"; // 关键字过滤未通过
																						// 。
	/**
	 * 短信执行状态
	 */
	public final static String SMS_WORKING_STATUS_SUCCESS = "Y"; // 执行成功
	public final static String SMS_WORKING_STATUS_FAILED = "N"; // 执行失败
	public final static String SMS_WORKING_STATUS_WAIT = "O"; // 未执行
	public final static String SMS_WORKING_STATUS_EACH = "C"; // 循环
	public final static String SMS_WORKING_STATUS_WORKING = "W"; // 执行中

	/**
	 * 快乐传媒短信执行状态
	 */
	public final static String HAMEDIA_WORKING_SEND = "S"; // 发送短信
	public final static String HAMEDIA_WORKING_OVERAGE = "O"; // 余额及已发送量
	public final static String HAMEDIA_WORKING_CHECKKEYWORD = "C"; // 校验非法关键词
	public final static String HAMEDIA_WORKING_QUERY = "Q"; // 状态报告
	public final static String HAMEDIA_WORKING_CALLAPI = "QA"; // 上行接口

	/**
	 * 手机充值代理商直冲功能执行状态
	 */
	public final static String RECHARGE_WORKING_DIRECTFILL = "1"; // 直冲请求
	public final static String RECHARGE_WORKING_DIRECTPRODUCT = "2"; // 话费充值产品查询
	public final static String RECHARGE_WORKING_DIRECTSEARCH = "3"; // 订单结果查询接口
	public final static String RECHARGE_WORKING_ACCSEGMENT = "4"; // 号段查询接口

	/**
	 * 短信请求类型 0：不入库，只查询并发送 1:入库并发送
	 */

	public final static Integer SMS_POST_TYPE_SEARCHANDSEND = 0;
	public final static Integer SMS_POST_TYPE_SAVEANDSEND = 1;

	/**
	 * 短信模板
	 */
	public final static String SMSTYPE_ISAUTO_TYPE_YES = "Y"; // 是否自动发送 Y
	public final static String SMSTYPE_ISAUTO_TYPE_NO = "N"; // 是否自动发送 N
	public final static String SMSTYPE_ISDEFAULT_TYPE_IS = "Y"; // 是否默认 Y
	public final static String SMSTYPE_ISDEFAULT_TYPE_NOT = "N"; // 是否默认 N
	public final static String SMSTYPE_STATUS_ENABLE = "Y"; // 状态 Y 正常
	public final static String SMSTYPE_STATUS_DISABLED = "N"; // 状态 N 暂停

	/**
	 * 短信发送渠道 M:mas2.0 H:快乐传媒
	 */
	public final static String SMSCANAL_TYPE_MAS = "M"; // mas2.0
	public final static String SMSCANAL_TYPE_HAPPYMEDIA = "H"; // 快乐传媒
	/**
	 * 支付类型
	 */
	public final static String PAYCARDTYPE1 = "A";// 无卡支付
	public final static String PAYCARDTYPE2 = "B";// 语音支付
	public final static String PAYYIBAOSUCCESS = "1";// 易宝调用成功
	public final static String PAYHUIFUSUCCESS = "000000";// 汇付调用成功
	/**
	 * 平台分配的用户ID
	 */
	public final static String USERID = "001";// 平台分配的用户ID
	/**
	 * 支付表状态
	 */
	public final static Integer PAYSTATUS1 = 0;// 等待支付---即退款成功数据
	public final static Integer PAYSTATUS2 = 1;// 支付中---调用支付时状态
	public final static Integer PAYSTATUS3 = 2;// 支付完成--接口推送时状态
	public final static Integer PAYSTATUS4 = 3;// 支付失败--接口推送时状态
	/**
	 * 出票状态
	 */
	public final static Integer TICKETSTATUS0 = 0;// 未出票
	public final static Integer TICKETSTATUS1 = 1;// 已出票
	public final static Integer TICKETSTATUS2 = 2;// 有订单，代扣失败
	public final static Integer TICKETSTATUS3 = 3;// 出票中
	public final static Integer TICKETSTATUS4 = 4;// 出票失败
	public final static Integer TICKETSTATUS5 = 5;// 导单成功,支付成功
	/**
	 * 采购接口配置
	 */
	public final static String GETPOLICY = "ptws/getPolicies.htm";// 获取政策
	public final static String CREATEORDER = "ptws/createOrder.htm";// 生成订单
	public final static String AUTOPAY = "ptws/autoPay.htm";// 代扣支付
	public final static String BSPTICK = "autoTickInterfaces/bspAutoTicket.htm";// BSP出票请求地址
	/**
	 * 平台状态
	 */
	public final static String OPENSTATUS = "Y";// 获取政策
	public final static String CLOASESTATUS = "N";// 生成订单

	/**
	 * 平台类型
	 */
	public final static String PLATSTATUSSUP = "0";// 供应
	public final static String PLATSTATUSCGL = "1";// 采购

	/**
	 * 深航区域码
	 */
	public final static String PLATCORPID = "SZ";
	/**
	 * 自动出票状态码
	 */
	public final static String ORDERSTATUS1 = "1";// 订单提交失败
	public final static String ORDERSTATUS2 = "2";// 自动代扣失败
	public final static String ORDERSTATUS3 = "3";// 接口失败
	public final static String ORDERSTATUS4 = "4";// 成功
	/**
	 * 出票操作日志文字描述
	 */
	public final static String HANDCONTENT = "手动出票成功";// 成功
	public final static String HANDCONTENT1 = "手动出票失败";// 失败
	public final static String AUTOCONTENT = "自动出票成功";// 成功
	public final static String AUTOCONTENT1 = "自动出票失败";// 失败
	public final static String DAODANFACLSE = "导单失败";// 失败
	/**
	 * 排除联程出票
	 */
	public final static String AUTOTICKPTID = "12";// 鹏鹏的平台

	/**
	 * 采购平台支付方式
	 */
	public final static String PLATPAYMETHOD = "A";// 采购支付方式--支付宝
	/**
	 * 订单支付状态
	 */
	public final static Integer ORDERPAYSTATUS0 = 0;// 未支付
	public final static Integer ORDERPAYSTATUS1 = 1;// 支付中
	public final static Integer ORDERPAYSTATUS2 = 2;// 支付成功
	public final static Integer ORDERPAYSTATUS3 = 3;// 支付失败
	public final static Integer ORDERPAYSTATUS4 = 4;// 待支付---虚拟支付使用
	/**
	 * 配送方式
	 */
	public final static String RESSENDMETHOD0 = "5";// 配送方式

	/**
	 * 配送方式：配送收款
	 */
	public final static Integer RESSEND_SENDMETHOD_PS = 1;

	/**
	 * 配送方式：配送行程单
	 */
	public final static Integer RESSEND_SENDMETHOD_PX = 2;

	/**
	 * 配送方式：网点取票
	 */
	public final static Integer RESSEND_SENDMETHOD_WQ = 3;

	/**
	 * 配送方式：机场取票
	 */
	public final static Integer RESSEND_SENDMETHOD_JQ = 4;
	/**
	 * 配送方式：快递
	 */
	public final static Integer RESSEND_SENDMETHOD_KD = 5;
	/**
	 * 配送方式：不需配送
	 */
	public final static Integer RESSEND_SENDMETHOD_BP = 6;
	/**
	 * 订单标记类型：C-紧急订单/G-GP订单/T-团队订单/I-接口订单/E-自动出票/F-符合自动出票
	 */
	public final static String ORDER_TAG_EMERGENCY = "C";
	/**
	 * 订单标记类型：C-紧急订单/G-GP订单/T-团队订单/I-接口订单/E-自动出票/F-符合自动出票
	 */
	public final static String ORDER_TAG_GP = "G";
	/**
	 * 订单标记类型：C-紧急订单/G-GP订单/T-团队订单/I-接口订单/E-自动出票/F-符合自动出票
	 */
	public final static String ORDER_TAG_TEAM = "T";
	/**
	 * 订单标记类型：C-紧急订单/G-GP订单/T-团队订单/I-接口订单/E-自动出票/F-符合自动出票
	 */
	public final static String ORDER_TAG_INTERFACE = "I";
	/**
	 * 订单标记类型：C-紧急订单/G-GP订单/T-团队订单/I-接口订单/E-自动出票/F-符合自动出票
	 */
	public final static String ORDER_TAG_AUTOOUTTICKET = "E";
	/**
	 * 订单标记类型：C-紧急订单/G-GP订单/T-团队订单/I-接口订单/E-自动出票/F-符合自动出票
	 */
	public final static String ORDER_TAG_CANAUTOOUTTICKET = "F";
	/**
	 * 订单是否发送过短信
	 */
	public final static Integer ORDERSENDSMS = 1;// 已发送
	/**
	 * 系统推单
	 */
	public final static String SYSTEMUSERID = "SYSTEM";

	/**
	 * 充值模板文件名
	 */
	public final static String PHONEFILL_TEMPLATE = "phoneFillTemplate.xlsx";
	/**
	 * 保险公司logo图片路径
	 */
	public final static String INSURANCECOMPANYLOGO_URL = "/image/insuranceCompany";// 保险公司Logo存放路径
	public final static String INSURANCECOMPANYLOGO_URL2 = "insuranceCompany";// 保险公司Logo存放相对image的路径
	/**
	 * 自动出票接口去回程类型 去程:0 返程 ：1
	 */
	public final static String AIRBOOK_AIRLINE_GOWAY = "0";
	public final static String AIRBOOK_AIRLINE_RETURNWAY = "1";
	/**
	 * 自动出票查询供应商政策查询类型 O：根据officeNo查询返点最高政策 S.根据供应商ID查询返点最高政策
	 */
	public final static String AUTOTICKET_SUPPLY_SEARCHTYPE_OFFICETYPE = "O";
	public final static String AUTOTICKET_SUPPLY_SEARCHTYPE_SUPPLYTYPE = "S";

	/**
	 * 自动出票office来源类型 S:指定office号类型 C：当前公司office号 P:政策带的office号
	 * I:订单自带office(适用于收单接口/供应导单)
	 */
	public final static String AUTOTICKET_OFFICESOURCE_SPECIFYOFFICE = "S";
	public final static String AUTOTICKET_OFFICESOURCE_CURRCORPOFFICE = "C";
	public final static String AUTOTICKET_OFFICESOURCE_POLICYOFFICE = "P";
	public final static String AUTOTICKET_OFFICESOURCE_INTEROFFICE = "I";
	/**
	 * 订单航程类型
	 */
	public final static Integer AIRBOOK_DETAIL_WAYTYPES = 0;// 单程
	public final static Integer AIRBOOK_DETAIL_WAYTYPEB = 1;// 往返
	public final static Integer AIRBOOK_DETAIL_WAYTYPEE = 2;// 联程
	/**
	 * 发短信时的订单来源
	 */
	public final static String SENDSMS_ORDERFROM_AIR = "A";// 机票
	public final static String SENDSMS_ORDERFROM_HOTEL = "H";// 酒店
	/**
	 * 是否修改订单明细表
	 */
	/**
	 * 是否修改订单明细表
	 */
	public final static String MODILY_NOORDERDETAIL = "0";// 为0时不修改
	public final static String MODILY_ORDERDETAIL = "1";// 为1时修改
	/**
	 * 保单操作类型
	 */
	public final static String INSUREADD = "add";
	public final static String INSUREMODIFY = "refusd";
	public final static String DOWNINSURE = "down";
	/**
	 * 保险状态
	 */
	public final static Integer ONLINENEW = 0;// 未投保
	public final static Integer ONLINESUCCESS = 1;// 投保成功
	public final static Integer ONLINEFAILE = 2;// 投保失败
	public final static Integer REFUSEDSUCCESS = 3;// 退保成功
	public final static Integer REFUSEDFAILE = 4;// 退保失败
	public final static Integer ONLINEPOLIC = 5;// 投保中
	/**
	 * 保单更新类型
	 */
	public final static String UPDATEMAP = "1";
	public final static String UPDATEBATCH = "2";
	/**
	 * 行程单打印
	 */
	public final static Integer PRINTFALSE = 0;// 未打印
	public final static Integer PRINTTRUE = 1;// 已打印
	/**
	 * 航意险处理类型
	 */
	public final static String INSUREHYXADD = "1";// 航意险新增
	public final static String INSUREHYXUPDATE = "2";// 航意险修改

	/**
	 * 保险接口返回状态
	 */
	public final static String INSURECORRECTSTATUS = "T";// 保险接口成功
	public final static String INSUREERRORSTATUS = "F";// 保险接口失败
	/**
	 * 上传文件的存放地址
	 */
	public final static String REPORT_ORACLINE_DIR = "/templent";
	public final static String REPORT_LOG_DIR = "/log";
	public final static String REPORT_ROOT_DIR = "/report";
	/**
	 * 核算项目分类 5-银行 6-中间账户 7-代金券
	 */
	public final static Integer ASSISTITEM_TYPE_BANK = 5;
	public final static Integer ASSISTITEM_TYPE_MIDDLEACCOUNT = 6;
	public final static Integer ASSISTITEM_TYPE_VOUCHER = 7;

	/**
	 * 短信模板中的smsChannel字段 短信渠道 A白屏预定 B机票出票 C配送单 D退费 E酒店预定 F酒店确认 G租车
	 */
	public final static String SMS_USECHANNEL01 = "A";
	public final static String SMS_USECHANNEL02 = "B";
	public final static String SMS_USECHANNEL03 = "C";
	public final static String SMS_USECHANNEL04 = "D";
	public final static String SMS_USECHANNEL05 = "E";
	public final static String SMS_USECHANNEL06 = "F";
	public final static String SMS_USECHANNEL07 = "G";

	/**
	 * 短信模板中的smsChannel字段 短信渠道Map
	 */
	public final static Map SMS_CHANELLIST = new HashMap() {
		{
			put("A", "白屏预定");
			put("B", "机票出票");
			put("C", "配送单");
			put("D", "退废");
			put("E", "酒店预定");
			put("F", "酒店确认");
			put("G", "租车");
		}
	};

	/**
	 * 产品政策中产品政策一字码 droupid 从字典表中取
	 */
	public final static Integer PRODUCT_POLICY_ONECHARACTER_CODE = 6;

	/**
	 * 默认保险产品代码（航意险）
	 */
	public final static String DEFAULT_INSUREPRODUCT_CODE = "1000000";
	/**
	 * 报表删除类型
	 */
	public final static String DELETE_TYPE_REPORT_SUPPERY = "A";// 供应商报表
	public final static String DELETE_TYPE_REPORT_SETTER = "B";// 结算室报表
	/**
	 * 客票状态
	 */
	public final static Integer ORDERDETAIL_TICKSTATUS_NORMAL = 0;// 正常
	/**
	 * 财务收银初始状态
	 */
	public final static Integer FINANCE_STATUS = 6;// 正常
	/**
	 * 订单锁定，或未锁定
	 */
	public final static Integer AIRBOOK_ISLOCK = 1;
	public final static Integer AIRBOOK_USLOCK = 0;
	public final static Integer AIRBOOK_ISLOCKGQ = 2;// 挂起
	/**
	 * 自定定义自动出票接口
	 */
	public final static String TAG_AUTO_TICK = "E";
	/**
	 * 字典表订单来源dictGroup 3
	 */
	public final static Integer ORDERFROM_DICTGROUP = 3;
	/**
	 * 是否散冲特价 0普通/1大客户/2直减/3k位/4散冲/5手工录入订单
	 */
	public final static Integer ORDER_ISTEAM_TYPE0 = 0;
	public final static Integer ORDER_ISTEAM_TYPE1 = 1;
	public final static Integer ORDER_ISTEAM_TYPE2 = 2;
	public final static Integer ORDER_ISTEAM_TYPE3 = 3;
	public final static Integer ORDER_ISTEAM_TYPE4 = 4;
	public final static Integer ORDER_ISTEAM_TYPE5 = 5;

	/**
	 * 酒店請求接口
	 */
	public final static Integer HOTEL_REQUEST_TYPE0 = 0;
	public final static Integer HOTEL_REQUEST_TYPE1 = 1;
	public final static Integer HOTEL_REQUEST_TYPE2 = 2;
	public final static Integer HOTEL_REQUEST_TYPE3 = 3;
	/**
	 * 保险订单类型 0-投保单/1退保单
	 */
	public final static Integer INSURANCE_ALERTSTATUS_TYPE0 = 0;
	public final static Integer INSURANCE_ALERTSTATUS_TYPE1 = 1;
	/**
	 * OFFice使用范围 1预订/2深航出票/3外航出票/4黑屏/5打印行程单
	 */
	public final static Integer OFFICE_USERANGE1 = 1;
	public final static Integer OFFICE_USERANGE2 = 2;
	public final static Integer OFFICE_USERANGE3 = 3;
	public final static Integer OFFICE_USERANGE4 = 4;
	public final static Integer OFFICE_USERANGE5 = 5;

	// 开币报表修改删除
	public final static Integer SECURITY_COIN_UPDATE = 1042;
	public final static Integer SECURITY_COIN_DELETE = 1043;

	// 费用项目修改删除
	public final static Integer SECURITY_COSTITEMS_UPDATE = 1054;
	public final static Integer SECURITY_COSTITEMS_DELETE = 1055;

	// 收入项目修改删除
	public final static Integer SECURITY_IMCOME_UPDATE = 1058;
	public final static Integer SECURITY_IMCOME_DELETE = 1059;

	// 儿童乐园信息删除
	public final static Integer SECURITY_CHILDREN_UPDATE = 1056;
	public final static Integer SECURITY_CHILEREN_DELETE = 1057;

	// 工厂订单修改
	public final static Integer SECURITY_FACTORYORDER_UPDATE = 1060;

	// 批发商订单修改
	public final static Integer SECURITY_WHOLESALERORDER_UPDATE = 1061;

	// 仓库修改
	public final static Integer SECURITY_DEPOTDET_UPDATE = 1062;

	// 验收评价
	// 如果用户没有输入评价内容，则默认随机如下几句评价内容：
	public static List<String> COMPREHENSIVEGRADE_GREATETHAN4 = new ArrayList<String>();;
	public static List<String> COMPREHENSIVEGRADE_LESSTHAN4 = new ArrayList<String>();;
	public static List<String> COMPREHENSIVEGRADE_LESSTHAN2GREATETHAN3 = new ArrayList<String>();;
	public static List<String> COMPREHENSIVEGRADE_LESSTHAN2 = new ArrayList<String>();;
	static {
		// 综合评分>=4.0
		COMPREHENSIVEGRADE_GREATETHAN4.add("出品质量非常高，非常满意！");
		COMPREHENSIVEGRADE_GREATETHAN4.add("设计师很专业，态度很好，非常满意！");
		COMPREHENSIVEGRADE_GREATETHAN4.add("创意及设计的质量和服务态度等都很到位，令我非常满意！");
		COMPREHENSIVEGRADE_GREATETHAN4.add("设计水准非常高，服务也很到位，非常满意！");
		COMPREHENSIVEGRADE_GREATETHAN4.add("品牌管家设计的作品非常满意，小包子平台也尽心尽责为客户服务！");
		COMPREHENSIVEGRADE_GREATETHAN4.add("超级满意，专业就是专业，非常感谢设计师！");
		COMPREHENSIVEGRADE_GREATETHAN4.add("沟通很顺畅，反馈很及时，设计很到位，服务有耐心，处理很灵活！");

		// 3.0<=综合评分<4.0
		COMPREHENSIVEGRADE_LESSTHAN4.add("挺好的，设计师态度很好，期待后期再合作。");
		COMPREHENSIVEGRADE_LESSTHAN4.add("服务不错，方案也比较满意。");
		COMPREHENSIVEGRADE_LESSTHAN4.add("服务不错，设计不错，交流很及时。");
		COMPREHENSIVEGRADE_LESSTHAN4.add("项目设计过程中沟通顺畅，态度良好，反馈及时。");
		COMPREHENSIVEGRADE_LESSTHAN4.add("总体还是比较满意的。");
		COMPREHENSIVEGRADE_LESSTHAN4.add("整体服务比较周到。");
		COMPREHENSIVEGRADE_LESSTHAN4.add("制作流程清晰，能够评价设计师，不错。");

		// 2.0<=综合评分<3.0
		COMPREHENSIVEGRADE_LESSTHAN2GREATETHAN3.add("服务还行。");
		COMPREHENSIVEGRADE_LESSTHAN2GREATETHAN3.add("设计师服务还算可以。");

		// 综合评分<2.0
		COMPREHENSIVEGRADE_LESSTHAN2.add("就那样吧。");
		COMPREHENSIVEGRADE_LESSTHAN2.add("一般般啦。");
	}

}
