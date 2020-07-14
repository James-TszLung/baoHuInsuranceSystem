<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="result_grid">
	</table>
	<c:if test="${sc.totalCount==0}">
		<br>
		<div style="text-align:center;">
			<span>抱歉，暂无数据!</span>
		</div>
	</c:if>
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
			{field:'title',width:'30%',title:'方案标题'},
			{field:'name',width:'15%',title:'客户姓名'},
			{field:'phone',width:'10%',title:'联系电话'},
			{field:'userCount',width:'10%',title:'定制对象人数'},
			{field:'createTime',width:'10%',title:'定制时间'},
			{field:'opt',title:'操作',width:'25%',align:'center',
				formatter:function(value,rec){
					var reStr = '<a style="color:#2A9CD5" onclick="editCustomizePlan(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>';
					reStr += '<a style="color:#2A9CD5;display: none;" onclick="deleteCustomizePlan(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>';
					return reStr;
				}
			}
		]]
	}));
</script>