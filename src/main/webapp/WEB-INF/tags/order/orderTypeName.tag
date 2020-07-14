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
	StringBuffer interType = new StringBuffer();
	if(null!=airBook){
		switch(airBook.getAlertStatus()){
			case 0:interType.append("国内");break;
			case 1:interType.append("国际");break;
			default:break;
		}
		switch(airBook.getIsTeam()){
			case 0:interType.append("普通");break;
			case 1:interType.append("特价");break;
			case 2:interType.append("团队");break;
			default:break;
		}
		
	}
	out.print(interType);
%>