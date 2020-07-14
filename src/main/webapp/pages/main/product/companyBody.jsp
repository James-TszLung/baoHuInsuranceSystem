<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultCompanyList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="result_grid" height="400px;">
	</table>
	<common:pageonly sc="${companySC}" />
</form>
<script>
	function fnOnGoToPage(p){
		doSearchCompany(false);
	}
	var levelCN = {1:'一级',2:'二级',3:'三级'};
	//初始化数据表格
	autoResizeDG($('#result_grid').datagrid({
		type: "POST",
		data:${companyData},
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
			{field:'companyName',width:'50%',title:'公司名称'},
			{field:'phone',width:'50%',title:'客服电话'}
		]]
	}));
</script>