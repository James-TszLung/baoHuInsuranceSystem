var activityType = 1;
$(function() {
	$("#name").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
	$("#describe").validatebox({
		required : true,
		missingMessage : '不超过200个字',
		validType : "length[1,200]",
	})
	// 新增页面的单品服务列表
	$('#servicePro_grid')
			.datagrid(
					{
						type : "POST",
						fitColumns : true,
						singleSelect : true,
						nowrap:false,
						autoRowHeight : false,
						pageSize : 10,
						columns : [ [
								{
									field : 'index',
									hidden : 'true'
								},
								{
									field : 'title',
									width : '15%',
									title : '推文标题'
								},
								{
									field : 'introduction',
									width : '15%',
									title : '内容简介'
								},
								{
									field : 'opt',
									title : '操作',
									width : '14%',
									align : 'center',
									formatter : function(value, rec, index) {
										var reStr = '&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="delService(\'' + index
												+ '\')">删除</a>';
										return reStr;
									}
								} ] ]
					});
	var rowsList = [];
	$("#saveSingleProject").click(function() {
		var rowsData = $('#single_grid').datagrid('getSelections');
		for ( var i = 0; i < rowsData.length; i++) {
			rowsList.push(rowsData[i]);
		}
		$('#servicePro_grid').datagrid('loadData', rowsList);
		$("#add_singleser").fadeOut('fast');
	});
	$("#cancelId").click(function() {
		$("#add_singleser").fadeOut('fast');
	});
	
	
});
//新增页面删除单品
function delService(index) {
	$('#servicePro_grid').datagrid('deleteRow', index);
}
function addServicePro() {
	console.log("s")
	$("#add_singleser").fadeIn('fast');
	var datas = $('#servicePro_grid').datagrid('getData');
	if (datas.rows.length > 0) {
		var ids = "";
		for ( var i = 0; i < datas.rows.length; i++) {
			ids = ids + datas.rows[i].id; // 假设Table中有列名number
			ids += ",";
		}
		ids = ids.substring(0, ids.length - 1);
		$("#serviceProIds").val(ids);
	} else {
		$("#serviceProIds").val("");
	}
	doSearchSinglePro(true);
}

var lastData;
function doSearchSinglePro(f) {

	var searchURL = "/content/serachPushEssay2.htm";
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
	// 头像校验
	var pictrueId = $("#covermapId").val();
	if (pictrueId.length <= 0 && "" == pictrueId) {
		$("#picts").text("请上传封面图！");
		$("#change_avatar").focus();
		return;
	}
	var rowsData = $("#servicePro_grid").datagrid("getRows");
	var dataArr = [];
	for ( var i = 0; i < rowsData.length; i++) {
		var str = "";
		str += "<input type=\"hidden\" name='konwRelationLs[" + i + "].essayId' value=\""
				+ rowsData[i].id + "\">";
		$("#serviceMealDiv").append(str)
	}
	if (rowsData == "" || rowsData == null || rowsData == undefined) {
		showMessage("推文收录不能为空");
		return;
	}
	// 新增
	$("#addform").form("submit", {
		url : ctxPath + "/content/dosaveKnowledgeBase.htm",
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
	visitUrl(ctxPath + "/content/pushknowledgeBasePage.htm", 1, 1, 1);
});

$("#change_skills").click(function() {
	 $('#alter_skills').fadeIn('fast');
});


$("#skill-btnClose").click(function(){
	 $('#alter_skills').fadeOut('fast');
})
// 封面图的上传弹出框
$(function() {
	$('#change_avatar').click(function() {
		$('#alter_avatar').fadeIn('fast');
		var avatar_input = document.getElementById('avatar_input');
		avatar_input.addEventListener('change', function() {
			if (!this['value'].match(/.jpg|.png|.bmp|.gif/i)) {
				alert('请上传图片！');
				return;
			}
			var reader = new FileReader();
			reader.readAsDataURL(this.files[0]);

			reader.onload = function(e) {
				var avatar_wrapper = document.getElementById('avatar_wrapper');
				var html = '<img class="" id="image" src="' + this.result + '">';
				avatar_wrapper.innerHTML = html;
				var $previews = $('.preview');

				$('#image').cropper({
					viewMode : 1,
					dragMode : 'move',
					autoCropArea : 1,
					aspectRatio : 318 / 750,
					restore : false,
					guides : false,
					highlight : false,
					cropBoxMovable : false,
					cropBoxResizable : false,

					ready : function(e) {
						var $clone = $(this).clone().removeClass('cropper-hidden');
						$clone.css({
							display : 'block',
							width : '100%',
							minWidth : 0,
							minHeight : 0,
							maxWidth : 'none',
							maxHeight : 'none'
						});
						$previews.css({
							width : '225px',
							height : '102px',
						// overflow:
						// 'hidden'
						}).html($clone);
					},

					crop : function(e) {
						var imageData = $(this).cropper('getImageData');

						var $imgdata = $(this).cropper('getCroppedCanvas', {
							width : 318,
							height : 750 
						});
						var dataurl = $imgdata.toDataURL('image/png');

						$("#images").val(dataurl);

						var previewAspectRatio = e.width / e.height;
						$previews.each(function() {
							var $preview = $(this);
							var previewWidth = $preview.width();
							var previewHeight = previewWidth / previewAspectRatio;
							var imageScaledRatio = e.width / previewWidth;

							$preview.height(previewHeight).find('img').css({
								width : imageData.naturalWidth / imageScaledRatio,
								height : imageData.naturalHeight / imageScaledRatio,
								marginLeft : -e.x / imageScaledRatio,
								marginTop : -e.y / imageScaledRatio
							});
						});
					}
				});
			}
		});
	});

	$('#sure-btn').click(function() {
		var dataurl = $("#images").val();
		if (dataurl != null && dataurl != "") {
			var html = '<img id="image2" style="width:120px;height:120px;" src="' + dataurl + '">';
			$("#images4").html(html);
			uploadRefundFile();
			$("#picts").text("");
			$(this).parent().parent().parent().parent().fadeOut('fast');
		} else {
			$(this).parent().parent().parent().parent().fadeOut('fast');
		}

	})
	
	
	$('#pic-btn').click(function() {
		$(this).parent().parent().parent().parent().fadeOut('fast');
	});
	
})



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
				$("#covermapId").val(result.pictureid);// 要保存的图片Id

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






