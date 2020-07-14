<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO" %>
<%@ tag import="com.xiaobaozi.dataSystem.commons.Constants" %>

<%@ attribute name="airBook" type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true" description="订单"%>
<%
	StringBuffer orderSource = new StringBuffer();
	if(null!=airBook && null!=airBook.getTicketStatus()){
		switch(airBook.getTicketStatus()){
			case 0:orderSource.append("未出票");break;
			case 1:orderSource.append("已出票");break;
			case 2:orderSource.append("已导单");break;
			case 3:orderSource.append("出票中");break;
			case 4:orderSource.append("出票失败");break;
			default:orderSource.append("未知状态");break;
		}		
	}
	orderSource.append("-");
	if(null!=airBook && null!=airBook.getPayStatus()){
		switch(airBook.getPayStatus()){
			case 0:orderSource.append("未支付");break;
			case 1:
			if(airBook.getAgtTotal().add(airBook.getOtherTotal()).compareTo(airBook.getRecvTotal())==0){
				orderSource.append("支付中");break;
			}else{
				orderSource.append("欠款");break;
			}			
			case 2:orderSource.append("已支付");break;
			case 3:orderSource.append("支付失败");break;
			default:orderSource.append("未知状态");break;
		}		
	}
	out.print(orderSource);
%>