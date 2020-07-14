$(function () {
	initRequired();
	//添加成员
	$('#btnAddPlanConfig').on('click', function () {
		var num = 0;
		$("div[name='planConfigItem']").each(function() {
			var index = $(this).attr("itemIndex");
			if(index != null && index != undefined && index != ''){
				num = parseInt(index) + 1;
			}
		})
		var str = '<div class="card bg-light my-3 config-item table-item" id="planConfigItemId'+num+'" name="planConfigItem" itemIndex="'+num+'">' +
			'                            <div class="card-body">' +
			'                                <div class="form-group row">' +
			'                                    <div style="width: 39%;">' +
			'                                        <label class="col_21">*姓名</label>' +
			'                                        <input class="form-control insure_required" style="width: 70%;" name="planConfigLs['+num+'].name">' +
			'                                    </div>' +
			'                                    <div style="width: 15%;">' +
			'                                        <label class="col_21">性别</label>' +
			'                                        <div class="form-control gender">' +
			'                                            <input type="radio" name="planConfigLs['+num+'].gender" value="1">男' +
			'                                            <input type="radio" name="planConfigLs['+num+'].gender" value="2">女' +
			'                                        </div>' +
			'                                    </div>' +
			'                                    <div style="width: 22%;">' +
			'                                        <label class="col_21" style="width: 20%;">出生日期</label>' +
			'                                        <input name="planConfigLs['+num+'].birth" type="text" class="Wdate form-control" onclick="WdatePicker()" style="width:120px;" />' +
			'                                    </div>' +
			'                                    <div style="width: 20%;">' +
			'                                        <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 open-button" style="margin-right: 10px;">收起</button>' +
			'                                        <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-plan-config-button">删除</button>' +
			'                                    </div>' +
			'                                </div>' +
			'                                <div class="info-list">' +
			'                                    <div class="form-group row">' +
			'                                        <label class="col_20"> 健康状况 </label>' +
			'                                        <div class="col-8">' +
			'                                            <textarea class="form-control" name="planConfigLs['+num+'].health" rows="5"></textarea>' +
			'                                        </div>' +
			'                                    </div>' +
			'                                    <div class="form-group row">' +
			'                                        <label class="col_20"> 已有保险 </label>\n' +
			'                                        <div class="col-8">' +
			'                                            <textarea class="form-control" name="planConfigLs['+num+'].insured" rows="5"></textarea>\n' +
			'                                        </div>' +
			'                                    </div>' +
			'                                    <div class="form-group row">' +
			'                                        <label class="col_20">模板概述</label>' +
			'                                        <div class="col-8">' +
			'                                            <button type="button" class="btn1 btn-sm btn-info ml-3 content-temp">选择</button>' +
			'                                        </div>' +
			'                                    </div>' +
			'                                    <div class="form-group row">' +
			'                                        <label class="col_20"> 方案概述</label>' +
			'                                        <div class="col-8">' +
			'                                            <textarea class="form-control" name="planConfigLs['+num+'].content" rows="4"></textarea>' +
			'                                        </div>' +
			'                                    </div>' +
			'                                    <div class="form-group row">' +
			'                                        <label class="col_20"> 方案备注</label>' +
			'                                        <div class="col-8">' +
			'                                            <textarea class="form-control" placeholder="该部分内容仅工作人员可见，不对客户开放" name="planConfigLs['+num+'].remark" rows="5"></textarea>' +
			'                                        </div>' +
			'                                    </div>' +
			'                                </div>' +
			'                            </div>' +
			'                        </div>';

            $("#planConfigList").append(str);
	})
	//展开和收起成员信息
	$("#planConfigList").on('click', '.open-button', function openItem(event){
        if($(this).text()=='收起'){
           $(this).parents('.card-body').find(".info-list").hide();
			$(this).text("展开");
		}else if($(this).text()=='展开'){
			$(this).parents('.card-body').find(".info-list").show();
			$(this).text("收起");
		}
	})
	//删除成员信息
	$("#planConfigList").on('click', '.delete-plan-config-button', function deletItem(event){
		if($("div[name='planConfigItem']").length==1){
			alert("至少保留一个成员信息");
		}else{
			$(this).parents(".config-item").remove();
		}

	})
	//概述模板选择
	$("#planConfigList").on('click','.content-temp', function openTemp(event){
		$("#configIndex").val($(this).parents(".config-item").attr("itemIndex"));
		$('#chooseContentTemp').modal('show');
	})

	$('#chooseContentTemp').on('shown.bs.modal', function (event) {
		doSearchContentTemp(true);
	})


	//概述模板选择确定
	$("#contentTempConfirm").click(function(){
		var row = $('#result_grid').datagrid('getSelected');
		var itemImdex = $("#configIndex").val();
		$("textarea[name='planConfigLs["+itemImdex+"].content']").val(row.content);
		$('#chooseContentTemp').modal('hide');
	})
    //添加风险保障
	$('#btnAddPlanRisk').on('click', function (){
		var num = 0;
		$("div[name='planRiskItem']").each(function() {
			var index = $(this).attr("itemIndex");
			if(index != null && index != undefined && index != ''){
				num = parseInt(index) + 1;
			}
		})
		const template = $('#template .plan-risk-item').find('.plan_risk_item');
		var cloneHtml = template.clone();
		cloneHtml.attr("id","planRiskItemId"+num);
		cloneHtml.attr("name","planRiskItem");
		cloneHtml.attr("itemIndex",num);
		cloneHtml.find('.risk_type').attr("name","planRiskLs["+num+"].typeId").removeClass("risk_type");
		cloneHtml.find('.risk_used').attr("name","planRiskLs["+num+"].used").removeClass("risk_used");
		cloneHtml.find('.risk_risk').attr("name","planRiskLs["+num+"].risk").removeClass("risk_risk");
		cloneHtml.find('.risk_insuredContent').attr("name","planRiskLs["+num+"].insuredContent").removeClass("risk_insuredContent");
		cloneHtml.find('.risk_insuredDesc').attr("name","planRiskLs["+num+"].insuredDesc").removeClass("risk_insuredDesc");
		cloneHtml.find('.risk_deadlineContent').attr("name","planRiskLs["+num+"].deadlineContent").removeClass("risk_deadlineContent");
		cloneHtml.find('.risk_deadlineDesc').attr("name","planRiskLs["+num+"].deadlineDesc").removeClass("risk_deadlineDesc");
		cloneHtml.find('.risk_suggest').attr("name","planRiskLs["+num+"].suggest").removeClass("risk_suggest");
		cloneHtml.find('.risk_modalPlanSafetyItems').attr("id","modalPlanSafetyItems"+num).removeClass("risk_modalPlanSafetyItems");
		cloneHtml.find('.add-plan-safety').attr("data-index",num)
        $("#planRiskList").append(cloneHtml);
	})
	//删除风险保障
	$("#planRiskList").on('click', '.delete-plan-risk-button', function deletItem(event){
		if($("div[name='planRiskItem']").length==1){
			alert("至少保留一个风险保障");
		}else{
			$(this).parents(".risk-item").remove();
		}
	})
	//产品选择
	$('#_productIds').acBox({
		source: ctxPath+'/product/select.htm?acbox=y&callback=?'
		,multiple : false
		,multi_limit : 1
		, navi_num: 5
		,numPerPage:25
		,sel_callback: function () {
			var productName = $('input[name=_productIds_name]').val();
			var productId = $('input[name=_productIds_id]').val();
			$("input[name=modalProductName]").val(productName);
			$("input[name=modalProductId]").val(productId);
			loadRelationData(productId,null);
		}
	});
	//添加保险产品
	$("#planRiskList").on('click', '.add-plan-safety',function (event) {
		event.preventDefault();
		const modal = $('#modalPlanSafety');
		$('.table-list .table-item').removeClass('cur');
		modal.data('cur', "add");
		$("input[name=_productIds_name]").val('');
		modal.find(".plan-safety-name").val('');
		$("input[name=modalProductId]").val('');
		$("#modalChannel").empty();
		modal.find(".plan-safety-premiums").val('');
		modal.find(".plan-safety-insured").find("option").eq(0).prop("selected", 'selected');
		modal.find(".plan-safety-pay").find("option").eq(0).prop("selected", 'selected');
		modal.find(".plan-safety-recomment").find("option").eq(0).prop("selected", 'selected');
		$("#modalPlanSafetyIndex").val("-1");
		$("#modalRiskIndex").val($(this).data("index"));
		$("#addPlanSafetySub").empty();
		$('#modalPlanSafety').modal('show');
	})
	//确定增加产品
	$('#modalPlanSafetyConfirm').on('click', function (event) {
		const modal = $('#modalPlanSafety');
		const action = modal.data('cur');
		var productName = modal.find('.plan-safety-name').val();
		var productId = $("input[name=modalProductId]").val();
		var channelName = modal.find('.plan-safety-channel').find("option:selected").text();//销售渠道
		var channelId = modal.find('.plan-safety-channel').val();
		var premiums = modal.find('.plan-safety-premiums').val();//年交保费
		var insuredName = modal.find('.plan-safety-insured').find("option:selected").text();//保险期限
		var insuredId = modal.find('.plan-safety-insured').val();
		var payName = modal.find('.plan-safety-pay').find("option:selected").text();//缴费期限
		var payId = modal.find('.plan-safety-pay').val();
		var recommentName = modal.find('.plan-safety-recomment').find("option:selected").text();//推荐原因
		var recommentId = modal.find('.plan-safety-recomment').val();

		if(productId==""){
			alert("请选择产品");
			return false;
		}else if(premiums==""){
			alert("年交保费不能为空");
			return false;
		}else if(channelId==""){
			alert("渠道不能为空");
			return false;
		}

		var riskIndex = parseInt($("#modalRiskIndex").val());
		var num = 0;
		var add = true;
		if(action === 'add'){
			if($("tr[name='planSafetyItem"+riskIndex+"']").length>0){
				$("tr[name='planSafetyItem"+riskIndex+"']").each(function() {
					var index = $(this).attr("itemIndex");
					if(index != null && index != undefined && index != ''){
						num = parseInt(index) + 1;
					}
				})
			}
		}else{
			num = parseInt($("#modalPlanSafetyIndex").val());
			add = false;
		}
		var subItemHtml = '';
		var _isInvalid = false;
		var amount = 0;
		var addPrice = true;
		if($("#addPlanSafetySub").find("tr").length>0){
			$("#addPlanSafetySub").find("tr").each(function() {
				var index = $(this).index();
				var guaranteeIntroduction = $(this).find(".guaranteeIntroduction").text();
				var oldPrice = $(this).find(".oldPrice").text();
				var guaranteeRelationId = $(this).find("input[name='guaranteeRelationId']").val();
				var dictionaryContentId = $(this).find("input[name='dictionaryContentId']").val();
				var dictionaryContentName = $(this).find(".dictionaryContentName").text();
				var price = $(this).find("input[name='price']").val();
				if(price==""){
					alert("保额不能为空");
					_isInvalid = true;
					return false;
				}
				if(addPrice && !isNaN(price)){//数字
					amount = amount + parseFloat(price);
				}else{
					addPrice = false;
				}
				subItemHtml += '<div subIndex="'+index+'"><input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].planGuaranteeRelationLs['+index+'].guaranteeRelationId" type="hidden" value="'+guaranteeRelationId+'"/>';
				subItemHtml += '<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].planGuaranteeRelationLs['+index+'].productId" type="hidden" value="'+productId+'"/>';
				subItemHtml += '<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].planGuaranteeRelationLs['+index+'].dictionaryContentId" type="hidden" value="'+dictionaryContentId+'"/>';
				subItemHtml += '<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].planGuaranteeRelationLs['+index+'].guaranteeIntroduction" type="hidden" value="'+guaranteeIntroduction+'"/>';
				subItemHtml += '<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].planGuaranteeRelationLs['+index+'].oldPrice" type="hidden" value="'+oldPrice+'"/>';
				subItemHtml += '<input type="hidden" name="dictionaryContentName'+index+'" type="hidden" value="'+dictionaryContentName+'"/>';
				subItemHtml += '<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].planGuaranteeRelationLs['+index+'].price" type="hidden" value="'+price+'"/></div>';
			})
		}
		if(_isInvalid){
			return;
		}
		if(!addPrice){
			amount = "无法统计保额";
		}else{
			amount = amount/10000;
		}
		var safetyHtml = '';
		safetyHtml += add ? '<tr class="table-item plan-safety-item" name="planSafetyItem'+riskIndex+'" id="planSafetyItem'+riskIndex+num+'" itemIndex="'+num+'">' : '';
		safetyHtml += '<td><input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].name" type="hidden" value="'+productName+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].productId" type="hidden" value="'+productId+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].premiums" type="hidden" value="'+premiums+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].amount" type="hidden" value="'+amount+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].insuredTerm" type="hidden" value="'+insuredId+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].payTerm" type="hidden" value="'+payId+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].channelId" type="hidden" value="'+channelId+'"/>' +
			'<input type="hidden" name="planRiskLs['+riskIndex+'].planSafetyLs['+num+'].recommentId" type="hidden" value="'+recommentId+'"/>' +
			'<div style="display: none;" name="subItems'+num+'">'+
			subItemHtml +
			'</div>' +
			productName + '</td>' +
			'<td>'+premiums+'</td>' +
			'<td>'+amount+'</td>' +
			'<td>'+insuredName+'</td>' +
			'<td>'+payName+'</td>' +
			'<td>'+channelName+'</td>' +
			'<td>'+recommentName+'</td>' +
			'<td>' +
			'<button class="btn1 btn-sm btn-secondary mr-1" onclick="delModalPlanSafety(this)">删除</button>' +
			'<button class="btn1 btn-sm btn-secondary editModalPlanSafety" data-toggle="modal" data-riskindex="'+riskIndex+'" data-action="edit">编辑</button>' +
			'</td>';
		safetyHtml += add ? '</tr>' : '';
		if(add){
			$("#modalPlanSafetyItems"+riskIndex).append(safetyHtml);
		}else{
			$("#modalPlanSafetyItems"+riskIndex).find("#planSafetyItem"+riskIndex+num).empty().append(safetyHtml);
		}
		$('#modalPlanSafety').modal('hide');
	});
    //编辑保险产品
	$('.table-list').on('click', '.editModalPlanSafety', function editItem(event) {
		event.preventDefault();
		const modal = $('#modalPlanSafety');
		$('.table-list .table-item').removeClass('cur');
		$(this).closest('.table-item').addClass('cur');
		modal.data('cur', "edit");
		var trObj = $(this).parent().parent();
		var riskIndex = $(this).parents(".plan_risk_item").attr("itemIndex");
		var num = parseInt($(trObj).attr("itemIndex"));
		$("#modalRiskIndex").val(riskIndex);
		$("#modalPlanSafetyIndex").val(num);
		var productName = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].name']").val();
		var productId = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].productId']").val();
		var premiums = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].premiums']").val();
		var channelId = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].channelId']").val();
		var insuredTerm = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].insuredTerm']").val();
		var payTerm = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].payTerm']").val();
		var recommentId = $(trObj).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].recommentId']").val();
		$("input[name=modalProductName]").val(productName);
		$("input[name=modalProductId]").val(productId);
		$('input[name=_productIds_name]').val(productName);
		$('input[name=_productIds_id]').val(productId);
		loadRelationData(productId,channelId);
		modal.find(".plan-safety-premiums").val(premiums);
		modal.find(".plan-safety-insured").val(insuredTerm);
		modal.find(".plan-safety-pay").val(payTerm);
		modal.find(".plan-safety-recomment").val(recommentId);

		var subItemHtml = '';
		if($(trObj).find("div[name='subItems"+num+"']").children().length>0){
			$(trObj).find("div[name='subItems"+num+"']").children().each(function() {
				var subIndex = $(this).attr("subIndex");
				var dictionaryContentName = $(this).find("input[name='dictionaryContentName"+subIndex+"']").val();
				var guaranteeRelationId = $(this).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].planGuaranteeRelationLs["+subIndex+"].guaranteeRelationId']").val();
				var dictionaryContentId = $(this).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].planGuaranteeRelationLs["+subIndex+"].dictionaryContentId']").val();
				var guaranteeIntroduction = $(this).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].planGuaranteeRelationLs["+subIndex+"].guaranteeIntroduction']").val();
				var oldPrice = $(this).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].planGuaranteeRelationLs["+subIndex+"].oldPrice']").val();
				var price = $(this).find("input[name='planRiskLs["+riskIndex+"].planSafetyLs["+num+"].planGuaranteeRelationLs["+subIndex+"].price']").val();
				subItemHtml += '<tr>';
				subItemHtml +='<td class="dictionaryContentName">'+dictionaryContentName+'</td>';
				subItemHtml +='<td class="guaranteeIntroduction">'+guaranteeIntroduction+'</td>';
				subItemHtml +='<td class="oldPrice">'+oldPrice+'</td>';
				subItemHtml +='<td>' +
					'<input type="hidden" name="guaranteeRelationId" value="'+guaranteeRelationId+'">' +
					'<input type="hidden" name="dictionaryContentId" value="'+dictionaryContentId+'">' +
					'<input type="text" name="price" class="price" value="'+price+'">'+
					'</td>';
				subItemHtml += '</tr>';
			})
			$("#addPlanSafetySub").empty().append(subItemHtml);
		}
		$('#modalPlanSafety').modal('show');
	})

	$("#backBtn").click(function() {
		visitUrl(ctxPath + "/customizeplan/customizePlanPage.htm", 1, 1, 1);
	});

	// 保存
	$("#addBtn").click( function() {
		if ($('#addform').form('enableValidation').form('validate') == false) {
			return false;
		}
		$("#addform").form("submit", {
			url : ctxPath+"/customizeplan/editCustomizePlan.htm",
			success : function(res) {
				var data = jQuery.parseJSON(res);
				if(data.success==="1"){
					alert("保存成功");
					$("#backBtn").click();
				}else{
					alert("保存失败");
				}
			},
			error : function() {
				alert("请求失败！");
			}
		});
	})
})

function initRequired() {
	$("#name").validatebox({
		required : true,
		missingMessage : '不超过10个字',
		validType : "length[1,10]",
	})
	$("#phone").validatebox({
		required : true,
		missingMessage : '不超过11个字',
		validType : "length[1,11]",
	})
	$("#title").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
}


function loadRelationData(productId,channelId) {
	$.post(ctxPath+"/product/getsalechennel.htm", {id: productId}, function(r) {
		//渠道
		var $option = '<option value="">请选择</option>';
		if (r.channelList && r.channelList.length > 0){
			r.channelList.forEach(function (item) {
				if(channelId!=null && channelId==item.id){
					$option += "<option value='" + item.id + "' selected>" + item.content + "</option>";
				}else{
					$option += "<option value='" + item.id + "'>" + item.content + "</option>";
				}

			})
		}
		$("#modalChannel").empty().append($option);

		if(channelId==null){
			//产品分项
			var $td = '';
			if(r.guaranteeRelationLs && r.guaranteeRelationLs.length > 0){
				r.guaranteeRelationLs.forEach(function (item) {
					$td += '<tr>';
					$td +='<td class="dictionaryContentName">'+item.dictionaryContent.content+'</td>';
					$td +='<td class="guaranteeIntroduction">'+item.guaranteeIntroduction+'</td>';
					$td +='<td class="oldPrice">'+item.price+'</td>';
					$td +='<td>' +
						'<input type="hidden" name="guaranteeRelationId" value="'+item.id+'">' +
						'<input type="hidden" name="dictionaryContentId" value="'+item.dictionaryContentId+'">' +
						'<input type="text" name="price" class="price" value="">' +
						'</td>';
					$td += '</tr>';
				})
			}
			$("#addPlanSafetySub").empty().append($td);
		}
	})
}

function delModalPlanSafety(obj) {
	$(obj).parent().parent().remove();
}

//概述模板搜索
var contentTempData;
function doSearchContentTemp(f) {
	var searchURL = "/dictionarycontent/serachplancontent.htm";
	var url = ctxPath + searchURL;
	if (f) {
		contentTempData = $('#searchContentTempForm :input').serializeArray();
		$("#contentTempList").load(url, contentTempData);
	} else {
		var postData = contentTempData.concat($('#resultList :input').serializeArray());
		$("#contentTempList").load(url, postData);
	}
}