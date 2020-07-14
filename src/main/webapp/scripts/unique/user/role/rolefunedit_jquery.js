var submitURL;
var modeText = "分配权限";

function initMode() {
	var editUser = $("input[name='userID']").val();
	var urls = {
		add : "/usermanage/addUser.htm",
		edit : "/usermanage/editUser.htm"
	};
	submitURL = urls.add;
	if (editUser != null && '' != editUser) {
		$("input[name='userID']").prop("readonly", true);
		submitURL = urls.edit;
		modeText = "修改";
		$("#addEdit_form").form('load',
				ctxPath + '/usermanage/getUserByID.htm?userID=' + editUser);
	}
}

function initPage() {
	initMode();
	if (nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1] + ' -- ' + modeText);
	else
		$("#page_loc_title").html(nowLoc[2] + ' -- ' + modeText);

	$("input[name='corpID']").combotree({
		url : ctxPath + '/baseinfo/corpinfo/getCorpList.htm',
		method : 'post',
		required : false,
		height : 28,
		width : 140
	});
}

function closeAddUserWin() {
	visitUrl(ctxPath + "/role/rolePage.htm", nowLoc[0], nowLoc[1], nowLoc[2]);
}

/**
 * 修改角色、分配权限部分
 */
var treeData;
function saveChange() {
	$('#bindFunc').attr("disabled", true);
	var mainloading = new ol.loading({
		id : "addDiv",
		loadingClass : "main_loading"
	});
	mainloading.show();
	var nodes = [];
	for ( var i = 0; i < treeData.length; i++) {
		nodes.push(treeData[i].id);// 1级菜单默认绑定给所有用户
		var halfChecked = $('#t' + i).tree('getChecked', 'indeterminate');// 实心父节点
		var checked = $('#t' + i).tree('getChecked');
		$.each(halfChecked, function() {
			nodes.push(this.id);
		});
		$.each(checked, function() {
			nodes.push(this.id);
		});
	}
	// alert(nodes.join(","));//将数组用逗号分隔成字符串
	$.ajax({
		url : ctxPath + "/role/saveConnection.htm",
		data : "roleId=" + $("#roleId").val() + "&funIDs=" + nodes,
		cache : false,
		success : function(d) {
			$('#bindFunc').attr("disabled", false);
			mainloading.hide();
			d = eval(d);
			if (d == true) {
				showMessage("保存成功！");
				closeAddUserWin();
			} else
				showMessage("保存失败！");
		},
		error : function() {
			$('#bindFunc').attr("disabled", false);
			mainloading.hide();
			showMessage("请求失败！");
		}
	});
}
/**
 * 初始化树列表，并加载数据
 */
var howManyTrees = 0;
function initTree(data) {
	howManyTrees = data.length;
	for ( var i = 0; i < howManyTrees; i++) {
		var oldHtml = $('#treediv').html();
		// <label class="single_tree_all_checker"
		// onclick="selectAllTrees('+i+')">全选/反选</label>
		var addHtml = '<li class="tileLi" style="height:230px;"><p><h2>' + data[i].text
				+ '</h2><ul id="t' + i + '" class="easyui-tree"></ul></p><li>';
		$('#treediv').html(oldHtml + addHtml);
	}
	// 初始化每个tree，并加载数据
	for ( var n = 0; n < howManyTrees; n++) {

		$('#t' + n).tree({
			data : data[n].children,
			checkbox : true,
			animate : true,
			lines : true,
			formatter : function(node) {
				// if(node.typeID=='0')
				return node.text;
				/*
				 * else if(node.typeID=='1') return "<label
				 * class='com-tree-txticon'>功</label>&nbsp;" + node.text; else
				 * if(node.typeID=='2') return "<label
				 * class='com-tree-txticon'>隐</label>&nbsp;" + node.text;
				 */
			},
			fccc : true
		// 可以使得父节点勾选复选框时，子节点不被全选（自编功能）
		});
	}
}
/**
 * 全选/反选相关
 */
var allSelected = false;
function selectAllTrees(id) {
	for ( var n = 0; n < howManyTrees; n++) {
		var roots = $('#t' + n).tree('getRoots');
		if (id != null && id != n)
			continue;
		allChildSelected('#t' + n, roots);
	}
	allSelected = !allSelected;
}
function allChildSelected(treeID, roots) {
	for ( var i = 0; i < roots.length; i++) {
		var node = $(treeID).tree('find', roots[i].id);// 查找节点
		if (allSelected)
			$(treeID).tree('uncheck', node.target);
		else
			$(treeID).tree('check', node.target);// 将得到的节点选中
		if (node.children.length > 0)
			allChildSelected(treeID, node.children);
	}
}
//
$(function() {
	$.ajax({
		url : ctxPath + "/function/queryAllFun.htm",
		data : {
			roleId : $("#roleId").val()
		},
		cache : false,
		success : function(d) {
			treeData = d;
			initTree(d);
		}
	});
});