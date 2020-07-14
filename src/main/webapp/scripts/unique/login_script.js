var _loading;
// 要想使用图片按钮提交表单并用nice valid，必须像下面那样写，用JS原有的name.submit()方法不行
function loginSubmit() {
	var uName = $("#j_username").val();
	if(uName=="" && uName.length<=0){
		$("#faulsts").text("请输入用户名！"); 
		return;
	}
	
	var upass = $("#j_password").val();
	if(upass=="" && upass.length<=0){
		$("#faulsts").text("请输入密码！"); 
		return;
	}
	
	var uCode = $("#code_input").val();
	if(uCode=="" && uCode.length<=0){
		$("#faulsts").text("请输入验证码！"); 
		return;
	}
	
	/*if ($('#reqForm').form('enableValidation').form('validate') == false) {
		return;
	}*/
	_loading.show();
	$("#reqForm").form("submit", {
		url : ctxPath + "/j_spring_security_check",
		cache : false,
		success : function(d) {
			_loading.hide();
			if ("undefined" == typeof d || !d.match("^\{(.+:.+,*){1,}\}$")) {
				_loading.show();
				$("#checkLoginUser").val("false");
				$('#reqForm').submit();
			} else {
				d = eval("(" + d + ")");
				if (d.result == 'false') {
					_loading.show();
					$(document).unbind('keyup');
					$.messager.confirm('提示', d.message, function(r) {
						if (r) {
							$("#isExpiredAnother").val("true");
							$('#reqForm').submit();
						} else {
							$(document).keyup(keyUp13Handler);
							_loading.hide();
						}
					});
				}
			}
		}
	});
}
function keyUp13Handler(event) {
	if (event.keyCode == 13) {
		loginSubmit();
	}
}
$(function() {
	_loading = new ol.loading({
		id : 'mainBody'
	})
	// 回车登录
	$(document).keyup(keyUp13Handler);

	// 绑定-验证码点击刷新
	$("#refreshCode").on("click", function() {
		var append = '?' + new Date().getTime() + 'a' + Math.random();
		$("#valid_code").attr("src", $("#valid_code").attr("src") + append);
	});
	// 为登录表单设置必填验证
	/*$("[name=j_username]").validatebox({
		required : true
	});
	$("[name=j_password]").validatebox({
		required : true
	});
	 $("[name=code_input]").validatebox({
	 required: true
	});*/
});