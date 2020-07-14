$(function() {
	$("#name").validatebox({
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
	// 新增
	$("#addform").form("submit", {
		url : ctxPath + "/configuration/dosaveDictionary.htm",
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
	visitUrl(ctxPath + "/configuration/dictionaryPage.htm", 1, 1, 1);
});


	






function addother(){
	var num = $("div[name='qtdiv']").length;
	var num = -1;
    console.log($("div[name=qtdiv]").last().attr("index"));
	if ($("div[name=qtdiv]").last() != null && $("div[name=qtdiv]").last() != undefined
			&& $("div[name=qtdiv]").last().attr("index") != undefined) {
		num = $("div[name=qtdiv]").last().attr("index");
	}
	num = parseInt(num) + 1;
	var str = "";
	str += "<div id='qtdiv" + num + "'  class=\"qtdiv\"  name=\"qtdiv\" index='"+ num + "' style=\"width:100%;overflow:hidden;\">";
	str +="<span class=\"form-block\" >";
	str +="<input name='contentLs[" + num + "].content'  type=\"text\" /> : <input name='contentLs[" + num + "].sort'  type=\"number\">";
	str +="</span>&nbsp&nbsp";
	str +="<a onclick=\"delqt('" + num + "')\" style=\"color:#2A9CD5\">删除</a>";
	str +="</div>&nbsp";
	$("#fwphtd").append(str);
	$("input[name$=content]").each(function() {
		$(this).validatebox({
			required : true,
		});
	});

	$("input[name$=sort]").each(function() {
		$(this).validatebox({
			required : true,
		});
	});

}  
//删除其他要求
function delqt(a) {
	var str = "qtdiv" + a;
	$("#" + str).remove();

}

