var funTreeData;
/**
 * 页面初始化方法
 */
function initPage() {
	// 初始化功能所在位置title
	if (nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1]);
	else
		$("#page_loc_title").html(nowLoc[2]);
	$("#parentMenuPage").combotree({
		url : ctxPath + "/function/queryAllFun.htm?mode=hasroot",
		method : 'post',
		required : true,
		onLoadSuccess : function(node, data) {
			if (!funTreeData)
				funTreeData = data;
		},
		onChange : function(newID, oldID) {
			var t = $(this).combotree('tree');
			var n = t.tree('getSelected');
			$("#level_by_parent").textbox('setValue', n.level + 1);
		}
	});
	doSearch(true);

}
function openAddUserWin(idx) {
	if (idx != null)
		eoa = idx
	else {
		eoa = 'add';
		$("#add_function_win").window({
			title : "新增模块"
		});
		$("#add_function_form").form("clear");
		$("#level_by_parent").textbox("setValue", 1);
	}
	$('.display_div').css('display', 'block');
	$('#add_function_win').window('open');
}
function closeAddUserWin() {
	$('#add_function_win').window('close');
}
/**
 * 新增-提交表单
 */
var urls = {
	add : "/function/addFunction.htm",
	edit : "/function/editFunction.htm"
};
var eoa = 'add';// add:新增；edit:修改
function submitAddForm() {
	$("#add_function_form").form("submit", {
		url : ctxPath + urls[eoa],
		success : function(d) {
			d = eval("(" + d + ")");
			if (d == true) {
				showMessage("保存成功！");
				closeAddUserWin();
				doSearch(false);
			} else
				showMessage("保存失败！");
		},
		error : function() {
			showMessage("请求失败！");
		}
	});
}

/**
 * 读取要修改的数据并打开修改窗口
 * 
 * @param gid
 */
function loadEditingData(gid) {
	$.getJSON(ctxPath + '/function/getFunctionByID.htm', {
		functionID : gid
	}, function(d) {
		var pale = d.funPale.split(',');
		d.funPale = pale;
		$("#add_function_form").form('load', d);
		$("#add_function_win").window({
			title : "修改模块"
		});
		openAddUserWin('edit');
	});
}

/**
 * 删除
 * 
 * @param funName
 * @param id
 */
function deleteFunction(funName, id) {
	$.messager.confirm('消息确认', "确定要删除‘" + funName + "’？", function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : ctxPath + "/function/delFunction.htm",
				dataType : "json",
				data : {
					id : id
				},
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

/**
 * 分页查询方法true为第一次查询分页查询，false为下一页等操作用
 * 
 * @param f
 * @returns
 */
var lastPostData;
function doSearch(f) {
	var searchURL = "/function/searchFunction.htm";
	if (f) {
		var url = ctxPath + searchURL;
		lastPostData = $('#function_search_from :input').serializeArray();
		$("#results").load(url, lastPostData);
	} else {
		var url = ctxPath + searchURL;
		var postData = lastPostData.concat($('#resultList :input')
				.serializeArray());
		$("#results").load(url, postData);
	}
}

function viewEditingData(id) {
	visitUrl(ctxPath + "/function/viewFunction.htm?Id=" + id, nowLoc[0],
			nowLoc[1], nowLoc[2]);
}

/**
 * 编辑
 */
var urls = {
	add : "/function/viewFunction.htm",
	edit : "/function/viewFunction.htm"
};
var eoa = 'view';// view:编辑
function submitviewForm() {
	$("#view_function_form").form("submit", {
		url : ctxPath + urls[eoa],
		success : function(d) {
			d = eval("(" + d + ")");
			if (d == true) {
				showMessage("保存成功！");
				closeAddUserWin();
				doSearch(false);
			} else
				showMessage("保存失败！");
		},
		error : function() {
			showMessage("请求失败！");
		}
	});
}

// 切换到添加菜单页面
function addView() {
	visitUrl(ctxPath + "/function/addView.htm", 1, 1, 1);
}