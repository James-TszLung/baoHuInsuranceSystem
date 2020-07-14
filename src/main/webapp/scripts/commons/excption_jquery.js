/**
 * 页面初始化方法
 */
function initPage(){
	//初始化功能所在位置title
	$('#start,#end').datebox({
		editable:false
	});
	doSearch(true);
}
function doSearch(f){
	if(f){
	    var url = ctxPath+"/exception/search.htm";
	    var postData = $('#condition_search :input').serializeArray();
	    $("#results").load(url, postData);
	}else{
		var url = ctxPath+"/exception/search.htm";
	    var postData = $('#exceptionList :input').serializeArray();
	    $("#results").load(url, postData);
	}
}

function mulitiDelete(){
	var ids = [];
	var rows = $('#dg').datagrid("getSelections");
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	if(ids.length<1){
		easyalert("请选择要删除的记录！");
		return false;
	}
	var str = ids.join(",");
	$.messager.confirm('消息确认',"确定要删除记录"+str+"？",function(r){
	    if (r){
	    	var postData = {"ids":str};
			var url = ctxPath+"/exception/mulitedelete.htm";
			$.post(url,postData,mulitiDeleteCallback,"text");
	    }
	});
}

function mulitiDeleteCallback(data){
	var result = eval('('+data+')');
	if(result.success==1){
		easyalert("成功删除记录:"+result.succf);
	}else{
		easyalert("删除记录失败！");
	}
	
	refreshList();
}

function refreshList(){
	doSearch(false);
}

function easyalert(msg){
	$.messager.alert('消息提醒',msg);
}

function selectAll(){
	var checks = $('#dg').datagrid('getChecked');
	if(checks.length <= 0)
		$('#dg').datagrid('selectAll');
	else
		$('#dg').datagrid('unselectAll');
}

function showdetail(obj){
	var o = $(obj).parent().parent().parent();
	var id = $(obj).parent().parent().parent().attr("id");
	var arr = id.split("-");
	arr[3]=1;
	var fid = arr.join("-");
	var ckk = $("#"+fid).find("span");
	ckk.click();
}