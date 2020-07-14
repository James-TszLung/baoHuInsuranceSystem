$(function() {	
	
	$("#status").validatebox({
		editable:false,
		panelHeight:'auto'
	})

});

/**
 * 页面初始化方法 注：所有新页面均要实现该方法，供主模板调用
 */
function initPage(){
	// 初始化功能所在位置title
	if(nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1]);
	else
		$("#page_loc_title").html(nowLoc[2]);
	
	/*
	 * $("input[name='corpid']").combotree({
	 * url:ctxPath+'/baseinfo/corpinfo/getCorpList.htm', method:'post',
	 * required:false });
	 */
	
	$("input[name='loginName']").key_press();
	
	doSearch(true);
}
/**
 * 分页查询方法true为第一次查询分页查询，false为下一页等操作用
 * 
 * @param f
 * @returns
 */
var lastPostData;
function doSearch(f){
	var searchURL = "/usermanage/searchUser.htm";
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
 * 全选/全不选 注:datagrid的id号要对上
 */
function selectAll(){
	var checks = $('#result_grid').datagrid('getChecked');
	if(checks.length <= 0)
		$('#result_grid').datagrid('selectAll');
	else
		$('#result_grid').datagrid('unselectAll');
}
function addUser() {
	visitUrl(ctxPath+"/usermanage/editUserPage.htm",nowLoc[0],nowLoc[1],nowLoc[2]);
}
function eidtUser(id){
	visitUrl(ctxPath+"/usermanage/editUserPage.htm?userID="+id,nowLoc[0],nowLoc[1],nowLoc[2]);
}

function viewEditingData(id) {
	visitUrl(ctxPath + "/usermanage/viewUserManage.htm?id=" + id,
			nowLoc[0], nowLoc[1], nowLoc[2]);
}

var urls = {view:"/usermanage/viewUser.htm",edit:"/usermanage/editUser.htm"};
var eoa = 'view';// view:编辑
function submitviewForm(){
	$("#view_UserInfo_form").form("submit",{
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
			url:ctxPath+'/usermanage/setUserStatus.htm',
			data:{newStatus:_s,userIDs:_id},
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

function deleteUser(id,account) {
	$.messager.confirm('消息确认', "确定要删除‘" + account + "’？",
			function(r) {
				if (r) {
					$.ajax({  
					       type: "POST", 
					       url: ctxPath + "/usermanage/delUserManage.htm",
					       dataType:"json",
					       data:{id:id},
					       cache:false,
					       success:function(response){  
					           doSearch(false);
					       },  
					       error:function (xhr, ajaxOptions, thrownError){    
					           alert(thrownError);  
					       }  
					       });
				}
			});
}

function setUser(id,account,status) {
	$.messager.confirm('消息确认', "确定要禁用‘" + account + "’？",
			function(r) {
				if (r) {
					$.ajax({  
					       type: "POST", 
					       url: ctxPath + "/usermanage/setUserManage.htm",
					       dataType:"json",
					       data:{id:id,status:status},					       
					       cache:false,
					       success:function(response){  
					           doSearch(false);
					       },  
					       error:function (xhr, ajaxOptions, thrownError){    
					           alert(thrownError);  
					       }  
					       });
				}
			});
}

function getIDs(checks){
	var str = "";
	for(var i=0; i<checks.length; i++){
		if(i>0)
			str += ","; 
		str += checks[i].userID;
	}
	return str;
}

/**
 * 打开分配用户组的接口
 */
var changingUser;
function openAEWin(uid,gid,clv){
	$('.display_div').css('display','block');
	$('#add_edit_win').window('open');
	changingUser = uid;
	$("#group_id").combotree({
		url:ctxPath+'/usermanage/setUserStatus.htm?corpLevel='+clv,
		method:'post',
		panelHeight:'auto',
		required:false
	});
}
function closeAEWin(){
	$('#add_edit_win').window('close');
}
function submitAEForm(){
// var f = $("#change_group_form");
// f.form("submit",{
// url: ctxPath+'/usermanage/changeGroup.htm',
// success: function(d){
// alert(d);
// d=eval('(' + d + ')');
// if(d==true){
// showMessage("用户组分配成功！");
// closeAEWin();
// doSearch(false);
// }else
// showMessage("用户组分配失败！");
// },
// onLoadError: function(){
// showMessage("请求失败！");
// }
// });
	var tr = $('#group_id').combotree('tree');
	var n = tr.tree("getSelected");
	var url = ctxPath+'/usermanage/changeGroup.htm';
	$.post(url, "groupID="+n.id+"&userID="+changingUser, function (result) {
		var d=eval('(' + result + ')');
		if(d==true){
			showMessage("用户组分配成功！");
			closeAEWin();
			doSearch(false);
		}else
			showMessage("用户组分配失败！");
    });
}
// 下载触发-------xubiao
function downuser(){
	var searchURL = "/usermanage/downUser.htm";
	var url = ctxPath+searchURL;
	$("#downForm").attr("action",url);
    $("#downForm").submit();// from表单去提交
}

/**
 * 修改密码窗口
 */
function updatePwdWin(id){
	console.log($('#updatePwd_win'));
	$('#updatePwd_win').window('open');
	$("#updatePwd_win_id").val(id);
	$("#password").validatebox({
		required : true,
		validType : "length[6,16]",
		missingMessage : "6-16位",
	});
}

function updatePwd() {
	var id = $("#updatePwd_win_id").val();
	var password = $("#password").val();
	if ($('#updatePwdForm').form('enableValidation').form('validate') == false) {
		return;
	}
	console.log();
	$.ajax({
	       type: "POST", 
	       url: ctxPath + "/usermanage/updatePwd.htm",
	       dataType:"json",
	       data:{id:id,password:password},
	       cache:false,
	       success:function(response){
	           doSearch(false);
	           closeUpdatePwdWin();
	           showMessage("密码已更改");
	       },
	       error:function (xhr, ajaxOptions, thrownError){
	           alert(thrownError);  
	           closeUpdatePwdWin();
	       }
	 });
}

function closeUpdatePwdWin(){
	$('#updatePwd_win').window('close');
}