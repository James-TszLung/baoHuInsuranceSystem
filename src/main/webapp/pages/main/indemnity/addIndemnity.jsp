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
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off" action="/indemnity/saveIndemnity.htm">
			<div class="policy_added">
				<ul>
					<li>
						<label>*保险类型：</label>
						<span class="sel_span">
							<select name="dictionaryContentId">
								<c:forEach var="item" items="${typeLs}">
									<option value="${item.id}">${item.content}</option>
								</c:forEach>
					         </select>
					    </span>
					</li>
					<li>
						<label>*保障类型：</label>
						<span class="sel_span"><input placeholder="请输入保障类型，最多50字" type="text" name="content" id="content" maxlength="50"/></span>
					</li>
					<li>
						<label>排序：</label>
						<span class="sel_span"><input type="number" name="sort"/></span>
					</li>
					<li>
						<label>子项目：</label>
						<span class="sel_span">
							<table>
								<tr>
									<td id="fwphtd">
										<div style="width:100%;overflow:hidden;">
											<input id="addstand" onclick="addServicePro()" type="button" value="增加" class="city_airport_button_s01" />
										</div>
										<br>
										<div class="qtdiv" style="width:100%;overflow:hidden;margin-bottom: 8px;">
											<span class="title-block">
												<span class="table-title">子项目名称</span>
												<span class="table-title">排序</span>
											</span>
										</div>
										<div  id='qtdiv0' class="qtdiv" name="qtdiv" index='0' style="width:100%;overflow:hidden;">
											<span class="form-block" >
												<input name="indemnitySubLs[0].name" maxlength="50" type="text" /> : <input name="indemnitySubLs[0].sort"  type="number">
											</span>
											&nbsp&nbsp
											<a onclick="delqt('0')" style="color:#2A9CD5">删除</a>
										</div>&nbsp

									</td>
								</tr>
							</table>
						</span>
					</li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="保存" class="city_airport_button_s01" /> &nbsp;&nbsp;
							<input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" /> &nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/indemnity/addIndemnity.js"></script>
