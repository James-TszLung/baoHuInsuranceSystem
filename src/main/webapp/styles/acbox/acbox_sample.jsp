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
<li>=========//��ѡ��������ʾģʽacBox��������==================</li>
<div id="test_show_1111"></div>
<div class="acbox_div" id="test1" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//��ѡ��acBox��������======================</li>
<div class="acbox_div" id="test2" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//��ѡ��acBox��������======================</li>
<div class="acbox_div" id="test3" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//��ѡ����������ʾģʽacBox��������=====================</li>
<div class="acbox_div" id="test4" style="width: 500px;"></div></dl>
<br/><dl>
<li>========//��ѡ������ѡ����->�û���ѡģʽacBox��������========================</li>
<div class="acbox_div" id="test5" style="width: 500px;"></div></dl>
</td><td valign="top">
<pre>
//���µ��������ڡ�/acbox/acbox.js.jspf��JS�ļ����幩ѡ��ʹ��
/**************************************************************************************/
//------------------------------------------------------
//Select-only
//------------------------------------------------------		
//Only "Select" for the option
source		�� ����Դ��URL
init_val_src	�� ��ѡ�����Ե����ݵ�URL
select_field	�� ��ѡ��ָ���������ݵ��ֶ���'id,name,level'
num			�� ��ѡ������ajax combobox��˳��ţ���ID������ʹ��
is_debug		�� �Ƿ���ʾ���԰�ť��true����,false����		
name_suffix	�� ��ѡ������ֵ��name�������ĺ�׺ֵ
multiple		�� ��ѡ��Ĭ��Ϊfalse��true��Ϊ��ѡ��false��Ϊ��ѡ
rv_name		�� ��ѡ������ֵ����ĵ�������������ѡ���ֵ���԰��ð�ţ�:���ָ�
multi_limit		�� ��ѡ�����ƶ�ѡֵ����������Ĭ�������ƣ�������������
sel_show_id_arry		�� ��ѡ����ѡ���Ե�ID���顣��NAMEһһ��Ӧ
sel_show_name_arry	�� ��ѡ����ѡ���Ե�NAME���ݡ���IDһһ��Ӧ
rvalName		�� ��ѡ������ֵ�ƶ�����
init_valJson	�� ��ѡ��json�������ݣ�{sel_id_array:[1,2,3,4,5],sel_name_array:["��","����","����","��","�ͷ������"]} 
vclass		�� ��ѡ��ֵ�Ƿ�������롣'required'�Ǳ�������
sel_callback / callBack_fun	�� ��ѡ��ѡ�лص�����
isCssList / wrap_line		�� ��ѡ��ֻ�����ڶ�ѡ��ʹ�ø�ʽ(&ltul&gt&ltli&gt&lt/li&gt...&lt/ul&gt)��ʾ
isTop / show_bottom		�� ��ѡ��ֻ�����ڶ�ѡ��true���������ʾ��TOP
is_delete					�� ��ѡ��ֻ�����ڶ�ѡ���Ƿ���ʾɾ����ť��true����,false����
sel_show_id	�� ��ѡ��ָ����ʾ���������ID
vspliter		�� ��ѡ��ָ�����ض�ѡ����ʹ�õķָ��ַ���Ĭ���ǰ��ð�ţ�:��
numPerPage / pageSize	��  ÿҳ��ʾ�ļ�¼����Ĭ����25
navi_num		�� ��ҳ����ʾҳ������Ĭ����5
/**************************************************************************************/

//������ҳ��������ӣ�����������ʾ
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
	/*��ѡ��������ʾ*/
	$('#test1').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["��","����","����","��","�ͷ������"]}
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
	/*��ѡ*/
	$('#test2').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
		,is_debug : true	
		,sel_callback : test 
	});
	/*��ѡ��������ʾ*/
	$('#test3').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
		,multiple : true	
		//,rv_name  : 'test3'
		,wrap_line : true		
		,is_delete:true
		,isTop : true 
		,is_debug : true
	});
	/*��ѡ*/
	$('#test4').acBox({ 
		source 		: '/one/geliuser/select.do?callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["��","����","����","��","�ͷ������"]}
		,multiple : true
		,is_delete:true
		,is_debug : true
	});
	/*��ѡ����*/
	$('#test5').acBox({ 
		source 		: '/static/acbox/user/ajaxComboBox.jsp?callback=?'	 	
		,is_debug : true
		,sel_callback : test 
		,init_valJson:{forData:1,id:65946,name:"Ф��",parent_id:1434,sel_id_array:[0,1434],sel_name_array:["��", "ʱ����_��Ѷ��"]}
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
	/*��ѡ��������ʾ*/
	$('#test1').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["��","����","����","��","�ͷ������"]}
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
	/*��ѡ*/
	$('#test2').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
		,is_debug : true	
		,sel_callback : test 
	});
	/*��ѡ��������ʾ*/
	$('#test3').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
		,multiple : true	
		//,rv_name  : 'test3'
		,wrap_line : true		
		,is_delete:true
		,isTop : true 
		,is_debug : true
	});
	/*��ѡ*/
	$('#test4').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
	 	,init_valJson:{sel_id_array:[1,2,3,4,5],sel_name_array:["��","����","����","��","�ͷ������"]}
		,multiple : true
		,is_delete:true
		,is_debug : true
	});
	/*��ѡ����*/
	$('#test5').acBox({ 
		source 		: '/one/yuser/select.do?q_eq_userType_i=2&acbox=y&callback=?'
		,is_debug : true
		,sel_callback : test 
		,init_valJson:{forData:1,id:65946,name:"Ф��",parent_id:1434,sel_id_array:[0,1434],sel_name_array:["��", "ʱ����_��Ѷ��"]}
	,rv_name  : 'intere99sts'
	});
});
</script>
</div>
</div>
</body>
</html>
