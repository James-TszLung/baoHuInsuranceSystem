<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="com.xiaobaozi.dataSystem.order.vo.AirBookVO" %>
<%@ tag import="com.xiaobaozi.dataSystem.commons.Constants" %>
<%@ tag import="java.util.List" %>
<%@ tag import="com.xiaobaozi.dataSystem.baseinfo.vo.Dictionary" %>

<%@ attribute name="airBook" type="com.xiaobaozi.dataSystem.order.vo.AirBookVO" required="true" description="订单"%>
<%
	StringBuffer orderSource = new StringBuffer();
	if(null!=airBook && null!=airBook.getBookStatus()){
		List<Object> objlist = com.hanwei.commons.Constants.cacheListByMap.get("com.xiaobaozi.dataSystem.baseinfo.vo.Dictionary");
		if(null!= objlist && objlist.size()>0){
			for(int i=0; i<objlist.size();i++){	
				Dictionary dict = (Dictionary)objlist.get(i);
				if(dict.getCorpNum().equals("100001")&&dict.getDictGroup().equals(1)&& dict.getDictId().equals(airBook.getBookStatus())){
					if(com.xiaobaozi.dataSystem.commons.Constants.AIRBOOK_ALERTSTATUS_VOID.equals(airBook.getAlertStatus())||com.xiaobaozi.dataSystem.commons.Constants.AIRBOOK_ALERTSTATUS_RETURN.equals(airBook.getAlertStatus())){
						orderSource.append(dict.getOptionVal());
						if(11==airBook.getAlertTimes()){
							orderSource.append("(非自愿)");
						}
						if(22==airBook.getAlertTimes()){
							orderSource.append("(自愿)");
						}
					}else{
						orderSource.append(dict.getDictName());
						if(11==airBook.getAlertTimes()){
							orderSource.append("(非自愿)");
						}
						if(22==airBook.getAlertTimes()){
							orderSource.append("(自愿)");
						}
					}
					break;
				}
			}
		}	
	}
	out.print(orderSource);
%>