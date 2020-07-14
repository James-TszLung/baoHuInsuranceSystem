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
			{field:'sort',width:'10%',title:'排序'},
			{field:'content',width:'25%',title:'保障类型'},
			{field:'name',width:'25%',title:'产品类型',formatter:function(value,rec){
					if(rec.dictionaryContent!=null){
						return rec.dictionaryContent.content;
					}else{
						return '';
					}
				}
			},
			{field:'updateTime',width:'20%',title:'修改时间'},
			{field:'opt',title:'操作',width:'20%',align:'center',
				formatter:function(value,rec){
					var reStr = '<a style="color:#2A9CD5" onclick="editView(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>';
					reStr += '<a style="color:#2A9CD5" onclick="copyData(\''+rec.id+'\')">&nbsp;&nbsp;复制</a>';
					reStr += '<a style="color:#2A9CD5;display: none;" onclick="deleteIndemnity(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>';
					return reStr;
				}
			}
		]]
	}));
</script>