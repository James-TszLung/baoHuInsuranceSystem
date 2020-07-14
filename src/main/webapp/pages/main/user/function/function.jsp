<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/function/function_jquery.js"></script>
<%--页面组件部分--%>
<div id="function_search_from" class="query_condition_body">
	<input type="hidden" name="typeID" value="20">
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="40%"><label>菜单名称：</label><input placeholder="输入菜单名称关键字" name="funName" type="text"
				class="condition" />
			</td>
			<td width="20%" align="center"><input name="" type="submit"
				class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
			</td>
		</tr>
	</table>
</div>
<div class="btn_tool_bar" >
		<input id="" name="" type="button" class="btn" value="添加菜单"
			onclick="addView()" style="width:85px"/>
</div>
<div id="results"></div>
<div class="display_div">
<div id="add_function_win" class="easyui-window" 
	style="width:330px;height:330px;padding: 15px;"
	data-options="title: '新增模块',
		inline: true,
		modal : true,
		closed : true">
		<div>
			<form id="add_function_form" method="post">
				<input type="hidden" name="functionID">
				<table>
					<tr>
						<td class="form_label">模块名称：</td>
						<td>
						<input type="text" name="funName" class="easyui-textbox" 
						style="width:180px;height:28px;"
				data-options="required:true,validType:'length[1,32]',missingMessage:'该输入项为必输项'"/>
						</td>
					</tr>
					<tr>
						<td class="form_label">模块路径：</td>
						<td>
						<input type="text" name="funFile" class="easyui-textbox" 
						style="width:180px;height:28px;"
				data-options="validType:'length[0,128]'"/>
						</td>
					</tr>
					<tr>
						<td class="form_label">模块排序：</td>
						<td>
						<input type="text" name="order" class="easyui-textbox"  value="5"
						style="width:180px;height:28px;"
				data-options="validType:'number'"/>
						</td>
					</tr>
					<tr>
						<td class="form_label">父级模块：</td>
						<td><input type="text" name="parentMenuPage" id="parentMenuPage"
							style="width:180px;height:28px;" data-options="required:true"
							class="text"/>
						</td>
					</tr>
					<tr>
						<td class="form_label">当前级别：</td>
						<td><input type="text" name="level" id="level_by_parent"
							class="easyui-textbox" data-options="editable:false"
							style="width:180px;height:28px;"
							/>
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
</div></div>
