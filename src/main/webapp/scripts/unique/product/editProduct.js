$(function () {
	saleChannelLSlistdata=jQuery.parseJSON(saleChannelLSlistdata);
	premiumCalculationLs=jQuery.parseJSON(premiumCalculationLs);
	// init();
	bindEvent();
	$('#insuranceTypeId').children("option:selected").trigger('change');
	loadAcBox();
})

function init() {
	$("textarea[name='brightspot'],textarea[name='insufficient'],textarea[name='attention'],textarea[name='suitable'],textarea[name='premiumsExplain']").validatebox({
		required : true,
		missingMessage : '不超过500个字',
		validType : "length[1,500]",
	});
	$("textarea[name='comment']").validatebox({
		required : true
	});
}

function bindEvent() {
	// 保存
	$("#addBtn").click( function() {
		if ($('#addform').form('enableValidation').form('validate') == false) {
			return false;
		}
		if(!tips()){
			return false;
		}
		$("#addform").form("submit", {
			url :"/baoHuInsuranceSystem/product/editProduct.htm",
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

	//附件上传
	var file_input = document.getElementById('inputGroupFile01');
	file_input.addEventListener('change', function() {
		var filesize=file_input.files[0].size;
		if (!this['value'].match(/.pdf|.PDF/i)) {
			alert('请上传pdf文件！');
			return;
		}
		if (filesize>10485760) {
			alert('请上传文件大小少于10m！');
			return;
		}
		if($("#fileIdName").children().length>=5){
			alert('最多只能上传5个文件！');
			return;
		}
		var reader = new FileReader();
		reader.readAsDataURL(this.files[0]);
		reader.onload = function(e) {
			if (this.result != null && this.result != '') {
				var imageItls = this.result;
				$.ajax({
					url :  "/baoHuInsuranceSystem/uploadFile/uploadFileItlsProduct.htm",
					data : {
						imageItls : imageItls
					},
					cache : false,
					type : "POST",
					success : function(data) {
						var result = eval('(' + data + ')');
						var pictureid = result.pictureid;
						var num = 0;
						$("div[name='fileItem']").each(function() {
							var index = $(this).attr("fileIndex");
							if (index != null && index != undefined && index != '') {
								num = parseInt(index) + 1;
							}
						})
						var fileHtml = '';
						fileHtml += '<div name="fileItem" fileIndex="'+num+'">'+
							'<a class="removeFile" href="javascript:void(0);" style="margin-right: 20px;">删除</a>'+
							'<span>'+file_input.files[0].name+'</span><input type="hidden" name="fileIds" value="'+pictureid+'">';
						fileHtml += '<input type="hidden" name="clauseFileLs['+num+'].fileAddress" value="'+result.Url+'">';
						fileHtml += '<input type="hidden" name="clauseFileLs['+num+'].fileName" value="'+file_input.files[0].name+'">';
						fileHtml += '<input type="hidden" name="clauseFileLs['+num+'].fileId" value="'+pictureid+'">';
						fileHtml += '</br></div>';
						$("#fileIdName").append(fileHtml);
					},
				})
			}
		}
	});

	// 增加合作供应商
	$('#btnAddVendor').on('click', function () {
		if($("div[name='cooperationItem']").length===20){
			alert("最多只能添加20个合作供应商");
			return false;
		}
		var num = 0;
		$("div[name='cooperationItem']").each(function() {
			var idStr = this.id;
			if (idStr != null && idStr != undefined && idStr != '') {
				idStr = idStr.charAt(idStr.length - 1);
				num = parseInt(idStr) + 1;
			}
		})
		var str="<div class=\"card bg-light my-3 vendor-item table-item\" id=\"cooperationItemId"+num+"\" name=\"cooperationItem\">";
		str +="<div class=\"card-header d-flex align-items-center\">";
		str +="<div><input type=\"hidden\" name=\"cooperationSupplierLs["+num+"].recommendStatus\" id=\"recommendStatusId"+num+"\" />";
		str+="<input class=\"d-inline-block mr-1\" id=\"recommendStatusIdCP"+num+"\" type=\"checkbox\" onclick=\"clickCheckbox("+num+")\"> <label class=\"text-center\">推荐</label>";
		str+="</div>";
		str+="<button class=\"btn1 btn-sm btn-secondary ml-auto btn-del-item1\">删除</button></div>";
		str+="<div class=\"card-body\">";
		str+="<div class=\"form-group row\">";
		str+="<label class=\"col-1 col-form-label text-right\">销售渠道</label>";
		str+="<div class=\"col-4\">";
		str+="<select class=\"form-control channel_required\" name=\"cooperationSupplierLs["+num+"].dictionaryContentId\">";
		str+="<option value=\"\">请选择</option>";
		if(saleChannelLSlistdata.length>0 && saleChannelLSlistdata!=undefined){
			for(var i in saleChannelLSlistdata){
				str+="<option value=\""+saleChannelLSlistdata[i].id+"\">"+saleChannelLSlistdata[i].content+"</option>";
			}
		}
		str+="</select></div></div>";
		str+="<div class=\"form-group row\">";
		str+="<label class=\"col-1 col-form-label text-right\"> *投保链接 </label>";
		str+="<div class=\"col-8\">";
		str+="<input class=\"form-control insure_required \" name=\"cooperationSupplierLs["+num+"].insureLink\">";
		str+="</div>";
		str+="</div>";
		str+="<div class=\"form-group row\">";
		str+="<label class=\"col-1 col-form-label text-right\"> *条款链接 </label>";
		str+="<div class=\"col-8\">";
		str+="<input class=\"form-control clause_required \" name=\"cooperationSupplierLs["+num+"].clauseLink\">";
		str+="</div></div>";
		str+="</div></div>";
		$(".vendor-list").append(str);
	})
	// 删除合作供应商
	$('.table-list').on('click', '.btn-del-item1', function delItem(e) {
		e.preventDefault();
		if (confirm('确定要删除吗')) {
			if($("div[name='cooperationItem']").length==1){
				alert('至少要保留一个合作供应商！');
			}else{
				$(this).closest('.table-item').remove();
			}
		}
	});

	//保险类型选择
	$("#insuranceTypeId").change(function(){
		if(!initLoad){
			$("#modalGuardWhatItems").empty();
			$("#modalGuardBuyRuleItems").empty();
		}
		//加载投保规则
		var rule = $(this).children('option:selected').attr("rules");
		if(rule!=undefined && rule!=null && rule!=""){
			rule = rule.replace(/'/g, '"');
			var rules = jQuery.parseJSON(rule);
			if(rules.length>0){
				var str = '';
				for(var i in rules){
					str +='<option ruleId="'+rules[i]["guaranteeContent.id"]+'" value="'+rules[i]["guaranteeContent.content"]+'">'+rules[i]["guaranteeContent.content"]+'</option>';
				}
				$("#rule").empty().append(str);
			}
		}
		//加载保费测算
		var calculation = $(this).children('option:selected').attr("calculations");
		if(calculation!=undefined && calculation!=null && calculation!="") {
			loadCalculation(calculation);
		}

	})

	//保什么
	$('#addModalGuardWhat').on('click', function (event) {
		event.preventDefault();
		const modal = $('#modalGuardWhat');
		$('.table-list .table-item').removeClass('cur');
		modal.data('cur', "add");
		$('input[name=_guaranteeTypeId_name]').val('');
		$('input[name=_guaranteeTypeId_id]').val('');
		modal.find('.guard-what-id').val('');
		modal.find('.guard-what-price').val('');
		modal.find('.guard-what-profile').val('');
		modal.find('.guard-what-detail').val('');
		modal.find('.guard-what-type').val('');
		$("#addGuardWhatSub").empty();
		$("#modalGuardWhatIndex").val("-1");
		$('#modalGuardWhat').modal('show');
	})
	$('#modalGuardWhatConfirm').on('click', function (event) {
		const modal = $('#modalGuardWhat');
		const action = modal.data('cur');
		var guaranteeTypeId = modal.find('input[name=_guaranteeTypeId_id]').val();
		var guaranteeTypeName = modal.find('input[name=_guaranteeTypeId_name]').val();
		var price = modal.find('.guard-what-price').val();
		var profile = modal.find('.guard-what-profile').val();
		var detail = modal.find('.guard-what-detail').val();
		var id = modal.find('.guard-what-id').val();
		var checkType = modal.find('.guard-what-type').val();
		if(guaranteeTypeId==""){
			alert("请选择保障类型");
			return false;
		}else if(price==""){
			alert("保额不能为空");
			return false;
		}else if(profile==""){
			alert("保障简介不能为空");
			return false;
		}else if(detail==""){
			alert("保障详情不能为空");
			return false;
		}
		var num = 0;
		var add = true;
		if(action === 'add'){
			checkType = "true";
			if($("tr[name='guaranteeRelationItem']").length>0){
				$("tr[name='guaranteeRelationItem']").each(function() {
					var index = $(this).attr("itemIndex");
					if(index != null && index != undefined && index != ''){
						num = parseInt(index) + 1;
					}
				})
			}
		}else{
			num = parseInt($("#modalGuardWhatIndex").val());
			add = false;
		}
		var subItemHtml = '';
		var _isInvalid = false;
		if($("#addGuardWhatSub").find("tr").length>0){
			$("#addGuardWhatSub").find("tr").each(function() {
				var index = $(this).index();
				var subId = $(this).find("input[name='guardWhatSubId']").val();
				var title = $(this).find("input[name='guardWhatSubTitle']").val();
				var content = $(this).find("input[name='guardWhatSubContent']").val();
				if(title==""){
					alert("子项目名称不能为空");
					_isInvalid = true;
					return false;
				}
				if(content==""){
					alert("子项目描述不能为空");
					_isInvalid = true;
					return false;
				}
				subItemHtml += '<div subIndex="'+index+'"><input type="hidden" name="guaranteeRelationLs['+num+'].guaranteeLowerLevelRelationLs['+index+'].title" type="hidden" value="'+title+'"/>';
				subItemHtml += '<input type="hidden" name="guaranteeRelationLs['+num+'].guaranteeLowerLevelRelationLs['+index+'].indemnitySubId" type="hidden" value="'+subId+'"/>';
				subItemHtml += '<input type="hidden" name="guaranteeRelationLs['+num+'].guaranteeLowerLevelRelationLs['+index+'].content" type="hidden" value="'+content+'"/>';
				subItemHtml += '<input type="hidden" name="guaranteeRelationLs['+num+'].guaranteeLowerLevelRelationLs['+index+'].flag" type="hidden" value="'+index+'"/></div>';
			})
		}
		if(_isInvalid){
			return;
		}
		var relationHtml = '';
		relationHtml += add ? '<tr class="table-item guard-what-item" name="guaranteeRelationItem" id="guaranteeRelationItem'+num+'" itemIndex="'+num+'">' : '';
		relationHtml += '<td>';
		if(!add && id!=''){
			relationHtml += '<input type="hidden" name="guaranteeRelationLs['+num+'].id" type="hidden" value="'+id+'"/>';
		}
		relationHtml +=	'<input type="hidden" name="guaranteeRelationLs['+num+'].dictionaryContentId" type="hidden" value="'+guaranteeTypeId+'"/>' +
			'<input type="hidden" name="guaranteeRelationLs['+num+'].name" type="hidden" value="'+guaranteeTypeName+'"/>' +
			'<input type="hidden" name="guaranteeRelationLs['+num+'].price" type="hidden" value="'+price+'"/>' +
			'<input type="hidden" name="guaranteeRelationLs['+num+'].guaranteeIntroduction" type="hidden" value="'+profile+'"/>' +
			'<input type="hidden" name="guaranteeRelationLs['+num+'].guaranteeDetail" type="hidden" value="'+detail+'"/>' +
			'<div style="display: none;" name="subItems'+num+'">'+
			subItemHtml +
			'</div>' +
			'<div class="form-check d-flex justify-content-center">' +
			'<input class="form-check-input" type="checkbox" name="guaranteeRelationLs['+num+'].type" value="1" '+ (checkType=="true" ? "checked" : "") +'/>' +
			'</div></td>' +
			'<td>'+guaranteeTypeName+'</td>' +
			'<td>'+price+'</td>' +
			'<td>'+profile+'</td>' +
			'<td>' +
			'<button class="btn1 btn-sm btn-secondary mr-1" onclick="delModalGuardWhat(this)">删除</button>' +
			'<button class="btn1 btn-sm btn-secondary editModalGuardWhat" data-toggle="modal" data-action="edit">编辑</button>' +
			'</td>';
		relationHtml += add ? '</tr>' : '';
		if(add){
			$("#modalGuardWhatItems").append(relationHtml);
		}else{
			// $('.guard-what-list .guard-other-item.cur').replaceWith(relationHtml);
			// $('.guard-what-list .guard-other-item.cur').empty().append(relationHtml);
			$("#modalGuardWhatItems").find("#guaranteeRelationItem"+num).empty().append(relationHtml);
		}
		$('#modalGuardWhat').modal('hide');
	});
	$('.table-list').on('click', '.editModalGuardWhat', function editItem(event) {
		event.preventDefault();
		const modal = $('#modalGuardWhat');
		$('.table-list .table-item').removeClass('cur');
		$(this).closest('.table-item').addClass('cur');
		modal.data('cur', "edit");
		var trObj = $(this).parent().parent();
		var num = parseInt($(trObj).attr("itemIndex"));
		$("#modalGuardWhatIndex").val(num);
		var guaranteeTypeId = $(trObj).find("input[name='guaranteeRelationLs["+num+"].dictionaryContentId']").val();
		var guaranteeTypeName = $(trObj).find("input[name='guaranteeRelationLs["+num+"].name']").val();
		var price = $(trObj).find("input[name='guaranteeRelationLs["+num+"].price']").val();
		var profile= $(trObj).find("input[name='guaranteeRelationLs["+num+"].guaranteeIntroduction']").val();
		var detail = $(trObj).find("input[name='guaranteeRelationLs["+num+"].guaranteeDetail']").val();
		var checkType = $(trObj).find("input[name='guaranteeRelationLs["+num+"].type']").is(':checked');
		modal.find('input[name=_guaranteeTypeId_id]').val(guaranteeTypeId);
		modal.find('input[name=_guaranteeTypeId_name]').val(guaranteeTypeName);
		modal.find('.guard-what-price').val(price);
		modal.find('.guard-what-profile').val(profile);
		modal.find('.guard-what-detail').val(detail);
		modal.find('.guard-what-type').val(checkType);
		if($(trObj).find("input[name='guaranteeRelationLs["+num+"].id']").length>0 && $(trObj).find("input[name='guaranteeRelationLs["+num+"].id']").val()!=''){
			modal.find('.guard-what-id').val($(trObj).find("input[name='guaranteeRelationLs["+num+"].id']").val());
		}
		var subItemHtml = '';
		if($(trObj).find("div[name='subItems"+num+"']").children().length>0){
			$(trObj).find("div[name='subItems"+num+"']").children().each(function() {
				var subIndex = $(this).attr("subIndex");
				var subId = $(this).find("input[name='guaranteeRelationLs["+num+"].guaranteeLowerLevelRelationLs["+subIndex+"].indemnitySubId']").val();
				var title = $(this).find("input[name='guaranteeRelationLs["+num+"].guaranteeLowerLevelRelationLs["+subIndex+"].title']").val();
				var content = $(this).find("input[name='guaranteeRelationLs["+num+"].guaranteeLowerLevelRelationLs["+subIndex+"].content']").val();
				subItemHtml += '<tr class="table-item sub-item"><td>' +
					'<input type="hidden" class="form-control" name="guardWhatSubId" value="'+subId+'"/>'+
					'<input type="hidden" class="form-control" name="guardWhatSubTitle" value="'+title+'"/>'+title+'</td>';
				subItemHtml += '<td><input type="text" class="form-control" name="guardWhatSubContent" value="'+content+'" maxlength="500"/></td>';
				subItemHtml += '<td><button class="btn1 btn-sm btn-secondary mr-1" onclick="delGuardWhatSubItem(this)">删除</button></td></tr>';
			})
		}
		$('#addGuardWhatSub').empty().append(subItemHtml);

		$('#modalGuardWhat').modal('show');
	});

	// 增值服务
	$('#addModalGuardBuyService').on('click', function (event) {
		event.preventDefault();
		const modal = $('#modalGuardBuyService');
		$('.table-list .table-item').removeClass('cur');
		modal.data('cur', "add");
		modal.find('.guard-service-content').val('');
		modal.find('.guard-service-desc').val('');
		$('#modalGuardBuyService').modal('show');
	});
	$('#modalGuardBuyServiceConfirm').on('click', function (event) {
		const modal = $('#modalGuardBuyService');
		const template = $('#template .guard-service-item');
		var content = modal.find('.guard-service-content').val();
		var desc = modal.find('.guard-service-desc').val();
		if(content==""){
			alert("保障内容不能为空")
			return false;
		}
		if(desc==""){
			alert("描述不能为空")
			return false;
		}
		template.find('.guard-service-content').text(content);
		template.find('.guard-service-desc').text(desc);
		template.find('.btn-edit-item').attr('data-content', content);
		template.find('.btn-edit-item').attr('data-desc', desc);
		template.find('.guard-title').val(content);
		template.find('.guard-content').val(desc);

		const action = modal.data('cur');
		var num = 0;
		if (action === 'add') {
			$('.guard-service-list .guard-service-item').each(function () {
				var index = $(this).attr("indexItem");
				if (index != null && index != undefined && index != '') {
					num = parseInt(index)+1;
				}
			})
		} else {
			num = parseInt($("#modalGuardBuyServiceIndex").val());
		}
		template.attr("indexItem",num);
		template.find('.guard-title').attr('name', 'incrementRelationLs['+num+'].title');
		template.find('.guard-content').attr('name', 'incrementRelationLs['+num+'].content');
		if (action === 'add') {
			$('.guard-service-list').append(template.clone());
		}else{
			$('.guard-service-list .guard-service-item.cur').replaceWith(template.clone());
		}
		$('#modalGuardBuyService').modal('hide');
	});
	$('.table-list').on('click', '.editModalGuardBuyService', function editItem(event) {
		event.preventDefault();
		const modal = $('#modalGuardBuyService');
		$('.table-list .table-item').removeClass('cur');
		$(this).closest('.table-item').addClass('cur');
		modal.data('cur', "edit");
		modal.find('.guard-service-content').val($(this).data('content'));
		modal.find('.guard-service-desc').val($(this).data('desc'));
		var trObj = $(this).parent().parent();
		var num = $(trObj).attr("indexItem");
		$("#modalGuardBuyServiceIndex").val(num);
		$('#modalGuardBuyService').modal('show');
	});

	// 续保规则
	$('#addModalGuardRenewRule').on('click', function (event) {
		event.preventDefault();
		const modal = $('#modalGuardRenewRule');
		$('.table-list .table-item').removeClass('cur');
		modal.data('cur', "add");
		modal.find('.guard-renew-rule-content').val('');
		modal.find('.guard-renew-rule-desc').val('');
		$('#modalGuardRenewRule').modal('show');
	});
	$('#modalGuardRenewRuleConfirm').on('click', function (event) {
		const modal = $('#modalGuardRenewRule');
		const template = $('#template .guard-renew-rule-item');
		var content = modal.find('.guard-renew-rule-content').val();
		var desc = modal.find('.guard-renew-rule-desc').val();
		if(content==""){
			alert("保障内容不能为空")
			return false;
		}
		if(desc==""){
			alert("描述不能为空")
			return false;
		}
		template.find('.guard-renew-rule-content').text(content);
		template.find('.guard-renew-rule-desc').text(desc);
		template.find('.btn-edit-item').attr('data-content', content);
		template.find('.btn-edit-item').attr('data-desc', desc);
		template.find('.guard-renew-title').val(content);
		template.find('.guard-renew-content').val(desc);

		const action = modal.data('cur');
		var num = 0;
		if (action === 'add') {
			$('.guard-renew-rule-list .guard-renew-rule-item').each(function () {
				var index = $(this).attr("indexItem");
				if (index != null && index != undefined && index != '') {
					num = parseInt(index)+1;
				}
			})
		} else {
			num = parseInt($("#modalGuardRenewRuleIndex").val());
		}
		template.attr("indexItem",num);
		template.find('.guard-renew-title').attr('name', 'renewRelationLs['+num+'].title');
		template.find('.guard-renew-content').attr('name', 'renewRelationLs['+num+'].content');
		if (action === 'add') {
			$('.guard-renew-rule-list').append(template.clone());
		}else{
			$('.guard-renew-rule-list .guard-renew-rule-item.cur').replaceWith(template.clone());
		}
		$('#modalGuardRenewRule').modal('hide');
	});
	$('.table-list').on('click', '.editModalGuardRenewRule', function editItem(event) {
		event.preventDefault();
		const modal = $('#modalGuardRenewRule');
		$('.table-list .table-item').removeClass('cur');
		$(this).closest('.table-item').addClass('cur');
		modal.data('cur', "edit");
		modal.find('.guard-renew-rule-content').val($(this).data('content'));
		modal.find('.guard-renew-rule-desc').val($(this).data('desc'));
		var trObj = $(this).parent().parent();
		var num = $(trObj).attr("indexItem");
		$("#modalGuardRenewRuleIndex").val(num);
		$('#modalGuardRenewRule').modal('show');
	});

	// 投保规则
	$('#addModalGuardBuyRule').on('click', function (event) {
		event.preventDefault();
		const modal = $('#modalGuardBuyRule');
		$('.table-list .table-item').removeClass('cur');
		modal.data('cur', "add");
		modal.find('.guard-buy-rule-content').val('');
		modal.find('.guard-buy-rule-title').val('');
		modal.find(".guard-buy-rule-title").find("option").eq(0).prop("selected", 'selected');
		modal.find(".guard-buy-rule-title").find("option").eq(0).trigger('change');
		$('#modalGuardBuyRule').modal('show');
	});
	$('#modalGuardBuyRuleConfirm').on('click', function (event) {
		const modal = $('#modalGuardBuyRule');
		const template = $('#template .guard-buy-rule-item');
		var content = modal.find('.guard-buy-rule-content').val();
		var title = modal.find('.guard-buy-rule-title').val();
		var dictionaryContentId = modal.find('.guard-buy-rule-title').find("option:selected").attr("ruleId");
		if(title==""){
			alert("规则类别不能为空");
			return;
		}
		if(content==""){
			alert("规则内容不能为空");
			return;
		}
		template.find('.guard-buy-rule-content').text(content);
		template.find('.guard-buy-rule-title').text(title);
		template.find('.btn-edit-item').attr('data-content', content);
		template.find('.btn-edit-item').attr('data-title', title);
		template.find('.btn-edit-item').attr('data-id', dictionaryContentId);
		template.find('.guard-buy-dictionaryContentId').val(dictionaryContentId);
		template.find('.guard-buy-content').val(content);
		const action = modal.data('cur');
		var num = 0;
		if (action === 'add') {
			$('.guard-buy-rule-list .guard-buy-rule-item').each(function () {
				var index = $(this).attr("indexItem");
				if (index != null && index != undefined && index != '') {
					num = parseInt(index)+1;
				}
			})
		} else {
			num = parseInt($("#modalGuardBuyRuleIndex").val());
		}
		template.attr("indexItem",num);
		template.find('.guard-buy-dictionaryContentId').attr('name', 'guaranteedRuleLs['+num+'].dictionaryContentId');
		template.find('.guard-buy-content').attr('name', 'guaranteedRuleLs['+num+'].content');
		if (action === 'add') {
			$('.guard-buy-rule-list').append(template.clone());
		}else{
			$('.guard-buy-rule-list .guard-buy-rule-item.cur').replaceWith(template.clone());
		}

		$('#modalGuardBuyRule').modal('hide');
	});
	$('.table-list').on('click', '.editModalGuardBuyRule', function editItem(event) {
		event.preventDefault();
		const modal = $('#modalGuardBuyRule');
		$('.table-list .table-item').removeClass('cur');
		$(this).closest('.table-item').addClass('cur');
		modal.data('cur', "edit");
		modal.find('.guard-buy-rule-content').val($(this).data('content'));
		modal.find('.guard-buy-rule-title').val($(this).data('title'));
		var trObj = $(this).parent().parent();
		var num = $(trObj).attr("indexItem");
		$("#modalGuardBuyRuleIndex").val(num);
		$('#modalGuardBuyRule').modal('show');
	});

	// 保费计算
	$('#addCaculateItem').on('click', function addCaculateItem() {
		var num = 0;
		$('.caculate-list tbody').find('.caculate-item').each(function () {
			var index = $(this).attr("indexItem");
			if (index != null && index != undefined && index != '') {
				num = parseInt(index)+1;
			}
		})
		const template = $('#template .caculate-item');
		var expands = $('#template .expands').val();
		template.attr("indexItem",num);
		if(expands!=undefined && expands!=null && expands!=''){
			var datas = expands.split(",");
			if(datas.length>0){
				for(var i in datas){
					template.find('.'+datas[i]).attr("name","premiumCalculationLs["+num+"]."+datas[i]);
				}
			}
		}
		template.find('.flag').attr("name","premiumCalculationLs["+num+"].flag")
		$('.caculate-list tbody ').append(template.clone());
	});

	// 删除
	$('.table-list').on('click', '.btn-del-item', function delItem(e) {
		e.preventDefault();
		if (confirm('确定要删除吗')) {
			$(this).closest('.table-item').remove();
		}
	});

	//条款附件删除
	$('#fileIdName').on('click', '.removeFile', function delItem(e) {
		e.preventDefault();
		var id = $(this).parent().find("input[name='fileIds']").val();
		var fileVal = $("#deleteFileIds").val();
		if(fileVal==''){
			fileVal = id;
		}else{
			fileVal = fileVal +"," +id;
		}
		$("#deleteFileIds").val(fileVal);
		$(this).parent().remove();
	});

	$("#backBtn").click(function() {
		visitUrl(ctxPath + "/product/productPage.htm", 1, 1, 1);
	});

  	$('#chooseSimilarLabel').on('shown.bs.modal', function (event) {
         var serviceDicIds = $("input[name='similarLabelId']").val();
      	$("#searchSimilarLabelForm").find("input[name='serviceDicIds']").val(serviceDicIds);
      	$("#searchSimilarLabelForm").find("input[name='content']").val("");
      	doSearchSimilarLabel(true);
      })

	$('#chooseCompany').on('shown.bs.modal', function (event) {
		doSearchCompany(true);
	})

	$('#chooseSpecialLabel').on('shown.bs.modal', function (event) {
		doSearchSpecialLabel(true);
	})

	//承保公司选择确定
	$("#companyConfirm").click(function(){
		var row = $('#result_grid').datagrid('getSelected');
		$("#companyName").val(row.companyName);
		$("#insuranceCompanyId").val(row.id);
		$("#companyId").val(row.company_id);
		$('#chooseCompany').modal('hide');
	})

	//产品同类标签选择确定
	$("#similarLabelConfirm").click(function(){
		var row = $('#similarLabel_result_grid').datagrid('getSelected');
		var str = '<label class="tab__tag-btn"><input type="hidden" name="similarLabelId" value="'+row.id+'"><span class="text">'+row.name+'</span><a class="btn1 btn-del-tag">删除</a></label>';
		$("#similarLabelId").empty().append(str);
		$('#chooseSimilarLabel').modal('hide');
	})
	//特色产品标签选择确定
	$("#specialLabelConfirm").click(function(){
		var rowsData = $('#specialLabel_result_grid').datagrid('getSelections');
		var str = '';
		for ( var i = 0; i < rowsData.length; i++) {
			var row = rowsData[i];
			str += '<label class="tab__tag-btn"><input type="hidden" name="specialLabelIds" value="'+row.id+'"><span class="text">'+row.content+'</span><a class="btn1 btn-del-tag">删除</a></label>';
		}
		$("#specialLabelIds").append(str);
		$('#chooseSpecialLabel').modal('hide');
	})

	//标签删除
	$("#pills-tag").on('click', '.btn-del-tag', function delTag(e) {
		if (confirm('确定要删除吗')) {
			$(this).parent().remove();
		}
	});

}

//合作供应商推荐
function clickCheckbox(num) {
	if ($("#recommendStatusIdCP"+num) .is(':checked')) {
		$("#recommendStatusId"+num).val(2);
	} else {
		$("#recommendStatusId"+num).val(1);
	}
};

//保什么 删除
function delModalGuardWhat(obj) {
	$(obj).parent().parent().remove();
}
//保什么 删除子项目
function delGuardWhatSubItem(obj) {
	$(obj).parent().parent().remove();
}

//加载保费测算
function loadCalculation(calculation){
     calculation = calculation.replace(/'/g, '"');
     var calculations = jQuery.parseJSON(calculation);
     if(calculations.length>0){
         var str = '<thead class="thead-dark"><tr>';
         var thStr = '';
         var tdStr = '';
         var expands = '';
         var flag = true;
         for(var i in calculations){
             var content = calculations[i]["guaranteeContent.content"]
             var expand = calculations[i].expand;
             expands += expand + ",";
             thStr +='<th>'+content+'</th>';
             tdStr += '<td>';
             if(flag){
                 tdStr += '<input type="hidden" class="flag" name="premiumCalculationLs[0].flag" value="2">';
                 flag = false;
             }
             if(expand=='age'){//年龄
                 tdStr += ' <select class="form-control cla_required '+expand+'" name="premiumCalculationLs[0].age">' +
					 ' <option value="0">0</option>' +
					 ' <option value="3">3</option>' +
					 ' <option value="5">5</option>' +
					 '<option value="10">10</option>' +
					 '<option value="15">15</option>' +
					 '<option value="20">20</option>' +
					 '<option value="25">25</option>' +
					 '<option value="30">30</option>' +
					 '<option value="35">35</option>' +
					 '<option value="40">40</option>' +
					 '<option value="45">45</option>' +
					 '<option value="50">50</option>' +
					 '<option value="55">55</option>' +
					 '<option value="60">60</option>' +
					 '<option value="65">65</option>' +
					 '<option value="70">70</option>' +
					 '<option value="75">75</option>' +
					 '<option value="80">80</option>' +
                     '</select>'
             }else if(expand=='gender'){//性别
                 tdStr += '<select class="form-control cla_required '+expand+'" name="premiumCalculationLs[0].gender">' +
                     ' <option value="1">男</option>' +
                     '<option value="2">女</option>' +
                     '</select>'
             }else if(expand=='socialSecurity'){//社保：有与无
                 tdStr += ' <select class="form-control cla_required '+expand+'" name="premiumCalculationLs[0].socialSecurity">' +
                     ' <option value="0">无</option>' +
                     '<option value="1">有</option>' +
                     '</select>'
             }else if(expand=='insuredAmount' || expand=='firstPremiums' || expand=='totalPremium'){//价钱，单位元
                 tdStr += '<input type="number" class="form-control cla_required '+expand+'" name="premiumCalculationLs[0].'+expand+'">';
             }else if(expand=='guaranteePeriod' || expand=='paymentPeriod' || expand=='guarantee'){//字符串，输入框
                 tdStr += '<input type="text" class="form-control cla_required '+expand+'" name="premiumCalculationLs[0].'+expand+'">';
             }
             tdStr += '</td>';
         }
         thStr +='<th>操作</th>';
         tdStr += '<td><button class="btn1 btn-sm btn-secondary mr-1" onclick="delModalGuardWhat(this)">删除</button></td>';
         str += thStr;
         str += '</tr></thead><tbody class="table-list">';
         if(!initLoad){
           str += '<tr class="table-item caculate-item" indexItem="0">' + tdStr + '</tr>';
         }
         str += '</tbody>';
         $('#template .caculate-item').empty().append(tdStr);
         if(expands.length>0){
             expands = expands.substring(0,expands.length-1);
         }
         $('#template .expands').val(expands);
         $("#caculateList").empty().append(str);
         if(initLoad){
           for(var num=0;num<premiumCalculationLs.length;num++){
              var obj = premiumCalculationLs[num];
              const template = $('#template .caculate-item');
              var expands = $('#template .expands').val();
              template.attr("indexItem",num);
              var tempHtml = template.clone();
              if(expands!=undefined && expands!=null && expands!=''){
                  var datas = expands.split(",");
                  if(datas.length>0){
                      for(var i in datas){
						  tempHtml.find('.'+datas[i]).attr("name","premiumCalculationLs["+num+"]."+datas[i]);
						  tempHtml.find('.'+datas[i]).val(obj[datas[i]]);
                          if(tempHtml.find('.'+datas[i]).is("select")){
							  tempHtml.find('.'+datas[i]).find("option[value="+obj[datas[i]]+"]").attr("selected",true);
							  tempHtml.find('.'+datas[i]).find("option[value="+obj[datas[i]]+"]").trigger('change');
                          }
                      }
                  }
              }
			   tempHtml.find('.flag').attr("name","premiumCalculationLs["+num+"].flag")
              $('.caculate-list tbody ').append(tempHtml.clone());
           }
           initLoad = false;
         }
     }
}

//提交校验
function tips(e){
	var _isInvalid = false;
	//基础信息
	var name = $("#name").val().trim();
	if(name==""){
		alert('产品名不能为空');
		return false;
	}
	var insuranceCompanyId = $("#insuranceCompanyId").val();
	if(insuranceCompanyId==''){
		alert('承保公司不能为空');
		return false;
	}
	// if($("div[name='fileItem']").length===0){
	// 	alert('请上传条款附件');
	// 	return false;
	// }
	if(!_isInvalid && $(".channel_required").length>0){
		$(".channel_required").each(function() {
			if($(this).val()==''){
				alert('销售渠道不能为空');
				_isInvalid = true;
				return false;
			}
		})
	}
	if(!_isInvalid && $(".insure_required").length>0){
		$(".insure_required").each(function() {
			if($(this).val()==''){
				alert('投保链接不能为空');
				_isInvalid = true;
				return false;
			}
		})
	}
	if(!_isInvalid && $(".clause_required").length>0){
		$(".clause_required").each(function() {
			if($(this).val()==''){
				alert('条款链接不能为空');
				_isInvalid = true;
				return false;
			}
		})
	}
	//保什么
	if(!_isInvalid && $("#modalGuardWhatItems").find(".guard-what-item").length===0){
		alert('保什么不能为空');
		return false;
	}
	//保费测算
	if(!_isInvalid && $("#caculateList .caculate-item").length==0){
		alert('保费测算不能为空');
		return false;
	}
	if(!_isInvalid && $("#addform").find(".cla_required").length>0){
		$("#addform").find(".cla_required").each(function() {
			if($(this).val()==''){
				alert('请补全保费测算信息');
				_isInvalid = true;
				return false;
			}
		})
	}
	//同类产品标签
	var similarLabel = $("input[name='similarLabelId']").val();
	if(!_isInvalid && (similarLabel==undefined || similarLabel==null || similarLabel=="")){
		alert('请选择同类产品标签');
		return false;
	}
	if(_isInvalid){
		return false;
	}
	return true;
};


function addCompany() {
	$('#chooseCompany').modal('show');
}

var companyData;
function doSearchCompany(f) {
	var searchURL = "/product/serachCompany.htm";
	if (f) {
		var url = ctxPath + searchURL;
		companyData = $('#searchCompanyForm :input').serializeArray();
		$("#companyList").load(url, companyData);
	} else {
		var url = ctxPath + searchURL;
		var postData = companyData.concat($('#resultCompanyList :input').serializeArray());
		$("#companyList").load(url, postData);
	}
}


//同类产品标签添加
function addSimilarLabel() {
	$('#chooseSimilarLabel').modal('show');
}
//同类产品标签搜索
var similarLabelData;
function doSearchSimilarLabel(f) {
	var searchURL = "/product/serachSimilarLabel.htm";
	var url = ctxPath + searchURL;
	if (f) {
		similarLabelData = $('#searchSimilarLabelForm :input').serializeArray();
		$("#similarLabelList").load(url, similarLabelData);
	} else {
		var postData = similarLabelData.concat($('#resultSimilarLabelList :input').serializeArray());
		$("#similarLabelList").load(url, postData);
	}
}

//产品特色标签添加
function addSpecialLabel() {
	var serviceDicIds = "";
	$("#specialLabelIds").find("input[name='specialLabelIds']").each(function () {
		serviceDicIds += $(this).val()+",";
	})
	$("#searchSpecialLabelForm").find("input[name='serviceDicIds']").val(serviceDicIds);
	$("#searchSpecialLabelForm").find("input[name='content']").val("");
	$('#chooseSpecialLabel').modal('show');
}
//产品特色标签搜索
var specialLabelData;
function doSearchSpecialLabel(f) {
	var searchURL = "/product/serachSpecialLabel.htm";
	var url = ctxPath + searchURL;
	if (f) {
		specialLabelData = $('#searchSpecialLabelForm :input').serializeArray();
		$("#specialLabelList").load(url, specialLabelData);
	} else {
		var postData = specialLabelData.concat($('#resultSpecialLabelList :input').serializeArray());
		$("#specialLabelList").load(url, postData);
	}
}



//保障类型选择
function loadAcBox() {
	$('#_guaranteeTypeId').acBox({
		source: ctxPath+'/indemnity/selectIndemnity.htm?acbox=y&callback=?'
		,multiple : false
		,multi_limit : 1
		, navi_num: 5
		,numPerPage:25
		,relFields: 'insuranceTypeId'
		,sel_callback: function () {
			var guaranteeTypeName = $('input[name=_guaranteeTypeId_name]').val();
			var guaranteeTypeId = $('input[name=_guaranteeTypeId_id]').val();
			$("input[name=guaranteeName]").val(guaranteeTypeName);
			$("input[name=guaranteeId]").val(guaranteeTypeId);
			loadSubData(guaranteeTypeId);
		}
	});
}

function loadSubData(indemnityId) {
	$.post(ctxPath+"/indemnity/selectIndemnitySub.htm", {id: indemnityId}, function(r) {
		if (r.subLs && r.subLs.length > 0){
			var subItems = '';
			r.subLs.forEach(function (item) {
				subItems += '<tr class="table-item sub-item">' +
					'<td><input type="hidden" class="form-control" name="guardWhatSubId" value="'+item.id+'"/>' +
					'<input type="hidden" class="form-control" name="guardWhatSubTitle" value="'+item.name+'"/>'+item.name+'</td>' +
					'<td><input type="text" class="form-control" name="guardWhatSubContent" maxlength="500"/></td>' +
					'<td><button class="btn1 btn-sm btn-secondary mr-1" onclick="delGuardWhatSubItem(this)">删除</button></td>' +
					'</tr>';
			})
			$("#addGuardWhatSub").empty().append(subItems);
		}
	})
}


