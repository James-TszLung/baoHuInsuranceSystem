<%@page import="java.net.URLDecoder"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 	
	Object reqMsgObj = request.getAttribute("msg");
	String reqMsg = reqMsgObj!=null?(String)reqMsgObj:null;
	Cookie cookies[]=request.getCookies();
	String msg = null;
	for(int i=0; i<cookies.length; i++){
		Cookie c= cookies[i];
		if(c.getName().equals("msg")){
			msg = URLDecoder.decode(c.getValue(),"utf-8");
		}
	} 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=StringUtils.isNotBlank(msg)?msg:(StringUtils.isNotBlank(reqMsg)?reqMsg:"您还未登录") %>-等待跳转</title>
</head>
<body>
	<div class="timeout_content">
		<h1>
			<%
				if(StringUtils.isNotBlank(msg)) {
			%>
					<font color="red"><%=msg %></font>
			<%	} else if(StringUtils.isNotBlank(reqMsg)) { %>
					<font color="red"><%=reqMsg %></font>
			<%	} else{ %>
					您还未登录
			<%	}
			%>
		</h1>
		<%--	<h4>注：长时间不进行任何操作也会导致自动退出</h4>--%>
		<p>
			将在<span id="jumpTo">5</span>秒后为您自动跳转至登录界面<a
				href="<%=request.getContextPath() %>/auth/login.htm">点击立即跳转</a>
		</p>
	</div>
	<script type="text/javascript">
		function countDown(secs, surl) {
			$("#jumpTo").html(secs);
			if (--secs >= 0) {
				setTimeout("countDown(" + secs + ",'" + surl + "')", 1000);
			} else {
				location.href = surl;
			}
		}
		countDown(5, ctxPath + '/auth/login.htm');
		function initPage() {
			countDown(5, ctxPath + '/auth/login.htm');
		}
	</script>
</body>
</html>