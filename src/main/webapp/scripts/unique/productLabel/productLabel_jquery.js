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
	var searchURL = "/product/serachProductLabel.htm";
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

// 切换到添加菜单页面
function addView() {
	visitUrl(ctxPath + "/product/goAddProductLabelView.htm", 1, 1, 1);
}
function geEditView(id) {
	visitUrl(ctxPath + "/product/goEditProductLabel.htm?id=" + id, 1, 1, 1);
}

//删除
function deleteProductLabel(id) {
	$.messager.confirm('消息确认', "确定要删除吗？", function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : ctxPath + "/product/deleteProductLabel.htm",
				dataType : "json",
				data:{id:id},
				cache : false,
				success : function(res) {
					showMessage(res.mes);
					if (res.success == "1") {
						doSearch(false);
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert(thrownError);
				}
			});
		}
	});
}
