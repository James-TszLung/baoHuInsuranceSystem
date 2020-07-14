<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="../commons/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css" href="../styles/showmessage.css">
</head>
<body>
	<div id="g_message_wrap">
		<dl>
			<dt>对不起，您没有权限访问本系统！</dt>
			<dd class="g_message_wrong">
				<p>
					请检查：<br> 1、您的帐号是否同时在其它电脑上登录了本系统。<br>
					2、您的帐号是否在这台电脑的另外一个程序内登录了本系统。<br> 3、您的帐号是否正在被他人使用。<br>
				</p>
				<div class="g_message_operation">
					<a href="javascript:history.go(-1);">[ 点这里返回上一页 ]</a>
				</div>
			</dd>
		</dl>
	</div>
</body>
</html>