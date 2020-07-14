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
    <td>
    	<c:set var="passgernames" value=""/>
    	<c:set var="passgernameshtml" value=""/>
    	<c:forEach var="airBookDetail" items="${airBookLine.airBookDetails}">
    		<c:choose>
    			<c:when test="${airBookDetail.insureNum gt 0}">
    				<c:set var="passgernames" value="${airBookDetail.passengerName}+${airBookDetail.insureNum}"/>
    				<c:set var="passgernameshtml" value="${airBookDetail.passengerName}<span class='ins' title='${airBookDetail.insureNum}份保险'>+${airBookDetail.insureNum}</span>"/>
    				<c:if test="${airBookDetail.ticketStatus eq 1}">
    					<span class="" title="${passgernames}">${passgernameshtml}</span><img src="${ctxPath}/images/txt/waste.png" border="0" title="已申请退票"/></span>
    				</c:if>
    				<c:if test="${airBookDetail.ticketStatus eq 2}">
    					<span class="" title="${passgernames}">${passgernameshtml}</span><img src="${ctxPath}/images/txt/refund.png" border="0" title="已申请退废"/></span>
    				</c:if>
    				<c:if test="${airBookDetail.ticketStatus eq 4}">
    					<span class="" title="${passgernames}">${passgernameshtml}</span><img src="${ctxPath}/images/txt/change.png" border="0" title="改期"/></span>
    				</c:if>
    				<c:if test="${airBookDetail.ticketStatus != 1 and airBookDetail.ticketStatus != 2 and airBookDetail.ticketStatus != 4} ">
    					<span class="" title="${passgernames}">${passgernameshtml}</span>
    				</c:if>
    			</c:when>
    			<c:otherwise>
    				<c:set var="passgernames" value="${airBookDetail.passengerName}"/>
    				<c:set var="passgernameshtml" value="${airBookDetail.passengerName}"/>
    				<c:if test="${airBookDetail.ticketStatus eq 1}">
    					<span  title="${passgernames}">${passgernameshtml}</span><img src="${ctxPath}/images/txt/refund.png" border="0" title="已申请退票"/></span>
    				</c:if>
    				<c:if test="${airBookDetail.ticketStatus eq 2}">
    					<span  title="${passgernames}">${passgernameshtml}</span><img src="${ctxPath}/images/txt/waste.png" border="0" title="已申请退废"/></span>
    				</c:if>
    				<c:if test="${airBookDetail.ticketStatus eq 4}">
    					<span  title="${passgernames}">${passgernameshtml}</span><img src="${ctxPath}/images/txt/change.png" border="0" title="改期"/></span>
    				</c:if>
    				<c:if test="${airBookDetail.ticketStatus != 1 and airBookDetail.ticketStatus != 2 and airBookDetail.ticketStatus != 4}">
    					<span  title="${passgernames}">${passgernameshtml}</span>
    				</c:if>
    			</c:otherwise>    	
	    	</c:choose>	
	    </c:forEach>
    </td>
</tr>
</c:forEach>
</table>
</c:if>