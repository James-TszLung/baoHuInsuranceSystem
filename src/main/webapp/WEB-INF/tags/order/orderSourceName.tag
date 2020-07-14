<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="java.util.List" %>
<%@ tag import="com.hanwei.mingkai.order.vo.AirBookVO" %>
<%@ tag import="com.hanwei.mingkai.baseinfo.vo.Dictionary" %>
<%@ tag import="com.hanwei.commons.Constants" %>


<%@ attribute name="airBook" type="com.hanwei.mingkai.order.vo.AirBookVO" required="true" description="订单"%>
<%
	StringBuffer orderSource = new StringBuffer();
	if(null!=airBook && null!=airBook.getResFrom()){
		List<Object> objlist = Constants.cacheListByMap.get("com.hanwei.mingkai.baseinfo.vo.Dictionary");
		if(null!= objlist && objlist.size()>0){
			for(int i=0; i<objlist.size();i++){
				Dictionary dict = (Dictionary)objlist.get(i);
				if(dict.getCorpNum().equals("100001")&&dict.getDictGroup().equals(3)&& dict.getDictId().equals(airBook.getResFrom())){
					orderSource.append(dict.getDictName());
					break;
				}
			}
		}
	}
	out.print(orderSource);
%>