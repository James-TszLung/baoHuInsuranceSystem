<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

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
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<div class="policy_added">
				<ul>
					<li><input name="roleId" value="${data.roleId}" type="hidden" />
						<label>*角色名称：</label> <span class="sel_span"> <input placeholder="请输入角色名称"
							name="roleName" id="roleName" value="${data.roleName}"  /> </span>
					</li>
					<li><label>*角色描述：</label> <span class="sel_span"> <textarea placeholder="请输入角色描述"
								rows="4" cols="100" name="remark" id="remarkAdd" 
								style="margin-left:0px;" value="">${data.remark}</textarea> </span>
					</li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input onclick="add()" id="addBtn" type="button" value="保存"
								class="city_airport_button_s01" />&nbsp;&nbsp; <input
								id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;"
								class="city_airport_button_s01" />&nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/role/viewRole.js"></script>

