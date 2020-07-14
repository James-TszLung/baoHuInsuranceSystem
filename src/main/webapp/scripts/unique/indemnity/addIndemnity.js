$(function() {
	$("#content").each(function() {
		$(this).validatebox({
			required : true
		});
	});
	$("input[name$=name]").each(function() {
		$(this).validatebox({
			required : true
		});
	});
	// 返回操作
	$("#backBtn").click(function() {
		visitUrl(ctxPath + "/indemnity/indemnityPage.htm", 1, 1, 1);
	});

	// 保存操作
	$("#addBtn").click(function() {
		if ($('#addform').form('enableValidation').form('validate') == false) {
			return;
		}
		var action = $("#addform").attr("action");
		$("#addform").form("submit", {
			url : ctxPath + action,
			success : function(res) {
				var data = jQuery.parseJSON(res);
				showMessage(data.mes);
				if (data.success == "1") {
					visitUrl(ctxPath + "/indemnity/indemnityPage.htm", 1, 1, 1);
				}
			},
			error : function() {
				showMessage("请求失败！");
			}
		});
	});
})

//新增子项目
function addServicePro() {
	var num = -1;
	if ($("div[name=qtdiv]").last() != null && $("div[name=qtdiv]").last() != undefined
		&& $("div[name=qtdiv]").last().attr("index") != undefined) {
		num = $("div[name=qtdiv]").last().attr("index");
	}
	num = parseInt(num) + 1;
	var str = '';
	str += '<div id="qtdiv' + num + '"  class="qtdiv"  name="qtdiv" index="'+ num + '" style="width:100%;overflow:hidden;">';
	str +='<span class="form-block" >';
	str +='<input name="indemnitySubLs[' + num + '].name" type="text" maxlength="50" /> : <input name="indemnitySubLs[' + num + '].sort"  type="number">';
	str +='</span>&nbsp&nbsp';
	str +='<a onclick="delqt(' + num + ')" style="color:#2A9CD5">删除</a>';
	str +='</div>&nbsp';
	$("#fwphtd").append(str);
	$("input[name$=name]").each(function() {
		$(this).validatebox({
			required : true
		});
	});
}

// 新增页面删除单品
function delqt(index) {
	var str = "qtdiv" + index;
	$("#" + str).remove();
}