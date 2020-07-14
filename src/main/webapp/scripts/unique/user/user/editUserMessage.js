/**
 * 页面初始化方法
 * 注：所有新页面均要实现该方法，供主模板调用
 */
function initPage(){
}
function saveUserdataInfo(){
	var userID = $("#userDataForm input[name='userID']").val();
	var password = $("#userDataForm input[name='password']").val();
	var oldpassword = $("#userDataForm input[name='oldword']").val();
	var checknewpwd = $("#userDataForm input[name='checknewpwd']").val();
	if($("#userDataForm input[name='password']").length==0){
		password = '';
		oldpassword = '';
		checknewpwd = '';
	}	
	//执行验证	
	validateData();
	if($('#userDataForm').form('validate')){
	//需求改动	
	//执行验证
	//-----------------------------------	
	$.ajax({
		url:ctxPath+"/usermanage/updateUserMessage.htm",
		cache:false,
		data:$("#userDataForm").serialize(),
		method:"post",
		success:function(data){
			var jsonObj = eval("("+data+")");
			if("Y"==jsonObj.flag){
				showMessage("修改成功");
			}else if("S"==jsonObj.flag){
				showMessage("新密码不能和旧密码相同");
			}else if("E"==jsonObj.flag){
				showMessage("密码错误，不能修改密码");
			}else if("T"==jsonObj.flag){
				showMessage("两次新密码输入不一致");
			}else{
				showMessage("修改失败");
			}
		},
		error:function(){
			showMessage("修改失败");
		}
	});}
}

function validateData(){
	$.extend($.fn.validatebox.defaults.rules, {
		checkPwdLength: {
	        validator: function(value, param){
	            return /^[0-9a-zA-Z]{6,12}$/i.test(value);
	        },
	        message: '由6~12位数字或大小写字母组成'
	    },
	    checkTwoNewPwd:{
	    	validator: function(value, param){
	            var password = $("#userDataForm input[name='password']").val();
	            var checknewpwd = $("#userDataForm input[name='checknewpwd']").val();
	            return checknewpwd==password;
	        },
	        message: '两次新密码输入不一致'
	    }
	});
	//新密码和确认密码验证
	$("#userDataForm input[name='password']").validatebox({    
		validType:['checkPwdLength','checkTwoNewPwd']
	});
	$("#userDataForm input[name='checknewpwd']").validatebox({    
		validType:['checkPwdLength','checkTwoNewPwd']
	});
}