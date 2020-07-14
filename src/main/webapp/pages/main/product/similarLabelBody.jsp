<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultSimilarLabelList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="similarLabel_result_grid" style="max-height: 400px;">
	</table>
	<common:pageonly sc="${similarLabelSC}" />
</form>
<script>
	function fnOnGoToPage(p){
		doSearchSimilarLabel(false);
	}
	//初始化数据表格
	autoResizeDG($('#similarLabel_result_grid').datagrid({
		type: "POST",
		data:${similarLabelData},
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
			{field:'name',width:'100%',title:'同类产品标签'}
		]]
	}));
</script>