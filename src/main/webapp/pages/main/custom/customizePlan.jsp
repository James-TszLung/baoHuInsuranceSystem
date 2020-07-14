<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/custom/customizePlan_jquery.js"></script>
<%--页面组件部分--%>
<form id="customizePlanForm" method="post" onkeydown="if(event.keyCode==13){return false;}" >
	<div id="tgySteward_search_from" class="query_condition_body">
		<table width="98%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="22%"><label>方案标题：</label><input name="title" type="text" class="condition"/></td>
				<td width="22%"><label>客户姓名：</label><input name="name" type="text" class="condition"/></td>
				<td width="30%"><label>定制时间：&nbsp;</label> <input
						name="startTime" placeholder="输入开始时间" id="startTime" type="text"
						class="Wdate" onclick="WdatePicker()" value="" style="width:105px;" />
					- <input name="endTime" placeholder="输入结束时间" id="endTime"
							 type="text" class="Wdate" onclick="WdatePicker()" value=""
							 style="width:105px;" />
				</td>
				<td width="20%" align="center"><input type="button" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="btn_tool_bar" >
	<input  type="button" class="btn" value="添加" onclick="addView()" style="width:75px"/>
<%--	<input  type="button" class="btn" value="导出" onclick="exportCustomizePlan()" style="width:75px"/>--%>
</div>
<div id="productDown" style="display:none"></div>
<div id="resultList"></div>
<div class="display_div"></div>
