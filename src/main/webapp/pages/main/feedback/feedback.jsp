<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/feedback/feedback_jquery.js"></script>


<%--页面组件部分--%>

<div id="tgySteward_search_from" class="query_condition_body">
	<!-- <input type="hidden" name="typeID" value="20"> -->
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20%"><label>反馈内容：</label><input placeholder="输入反馈内容" name="content" type="text" class="condition" /></td>

			<td width="20%" align="center"><input name="" type="submit" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
			</td>
		</tr>
	</table>
</div>
<div id="resultList"></div>
<div class="display_div"></div>
