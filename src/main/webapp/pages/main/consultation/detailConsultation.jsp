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

.m-btn {
	transition: all .3s ease;
	padding: 8px 16px;
	font-size: 16px;
	display: inline-flex;
	justify-content: center;
	align-items: center;
	color: white;
	border-radius: 4px;
}

.m-btn:hover {
	cursor: pointer;
}

.btn-add-person {
	background-color: #3c61db;
}

.wrapper {
	display: flex;
}

.wrapper .block-title {
	flex-grow: 0;
	font-size: 24px;
	margin-right: 12px;
}

.wrapper .form {
	flex-grow: 1;
	max-width: 1200px;
}

.form .content {
	background-color: rgb(219, 215, 215);
	border-radius: 4px;
	margin-top: 8px;
	padding: 8px;
	position: relative;
}

.form .content .row {
	margin-bottom: 12px;
}

.row .title {
	display: inline-block;
	vertical-align: top;
	width: 80px;
}

.row .value {
	display: inline-block;
	width: 1000px;
	margin-bottom: 12px;
}

input,select {
	height: 32px;
	border-radius: 4px;
	border: none;
}

textarea {
	margin-top: 8px;
	display: block;
	width: 600px;
	border: none;
	border-radius: 4px;
}

.value.product-list {
	
}

.value.product-list .product-item {
	border-bottom: 1px grey dotted;
	padding: 4px 0;
	width: 100%;
	display: inline-flex;
	justify-content: space-between;
}

.product-item .item-block {
	flex-basis: 23%;
	display: inline-flex;
	align-items: center;
}

.product-item .item-block span {
	display: inline-block;
	white-space: nowrap;
	margin-right: 4px;
	flex-grow: 0;
}

.product-item .item-block select,.product-item .item-block input {
	flex-grow: 1;
}

.btn-delete-product,.btn-delete-person {
	background-color: gray;
}

.btn-delete-person {
	position: absolute;
	top: 8px;
	right: 8px;
}

.btn-add-product {
	background-color: #3c61db;
	margin-bottom: 12px;
}

.template {
	display: none;
}
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<input type="hidden" name="id" id="id" value="${sc.id}" />
			<div class="policy_added">
				<ul>
					<li><label>姓名：</label> <span class="sel_span">${name} </span>
					</li>
					<li><label>称呼：</label> <span class="sel_span">${sc.call} </span>
					</li>
					<li><label>联系电话：</label> <span class="sel_span">${sc.phone} </span>
					</li>
					<li><label>保障需求：</label> <span class="sel_span">${sc.guaranteeDemand} </span>
					</li>
					<li><label>为谁投保：</label> <span class="sel_span">${sc.forWhom} </span>
					</li>
					<li><label>提交时间：</label> <span class="sel_span"> <fmt:formatDate value="${sc.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span>
					</li>
				   <c:if test="${sc.status==1}">
					<li>
						<div id="m-config-form" class="wrapper">
							<span class="block-title"><strong>方案配置</strong> </span>
							<div class="form">
								<div class="m-btn btn-add-person">增加投保人:</div>
								<div class="person-list" id="personList">
									<div class="content person-item" id='contentItemId0' name="contentItem">
										<div class="m-btn btn-delete-person">删除</div>
										<div class="row">
											<span class="title">投保人</span>
											<div class="value">
												<select style="width: 400px;" name="consultationReplyLs[0].insureName">
													<option value="本人">本人</option>
													<option value="配偶">配偶</option>
													<option value="父亲">父亲</option>
													<option value="母亲">母亲</option>
													<option value="儿子">儿子</option>
													<option value="女儿">女儿</option>
													<option value="其他">其他</option>
												</select>
											</div>
										</div>
										<div class="row">
											<span class="title">推荐理由</span>
											<div class="value">
												<select style="width: 400px;" name="consultationReplyLs[0].reasonDictionaryId">
													<c:forEach items="${list}" var="s">
														<option value="${s.id}">${s.content}</option>
													</c:forEach>
												</select>
												<textarea style="margin-left: 0px;" cols="30" rows="10" name="consultationReplyLs[0].reasonContent"></textarea>
											</div>
										</div>
										<div class="row">
											<span class="title"><strong>推荐产品</strong> </span>
											<div class="value product-list" id="btn-add-product0">
												<div class="m-btn btn-add-product" data-val="0">新增产品</div>
												<div class="product-item" id='productItemId0' name="productItem0">
													<div class="item-block">
														<input type="hidden" id="productIdVal00" name="productIdName0"/>
														<input type="hidden" id="productIdSaveVal00" name="consultationReplyLs[0].ReplyRelationProductLs[0].productId"/>
														<span>产品名称:</span> <input type="text" readonly id="productId00" >
													</div>
													<div class="item-block">
														<span>产品类别:</span> <input type="text" readonly id="productTypeName00">
													</div>
													<div class="item-block" id="channel00">
														
													</div>
													<div class="m-btn btn-delete-product">删除</div>
												</div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</div></li>
					</c:if>
					  <c:if test="${sc.status==2 && !empty replyLs}">
					<li>
						<div id="m-config-form" class="wrapper">
							<span class="block-title"><strong>方案配置</strong> </span>
							<div class="form">
							  	<c:forEach items="${replyLs}" var="s">
								<div class="person-list" id="personList">
									<div class="content person-item"  name="contentItem">
										<div class="row">
											<span class="title">投保人</span>
											<div class="value">
													${s.insureName}
											</div>
										</div>
										<div class="row">
											<span class="title">推荐理由</span>
											<div class="value">
														${s.dictionaryContent.content}
												<textarea style="margin-left: 0px;" cols="30" rows="10" readonly>${s.reasonContent}</textarea>
											</div>
										</div>
										<div class="row">
											<span class="title"><strong>推荐产品</strong> </span>
											<div class="value product-list" >
											 <c:forEach items="${s.replyRelationProductLs}" var="sp">
												<div class="product-item" >
													<div class="item-block">
														<span>产品名称:</span>${sp.product.name} 
													</div>
													<div class="item-block">
														<span>产品类别:</span> ${sp.product.dictionaryContent.content}
													</div>
													<div class="item-block" >
														 ${sp.dictionaryContent.content}
													</div>
												</div>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
								</c:forEach>
								
							</div>
						</div></li>
					</c:if>
					
					
					
					
					
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<c:if test="${sc.status==1}">
								<input onclick="addBtn();" type="button" value="保存" class="city_airport_button_s01" />
							&nbsp;&nbsp;
							</c:if>
							<input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" />
							&nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
		</form>

	</div>
</div>

<!-- 弹窗:添加单品服务 -->
<div class="black add-basecls" id="add_singleser" style="display:none">
	<div class="pop-up">
		<div class="header">
			<span class="tit">添加单品服务</span> <span class="cancel iconfont" id="cancelId"></span>
		</div>
		<div class="search" id="search">
			<form id="searchSingleProjectForm">
				<input type="hidden" name="serviceProIds" id="serviceProIds">
				<div class="input-box box1">
					<label for="input1">单品服务：</label> <input name="id" id="serviceName" style="display:none" /> <input type="text" name="name" id="input1"
						placeholder="输入单品服务名称关键字">

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

<script>
	var reasonData = '${data}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/consultation/replyConsultation.js"></script>

