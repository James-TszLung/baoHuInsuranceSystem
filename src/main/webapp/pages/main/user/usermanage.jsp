<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="styles/unique/user/usermanage.css"
	type="text/css"></link>
<script type="text/javascript"
	src="scripts/unique/user/usermanage_jquery.js"></script>
<%--页面组件部分--%>
<h3 id="page_loc_title"></h3>
<div id="group_search_from" class="condition_search">
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left">登录名：<input name="loginName" type="text"
				class="form_one" /></td>
			<td align="left">用户姓名：<input name="lastName" style="width:60px;"
				type="text" class="form_one" />&nbsp;<input name="firstName"
				type="text" class="form_one" style="width:120px;" /></td>
		</tr>
		<tr>
			<td align="left">性&nbsp;&nbsp;别：<select name="sex"
				class="form_one easyui-combobox" data-options="editable:false">
					<option value="M">男</option>
					<option value="F">女</option>
			</select>
			</td>
			<td align="left">所在中心：<input name="corpNum" type="text"
				class="form_one" />
			</td>
			<td align="left"><input name="" type="submit"
				class="searchbutton" value="查询" onclick="javascript:queryByPage();" />
				<input name="" type="submit" class="icon" value="新增"
				onclick="javascript:addUser();" /></td>
		</tr>
	</table>
</div>
<div class="btn_tool_bar">
	<input type="submit" class="all_disable" value=""
		onClick="selectAll();">
	<input type="submit" class="all_disable_s02" value=""
		onClick="editStatusBySelect();">
	<input type="submit" class="all_disable_s03" value=""
		onClick="editUser();">
</div>
<table id="result_grid">
</table>