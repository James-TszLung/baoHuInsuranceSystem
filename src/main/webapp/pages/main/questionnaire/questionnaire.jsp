<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/questionnaire/questionnaire_jquery.js"></script>


<%--页面组件部分--%>
<div id="tgySteward_search_from" class="query_condition_body">
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20%"><label>姓名：</label><input  name="name" type="text" class="condition" /></td>
			<td width="20%"><label>联系电话：</label><input  name="phone" type="text" class="condition" /></td>
			<td width="20%">
				<label>状态：</label>
				<select name="status" class="validatebox-text">
					<option value="">全部</option>
					<option value="1">待付款</option>
					<option value="2">已付款</option>
				</select>
			</td>
			<td width="30%"><label>提交时间：&nbsp;</label> <input
					name="startTime" placeholder="输入开始时间" id="startTime" type="text"
					class="Wdate" onclick="WdatePicker()" value="" style="width:105px;" />
				- <input name="endTime" placeholder="输入结束时间" id="endTime"
						 type="text" class="Wdate" onclick="WdatePicker()" value=""
						 style="width:105px;" />
			</td>

			<td width="20%" align="center"><input name="" type="submit" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
			</td>
		</tr>
	</table>
</div>
<div id="resultList"></div>
<div class="display_div"></div>
