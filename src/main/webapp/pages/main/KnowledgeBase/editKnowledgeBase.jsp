<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/cropper.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/selector.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/style.css" type="text/css"></link>



<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/cropper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/Selector.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/plugins/editor/icomoon/style.css" type="text/css"></link>


<style>
ul {
	overflow: hidden;
}

.ui_addUser ul li {
	width: 100%;
}

.ui_addUser ul li label {
	width: 110px;
	position: static;
}

input {
	border: 1px solid #bbb;
}

select {
	width: 247px;
}

.skill-cls input {
	width: 13px;
}
.title-block span:nth-child(1) {
	display: inline-block;
    width: 600px;
    border-radius: 4px;
    background-color: grey;
    color: white;
    text-align: center;
    margin-right: 9px;
	
}
.title-block span:nth-child(2) {
	display: inline-block;
    width: 200px;
    border-radius: 4px;
    background-color: grey;
    color: white;
    text-align: center;
	
}
.form-block input:nth-child(1){
    width: 600px;
}
.form-block input:nth-child(2){
    width: 200px;
}
    .form .item {
            overflow: hidden;
            display: inline-block;
                margin-right: 30px;
        }

        .item label {
            float: left;
            width: auto;
    vertical-align: bottom;
    margin-right: 12px;
        }

        .item input {
            float: right;
            width: auto;
    vertical-align: bottom;
        }
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
		<input type="hidden" name="id" id="id" value="${sc.id}"/>
			<div class="policy_added">
				<ul>
					<li>
						<label>*知识模块名称：</label>
						<span class="sel_span">
							<input placeholder="请输入知识模块名称" type="text" name="name" id="name" value="${sc.name}"/>
						</span>
					</li>
						<li>
						<label>*知识模块描述:</label>
						<span class="sel_span">
							<textarea placeholder="请输入内容简介" name="describe" id="describe"  value="${sc.describe}"
									style="margin-left:0px;height:112px;">${sc.describe}</textarea>
						</span>
					</li>
				
						<li style="border-bottom:none">
						<label>*展示位置：</label>
						<span class="sel_span">
							<input id="change_skills" type="button" value="选择" class="city_airport_button_s01" />
						</span>
					</li>
					<li>
						<label>&nbsp</label>
						<span class="sel_span">
							<span id="skillspan2">
										<input  type="text" name="showLocation" id="showLocation" />
							</span>
						</span>
					</li>
					<li>
						<label id="picfc">*封面图片:</label>
						<span class="sel_span">
							<input id="change_avatar" type="button" value="上传封面" class="city_airport_button_s01" />
							<input type="hidden" name="covermapId" id="covermapId" value="${sc.covermapId}"/>
							&nbsp;&nbsp;&nbsp;&nbsp;<font id="picts" style="color:red;"></font>
						</span>
					</li>
					<li>
						<label>&nbsp;</label>
						<span id="images4" class="sel_span">
						 <img style="width:120px;height:120px;"
							src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.covermapId}" /> 
						</span>
					</li>
					<li>
						<label>*推文收录：</label>
						<span class="sel_span">
							<input  onclick="addServicePro()" type="button" value="新增" class="city_airport_button_s01" />
						</span>
					</li>
					<li>
						<label>&nbsp</label>
						<span class="sel_span">
							<table id="servicePro_grid">
							
							</table>
						</span>
					</li>
					
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="提交" class="city_airport_button_s01" />
							&nbsp;&nbsp;
							<input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" />
							&nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
			<div id="serviceMealDiv" style="display:none">
			  
			</div>
		</form>
	</div>
</div>



<div class="black" style="display:none;" id="alter_avatar">
	<form id="uploadRefundFrom" method="post" enctype="multipart/form-data" autocomplete="off">
		<div class="edit-avatar">
			<div class="edit-head">
				上传LOGO
				<div id="pic-btn" class="close-btn"></div>
			</div>
			<div class="edit-box">
				<div class="avatar-tip">
					<ul>

						<li>图片不允许涉及政治敏感与色情</li>
						<li>图片格式支持：BMP、JPEG、JPG、PNG，大小不超过500k</li>

					</ul>
				</div>
				<div class="edit-content">
					<div class="left">
						<div class="sel-pic">
							选择图片
							<input type="file" id="avatar_input">
						</div>
						<div class="avatar-wrapper" id="avatar_wrapper"></div>
					</div>
					<div class="right">
						<div class="tt">图片预览(750*318)</div>
						<div class="f-avatar preview" ></div>
					</div>
				</div>
			</div>
			<div class="edit-foot">
				<div id="sure-btn" class="sure-btn">确定</div>
			</div>
		</div>
		<input type="hidden" name="images" id="images">
		<input type="hidden" name="picid" id="picid" value="${p.id}">
		<input type="hidden" name="picName" id="picName" value="${p.pictureName}">
	</form>
</div>


<!-- 弹窗:添加推文 -->
	<div class="black add-basecls" id="add_singleser" style="display:none">
		<div class="pop-up">
			<div class="header">
				<span class="tit">添加推文</span>
				<span class="cancel iconfont" id="cancelId"></span>
			</div>
			<div class="search" id="search">
				<form id="searchSingleProjectForm">
					<input type="hidden" name="serviceProIds" id="serviceProIds">
					<div class="input-box box1">
						<label for="input1">推文名称：</label>
						<input name="id" id="serviceName" style="display:none" />
						<input type="text" name="title" id="input1" placeholder="输入单品服务名称关键字">

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
			serviceList.push(servicePro[i].pushEssay);
		}
	}
	console.log(serviceList);
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
									field : 'title',
									width : '15%',
									title : '推文标题'
								},
								{
									field : 'introduction',
									width : '15%',
									title : '内容简介'
								},
								{
									field : 'opt',
									title : '操作',
									width : '14%',
									align : 'center',
									formatter : function(value, rec, index) {
										var reStr = '&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="delService(\'' + index
												+ '\',\''+rec.id+'\')">删除</a>';
										return reStr;
									}
								} ] ]
					});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/KnowledgeBase/editKnowledgeBase_jquery.js"></script>





