<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/usermanage.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/user/user/usermanage_jquery.js"></script>
<%--页面组件部分--%>
<form method="post" autocomplete="off" id="downForm">
	<div id="group_search_from" class="query_condition_body">
		<table width="98%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<label>账号：</label>
					<input placeholder="输入账号关键字" name="account" type="text" class="condition" />
				</td>
				<td>
					<label>角色：</label>
					<input placeholder="输入角色名称关键字" name="roleName" type="text" class="condition" />
				</td>
				<td width="25%">
					<label>状&nbsp;&nbsp;态：</label>
					<select style="width:252px;" name="status" id="status">
						<option value="">全部</option>
						<option value="1">正常</option>
						<option value="2">禁用</option>
					</select>
				</td>
				<td width="20%" align="center" rowspan="3">
					<input name="" type="button" class="airlines_button" value="查询" onclick="javascript:doSearch(true);" />
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="btn_tool_bar">
	<input name="" type="button" class="btn" value="添加账号" style="width:85px" onclick="javascript:addUser();">
</div>
<div id="results"></div>
<div id="updatePwd_win" class="easyui-window" style="width:300px;height:150px;"
	data-options="title: '修改密码',
		inline: true,
		modal: true,
		closed: true" style="dispaly:none">
	<form id="updatePwdForm">
		<input style="display:none" id="updatePwd_win_id"/>
		<table style="width:100%;height:100%;text-align: center;border-collapse:separate; border-spacing:13px 15px;"   >
			<tr>
				<td class="form_label">新密码：</td>
				<td>
					<input type="password" id="password" name="password" class="text" style="width:180px;height:28px;" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="button" class="city_airport_button_s01" value="保存" onclick="javascript:updatePwd();" />
				</td>
			</tr>
		</table>
	</form>
</div>
