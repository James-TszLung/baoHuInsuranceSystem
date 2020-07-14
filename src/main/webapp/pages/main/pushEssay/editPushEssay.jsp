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

	.form .item {
		overflow: hidden;
		display: inline-block;
	}

	.item label {
		float: left;
	}

	.item input {
		float: right;
	}

	input [type='checkbox'] :not(:checked ) {display: none;}
	input[type='checkbox']:not  (:checked  )+label {
		display: none;
		opacity: 0;
	}

	.form-block input:nth-child(1) {
		width: 600px;
	}

	.form-block input:nth-child(2) {
		width: 200px;
	}
	.tab__tag-list label{width: auto !important;display: inline-flex !important;margin-bottom: 5px;}
	.tab__tag-btn{display: inline-flex;position: relative;margin-right: 16px;}
	.tab__tag-btn .text {
		display: inline-block;
		/*width: 100%;*/
		height: 100%;
		line-height: 40px;
		text-align: center;
		white-space: nowrap;
		color: white;
		background-color: grey;
		border-radius: 4px;
		transition: all 0.3s ease;
		padding: 0px 20px;
	}
	.btn-del-tag {
		color: #007bff !important;
		margin-left: 5px;
		padding-top: 10px;
	}
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<input type="hidden" name="id" id="id" value="${sc.id}" />
			<div class="policy_added">
				<ul>
					<li><label>*推文标题：</label> <span class="sel_span"> <input placeholder="请输入字段名称" type="text" name="title" id="title"
																			 value="${sc.title}" /> </span></li>
					<li><label>*排序:</label> <span class="sel_span"> <input type="number" name="sort" id="sort" value="${sc.sort}" placeholder="请输入数字，最大长度3位">
					</span></li>
					<li><label>*内容简介:</label> <span class="sel_span"> <textarea placeholder="请输入内容简介" name="introduction" id="introduction"
																				value="${sc.introduction}" style="margin-left:0px;height:112px;">${sc.introduction}</textarea> </span></li>
					<li><label>*链接地址：</label> <span class="sel_span"> <input placeholder="请输入链接地址" type="text" name="linkAddress"
																			 id="linkAddress" value="${sc.linkAddress}" /> </span></li>
					<li><label>*所属类别：</label> <span class="sel_span"> <select style="margin-left:0px;" name="typeDictionaryId"
																			  id="typeDictionaryId" value="${sc.typeDictionaryId}">
								<option value="">请选择</option>
								<c:forEach items="${list}" var="s">
									<option <c:if test='${s.id == sc.typeDictionaryId}'>selected="selected"</c:if> value="${s.id}">${s.content}</option>
								</c:forEach>
						</select> </span></li>

					<li><label>*导航类别：</label>
						<span class="sel_span">
						<select style="margin-left:0px;" name="classification" id="classification">
								<option value="">请选择</option>
								<option <c:if test='${sc.classification == 1}'>selected="selected"</c:if> value="1">热门</option>
								<option <c:if test='${sc.classification == 2}'>selected="selected"</c:if> value="2">最新</option> 
								<option <c:if test='${sc.classification == 3}'>selected="selected"</c:if> value="3">精选</option> 		
						</select>
					</span>
					</li>

					<li style="border-bottom:none">
						<label>*推文标签：</label>
						<span class="sel_span">
							<input id="change_skills" type="button" value="新增" class="city_airport_button_s01" />
						</span>
					</li>
					<li>
						<label>&nbsp</label>
						<span class="sel_span">
							<span id="skillspan2">
								<div class="form" id="formcheckboxId">
									<input type="hidden" id="tradeList" value="${data}" />
									<c:forEach items="${sc.essayRelationDictionaryLs}" var="e" varStatus="ess">
										<div class="item">
											<label for="man">${e.content} </label><input type="hidden" name="essayRelationDictionaryLs[${ess.index}].dictionaryId" value='${e.dictionaryId}'> <input type="hidden" value="${e.content}" />
										</div>
									</c:forEach>

								</div>
							</span>
						</span>
					</li>
					<li style="border-bottom:none">
						<label>关联产品：</label>
						<span class="sel_span">
							<input onclick="addServicePro()" type="button" value="新增" class="city_airport_button_s01" />
						</span>
					</li>
					<li>
						<span class="sel_span">
<%--							<label>&nbsp</label>--%>
							<span id="productspan2">
							    <div class="form tab__tag-list" id="formcheckproductId">
									<c:forEach var="item" items="${sc.pushProductLs}">
										<label class="tab__tag-btn">
											<input type="hidden" name="productIds" value="${item.productId}">
											<span class="text">${item.product.name}</span>
											<a class="btn1 btn-del-tag">删除</a>
										</label>
									</c:forEach>
								</div>
							</span>
						</span>
					</li>

					<li>
						<label id="picfc">*封面图片:</label>
						<span class="sel_span">
						<input id="change_avatar" type="button" value="上传封面" class="city_airport_button_s01" />
						<input type="hidden" name="covermapId" id="covermapId" value="${sc.covermapId}" />
						<font id="picts" style="color:red;"></font>
					</span>
					</li>
					<li><label>&nbsp;</label> <span id="images4" class="sel_span"> <img style="width:120px;height:120px;" src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.covermapId}" /> </span></li>

					<li><label>推送：</label> <span class="sel_span"> <input id="attesCk1" name="homePageXcxflag" onclick="ckchange()"
																		  <c:if test='${sc.homePageXcxflag==1}'>checked="checked"</c:if> value='1' style="width: initial;" type="checkbox">
							小程序首页&nbsp;&nbsp;&nbsp; <input id="attesCk2" name="columnXcxflag" onclick="ckchange()"
														   <c:if test='${sc.columnXcxflag==1}'>checked="checked"</c:if> value='1' style="width: initial;" type="checkbox">
							小程序栏目精选&nbsp;&nbsp;&nbsp; <input id="attesCk3" name="websiteflag" onclick="ckchange()"
															 <c:if test='${sc.websiteflag==1}'>checked="checked"</c:if> value='1' style="width: initial;" type="checkbox"> 网站首页
							&nbsp;&nbsp;&nbsp;&nbsp; </span></li>
					<li style="display:none;" id="onePicture"><label id="picfc">小程序首页图:</label> <span class="sel_span"> <input
							id="activity_avatar1" onclick="activity(1);" type="button" value="上传活动图片一" class="city_airport_button_s01" /> <input type="hidden"
																																				 name="activityImage1" id="activityImage1" /> &nbsp;&nbsp;&nbsp;&nbsp;<font id="activityPicts1" style="color:red;"></font> </span></li>
					<li id="onePicturePicture"><label>&nbsp;</label> <span id="imagesShow1" class="sel_span"> <c:if
							test="${!empty sc.activityImage1}">
						<img style="width:120px;height:120px;" src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.activityImage1}" />
					</c:if> </span></li>
					<li style="display:none;" id="twoPicture"><label id="picfc">小程序栏目精选图:</label> <span class="sel_span"> <input
							id="activity_avatar2" onclick="activity(2);" type="button" value="上传活动图片二" class="city_airport_button_s01" /> <input type="hidden"
																																				 name="activityImage2" id="activityImage2" /> &nbsp;&nbsp;&nbsp;&nbsp;<font id="activityPicts2" style="color:red;"></font> </span></li>
					<li id="twoPicturePicture"><label>&nbsp;</label> <span id="imagesShow2" class="sel_span"> <img
							style="width:120px;height:120px;" src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.activityImage2}" /> </span></li>
					<li style="display:none;" id="threePicture"><label id="picfc">网站首页图:</label> <span class="sel_span"> <input
							id="activity_avatar3" onclick="activity(3);" type="button" value="上传活动图片三" class="city_airport_button_s01" /> <input type="hidden"
																																				 name="activityImage3" id="activityImage3" /> &nbsp;&nbsp;&nbsp;&nbsp;<font id="activityPicts3" style="color:red;"></font> </span></li>
					<li id="threePicturePicture"><label>&nbsp;</label> <span id="imagesShow3" class="sel_span"> <img
							style="width:120px;height:120px;" src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.activityImage3}" /> </span></li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="提交" class="city_airport_button_s01" /> &nbsp;&nbsp; <input id="backBtn" type="button"
																															   value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" /> &nbsp;&nbsp;
						</div></li>
				</ul>
			</div>
		</form>
	</div>
</div>

<div class="black" style="display:none;" id="alter_skills">
	<div class="pop-up">
		<div class="header">
			<span class="tit">标签列表；</span> <span id="skill-btnClose" class="cancel iconfont"></span>
		</div>
		<div class="content">
			<div class="skill-tab">
				<ul>
					<c:forEach items="${listLabel}" var="its" varStatus="ss">
						<li name="skillDiv" sc="adfadsf${ss.index }" <c:if test="${its.flag}">class="active"</c:if>
							onclick="prochls('${its.id}','${its.content}',this)">${its.content}</li>
					</c:forEach>
					<input type="hidden" name="typesName" id="typesName">
				</ul>
			</div>
		</div>
		<div class="footer">
			<div id="skillsure-btn" class="sure-btn">确定</div>
		</div>
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
							选择图片 <input type="file" id="avatar_input">
						</div>
						<div class="avatar-wrapper" id="avatar_wrapper"></div>
					</div>
					<div class="right">
						<div class="tt">图片预览(750*318)</div>
						<div class="f-avatar preview"></div>
					</div>
				</div>
			</div>
			<div class="edit-foot">
				<div id="sure-btn" class="sure-btn">确定</div>
			</div>
		</div>
		<input type="hidden" name="images" id="images"> <input type="hidden" name="picid" id="picid" value="${p.id}"> <input
			type="hidden" name="picName" id="picName" value="${p.pictureName}">
	</form>
</div>

<div class="black" style="display:none;" id="activity_avatar">
	<form id="uploadActivityFrom" method="post" enctype="multipart/form-data" autocomplete="off">
		<div class="edit-avatar">
			<div class="edit-head">
				上传图片
				<div id="pic-btn3" class="close-btn"></div>
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
							选择图片 <input type="file" id="activity_input">
						</div>
						<div class="avatar-wrapper" id="activity_wrapper"></div>
					</div>
					<div class="right">
						<div class="tt" id="picPreViewTitle">图片预览</div>
						<div class="f-avatar previewActivity"></div>
					</div>
				</div>
			</div>
			<div class="edit-foot">
				<div id="sureActivity-btn" class="sure-btn">确定</div>
			</div>
		</div>
		<input type="hidden" name="imagesActivity" id="imagesActivity">
	</form>
</div>
<!-- 弹窗:添加关联产品 -->
<div class="black add-basecls" id="add_product" style="display:none">
	<div class="pop-up">
		<div class="header">
			<span class="tit">添加关联产品</span> <span class="cancel iconfont" id="cancelId"></span>
		</div>
		<div class="search" id="search">
			<form id="searchSingleProjectForm">
				<input type="hidden" name="showLabel" value="1">
				<input type="hidden" name="serviceProIds" id="serviceProIds">
				<div class="input-box box1">
					<label for="input1">产品：</label>
					<input name="id" id="serviceName" style="display:none" />
					<input type="text" name="name" id="input1" placeholder="输入产品名称关键字">

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
	var homePageXcxflag = '${sc.homePageXcxflag}';
	var columnXcxflag = '${sc.columnXcxflag}';
	var websiteflag = '${sc.websiteflag}';
	var data = '${data}';
	if (homePageXcxflag == 1) {
		$('#onePicture').fadeIn('fast');
		$('#onePicturePicture').fadeIn('fast');
	} else {
		$('#onePicture').fadeOut('fast');
		$('#onePicturePicture').fadeOut('fast');
	}
	if (columnXcxflag == 1) {
		$('#twoPicture').fadeIn('fast');
		$('#twoPicturePicture').fadeIn('fast');
	} else {
		$('#twoPicture').fadeOut('fast');
		$('#twoPicturePicture').fadeOut('fast');
	}
	if (websiteflag == 1) {
		$('#threePicture').fadeIn('fast');
		$('#threePicturePicture').fadeIn('fast');
	} else {
		$('#threePicture').fadeOut('fast');
		$('#threePicturePicture').fadeOut('fast');
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/pushEssay/EditPushEssay_jquery.js"></script>




