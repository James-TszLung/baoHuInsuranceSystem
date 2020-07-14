function initPage() {

}

// 修改提交
function submitUpdateForm() {
	var options = $("#areaIdSel option:selected"); // 获取选中的项
	if (options.val() == 0) {
		showMessage("请选择区域");
		return;
	}
	if (options.val() == $("#areaId").val()) {
		showMessage("无法调拨到本区域");
		return;
	}
	$("#addAreaform").form("submit", {
		url : ctxPath + "/depotDet/allocation.htm",
		success : function(d) {
			d = eval("(" + d + ")");
			if (d == true || d == 'true') {
				showMessage("调拨完成！");
				closeAddUserWin();
				doSearch(false);
			} else
				showMessage("调拨败！");
		},
		error : function() {
			showMessage("请求失败！");
		}
	});
}

function closeAddUserWin() {
	$('#add_area_win').window('close');
}

// 修改
function modifyAreaInfo(row) {
	initSelect(row);
}

// 修改城市信息
function initSelect(row) {
	$('#sub').unbind(); // 移除所有
	$('#sub').bind("click", function() {
		submitUpdateForm();
	});
	$("#add_area_win").show();
	$("#addAreaform").form("load", row);
	$("#areaId").val(row.areaId);
	$('#numDisable').numberbox('setValue', row.num);
	// 设置主键不能修改
	$("#add_area_win").window("open");
}

function doSearch(f) {
	var searchURL = "/depotDet/queryFactoryOrderList.htm";
	if (f) {
		var url = ctxPath + searchURL;
		lastPostData = $('#queryform :input').serializeArray();
		$('#results').load(url, lastPostData);
	} else {
		var url = ctxPath + searchURL;
		var postData = lastPostData.concat($('#resultList :input').serializeArray());
		$('#results').load(url, postData);
	}
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
			var result = eval('(' + data + ')');
			if (result.state == 'true' || result.state == true) {
				$("#picidSub").val(result.pictureid);
				$("#picNameSub").val(result.picName);
				$("#pictureIdSon" + subNum).val(result.pictureid);// 要保存的图片Id
				
				$(this).parent().parent().parent().parent().fadeOut('fast');
			} else {
				$(this).parent().parent().parent().parent().fadeOut('fast');
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

//增加需求了解字段的图片集
function uploadRefundFromLsDemand() {
	// 触发上传控制器
	$("#uploadRefundFromLsDemand").form("submit", {
		url : ctxPath + "/uploadFile/uploadFilelsDemand.htm",
		cache : false,
		type : "POST",
		success : function(data) {
			var result = eval('(' + data + ')');
			if (result.state == 'true' || result.state == true) {
				var num = $("div[name='divPicLsDemand']").length;
				var str = "pictureDemandLsId" + (num - 1);
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
				console.log(subNum);
				var num = $("li[name^=divPicLSub" + subNum + "]").length;
				console.log(num);
				var str = "pictureLsIdSub" + subNum + "_" + (num - 1);
				$("#" + str).val(result.pictureid);
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