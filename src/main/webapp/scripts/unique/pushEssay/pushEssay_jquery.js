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
	var searchURL = "/content/serachPushEssay.htm";
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
	visitUrl(ctxPath + "/content/goSavepushEssayPage.htm?id="+id, 1, 1, 1);
}
//切换到添加菜单页面
function editView(id) {
	visitUrl(ctxPath + "/content/goEditpushEssayPage.htm?id="+id, 1, 1, 1);
}

function deleteById(id) {
	$.messager.confirm('消息确认', "确定要删除这条推文吗？", function(r) {
		if (r) {
			$.ajax({
				url : ctxPath + "/content/deletePushEssayById.htm",
				data : "id=" + id,
				type : "POST",
				success : function(data) {
					doSearch(true);
				},
			})
		}
	});
}

function saveSort() {
	var rowsData = $('#result_grid').datagrid('getSelections');
	var html = '';
	for (var i = 0; i < rowsData.length; i++) {
		var sort = $('#resultList').find("input[name='sort"+rowsData[i].id+"']").val();
        if(sort==undefined || sort==null || sort==''){
			sort = 0;
		}
		html += '<div style="display: none;"><input type="hidden" name="ids" value="'+rowsData[i].id+'">';
		html += '<input type="hidden" name="sort'+rowsData[i].id+'" value="'+sort+'"></div>'
	}
	$("#sortForm").append(html);

	$("#sortForm").form("submit", {
		url : ctxPath +"/content/updateSort.htm",
		success : function(res) {
			var data = jQuery.parseJSON(res);
			showMessage(data.mes);
			if(data.success==="1"){
				doSearch(true);
			}
		},
		error : function() {
			alert("请求失败！");
		}
	});


}



