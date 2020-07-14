// 显示提示信息，停留三秒后自动消失
function showMessage(msg, title) {
	$.messager.show({
		title : title == null ? '温馨提示' : title,
		msg : msg,
		timeout : 3000,
		showType : 'fade'
	});
}
// 显示消息提醒对话框
function alertMessage(msg, title) {
	$.messager.alert(title == null ? '消息提醒' : title, msg);
}
// 显示消息确认对话框
function confirmMessage(msg, fun, title) {
	$.messager.confirm(title == null ? '消息确认' : title, msg, function(r) {
		if (r) {
			fun();
		}
	});
}
function fillFormData(form, obj) {
	var formEL = $(form);
	$.each(obj, function(index, item) {
		formEL.find("[name=" + index + "]").val(item);
		formEL.find("[name=" + index + "]").html(item);
	});
}
// 如果array的indexof不存在则自动创建该方法
if (typeof Array.indexOf !== 'function') {
	Array.prototype.indexOf = function(args) {
		var index = -1;
		for ( var i = 0, l = this.length; i < l; i++) {
			if (this[i] === args) {
				index = i;
				break;
			}
		}
		return index;
	}
}
/**
 * datagrid无数据时使用
 */
var noDataView = $.extend({}, $.fn.datagrid.defaults.view, {
	onAfterRender : function(target) {
		$.fn.datagrid.defaults.view.onAfterRender.call(this, target);
		var opts = $(target).datagrid('options');
		var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
		vc.children('div.datagrid-empty').remove();
		if (!$(target).datagrid('getRows').length) {
			var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
		}
	}
});

// 批量取消ajax异步请求
var shutAbleAjax = [];
function multiAjaxAbort() {
	for ( var i = 0; i < shutAbleAjax.length; i++) {
		shutAbleAjax[i].abort();
	}
	shutAbleAjax = [];
}

// 保存 Cookie
function setCookie(name, value) {
	expires = new Date();
	expires.setTime(expires.getTime() + (1000 * 86400 * 365));
	document.cookie = name + "=" + escape(value) + "; expires=" + expires.toGMTString() + "; path=/";
}

// 获取 Cookie
function getCookie(name) {
	cookie_name = name + "=";
	cookie_length = document.cookie.length;
	cookie_begin = 0;
	while (cookie_begin < cookie_length) {
		value_begin = cookie_begin + cookie_name.length;
		if (document.cookie.substring(cookie_begin, value_begin) == cookie_name) {
			var value_end = document.cookie.indexOf(";", value_begin);
			if (value_end == -1) {
				value_end = cookie_length;
			}
			return unescape(document.cookie.substring(value_begin, value_end));
		}
		cookie_begin = document.cookie.indexOf(" ", cookie_begin) + 1;
		if (cookie_begin == 0) {
			break;
		}
	}
	return null;
}

// 清除 Cookie
function delCookie(name) {
	var expireNow = new Date();
	document.cookie = name + "=" + "; expires=Thu, 01-Jan-70 00:00:01 GMT" + "; path=/";
}
/**
 * 判断是否为数组
 */
function isArray(data) {
	return '[object Array]' == Object.prototype.toString.call(data);
}
/**
 * 序列化form
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
function paramString2obj(serializedParams) {

	var obj = {};
	function evalThem(str) {
		var attributeName = str.split("=")[0];
		var attributeValue = str.split("=")[1];
		if (!attributeValue) {
			return;
		}

		var array = attributeName.split(".");
		for ( var i = 1; i < array.length; i++) {
			var tmpArray = Array();
			tmpArray.push("obj");
			for ( var j = 0; j < i; j++) {
				tmpArray.push(array[j]);
			}
			;
			var evalString = tmpArray.join(".");
			// alert(evalString);
			if (!eval(evalString)) {
				eval(evalString + "={};");
			}
		}
		;
		eval("obj." + attributeName + "='" + attributeValue + "';");

	}
	;
	var properties = serializedParams.split("&");
	for ( var i = 0; i < properties.length; i++) {
		evalThem(properties[i]);
	}
	;
	return obj;
}
$.fn.form2json = function() {
	var serializedParams = this.serialize();
	var obj = paramString2obj(serializedParams);
	return obj;
};
/*
 * 快速排序，按某个属性，或按“获取排序依据的函数”，来排序. @method soryBy @static @param {array} arr 待处理数组
 * @param {string|function} prop 排序依据属性，获取 @param {boolean} desc 降序 @return
 * {array} 返回排序后的新数组
 */
var sortBy = function(arr, prop, desc, func) {
	var props = [], ret = [], i = 0, len = arr.length;
	if (typeof prop == 'string') {
		for (; i < len; i++) {
			var oI = arr[i];
			(props[i] = new String(oI && oI[prop] || ''))._obj = oI;
		}
	} else if (typeof prop == 'function') {
		for (; i < len; i++) {
			var oI = arr[i];
			(props[i] = new String(oI && prop(oI) || ''))._obj = oI;
		}
	} else {
		throw '参数类型错误';
	}
	if (func)
		props.sort(func);
	else
		props.sort();
	for (i = 0; i < len; i++) {
		ret[i] = props[i]._obj;
	}
	if (desc)
		ret.reverse();
	return ret;
};

/**
 * 输入字母自动转换大写
 * 
 * @param len
 *            输入长度限制 示例: $("#code").key_press(); $("#code").key_press(3);
 */
$.fn.key_press = function(len) {
	this.keypress(function(event) {
		if ($(this).val() != undefined) {
			if (len == undefined || $(this).val().length < len) { // 限制输入字符串长度
				var key = event.which;// event.keyCode
				if (key >= 97 && key <= 122) {// 找到输入是小写字母的ascII码的范围
					event.preventDefault();// 取消事件的默认行为
					$(this).val($(this).val() + String.fromCharCode(key - 32));// 转换
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	});
	this.blur(function() {
		$(this).val($(this).val().toUpperCase());
	});
};

// 身份证验证相关设置和方法
var aCity = {
	11 : "北京",
	12 : "天津",
	13 : "河北",
	14 : "山西",
	15 : "内蒙古",
	21 : "辽宁",
	22 : "吉林",
	23 : "黑龙江",
	31 : "上海",
	32 : "江苏",
	33 : "浙江",
	34 : "安徽",
	35 : "福建",
	36 : "江西",
	37 : "山东",
	41 : "河南",
	42 : "湖北",
	43 : "湖南",
	44 : "广东",
	45 : "广西",
	46 : "海南",
	50 : "重庆",
	51 : "四川",
	52 : "贵州",
	53 : "云南",
	54 : "西藏",
	61 : "陕西",
	62 : "甘肃",
	63 : "青海",
	64 : "宁夏",
	65 : "新疆",
	71 : "台湾",
	81 : "香港",
	82 : "澳门",
	91 : "国外"
}

// 身份证验证方法
function isCardID(sId) {
	var iSum = 0;
	var info = "";
	if (!/^\d{17}(\d|x)$/i.test(sId))
		return "你输入的身份证长度或格式错误";
	sId = sId.replace(/x$/i, "a");
	if (aCity[parseInt(sId.substr(0, 2))] == null)
		return "你的身份证地区非法";
	sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
	var d = new Date(sBirthday.replace(/-/g, "/"));
	if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))
		return "身份证上的出生日期非法";
	for ( var i = 17; i >= 0; i--)
		iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
	if (iSum % 11 != 1)
		return "你输入的身份证号非法";
	return true;// aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")
}

// 从身份证中获取生日
function getBirthdatByIdNo(iIdNo) {
	var tmpStr = "";
	var idDate = "";
	var tmpInt = 0;
	var strReturn = "";

	if (iIdNo.length == 15) {
		tmpStr = iIdNo.substring(6, 12);
		tmpStr = "19" + tmpStr;
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

		return tmpStr;
	} else {
		tmpStr = iIdNo.substring(6, 14);
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

		return tmpStr;
	}
}

// 截取指定长度字符串放到<div>
function getShortDiv(str, length) {
	if (str.length > length) {
		var newstr = str.substr(0, length) + "...";
	} else {
		var newstr = str;
	}
	return "<div title='" + str + "'>" + newstr + "</div>";
}

// 自定义公共校验规则
$.extend($.fn.validatebox.defaults.rules, {
	// 验证IP地址
	ip : {
		validator : function(value) {
			var regexp = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
			var valid = regexp.test(value);

			if (!valid) {// 首先必须是 xxx.xxx.xxx.xxx
				// 类型的数字，如果不是，返回false
				return false;
			}

			var _isValid = false;
			$.each(value.split('.'), function(i, num) {
				// 切割开来，每个都做对比，可以为0，可以小于等于255，但是不可以0开头的俩位数
				// 只要有一个不符合就返回false
				if (num.length > 1 && num.charAt(0) === '0') {
					// 大于1位的，开头都不可以是‘0’
					_isValid = false;
				} else if (parseInt(num, 10) > 255) {
					// 大于255的不能通过
					_isValid = false;
				} else
					_isValid = true;
			});

			return _isValid;
		},
		message : 'IP地址格式不正确'
	},
	// 用户账号验证(只能包括 _ 数字 字母)
	account : {// param的值为[]中值
		validator : function(value, param) {
			if (value.length < param[0] || value.length > param[1]) {
				$.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
				return false;
			} else {
				if (!/^(?![^a-zA-Z]+$)|(?!\D+$).+$/.test(value)) {
					$.fn.validatebox.defaults.rules.account.message = '只允许输入英文字母、数字';
					return false;
				} else {
					return true;
				}
			}
		},
		message : '请输入{0}到{1}长度的账号'
	},
	// 判断最小长度
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '最少输入 {0} 个字符'
	},
	numberLength : {
		validator : function(value, param) {
			if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
				return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
			} else {
				return false;
			}
		},
		message : '请输入0到100之间的最多俩位小数的数字'
	},
	// 数字
	number : {
		validator : function(value, param) {
			return /^\d*$/.test(value);
		},
		message : '请输入数字'
	},
	// 字母
	alphabet : {
		validator : function(value, param) {
			return /^[a-zA-Z]*$/.test(value);
		},
		message : '请输入字母'
	},
	// 验证年龄
	zhengshu : {
		validator : function(value, param) {
			return /^(?:[1-9][0-9]?|100)$/.test(value);
		},
		message : '必须是0到100之间的整数'
	},
	// 航线校验
	airline : {
		validator : function(value, param) {
			return /^[A-Z]{3}-[A-Z]{3}$/.test(value);
		},
		message : '请输入正确航线格式，如XXX-XXX'
	},
	// 数字或字母
	alphanumeric : {
		validator : function(value, param) {
			return /^[a-zA-Z0-9]*$/.test(value);
		},
		message : '请输入数字或字母'
	},
	// 校验小数，参数指定整数和小数范围，如param[6,2]表示整数位最多6位，小数位最多2位
	decimal : {
		validator : function(value, param) {
			// 整数位
			if (param == null || param[0] == null) {
				var d1 = "^\\d{1,}"; // 不指定范围，至少一位整数
			} else if (param[0] > 0) {
				var d1 = "^\\d{1," + param[0] + "}";
			} else { // 0表示没有整数，整数位是0
				var d1 = "^0";
			}
			// 小数位
			if (param == null || param[1] == null) {
				var d2 = "(\\.\\d{1,})?$"; // 不指定范围，至少一位小数
			} else if (param[1] > 0) {
				var d2 = "(\\.\\d{1," + param[1] + "})?$";
			} else { // 0表示没有小数
				var d2 = "$";
			}
			var regexp = new RegExp((d1 + d2), "g");
			return regexp.test(value);
		},
		message : '请填写有效的数字 '
	},
	// 数字范围
	numberRange : {
		validator : function(value, param) {
			return value >= param[0] && value <= param[1];
		},
		message : '请输入{0}到{1}之间的数字'
	},
	// 中文
	chinese : {
		validator : function(value, param) {
			return /^[\u4e00-\u9fa5]*$/.test(value);
		},
		message : '请输入中文'
	},
	// 字节长度（一个双字节字符长度计2，ASCII字符计1）
	// param[0]最小长度，param[1]最大长度
	byteLength : {
		validator : function(value, param) {
			if (param == null || param[0] == null) {
				return true;
			}
			var len = value.replace(/[^\x00-\xff]/g, "aa").length;
			return len >= param[0] && len <= param[1];
		},
		message : '输入内容长度必须介于{0}和{1}之间'
	},
	// 联系电话
	telephone : {
		validator : function(value, param) {
			// 支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号，例如0775-27771999-3917
			return /^(\d{11}|(\d{3,4}\-\d{7,8}(\-\d{1,4})?))$/.test(value);
		},
		message : '电话格式不正确'
	},
	// 联系电话
	phoneVail : {
		validator : function(value, param) {

			return false;
		},
		message : '该手机号码已经被使用了，请更换其他手机号码!'
	},
	emailVail : {
		validator : function(value, param) {

			return false;
		},
		message : '该邮箱地址已经被使用了，请更换其他邮箱地址!'
	},
	phoneVailTrue : {
		validator : function(value, param) {

			return true;
		},
		message : 'ok'
	},
	// 邮箱地址
	email : {
		validator : function(value, param) {
			// 支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号，例如0775-27771999-3917
			return /^[a-zA-Z0-9_-]+(\.([a-zA-Z0-9_-])+)*@[a-zA-Z0-9_-]+[.][a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*$/.test(value);
		},
		message : '邮箱地址格式不正确'
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '格式不正确,请使用下面格式:020-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确'
	},
	unnormal : {// 验证是否包含空格和非法字符
		validator : function(value) {
			return /.+/i.test(value);
		},
		message : '输入值不能为空和包含其他非法字符'
	},
	// 城市三字码，参数是分隔符，如CAN/PEK
	cityCode : {
		validator : function(value, param) {
			var restr = "^[A-Z]{3}(" + (param == null ? "" : param) + "[A-Z]{3})*$";
			var regexp = new RegExp(restr, "g");
			return regexp.test(value);
		},
		message : '格式不正确'
	},
	// 有效时间段校验 因为message无法自定义多种提示，需要在外部传进来 param[0]起始时间
	// param[1]结束时间
	// param[2]提示文字
	validDate : {
		validator : function(value, param) {
			var flag = false;
			if (param[0] != '' && param[1] != '') {
				var startValue = $("#" + param[0]).val();
				var endValue = $("#" + param[1]).val();
				var startDate = new Date(startValue.replace("-", "/").replace("-", "/"));// 开始时间
				var endDate = new Date(endValue.replace("-", "/").replace("-", "/"));// 结束时间
				var num = dateInterval; // 间隔数
				var unit = dateIntervalUnit;// 单位：月/天
				if (unit == 1) {
					startDate.setMonth(startDate.getMonth() + num, startDate.getDay());
					if (endDate > startDate) {// 结束日期必须小于开始日期+间隔
						flag = false;
					} else {
						flag = true;
					}
				} else if (unit == 2) {
					startDate.setDate(startDate.getDate() + num);// num天后的日期
					if (endDate > startDate) {// 结束日期必须小于开始日期+间隔
						flag = false;
					} else {
						flag = true;
					}
				}
			} else {
				alertMessage("参数出错，请联系管理员！");
			}
			return flag;
		},
		message : '{2}'
	},
	// 验证用户名是否重复
	remote : {
		validator : function(value, url) {
			$.post(url + "", {
				userName : value
			}, function(data) {
				var rs = JSON2.parse(data);
				if (rs.success == true) {
					flag = true;
					$.data(document.body, "flag", flag);
				} else if (rs.success == false) {
					flag = false;
					$.data(document.body, "flag", flag);
				}
			});
			return $.data(document.body, "flag");
		},
		message : '该用户名已被占使用'
	},
	idcared : {
		validator : function(value, param) {
			var flag = isCardID(value);
			return flag == true ? true : false;
		},
		message : '不是有效的身份证号码'
	}
});

/**
 * 加载等待提示
 */
$.fn.extend({
	loading : function() {
		return this.each(function() {
			$(this).html('<div class="panel-loading">正在加载中...</div>');
		});
	}
});
/**
 * 结合my97，以便在一些特殊场合使用，如datagrid编辑模式中
 */
$
		.extend(
				$.fn.datagrid.defaults.editors,
				{
					my97 : {
						init : function(container, options) {
							var input = $(
									'<input class="Wdate easyui-validatebox" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\',readOnly:true});"/>')
									.appendTo(container);
							input.validatebox(options);
							return input;
						},
						getValue : function(target) {
							return $(target).val();
						},
						setValue : function(target, value) {
							$(target).val(value);
						},
						resize : function(target, width) {
							var input = $(target);
							if ($.boxModel == true) {
								input.width(width - (input.outerWidth() - input.width()));
							} else {
								input.width(width);
							}
						}
					},
					my97HH : {
						init : function(container, options) {
							var input = $(
									'<input class="Wdate easyui-validatebox" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',minDate:\'%y-%M-%d HH:{%m+1}:ss\',readOnly:true});"/>')
									.appendTo(container);
							input.validatebox(options);
							return input;
						},
						getValue : function(target) {
							return $(target).val();
						},
						setValue : function(target, value) {
							$(target).val(value);
						},
						resize : function(target, width) {
							var input = $(target);
							if ($.boxModel == true) {
								input.width(width - (input.outerWidth() - input.width()));
							} else {
								input.width(width);
							}
						}
					},
					my97HD : {
						init : function(container, options) {
							var input = $(
									'<input class="Wdate easyui-validatebox" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\',minDate:\'%y-%M-#{%d+1}\',readOnly:true});"/>')
									.appendTo(container);
							input.validatebox(options);
							return input;
						},
						getValue : function(target) {
							return $(target).val();
						},
						setValue : function(target, value) {
							$(target).val(value);
						},
						resize : function(target, width) {
							var input = $(target);
							if ($.boxModel == true) {
								input.width(width - (input.outerWidth() - input.width()));
							} else {
								input.width(width);
							}
						}
					}
				});
