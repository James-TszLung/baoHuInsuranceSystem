$(function() {
	$("#name").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
		$("#content").validatebox({
		required : true,
		missingMessage : '不超过500个字',
		validType : "length[1,500]",
	})
	
		$("#typeDictionaryId").validatebox({
		required : true,
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
	var activeLength=$("li[name='skillDiv'].active").size();
	if(activeLength==0){
		showMessage("请选择标签！");
		return;
	}
	var videoId=$("#videoId").val();
	if(videoId==""){
		showMessage("请上传视频！");
		return;
	}
	// 新增
	$("#addform").form("submit", {
		url : ctxPath + "/content/updateVideoManager.htm",
		success : function(res) {
			console.log(res.success) 
			$("#backBtn").click();
			showMessage("保存成功");

		},
		error : function() {
			showMessage("请求失败！");
		}
	});
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
					aspectRatio :  329 / 411 ,
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
							width : 329,
							height :  411
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
});
// 新增页面返回
$("#backBtn").click(function() {
	visitUrl(ctxPath + "/content/videoManagerPage.htm", 1, 1, 1);
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
	console.log(jsonObj[i]);  //取json中的值
  	 var jsonArr  = {"id" : jsonObj[i].dictionaryId,"content" : jsonObj[i].content}
  	 labelArr.push(jsonArr);
}
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
			  str+="<div class=\"item\"><label for=\"man\">"+jsonobj[i].content+" </label><input type=\"hidden\" name=\"videoRelationDictionaryLs[" + i + "].dictionaryId\"  value='"+jsonobj[i].id+"'></div>";
		  }
	  }
	   $("#formcheckboxId").append(str);
	
	 $('#alter_skills').fadeOut('fast');
	
})
$("#skill-btnClose").click(function(){
	 $('#alter_skills').fadeOut('fast');
})
$("#video_avatar").click(function() {
	$('#alter_video').fadeIn('fast');
})

var video_file_input = document.getElementById('file');
	var uploadflag=false;
	video_file_input.addEventListener('change', function() {
		var filesize=video_file_input.files[0].size;
		if (!this['value'].match(/.mp4|.rmvb|.avi|.ts/i)) {
			alert('请上传视频！');
			uploadflag=true;
			return;
		}else{
			uploadflag=false;
		}
		if (filesize>104857600) {
			alert('请上传视频大小少于100m！');
			uploadflag=true;
			return;
		}else{
			uploadflag=false;
		}
	});
	$("#upload").on("click", function() {
		if(uploadflag){
       	 alert('上传格式错误！');
       	 return;
        }
		var mainloading = new ol.loading({
			id : "serviceProject",
			loadingClass : "main_loading"
		});
		mainloading.show();
		$("#uploadRefundVideoFrom").ajaxSubmit({
			beforeSubmit : function() {
			},
			url : ctxPath + "/uploadFile/uploadFileVideoItls.htm",
			// 请求地址，http://localhost:8080/test-web/file
			// 后端接口获取File文件对象，java为MultipartFile对象
			success : function(data) {
				var result = eval('(' + data + ')');
				if (result.state == 'true' || result.state == true) {
					$('#alter_video').fadeOut('fast');
					$("#uploadViodeSpan").hide();
					$("#DelViodeSpan").show();
					$("#videoName").append("<span id=\"videoName"+result.videoId+"\">"+result.videoName+"</span>");
					$("#videoId").val(result.videoId);
					showMessage("上传成功");
				} else {
					showMessage("上传异常");
					$('#alter_video').fadeOut('fast');
				}
				mainloading.hide();
			}
		});
		
		
		
		
	});

$("#video_Del").click(function(){
	var videoId=$("#videoId").val();
	$.messager.confirm('消息确认', "确定要删除这条记录？", function(r) {
		if (r) {
			$.ajax({
				url : ctxPath + "/uploadFile/deleteFileVideoItls2.htm",
				data : "videoId=" + videoId,
				type : "POST",
				success : function(data) {
					var result = eval('(' + data + ')');
					console.log(result)
					if (result.state == 'true' || result.state == true) {
						showMessage("刪除成功");
						$("#videoId").val("");
						$("#videoName"+videoId).remove();
						$("#uploadViodeSpan").show();
						$("#DelViodeSpan").hide();
					}else{
						showMessage("刪除异常");
					}
				},
			})
		}
	});
	
	
})




