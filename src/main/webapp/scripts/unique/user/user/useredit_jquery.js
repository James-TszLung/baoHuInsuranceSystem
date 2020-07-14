$(function() {
	$("#password").validatebox({
		required : true,
		validType : "length[6,16]",
		missingMessage : "6-16位",
	})
	$("#mail").validatebox({
		required : false,
		validType : "length[1,50]",
		missingMessage : "不超过50个字",
	})
	
	$("#account").validatebox({
		required : true,
		validType : "length[1,30]",
	})
	
	$("#account").validatebox({
		required : true,
		invalidMessage : '只允许输入英文字母、数字、',
		validType : 'alphanumeric[1,50]',
	})
	
	$("#roleId").validatebox({
		required : false,
	})
	

});

var submitURL;
var modeText = "新增";

function initMode() {
	var editUser = $("input[name='userId']").val();
	var urls = {
		add : "/usermanage/addUser.htm",
		edit : "/usermanage/editUser.htm"
	};
	submitURL = urls.add;

	if (editUser != null && '' != editUser) {
		$("#uesrId").textbox('readonly');
		submitURL = urls.edit;
		modeText = "修改";
		$("#addEdit_form").form('load',
				ctxPath + '/usermanage/getUserByID.htm?userId=' + editUser);
		var areaId = eval("[" + $("#areaId").val() + "]");
		$("#areaId").combotree("setValues", areaId);
	}
	$("#uesrId").textbox({
		onChange : function(n, o) {
			$("#uesrId").textbox('setValue', n);
		}
	});
	if (nowLoc.length == 2) {
		$("#page_loc_title").html(nowLoc[1] + ' -- ' + modeText);
	} else {
		$("#page_loc_title").html(nowLoc[2] + ' -- ' + modeText);
	}

	$('#roleIdCom').combobox({
		url : ctxPath + '/role/getAllRole.htm',
		valueField : 'roleId',
		textField : 'roleName',
		required : true
	});

}

function initPage() {
	initMode();
	/*
	 * $("input[name='corpID']").combotree({
	 * url:ctxPath+'/baseinfo/corpinfo/getCorpList.htm?status=Y', required:true,
	 * panelHeight:'auto', prompt:'请选择...', height:28, width:140,
	 * onLoadSuccess:function(node, data){ }, onSelect:function(node){
	 * $("#group_id_combo").combobox({
	 * url:ctxPath+'/usergroup/queryGroupList.htm?corpLV='+node.corpLevel,
	 * panelHeight:'auto', prompt:'请选择...', valueField:'id', textField:'text',
	 * editable:false, required:true }); } });
	 */
}

/**
 * 新增-提交表单
 */
function submitAddForm() {
	if ($('#addEdit_form').form('enableValidation').form('validate') == false) {
		return;
	}
	var uName = $("#uesrId").val();
	
	//判断是否重名再添加
	$.ajax({
		url : ctxPath + "/usermanage/isRepeat.htm",
		cache : false,
		type : "POST",
		data : "uName="+uName,
		success : function(data) {
			var result = eval('(' + data + ')');
			if(result){
				$("#isrepeas").text("登录账号已被占用！");
				$("#isrepeas").focus();
				return;
			}else{
				$("#isrepeas").text("");
					$("#addEdit_form").form("submit", {
						url : ctxPath + submitURL,
						cache : false,
						type : "POST",
						success : function(d) {
							d = eval("(" + d + ")");
							if (d == 1) {
								showMessage("保存成功！");
								closeAddUserWin();
							} else
								showMessage("保存失败！");
						},
						error : function() {
							showMessage("请求失败！");
						}
					});
				}
			},
		}, 'json');
}

function closeAddUserWin() {
	visitUrl(ctxPath + "/usermanage/userPage.htm", nowLoc[0], nowLoc[1],
			nowLoc[2]);
}