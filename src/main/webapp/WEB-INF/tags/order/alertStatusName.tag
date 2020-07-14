<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO" %>

<%@ attribute name="order" type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true" description="订单"%>

<c:if test="${not empty order}">
	<c:choose>
		<c:when test="${order.alertStatus eq 0}">
			正常单
		</c:when>
		<c:when test="${order.alertStatus eq 1}">
			退票单
		</c:when>
		<c:when test="${order.alertStatus eq 2}">
			废票单
		</c:when>
		<c:when test="${order.alertStatus eq 3}">
			变更单
		</c:when>
		<c:when test="${order.alertStatus eq 4}">
			调账单
		</c:when>
		<c:when test="${order.alertStatus eq 5}">
			单独配送单
		</c:when>
		<c:otherwise>未知状态</c:otherwise>
	</c:choose>
</c:if>