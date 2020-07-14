<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp" %>
<link rel="stylesheet" href="${ctxPath}/styles/commons/contentStyle.css" type="text/css"></link>
<div class="main_content">
	<div class="orders_tile">
       <p class="orders_tile_s01">
       <span>异常管理</span>
       </p>
    </div>
	<div id="condition_search" class="query_condition_body">
		<table width="96%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="40%">开始时间：<input id="start" type="text" name="startTime"/></td>
				<td width="40%">结束时间：<input id="end"  type="text" name="endTime"/></td>
				<td width="20%" align="center"><input name="" type="button" onclick="doSearch(true)" class="airlines_button" value="查询"/></td>
			</tr>
		</table>
	</div>
	<div class="btn_tool_bar">
		<input type="button" class="btn" value="全选" onclick="selectAll();">
		<input type="button" class="btn" value="删除" onclick="mulitiDelete();">
	</div>
	<div id="results"></div>
	<div id="dd"></div>
</div>
<script type="text/javascript" src="${ctxPath}/scripts/commons/excption_jquery.js"></script>
