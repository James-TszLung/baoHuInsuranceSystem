<%@ page import="com.xiaobaozi.ConfigUtil" %>
<c:set var="imagePath"  scope="session" ><%=ConfigUtil.getInstance().getImageUrl() %></c:set>
<c:set var="dateInterval"  scope="session" ><%=ConfigUtil.getInstance().getDefaultTimeInterval() %></c:set>
<c:set var="dateIntervalUnit"  scope="session" ><%=ConfigUtil.getInstance().getDefaultTimeIntervalUnit() %></c:set>
