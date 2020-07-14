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
	pageSize:10,
 	rowStyler:function(index,row){   
         if (row.level==1){   
             return 'background-color:#CFF2FF;';   
         }else if (row.level==2){
         	return 'background-color:#D9FFEE;';  
         } 
     },
	columns:[[
		{field:'name',width:'18%',title:'标签名称'},
			{field:'productcount',width:'18%',title:'产品数量',formatter:function (val,row) {
				if(row.productLabelRelationLs!=null){
					return row.productLabelRelationLs.length;
				}
				return val;
				}},
		  {field:'updateTime',width:'18%',title:'修改时间',
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
				var reStr = '<a style="color:#2A9CD5" onclick="geEditView(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>';
				reStr += '<a style="color:#2A9CD5;display: none;" onclick="deleteProductLabel(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>';
				return reStr;
			}
		}
	]]
}));
</script>