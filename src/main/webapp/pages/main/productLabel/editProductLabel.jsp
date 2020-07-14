<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/cropper.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/selector.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/style.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/cropper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/Selector.js"></script>
<style>
ul {
	overflow: hidden;
}

.ui_addUser ul li {
	width: 100%;
}

.ui_addUser ul li label {
	width: 150px;
	position: static;
}

input {
	border: 1px solid #bbb;
}
</style>
<div class="content">
	<div class="ui_addUser" id="seriesService">
		<form id="addSeriesform" method="post">
			<div class="policy_added">
				<ul>

					<li><label>*标签名称：</label> <span class="sel_span"> <input placeholder="请输入规格名称，最多30字" type="text" name="name" id="name"
							value="${sc.name}" /> 
							 <input type="hidden" name="id" id="id"
							value="${sc.id}" /> 
							</span>
					</li>
					<li><label>产品：</label> <span class="sel_span"> <input onclick="addServicePro()" type="button" value="新增"
							class="city_airport_button_s01" /> </span>
					</li>
					<li><label>&nbsp</label> <span class="sel_span">
							<table id="servicePro_grid">

							</table> </span>
					</li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addSeriesBtn" type="button" value="保存" class="city_airport_button_s01" /> &nbsp;&nbsp; <input id="backBtn" type="button"
								value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" /> &nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
			<ul id="serviceSub">
			</ul>

			<div id="serviceMealDiv" style="display:none"></div>

		</form>
	</div>
</div>
<!-- 弹窗:添加单品服务 -->
<div class="black add-basecls" id="add_singleser" style="display:none">
	<div class="pop-up">
		<div class="header">
			<span class="tit">添加产品</span> <span class="cancel iconfont" id="cancelId"></span>
		</div>
		<div class="search" id="search">
			<form id="searchSingleProjectForm">
				<input type="hidden" name="serviceProIds" id="serviceProIds">
				<div class="input-box box1">
					<label for="input1">产品名称：</label>
					<input type="text" name="name" id="input1" placeholder="输入产品名称关键字"/>
				</div>
				<div class="input-box box1">
					<input type="button" value="搜索" onclick="doSearchSinglePro(true);">
				</div>
			</form>
		</div>
		<div class="mid" id="singleProjectListMeal"></div>
		<div class="footer" style="float:left">
			<div class="sure-btn" id="saveSingleProject">确定</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	var dataList = '${data}';
	var servicePro = $.parseJSON(dataList);
	var serviceList = [];
	if (servicePro != null && servicePro != undefined && servicePro != '') {
		for ( var i = 0; i < servicePro.length; i++) {
			servicePro[i].product.sort = servicePro[i].sort;
			serviceList.push(servicePro[i].product);
		}
	}
	// 新增页面的单品服务列表
	$('#servicePro_grid')
			.datagrid(
					{
						type : "POST",
						data : serviceList,
						fitColumns : true,
						singleSelect : true,
						autoRowHeight : false,
						pageSize : 10,
						columns : [ [
								{
									field : 'index',
									hidden : 'true'
								},
								{
									field : 'name',
									width : '25%',
									title : '产品名称'
								},
								{
									field : 'sort',
									width : '15%',
									title : '排序',
									formatter : function(value, rec) {
										var serviceTime = "sort" + rec.id;
										var reStr = "<input placeholder='请输入排序,整数' name='" + serviceTime
												+ "' type='number' value='"+value+"' style='height: 22px;width: 115px;'>";
										return reStr;
									}
								},
								{
									field : 'opt',
									title : '操作',
									width : '14%',
									align : 'center',
									formatter : function(value, rec, index) {
										var reStr = '&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="deleteRow(this)">删除</a>';
										return reStr;
									}
								} ] ]
					});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/productLabel/editProductLabel.js"></script>
