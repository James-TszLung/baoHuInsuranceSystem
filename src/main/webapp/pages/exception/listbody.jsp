<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp" %>
<form id="exceptionList" action="${ctxPath}/exception/search.htm" method="post" autocomplete="off">
<table id="dg"></table>
<common:pageonly sc="${sc}"/>
<input type="hidden" name="startTime" value='<fmt:formatDate value="${sc.startTime}" pattern="yyyy-MM-dd"/>'/>
<input type="hidden" name="endTime" value='<fmt:formatDate value="${sc.endTime}" pattern="yyyy-MM-dd"/>'/>
</form>
<script>
function fnOnGoToPage(p){
	doSearch(false);
}

$('#dg').datagrid({
 	view:detailview,
    data:${data},
	autoRowHeight:true,
	striped:true,
	nowrap:false,
	fitColumns:true,
    columns:[[  
    	{field:'ck',checkbox:true},
    	{title:'异常ID',field:'id',width:'10%'},  	
	    {title:'异常类型',field:'type',width:'54%'},
        {field:'createTime',title:'异常时间',width:'15%'},
        {field:'opt',title:'操作',width:'10%',align:'center',
			formatter:function(value,rec){
				return '&nbsp;<a style="color:#2A9CD5" onclick="showdetail(this)">查看</a>';
			}
		}
    ]],
    detailFormatter: function(rowIndex, rowData){
    		return '<div class="ddv" style="padding:5px 0"></div>';
    },
    onExpandRow: function(index,row){
		var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
		ddv.panel({
			width:'98%',
			border:false,
			cache:false,
			content:'<b class="red">异常描述:</b> ' + row.msg,
			onLoad:function(){
				$('#dg').datagrid('fixDetailRowHeight',index);
			}
		});
		$('#dg').datagrid('fixDetailRowHeight',index);
	}
});
</script>