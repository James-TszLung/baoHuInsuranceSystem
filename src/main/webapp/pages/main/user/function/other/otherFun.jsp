<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/unique/user/function.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/function/otherFun_jquery.js"></script>
<%--页面组件部分--%>
<div class="query_condition_title">
	<p id="page_loc_title"></p>
</div>
<div id="function_search_from" class="query_condition_body">
	<input type="hidden" name="typeID" value="1">
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center"><label>模块名：</label><input name="funName"
				type="text" class="form_one" />
			</td>
			<td align="center"><label>父级模块：</label><input name="parentMenu"
				type="text" class="form_one" />
			</td>
			<td align="center"><input name="" type="submit"
				class="airlines_button" value="查询"
				onclick="javascript:doSearch(true);" />
			</td>
		</tr>
	</table>
</div>
<div class="btn_tool_bar">
	<input name="" type="button" class="btn" value="添加"
		onclick="javascript:openAddUserWin();">
</div>
<div id="results"></div>
<div id="add_function_win" class="easyui-window" style="width:300px;"
	data-options="title: '新增请求权限',
		inline: true,
		modal : true,
		closed : true">
	<div>
		<form id="add_function_form" method="post">
			<input type="hidden" name="functionID"> <input type="hidden"
				name="typeID" value="1">
			<table>
				<tr>
					<td class="form_label">请求名称：</td>
					<td><input type="text" name="funName" class="easyui-textbox"
						style="width:180px;height:28px;"
						data-options="required:true,validType:'length[1,32]',missingMessage:'该输入项为必输项'" />
					</td>
				</tr>
				<tr>
					<td class="form_label">请求路径：</td>
					<td><input type="text" name="funFile" class="easyui-textbox"
						style="width:180px;height:28px;"
						data-options="validType:'length[0,128]'" />
					</td>
				</tr>
				<tr>
					<td class="form_label">请求排序：</td>
					<td><input type="text" name="order" class="easyui-textbox"
						value="5" style="width:180px;height:28px;"
						data-options="validType:'number'" />
					</td>
				</tr>
				<tr>
					<td class="form_label">父级模块：</td>
					<td><input type="text" name="parentMenu"
						style="width:180px;height:28px;" class="text" />
					</td>
				</tr>
				<tr>
					<td class="form_label">当前级别：</td>
					<td><input type="text" name="level" id="level_by_parent"
						class="easyui-textbox" data-options="editable:false"
						style="width:180px;height:28px;" />
					</td>
				</tr>
				<%--<tr>
						<td class="form_label">适用对象：</td>
						<td><input type="text" name="funPale"
				data-options="required:true,editable:false" style="width:180px;height:50px;"/>
						</td>
					</tr>
				--%>
			</table>
		</form>
	</div>
	<div style="text-align: right;">
		<input type="button" class="query" value="保存"
			onclick="javascript:submitAddForm();" /><input type="button"
			class="query" value="返回" onclick="javascript:closeAddUserWin();" />
	</div>
</div>
