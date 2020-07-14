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
		{field:'content',width:'18%',title:'留言内容'},
		 {field:'name',width:'6%',title:'留言用户',
        	formatter:function(value,rec){
        	  if(rec.user!=null){
       		 		return rec.user.name;        	  
        	  }else{
        	       return "暂无";
        	  }
       		 }
        },
		{field:'praiseNum',width:'10%',title:'点赞数'},
		 {field:'status',width:'8%',title:'状态',
	    formatter:function(value,rec){
        		if(value=='1'){
        			return '待审核';
        		}else if(value=='2'){
        			return'待回复';
        		}else if(value=='3'){
        			return'不通过';
        		}else if(value=='4'){
        			return'已回复';
        		}
        	}
        },
		  {field:'createTime',width:'10%',title:'留言时间',
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
				 	if(rec.status == '1'){
				 	reStr = '<a style="color:#2A9CD5" onclick="detail(\''+rec.id+'\')">&nbsp;&nbsp;待审核</a>'+reStr;
				    }else if(rec.status == '2'){
				       reStr = '<a style="color:#2A9CD5" onclick="detail(\''+rec.id+'\',\'1\')">&nbsp;&nbsp;回复</a>'+reStr;
				    }else {
				       reStr = '<a style="color:#2A9CD5" onclick="detail(\''+rec.id+'\',\'1\')">&nbsp;&nbsp;详情</a>'+reStr;
				    }
				return reStr;
			}
		}
	]]
}));
</script>