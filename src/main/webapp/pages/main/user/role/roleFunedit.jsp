<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/unique/user/role.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/role/rolefunedit_jquery.js"></script>
<%--页面组件部分--%>
<div id="addDiv">
	<div class="query_condition_title">
		<p id="page_loc_title"></p>
	</div>
	<div class="cfgtail">
		<h3>**友情提示**：</h3>
		<ul>
			<li>绑定/解绑权限成功之后，按F5刷新即可看到效果。</li>
		</ul>
	</div>
	<input id="roleId" name="roleId" value="${roleId}" type="hidden">
	<ul id="ttt"></ul>
	<table style="width: 100%;">
		<tr>
			<td>
				<div class="ui_u_qux">
					<div class="ui_u_qux_title">
						<label><input type="button" class="airlines_button"
							value="全选/反选" onclick="selectAllTrees()" /> </label>
					</div>
					<ul id="treediv">

					</ul>
				</div>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;"><input type="button"
				class="query" value="确定" id="bindFunc" onClick="saveChange();" /> <input
				type="button" class="query" value="取消" onClick="closeAddUserWin();" />
			</td>
		</tr>
	</table>
</div>