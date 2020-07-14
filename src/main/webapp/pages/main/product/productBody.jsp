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
			{field:'name',width:'15%',title:'产品名称'},
			{field:'insuranceType',width:'10%',title:'所属类别',
				formatter:function(value,rec){
					if(rec.dictionaryContent!=null){
						return rec.dictionaryContent.content;
					}else{
						return '';
					}
				}
			},
			{field:'saleChannel',width:'10%',title:'销售渠道',
				formatter:function(value,rec){
				   var saleChannelNames = "";
				   if(rec.recommendCooperation!=null && rec.recommendCooperation.dictionaryContent!=null){
					   saleChannelNames = rec.recommendCooperation.dictionaryContent.content;
				   }
					return saleChannelNames;
				}
			},
			{field:'companyName',width:'15%',title:'承保公司',
				formatter:function(value,rec){
					if(rec.insuranceCompany!=null){
						return rec.insuranceCompany.companyName;
					}else{
						return '';
					}
				}
			},
			{field:'status',width:'6%',title:'状态',
				formatter:function(value,rec){
					if(value=='1'){
						return '已发布';
					}else if(value=='2'){
						return '已下线';
					}else{
						return '未发布';
					}
				}
			},
			{field:'publishTime',width:'10%',title:'发布时间'},
			{field:'opt',title:'操作',width:'20%',align:'center',
				formatter:function(value,rec){
					var reStr = '<a style="color:#2A9CD5" onclick="showDetail(\''+rec.id+'\')">&nbsp;&nbsp;详情</a>';
					if(rec.status=='1'){
						reStr += '<a style="color:#2A9CD5" onclick="pushStatus(\''+rec.id+'\',2,\''+rec.name+'\')">&nbsp;&nbsp;下线</a>';
					}else if(rec.status=='2'){//已下线
						reStr += '<a style="color:#2A9CD5" onclick="editProduct(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>';
						reStr += '<a style="color:#2A9CD5" onclick="pushStatus(\''+rec.id+'\',1,\''+rec.name+'\')">&nbsp;&nbsp;发布</a>';
						reStr += '<a style="color:#2A9CD5;display: none;" onclick="deleteById(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>';
					}else{
						reStr += '<a style="color:#2A9CD5" onclick="editProduct(\''+rec.id+'\')">&nbsp;&nbsp;修改</a>';
						reStr += '<a style="color:#2A9CD5" onclick="pushStatus(\''+rec.id+'\',1,\''+rec.name+'\')">&nbsp;&nbsp;发布</a>';
						reStr += '<a style="color:#2A9CD5;display: none;;" onclick="deleteById(\''+rec.id+'\')">&nbsp;&nbsp;删除</a>';
					}
					return reStr;
				}
			}
		]]
	}));
</script>