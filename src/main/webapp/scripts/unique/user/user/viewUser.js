$(function() {
	$("#uesrId").validatebox({
		required : true,
		invalidMessage : '不超过50字，只允许输入英文字母、数字、',
		validType : 'alphanumeric[1,50]',
	})
	
	$("#new_password").validatebox({
		required : true,
		validType : "length[6,16]",
		missingMessage : "6-16位"
	})

	$("#mail").validatebox({
		required : false,
		validType : "length[1,50]",
		missingMessage : "不超过50个字"
	})

	$("#uesrId").validatebox({
		readonly : true,
	})
});

function add() {
	$("#addEdit_form").form("submit", {
		url : ctxPath + "/usermanage/setUserManage.htm",
		success : function(data) {
			data = eval("(" + data + ")");
			if (data == "true" || data == true) { // 保存成功
				$("#backBtn").click();
				showMessage("保存成功");
				visitUrl(ctxPath + "/usermanage/userPage.htm", 1, 1, 1);
			} else { // 保存失败
				showMessage("系统错误！");
			}
		},
		error : function() {
			showMessage("请求失败！");
		}
	});
}

$("#backBtn").click(function() {
	visitUrl(ctxPath + "/usermanage/userPage.htm", 1, 1, 1);
});

$('#roleIdCom').combobox({
	url : ctxPath + '/role/getAllRole.htm',
	valueField : 'roleId',
	textField : 'roleName',
	required : true
});