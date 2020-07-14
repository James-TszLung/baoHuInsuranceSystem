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
 * @param f
 * @returns
 */
var lastPostData;
function doSearch(f) {
	var searchURL = "/role/searchRole.htm";
	if (f) {
		var url = ctxPath + searchURL;
		lastPostData = $('#role_search_from :input').serializeArray();
		$("#results").load(url, lastPostData);
	} else {
		var url = ctxPath + searchURL;
		var postData = lastPostData.concat($('#resultList :input')
				.serializeArray());
		$("#results").load(url, postData);
	}
}
/**
 * 全选/全不选 注:datagrid的id号要对上
 */
function selectAll() {
	var checks = $('#result_grid').datagrid('getChecked');
	if (checks.length <= 0)
		$('#result_grid').datagrid('selectAll');
	else
		$('#result_grid').datagrid('unselectAll');
}

/**
 * 停用/启用被选中行的数据
 */
function editStatusBySelect(sts, id) {
	var checks = $('#result_grid').datagrid('getChecked');
	if (checks.length <= 0) {
		if (id == null)
			$.messager.alert('错误', '必须至少选择一行！');
	} else {
		var _s = sts == null ? "N" : sts;
		var _id = id == null ? getIDs(checks) : id;
		$.ajax({
			url : ctxPath + '/role/setRoleStatus.htm',
			data : {
				newStatus : _s,
				roleIDs : _id
			},
			cache : false,
			success : function(d) {
				d = eval("(" + d + ")");
				if (d == true) {
					showMessage("状态修改成功！");
					$('#add_role_win').window('close');
					doSearch(false);
				} else
					showMessage("状态修改失败！");
			},
			error : function() {
				showMessage("请求失败！");
			}
		});
	}
}
function getIDs(checks) {
	var str = "";
	for ( var i = 0; i < checks.length; i++) {
		if (i > 0)
			str += ",";
		str += checks[i].roleID;
	}
	return str;
}

function openAddUserWin(idx) {
	if (idx != null)
		eoa = idx
	else {
		eoa = 'add';
		$("#add_role_win").window({
			title : "新增角色"
		});
		$("#add_role_form").form("reset");
	}
	$('.display_div').css('display', 'block');
	$('#add_role_win').window('open');
}
function closeAddUserWin() {
	$('#add_role_win').window('close');
}
/**
 * 新增-提交表单
 */
var urls = {
	add : "/role/addRole.htm",
	edit : "/role/editRole.htm"
};
var eoa = 'add';// add:新增；edit:修改
function submitAddForm() {
	$("#add_role_form").form("submit", {
		url : ctxPath + urls[eoa],
		success : function(d) {
			d = eval("(" + d + ")");
			if (d == true) {
				showMessage("保存成功！");
				$('#add_role_win').window('close');
				doSearch(false);
			} else
				showMessage("保存失败！");
		},
		error : function() {
			showMessage("请求出错！");
		}
	});
}

/**
 * 读取要修改的数据并打开修改窗口
 * 
 * @param gid
 */
function loadEditingData(gid) {
	$.getJSON(ctxPath + '/role/getRoleByID.htm', {
		roleID : gid
	}, function(d) {
		var pale = d.rolePale.split(',');
		d.rolePale = pale;
		$("#add_role_form").form('load', d);
		$("#roleID").val(gid);
		$("#add_role_win").window({
			title : "修改角色"
		});
		openAddUserWin('edit');
	});
}

/**
 * 删除
 * 
 * @param roleName
 * @param roleId
 */
function deleteRole(roleName, roleId) {
	$.messager.confirm('消息确认', "确定要删除‘" + roleName + "’？", function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : ctxPath + "/role/delRole.htm",
				dataType : "json",
				data : {
					roleId : roleId
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
 * 修改
 */
var urls = {
	add : "/role/viewRole.htm",
	edit : "/role/viewRole.htm"
};
var eoa = 'view';// view:编辑
function submitviewForm() {
	$("#view_Role_form").form("submit", {
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

function viewEditingData(id) {
	visitUrl(ctxPath + "/role/viewRole.htm?roleId=" + id, nowLoc[0], nowLoc[1],
			nowLoc[2]);
}

var urls = {
	view : "/role/viewRole.htm",
	edit : "/role/editRole.htm"
};
var eoa = 'view';// view:编辑
function submitviewForm() {
	$("#view_role_form").form("submit", {
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
 * 读取用户组绑定的角色，并打开分配角色窗口
 * 
 * @param gid
 */
function changeRoles(rid) {
	// visitUrl(ctxPath+"/exception/develop.htm",null,null,null);
	visitUrl(ctxPath + "/role/connectRoleFun.htm?roleId=" + rid, nowLoc[0],
			nowLoc[1], nowLoc[2]);
}

// 切换到添加菜单页面
function addView() {
	visitUrl(ctxPath + "/role/addView.htm", 1, 1, 1);
}