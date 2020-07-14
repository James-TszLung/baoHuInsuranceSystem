$(function() {
	$("#reply").validatebox({
		required : true,
		missingMessage : '最多100个字',
		validType : 'length[1,100]'
	});
});

// 新增页面返回
$("#backBtn").click(function() {
	visitUrl(ctxPath + "/product/essayLeavingMsgPage.htm", 1, 1, 1);
});

function audit(id, status) {
    var replyVal=$("#reply").val();
    if(status==4 && replyVal==''){
    	return showMessage("请填写回复内容");
    }
	$.messager.confirm('消息确认', "确定要操作这条记录？", function(r) {
		if (r) {
			$.ajax({
				url : ctxPath + '/product/doAudit.htm',
				data : {
					id : id,
					status : status,
					replyVal:replyVal
				},
				cache : false,
				success : function(d) {
					console.log(d.success);
					if (d.success == 1 || d.success == '1') {
						visitUrl(ctxPath + "/product/essayLeavingMsgPage.htm", 1, 1, 1);
					} else {
						showMessage("异常");
					}
				},
				error : function() {
					showMessage("请求失败！");
				}
			})
		}
	});
}
