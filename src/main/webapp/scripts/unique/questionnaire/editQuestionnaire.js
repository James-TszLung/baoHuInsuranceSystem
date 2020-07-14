
$(function () {
     initRequired();
    //添加成员
    $('#addMemberBtn').on('click', function (){
        if($("#memberList").find(".member-item").length===4){
            alert("最多只能添加4个成员信息");
            return false;
        }
        var num = 0;
        $("#memberList").find(".member-item").each(function() {
            var index = $(this).attr("itemIndex");
            if (index != null && index != undefined && index != '') {
                num = parseInt(index)+1;
            }
        })
        const template = $('#template').find('.member-item-temp').find(".member-item");
        var cloneHtml = template.clone();
        cloneHtml.attr("id","memberItem"+num);
        cloneHtml.attr("name","memberItem");
        cloneHtml.attr("itemIndex",num);
        cloneHtml.find('.member_name').attr("name","memberInformationLs["+num+"].name");
        cloneHtml.find('.member_name').attr("onBlur","changeName(this,'"+num+"')").removeClass("member_name");
        cloneHtml.find('.member_sex1').attr("name","memberInformationLs["+num+"].sex").removeClass("member_sex1");
        cloneHtml.find('.member_sex2').attr("name","memberInformationLs["+num+"].sex").removeClass("member_sex2");
        cloneHtml.find('.member_age').attr("name","planRiskLs["+num+"].age").removeClass("member_age");
        cloneHtml.find('.member_relationshipId').attr("name","planRiskLs["+num+"].relationshipId").removeClass("member_relationshipId");
        cloneHtml.find('.member_profession').attr("name","planRiskLs["+num+"].profession").removeClass("member_profession");
        $("#memberList").append(cloneHtml);

        //参与方案定制的家庭成员的已有保险 增加
        const template1 = $('#template').find('.information-item-temp').find(".information-item");;
        var cloneHtml1 = template1.clone();
        cloneHtml1.attr("id","informationItem"+num);
        cloneHtml1.attr("name","informationItem");
        cloneHtml1.attr("itemIndex",num);
        cloneHtml1.find('.information_name').attr("name","memberInformationLs["+num+"].userName").removeClass("information_name");
        cloneHtml1.find('.information_companyName').attr("name","memberInformationLs["+num+"].companyName").removeClass("information_companyName");
        cloneHtml1.find('.information_productName').attr("name","memberInformationLs["+num+"].productName").removeClass("information_productName");
        cloneHtml1.find('.information_situation').attr("name","memberInformationLs["+num+"].situation").removeClass("information_situation");
        cloneHtml1.find('.information_years').attr("name","memberInformationLs["+num+"].years").removeClass("information_years");
        cloneHtml1.find('.information_payMoney').attr("name","memberInformationLs["+num+"].payMoney").removeClass("information_payMoney");
        cloneHtml1.find('.information_guaranteeYears').attr("name","memberInformationLs["+num+"].guaranteeYears").removeClass("information_guaranteeYears");
        $("#informationList").append(cloneHtml1);

    })
    //删除成员
    $("#memberList").on('click', '.delete-member-button', function deletItem(event){
        if($("#memberList").find(".member-item").length==1){
            alert("至少保留一个成员信息");
        }else{
           var num = $(this).parents(".member-item").attr("itemIndex");
            $(this).parents(".member-item").remove();
            $("#informationItem"+num).remove();
        }
    })
    //添加个人年收入统计
     $('#addIncomeBtn').on('click', function (){
        var num = 0;
        $("#incomeList").find(".income-item").each(function() {
            var index = $(this).attr("itemIndex");
            if (index != null && index != undefined && index != '') {
               num = parseInt(index)+1;
            }
        })
        const template = $('#template').find('.income-item-temp').find(".income-item");
        var cloneHtml = template.clone();
        cloneHtml.attr("id","incomeItem"+num);
        cloneHtml.attr("name","incomeItem");
        cloneHtml.attr("itemIndex",num);
        cloneHtml.find('.income-name').attr("name","incomeRLQnaireLs["+num+"].name").removeClass("income-name");
        cloneHtml.find('.income-money').attr("name","incomeRLQnaireLs["+num+"].money").removeClass("income-money");
        cloneHtml.find('.income-source').attr("name","incomeRLQnaireLs["+num+"].source").removeClass("income-source");
        $("#incomeList").append(cloneHtml);
     })
     //删除个人年收入统计
     $("#incomeList").on('click', '.delete-income-button', function deletItem(event){
           $(this).parents(".income-item").remove();
     })
    //添加家庭负债情况
    $('#addLiabilitiesBtn').on('click', function (){
        var num = 0;
        $("#liabilitiesList").find(".liabilities-item").each(function() {
            var index = $(this).attr("itemIndex");
            if (index != null && index != undefined && index != '') {
               num = parseInt(index)+1;
            }
        })
        const template = $('#template').find('.liabilities-item-temp').find(".liabilities-item");
        var cloneHtml = template.clone();
        cloneHtml.attr("id","liabilitiesItem"+num);
        cloneHtml.attr("name","liabilitiesItem");
        cloneHtml.attr("itemIndex",num);
        cloneHtml.find('.liabilities-loanType').attr("name","liabilitiesRLQnaireLs["+num+"].loanType").removeClass("liabilities-loanType");
        cloneHtml.find('.liabilities-repaymentType').attr("name","liabilitiesRLQnaireLs["+num+"].repaymentType").removeClass("liabilities-repaymentType");
        cloneHtml.find('.liabilities-loanMoney').attr("name","liabilitiesRLQnaireLs["+num+"].loanMoney").removeClass("liabilities-loanMoney");
        cloneHtml.find('.liabilities-years').attr("name","liabilitiesRLQnaireLs["+num+"].years").removeClass("liabilities-years");
        cloneHtml.find('.liabilities-interestRate').attr("name","liabilitiesRLQnaireLs["+num+"].interestRate").removeClass("liabilities-interestRate");
        cloneHtml.find('.liabilities-surplus').attr("name","liabilitiesRLQnaireLs["+num+"].surplus").removeClass("liabilities-surplus");
        cloneHtml.find('.liabilities-supply').attr("name","liabilitiesRLQnaireLs["+num+"].supply").removeClass("liabilities-supply");
        $("#liabilitiesList").append(cloneHtml);
     })
    //删除家庭负债情况
    $("#liabilitiesList").on('click', '.delete-liabilities-button', function deletItem(event){
          $(this).parents(".liabilities-item").remove();
    })
    //返回
    $("#backBtn").click(function() {
    	visitUrl(ctxPath + "/operate/questionnairePage.htm", 1, 1, 1);
    });
    // 保存
    $("#addBtn").click( function() {
        if ($('#addform').form('enableValidation').form('validate') == false) {
            return false;
        }
        $("#addform").form("submit", {
            url : ctxPath+"/operate/editQuestionnaire.htm",
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

function changeName(obj,index){
    var name = $(obj).val();
    $("#informationList").find("input[name='memberInformationLs["+index+"].userName']").val(name);
}

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
	$("#wxNumber").validatebox({
		required : true,
		missingMessage : '不超过50个字',
		validType : "length[1,50]",
	})
}