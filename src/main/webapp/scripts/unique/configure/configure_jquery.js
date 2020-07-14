 var selectDays = new Array() ; 
function initPage() {
	
	
	$("#cost").validatebox({
		required : true,
		validType : 'decimal[6,2]',
		invalidMessage : "金额无效"
	});
	$("#appointmentDay").validatebox({
		required : true,
		missingMessage : '最多5位数',
		validType : 'length[1,5]'
	})
		$("#timeNum").validatebox({
		required : true,
		missingMessage : '最多5位数',
		validType : 'length[0,5]'
	})
	$('#my97DateIcon').css('display', 'none');
	$('.tj_a').css('display', 'none');
	selectDays=appointmentTimeStr.split(',');
	$("#my97DateIcon").click(function(){
		if(selectDays.length >=5){
		alert("最多选择5个日期");
		return;
		}
		WdatePicker({
		el:'yyTime2',
		onpicked:function(){
			var dateStr =this.value;
		 $("#my97DateIcon").val(dateStr) ;
		   selectDays.push(dateStr);
		 
		 var html = dateStr;
			// 获得图片的个数
			var num = 0;
			$("div[name='picList']").each(function() {
				var idStr = this.id;
				if (idStr != null && idStr != undefined && idStr != '') {
					idStr = idStr.charAt(idStr.length - 1);
					num = parseInt(idStr) + 1;
				}
			})
			value = num;
			// 往图片集的div中添加个子div
			var strs = "";
			strs += "<div class='divPicLs' id='divPicLs" + num
					+ "' name=\"divPicLs\" style=\"overflow:hidden;width:200px;float:left;\">";
			strs += "<div id='picList1" + num + "' name='picList' style=\"width:125px;height:50px;float:left;\"></div>";
			strs += "<input  value='" +dateStr
			+ "'  id='pictureItLsId" + num + "' type=\"text\"  style=\"display:none\"/>";
			strs += "<a style=\"color:#2A9CD5;margin-left:20px;\"  class=\"tj_a\" onclick='delpicLs(" + num + ")'>删除</a>";
			strs += "</div>";
			$("#pictotal").append(strs);
			$("#picList1" + num).html(html);
		 },
		 dateFmt:'yyyy-MM-dd',
		 minDate:'%y-%M-{%d+1}',
		 disabledDates:selectDays
		});
	});
};
// 删除
function delpicLs(num) {
	for ( var i = 0; i < selectDays.length; i++) {
		if(selectDays[i]==$("#pictureItLsId"+num).val()){
			selectDays.splice(i,1);
		}
	}
	$("#divPicLs" + num).remove();
}
// 新增页面返回
$("#cancel_jf").click(function() {
	visitUrl(ctxPath + "/ticketConfigure/ticketConfigurePage.htm", 1, 1, 1);
});
$("#timeNum").change(function(){
	var timeNumVal=$("#timeNum").val();
	$("#dayNum").val(timeNumVal*4);
});



// 单次复选框的处理
function docheck(s, obj) {
	if ($(obj).is(':checked')) {
		$("#" + s).val(1);
		$("#dcsz").css("display", "block");
	} else {
		$("#" + s).val(2);
		$("#dcsz").css("display", "none");
	}
}

// 服务复选框的处理
function dochecks(s, obj) {
	if ($(obj).is(':checked')) {
		$("#" + s).val(1);
		$("#tcsz").css("display", "block");
	} else {
		$("#" + s).val(2);
		$("#tcsz").css("display", "none");
	}
}

// 切换到编辑菜单页面

$("#editebtn").click(function() {
	visitUrl(ctxPath + "/divide/editePage.htm?id=" + id, 1, 1, 1);
});

// 保存或修改服务类型的数据
$("#save_jf").click(function() {
	if ($('#addform').form('enableValidation').form('validate') == false) {
		return;
	}
	var mainloading = new ol.loading({
		id : "addDiv",
		loadingClass : "main_loading"
	});
	console.log(selectDays);
	var timeStr="";
	for ( var i = 0; i < selectDays.length; i++) {
		timeStr+=selectDays[i];
		timeStr+=",";
	}
	timeStr=timeStr.substring(0,timeStr.length - 1);
	$("#appointmentTime").val(timeStr);
	$("#addform").form("submit", {
		url : ctxPath + "/ticketConfigure/updateTicketConfigure.htm",
		success : function(data) {
				// $("#backBtn").click();
				showMessage("保存成功");
				visitUrl(ctxPath + "/ticketConfigure/ticketConfigurePage.htm", 1, 1, 1);
		},
		error : function() {
			showMessage("请求失败！");
		}
	});

});
