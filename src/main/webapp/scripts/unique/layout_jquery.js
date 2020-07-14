function initPage(){
	
}

// 非正常退出页面（关闭浏览器）时，退出登录
//UnloadConfirm.MSG_UNLOAD = "数据1尚未保存，离开后可能会导致数据丢失\n\n您确定要离开吗？";
//UnloadConfirm.set = function(a) {
//    window.onbeforeunload = function(b) {
//        b = b || window.event;
//        b.returnValue = a;
//        return a
//    }
//};
//UnloadConfirm.clear = function() {
//    window.onbeforeunload = function() {}
//};
//UnloadConfirm.set(UnloadConfirm.MSG_UNLOAD);
//
var cityListOpts = {
		url:ctxPath+"/baseinfo/iata/queryCityAirPort.htm",
		valueField:'iataID',
		textField:'cityCname',
		formatter:function(row){//显示文本格式化
			return row.iataID+' '+row.cityCname;
		},
		filter:function(q,row){//输入过滤查询
			var id = row.iataID;
			var text = row.cityCname;
			if(id.indexOf(q.toUpperCase())>=0
					|| text.indexOf(q)>=0)
				return true;
			else
				return false;
		}
};
var ailinesOpts = {
	url:ctxPath+"/baseinfo/airlines/queryAirlineList.htm",
	valueField:'airlineCode',
	textField:'airlineCsname',
	formatter:function(row){//显示文本格式化
		return row.airlineCode +' '+ row.airlineCsname;
	},
	onLoadSuccess: function () { //加载完成后,设置选中第一项
        var val = $(this).combobox("getData");
        for (var item in val[0]) {
            if (item == "airlineCode") {
                $(this).combobox("select", val[0][item]);
            }
        }
    },
	filter:function(q,row){//输入过滤查询
		var id = row.airlineCode;
		var text = row.airlineCsname;
		if(id.indexOf(q.toUpperCase())>=0
				|| text.indexOf(q)>=0)
			return true;
		else
			return false;
	}
};

/**
 * 刷新或关闭页面前事件
 */
window.onbeforeunload = function(){
	// 刷新页面前，要先关闭正在请求的ajax
	multiAjaxAbort();
};
// JavaScript Document
var _change_speed = 250;
function w(vd) {
	var ob = document.getElementById(vd);
	if($("#"+vd).children().length>0){
		if (ob.style.display == "block" || ob.style.display == "")
//		$("#"+vd).hide(_change_speed);
			var ob2 = document.getElementById('s' + vd);
		else
//		$("#"+vd).show(_change_speed);
			var ob2 = document.getElementById('s' + vd);
		$("#"+vd).stop().animate({
			height: 'toggle', opacity: 'toggle'
		}, _change_speed);
	}
}
function k(vd) {
	var ob = document.getElementById(vd);
	if($("#"+vd).children().length>0){
		if (ob.style.display == "block")
			var ob2 = document.getElementById('s' + vd);
		else
			var ob2 = document.getElementById('s' + vd);
		$("#"+vd).stop().animate({
			height: 'toggle', opacity: 'toggle'
		}, _change_speed);
	}
}
function m(vd) {
	var ob = document.getElementById(vd);
	if (ob.style.display == "block")
		var ob2 = document.getElementById('s' + vd);
	else
		var ob2 = document.getElementById('s' + vd);
	$("#"+vd).stop().animate({
		height: 'toggle', opacity: 'toggle'
	}, _change_speed);
}
/**
 * 左侧菜单鼠标划入划出特效
 * @param id
 * @param b
 */
function menu_mouseoverhandler(id, b) {
	if (b == true){
//		$("#" + id).addClass("left-menu-mouseover");
//		$("#" + id).animate({'background-color':'#efefef','color':'red','borderRightColor':'red'});
	}else{
//		$("#" + id).removeClass("left-menu-mouseover");
//		$("#" + id).animate({'background-color':'white','color':'black','borderRightColor':'white'});
	}
//	if (b == true){
//		if(!$("#" + id).hasClass("left_menu_selected"))
//			$("#" + id).animate({'background-color':'#EFEFEF','color':'red','borderRightColor':'red','border-right-width':'3px'});
//	}else{
//		if(!$("#" + id).hasClass("left_menu_selected"))
//			$("#" + id).animate({'background-color':'white','color':'black','borderRightColor':'white','border-right-width':'0'});
//	}
}
/**
 * 绑定需要自动调整尺寸的datagrid，如果传第二个参数，则视为清空所有已绑定对象
 */ 
var $dgs = [];//panel中的easyui-datagrid对象
function autoResizeDG(dg,doClear){
	if(doClear)
		$dgs = [];
	else
		$dgs.push(dg);
}
/**
 * 根据url获取相应页面html，然后插入到主页面的指定div中。
 * 当参数过多时，请另外创建一个json对象，并传递给此方法的params。
 */
var nowLoc;
var lastSelectedMenu;
function visitUrl(url, p1, p2, p3, idStr, params) {
	// 跳转页面前，要先关闭正在请求的ajax
	multiAjaxAbort();
	// 清除$dg对象
	autoResizeDG(null,true);
	if(url.indexOf("/")!=0){
		if(url.indexOf("http://")==0)
			window.open(url);
		else
			window.open("http://"+url);
	}else{
		// 先用ajax请求测试是否有权限
//		$.ajax({
//			url:url,
//			success:function(){
				$("#center_page").panel({  
				    href:url,
				    cache:false,
				    method:'POST',
				    queryParams:params?params:{},
				    loadingMessage:"正在加载中...",
				    onLoad:function(){
				    	//通用处理
				    	var locText = "您的位置：首页 >> " + p1 + " >> " + p2;
						nowLoc=[p1,p2];
						if(p3 != null && p3!='' && typeof(p3)!='undefined'){
							locText += " >> " + p3;
							nowLoc[2]=p3;
						}
						if(idStr!=null){
							$("#user_location").html(locText);
							if (lastSelectedMenu)
								lastSelectedMenu.removeClass("left_menu_selected");
							$("#" + idStr).addClass("left_menu_selected");
							lastSelectedMenu = $("#" + idStr);
						}
						//特殊处理-调用每个被加载页面中的初始化方法initPage
					    initPage();  
				    },
				    onLoadError:function(){
				    	alert("请求出错或超时！");
				    },
				    onResize:function(width, height){
				    	$.each($dgs,function(i,dg){
				    		dg.datagrid('resize');
				    	});
				    }
				});
//			}
//		});
	}
	// location.href = url;
}
/**
 * 窗口大小改变事件
 */
function window_resizeHandler(){
	if($('#result_grid'))
		$('#result_grid').datagrid('resize');
}

/**
 * 定时修改密码窗口保存事件
 */
function submitChangePwd(){
	$("#change_pwd_form").form("submit",{
		url: ctxPath+"/usermanage/editUser.htm",
		cache:false,
		type:"POST",
		success: function(d){
			d=eval("("+d+")");
			if(d.result==true || d==true){
				alert("修改成功，自动退出登录！");
				// 修改成功后退出登录
				logout_clickhandler();
			}else
				showMessage("修改失败，可能验证码有误！");
		},
		error: function(){
			showMessage("发送请求失败！");
		}
	});
}

/**
 * 发送验证码短信
 */
function sendValidCode(){
	$.ajax({
		   type: "POST",
		   url: ctxPath + "/usermanage/getValidCode.htm",
		   cache: false,
		   success: function(d){
			   d = JSON2.parse(d);
			   alert( d.msg );
		   }
	});
}

/**
 * 将退改签代码转换成中文
 * 例： :-24:5%;-24:-2:10%;-2:2:20%;2::40% 截取第一段:-24:5%传入方法
 * 如果type是name转换成起飞前24小时, 如果是value转换成5%
 */
function parseAirRuleToCN(code, type){
	code = code.replace(/\s/g, "");
	var arr = code.split(":");
	var rs = "";
	
	switch (type) {
		case "name":
			if(arr[0]=="" && arr[1]!=""){// 起飞前N小时
				rs = "起飞前" + arr[1].substring(1) + (arr[1].indexOf("!") > -1 ? "（不含）":"（含）") + "小时";
			}else if(arr[0]!="" && arr[1]!=""){
				
				if(arr[1].indexOf("-")==0){// 起飞前N小时至N小时
					rs = "起飞前" + arr[0].substring(1) + (arr[0].indexOf("!") > -1 ? "（不含）":"（含）") + "小时至" 
						+ arr[1].substring(1) + (arr[1].indexOf("!") > -1 ? "（不含）":"（含）") + "小时";
				}else if(arr[0].indexOf("-")==0
						&& arr[1].indexOf("-")!=0){// 起飞前N小时至起飞后N小时
					rs = "起飞前" + arr[0].substring(1) + (arr[0].indexOf("!") > -1 ? "（不含）":"（含）") + "小时至起飞后"
						+ arr[1] + (arr[1].indexOf("!") > -1 ? "（不含）":"（含）") + "小时";
				}else if(arr[0].indexOf("-")!=0){// 起飞后N小时至N小时
					rs = "起飞后" + arr[0] + (arr[0].indexOf("!") > -1 ? "（不含）":"（含）") + "小时至"
						+ arr[1]  + (arr[1].indexOf("!") > -1 ? "（不含）":"（含）") + "小时";
				}
				
			}else if(arr[0]!="" && arr[1]==""){// 起飞后N小时
				rs = "起飞后" + arr[0] + (arr[0].indexOf("!") > -1 ? "（不含）":"（含）") + "小时";
			}
			break;
	
		case "value":
			var valArr = arr[2].split(",");
			for(var i=0; i<valArr.length; i++){
				if(valArr[i].indexOf("*") > -1){
					var limitArr = valArr[i].split("*");
					if(limitArr[0] == "0")
						rs += limitArr[1] + "次内免费改签";
					else if(limitArr[0] == "!")
						rs += limitArr[1] + "次后（不含）不允许改签";
					else if(limitArr[0].indexOf("%") > -1){
						if(limitArr[1].indexOf("-") > -1)
							rs += limitArr[1] + "次后（不含）收取票面价" + limitArr[0] + "手续费";
						else
							rs += limitArr[1] + "次内（含）收取票面价" + limitArr[0] + "手续费";
					}else if(limitArr[0].indexOf("%") == -1){
						if(limitArr[1].indexOf("-") > -1)
							rs += limitArr[1] + "次后（不含）收取" + limitArr[0] + "元手续费";
						else
							rs += limitArr[1] + "次内（含）收取" + limitArr[0] + "元手续费";
					}
				}else{
					if(valArr[i] == "0")
						rs += "不限次数免费改签";
					else if(valArr[i] == "!")
						rs += "不允许改签";
					else if(valArr[i].indexOf("%") > -1)
						rs += "每次收取票面价" + valArr[i] + "手续费";
					else if(valArr[i].indexOf("%") == -1)
						rs += "每次收取" + valArr[i] + "元手续费";
				}
				if(i<valArr.length-1)
					rs += "，";
			}
			break;
	}
	return rs.replace("!", "").replace("-", "");
}
/**
 * 退出登录
 */
//function logout_clickhandler() {
//	location.href = ctxPath + "/auth/toLogout.htm";
//}
/**
 * 页眉右上角菜单事件
 * 以下顺序与图标尾字母相对应,请将相应图标与功能对应,否则会导致js功能与图标标示不一致
 * 1:国内白屏/2:国际白屏/3:黑屏预订/4:出票中心订单处理/5:网点订单处理/6:呼叫中心/9:退出
 * @param option
 */
function rightUpMenueEvent(option) {
	var index = option.indexOf('.')-1;
	var opt = option.substring(index,index+1); 
	if(opt=='1') {
		//国内白屏
		visitUrl(ctxPath+"/reservation/domestic/whiteScreen/searchPage.htm","机票预订","国内机票","白屏预订","1410");
	} else if(opt=='2') {
		//国际白屏
	} else if(opt=='3') {
		//黑屏预订
		visitUrl(ctxPath+'/reservation/domestic/blackScreen/loadBlackScreenView.htm','机票预订','国内机票','黑屏预订','1411');
	} else if(opt=='4') {
		//出票中心订单处理
	} else if(opt=='5') {
		//网点订单处理
		visitUrl('/baoHuInsuranceSystem/ticketorder/list.htm?mod=net','订单处理','网点订单处理','','s158');
	} else if(opt=='6') {
		//呼叫中心
	} else if(opt=='9') {
		//退出
		location.href = ctxPath + "/auth/toLogout.htm";
	}
}

$(function() {
	if (/msie/.test(navigator.userAgent.toLowerCase())) {
		$('input:checkbox').click(function () {
		   this.blur();  
		   this.focus();
		});
	}
	// 定时修改密码窗口
	if(changePwd=='true')
		$("#change_pwd_win").window('open');
	else
		$("#change_pwd_win").window('destroy');
	//该方法主要用于监听用户权限操作，没权限时提示
	$(document).ajaxError(function(event,xhr,options,exc){
//("事件名-----" + event.type + "\n"
//                +"状态码-----" + xhr.status + "\n"
//                +"请求源-----" + options.url + "\n"
//                +"描述是-----" + exc + "\n");
		if(xhr.status == '404'){//由于没权限的时候会返回404，所以跟无页面一起提示
			showMessage('404：地址不存在！');
		}else if(xhr.status == '500'){
			showMessage('内部服务器错误！');
		}else if(xhr.status == '403'){
			showMessage('禁止访问！');
		}else if(xhr.status == '401'){
			showMessage('访问被拒绝！');
		}else if(xhr.status == '400'){
			showMessage('错误的请求！');
		}
	});
	
	//该方法主要用于监听用户过期时返回的信息
	$(document).ajaxSuccess(function(event,xhr,options){
		if (options.dataType == "json"){
			var d = eval("("+xhr.responseText+")");
			if(d.result=='false'){
//				$.messager.alert('提示',d.message,'error');
				alert(d.message);
			}
		}
	});
	
	// 当layout的中间区域大小改变之后，中央的内容div也需要跟着改变大小
	$("#main_layout").layout("panel", "center").panel({
		onResize : function(w, h) {
			$("#center_page").css("height", "100%");
		}
	});
	// 左侧菜单栏的开关按钮
	$("#closeopen_menu_btn").click(function() {
		if ($('#main_layout').layout('isVisible', 'west')){
			$('#main_layout').layout('hidden', 'west');
		}else{
			$('#main_layout').layout('show', 'west');
		}
	});
	// ----------初始化数据-右上角菜单 TODO 通过ajax获取
	var data = {
		rtMenu : [ {
			id : 'sys_logout',
			cname : '登出系统',
			img : '/images/common_page/exit9.gif'
		} ]
	};
	// 附上模板
	$("#top_right_menu").setTemplateElement("top_right_menu_model");
	// 给模板加载数据
	$("#top_right_menu").processTemplate(data);
	// ----------
	// ----------初始化数据-左侧菜单
	$.ajax({
		url:ctxPath+"/auth/getMenu.htm",
		cache:false,
		data:{userid:$("#user_id").val()},
		success:function(data){
//			alert(data);
			// 附上模板
			$("#content_left").setTemplateElement("left_menu_model");
			// 给模板加载数据
			$("#content_left").processTemplate(data);
		}
	});
	
	// 右上角按钮tooltip初始化，注：一定要在模板加载完之后执行才有效
	$(".hotspot").tooltip();
	$("#user_name_text").tooltip();
});

//-------------------------------------------------liuzj---------------------------------------------------
//查询个人信息
function editUserInfo(userId,corpId){
	//console.log("userId,corpId:"+userId+","+corpId);
	var css = $("#editUserInfo_pannel").parent().attr("style");
	//console.log("css:"+css);
	if(typeof(css)!="undefined"&&css.indexOf("block")!=-1){
		closeUserInfo();
		return false;
	}
	var url = ctxPath+'/usermanage/editUserInfo.htm?userName='+userId+'&corpid='+corpId;
	$("#editUserInfo_pannel").window({  
		width: 800,
		height: 320,
		title:'个人信息修改',
		href:url,
	    cache:false,
	    method:'POST',
	    loadingMessage:"正在加载中...",
	    onLoad:function(data){
	    	//html 处理
	    	var html = processHtmlStr(data);
	    	$(this).html(html);
	    	
	    },
	    onLoadError:function(){
	    	alert("请求出错或超时！");
	    },
	    onResize:function(width, height){
	    	//console.log("onResize");
	    }
	});
	//$("#editUserInfo_pannel").window('open');
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}
//调用： 
//
//var time1 = new Date().Format("yyyy-MM-dd");
//var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");

//隐藏修改密码行和取消按钮
function cancel_changePwd(){
	//--恢复初始状态
	var $arr = $("#changePwd_tr input");
	for(var i=0;i<$arr.length;i++){
		$arr.eq(i).val("");
	}
	//--恢复初始状态
	
	$("#cancel_changePwd_span").fadeOut();
	$("#changePwd_tr").remove();
}
//显示修改密码行和取消按钮
function show_changePwd_tr(){
//	$("#changePwd_tr").fadeIn();
	$("#cancel_changePwd_span").fadeIn();
	var pwdHtml= "<tr id='changePwd_tr'><td type='password' width='50px' align='right'>旧密码</td>" +
	"<td width='100px' align='left'><input name='oldword' value=''/></td>" +
	"<td width='50px' align='right'>新密码</td>" +
	"<td width='100px' align='left'><input type='password' name='password' value=''/></td>" +
	"<td width='50px' align='right'>确认密码</td>" +
	"<td width='100px' align='left'><input type='password' name='checknewpwd' value=''/></td></tr>" ;
	if(($("input[name='password']").length)==0){
		$("#pwd_tr").after(pwdHtml);
	}
}
//显示文本框和取消按钮
function show_input(obj){
	$(obj).parent().find("input").attr("type","input");
	$(obj).parent().find("span").eq(0).hide();
	$(obj).parent().find("span").eq(2).show();
}
//隐藏文本框和取消按钮
function hide_input(obj){
	var $dom = $(obj).parent();
	$dom.find("input").attr("type","hidden").val($dom.find("span").eq(0).text());//隐藏并恢复初始值
	$dom.find("span").eq(0).show();
	$dom.find("span").eq(2).hide();
}
function saveUserInfo(){
	var userID = $("#userInfoForm input[name='userID']").val();
	var sex = $("#userInfoForm select[name='sex']").val();
	//console.log("sex:"+sex);
	var position = $("#userInfoForm input[name='position']").val();
	var mobile = $("#userInfoForm input[name='mobile']").val();
	var password = $("#userInfoForm input[name='password']").val();
	var oldpassword = $("#userInfoForm input[name='oldword']").val();
	var checknewpwd = $("#userInfoForm input[name='checknewpwd']").val();
	if($("#userInfoForm input[name='password']").length==0){
		password = '';
		oldpassword = '';
		checknewpwd = '';
	}	
	//执行验证	
	validateData();
	if($('#userInfoForm').form('validate')){
	//需求改动	
	//执行验证
	//-----------------------------------	
	$.ajax({
		url:ctxPath+"/usermanage/updateUserInfo.htm",
		cache:false,
		data:"userID="+userID+"&sex="+sex+"&position="+position+"&mobile="+mobile+"&password="+password+"&oldpassword="+oldpassword+"&checknewpwd="+checknewpwd,
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
			//  刷新 window 内容
			
			$("#editUserInfo_pannel").window('refresh');
		},
		error:function(){
			showMessage("修改失败");
		}
	});}
	
}
function closeUserInfo(){
	$("#editUserInfo_pannel").window('close');
}
//html 处理
function processHtmlStr(data){
//	var jsonObj = JSON.parse(data);
	var jsonObj = eval('(' + data + ')');
	var u=jsonObj.userInfo;
	var c=jsonObj.corpInfo;
	var sex = (u.sex=="M"?"男":"女");
	var html = "<form id='userInfoForm' method='post'><input type='hidden' name='userID' value='"+u.userID+"'/><table cellspacing='10' cellpadding='0' border='0' width='100%' align='center'>";
	var status = (u.status=="Y"?"正常":"暂停");
	var userType = (u.userType=="O"?"操作员":"会员");
	html = html + "<tr><td width='50px' align='right'>人员姓名</td>" +
					  "<td width='100px' align='left'>"+(u.lastName+u.firstName)+"</td><td width='50px' align='right'>性别</td>" ;
	if("M"==u.sex){//男
		html=html+"<td width='100px' align='left'><select name='sex' disabled><option selected='selected' value='M'>男</option><option value='F'>女</option></select></td>";
	}else{
		html=html+"<td width='100px' align='left'><select name='sex' disabled><option value='M'>男</option><option selected='selected' value='F'>女</option></select></td>";
	}
	html = html + "<td width='50px' align='right'>职务</td>" +
	"<td width='150px'><span><span style='width:100px;'>"+u.position+"</span>" +
		"<input type='hidden' name='position' value='"+u.position+"' style='width:100px;'/>" +
		"<span style='color:blue;cursor:pointer;display:none;' onclick='show_input(this)'>&nbsp;修改</span>" +
		"<span id='cancel_input' style='color:blue;cursor:pointer;display:none;' onclick='hide_input(this)'>&nbsp;取消</span></span></td></tr>"+
	"<tr><td width='50px' align='right'>工号</td>" +
		"<td width='100px' align='left'>"+u.work_no+"</td>" +
		"<td width='50px' align='right'>状态</td>" +
		"<td width='100px' align='left'>"+status+"</td>" +
		"<td width='50px' align='right'>手机号码</td>" +
		"<td width='150px' align='left'><span><span style='width:100px;'>"+u.mobile+"</span>" +
			"<input type='hidden' name='mobile' value='"+u.mobile+"' style='width:100px;'/>" +
			"<span style='color:blue;cursor:pointer;display:none;' onclick='show_input(this)'>&nbsp;修改</span>" +
			"<span id='cancel_input' style='color:blue;cursor:pointer;display:none;' onclick='hide_input(this)'>&nbsp;取消</span></span></td></tr>"+				  
	"<tr>" +
//		"<td width='50px' align='right'>用户类型</td>" +
//		"<td width='100px' align='left'>"+userType+"</td>" +
	    "<td width='50px' align='right'>用户组名</td>" +
	    "<td width='100px' align='left'>"+u.groupName+"</td>" +
	    "<td width='50px' align='right'>注册日期</td>" +
	    "<td width='150px' align='left'>"+new Date(u.initDate.time).Format("yyyy-MM-dd")+"</td>" +
	    "<td width='50px' align='right'>固定电话</td>" +
		"<td width='100px' align='left'>"+u.tel+"</td>" +
	"</tr>"+					  
//	"<tr><td width='50px' align='right'>固定电话</td>" +
//		"<td width='100px' align='left'>"+u.tel+"</td>" +
//		"<td width='50px' align='right'>操作员</td>" +
//		"<td width='100px' align='left'>"+u.operator+"</td>" +
//		"<td width='50px' align='right'>中心</td>" +
//		"<td width='150px' align='left'>"+u.corpNum+"</td></tr>"+
//	"<tr id='pwd_tr'><td width='50px' align='right'><span style='color:blue;cursor:pointer;' onclick='show_changePwd_tr()'>修改密码</span></td>" +
//		"<td width='500px' colspan='5' align='left'><span id='cancel_changePwd_span' onclick='cancel_changePwd()' style='display:none;color:blue;cursor:pointer;'>取消</span></td></tr>"+
	"<tr id='changePwd_tr'><td width='50px' align='right'>旧密码</td>" +
		"<td width='100px' align='left'><input type='password' name='oldword' value=''/></td>" +
		"<td width='50px' align='right'>新密码</td>" +
		"<td width='100px' align='left'><input type='password' name='password' value=''/></td>" +
		"<td width='50px' align='right'>确认密码</td>" +
		"<td width='100px' align='left'><input type='password' name='checknewpwd' value=''/></td></tr>"+
	"<tr><td colspan='3' align='right' width='50%'><input type='button' onclick='saveUserInfo()' value='保存' class='airlines_button'/>&nbsp;&nbsp;&nbsp;&nbsp;</td>" +
	"<td colspan='3' width='50%'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' onclick='closeUserInfo()' value='关闭' class='airlines_button'/></td></tr></table></form>";
	return html;
}
//验证
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
	            var password = $("#userInfoForm input[name='password']").val();
	            var checknewpwd = $("#userInfoForm input[name='checknewpwd']").val();
	            return checknewpwd==password;
	        },
	        message: '两次新密码输入不一致'
	    }
	});
	//新密码和确认密码验证
	$("#userInfoForm input[name='password']").validatebox({    
		validType:['checkPwdLength','checkTwoNewPwd']
	});
	$("#userInfoForm input[name='checknewpwd']").validatebox({    
		validType:['checkPwdLength','checkTwoNewPwd']
	});
}
//-------------------------------------------------liuzj---------------------------------------------------