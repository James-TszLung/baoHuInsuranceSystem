<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/otherCss/style.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/style.css" type="text/css"></link>
<style>
	.add-basecls .search form input[type=button] {
		margin-top: 0px;
	}
</style>
<form id="resultMealList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="single_grid" style="width:800px;height:500px">

	</table>
	<c:if test="${sc.totalCount==0}">
		<br>
		<div style="text-align:center;">
			<span>抱歉，暂无单品服务!</span>
		</div>
	</c:if>
	<common:pageonly sc="${sc}" />
</form>
<script type="text/javascript">
var propertyDate='${data}';
function fnOnGoToPage(p){
	doSearchSinglePro(false);
}
var IsCheckFlag = true;
var showField = '${showLabel}';
//初始化数据表格
autoResizeDG($('#single_grid').datagrid({
	type: "POST",
	data:${data}, 
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
             $("#single_grid").datagrid("unselectRow", rowIndex);
         }
     },                    
     onUnselect: function (rowIndex, rowData) {
         if (!IsCheckFlag) {
             IsCheckFlag = true;
             $("#single_grid").datagrid("selectRow", rowIndex);
         }
     },
 	 columns : [ [ {
		field : 'id',
        checkbox : true
	}, {
		field : 'name',
		width : '60%',
		title : '产品名称'
	}, {field:'dictionaryContent',width:'25%',title:'${showLabel==1 ? "承保公司" : '所属分类'}',
	    	 formatter:function(value,rec){
		        if(showField==1){
					return rec.insuranceCompany.companyName;
				}
        		return rec.dictionaryContent.content;
        	} 
	 }] ]
}));
</script>