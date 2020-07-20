<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/geli_functions.tld" prefix="geli"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newStyle/normalize.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newStyle/bootstrap/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/styles/newStyle/bootstrap/bootstrap.min.js"></script>
<style>
    ul {overflow: hidden;margin-bottom:auto;}

    .ui_addUser ul li {width: 100%;}
    .ui_addUser ul li label {width: 150px;position: static;}
    input {border: 1px solid #bbb;}
    #template {display: none;}
    table td,table th {text-align: center;}
    .tab-block {padding: 8px;}
    .tab__tag-title {margin: 8px 0 12px;}
    .tab__tag-list {margin-bottom: 32px;}
    .tab__tag-btn input {width: 0;height: 0;opacity: 0;position: absolute;}
    .tab__tag-btn input:checked       ~.text {background-color: #ff756c;}
    .tab__tag-btn {display: inline-block;position: relative;margin-right: 16px;}
    .tab__tag-btn .text {
        display: inline-block;
        width: 100%;
        height: 100%;
        line-height: 40px;
        text-align: center;
        white-space: nowrap;
        color: white;
        background-color: grey;
        border-radius: 4px;
        transition: all 0.3s ease;
        padding: 0px 20px;
    }
    .nav .nav-link {color: #343a40;}
    /*=====================*/
    .nav {height: auto;background: none;}
    input {width: auto;padding-left: initial;height: auto;border: none;}
    .btn1{border: 1px solid transparent;}
    .bottom-panel{margin-top: 10px;}
    .bottom-panel .btn1{padding: .575rem .85rem;font-size: 1rem;border-radius: 4px;}
    .btn-block{
        font-size: 18px;
        font-weight: 600;
        color: black;
        background-color: transparent;
        border: 1px solid transparent;
        height: 24px;
        outline: none !important;
    }
    .btn-link:hover {
        color: black !important;
        text-decoration: none !important;
    }
    .modal-backdrop {
        position: initial !important;
    }
    .btn2{
        border: 1px solid transparent;
        padding: .375rem .75rem;
        border-radius: .25rem;
    }
    label.col-1.col-form-label.text-right {
        padding-left: initial;
    }
    *, ::after, ::before {
        box-sizing: inherit !important;
    }
    .content{
        box-sizing: border-box !important;
    }
</style>
<div class="content" >
    <div>
        <div class="card">
            <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                    <button class="btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        基础信息
                        <i class="icon el-icon-arrow-up"></i>
                    </button>
                </h2>
            </div>
            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne">
                <div class="card-body">
                    <div class="tab-pane fade show active tab-info card p-3" id="pills-info">
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">产品名称</label>
                            <div class="col-5 form-control">${sc.name}</div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">保险类型</label>
                            <div class="col-5 form-control">${sc.dictionaryContent.content}</div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">承保公司 </label>
                            <div class="col-5 form-control">${sc.insuranceCompany.companyName}</div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">适用人群</label>
                            <div class="col-5 form-control">${sc.ageDictionaryContent.content}</div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">发布时间</label>
                            <div class="col-5 form-control"><fmt:formatDate value="${sc.publishTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">条款附件</label>
                            <div class="col-5 form-control" style="border:none;">
                                <input type="checkbox" name="clauseOr" value="2" id="clauseOr"  style="width: 18px;" ${sc.clauseOr== 2 ? 'checked' : ''} disabled="disabled"><span class="d-inline-block ml-2">启用</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">&nbsp</label>
                            <div class="col-5">
                                <div class="input-group">
                                    <div id="fileIdName">
                                        <c:forEach var="item" items="${sc.clauseFileLs}" varStatus="st">
                                            <div name="fileItem" fileIndex="${st.index}">
                                                <a href="${item.fileAddress}" target="_blank">${item.fileName}</a>
                                                </br>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="block pt-3 mt-3 mb-5 border-top">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">合作供应商</h5>
                            </div>
                            <div class="table-list vendor-list" id="vendorListId">
                                <c:forEach var="item" items="${sc.cooperationSupplierLs}" varStatus="st">
                                    <div class="card bg-light my-3 vendor-item table-item">
                                        <div class="card-header d-flex align-items-center">
                                            <div>
                                                <input class="d-inline-block mr-1" style="width: 18px;" id="recommendStatusIdCP${st.index}" type="checkbox" ${item.recommendStatus==2 ? 'checked' : ''} disabled="disabled">
                                                <label class="text-center">推荐</label>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="form-group row">
                                                <label class="col-1 col-form-label text-right">销售渠道</label>
                                                <div class="col-4 form-control">${item.dictionaryContent.content}</div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-1 col-form-label text-right">投保链接 </label>
                                                <div class="col-8 form-control">${item.insureLink}</div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-1 col-form-label text-right">条款链接 </label>
                                                <div class="col-8 form-control">${item.clauseLink}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h2 class="mb-0">
                    <button class="btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                        产品保障
                    </button>
                </h2>
            </div>
            <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo">
                <div class="card-body">
                    <div class="tab-pane fade show tab-guarantee card p-3" id="pills-guarantee">
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">保什么</h5>
                            </div>
                            <div class="mt-3">
                                <table class="table table-hover table-bordered">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>推荐</th>
                                        <th>保障内容</th>
                                        <th>保额</th>
                                        <th>保障简介</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-list guard-what-list" id="modalGuardWhatItems">
                                    <c:forEach var="item" items="${sc.guaranteeRelationLs}" varStatus="st">
                                        <tr class="table-item guard-what-item" name="guaranteeRelationItem" id="guaranteeRelationItem${st.index}" itemIndex="${st.index}">
                                            <td>
                                                <input type="hidden" name="guaranteeRelationLs[${st.index}].dictionaryContentName" type="hidden" value="${item.dictionaryContent.content}"/>
                                                <input type="hidden" name="guaranteeRelationLs[${st.index}].price" type="hidden" value="${item.price}"/>
                                                <input type="hidden" name="guaranteeRelationLs[${st.index}].guaranteeIntroduction" type="hidden" value="${item.guaranteeIntroduction}"/>
                                                <input type="hidden" name="guaranteeRelationLs[${st.index}].guaranteeDetail" type="hidden" value="${item.guaranteeDetail}"/>
                                                <div style="display: none;" name="subItems${st.index}">
                                                    <c:forEach var="o" items="${item.guaranteeLowerLevelRelationLs}" varStatus="vs1">
                                                        <div subIndex="${vs1.index}">
                                                            <input type="hidden" name="guaranteeRelationLs[${st.index}].guaranteeLowerLevelRelationLs[${vs1.index}].title" type="hidden" value="${o.title}"/>
                                                            <input type="hidden" name="guaranteeRelationLs[${st.index}].guaranteeLowerLevelRelationLs[${vs1.index}].content" type="hidden" value="${o.content}"/>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <div class="form-check d-flex justify-content-center">
                                                    <input class="form-check-input" style="width: 18px;" type="checkbox" name="guaranteeRelationLs[${st.index}].type" value="1" ${item.type==1 ? 'checked' : ''} disabled="disabled"/>
                                                </div>
                                            </td>
                                            <td>${item.dictionaryContent.content}</td>
                                            <td>${item.price}</td>
                                            <td>${item.guaranteeIntroduction}</td>
                                            <td>
                                                <button class="btn1 btn-sm btn-secondary editModalGuardWhat" data-toggle="modal" data-action="edit">详情</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        </div>
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">增值服务</h5>
                            </div>
                            <div class="mt-3">
                                <table class="table table-hover table-bordered">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>增值服务</th>
                                        <th>描述</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-list guard-service-list" id="modalGuardBuyServiceItems">
                                    <c:forEach var="item" items="${sc.incrementRelationLs}" varStatus="vs">
                                        <tr class="table-item guard-service-item" indexItem="${vs.index}">
                                            <td class="guard-service-content">${item.title}</td>
                                            <td class="guard-service-desc">${item.content}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">续保规则</h5>
                            </div>
                            <div class="mt-3">
                                <table class="table table-hover table-bordered">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>续保规则</th>
                                        <th>描述</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-list guard-renew-rule-list" id="modalGuardRenewRuleItems">
                                    <c:forEach var="item" items="${sc.renewRelationLs}" varStatus="vs">
                                        <tr class="table-item guard-renew-rule-item" indexItem="${vs.index}">
                                            <td class="guard-renew-rule-content">${item.title}</td>
                                            <td class="guard-renew-rule-desc">${item.content}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">不保什么</h5>
                            </div>
                            <div class="mt-3">
                                <textarea class="form-control" name="notGuaranteedSituation" rows="10" value="${sc.notGuaranteedSituation}" disabled>${sc.notGuaranteedSituation}</textarea>
                                </div>
                        </div>
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">投保规则</h5>
                            </div>
                            <div class="mt-3">
                                <table class="table table-hover table-bordered">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>规则类别</th>
                                        <th>规则内容</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-list guard-buy-rule-list" id="modalGuardBuyRuleItems">
                                    <c:forEach var="item" items="${sc.guaranteedRuleLs}" varStatus="st">
                                        <tr class="table-item guard-buy-rule-item" indexItem="${st.index}">
                                            <td class="guard-buy-rule-title">${item.dictionaryContent.content}</td>
                                            <td class="guard-buy-rule-content">${item.content}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThree">
                <h2 class="mb-0">
                    <button class="btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                        产品点评
                    </button>
                </h2>
            </div>
            <div id="collapseThree" class="collapse show" aria-labelledby="headingThree">
                <div class="card-body">
                    <div class="tab-pane fade show tab-comment card p-3" id="pills-comment">
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">亮点</label>
                            <div class="col-10"><textarea class="form-control" rows="6" disabled> ${sc.brightspot}</textarea></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right"> 不足 </label>
                            <div class="col-10"><textarea class="form-control" rows="6" disabled>${sc.insufficient}</textarea></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">注意事项</label>
                            <div class="col-10"><textarea class="form-control" rows="6" disabled>${sc.attention}</textarea></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">适合人群</label>
                            <div class="col-10"><textarea class="form-control" rows="6" disabled>${sc.suitable}</textarea></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">综合点评</label>
                            <div class="col-10"><textarea class="form-control" rows="10" disabled>${sc.comment}</textarea></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingFour">
                <h2 class="mb-0">
                    <button class="btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
                        保费测算
                    </button>
                </h2>
            </div>
            <div id="collapseFour" class="collapse show" aria-labelledby="headingFour">
                <div class="card-body">
                    <div class="tab-pane fade show tab-caculate card p-3" id="pills-caculate">
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">保费测算</h5>
                            </div>
                            <div class="mt-3">
                                <table class="table table-hover table-bordered caculate-list" id="caculateList">
                                    <thead class="thead-dark">
                                    <tr>
                                        <c:forEach var="item" items="${expandName}">
                                            <th>${item}</th>
                                        </c:forEach>
                                    </tr>
                                    </thead>
                                    <tbody class="table-list">
                                    <c:forEach var="item" items="${sc.premiumCalculationLs}">
                                        <tr class="table-item caculate-item">
                                            <c:forEach var="expand" items="${expands}">
                                                <c:if test="${expand=='gender'}">
                                                    <th>${item[expand]==1}?'男':'女'</th>
                                                    <c:if test="${item[expand]==1}">
                                                        <th>男</th>
                                                    </c:if>
                                                    <c:if test="${item[expand]==2}">
                                                        <th>女</th>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${expand=='socialSecurity'}">
                                                    <c:if test="${item[expand]==0}">
                                                        <th>无</th>
                                                    </c:if>
                                                    <c:if test="${item[expand]==1}">
                                                        <th>有</th>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${expand!='gender' && expand!='socialSecurity'}">
                                                    <th>${item[expand]}</th>
                                                </c:if>
<%--                                                <th>${item[expand]}</th>--%>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                            <div class="form-group row border-top pt-3">
                                <label class="col-1 col-form-label text-right">保费说明</label>
                                <div class="col-11"><textarea class="form-control" rows="6" disabled>${sc.premiumsExplain}</textarea></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingFive">
                <h2 class="mb-0">
                    <button class="btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="true" aria-controls="collapseFive">
                        产品标签
                    </button>
                </h2>
            </div>
            <div id="collapseFive" class="collapse show" aria-labelledby="headingFive">
                <div class="card-body">
                    <div class="tab-pane fade show tab-tag card p-3" id="pills-tag">
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">同类产品标签</h5>
                            </div>
                            <div class="tab__tag-list mt-3"> <label class="tab__tag-btn"><span class="text">${sc.similarDictionaryContent.name}</span></label></div>
                        </div>
                        <div class="block mt-3 mb-5">
                            <div class="block-title d-flex align-items-center">
                                <h5 class="mb-0">产品特色标签（多选）</h5>
                            </div>
                            <div class="tab__tag-list mt-3">
                                <c:forEach var="s" items="${sc.specialLabelLs}">
                                    <label class="tab__tag-btn"><span class="text">${s.dictionaryContent.content}</span></label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="d-flex justify-content-center pb-3 bottom-panel">
        <button type="button" id="backBtn" class="btn1 btn-secondary">返回</button>
    </div>

    <div class="modal fade" tabindex="-1" role="dialog" id="modalGuardWhat">
        <input type="hidden" name="modalGuardWhatIndex" value="-1" id="modalGuardWhatIndex">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">保什么</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">保障类型</label>
                            <div class="col-10 form-control guard-what-title"></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">保额</label>
                            <div class="col-10 form-control guard-what-price"></div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">保障简介</label>
                            <div class="col-10">
                                <textarea class="form-control guard-what-profile" rows="6" disabled></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-2 col-form-label text-right">保障详情</label>
                            <div class="col-10">
                                <textarea class="form-control guard-what-detail" rows="10" disabled></textarea>
                            </div>
                        </div>
                        <div class="block-title d-flex align-items-center">
                            <h6 class="mb-0" style="font-size: 18px;">子项目</h6>
                        </div>
                        <div class="mt-3">
                            <table class="table table-hover table-bordered">
                                <thead class="thead-dark">
                                <tr>
                                    <th>项目名称</th>
                                    <th>项目描述</th>
                                </tr>
                                </thead>
                                <tbody class="table-list guard-what-sub-list" id="addGuardWhatSub">
                                </tbody>
                            </table>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn2 btn-secondary" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    $(function () {
        $("#backBtn").click(function() {
            visitUrl(ctxPath + "/product/productPage.htm", 1, 1, 1);
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
            var dictionaryContentName = $(trObj).find("input[name='guaranteeRelationLs["+num+"].dictionaryContentName']").val();
            var price = $(trObj).find("input[name='guaranteeRelationLs["+num+"].price']").val();
            var profile= $(trObj).find("input[name='guaranteeRelationLs["+num+"].guaranteeIntroduction']").val();
            var detail = $(trObj).find("input[name='guaranteeRelationLs["+num+"].guaranteeDetail']").val();
            modal.find('.guard-what-title').text(dictionaryContentName);
            modal.find('.guard-what-price').text(price);
            modal.find('.guard-what-profile').text(profile);
            modal.find('.guard-what-detail').text(detail);
            var subItemHtml = '';
            if($(trObj).find("div[name='subItems"+num+"']").children().length>0){
                $(trObj).find("div[name='subItems"+num+"']").children().each(function() {
                    var subIndex = $(this).attr("subIndex");
                    var title = $(this).find("input[name='guaranteeRelationLs["+num+"].guaranteeLowerLevelRelationLs["+subIndex+"].title']").val();
                    var content = $(this).find("input[name='guaranteeRelationLs["+num+"].guaranteeLowerLevelRelationLs["+subIndex+"].content']").val();
                    subItemHtml += '<tr class="table-item sub-item"><td><input type="text" class="form-control" name="guardWhatSubTitle" value="'+title+'"/></td>';
                    subItemHtml += '<td><input type="text" class="form-control" name="guardWhatSubContent" value="'+content+'"/></td></tr>';
                })
            }
            $('#addGuardWhatSub').empty().append(subItemHtml);

            $('#modalGuardWhat').modal('show');
        })
    })

</script>