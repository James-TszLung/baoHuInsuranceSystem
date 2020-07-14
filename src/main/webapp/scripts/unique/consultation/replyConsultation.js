$(function() {
       
});

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

function addBtn() {
	var mainloading = new ol.loading({
		id : "xcxOrderPayAuditDiv",
		loadingClass : "main_loading"
	});
	mainloading.show();
	$("#addform").form("submit", {
		url : ctxPath + "/operate/saveConsultation.htm",
		success : function(data) {
			mainloading.hide();
			data = eval("(" + data + ")");
			if (data.success == 1 || data.success == '1') {
				visitUrl(ctxPath + "/operate/consultationPage.htm", 1, 1, 1);
			} else {
				showMessage("异常");
			}
		}
	});
}
// 新增页面返回
$("#backBtn").click(function() {
	visitUrl(ctxPath + "/operate/consultationPage.htm", 1, 1, 1);
});
$(function () {
	var overallNum=0;
	reasonData=jQuery.parseJSON(reasonData);
    $('.btn-add-person').on('click', (function addPerson() {
    	var num = 0;
		$("div[name='contentItem']").each(function() {
			var idStr = this.id;
			if (idStr != null && idStr != undefined && idStr != '') {
				idStr = idStr.charAt(idStr.length - 1);
				console.log(idStr)
				num = parseInt(idStr) + 1;
			}
		})
        var str="<div class=\"content person-item\" id='contentItemId"+num+"' name=\"contentItem\"><div class=\"m-btn btn-delete-person\">删除</div><div class=\"row\">";    	
    	str +="<span class=\"title\">投保人</span><div class=\"value\">" +
    	  "<select style=\"width: 400px;\" name=\"consultationReplyLs["+num+"].insureName\"><option value=\"本人\">本人</option>";
    	str +="<option value=\"配偶\">配偶</option>";
    	str +="<option value=\"父亲\">父亲</option>";
    	str +="<option value=\"母亲\">母亲</option>";
    	str +="<option value=\"儿子\">儿子</option>";
    	str +="<option value=\"女儿\">女儿</option>";
    	str +="<option value=\"其他\">其他</option></select>";
    	str +="</div></div><div class=\"row\"><span class=\"title\">推荐理由</span>";
    	str +="<div class=\"value\"><select style=\"width: 400px;\" name=\"consultationReplyLs["+num+"].reasonDictionaryId\">";
    	for(var i in reasonData){
    		str +="<option value='"+reasonData[i].id+"'>"+reasonData[i].content+"</option>";
    		}
        str +="</select><textarea cols=\"30\" rows=\"10\" name=\"consultationReplyLs["+num+"].reasonContent\"></textarea>" +
        		"</div></div><div class=\"row\"><span class=\"title\"><strong>推荐产品</strong> </span><div class=\"value product-list\" id=\"btn-add-product"+num+"\">";
        str +="<div class=\"m-btn btn-add-product\" data-val=\""+num+"\">新增产品</div><div class=\"product-item\" id='productItemId0' name=\"productItem"+num+"\"><div class=\"item-block\">" +
        		"<input type=\"hidden\" id=\"productIdVal"+num+"0\"  name=\"productIdName"+num+"\"/>" +
        				"<input type=\"hidden\" id=\"productIdSaveVal"+num+"0\" name=\"consultationReplyLs["+num+"].ReplyRelationProductLs[0].productId\"/> <span>产品名称:</span> " +
        		"<input type=\"text\" readonly id=\"productId"+num+"0\" >";
        str +="</div><div class=\"item-block\"><span>产品类别:</span> <input type=\"text\" readonly id=\"productTypeName"+num+"0\"></div><div class=\"item-block\" id=\"channel"+num+"0\"><span>渠道:</span>";
        str+="</div><div class=\"m-btn btn-delete-product\">删除</div></div></div></div></div>";
    	$('.person-list').append(str)
    	
    	
    }))

    $('#personList').on('click', '.btn-delete-person', function delPerson() {
        if ($('.person-list .person-item').length === 1) {
            return
        }
        $(this).closest('.person-item').remove()
    })
    $('#personList').on('click', '.btn-add-product', function addProduct() {
    	overallNum=parseInt($(this).attr("data-val"));
		 $("#add_singleser").fadeIn('fast');
			var ids = "";
		 $("input[name='productIdName"+overallNum+"']").each(function() {
				if (this.value!="") {
					ids += this.value;
					ids += ",";
				}
			})
				if (ids!="") {
					ids = ids.substring(0, ids.length - 1);
					$("#serviceProIds").val(ids);
				} else {
					$("#serviceProIds").val("");
				}
		 doSearchSinglePro(true);
		 $(this).closest('.person-item').addClass('cur')
		 
         // $(this).parent().append($('.template
			// .product-item')[0].outerHTML)
    })

    $('#personList').on('click', '.btn-delete-product', function delProduct() {
    	console.log($(this).closest('.product-list').children().length)
        if ($(this).closest('.product-list').children().length === 2) {
            return
        }
        $(this).closest('.product-item').remove()
    })
    
    $("#saveSingleProject").click(function() {
    	var rowsData = $('#single_grid').datagrid('getSelections');
    	if(rowsData.length==0){
    		showMessage("请选择产品");
    		return;
    	}
    	var num = 0;
		$("div[name='productItem"+overallNum+"']").each(function() {
			var idStr = this.id;
		
			if (idStr != null && idStr != undefined && idStr != '') {
				idStr = idStr.charAt(idStr.length - 1);
				num = parseInt(idStr);
			}
		})
		console.log("num="+num);
    	var forI=0;
    	if($('.person-item.cur .item-block:first input').val()==""){
    		$("#productId"+overallNum+"0").val(rowsData[0].name);
    		$("#productIdVal"+overallNum+"0").val(rowsData[0].id);
    		$("#productIdSaveVal"+overallNum+"0").val(rowsData[0].id);
    		$("#productTypeName"+overallNum+"0").val(rowsData[0].dictionaryContent.content);
    		var cooperationSupplierLs=rowsData[0].cooperationSupplierLs;
    		if(cooperationSupplierLs.length>0 && cooperationSupplierLs!=undefined){
    			var str="";
        		str+="<span>渠道:</span> <select name=\"consultationReplyLs["+overallNum+"].ReplyRelationProductLs[0].channelDictionaryId\">";
        		for ( var i = 0; i < cooperationSupplierLs.length; i++) {
        			str+="	<option value=\""+cooperationSupplierLs[i].dictionaryContent.id+"\">"+cooperationSupplierLs[i].dictionaryContent.content+"</option>";
				}
        		str+="</select>";
        		$("#channel"+overallNum+"0").append(str);
    		}
    		forI=1;
    	}
    	for ( var i = forI; i < rowsData.length; i++) {
    		num=num+1;
    		var str="";
    		str+="<div class=\"product-item\" id='productItemId"+num+"' name=\"productItem"+overallNum+"\"><div class=\"item-block\">" +
    				"<input type=\"hidden\" id=\"productIdVal"+overallNum+""+num+"\" value='"+rowsData[i].id+"' name=\"productIdName"+overallNum+"\"/>" +
    				"<input type=\"hidden\" value='"+rowsData[i].id+"' id=\"productIdSaveVal"+overallNum+""+num+"\" name=\"consultationReplyLs["+overallNum+"].ReplyRelationProductLs["+num+"].productId\"/><span>产品名称:</span>" +
    				"<input type=\"text\" readonly id=\"productId"+overallNum+""+num+"\" value='"+rowsData[i].name+"'></div>";
    	   str+="<div class=\"item-block\">";
    	   str+="<span>产品类别:</span> <input type=\"text\" readonly id=\"productTypeName"+overallNum+""+num+"\" value='"+rowsData[i].dictionaryContent.content+"'>	</div>";
    	   str+="	<div class=\"item-block\" id=\"channel"+overallNum+""+num+"\"><span>渠道:</span> " +
    	   		"<select name=\"consultationReplyLs["+overallNum+"].ReplyRelationProductLs["+num+"].channelDictionaryId\">";
    	   		var cooperationSupplierLs=rowsData[i].cooperationSupplierLs;
   		      if(cooperationSupplierLs.length>0 && cooperationSupplierLs!=undefined){
       		    for ( var j = 0; j < cooperationSupplierLs.length; j++) {
       			   str+="<option value=\""+cooperationSupplierLs[j].dictionaryContent.id+"\">"+cooperationSupplierLs[j].dictionaryContent.content+"</option>";
				 }
   		      }
   		   str+="</select></div><div class=\"m-btn btn-delete-product\">删除</div></div>";
   	      	$("#btn-add-product"+overallNum+"").append(str);
		}
		$("#add_singleser").fadeOut('fast');
		$('.person-item.cur').removeClass('cur')
	});
})



