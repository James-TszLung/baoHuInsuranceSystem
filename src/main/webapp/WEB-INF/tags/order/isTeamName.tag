<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO" %>

<%@ attribute name="airBook" type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true" description="订单"%>
<%
	String isTeam = "";
	if(null!=airBook){
		switch(airBook.getIsTeam()){
			case 0:isTeam="普通";break;
			case 1:isTeam="特价";break;
			case 2:isTeam="团队";break;
			default:break;
		}
	}
	out.print(isTeam);
%>