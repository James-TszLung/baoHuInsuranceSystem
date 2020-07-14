<%@page session="false" contentType="text/html;charset=GBK" language="java" %><%--
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
--%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%--
--%><%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%--
--%><c:set var="ROOT" value="http://${header.host}${pageContext.request.contextPath}" scope="request"/><%--
--%>
<html>
	<head>
		<meta charset="GBK">
		<title>ACBOX</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/acbox/css/jquery.ajaxComboBox.3.7.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugins/jquery-core/jquery-1.11.0.min.js" charset="UTF-8"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/styles/acbox/js/jquery.ajaxComboBox.3.7.js" charset="UTF-8"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/acbox/acbox.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/styles/acbox/acbox.js" charset="UTF-8"></script>
	</head>
<body>
<div class="pageContent">
<div class="pageFormContent nowrap" layoutH="20">
<table><tr><td valign="top" width="50%" nowrap="nowrap">
<dl>
<li>=========//多选，分行显示模式acBox对象生成==================</li>
<div id="test_show_1111"></div>
<div class="acbox_div" id="test1" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//单选，acBox对象生成======================</li>
<div class="acbox_div" id="test2" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//多选，acBox对象生成======================</li>
<div class="acbox_div" id="test3" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//多选，不分行显示模式acBox对象生成=====================</li>
<div class="acbox_div" id="test4" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//单选级联：选部门->用户单选模式acBox对象生成========================</li>
<div class="acbox_div" id="test5" style="width: 500px;"></div></dl>
</td><td valign="top">
<pre>
//以下的内容已在“/acbox/acbox.js.jspf”JS文件定义供选择使用
/**************************************************************************************/
//------------------------------------------------------
//Select-only
//------------------------------------------------------		
//Only "Select" for the option
source		： 数据源的URL
init_val_src	： 可选，回显的数据的URL
select_field	： 可选，指定返回数据的字段名'id,name,level'
num			： 可选，创建ajax combobox的顺序号，在ID属性中使用
is_debug		： 是否显示调试按钮，true：是,false：否		
name_suffix	： 可选，返回值的name属性名的后缀值
multiple		： 可选，默认为false。true：为多选，false：为单选
rv_name		： 可选，返回值回填的的名，返回所有选择的值，以半角冒号（:）分隔
multi_limit		： 可选，限制多选值的最大个数，默认无限制，大于零有限制
sel_show_id_arry		： 可选，多选回显的ID数组。与NAME一一对应
sel_show_name_arry	： 可选，多选回显的NAME数据。与ID一一对应
rvalName		： 可选，返回值制定的名
init_valJson	： 可选，json回填数据，{sel_id_array:[1,2,3,4,5],sel_name_array:["热","积分","很少","的","就发生大幅"]} 
vclass		： 可选，值是否必须输入。'required'是必须输入
sel_callback / callBack_fun	： 可选，选中回调函数
isCssList / wrap_line		： 可选，只适用于多选，使用格式(&ltul&gt&ltli&gt&lt/li&gt...&lt/ul&gt)显示
isTop / show_bottom		： 可选，只适用于多选，true：输入框显示在TOP
is_delete					： 可选，只适用于多选，是否显示删除按钮，true：是,false：否
sel_show_id	： 可选，指定显示内容区域的ID
vspliter		： 可选，指定返回多选数据使用的分隔字符，默认是半角冒号（:）
numPerPage / pageSize	：  每页显示的记录数。默认是25
navi_num		： 分页条显示页列数。默认是5
/**************************************************************************************/

//以下是页面调用例子，结果如左边显示
/*************************************sample begin*************************************/
&lthtml&gt
&lthead&gt
&ltmeta charset="GBK"&gt
&ltlink rel="stylesheet" type="text/css" href="/acbox/css/jquery.ajaxComboBox.css"&gt
&ltscript type="text/javascript" src="http://www1.pconline.com.cn/js/pc.jquery1.3.js"&gt&lt/script&gt
&ltscript type="text/javascript" src="/acbox/js/jquery.ajaxComboBox-gbk.3.7.js"&gt&lt/script&gt
&ltlink rel="stylesheet" type="text/css" href="/acbox/acbox.css"&gt 
&ltscript type="text/javascript" src="/acbox/acbox.js.jspf"&gt&lt/script&gt
&lt/head&gt
&ltbody&gt
&ltdiv id="test1"&gt&lt/div&gt
===============================================================================
&ltdiv id="test2"&gt&lt/div&gt
===============================================================================
&ltdiv id="test3"&gt&lt/div&gt
===============================================================================
&ltdiv id="test4"&gt&lt/div&gt
===============================================================================
&ltdiv id="test5"&gt&lt/div&gt
&ltscript type="text/javascript"&gt
function test(){
	alert('call back function');
}
jQuery(document).ready(function($){
	/*多选，分行显示*/
	$('#test1').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["热","积分","很少","的","就发生大幅"]}
		,multiple : true
		,rv_name  : 'test1'
		,sel_show_id : 'test_show_1111'
		,wrap_line : true
		,is_delete:true
		,navi_num : 0
		,vspliter : ','
		,sel_callback : test 
		,is_debug : true
	});
	/*单选*/
	$('#test2').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
		,is_debug : true	
		,sel_callback : test 
	});
	/*多选，分行显示*/
	$('#test3').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
		,multiple : true	
		//,rv_name  : 'test3'
		,wrap_line : true		
		,is_delete:true
		,isTop : true 
		,is_debug : true
	});
	/*多选*/
	$('#test4').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["热","积分","很少","的","就发生大幅"]}
		,multiple : true
		,is_delete:true
		,is_debug : true
	});
	/*单选级联*/
	$('#test5').acBox({ 
		source 		: '/static/acbox/user/ajaxComboBox.jsp?callback=?'	 	
		,is_debug : true
		,sel_callback : test 
		,init_valJson:{forData:1,id:65946,name:"肖娜",parent_id:1434,sel_id_array:[0,1434],sel_name_array:["根", "时尚网_资讯部"]}
	,rv_name  : 'intere99sts'
	});
});
&lt/script&gt
&lt/body&gt
&lt/html&gt
/*************************************sample end****************************************/
</pre>
</td></tr></table>


<script type="text/javascript">
function test(){
	alert('tert');
}
jQuery(document).ready(function($){
	/*多选，分行显示*/
	$('#test1').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["热","积分","很少","的","就发生大幅"]}
		,multiple : true
		,rv_name  : 'test1'
		,sel_show_id : 'test_show_1111'
		,wrap_line : true
		,is_delete:true
		,navi_num : 0
		,vspliter : ','
		,sel_callback : test 
		,is_debug : true
	});
	/*单选*/
	$('#test2').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
		,is_debug : true	
		,sel_callback : test 
	});
	/*多选，分行显示*/
	$('#test3').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
		,multiple : true	
		//,rv_name  : 'test3'
		,wrap_line : true		
		,is_delete:true
		,isTop : true 
		,is_debug : true
	});
	/*多选*/
	$('#test4').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["热","积分","很少","的","就发生大幅"]}
		,multiple : true
		,is_delete:true
		,is_debug : true
	});
	/*单选级联*/
	$('#test5').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
		,is_debug : true
		,sel_callback : test 
		,init_valJson:{forData:1,id:65946,name:"肖娜",parent_id:1434,sel_id_array:[0,1434],sel_name_array:["根", "时尚网_资讯部"]}
	,rv_name  : 'intere99sts'
	});
});
</script>
</div>
</div>
</body>
</html>
