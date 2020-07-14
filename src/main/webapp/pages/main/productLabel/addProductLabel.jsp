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

					<li><label>*标签名称：</label> <span class="sel_span"> <input placeholder="请输入标签名称，最多30字" type="text" name="name" id="name" />
					</span></li>
					<li><label>产品：</label> <span class="sel_span"> <input onclick="addServicePro()" type="button" value="新增"
							class="city_airport_button_s01" /> </span></li>
					<li><label>&nbsp</label> <span class="sel_span">
							<table id="servicePro_grid">

							</table> </span></li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addSeriesBtn" type="button" value="保存" class="city_airport_button_s01" /> &nbsp;&nbsp; <input id="backBtn" type="button"
								value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" /> &nbsp;&nbsp;
						</div></li>
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
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/productLabel/addProductLabel.js"></script>
