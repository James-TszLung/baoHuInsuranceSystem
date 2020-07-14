<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/usermanage.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/user/user/useredit_jquery.js"></script>
<style>
ul {
	overflow: hidden;
}

.ui_addUser ul li {
	width: 100%;
}

.ui_addUser ul li label {
	width: 110px;
}

#cds input {
	height: 20px;
}
</style>
<%--页面组件部分--%>
<div class="content">
	<div class="ui_addUser">
		<form id="addEdit_form" method="post">
			<div class="policy_added">
				<ul>
					<li>
						<label>登录账号：</label>
						<span class="sel_span">
							<c:choose>
								<c:when test="${!empty userId}">
									<input name="account" value="${userName}" type="hidden" />
								</c:when>
								<c:otherwise>
									<input placeholder="请输入登录账号" type="text" name="account" id="account" />
								</c:otherwise>
							</c:choose>
							<font id="isrepeas" style="color:red;font-size:13px;"></font>
						</span>
					</li>
					<li>
						<label>密码：</label>
						<span class="sel_span">
							<input placeholder="请输入登录密码" type="text" name="password" id="password" />
						</span>
					</li>
					<li>
						<label>描述：</label>
						<span class="sel_span">
							<textarea placeholder="请输入职能描述" rows="4" cols="100" name="position" id="position" style="margin-left:0px;" value=""></textarea>
						</span>
					</li>
					<li>
						<label>邮箱地址：</label>
						<span class="sel_span">
							<input placeholder="请输入账号拥有人的邮箱地址" type="text" name="mail" id="mail" />
						</span>
					</li>
					<li id="cds">
						<label>角色：</label>
						<span class="sel_span">
							<input style="width:250px;height:28px;" type="text" id="roleIdCom" name="roleId" required="false" data-options="editable : false," />
						</span>
					</li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="保存" onclick="javascript:submitAddForm();" class="city_airport_button_s01" />
							&nbsp;&nbsp;
							<input id="backBtn" type="button" value="返回" onclick="javascript:closeAddUserWin();" style="background-color:#cccccc;color:#ffffff;"
								class="city_airport_button_s01" />
							&nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
