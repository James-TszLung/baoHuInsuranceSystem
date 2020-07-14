function initPage() {

}


function closeAddUserWin() {
	$('#add_area_win').window('close');
}

// 修改
function modifyAreaInfo(row) {
	initSelect(row);
}

// 修改页面的关闭窗口
function closes() {
	var reset = $("#resets").val();
	if (reset == "返回") {
		$('#add_Newcity').window("close");
	} else {
		$("#add_Newcity").resetForm();
	}
}

// ===============上传图片=================
function uploadRefundFile() {
	// 触发上传控制器
	$("#uploadRefundFrom").form("submit", {
		url : ctxPath + "/uploadFile/uploadFile.htm",
		cache : false,
		type : "POST",
		success : function(data) {
			// mainloading.hide();
			var result = eval('(' + data + ')');

			if (result.state == 'true' || result.state == true) {
				$("#picid").val(result.pictureid);
				$("#picName").val(result.picName);
				$("#pictureId").val(result.pictureid);// 要保存的图片Id

				$(this).parent().parent().parent().parent().fadeOut('fast');
				// showMessage("上传成功");
			} else {

				$(this).parent().parent().parent().parent().fadeOut('fast');
				// showMessage("上传异常");

			}

		},
		error : function() {
			Alertify.log.error("请求失败！", 3000);
		}
	}, 'json');
}

function uploadRefundFileSub(subNum) {
	// 触发上传控制器
	$("#uploadRefundFromSub").form("submit", {
		url : ctxPath + "/uploadFile/uploadFileSub.htm",
		cache : false,
		type : "POST",
		success : function(data) {
			console.log(subNum);
			var result = eval('(' + data + ')');
			if (result.state == 'true' || result.state == true) {
				$("#picidSub").val(result.pictureid);
				$("#picNameSub").val(result.picName);
				$("#pictureIdSon" + subNum).val(result.pictureid);// 要保存的图片Id
			} 
		},
		error : function() {
			Alertify.log.error("请求失败！", 3000);
		}
	}, 'json');
}

// 图片集
function uploadRefundFromLs() {
	// 触发上传控制器
	$("#uploadRefundFromLs").form("submit", {
		url : ctxPath + "/uploadFile/uploadFilels.htm",
		cache : false,
		type : "POST",
		success : function(data) {
			var result = eval('(' + data + ')');
			if (result.state == 'true' || result.state == true) {
				var num = $("div[name='divPicLs']").length;
				var str = "pictureLsId" + (num - 1);
				$("#" + str).val(result.pictureid);
			}
		},
		error : function() {
			Alertify.log.error("请求失败！", 3000);
		}
	}, 'json');
}

// 服务子类图片集
function uploadRefundFromSubLs(subNum) {
	// 触发上传控制器
	$("#uploadRefundFromSubLs").form("submit", {
		url : ctxPath + "/uploadFile/uploadFilelSub.htm",
		cache : false,
		type : "POST",
		success : function(data) {
			var result = eval('(' + data + ')');
			if (result.state == 'true' || result.state == true) {
				var num = $("li[name^=divPicLSub" + subNum + "]").length;
				var str = "pictureLsIdSub" + subNum + "_" + (num - 1);
				$("#" + str).val(result.pictureid);
			} else {

			}

		},
		error : function() {
			Alertify.log.error("请求失败！", 3000);
		}
	}, 'json');
}

function uploadRefundFileView() {
	$("#uploadRefundFile").css('display', 'block'); // 显示
	$("#uploadRefundFile").window('open');
}