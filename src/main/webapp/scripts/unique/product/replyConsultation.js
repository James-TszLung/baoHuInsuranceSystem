$(function() {
       
});

var lastData;
function doSearchSinglePro(f) {
	var searchURL = "/product/serachProductConsultation.htm";
	if (f) {
		var url = ctxPath + searchURL;
		lastData = $('#searchSingleProjectForm :input').serializeArray();
		$("#singleProjectListMeal").load(url, lastData);
	} else {
		var url = ctxPath + searchURL;
		var postData = lastData.concat($('#resultMealList :input').serializeArray());
		$("#singleProjectListMeal").load(url, postData);
	}
}

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
			data = eval("(" + data + ")");
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
	visitUrl(ctxPath + "/operate/consultationPage.htm", 1, 1, 1);
});




