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
<table width="100%" class="orderDetailTag">
<c:set var="lcount" value="0"/>
<c:forEach var="airBookLine" items="${airBook.airBookLines}">
<tr <c:if test="${lcount > 0}">class="tbairline"</c:if>>
	<td width="30%">${airBookLine.departure}-${airBookLine.arrival}&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${airBookLine.airDate}" /></td>
	<td width="70%">
		<table width="100%" class="tbairdetail">
			<c:forEach var="airBookDetail" items="${airBookLine.airBookDetails}"> 
			<tr>
				<td width="12%"><div title="${airBookDetail.passengerName}" class="text-overflow">${airBookDetail.passengerName}</div></td>
				<td width="17%">${airBookDetail.outPrice}</td>
				<td width="17%">${airBookDetail.agtPrice}</td>
				<td width="8%">${airBookDetail.taxFee}</td>
				<td width="8%">${airBookDetail.yqFee}</td>
				<td width="17%">${airBookDetail.otherPrice}</td>
				<td>${airBookDetail.recvPrice}<c:if test="${empty airBookDetail.recvPrice}">0</c:if></td>
	    	</tr>
	    	<c:set var="lcount" value="${lcount + 1}"/>
	    	</c:forEach>			
		</table>
	</td> 
</tr>
</c:forEach>
</table>
</c:if>