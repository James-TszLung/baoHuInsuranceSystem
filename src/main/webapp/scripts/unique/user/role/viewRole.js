$(function() {
	$("#roleName").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : 'length[1,50]'
	})

	$("#remarkAdd").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : 'length[1,50]'
	})
});

var urls = {
	view : "/role/viewRole.htm",
	edit : "/role/editRole.htm"
};
var eoa = 'view';// view:编辑

function add() {
	$("#addform").form("submit", {
		url : ctxPath + "/role/editRole.htm",
		success : function(data) {
			data = eval("(" + data + ")");
			if (data == "true" || data == true) { // 保存成功
				$("#backBtn").click();
				showMessage("保存成功");
				visitUrl(ctxPath + "/role/rolePage.htm", 1, 1, 1);
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
	visitUrl(ctxPath + "/role/rolePage.htm", 1, 1, 1);
});
