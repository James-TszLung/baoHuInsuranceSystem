var submitURL;
var modeText = "分配角色";

function initPage(){
	passenger.init();
	if(nowLoc.length == 2)
		$("#page_loc_title").html(nowLoc[1]+' -- '+modeText);
	else
		$("#page_loc_title").html(nowLoc[2]+' -- '+modeText);
	
}

function closeAddUserWin(){
	visitUrl(ctxPath+"/usergroup/groupPage.htm",nowLoc[0],nowLoc[1],nowLoc[2]);
}

//↓多选框JS
//待选选项集合，例：['ID','角色名', '状态']
//['0','姓名', '证件号码', '证件类型', '常旅客卡', '用户名', '项目组ID', '项目组名称', '部门ID', '部门名称']
//['0','张大斌', '441424198504102839', 'NI', '', 'admin', '', '', ''],
var passenger_all = [];
//选项分组
var passenger_group = [
//['0', [0,1,2,3,4], '工贸总部']
];
//被选中选项集合
var passenger_selected = [];
var passenger_num = 0;
var passenger_unselect = [];
var group_pale = [];
var role_status_cn = ['停用','不适用'];
function parseToArray(jdata) {
	var rsArr = [];
	for(var i=0; i<jdata.length; i++){
		var arr = [jdata[i].roleID,jdata[i].roleName,'','','','','','','',''];
		rsArr[i] = arr;
	}
	return rsArr;
}
var passenger = {
	/*
	init 初始化
	fillRight 填充右列表
	autoList 搜索乘机人填充左列表
	onEvent 常旅客左列表事件
	fillPassengerNum 显示常旅客人数和乘机人数
	fillTemp 填充左列表
	addPassenger 添加乘机人
	closeDialog 关闭窗口
	*/
	init : function() {
		group_pale = $("#groupPale").val().split(',');
		$.ajax({
			url:ctxPath+"/role/getGroupRoleByGID.htm",
			data:{groupID:$("#groupID").val(),groupPale:$("#groupPale").val()},
			cache:false,
			success:function(data){
				passenger_all = data.sysrole;
				passenger_selected = data.userrole;
				passenger.autoList();
				passenger.fillPassengerNum();
			}
		});
	},
	fillRight : function() {
		var passenger_list = '';
		for (var i=0; i<passenger_selected.length; i++)
		{
			var role_id = passenger_selected[i].roleID;
			var role_name = passenger_selected[i].roleName;
			var role_corp = passenger_selected[i].corpNum;
			var role_pale = passenger_selected[i].rolePale;
			var role_remark = passenger_selected[i].remark;
			var role_init = passenger_selected[i].init;
			var role_initdate = passenger_selected[i].initDate;
			var role_status = passenger_selected[i].status;
			var msg = '';
			var isPale = false;
			
			if(role_status == 'N')
				msg = "<font color='red'>("+role_status_cn[0]+")</font>";
			else{
				for(var z=0; z<group_pale.length; z++){
					if(role_pale.indexOf(group_pale[z])>=0){
						isPale = true;
					}
				}
				if(!isPale){
					passenger_selected[i].status = 'N';
					msg = "<font color='red'>("+role_status_cn[1]+")</font>";
				}
			}
			
			//passenger_list += '<li><a href="javascript:void(0);" title="点击删除"><em class="name">' + passenger_selected[i][1] + '</em><em class="card" title="证件号码">' + temp_cardid + '</em></a></li>';
			passenger_list += '<li><a href="javascript:void(0);" title="点击删除">' + role_name + msg + '</a></li>';
		}
		var linkman_right = document.getElementById('linkman_right').getElementsByTagName('a');
		document.getElementById('linkman_right').getElementsByTagName('ul')[0].innerHTML = passenger_list;
		for (var i=0; i<linkman_right.length; i++)
		{
			with({i:i})linkman_right[i].onclick = function() {
				if(passenger_selected[i].status == 'Y')
					passenger_unselect.push(passenger_selected[i]);
				passenger_selected.remove(i);
				passenger.autoList();
//				passenger.fillRight();
				passenger.fillPassengerNum();
			}
		}
	},
	autoList : function() {
		var quicksearch = document.getElementById('quicksearch').value;
		var searchformat = new RegExp(quicksearch);
		var passenger_listall = '';

		passenger_unselect = [];
//		for (var gi=0; gi<passenger_group.length; gi++)
//		{
//			for (var si=0; si<passenger_group[gi][1].length; si++)
//			{
				for (var i=0; i<passenger_all.length; i++)
				{
//					if (passenger_group[gi][1][si] == passenger_all[i][0])
//					{
						if (searchformat.test(passenger_all[i].roleName))
						{
							var hasS = false;
							for(var j=0; j<passenger_selected.length; j++){
								if (passenger_selected[j].roleID == passenger_all[i].roleID){
									hasS = true;
								}
							}
							if (!hasS)
							{
								passenger_unselect.push(passenger_all[i]);
							}
						}
//					}
				}
//			}
//		}
		this.fillRight();
		this.fillTemp();
		this.onEvent();
	},
	onEvent : function() {
		var linkman_left = document.getElementById('linkman_left').getElementsByTagName('a');
		for (var i=0; i<linkman_left.length; i++)
		{
			with({i:i})linkman_left[i].onclick = function() {
					if (passenger_selected.exists(passenger_unselect[i])) {
						alert('已存在！');
						return;
					}
					else {
						passenger_selected.push(passenger_unselect[i]);
					}
					if (passenger_unselect.length > 0) passenger_unselect.remove(i);
					passenger.fillRight();
					passenger.fillTemp();
					passenger.onEvent();
				}
				passenger.fillPassengerNum();
		}
		passenger.fillPassengerNum();
	},
	fillPassengerNum : function() {
		document.getElementById('totleNum').innerHTML = '';
		document.getElementById('selectNum').innerHTML = '';
		if (passenger_unselect.length >= 0) {
			document.getElementById('totleNum').innerHTML = '(' + passenger_unselect.length + ')';
		}
		if (passenger_selected.length >= 0) {
			document.getElementById('selectNum').innerHTML = '(' + passenger_selected.length + ')';
		}
	},
	fillTemp : function() {
		document.getElementById('linkman_left').getElementsByTagName('ul')[0].innerHTML = '';
		var dom = document.getElementById('linkman_left').getElementsByTagName('ul')[0];
//		for (var gi=0; gi<passenger_group.length; gi++)
//		{
			var pnum = 0;
//			var element = document.createElement('li');
//			element.className = 'cust';
//			if (passenger_group[gi][3] == 0) {
//				element.className = 'group';
//			}
//			else if(passenger_group[gi][3] == 1) {
//				element.className = 'cost';
//			}
//			element.id = 'group' + gi
//			var elementText = document.createTextNode(passenger_group[gi][2]);
//			element.appendChild(elementText);
//			dom.appendChild(element);

//			for (var si=0; si<passenger_group[gi][1].length; si++)
//			{
				for (var i=0; i<passenger_unselect.length; i++)
				{
//					if (passenger_group[gi][1][si] == passenger_unselect[i][0])
//					{
					var role_id = passenger_unselect[i].roleID;
					var role_name = passenger_unselect[i].roleName;
					var role_corp = passenger_unselect[i].corpNum;
					var role_pale = passenger_unselect[i].rolePale;
					var role_remark = passenger_unselect[i].remark;
					var role_init = passenger_unselect[i].init;
					var role_initdate = passenger_unselect[i].initDate;
					var role_status = passenger_unselect[i].status;
					
					
						var passenger_element = document.createElement('li');
						//passenger_element.innerHTML = '<a href="javascript:void(0);" title="点击添加"><em class="name" title=' + passenger_unselect[i][5] + '>' + passenger_unselect[i][1] + '</em><em class="card" title="证件号码">' + temp_cardid + '</em></a>';
						passenger_element.innerHTML = '<a href="javascript:void(0);" title="点击添加">' + role_name + '</a>';
						dom.appendChild(passenger_element);
						pnum++;
//					}
				}
//			}
//			if (pnum < 1)
//			{
//				document.getElementById('group' + gi).style.display = 'none';
//			}
//		}
	},
	addPassenger : function() {
		var srids = "";
		for(var i=0; i<passenger_selected.length; i++){
			if(i>0)
				srids += ",";
			srids += passenger_selected[i].roleID;
		}
		$.ajax({
			url:ctxPath+"/usergroup/saveRelation.htm",
			data:{groupID:$("#groupID").val(),roleIDs:srids},
			cache:false,
			success:function(data){
				showMessage("成功绑定 "+data+" 个角色！");
				visitUrl(ctxPath+"/usergroup/groupPage.htm",nowLoc[0],nowLoc[1],nowLoc[2]);
			},
			error:function(){
				showMessage("绑定失败！");
			}
		});
	},
	closeDialog : function() {
		parent.pmwin('close');
	}
}

// 删除数组元素
Array.prototype.remove = function(dx)
{
	if(isNaN(dx) || dx>this.length){return false;}
	for(var i=0,n=0; i<this.length; i++)
	{
		if(this[i] != this[dx])
		{
			this[n++] = this[i];
		}
	}
	this.length -= 1;
}
//检查数组元素是否存在
Array.prototype.exists = function(item)
{
	for (var n = 0; n < this.length; n++)
	if (item == this[n]) return true;
	return false;
}
