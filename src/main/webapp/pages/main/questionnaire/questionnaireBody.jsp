<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<form id="resultList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
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
			{field:'name',width:'18%',title:'姓名'},
			{field:'phone',width:'10%',title:'联系电话'},
			{field:'city1',width:'15%',title:'常驻地址'},
			{field:'residence',width:'15%',title:'户籍'},
			{field:'status',width:'10%',title:'状态',
				formatter:function(value,rec){
					if(value=='1'){
						return '待付款';
					}else if(value=='2'){
						return'已付款';
					}
				}
			},
			{field:'createTime',width:'12%',title:'提交时间'},
			{field:'opt',title:'操作',width:'20%',align:'center',
				formatter:function(value,rec){
					var reStr = '<a style="color:#2A9CD5" onclick="editShow(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>';
					reStr += '<a style="color:#2A9CD5" onclick="editPayment(\''+rec.id+'\')">&nbsp;&nbsp;收款码</a>';
					return reStr;
				}
			}
		]]
	}));
</script>