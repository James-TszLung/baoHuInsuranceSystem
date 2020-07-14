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

#cds input{
		height:20px;
	}
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<div class="policy_added">
				<ul>
					<li><label>*菜单名称：</label> <input placeholder="请输入菜单名称"
						name="funName" id="funName" value="" />
					</li>
					<li id="cds"><label>*上级菜单：</label> <input type="text" name="parentMenu"
						id="parentMenu" style="width:250px;height:28px;"
						data-options="required:true" class="text" />
					</li>
					<li><label>菜单描述:</label> <textarea placeholder="请输入菜单描述"
							rows="4" cols="100" name="remark" id="remarkAdd"
							style="margin-left:0px;" value=""></textarea>
					</li>
					<li><label>菜单链接:</label> <input placeholder="请输入菜单链接地址"
						name="funUrl" id="funUrl" value="" />
					</li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="保存"
								class="city_airport_button_s01" />&nbsp;&nbsp; <input
								id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;"
								class="city_airport_button_s01" />&nbsp;&nbsp;
						</div>
					</li>
					<input type="text" name="level" id="level_by_parent"
						style="width:180px;height:28px;display:none;" />
				</ul>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/function/add.js"></script>



