<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:authentication property="principal" var="userDetails" />
<%--css/js文件导入--%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/unique/user/role.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/role/role_jquery.js"></script>
<%--页面组件部分--%>
<div id="role_search_from" class="query_condition_body">
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="5%"><label>角色：</label><input placeholder="输入角色关键字" name="roleName"
				type="text" class="condition" /> <input name="" type="submit"
				class="airlines_button" value="搜索"
				onclick="javascript:doSearch(true);" />
			</td>
			<%--<td width="40%"><label>适用对象：</label><select name="rolePale"
				class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
			</select>
			</td>
			--%>
		</tr>
	</table>
</div>
<div class="btn_tool_bar">
	<input id="" name="" type="button" class="btn" value="添加角色"
		onclick="addView()" style="width:85px" />
</div>
<div id="results"></div>
<div class="display_div">
	<div id="add_role_win" class="easyui-window"
		style="width:330px;height:260px;padding: 15px;"
		data-options="title: '新增角色',
		inline: true,
		modal : true,
		closed : true">
		<div>
			<form id="add_role_form" method="post">
				<input type="hidden" name="roleID" id="roleID">
				<table>
					<tr>
						<td class="form_label">角色名称：</td>
						<td><input type="text" name="roleName" class="easyui-textbox"
							style="width:180px;height:28px;"
							data-options="required:true,validType:'length[1,30]',missingMessage:'该输入项为必输项'" />
						</td>
					</tr>
					<input type="hidden" name="corpNum" value="ESL003" />
					<tr>
						<td class="form_label">状态：</td>
						<td><select name="status" class="form_one easyui-combobox"
							style="width:180px;height:28px;"
							data-options="editable:false,panelHeight:'auto'">
								<option value="Y">启用</option>
								<option value="N">停用</option>
						</select>
						</td>
					</tr>
					<tr>
						<td class="form_label">说明：</td>
						<td><input type="text" name="remark" value=""
							style="width:180px;height:28px;" class="easyui-textbox" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center;padding-top:8px;">
			<input type="button" class="query" value="保存"
				onclick="javascript:submitAddForm();" /><input type="button"
				class="query" value="返回" onclick="javascript:closeAddUserWin();" />
		</div>
	</div>
</div>
