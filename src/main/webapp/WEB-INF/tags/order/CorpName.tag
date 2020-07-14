<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ tag import="org.apache.commons.lang.StringUtils" %>
<%@ tag import="com.xiaobaozi.dataSystem.baseinfo.vo.CorpInfo" %>
<%@ tag import="com.xiaobaozi.dataSystem.baseinfo.service.CorpInfoService" %>


<%@ attribute name="corpId" type="java.lang.String" required="true" description="corpId"%>
<%
	if(StringUtils.isNotEmpty(corpId)){
		org.springframework.context.ApplicationContext ctx = org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext(application);
		CorpInfoService corpInfoSerivce = (CorpInfoService)ctx.getBean("corpInfoSerivce");
		CorpInfo corpInfo = corpInfoSerivce.findById(corpId);
		if(null!=corpInfo && StringUtils.isNotEmpty(corpInfo.getCorpCname())){
			out.print(corpInfo.getCorpCname());
		}
	}
%>