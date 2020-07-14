var activityType = 1;
$(function() {
	$("#title").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
	$("input[name$=content]").each(function() {
		$(this).validatebox({
			required : true,
		});
	});

	$("input[name$=sort]").each(function() {
		$(this).validatebox({
			required : true,
			missingMessage : '不超过3位数',
			validType : "length[1,3]",
		});
	});
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
	// 头像校验
	var pictrueId = $("#covermapId").val();
	if (pictrueId.length <= 0 && "" == pictrueId) {
		$("#picts").text("请上传封面图！");
		$("#change_avatar").focus();
		return;
	}
	// 新增
	$("#addform").form("submit", {
		url : ctxPath + "/content/updateCompany.htm",
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
	visitUrl(ctxPath + "/content/pushEssayPage.htm", 1, 1, 1);
});

$("#change_skills").click(function() {
	 $('#alter_skills').fadeIn('fast');
});
var labelArr=new Array();
/*$("#skillspan2 input").each(function(i){
	console.log(i+$(this).val())
})*/
var jsonObj =  JSON.parse(data);//转换为json对象
for(var i=0;i<jsonObj.length;i++){
  	 var jsonArr  = {"id" : jsonObj[i].dictionaryId,"content" : jsonObj[i].content}
  	 labelArr.push(jsonArr);
}
function prochls(id, name, obj) {
	
	 if($(obj).hasClass("active")){
		  $(obj) .removeClass("active");
		  var jsonobj = eval(labelArr);
		  if(jsonobj.length>0){
			  for ( var i = 0; i < jsonobj.length; i++) {
				  if(jsonobj[i].id==id){
					  labelArr.splice(i,1);
					  break;
				  }
			  }
		  }
	    }else{
	    	$(obj).attr("class", "active");
		   	 var jsonArr  = {"id" : id,"content" : name }
			 labelArr.push(jsonArr);
	    }
}
$('#skillsure-btn').click(function() {
	document.getElementById("formcheckboxId").innerHTML = "";
	console.log(labelArr);
	var activeLength=$("li[name='skillDiv'].active").size();
	if(activeLength>5){
		showMessage("不能超过五个");
		return;
	}
	var str="";
	  var jsonobj = eval(labelArr);
	   if(jsonobj.length>0){
		  for ( var i = 0; i < jsonobj.length; i++) {
			  str+="<div class=\"item\"><label for=\"man\">"+jsonobj[i].content+" </label><input type=\"hidden\" name=\"essayRelationDictionaryLs[" + i + "].dictionaryId\"  value='"+jsonobj[i].id+"'></div>";
		  }
	  }
	   $("#formcheckboxId").append(str);
	
	 $('#alter_skills').fadeOut('fast');
	
})
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
					aspectRatio : 210 / 147,
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
							width : 210,
							height : 147
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
	
	// 活动图片的弹框保存
	$('#sureActivity-btn').click(function() {
		console.log("sdsadsads")
		var dataurl = $("#imagesActivity").val();
		if (dataurl != null && dataurl != "") {
			var imageActivityReal = "imageActivityReal" + activityType;
			var html = '<img id="' + imageActivityReal + '"style="width:120px;height:120px;" src="' + dataurl + '">';
			$("#imagesShow" + activityType).html(html);
			uploadRefundFileActivity(activityType);
			$("#activityPicts" + activityType).text("");
			$(this).parent().parent().parent().parent().fadeOut('fast');
		} else {
			$(this).parent().parent().parent().parent().fadeOut('fast');
		}

	})
	$('#pic-btn3').click(function() {
		$(this).parent().parent().parent().parent().fadeOut('fast');
	});

	//新增关联产品弹窗关闭
	$("#cancelId").click(function() {
		$("#add_product").fadeOut('fast');
	});

	//新增关联产品确定
	$("#saveSingleProject").click(function() {
		var rowsData = $('#single_grid').datagrid('getSelections');
		if(($("input[name='productIds']").length + rowsData.length)>20){
			alert("最多只能添加20款产品")
		}else{
			var str="";
			for ( var i = 0; i < rowsData.length; i++) {
				str += ' <label class="tab__tag-btn">' +
					'<input type="hidden" name="productIds" value="'+rowsData[i].id+'">' +
					'<span class="text">'+rowsData[i].name+'</span>' +
					'<a class="btn1 btn-del-tag">删除</a>' +
					'</label>';
			}
			$("#formcheckproductId").append(str);
			$("#add_product").fadeOut('fast');
		}

	});

	//关联产品删除
	$("#formcheckproductId").on('click', '.btn-del-tag', function delTag(e) {
		if (confirm('确定要删除吗')) {
			$(this).parent().remove();
		}
	});


})
function ckchange(){
	if($("input[name='homePageXcxflag']").prop('checked')){
		$('#onePicture').fadeIn('fast');
		$('#onePicturePicture').fadeIn('fast');
	}else{
		$('#onePicture').fadeOut('fast');
		$('#onePicturePicture').fadeOut('fast');
	}
	if($("input[name='columnXcxflag']").prop('checked')){
		$('#twoPicture').fadeIn('fast');
		$('#twoPicturePicture').fadeIn('fast');
	}else{
		$('#twoPicture').fadeOut('fast');
		$('#twoPicturePicture').fadeOut('fast');
	}
	if($("input[name='websiteflag']").prop('checked')){
		$('#threePicture').fadeIn('fast');
		$('#threePicturePicture').fadeIn('fast');
	}else{
		$('#threePicture').fadeOut('fast');
		$('#threePicturePicture').fadeOut('fast');
	}
}
function activity(type) {
	var activity_wrapper = document.getElementById('activity_wrapper');
	activity_wrapper.innerHTML = "";
	$("#imagesActivity").val("");
	$('#imageActivity').remove();
	activityUpload(type);
	activityType = type;
}

function activityUpload(type) {
	var widthNum=0;
	var heightNum=0;
	if(type==1){
		widthNum=690;
		heightNum=260;
	}else if(type==2){
		widthNum=690;
		heightNum=180;
	}else if(type==3){
		widthNum=1920;
		heightNum=620;
	}
	$('#activity_avatar').fadeIn('fast');
	var activity_input = document.getElementById('activity_input');
	activity_input.addEventListener('change', function() {
		if (!this['value'].match(/.jpg|.png|.bmp|.gif/i)) {
			alert('请上传图片！');
			return;
		}
		var reader = new FileReader();
		reader.readAsDataURL(this.files[0]);
		reader.onload = function(e) {
			var activity_wrapper = document.getElementById('activity_wrapper');
			var html = '<img class="" id="imageActivity" src="' + this.result + '">';
			activity_wrapper.innerHTML = html;
			var $previews = $('.previewActivity');
			$('#imageActivity').cropper({
				viewMode : 1,
				dragMode : 'move',
				autoCropArea : 1,
				aspectRatio : widthNum / heightNum,
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
						width : widthNum,
						height : heightNum
					});
					var dataurl = $imgdata.toDataURL('image/png');

					$("#imagesActivity").val(dataurl);

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


function uploadRefundFileActivity() {
	// 触发上传控制器
	$("#uploadActivityFrom").form("submit", {
		url : ctxPath + "/uploadFile/uploadFileActivity.htm",
		cache : false,
		type : "POST",
		success : function(data) {
			// mainloading.hide();
			var result = eval('(' + data + ')');
			console.log(result);
			if (result.state == 'true' || result.state == true) {
				console.log(result.pictureid)
				$("#activityImage" + activityType).val(result.pictureid);// 要保存的图片Id
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




// 新增关联产品
function addServicePro() {
	$("#add_product").fadeIn('fast');
	//已选产品
	var ids = "";
	if($("input[name='productIds']").length>0){
		$("input[name='productIds']").each(function () {
			ids += $(this).val() + ",";
		})
		ids = ids.substring(0, ids.length - 1);
		$("#serviceProIds").val(ids);
	}else{
		$("#serviceProIds").val("");
	}
	doSearchSinglePro(true);
}

// 关联产品弹框数据列表
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
