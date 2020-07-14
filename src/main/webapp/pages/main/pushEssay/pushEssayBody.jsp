<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<style>
	.datagrid input[type="number"] {
		width: auto;
		height: 25px;
	}
</style>
<form id="resultList" action="${ctxPath}/serviceProject/serachServiceProject.htm" method="post" autocomplete="off">
	<table id="result_grid">
	</table>
	<common:pageonly sc="${sc}" />
</form>
<form id="sortForm" enctype="multipart/form-data" autocomplete="off" style="display: none;">

</form>
<script>
	function fnOnGoToPage(p){
		doSearch(false);
	}
	var IsCheckFlag = true;
	//初始化数据表格
	autoResizeDG($('#result_grid').datagrid({
		type: "POST",
		data:${data},
		fitColumns:true,
		singleSelect:false,
		autoRowHeight:true,
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
				$("#result_grid").datagrid("unselectRow", rowIndex);
			}
		},
		onUnselect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#result_grid").datagrid("selectRow", rowIndex);
			}
		},
		columns:[[
			{field:'id',width:'15%',title:'全选',checkbox:true},
			{field:'sort',width:'10%',title:'排序',
				formatter:function(val, row) {
					var input = '<input type="number" name="sort'+row.id+'" value="'+val+'" oninput="if(this.value.length > 3 ) this.value = this.value.slice(0,3)"/>';
					return input;
				}
			},
			{field:'title',width:'20%',title:'文章标题'},
			{field:'introduction',width:'20%',title:'内容简介'},
			{field:'content',width:'8%',title:'所属类别',
				formatter:function(val, row) {
					if (row.dictionaryContent != null) {
						return row.dictionaryContent.content;
					} else {
						return "";
					}
				}
			},
			{field:'websiteHits',width:'8%',title:'网络点击量'},
			{field:'xcxHits',width:'8%',title:'小程序点击量'},
			{field:'createTime',width:'10%',title:'发布时间',
				formatter:function(val, row) {
					if (val != null && val!='') {
						var date = new Date(val);
						return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
								+ date.getDate()+ '     '+ date.getHours()+ ':'+date.getMinutes()+ ':'+date.getSeconds();
					}else{
						return "";
					}
				}
			},
			{field:'opt',title:'操作',width:'12%',align:'center',
				formatter:function(value,rec){
					var reStr = ''
					reStr = '<a style="color:#2A9CD5" onclick="editView(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>'+reStr;
					reStr = '<a style="color:#2A9CD5" onclick="deleteById(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>'+reStr;
					return reStr;
				}
			}
		]]
	}));
</script>