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
			{field:'content',width:'30%',title:'浏览内容'},
			{field:'typeDesc',width:'15%',title:'所属类别'},
			{field:'channelDesc',width:'10%',title:'渠道'},
			{field:'userName',width:'15%',title:'用户账号',formatter:function(value,rec) {
					var userName = "/";
					if (rec.registerUser != null) {
						userName = rec.registerUser.name;
					}
					return userName;
				}
			},
			{field:'createTime',width:'20%',title:'浏览时间'},
		]]
	}));
</script>