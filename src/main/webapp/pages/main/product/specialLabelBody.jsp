<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultSpecialLabelList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="specialLabel_result_grid" style="max-height: 400px;">
	</table>
	<common:pageonly sc="${specialLabelSC}" />
</form>
<script>
	function fnOnGoToPage(p){
		doSearchSpecialLabel(false);
	}
	var IsCheckFlag = true;
	//初始化数据表格
	autoResizeDG($('#specialLabel_result_grid').datagrid({
		type: "POST",
		data:${specialLabelData},
		fitColumns:true,
		singleSelect:false,
		autoRowHeight:false,
		pageSize:10,
		rowStyler:function(index,row){
			if (row.level==1){
				return 'background-color:#CFF2FF;';
			}else if (row.level==2){
				return 'background-color:#D9FFEE;';
			}
		},onClickCell: function (rowIndex, field, value) {
			IsCheckFlag = false;
		},
		onSelect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#specialLabel_result_grid").datagrid("unselectRow", rowIndex);
			}
		},
		onUnselect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#specialLabel_result_grid").datagrid("selectRow", rowIndex);
			}
		},
		columns:[[
			{
				field : 'id',
				checkbox : true
			},
			{field:'content',width:'100%',title:'标签'}
		]]
	}));
</script>