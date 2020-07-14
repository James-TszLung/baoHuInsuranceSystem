<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO" %>
<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookLineVO" %>
<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookDetailVO" %>

<%@ attribute name="airBook" type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true" description="订单"%>
<c:if test="${not empty airBook}">
<table class="airlinedetail">
<c:forEach var="airBookLine" items="${airBook.airBookLines}">
<tr>
	<td>${airBookLine.departure}-${airBookLine.arrival}&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${airBookLine.airDate}" /></td> 
</tr>
</c:forEach>
</table>
</c:if>