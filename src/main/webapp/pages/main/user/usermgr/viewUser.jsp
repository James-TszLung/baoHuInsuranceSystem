<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form id="addEdit_form" method="post">
		<div class="ui_addUser">
			<input name="id" value="${data.id}" type="hidden" />
			<ul>
				<li>
					<label>登录账号：</label>
					<span class="sel_span">
						<c:choose>
							<c:when test="${!empty userId}">
								<input name="account" type="hidden" />
							</c:when>
							<c:otherwise>
								<input placeholder="请输入登录账号" id="uesrId" type="text" name="account" value="${data.account}" style="width:232px;height:30px;" />
							</c:otherwise>
						</c:choose>
					</span>
				</li>
				<%--<li>
					<label>密码：</label>
					<span class="sel_span">
						<input placeholder="请输入登录密码" type="password" name="password" class="text" value="${data.password}" id="new_password"
							style="width: 227px; height: 20px;" />
					</span>
				</li>
				--%><li>
					<label>描述：</label>
					<span class="sel_span">
						<textarea placeholder="请输入职能描述" rows="4" cols="100" name="position" id="position" style="margin-left:0px;height:112px;" value="">${data.position}</textarea>
					</span>
				</li>
				<li>
					<label>邮箱地址：</label>
					<span class="sel_span">
						<input placeholder="请输入账号拥有人的邮箱地址" type="text" name="mail" value="${data.mail}" style="width:239px;height:30px;" id="mail" />
					</span>
				</li>
				<li id="cds">
					<label>角色：</label>
					<span class="sel_span">
						<input type="text" id="roleIdCom" name="roleId" value="${data.roleId}" data-options="editable : false,"
							style="width:250px;height:28px;" />
					</span>
				</li>
				<li style="width:100%;text-align: center;">
					<input id="addBtn" type="button" onclick="javascript:add();" value="保存" class="city_airport_button_s01" />
					&nbsp;&nbsp;
					<input id="backBtn" type="button" value="返回" onclick="javascript:closeAddUserWin();" style="background-color:#cccccc;color:#ffffff;"
						class="city_airport_button_s01" />
					&nbsp;&nbsp;
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function doChange() {
		//$("#new_password").val("");
	}
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/user/user/viewUser.js"></script>