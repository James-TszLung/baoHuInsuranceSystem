<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO"%>

<%@ attribute name="order"
	type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true"
	description="订单"%>

<c:if test="${not empty order}">
	<c:if test="${order.alertTimes eq 11}">
		<c:if test="${order.bookStatus eq 0}">已取消(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 5}">已拒绝(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 10}">申请退废(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 20}">退废待处理(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 25}">退废办理中(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 28}">退废失败(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 30}">退废成功(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 40}">已配送(非自愿)</c:if>
		<c:if test="${order.bookStatus eq 50}">已退款(非自愿)</c:if>
	</c:if>
	<c:if test="${order.alertTimes eq 22}">
		<c:if test="${order.bookStatus eq 0}">已取消(自愿)</c:if>
		<c:if test="${order.bookStatus eq 5}">已拒绝(自愿)</c:if>
		<c:if test="${order.bookStatus eq 10}">申请退废(自愿)</c:if>
		<c:if test="${order.bookStatus eq 20}">退废待处理(自愿)</c:if>
		<c:if test="${order.bookStatus eq 25}">退废办理中(自愿)</c:if>
		<c:if test="${order.bookStatus eq 28}">退废失败(自愿)</c:if>
		<c:if test="${order.bookStatus eq 30}">退废成功(自愿)</c:if>
		<c:if test="${order.bookStatus eq 40}">已配送(自愿)</c:if>
		<c:if test="${order.bookStatus eq 50}">已退款(自愿)</c:if>
	</c:if>
	<c:if test="${order.alertStatus eq 2}">
		<c:if test="${order.bookStatus eq 0}">已取消</c:if>
		<c:if test="${order.bookStatus eq 5}">已拒绝</c:if>
		<c:if test="${order.bookStatus eq 10}">申请退废</c:if>
		<c:if test="${order.bookStatus eq 20}">退废待处理</c:if>
		<c:if test="${order.bookStatus eq 25}">退废办理中</c:if>
		<c:if test="${order.bookStatus eq 28}">退废失败</c:if>
		<c:if test="${order.bookStatus eq 30}">退废成功</c:if>
		<c:if test="${order.bookStatus eq 40}">已配送</c:if>
		<c:if test="${order.bookStatus eq 50}">已退款</c:if>
	</c:if>
</c:if>