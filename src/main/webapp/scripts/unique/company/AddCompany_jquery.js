$(function() {
	$("#companyName").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
	$("#phone").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
	$("#searchKey").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
});

// 保存或修改技术管家

$("#addBtn").click(function() {

	if ($('#addform').form('enableValidation').form('validate') == false) {
		return;
	}
	var mainloading = new ol.loading({
		id : "addDiv",
		loadingClass : "main_loading"
	});
	mainloading.show();
	// 新增
	$("#addform").form("submit", {
		url : ctxPath + "/configuration/dosaveCompany.htm",
		success : function(res) {
			console.log(res.success)
			$("#backBtn").click();
			showMessage("保存成功");

		},
		error : function() {
			showMessage("请求失败！");
		}
	});

});
// 新增页面返回
$("#backBtn").click(function() {
	visitUrl(ctxPath + "/configuration/companyPage.htm", 1, 1, 1);
});


