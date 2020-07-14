<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultList" action="${ctxPath}/role/searchRole.htm"
	method="post" autocomplete="off">
	<table id="result_grid">
	</table>
	<common:pageonly sc="${sc}" />
</form>
<script>
function fnOnGoToPage(p){
	doSearch(false);
} 

//初始化数据表格
autoResizeDG($('#result_grid').datagrid({
	type: "POST",
	data:${data},
	fitColumns:true,
	singleSelect:false,
	autoRowHeight:false,
	striped:false,
	pageSize:10,
	nowrap:false,
	
	columns:[[
        {field:'roleName',width:'15%',title:'角色'},
        {field:'remark',width:'35%',title:'描述'},
        {field:'createTime',width:'15%',title:'创建时间',
        	formatter:function(val, row) {
						if (val != null) {
							var date = new Date(val);
							return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
							+ date.getDate();
							}
					}
        },
        {field:'createName',width:'16%',title:'创建人'},
		{field:'opt',title:'操作',width:'20%',align:'center',
			formatter:function(value,rec){
				var rsStr = '&nbsp;<a style="color:#2A9CD5" onclick="viewEditingData(\''+rec.roleId+'\')">修改</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="changeRoles(\''+rec.roleId+'\')">分配权限</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="deleteRole(\''+rec.roleName+'\',\''+rec.roleId+'\')">删除</a>';
				return rsStr;
			}
		}
	]]
}));
</script>