/**
 * 页面初始化
 */
function initPage() {
	// 初始化功能所在位置title
	if (nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1]);
	else
		$("#page_loc_title").html(nowLoc[2]);

	doSearch(true);

}
/**
 * 分页查询方法true为第一次查询分页查询，false为下一页等操作用
 * 
 * @param
 * @returns
 */
var lastPostData;
function doSearch(f) {
	var searchURL = "/product/serachEssayLeavingMsg.htm";
	if (f) {
		var url = ctxPath + searchURL;
		lastPostData = $('#tgySteward_search_from :input').serializeArray();

		$("#resultList").load(url, lastPostData);
	} else {
		var url = ctxPath + searchURL;
		var postData = lastPostData.concat($('#resultList :input').serializeArray());
		$("#resultList").load(url, postData);
	}
}



//切换到添加菜单页面
function detail(id) {
	visitUrl(ctxPath + "/product/detailEssayLeavingMsgPage.htm?id="+id, 1, 1, 1);
}

function audit(id, status) {
	$.messager.confirm('消息确认', "确定要審核这条记录？", function(r) {
		if (r) {
			$.ajax({
				url : ctxPath + '/product/doAudit.htm',
				data : {
					id : id,
					status : status,
					nopassReason : nopassReason,
					userId : userId,
					busstewardId : busstewardId
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


