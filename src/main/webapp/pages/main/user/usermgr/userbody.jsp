<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp" %>
<form id="resultList" action="${ctxPath}/usermanage/searchUser.htm" method="post" autocomplete="off">
<table id="result_grid">
</table>
<common:pageonly sc="${sc}"/>
</form>
<script>
function fnOnGoToPage(p){
	doSearch(false);
}
autoResizeDG($('#result_grid').datagrid({
	type: "POST",
	data:${data},
	fitColumns:true,
	singleSelect:false,
	autoRowHeight:false,
	striped:false,
	pageSize:10,
	onClickRow: function (rowIndex, rowData) {
	    $(this).datagrid('unselectRow', rowIndex);
	},
	nowrap:false,
	columns:[[
		        {field:'account',width:'8%',title:'账号'},
		        {field:'role',width:'8%',title:'角色',
		        	formatter:function(value,rec){
		        		if(value!=null){
		        			return value.roleName;
		        		}else{
		        			return "";
		        		}
		        	}
		        },
		        {field:'position',width:'20%',title:'职能描述'},
		        {field:'status',width:'6%',title:'状态',
		        	formatter:function(value,rec){
		        		if(value==1){
		        			return "正常";
		        		}else{
		        			return "禁用";
		        		}
		        	}
		        },
		        {field:'a',width:'12%',title:'最近登录时间',
        			formatter:function(val, row) {
						if (val != null) {
							var date = new Date(val);
							return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
							+ date.getDate();
							}
					}
		        },
		        {field:'b',width:'12%',title:'最近登录地址'},
		        {field:'modifyPwd',width:'8%',title:'修改密码次数'},
		        {field:'createTime',width:'12%',title:'创建时间',
		        	formatter:function(val, row) {
						if (val != null) {
							var date = new Date(val);
							return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
							+ date.getDate();
							}
					}
		        },
				{field:'opt',title:'操作',width:'15%',align:'center',
					formatter:function(value,rec){
						if(rec.status==1){
							var rsStr = '&nbsp;<a style="color:#2A9CD5" onclick="viewEditingData(\''+rec.id+'\')">修改</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="updatePwdWin(\''+rec.id+'\')">更改密码</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="setUser(\''+rec.id+'\',\''+rec.account+'\',\''+2+'\')">禁用</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="deleteUser(\''+rec.id+'\',\''+rec.account+'\')">删除</a>';
						}else {
							var rsStr = '&nbsp;<a style="color:#2A9CD5" onclick="viewEditingData(\''+rec.id+'\')">修改</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="updatePwdWin(\''+rec.id+'\')">更改密码&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="setUser(\''+rec.id+'\',\''+rec.account+'\',\''+1+'\')">启用</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="deleteUser(\''+rec.id+'\',\''+rec.account+'\')">删除</a>';
						}
						return rsStr;
					}
				}
			]]
}));


</script>