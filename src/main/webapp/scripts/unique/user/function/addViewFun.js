$(function(){
	$("#funName").validatebox({
		required : true,
		missingMessage : '最多50个字',
		validType : 'length[1,50]'
	})

	$("#funUrl").validatebox({
		required : false,
		validType : "length[1,1024]",
		missingMessage : "最多1024个字符"
	})
	$('#remarkAdd').validatebox({
		required : false,
		missingMessage : '由1~250位字符组成',
		validType : 'length[1,250]'
	});
});



var urls = {view:"/function/viewFunction.htm",edit:"/function/editFunction.htm"};
var eoa = 'view';//view:编辑
$("#addPro").click(function() {
	addPro();
});
function add(){
	$("#viewform").form("submit",{
			url: ctxPath + "/function/editFunction.htm",
			success : function(data) {
				data = eval("(" + data + ")");
				if (data =="true" || data == true) { // 保存成功
					$("#backBtn").click();
					showMessage("保存成功");
					visitUrl(ctxPath + "/function/functionPage.htm", 1, 1, 1);
				} else { // 保存失败
					showMessage("系统错误！");
				}
			},
			error : function() {
				showMessage("请求失败！");
			}
	});
}

$("#backBtn").click(function() {
	visitUrl(ctxPath + "/function/functionPage.htm", 1, 1, 1);
});
