/**
 * 页面初始化
 */
function initPage() {
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
	var searchURL = "/indemnity/serachIndemnity.htm";
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

//新增
function addView() {
	visitUrl(ctxPath + "/indemnity/goAddIndemnityPage.htm", 1, 1, 1);
}
//修改
function editView(id) {
	visitUrl(ctxPath + "/indemnity/goEditIndemnityPage.htm?id="+id, 1, 1, 1);
}

//修改
function copyData(id) {
	$.messager.confirm('消息确认', "确定要复制吗？", function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : ctxPath + "/indemnity/copyIndemnity.htm",
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
//删除
function deleteIndemnity(id) {
	$.messager.confirm('消息确认', "确定要删除吗？", function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : ctxPath + "/indemnity/deleteIndemnity.htm",
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



