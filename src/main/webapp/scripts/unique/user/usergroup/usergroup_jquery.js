/**
 * 页面初始化方法
 */
function initPage(){
	//初始化功能所在位置title
	if(nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1]);
	else
		$("#page_loc_title").html(nowLoc[2]);
	
	doSearch(true);
}
/**
 * 分页查询方法true为第一次查询分页查询，false为下一页等操作用
 * @param f
 * @returns
 */
var lastPostData;
function doSearch(f){
	var searchURL = "/usermanage/searchGroup.htm";
	if(f){
	    var url = ctxPath+searchURL;
	    lastPostData = $('#group_search_from :input').serializeArray();
	    $("#results").load(url, lastPostData);
	}else{
		var url = ctxPath+searchURL;
		var postData = lastPostData.concat($('#resultList :input').serializeArray());
	    $("#results").load(url, postData);
	}
}

/**
 * 全选/全不选
 * 注:datagrid的id号要对上
 */
function selectAll(){
	var checks = $('#result_grid').datagrid('getChecked');
	if(checks.length <= 0)
		$('#result_grid').datagrid('selectAll');
	else
		$('#result_grid').datagrid('unselectAll');
}

/**
 * 停用/启用被选中行的数据
 */
function editStatusBySelect(sts,id){
	var checks = $('#result_grid').datagrid('getChecked');
	if(checks.length <= 0){
		if(id==null)
			$.messager.alert('错误','必须至少选择一行！');
	}else{
		var _s = sts==null?"N":sts;
		var _id = id==null?getIDs(checks):id;
		$.ajax({
			url:ctxPath+'/usermanage/setGroupStatus.htm',
			data:{newStatus:_s,groupIDs:_id},
			cache:false,
			success:function(d){
				d=eval("("+d+")");
				if(d==true){
					showMessage("状态修改成功！");
					doSearch(false);
				}else
					showMessage("状态修改失败！");
			},
			error:function(){
				showMessage("请求失败！");
			}
		});
	}
}
function getIDs(checks){
	var str = "";
	for(var i=0; i<checks.length; i++){
		if(i>0)
			str += ","; 
		str += checks[i].groupID;
	}
	return str;
}

function openAddUserWin(idx) {
	if(idx!=null)eoa=idx
	else {
		eoa='add';
		$("#add_group_win").window({title:"新增用户组"});
		$("#add_group_form").form("reset");
	}
	$('.display_div').css('display','block');
	$('#add_group_win').window('open');
}
function closeAddUserWin() {
	$('#add_group_win').window('close');
}
/**
 * 新增-提交表单
 */
var urls = {add:"/usermanage/addGroup.htm",edit:"/usermanage/editGroup.htm"};
var eoa = 'add';//add:新增；edit:修改
function submitAddForm(){
	$("#add_group_form").form("submit",{
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
			ctxPath+'/usermanage/getGroupByID.htm',
			{groupID:gid},
			function(d){
				var pale = d.groupPale.split(',');
				d.groupPale = pale;
				$("#add_group_form").form('load',d);
				$("#groupID").val(gid);
				$("#add_group_win").window({title:"修改用户组"});
				openAddUserWin('edit');
			}
		);
}

/**
 * 读取用户组绑定的角色，并打开分配角色窗口
 * @param gid
 */
function changeRoles(gid,gp){
	visitUrl(ctxPath+"/usermanage/connectGroupRole.htm?groupID="+gid+"&groupPale="+gp,nowLoc[0],nowLoc[1],nowLoc[2]);
}