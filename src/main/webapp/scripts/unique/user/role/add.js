$(function() {
	$("#addBtn").click(function() {
		if ($('#addform').form('enableValidation').form('validate') == false) {
			return;
		}
		var mainloading = new ol.loading({
			id : "addDiv",
			loadingClass : "main_loading"
		});
		mainloading.show();
		$("#addform").form("submit", {
			url : ctxPath + "/role/addRole.htm",
			success : function(data) {
				data = eval("(" + data + ")");
				if (data =="true" || data == true) { // 保存成功
					$("#backBtn").click();
					showMessage("保存成功");
					visitUrl(ctxPath+"/role/rolePage.htm",1,1,1);
				} else { // 保存失败
					showMessage("系统错误！");
				}
			},
			error : function() {
				showMessage("请求失败！");
			}
		});
		$("#add_role_form").form("submit",{
			url : ctxPath + "/role/addRole.htm",
			success: function(d){
				d=eval("("+d+")");
				if(d==true){
					showMessage("保存成功！");
					$('#add_role_win').window('close');
					doSearch(false);
				}else
					showMessage("保存失败！");
			},
			error: function(){
				showMessage("请求出错！");
			}
		});
	});
	$("#backBtn").click(function() {
		visitUrl(ctxPath + "/role/rolePage.htm", 1, 1, 1);
	});

	$.extend($.fn.validatebox.defaults.rules, {
		codeAndNum : {
			validator : function(value, param) {
				return /^[0-9]{1,8}$/i.test(value);
			},
			message : '由1~6位数字组成'
		}
	});

	$("#roleName").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : 'length[1,50]'
	})

	$('#remarkAdd').validatebox({
		required : true,
		missingMessage : '必填 ，由1~250位字符组成',
		validType : 'length[1,250]'
	});

});

// 切换到下单页面
function addPro() {
	var index = parseInt($("#indexs").val()) + 1;
	$("#indexs").val(index);
	var html = "<div id='cabinDiv"
			+ index
			+ "' style='margin-top:10px'><span>产品名称: </span>"
			+ "<input type='text' name='preOrderList["
			+ index
			+ "].productName' id='productNameAdd"
			+ index
			+ "' class='textbest' style='width:400px;height:32px; margin-left:0px;' />"
			+ "<span>&nbsp;属性： </span>"
			+ "<select name='preOrderList["
			+ index
			+ "].attribute' style='width:80px;height:32px; margin-left:0px;'><option value='1'>大公仔</option><option value='2'>小公仔</option></select>"
			+ "<span>&nbsp;数量：   </span>"
			+ "<input type='text' name='preOrderList["
			+ index
			+ "].num' id=numAdd"
			+ index
			+ "  class='textbest' style='width:60px;height:32px; margin-left:0px;' />"
			+ "<span>&nbsp;单价：   </span>"
			+ "<input type='text' name='preOrderList["
			+ index
			+ "].price' id=priceAdd"
			+ index
			+ "  class='textbest' style='width:60px;height:32px; margin-left:0px;' />"
			+ "<span style='color: red;'>&nbsp;&nbsp;*</span><input type='button' onclick='remPro("
			+ index + ")' value='移除' style='width:30px;'/>" + "</div>";
	$("#cabinDiv").append(html);
	$("#productNameAdd" + index).textbox({
		required : true,
		missingMessage : '必填 ，由1~30位字符组成',
		validType : 'length[1,30]'
	})

	$("#numAdd" + index).textbox({
		required : true,
		validType : 'codeAndNum'
	})

	$("#priceAdd" + index).textbox({
		required : true,
		validType : "decimal[8,2]",
		invalidMessage : "金额无效"
	})
}

function remPro(index) {
	$('#cabinDiv' + index).remove();
}
