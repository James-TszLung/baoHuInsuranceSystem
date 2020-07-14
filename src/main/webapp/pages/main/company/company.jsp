<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/company/company_jquery.js"></script>


<%--页面组件部分--%>

<div id="tgySteward_search_from" class="query_condition_body">
	<!-- <input type="hidden" name="typeID" value="20"> -->
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20%"><label>公司名称：</label><input placeholder="输入公司名称" name="companyName" type="text" class="condition" /></td>
			

			<td width="20%" align="center"><input name="" type="submit" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
			</td>
		</tr>
	</table>
</div>
 <div class="btn_tool_bar" >
		<input id="" name="" type="button" class="btn" value="添加"
			onclick="addView()" style="width:75px"/>
</div>
<div id="resultList"></div>
<div class="display_div"></div>
