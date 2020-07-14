<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="../commons/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理系统错误页面</title>
<link rel="stylesheet" type="text/css" href="../styles/showmessage.css">
</head>
<body>
	<div id="g_message_wrap">
		<dl>
			<dt>对不起，检测到操作不规范导致业务流程不能继续！</dt>
			<dd class="g_message_wrong">
				<p>
					原因：<br> ${msg}<br>
				</p>
				<div class="g_message_operation">
					<a href="javascript:history.go(-1);">[ 点这里返回上一页 ]</a><br><br>
					如果需要技术支持，<a href="mailto:">点击这里发送邮件</a>。
				</div>
			</dd>
		</dl>
	</div>
</body>
</html>