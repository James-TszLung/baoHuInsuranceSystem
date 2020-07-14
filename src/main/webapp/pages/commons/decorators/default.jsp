<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../taglibs.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title><sitemesh:write property='title' />
</title>
<link type="text/css" rel="stylesheet" href="${ctxPath }/styles/commons/module.css">
<link type="text/css" rel="stylesheet" href="${ctxPath }/styles/commons/contentStyle.css">
<link href="${ctxPath }/scripts/plugins/loading/loading.css" type="text/css" rel="stylesheet">
<link href="${ctxPath }/scripts/plugins/validator/jquery.validator.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${ctxPath }/scripts/plugins/alertify/alertify.css" />
<link rel="stylesheet" href="${ctxPath }/scripts/plugins/alertify/themes/alertify.default.css" />
<link rel="stylesheet" href="${ctxPath }/styles/commons/common_easyui.css" />
<link rel="stylesheet" href="${ctxPath }/scripts/plugins/jquery-easyui/themes/icon.css" />
<link rel="stylesheet" href="${ctxPath }/scripts/plugins/jquery-plugins/qtip2/jquery.qtip.css" />


<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-core/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-easyui/extend/easyui.layout-extend.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-easyui/extend/easyui.validatebox-extend.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-plugins/jquery.animate-colors.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/loading/jquery.bgiframe.min.js" type="text/javascript"
	charset='utf-8'></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/loading/loading-min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/validator/jquery.validator.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/validator/zh_CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jTemplate/jquery-jtemplates.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-easyui/extend/datagrid-detailview.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/dateutil/moment-with-locales.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/alertify/alertify.min.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-plugins/jquery.form.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-plugins/jquery.json.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-easyui/easyui.extend.util.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/commons/defaultAjax.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/commons/util.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/commons/JSON.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/jquery-plugins/qtip2/jquery.qtip.js"></script>
<script type="text/javascript" src="${ctxPath }/scripts/plugins/flash/swfobject/swfobject.js"></script>
</head>
<script type="text/javascript" src="${ctxPath }/scripts/commons/transverse-timeline.js"></script>
<script type='text/javascript' src='${ctxPath }/scripts/plugins/cityquery/citylist.js'></script>
<script type='text/javascript' src='${ctxPath }/scripts/plugins/cityquery/querycity.js'></script>



<sitemesh:write property='head' />
<body>
	<input id="metaId" type="hidden" value="&lt;sitemesh:write property='body.id' /&gt;" />
	<input id="metaClass" type="hidden" value="&lt;sitemesh:write property='body.class' /&gt;" />

	<sitemesh:write property='body' />
	<!-- 	BODY属性脚本		开始		 -->
	<script type="text/javascript">
		moment.locale('zh-cn');

		$.ajaxSetup({
			method : 'post',
			cache : false
		//关闭AJAX相应的缓存
		});
	</script>
	<%--禁用后退键 --%>
	<script type="text/javascript">
		//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
		function forbidBackSpace(e) {
			var ev = e || window.event; //获取event对象 
			var obj = ev.target || ev.srcElement; //获取事件源 
			var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
			//获取作为判断条件的事件类型 
			var vReadOnly = obj.readOnly;
			var vDisabled = obj.disabled;
			//处理undefined值情况 
			vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
			vDisabled = (vDisabled == undefined) ? true : vDisabled;
			//当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
			//并且readOnly属性为true或disabled属性为true的，则退格键失效 
			var flag1 = ev.keyCode == 8
					&& (t == "password" || t == "text" || t == "textarea")
					&& (vReadOnly == true || vDisabled == true);
			//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
			var flag2 = ev.keyCode == 8 && t != "password" && t != "text"
					&& t != "textarea" && t != "application/x-shockwave-flash";
			//判断 
			if (flag2 || flag1)
				return false;
		}
		//禁止后退键 作用于Firefox、Opera
		//document.onkeypress = forbidBackSpace;
		//禁止后退键  作用于IE、Chrome
		//document.onkeydown = forbidBackSpace;
	</script>
</body>
</html>