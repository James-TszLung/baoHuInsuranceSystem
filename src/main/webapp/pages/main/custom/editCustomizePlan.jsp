<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newStyle/normalize.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newStyle/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/acbox/css/jquery.ajaxComboBox.3.7.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/acbox/acbox.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/styles/acbox/js/jquery.ajaxComboBox.3.7.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/styles/acbox/acbox.js?ver=1" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/styles/newStyle/bootstrap/bootstrap.min.js"></script>
<style>
    <%-- modal 弹窗  --%>
    .modal-backdrop {
        position: initial !important;
    }
    .col_20{
        margin-left: 20px;
        width: 5%;
        padding-top: calc(.375rem + 1px);
    }
    .col_21{
        margin-left: 20px;
        width: 15%;
        float: left;
        padding-top: calc(.375rem + 1px);
    }
    .col-22{
        flex: inherit !important;
        max-width: inherit !important;
    }
    .btn1{border: 1px solid transparent;}
    .bottom-panel{margin-top: 10px;}
    .bottom-panel .btn1{padding: .575rem .85rem;font-size: 1rem;border-radius: 4px;}
    .btn2{
        border: 1px solid transparent;
        padding: .375rem .75rem;
        border-radius: .25rem;
    }
    *, ::after, ::before {
        box-sizing: inherit !important;
    }
    .content{
        box-sizing: border-box !important;
    }
    .Wdate{
        height: initial;
    }
    .gender{
        background-color: initial;
        border: initial;
    }
    .gender input:first-child{
        margin-left: 20px;
        width: 18px;
    }
    .gender input:last-child{
        width: 18px;
        margin-left: 10px;
    }
    .open-button{
        margin-right: 10px;
        background-color: #17a2b8cc;
    }
    .open-button:hover{
        background-color: #17a2b8;
        border-color:#17a2b8;
    }
    #planRiskList textarea.form-control {
        margin-bottom: 15px;
    }
    #planRiskList input{
        margin-bottom: 8px;
    }
    #planRiskList label{
        font-size: 1.15rem;
    }
    #planRiskList .form-group {
        margin-bottom: 0rem;
    }
    .sub{
        width: 46%;
        margin-left: 16px;
    }
    .sub input{
        width: 90%;
    }
    .sub select{
        width: 90% !important;
    }
    .price{
        width: initial !important;
    }
    .page_list {
        margin-top: initial;
    }
    .add-plan-safety{
        margin-top: 5px;
    }

</style>
<div class="content" id="app" >
    <form id="addform" method="post" enctype="multipart/form-data" autocomplete="off">
        <input type="hidden" name="id" value="${sc.id}">
        <div class="tab-content">
            <div class="tab-pane fade show active tab-info card p-3" id="pills-info">
                <div class="form-group row">
                    <label class="col_20">*联系人姓名</label>
                    <div class="col-5">
                        <input class="form-control" name="name" maxlength="10" id="name" value="${sc.name}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">*联系电话</label>
                    <div class="col-5">
                        <input class="form-control" name="phone" maxlength="11" id="phone" value="${sc.phone}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">*方案标题</label>
                    <div class="col-5">
                        <input class="form-control" name="title" maxlength="50" id="title" value="${sc.title}">
                    </div>
                </div>
                <div class="block pt-3 mt-3 mb-5 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">方案配置</h5>
                        <button type="button" class="btn1 btn-sm btn-info ml-3" id="btnAddPlanConfig">添加成员</button>
                    </div>
                    <div class="table-list config-list" id="planConfigList">
                        <c:forEach var="item" items="${sc.planConfigLs}" varStatus="sc">
                            <div class="card bg-light my-3 config-item table-item" id="planConfigItemId${sc.index}" name="planConfigItem" itemIndex="${sc.index}">
                                <div class="card-body">
                                    <div class="form-group row">
                                        <div style="width: 39%;">
                                            <label class="col_21">*姓名</label>
                                            <input class="form-control insure_required" style="width: 70%;" name="planConfigLs[${sc.index}].name" value="${item.name}">
                                        </div>
                                        <div style="width: 15%;">
                                            <label class="col_21">性别</label>
                                            <div class="form-control gender">
                                                <input type="radio" name="planConfigLs[${sc.index}].gender" value="1" ${item.gender=='1' ? 'checked' : ''}>男
                                                <input type="radio" name="planConfigLs[${sc.index}].gender" value="2" ${item.gender=='2' ? 'checked' : ''}>女
                                            </div>
                                        </div>
                                        <div style="width: 22%;">
                                            <label class="col_21" style="width: 20%;">出生日期</label>
                                            <input name="planConfigLs[${sc.index}].birth" type="text" class="Wdate form-control" onclick="WdatePicker()" style="width:120px;" value="${item.birth}"/>
                                        </div>
                                        <div style="width: 20%;">
                                            <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 open-button" style="margin-right: 10px;">收起</button>
                                            <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-plan-config-button">删除</button>
                                        </div>
                                    </div>
                                    <div class="info-list">
                                        <div class="form-group row">
                                            <label class="col_20"> 健康状况 </label>
                                            <div class="col-8">
                                                <textarea class="form-control" name="planConfigLs[${sc.index}].health" rows="5" value="${item.health}">${item.health}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20"> 已有保险 </label>
                                            <div class="col-8">
                                                <textarea class="form-control" name="planConfigLs[${sc.index}].insured" rows="5" value="${item.insured}">${item.insured}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20">模板概述</label>
                                            <div class="col-8">
                                                <button type="button" class="btn1 btn-sm btn-info ml-3 content-temp">选择</button>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20"> 方案概述</label>
                                            <div class="col-8">
                                                <textarea class="form-control" name="planConfigLs[${sc.index}].content" rows="4" value="${item.content}">${item.content}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20"> 方案备注</label>
                                            <div class="col-8">
                                                <textarea class="form-control" placeholder="该部分内容仅工作人员可见，不对客户开放" name="planConfigLs[${sc.index}].remark" rows="5" value="${item.remark}">${item.remark}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="block pt-3 mt-3 mb-5 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">风险保障</h5>
                        <button type="button" class="btn1 btn-sm btn-info ml-3" id="btnAddPlanRisk">添加保障</button>
                    </div>
                    <div class="table-list risk-list" id="planRiskList">
                        <c:forEach var="item" items="${sc.planRiskLs}" varStatus="vs">
                            <div class="card bg-light my-3 risk-item table-item plan_risk_item" id="planRiskItemId${vs.index}" name="planRiskItem" itemIndex="${vs.index}">
                                <div class="card-body">
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">风险类别</label>
                                        <div class="col-4">
                                            <select class="form-control type_required" name="planRiskLs[${vs.index}].typeId">
                                                <c:forEach items="${riskTypeList}" var="s">
                                                    <option value="${s.id}" ${s.id==item.typeId ? 'selected' : ''}>${s.content}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div style="margin-left: 600px;">
                                            <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-plan-risk-button">删除</button>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">风险分析</label>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">&nbsp</label>
                                        <div class="col-8">
                                            <div>
                                                <span>产品作用</span>
                                                <textarea class="form-control" name="planRiskLs[${vs.index}].used" rows="4" maxlength="1000" value="${item.used}">${item.used}</textarea>
                                            </div>
                                            <div>
                                                <span>风险缺口</span>
                                                <textarea class="form-control" name="planRiskLs[${vs.index}].risk" rows="4" maxlength="1000" value="${item.risk}">${item.risk}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">投保建议</label>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">&nbsp</label>
                                        <div class="col-8">
                                            <div>
                                                <span>建议保额</span>
                                                <input class="form-control" name="planRiskLs[${vs.index}].insuredContent" value="${item.insuredContent}" placeholder="填入建议所选保险产品名称及购买保额，多个产品请用“，”隔开" maxlength="50">
                                                <textarea class="form-control" name="planRiskLs[${vs.index}].insuredDesc" value="${item.insuredDesc}" placeholder="填入建议保额计算依据" rows="4" maxlength="500">${item.insuredDesc}</textarea>
                                            </div>
                                            <div>
                                                <span>保障期限</span>
                                                <input class="form-control" name="planRiskLs[${vs.index}].deadlineContent" value="${item.deadlineContent}" placeholder="填入建议所选保险产品名称及保险期限，多个产品请用“，”隔开" maxlength="50">
                                                <textarea class="form-control" name="planRiskLs[${vs.index}].deadlineDesc" value="${item.deadlineDesc}" placeholder="填入购买产品建议期限的原因" rows="4" maxlength="500">${item.deadlineDesc}</textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">核保建议</label>
                                        <div class="col-4">
                                            <textarea class="form-control" name="planRiskLs[${vs.index}].suggest" rows="5" value="${item.suggest}" maxlength="1000">${item.suggest}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">保险产品</label>
                                        <div class="col-4">
                                            <button type="button" class="btn1 btn-sm btn-info ml-3 add-plan-safety" data-action="add" data-toggle="modal" data-index="${vs.index}">添加</button>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-1 col-form-label text-right">&nbsp</label>
                                        <div class="col-8">
                                            <div class="mt-3">
                                                <table class="table table-hover table-bordered">
                                                    <thead class="thead-dark">
                                                    <tr>
                                                        <th>保险产品</th>
                                                        <th>年缴保费（元）</th>
                                                        <th>保额（万元）</th>
                                                        <th>保险期限</th>
                                                        <th>缴费期限</th>
                                                        <th>销售渠道</th>
                                                        <th>推荐原因</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="table-list plan-safety-list" id="modalPlanSafetyItems${vs.index}">
                                                    <c:forEach var="safeItem" items="${item.planSafetyLs}" varStatus="vc">
                                                        <tr class="table-item plan-safety-item" name="planSafetyItem${vs.index}" id="planSafetyItem${vs.index}${vc.index}" itemIndex="${vc.index}">
                                                            <td>
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].name" value="${safeItem.name}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].productId" value="${safeItem.productId}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].premiums" value="${safeItem.premiums}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].amount" value="${safeItem.amount}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].insuredTerm" value="${safeItem.insuredTerm}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].payTerm" value="${safeItem.payTerm}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].channelId" value="${safeItem.channelId}">
                                                                <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].recommentId" value="${safeItem.recommentId}">
                                                                <div style="display: none;" name="subItems${vc.index}">
                                                                    <c:forEach var="subItem" items="${safeItem.guaranteeRelationLs}" varStatus="v">
                                                                        <div subindex="${v.index}">
                                                                            <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].planGuaranteeRelationLs[${v.index}].guaranteeRelationId" value="${subItem.id}">
                                                                            <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].planGuaranteeRelationLs[${v.index}].productId" value="${subItem.productId}">
                                                                            <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].planGuaranteeRelationLs[${v.index}].dictionaryContentId" value="${subItem.dictionaryContentId}">
                                                                            <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].planGuaranteeRelationLs[${v.index}].guaranteeIntroduction" value="${subItem.guaranteeIntroduction}">
                                                                            <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].planGuaranteeRelationLs[${v.index}].oldPrice" value="${subItem.price}">
                                                                            <input type="hidden" name="dictionaryContentName${v.index}" value="${subItem.dictionaryContent.content}">
                                                                            <input type="hidden" name="planRiskLs[${vs.index}].planSafetyLs[${vc.index}].planGuaranteeRelationLs[${v.index}].price" value="${subItem.newPrice}">
                                                                        </div>
                                                                    </c:forEach>
                                                                </div>${safeItem.name}</td>
                                                            <td>${safeItem.premiums}</td>
                                                            <td>${safeItem.amount}</td>
                                                            <td>${safeItem.insuredContent.content}</td>
                                                            <td>${safeItem.payContent.content}</td>
                                                            <td>${safeItem.channelContent.content}</td>
                                                            <td>${safeItem.recommentContent.content}</td>
                                                            <td>
                                                                <button class="btn1 btn-sm btn-secondary mr-1" onclick="delModalPlanSafety(this)">删除</button>
                                                                <button class="btn1 btn-sm btn-secondary editModalPlanSafety" data-toggle="modal" data-riskIndex="${vs.index}" data-action="edit">编辑</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center pb-3 bottom-panel">
            <button type="button" id="addBtn"  class="btn1 btn-primary mr-5">保存</button>
            <button type="button" id="backBtn" class="btn1 btn-secondary">返回</button>
        </div>
    </form>

    <div class="modal fade" tabindex="-1" role="dialog" id="modalPlanSafety">
        <input type="hidden" name="modalPlanSafetyIndex" value="-1" id="modalPlanSafetyIndex">
        <input type="hidden" name="modalRiskIndex" value="-1" id="modalRiskIndex">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">增加产品</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group row">
                            <label class="col-2 col-22 col-form-label">选择产品</label>
                            <div class="col-10">
                                <div class="acbox_div showborder" id="_productIds" style="width: 500px;"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="sub">
                                <label class="col-form-label">产品名称</label>
                                <input type="text" class="form-control plan-safety-name" name="modalProductName">
                                <input type="hidden" name="modalProductId">
                            </div>
                            <div class="sub">
                                <label class="col-form-label">渠道</label>
                                <select class="form-control plan-safety-channel" id="modalChannel">
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="sub">
                                <label class="col-form-label">年交保费（单位：元）</label>
                                <input type="text" class="form-control plan-safety-premiums">
                            </div>
                            <div class="sub">
                                <label class="col-form-label">保险期限</label>
                                <select class="form-control plan-safety-insured">
                                    <c:forEach var="item" items="${payTermList}">
                                        <option value="${item.id}">${item.content}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="sub">
                                <label class="col-form-label">缴费年限</label>
                                <select class="form-control plan-safety-pay">
                                    <c:forEach var="item" items="${payTermList}">
                                        <option value="${item.id}">${item.content}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="sub">
                                <label class="col-form-label">推荐原因</label>
                                <select class="form-control plan-safety-recomment">
                                    <c:forEach var="item" items="${recommentCauseList}">
                                        <option value="${item.id}">${item.content}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="block-title d-flex align-items-center">
                            <h6 class="mb-0" style="font-size: 18px;">产品分项</h6>
                        </div>
                        <div class="mt-3">
                            <table class="table table-hover table-bordered">
                                <thead class="thead-dark">
                                <tr>
                                    <th style="width: 30%;">分项</th>
                                    <th style="width: 50%;">介绍</th>
                                    <th style="width: 10%;">原保额</th>
                                    <th style="width: 10%;">保额(元)</th>
                                </tr>
                                </thead>
                                <tbody class="table-list plan-safety-sub-list" id="addPlanSafetySub">
                                </tbody>
                            </table>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn2 btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn2 btn-primary" id="modalPlanSafetyConfirm">确定</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 弹窗：概述模板 -->
    <div class="modal fade" id="chooseContentTemp" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <input type="hidden" name="configIndex" id="configIndex" value="0">
            <div class="modal-content" style="width:500px;">
                <div class="modal-header">
                    <h5 class="modal-title">概述模板</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="searchContentTempForm" onkeydown="if(event.keyCode==13){return false;}">
                        <div class="block-title d-flex align-items-center search_company" width="800px">
                            <div><label>模板：</label> <input type="text" name="content" placeholder="输入模板关键字"></div>
                            <button type="button" class="btn1 btn-sm btn-info ml-3" onclick="doSearchContentTemp(true)">搜索</button>
                        </div>
                    </form>
                    <div class="mid" id="contentTempList"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn2 btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn2 btn-primary" id="contentTempConfirm">确定</button>
                </div>
            </div>
        </div>
    </div>

    <div id="template" style="display: none;">
        <div class="plan-risk-item">
            <div class="card bg-light my-3 risk-item table-item plan_risk_item">
                <div class="card-body">
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">风险类别</label>
                        <div class="col-4">
                            <select class="form-control type_required risk_type" name="planRiskLs[x].typeId">
                                <c:forEach items="${riskTypeList}" var="s">
                                    <option value="${s.id}">${s.content}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div style="margin-left: 600px;">
                            <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-plan-risk-button">删除</button>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">风险分析</label>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">&nbsp</label>
                        <div class="col-8">
                            <div>
                                <span>产品作用</span>
                                <textarea class="form-control risk_used" name="planRiskLs[x].used" rows="4" maxlength="1000"></textarea>
                            </div>
                            <div>
                                <span>风险缺口</span>
                                <textarea class="form-control risk_risk" name="planRiskLs[x].risk" rows="4" maxlength="1000"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">投保建议</label>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">&nbsp</label>
                        <div class="col-8">
                            <div>
                                <span>建议保额</span>
                                <input class="form-control risk_insuredContent" name="planRiskLs[x].insuredContent" placeholder="填入建议所选保险产品名称及购买保额，多个产品请用“，”隔开" maxlength="50">
                                <textarea class="form-control risk_insuredDesc" name="planRiskLs[x].insuredDesc" placeholder="填入建议保额计算依据" rows="4" maxlength="500"></textarea>
                            </div>
                            <div>
                                <span>保障期限</span>
                                <input class="form-control risk_deadlineContent" name="planRiskLs[x].deadlineContent" placeholder="填入建议所选保险产品名称及保险期限，多个产品请用“，”隔开" maxlength="50">
                                <textarea class="form-control risk_deadlineDesc" name="planRiskLs[x].deadlineDesc" placeholder="填入购买产品建议期限的原因" rows="4" maxlength="500"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">核保建议</label>
                        <div class="col-4">
                            <textarea class="form-control risk_suggest" name="planRiskLs[x].suggest" rows="5" maxlength="1000"></textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">保险产品</label>
                        <div class="col-4">
                            <button type="button" class="btn1 btn-sm btn-info ml-3 add-plan-safety" data-action="add" data-toggle="modal" data-index="x">添加</button>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-1 col-form-label text-right">&nbsp</label>
                        <div class="col-8">
                            <div class="mt-3">
                                <table class="table table-hover table-bordered">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>保险产品</th>
                                        <th>年缴保费（元）</th>
                                        <th>保额（万元）</th>
                                        <th>保险期限</th>
                                        <th>缴费期限</th>
                                        <th>销售渠道</th>
                                        <th>推荐原因</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-list plan-safety-list risk_modalPlanSafetyItems" id="modalPlanSafetyItemsx">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/custom/editCustomizePlan.js"></script>