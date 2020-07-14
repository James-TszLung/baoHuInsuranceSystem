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


var levelCN = {1:'一级',2:'二级',3:'三级'};
//初始化数据表格
autoResizeDG($('#result_grid').datagrid({
	type: "POST",
	data:${data},
	fitColumns:true,
	singleSelect:true,
	autoRowHeight:false,
	nowrap:false,
	pageSize:10,
 	rowStyler:function(index,row){   
         if (row.level==1){   
             return 'background-color:#CFF2FF;';   
         }else if (row.level==2){
         	return 'background-color:#D9FFEE;';  
         } 
     },
	columns:[[
		{field:'name',width:'20%',title:'模块名称'},
		{field:'describe',width:'25%',title:'模块描述'},
		{field:'essayCount',width:'8%',title:'收录推文'},
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
			console.log(rec)
				var reStr = ''
				 	reStr = '<a style="color:#2A9CD5" onclick="editView(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>'+reStr;
				       reStr = '<a style="color:#2A9CD5" onclick="deleteById(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>'+reStr;
				return reStr;
			}
		}
	]]
}));
</script>