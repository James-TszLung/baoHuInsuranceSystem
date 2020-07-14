<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	//禁止缓存
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="${ctxPath}/images/images/logo-50px.jpg" >
<title><sitemesh:write property='title' />
</title>
<sitemesh:write property='head' />
<link type="text/css" rel="stylesheet"
	href="${ctxPath }/styles/unique/login.css">
<script type="text/javascript"
	src="${ctxPath }/scripts/unique/layout_jquery.js"></script>
<script type="text/javascript"
	src="${ctxPath }/scripts/unique/layout_script.js"></script>
</head>
<%--oncontextmenu="return false;" 可以屏蔽页面右键--%>
<body onResize="window_resizeHandler();" style="min-width:800px;">
	<sec:authentication property="principal" var="userDetails" />
	<%--***模板区域-开始***--%>
	<%--右上角菜单模板--%>
	<textarea id="top_right_menu_model" style="display:none;">
		<!--	
				{#foreach $T.rtMenu as rtm}
					<img
						class="hotspot"
						title="{$T.rtm.cname}"
						src="${ctxPath }{$T.rtm.img}" width="30"
						height="31" onclick="rightUpMenueEvent('{$T.rtm.img}')"/>
				{#/for}
		-->
	</textarea>
	<%--左侧菜单模板--%>
	<textarea id="left_menu_model" style="display:none;">
		<!--	
			{*
			主模板
			*}
				{#template MAIN}
					{#foreach $T as lm}
						{#if $T.lm.childs.length > 0}
							<div class="b" id="s{$T.lm.id}" onClick="w('{$T.lm.id}')"">
								<i class="{$T.lm.icon} iconfont" style="margin-right: 20px;"></i><span>{$T.lm.funName}</span>
									<b class="arrow iconfont"></b>
							</div>
							<div class="ps" id="{$T.lm.id}"
									 style="display:none;"
							>
								{#include CHILD1 root=$T.lm}
							</div>
						{#/if}
					{#/for}
				{#/template MAIN}
			{*
			递归子模板1
			*}
				{#template CHILD1}
				  {#foreach $T.childs as entry}
				  	<div id="s{$T.entry.id}"  onClick="k('{$T.entry.id}');
				  		{#if $T.entry.funUrl}
				  			visitUrl('{$T.entry.funUrl}','{$T.funName}','{$T.entry.funName}','','s{$T.entry.id}');
				  		{#/if}
				  		"title="{$T.entry.funName}"
				  		onMouseover="menu_mouseoverhandler('s{$T.entry.id}',true)"
				  		onMouseout="menu_mouseoverhandler('s{$T.entry.id}',false)"
				  		parent="{$T.entry.parent=$T.funName}">
				  		<ul>
						<li style="padding-left:42px;height:40px;line-height:40px;border-bottom:1px solid #e5e5e5;font-size:14px;"><a>{$T.entry.funName}</a></li>
						</ul>
					</div>
				    {#if $T.entry.childs}
				    	<div class="ps" id="{$T.entry.id}">
				    		{#include CHILD2 root=$T.entry}
				    	</div>
				    {#/if}
				  {#/for}
				{#/template CHILD1}
			{*
			递归子模板2
			*}
				{#template CHILD2}
					{#foreach $T.childs as entry}
						<div id="{$T.id}{$T.entry$index}" class="bd left_menu_div" parent="{$T.entry.parent=$T.funName}"
							onMouseover="menu_mouseoverhandler('{$T.id}{$T.entry$index}',true)"
				  		onMouseout="menu_mouseoverhandler('{$T.id}{$T.entry$index}',false)"
				  		{#if $T.entry.funUrl}
				  			onclick="visitUrl('{$T.entry.funUrl}','{$T.parent}','{$T.entry.parent}','{$T.entry.funName}','{$T.id}{$T.entry$index}');"
				  		{#/if}
				  		title="{$T.entry.funName}"				  		
				  		>
							<img src="${ctxPath }/images/common_page/sbg.gif" width="7"
								height="7">{$T.entry.funName}
						</div>
					{#/for}
				{#/template CHILD2}
		-->
	</textarea>
	<%--
	--%><%--***模板区域-结束***--%>
	<div id="main_layout" class="easyui-layout" fit="true">
		<div data-options="region:'north',border:false" style="background: #f2f2f2;width:189px;border-right:1px solid #ccc;">
			<div class="top" >
				<table width="100%" height="45px;" border="0" align="center">
					<tbody>
						<tr>
<%--							<td align="center" width="10%">--%>
<%--								<a href="#" style="width:170px;padding:7px 20px;float:left;">--%>
<%--									<div style="width:77px;height:23px;padding-top:3px;text-align:center;float:left;"><img src="/operateSystem/images/common_page/logo.png" alt=""></div>--%>
<%--									<h1 style="height:24px;line-height:24px;padding-left:14px;margin-top:3px;font-size:16px;color:#FFF;border-left:1px solid #FFF;float:right;">运营平台</h1>--%>
<%--								</a>--%>
<%--							</td>--%>
							<td align="center" width="10%">
									<a href="#" style="width:250px;padding:3px 20px;float:left;">
										<div style="width:32px;height:34px;padding-top:3px;text-align:center;float:left;"><img src="${ctxPath }/images/common_page/logo.png" alt=""></div>
										<h1 style="height:24px;line-height:24px;margin-top:3px;font-size:16px;color:#FFF;border-left:1px solid #FFF;margin-left: 50px;">保乎笔记业务管理系统</h1>
									</a>
								</td>
								<%--<h1>运营平台</h1>
							--%><%--<td align="center" width="1%"><img src="${ctxPath }/images/common_page/middles.gif"
								width="2" height="60"></td>
							<td align="center" width="8%"><img src="${ctxPath }/images/common_page/names.gif"
								height="25"></td>
							--%><td style="padding:0px 4px;" align="center" width="42%">
								<div class="info"></div></td>
							<td width="46%" align="right" style="padding:0px 26px 6px 0px;">
								<div id="" style="color: #FFF;font-size: 16px;"><a href="${ctxPath}/auth/toLogout.htm" style="color: #FFF;font-size: 16px;">退出</a>
								</div></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="nav">
				<div class="nav_left">
					欢迎您<span id="user_name_text"
						title="${userDetails.fullname }"
						style="cursor:pointer;">
						${userDetails.fullname } </span>
						<input id="user_id" value="${userDetails.username }" type="hidden"/>
						<div id="editUserInfo_pannel">
						</div>
				</div>
				<div id="user_location" class="nav_right">您的位置：首页</div>
			</div>
		</div>
		<div data-options="region:'west',border:false" id="content_left_parent"
			style="width:189px;border-right:1px solid #CCC;">
			<div id="content_left" class="content_left" style="background: #f2f2f2;width:189px;"></div>
		</div>
		<div data-options="region:'center',border:false">
			<div class="content_right">
				<img src="${ctxPath }/images/common_page/cc.gif" width="7"
					height="47" id="closeopen_menu_btn" />
			</div>
			<div id="center_page" class="easyui-panel" style="padding:16px 16px 16px 23px"
				data-options="border : false,fit : true">
				<sitemesh:write property='body' />
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var changePwd = '${userDetails.changePwd}';
	$(function(){
		$("#content_left_parent").css("width","189px");
	});
	var userDetails  = '${userDetails}';
</script>