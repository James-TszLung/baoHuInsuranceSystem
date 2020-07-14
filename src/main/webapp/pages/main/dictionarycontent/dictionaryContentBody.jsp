<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="result_grid" style="max-height: 400px; ">
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
	singleSelect:true,
	autoRowHeight:false,
	pageSize:10,
 	rowStyler:function(index,row){   
         if (row.level==1){   
             return 'background-color:#CFF2FF;';   
         }else if (row.level==2){
         	return 'background-color:#D9FFEE;';  
         } 
     },
	columns:[[
		{field:'content',width:'100%',title:'概述模板'}
	]]
}));
</script>