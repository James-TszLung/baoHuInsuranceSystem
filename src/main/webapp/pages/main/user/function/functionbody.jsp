<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp" %>
<form id="resultList" action="${ctxPath}/function/searchFunction.htm" method="post" autocomplete="off">
<table id="result_grid">
</table>
<common:pageonly sc="${sc}"/>
</form>
<script>
function fnOnGoToPage(p){
	doSearch(false);
}

function getParentText(data,lv,p){
	var rst;
	$.each(data,function(){
		if(this.level == lv-1){
			var pid = this.id;
			if(pid == p)
				rst = this.text;
		}else if(this.level < lv-1){
			var r = getParentText(this.children,lv,p);
			if(typeof(r) != 'undefined')
				rst = r;
		}
	});
	return rst;
}


var levelCN = {1:'一级',2:'二级',3:'三级'};
//初始化数据表格
autoResizeDG($('#result_grid').datagrid({
	type: "POST",
	data:${data},
	fitColumns:true,
	singleSelect:true,
	autoRowHeight:false,
	pageSize:10,
	striped:false,
 	
	columns:[[
	       {field:'funName',width:'10%',title:'菜单名称'},
	       {field:'remark',width:'32%',title:'菜单描述'},
	       {field:'funUrl',width:'17%',title:'菜单链接'},
	       {field:'parentMenu',width:'15%',title:'上级菜单',
			formatter:function(value,rec){
				var ptext;
				if(rec.level > 1){
					ptext = getParentText(funTreeData,rec.level,value);
				}
				return ptext;
			}
		},
	       {field:'createTime',width:'15%',title:'创建时间',
	       		formatter:function(val, row) {
						if (val != null) {
							var date = new Date(val);
							return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
							+ date.getDate();
							}
					}
	       },
		{field:'opt',title:'操作',width:'10%',align:'center',
			formatter:function(value,rec){
				return '<a style="color:#2A9CD5" onclick="viewEditingData(\''+rec.id+'\')">编辑</a>&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="deleteFunction(\''+rec.funName+'\',\''+rec.id+'\')">删除</a>';
			}
		}
	]]
}));
</script>