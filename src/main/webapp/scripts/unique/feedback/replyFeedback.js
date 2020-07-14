$(function() {
	$("#reply").validatebox({
		required : true,
		missingMessage : '最多100个字',
		validType : 'length[1,100]'
	});
});

function addBtn() {
	var mainloading = new ol.loading({
		id : "xcxOrderPayAuditDiv",
		loadingClass : "main_loading"
	});
	mainloading.show();
	$("#addform").form("submit", {
		url : ctxPath + "/operate/replyFeedback.htm",
		success : function(data) {
			mainloading.hide();
			if (data.success == 1 || data.success == '1') {
				visitUrl(ctxPath + "/operate/feedbackPage.htm", 1, 1, 1);
			} else {
				showMessage("异常");
			}
		}
	});
}
// 新增页面返回
$("#backBtn").click(function() {
	visitUrl(ctxPath + "/operate/feedbackPage.htm", 1, 1, 1);
});



