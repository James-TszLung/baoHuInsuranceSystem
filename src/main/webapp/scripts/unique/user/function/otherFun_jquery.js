var funTreeData;
/**
 * 页面初始化方法
 */
function initPage(){
	//初始化功能所在位置title
	if(nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1]);
	else
		$("#page_loc_title").html(nowLoc[2]);
	$("input[name='parentMenu']").each(function(){
		$(this).combotree({
			url:ctxPath+"/function/queryAllFun.htm?mode=hasroot&type=0",
			method:'post',
			required:false,
			onLoadSuccess:function(node,data){
				if(!funTreeData)
					funTreeData = data;
			},
			onChange:function(newID,oldID){
				var t = $(this).combotree('tree');
				var n = t.tree('getSelected');
				$("#level_by_parent").textbox('setValue',n.level+1);
			}
		});
	});
	doSearch(true);
	
	$("input[name='funPale']").combobox({
		data:apply_lv,
		multiple:true,
		multiline:true,
		panelHeight:'auto',
		valueField:'id',
		textField:'text'
	});
}
function openAddUserWin(idx) {
	if(idx!=null)eoa=idx
	else {
		eoa='add';
		$("#add_function_win").window({title:"新增请求权限"});
		$("#add_function_form").form("reset");
	}
	$('#add_function_win').window('open');
}
function closeAddUserWin() {
	$('#add_function_win').window('close');
}
/**
 * 新增-提交表单
 */
var urls = {add:"/function/addFunction.htm",edit:"/function/editFunction.htm"};
var eoa = 'add';//add:新增；edit:修改
function submitAddForm(){
	$("#add_function_form").form("submit",{
		url: ctxPath+urls[eoa],
		success: function(d){
			d=eval("("+d+")");
			if(d==true){
				showMessage("保存成功！");
				closeAddUserWin();
				doSearch(false);
			}else
				showMessage("保存失败！");
		},
		error:function(){
			showMessage("请求失败！");
		}
	});
}

/**
 * 读取要修改的数据并打开修改窗口
 * @param gid
 */
function loadEditingData(gid){
	$.getJSON(
		ctxPath+'/function/getFunctionByID.htm',
		{functionID:gid},
		function(d){
			var pale = d.funPale.split(',');
			d.funPale = pale;
			$("#add_function_form").form('load',d);
			$("#add_function_win").window({title:"修改请求权限"});
			openAddUserWin('edit');
		}
	);
}

/**
 * 分页查询方法true为第一次查询分页查询，false为下一页等操作用
 * @param f
 * @returns
 */
var lastPostData;
function doSearch(f){
	var searchURL = "/function/searchFunction.htm";
	if(f){
	    var url = ctxPath+searchURL;
	    lastPostData = $('#function_search_from :input').serializeArray();
	    $("#results").load(url, lastPostData);
	}else{
		var url = ctxPath+searchURL;
		var postData = lastPostData.concat($('#resultList :input').serializeArray());
	    $("#results").load(url, postData);
	}
}