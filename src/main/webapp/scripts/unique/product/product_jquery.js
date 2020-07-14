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
	var searchURL = "/product/serachProduct.htm";
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
function addView() {
	visitUrl(ctxPath + "/product/goAddProductPage.htm", 1, 1, 1);
}
//修改
function editProduct(id) {
	visitUrl(ctxPath + "/product/goEditProductPage.htm?id="+id, 1, 1, 1);
}
//发布/下线
function pushStatus(id,status,name){
    var fulName = "发布";
    if(status===2){
        fulName = "下线";
    }
    $.messager.confirm('消息确认', "确定要"+fulName+"‘" + name + "’？", function(r) {
        if (r) {
            $.ajax({
                type : "POST",
                url : ctxPath + "/product/editStatusProduct.htm",
                dataType : "json",
                data:{id:id,status:status},
                cache : false,
                success : function(response) {
                    doSearch(false);
                },
                error : function(xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            });
        }
    });
}

//导出
function exportProduct() {
    var url = ctxPath + '/product/exportExcel.htm';
    $('#productForm').attr("action",url);
    $('#productForm').submit();
}
//详情
function showDetail(id) {
    visitUrl(ctxPath + "/product/goDetailProductPage.htm?id="+id, 1, 1, 1);
}

//删除
function deleteById(id) {
    $.messager.confirm('消息确认', "确定要删除这条记录？", function(r) {
        if (r) {
            $.ajax({
                type : "POST",
                url : ctxPath+"/product/deleteproductbyid.htm",
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

