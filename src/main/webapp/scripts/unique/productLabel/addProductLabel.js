$(function() {
	$("#name").validatebox({
		required : true,
		missingMessage : '最多20个字',
		validType : 'length[1,20]'
	});
	// 返回操作
	$("#backBtn").click(function() {
		visitUrl(ctxPath + "/product/productLabelPage.htm", 1, 1, 1);
	});
	$("#cancelId").click(function() {
		$("#add_singleser").fadeOut('fast');
	});
	// 新增页面的单品服务列表
	$('#servicePro_grid')
			.datagrid(
					{
						type : "POST",
						fitColumns : true,
						singleSelect : true,
						autoRowHeight : false,
						pageSize : 10,
						columns : [ [
								{
									field : 'index',
									hidden : 'true'
								},
								{
									field : 'name',
									width : '25%',
									title : '产品名称'
								},
								{
									field : 'sort',
									width : '18%',
									title : '排序',
									formatter : function(value, rec) {
										var serviceTime = "sort" + rec.id;
										var reStr = "<input placeholder='请输入排序,整数' name='" + serviceTime
												+ "' type='number'  style='height: 22px;width: 115px;' value='0'>";
										return reStr;
									}
								},
								{
									field : 'opt',
									title : '操作',
									width : '14%',
									align : 'center',
									formatter : function(value, rec, index) {
										var reStr = '&nbsp;&nbsp;<a style="color:#2A9CD5" onclick="deleteRow(this)">删除</a>';
										return reStr;
									}
								} ] ]
					});
	var rowsList = [];
	$("#saveSingleProject").click(function() {
		var rowsData = $('#single_grid').datagrid('getSelections');
		for ( var i = 0; i < rowsData.length; i++) {
			rowsData[i].sort = 0;
			rowsList.push(rowsData[i]);
		}
		$('#servicePro_grid').datagrid('loadData', rowsList);
		$("#add_singleser").fadeOut('fast');
	});
	// 保存操作
	$("#addSeriesBtn").click(
			function() {
				var rowsData = $("#servicePro_grid").datagrid("getRows");
				var dataArr = [];
				for ( var i = 0; i < rowsData.length; i++) {
					var sort = "sort" + rowsData[i].id;
					var sortVal = $("input[name=" + sort + "]").val();
					var str = "";
					str += "<input type=\"hidden\" name='productLabelRelationLs[" + i + "].productId' value=\""
							+ rowsData[i].id + "\">";
					if (sortVal == "" || sortVal == null || sortVal == undefined) {
						showMessage("排序不能为空");
						return;
					}
					str += "<input type=\"hidden\" name='productLabelRelationLs[" + i + "].sort' value=\"" + sortVal + "\">";
					$("#serviceMealDiv").append(str)
				}
				$("#addSeriesform").form("submit", {
					type : "POST",
					url : ctxPath + "/product/saveProductLabel.htm",
					success : function(data) {
						data = eval("(" + data + ")")
						if (data.success == "1" || data.success == 1) {
							showMessage(data.mes);
							visitUrl(ctxPath + "/product/productLabelPage.htm", 1, 1, 1);
						}
					},
					error : function() {
						showMessage("请求失败！");
					}
				});
			});
})
// 单品服务弹框数据列表
var lastData;
function doSearchSinglePro(f) {
	var searchURL = "/product/serachProductConsultation.htm";
	if (f) {
		var url = ctxPath + searchURL;
		lastData = $('#searchSingleProjectForm :input').serializeArray();
		$("#singleProjectListMeal").load(url, lastData);
	} else {
		var url = ctxPath + searchURL;
		var postData = lastData.concat($('#resultMealList :input').serializeArray());
		$("#singleProjectListMeal").load(url, postData);
	}
}

// 新增单品
function addServicePro() {
	$("#add_singleser").fadeIn('fast');
	var datas = $('#servicePro_grid').datagrid('getData');
	if (datas.rows.length > 0) {
		var ids = "";
		for ( var i = 0; i < datas.rows.length; i++) {
			ids = ids + datas.rows[i].id; // 假设Table中有列名number
			ids += ",";
		}
		ids = ids.substring(0, ids.length - 1);
		$("#serviceProIds").val(ids);
	} else {
		$("#serviceProIds").val("");
	}
	doSearchSinglePro(true);
}
function deleteRow(target) {
	$('#servicePro_grid').datagrid('deleteRow', getRowIndex(target));
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}