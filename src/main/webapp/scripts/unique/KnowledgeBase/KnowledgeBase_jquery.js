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
	var searchURL = "/content/serachKnowledgeBase.htm";
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
function addView(id) {
	visitUrl(ctxPath + "/content/goSaveKnowledgeBasePage.htm?id="+id, 1, 1, 1);
}
//切换到添加菜单页面
function editView(id) {
	console.log(id)
	visitUrl(ctxPath + "/content/goEditKnowledgeBasePage.htm?id="+id, 1, 1, 1);
}
function deleteById(id) {
	
	$.messager.confirm('消息确认', "确定要删除这条记录？", function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : ctxPath+"/content/deleteKnowledgeBaseById.htm", //doDeleteServiceType
				data : "id=" + id,
				cache : false,
				success : function(data) {
					doSearch(true);
					showMessage('删除成功');
				},
				error : function() { // 调用失败
					showMessage('删除失败，请检查网络是否正常');
				},
				complete : function(XHR, TS) {
					XHR = null;
				}
			});
		}
	});
}


