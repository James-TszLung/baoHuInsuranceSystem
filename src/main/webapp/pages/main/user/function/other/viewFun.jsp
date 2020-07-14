<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<style>
	#cds input{
		height:20px;
	}
</style>
<script type="text/javascript">
	var parentMenu = '${data.parentMenu}';
	$(function() {
		$("#parentMenu").combotree({
			url : ctxPath + "/function/queryAllFun.htm?mode=all",
			method : 'post',
			required : true,
			onLoadSuccess : function(node, data) {
				console.log("========");
				console.log(data);
				if (parentMenu != "" && parentMenu != null && parentMenu != undefined) {
					//$("#parentMenu").combotree('setValue',parentMenu);
					//$('#selShenqFuwujg').combotree('setValue', { id: data[0].id, text: data[0].text }); 
					$("#parentMenu").combotree("setValue", parentMenu);
				} else {
					$("#parentMenu").combotree('setValue', 0);
				}
			},
			onChange : function(newID, oldID) {
				var t = $(this).combotree('tree');
				var n = t.tree('getSelected');
				$("#level_by_parent").val(n.level + 1);
			}
		});

	});
</script>
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
		<form id="viewform" enctype="multipart/form-data" autocomplete="off">
			<input type="hidden" id="id" name="id" value="${data.id}">
			<div class="policy_added">
				<ul>
					<li><label>*菜单名称：</label> <span class="sel_span"> <input
							placeholder="请输入菜单名称" name="funName" id="funName"
							value="${data.funName}" /> </span>
					</li>
					<li id="cds"><label>*上级菜单：</label> <span class="sel_span"> <input
							type="text" name="parentMenu" id="parentMenu"
							style="width:250px;height:30px;" data-options="required:true"
							class="text" value="${data.parentMenu}" /> </span>
					</li>
					<li><label>菜单描述:</label> <span class="sel_span"> <textarea
								placeholder="请输入菜单描述" rows="4" cols="100" name="remark"
								id="remarkAdd" style="margin-left:0px;" value="">${data.remark}</textarea>
					</span>
					</li>
					<li><label>菜单链接:</label> <span class="sel_span"> <input
							placeholder="请输入菜单链接地址" name="funUrl" id="funUrl"
							value="${data.funUrl}" /> </span>
					</li>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input onclick="add()" id="addBtn" type="button" value="保存"
								class="city_airport_button_s01" />&nbsp;&nbsp; <input
								id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;"
								class="city_airport_button_s01" />&nbsp;&nbsp;
						</div></li>
					<input type="text" name="level" id="level_by_parent"
						style="width:180px;height:28px;display:none;" />
				</ul>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/unique/user/function/addViewFun.js"></script>