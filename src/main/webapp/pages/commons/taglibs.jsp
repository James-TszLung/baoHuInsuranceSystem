<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=UTF-8" isELIgnored="false" buffer="24kb"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%-- 标签引入 --%>
<%@	taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/commons" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/order" prefix="order"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html PUBLIC "">
<%-- 全局变量设置 --%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="basePath" value="<%=basePath %>" scope="request" />
<%-- 脚本全局变量设置 --%>
<script type="text/javascript">
	var ctxPath = "${ctxPath}";
	if (ctxPath.indexOf("baoHuInsuranceSystem") == -1) {
		ctxPath = "baoHuInsuranceSystem" + ctxPath;
	}
	var basePath = "${basePath}";
	var dateInterval = parseInt("${dateInterval}"); //间隔数
	var dateIntervalUnit = parseInt("${dateIntervalUnit}");//单位：月/天
</script>


