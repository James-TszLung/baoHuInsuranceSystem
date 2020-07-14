<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO"%>

<%@ attribute name="order"	type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true"	description="订单"%>

<c:if test="${not empty order}">
<c:if test="${order.emergency}"><img src="${ctxPath}/images/txt/icon_emergency.png" border="0" title="紧急订单"/></c:if>
<c:if test="${order.GP}"><img src="${ctxPath}/images/txt/icon_gp.png" border="0" title="GP订单"/></c:if>
<c:if test="${order.isLock==1}"> <img name="lockImg" src="${ctxPath}/images/txt/icon_locked.png" border="0" lockTime="${order.lockLong }" lockBy="${order.lockByName}"
                                   onmouseover="showLockInfo(this)" title=""/></c:if>
    <c:if test="${order.isLock==0}"> <img name="lockImg" src="${ctxPath}/images/txt/icon_locked.png" border="0" lockTime="${order.lockLong }" lockBy="${order.lockByName}"
                                          onmouseover="showLockInfo(this)" title="" style="display:none;"/></c:if>
<c:if test="${order.hanguped}"><img src="${ctxPath}/images/txt/icon_hanguped.png" border="0" title="已挂起"/></c:if>
<c:if test="${order.team}"><img src="${ctxPath}/images/txt/icon_team.png" border="0" title="团队订单"/></c:if>
<c:if test="${order.interfaceOrder}"><img src="${ctxPath}/images/txt/icon_interface.png" border="0" title="接口订单"/></c:if>
<c:if test="${order.autoOutTicket}"><img src="${ctxPath}/images/txt/icon_autoTicket.png" border="0" title="自动出票订单"/></c:if>
<c:if test="${order.hasinsure == 1}"><img src="${ctxPath}/images/no_txt/plus.png" border="0" title="查看关联订单"
	onclick="viewMoreRelatedOrders(event, ${order.resID})"/></c:if>
</c:if>