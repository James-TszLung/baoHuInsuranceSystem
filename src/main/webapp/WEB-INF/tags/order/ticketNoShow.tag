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
<table class="airlinedetail" border="0">
<c:forEach var="airBookLines" items="${airBook.airBookLines}">
    	<c:forEach var="airBookDetails" items="${airBookLines.airBookDetails}" varStatus="status">
				<tr>
					<td style="height: initial;">${airBookDetails.airCode }${airBookDetails.ticketID }</td>
				</tr>
			</c:forEach>
</c:forEach>
</table>
</c:if>