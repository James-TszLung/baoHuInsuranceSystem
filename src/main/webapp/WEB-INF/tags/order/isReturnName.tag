<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookLineVO" %>

<%@ attribute name="airBookLine" type="com.xiaobaozi.dataSystem.order.vo.AirBookLineVO" required="true" description="订单"%>

<c:if test="${not empty airBookLine}">
	<c:choose>
		<c:when test="${airBookLine.isReturn eq 1}">
			去程
		</c:when>
		<c:when test="${airBookLine.isReturn eq 2}">
			回程
		</c:when>
		<c:otherwise></c:otherwise>
	</c:choose>
</c:if>