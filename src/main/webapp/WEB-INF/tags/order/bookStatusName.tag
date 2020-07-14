<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.hanwei.mingkai.order.vo.AirBookVO" %>

<%@ attribute name="order" type="com.hanwei.mingkai.order.vo.AirBookVO" required="true" description="订单"%>

<c:if test="${not empty order}">
	<c:choose>
		<c:when test="${order.bookStatus eq 1}">
			已取消
		</c:when>
		<c:when test="${order.bookStatus eq 2}">
			已拒绝
		</c:when>
		<c:when test="${order.bookStatus eq 10}">
			新订单
		</c:when>
		<c:when test="${order.bookStatus eq 20}">
			已出票
		</c:when>
		<c:when test="${order.bookStatus eq 30}">
			已配送
		</c:when>
		<c:otherwise>未知状态</c:otherwise>
	</c:choose>
</c:if>