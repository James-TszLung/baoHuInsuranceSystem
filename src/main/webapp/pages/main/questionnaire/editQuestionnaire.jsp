<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newStyle/normalize.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newStyle/bootstrap/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/styles/newStyle/bootstrap/bootstrap.min.js"></script>
<style>
    ul {overflow: hidden;margin-bottom:auto;}
    .col_20{
        margin-left: 20px;
        width: 6%;
        padding-top: calc(.375rem + 1px);
    }
    .col_21{
        margin-left: 20px;
        width: 15%;
        float: left;
        padding-top: calc(.375rem + 1px);
    }
    .btn1{border: 1px solid transparent;}
    .bottom-panel{margin-top: 10px;}
    .bottom-panel .btn1{padding: .575rem .85rem;font-size: 1rem;border-radius: 4px;}
    *, ::after, ::before {
        box-sizing: inherit !important;
    }
    .content{
        box-sizing: border-box !important;
    }
    .gender{
        background-color: initial;
        border: initial;
    }
    .gender input{
        width: 18px;
    }
    .gender input:not(:first-child){
        margin-left: 10px;
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
    .nav{
        height: inherit;
        background: inherit;
        background-color: #f0f0f0;
    }
    .unit{
        display: flex;
    }
    .unit input{
        width: 90%;
    }
    .unit span{
        padding: .375rem .75rem;
    }
    .h5, h5 {
        font-size: 1.1rem;
        font-weight: 600;
    }
    .healthy li{
        margin-bottom: 10px;
    }
    .delete-btn{
        text-align: right;
        margin-right: 100px
    }

</style>
<div class="content" id="app" >
    <form id="addform" method="post" enctype="multipart/form-data" autocomplete="off">
        <input type="hidden" value="${sc.id}" name="id">
        <div class="container">
            <ul class="nav nav-pills mb-3 justify-content-center nav-fill" id="tab">
                <li class="nav-item"><a class="nav-link active" data-toggle="pill" href="#pills-info">基础信息</a></li>
                <li class="nav-item"><a class="nav-link" data-toggle="pill" href="#pills-finance">家庭财务</a></li>
                <li class="nav-item"><a class="nav-link" data-toggle="pill" href="#pills-demands">总体需求</a></li>
                <li class="nav-item"><a class="nav-link" data-toggle="pill" href="#pills-health">健康信息</a></li>
            </ul>
        </div>
        <div class="tab-content">
            <div class="tab-pane fade show active tab-info card p-3" id="pills-info">
                <div class="block mt-3">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">1、您好，请提供您的真实姓名和联系电话</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col_20">*姓名</label>
                            <div class="col-5">
                                <input type="text" class="form-control" name="name" value="${sc.name}" id="name">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col_20">*电话</label>
                            <div class="col-5">
                                <input type="number" class="form-control" name="phone" value="${sc.phone}" id="phone">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col_20">*微信号</label>
                            <div class="col-5">
                                <input type="text" class="form-control" name="wxNumber" value="${sc.wxNumber}" id="wxNumber">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">2、参与定制方案成员的个人信息（最多4人）</h5>
                        <button type="button" class="btn1 btn-sm btn-info ml-3" id="addMemberBtn">添加</button>
                    </div>
                    <div class="table-list member-list" id="memberList">
                        <c:forEach var="item" items="${sc.memberInformationLs}" varStatus="vs">
                            <div class="card bg-light my-3 member-item table-item" id="memberItem${vs.index}" name="memberItem" itemIndex="${vs.index}">
                                <div class="card-body">
                                    <div class="delete-btn">
                                        <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-member-button">删除</button>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">姓名</label>
                                        <div class="col-5">
                                            <input type="text" class="form-control" name="memberInformationLs[${vs.index}].name" value="${item.name}" onBlur="changeName(this,'${vs.index}')">
                                        </div>
                                    </div>
                                    <div class="info-list">
                                        <div class="form-group row">
                                            <label class="col_20">性别</label>
                                            <div class="col-5 gender">
                                                <input type="radio" name="memberInformationLs[${vs.index}].sex" value="1" ${item.sex=='1' ? 'checked' : ''}>男
                                                <input type="radio" name="memberInformationLs[${vs.index}].sex" value="2" ${item.sex=='2' ? 'checked' : ''}>女
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20">年龄</label>
                                            <div class="col-5">
                                                <input type="number" class="form-control" name="memberInformationLs[${vs.index}].age" value="${item.age}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20">家庭关系</label>
                                            <div class="col-5">
                                                <select class="form-control" name="memberInformationLs[${vs.index}].relationshipId">
                                                    <c:forEach var="o" items="${familyTypeLs}">
                                                        <option value="${o.id}" ${o.id==item.relationshipId ? 'selected' : ''}>${o.content}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col_20">从事职业</label>
                                            <div class="col-5">
                                                <input type="text" class="form-control" name="memberInformationLs[${vs.index}].profession" value="${item.profession}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">3、你和家人常住城市和户口所在地</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col_20">常住城市一</label>
                            <div class="col-5">
                                <input type="text" class="form-control" name="city1" value="${sc.city1}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col_20">常住城市二</label>
                            <div class="col-5">
                                <input type="text" class="form-control" name="city2" value="${sc.city2}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col_20">户籍</label>
                            <div class="col-5">
                                <input class="form-control" type="text" name="residence" value="${sc.residence}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade tab-guarantee card p-3" id="pills-finance">
                <div class="block mt-3 ">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">4、个人年收入统计（按照所有有收入的填写）</h5>
                        <button type="button" class="btn1 btn-sm btn-info ml-3" id="addIncomeBtn">添加</button>
                    </div>
                    <div class="table-list income-list" id="incomeList">
                        <c:forEach var="item" items="${sc.incomeRLQnaireLs}" varStatus="vs">
                            <div class="card bg-light my-3 income-item table-item" id="incomeItem${vs.index}" name="incomeItem${vs.index}" itemIndex="${vs.index}">
                                <div class="card-body">
                                    <div class="delete-btn">
                                        <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-income-button">删除</button>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">姓名</label>
                                        <div class="col-5">
                                            <input class="form-control" type="text" name="incomeRLQnaireLs[${vs.index}].name" value="${item.name}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">税后收入</label>
                                        <div class="col-5 unit">
                                            <input class="form-control" type="number" name="incomeRLQnaireLs[${vs.index}].money" value="${item.money}"><span>万元</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">收入来源</label>
                                        <div class="col-5">
                                            <input class="form-control" type="text" name="incomeRLQnaireLs[${vs.index}].source" value="${item.source}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">5、家庭负债情况（每一笔填写一条数据）</h5>
                        <button type="button" class="btn1 btn-sm btn-info ml-3" id="addLiabilitiesBtn">添加</button>
                    </div>
                    <div class="table-list liabilities-list" id="liabilitiesList">
                        <c:forEach var="item" items="${sc.liabilitiesRLQnaireLs}" varStatus="vs">
                            <div class="card bg-light my-3 liabilities-item table-item" id="liabilitiesItem${vs.index}" name="liabilitiesItem" itemIndex="${vs.index}">
                                <div class="card-body">
                                    <div class="delete-btn">
                                        <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-liabilities-button">删除</button>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">贷款类型</label>
                                        <div class="col-5">
                                            <select class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].loanType">
                                                <option value="1" ${item.loanType=='1' ? 'selected' : ''}>房产</option>
                                                <option value="2" ${item.loanType=='2' ? 'selected' : ''}>车辆</option>
                                                <option value="3" ${item.loanType=='3' ? 'selected' : ''}>其他</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">还款方式</label>
                                        <div class="col-5">
                                            <select class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].repaymentType">
                                                <option value="1" ${item.repaymentType=='1' ? 'selected' : ''}>等额本息</option>
                                                <option value="2" ${item.repaymentType=='2' ? 'selected' : ''}>等额本金</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">贷款初始金额</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].loanMoney" value="${item.loanMoney}"><span>万</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">贷款初始年限</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].years" value="${item.years}"><span>年</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">剩余还款期</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].interestRate" value="${item.interestRate}"><span>期</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">贷款利率</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].surplus" value="${item.surplus}"><span>%</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20 unit">月供</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="liabilitiesRLQnaireLs[${vs.index}].supply" value="${item.supply}"><span>元</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">6、赡养老人开支统计（每年赡养老人的预备支出）</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col_20">赡养支出</label>
                            <div class="col-5 unit">
                                <input type="number" class="form-control" name="supportMoney" value="${sc.supportMoney}"><span>万</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col_20">赡养人数</label>
                            <div class="col-5">
                                <select class="form-control" name="supportNum">
                                    <option value="0" ${sc.supportNum=='0' ? 'selected' : ''}>0</option>
                                    <option value="1" ${sc.supportNum=='1' ? 'selected' : ''}>1</option>
                                    <option value="2" ${sc.supportNum=='2' ? 'selected' : ''}>2</option>
                                    <option value="3" ${sc.supportNum=='3' ? 'selected' : ''}>3</option>
                                    <option value="4" ${sc.supportNum=='4' ? 'selected' : ''}>4</option>
                                    <option value="5" ${sc.supportNum=='5' ? 'selected' : ''}>5</option>
                                    <option value="6" ${sc.supportNum=='6' ? 'selected' : ''}>6</option>
                                    <option value="7" ${sc.supportNum=='7' ? 'selected' : ''}>7</option>
                                    <option value="8" ${sc.supportNum=='8' ? 'selected' : ''}>8</option>
                                    <option value="9" ${sc.supportNum=='9' ? 'selected' : ''}>9</option>
                                    <option value="10" ${sc.supportNum=='10' ? 'selected' : ''}>10</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col_20">父母养老金属于</label>
                            <div class="col-5">
                                <select class="form-control" name="pensions">
                                    <option value="1" ${sc.pensions=='1' ? 'selected' : ''}>无</option>
                                    <option value="2" ${sc.pensions=='2' ? 'selected' : ''}>最低</option>
                                    <option value="3" ${sc.pensions=='3' ? 'selected' : ''}>一般</option>
                                    <option value="4" ${sc.pensions=='4' ? 'selected' : ''}>等同于公务员</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">7、每年家庭基本开支（仅包含家庭成员的基本生活开支，即吃饭、出行、基本穿衣、不含子女教育、大宗旅游、偿还贷款）</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col_20">基本开支</label>
                            <div class="col-5 unit">
                                <input type="number" class="form-control" name="expenditure" value="${sc.expenditure}"><span>元/年</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">8、你目前的家庭储备现金（包括常备金、银行理财、股票等能快速变现的流动性储备）</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col_20">现金储备</label>
                            <div class="col-5 unit">
                                <input type="number" class="form-control" name="reserve" value="${sc.reserve}"><span>元</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">9、是否有二胎需求</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-5 gender">
                                <input type="radio" name="second" value="1" ${sc.second=='1' ? 'checked' : ''}>有
                                <input type="radio" name="second" value="2" ${sc.second=='2' ? 'checked' : ''}>没有
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">10、孩子是否有出国留学的计划</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-5 gender">
                                <input type="radio" name="abroad" value="1" ${sc.abroad=='1' ? 'checked' : ''}>有
                                <input type="radio" name="abroad" value="2" ${sc.abroad=='2' ? 'checked' : ''}>没有
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade tab-comment card p-3" id="pills-demands">
                <div class="block mt-3">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">11、对保险公司的需求</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-5 gender">
                                <input type="radio" name="demandCompany" value="1" ${sc.demandCompany=='1' ? 'checked' : ''}>产品性价比，保司品牌为辅
                                <input type="radio" name="demandCompany" value="2" ${sc.demandCompany=='2' ? 'checked' : ''}>产品性价比，保司品牌为辅
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">12、你希望在以下哪几项风险保障领域得到精算君的配置建议（多选）</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-5 gender">
                                <input type="checkbox" name="securityField" value="寿险保障" ${fn:contains(sc.securityField,'寿险保障') ? 'checked' : ''}>寿险保障
                                <input type="checkbox" name="securityField" value="重疾保障" ${fn:contains(sc.securityField,'重疾保障') ? 'checked' : ''}>重疾保障
                                <input type="checkbox" name="securityField" value="意外保障" ${fn:contains(sc.securityField,'意外保障') ? 'checked' : ''}>意外保障
                                <input type="checkbox" name="securityField" value="医疗保障" ${fn:contains(sc.securityField,'医疗保障') ? 'checked' : ''}>医疗保障
                                <input type="checkbox" name="securityField" value="养老保障" ${fn:contains(sc.securityField,'养老保障') ? 'checked' : ''}>养老保障
                                <input type="checkbox" name="securityField" value="孩子教育金保障" ${fn:contains(sc.securityField,'孩子教育金保障') ? 'checked' : ''}>孩子教育金保障
                                <input type="checkbox" name="securityField" value="癌症保障" ${fn:contains(sc.securityField,'癌症保障') ? 'checked' : ''}>癌症保障
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">13、你目标的全家人的年保费开支预算（精算君个人建议可以控制在家庭年收入的8%-15%之间，但也可由你的实际情况决定）</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col_20">年保费</label>
                            <div class="col-5 unit">
                                <input type="number" class="form-control" name="budget" value="${sc.budget}"><span>%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="block pt-3 mt-3 border-top">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">14、本次参与方案定制的家庭成员的已有保险</h5>
                    </div>
                    <div class="table-list information-list" id="informationList">
                        <c:forEach var="item" items="${sc.memberInformationLs}" varStatus="vs">
                            <div class="card bg-light my-3 information-item table-item" id="informationItem${vs.index}" name="informationItem${vs.index}"  itemIndex="${vs.index}">
                                <div class="card-body">
                                    <div class="form-group row">
                                        <label class="col_20">家庭成员</label>
                                        <div class="col-5">
                                            <input type="text" class="form-control" name="memberInformationLs[${vs.index}].userName" value="${item.name}" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">公司名称</label>
                                        <div class="col-5">
                                            <input type="text" class="form-control" name="memberInformationLs[${vs.index}].companyName" value="${item.companyName}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">产品名称</label>
                                        <div class="col-5">
                                            <input type="text" class="form-control" name="memberInformationLs[${vs.index}].productName" value="${item.productName}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">保障情况</label>
                                        <div class="col-5">
                                            <input type="text" class="form-control" name="memberInformationLs[${vs.index}].situation" value="${item.situation}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">保障时间</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="memberInformationLs[${vs.index}].guaranteeYears" value="${item.guaranteeYears}"><span>年</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">缴费期限</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="memberInformationLs[${vs.index}].years" value="${item.years}"><span>年</span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col_20">缴费金额</label>
                                        <div class="col-5 unit">
                                            <input type="number" class="form-control" name="memberInformationLs[${vs.index}].payMoney" value="${item.payMoney}"><span>元/年</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade tab-caculate card p-3" id="pills-health">
                <div class="block mt-3">
                    <div class="block-title d-flex align-items-center">
                        <h5 class="mb-0">15、参与本次定制方案家庭成员健康调查表（如果有多人涉及多种健康问题，每一项涉及到的都请勾上，然后再简略描述具体人名和健康异常情况）</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <div class="gender">
                                <ul class="healthy">
                                    <li>
                                        <input type="checkbox" name="healthy" value="1" ${fn:contains(sc.healthy,'1') ? 'checked' : ''}>
                                        1、最近1年，是否有淋巴结增大、胸痛、胸闷、气喘、咳血、痰中带血、呕血、持续吞咽困难或哽噎感、反复腹、腹痛、血尿、蛋白尿、便血、浮肿或水肿、皮肤出血点、反复头痛、眩晕、体重下降超过5公斤（非健身及减肥原因）
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="2" ${fn:contains(sc.healthy,'2') ? 'checked' : ''}>
                                        2、是否提交过人身保险理赔申请或5年内有住院或2年内有门诊就诊？本项不包括顺产、人工流产、龋齿、牙周炎、感冒、鼻炎、鼻窦炎、急性支气管炎、颈椎病、急性胃肠炎、胆囊炎、胆囊结石、阑尾炎、脂肪瘤、皮脂腺囊肿、皮炎、癣、皮疹。
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="3" ${fn:contains(sc.healthy,'3') ? 'checked' : ''}>
                                        3、过去2年是否有心肺听诊、超声、心电图、脑电图、肌电图、X线、CT、造影、核磁共振、内窥镜、病理活检、眼底、血液、尿液、细胞学检查结果异常？
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="4" ${fn:contains(sc.healthy,'4') ? 'checked' : ''}>
                                        4、是否被保险公司拒保、延期、加费或除外责任承保？
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="5" ${fn:contains(sc.healthy,'5') ? 'checked' : ''}>
                                        5、是否有智能障碍？是否听力下降、耳聋、或高度近视1000度（含）以上？是否身体发育异常、残疾、畸形或功能障碍？
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="6" ${fn:contains(sc.healthy,'6') ? 'checked' : ''}>
                                        6、是否有酒精依赖、酗酒、药物滥用或使用镇静剂、麻醉剂（不包含治疗性麻醉）、迷幻剂、其他成瘾性药物或毒品，或者接受戒毒治疗？
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="7" ${fn:contains(sc.healthy,'7') ? 'checked' : ''}>
                                        7、是否高危妊娠或孕检异常或孕周已超过28周？是否有乳腺包块、肿块或结节、子宫肌瘤、血性溢乳、阴道不规律出血、重度宫颈炎、TCT或HPV阳性？
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="8" ${fn:contains(sc.healthy,'8') ? 'checked' : ''}>
                                        8、如为不满3周岁儿童：出生体重是否低于2.5公斤？是否为早产、难产、过期产？是否有抽搐、窒息、缺氧？
                                    </li>
                                    <li>
                                        <input type="checkbox" name="healthy" value="9" ${fn:contains(sc.healthy,'9') ? 'checked' : ''}>
                                        9、父母是否有人患过以下疾病： 卵巢癌或乳腺癌（仅对女性被保险人）、大肠癌、心肌梗塞、冠心病、脑中风、糖尿病、多发性硬化症、帕金森氏病、多囊肾性疾病。
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center pb-3 bottom-panel">
            <button type="button" id="addBtn"  class="btn1 btn-primary mr-5">保存</button>
            <button type="button" id="backBtn" class="btn1 btn-secondary">返回</button>
        </div>
    </form>
</div>
<div id="template" style="display: none;">
    <div class="member-item-temp">
        <div class="card bg-light my-3 member-item table-item" id="memberItemx" name="memberItem" itemIndex="x">
            <div class="card-body">
                <div class="delete-btn">
                    <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-member-button">删除</button>
                </div>
                <div class="form-group row">
                    <label class="col_20">姓名</label>
                    <div class="col-5">
                        <input type="text" class="form-control member_name" name="memberInformationLs[x].name" onBlur="changeName(this,'x')">
                    </div>
                </div>
                <div class="info-list">
                    <div class="form-group row">
                        <label class="col_20">性别</label>
                        <div class="col-5 gender">
                            <input type="radio" name="memberInformationLs[x].sex" value="1" class="member_sex1">男
                            <input type="radio" name="memberInformationLs[x].sex" value="2" class="member_sex2">女
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col_20">年龄</label>
                        <div class="col-5">
                            <input type="number" class="form-control member_age" name="memberInformationLs[x].age">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col_20">家庭关系</label>
                        <div class="col-5">
                            <select class="form-control member_relationshipId" name="memberInformationLs[x].relationshipId">
                                <c:forEach var="o" items="${familyTypeLs}">
                                    <option value="${o.id}">${o.content}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col_20">从事职业</label>
                        <div class="col-5">
                            <input type="text" class="form-control member_profession" name="memberInformationLs[x].profession">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="information-item-temp">
        <div class="card bg-light my-3 information-item table-item" id="informationItemx" name="informationItemx"  itemIndex="x">
            <div class="card-body">
                <div class="form-group row">
                    <label class="col_20">家庭成员</label>
                    <div class="col-5">
                        <input type="text" class="form-control information_name" name="memberInformationLs[x].userName" readonly="readonly">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">公司名称</label>
                    <div class="col-5">
                        <input type="text" class="form-control information_companyName" name="memberInformationLs[x].companyName">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">产品名称</label>
                    <div class="col-5">
                        <input type="text" class="form-control information_productName" name="memberInformationLs[x].productName">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">保障情况</label>
                    <div class="col-5">
                        <input type="text" class="form-control information_situation" name="memberInformationLs[x].situation">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">保障时间</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control information_guaranteeYears" name="memberInformationLs[x].guaranteeYears"><span>年</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">缴费期限</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control information_years" name="memberInformationLs[x].years"><span>年</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">缴费金额</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control information_payMoney" name="memberInformationLs[x].payMoney"><span>元/年</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="income-item-temp">
        <div class="card bg-light my-3 income-item table-item" id="incomeItemx" name="incomeItemx" itemIndex="x">
            <div class="card-body">
                <div class="delete-btn">
                    <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-income-button">删除</button>
                </div>
                <div class="form-group row">
                    <label class="col_20">姓名</label>
                    <div class="col-5">
                        <input class="form-control income-name" type="text" name="incomeRLQnaireLs[x].name">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">税后收入</label>
                    <div class="col-5 unit">
                        <input class="form-control income-money" type="number" name="incomeRLQnaireLs[x].money"><span>万元</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">收入来源</label>
                    <div class="col-5">
                        <input class="form-control income-source" type="text" name="incomeRLQnaireLs[x].source">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="liabilities-item-temp">
        <div class="card bg-light my-3 liabilities-item table-item" id="liabilitiesItemx" name="liabilitiesItem" itemIndex="x">
            <div class="card-body">
                <div class="delete-btn">
                    <button type="button" class="btn1 btn-sm btn-secondary ml-auto btn-del-item1 delete-liabilities-button">删除</button>
                </div>
                <div class="form-group row">
                    <label class="col_20">贷款类型</label>
                    <div class="col-5">
                        <select class="form-control liabilities-loanType" name="liabilitiesRLQnaireLs[x].loanType">
                            <option value="1">房产</option>
                            <option value="2">车辆</option>
                            <option value="3">其他</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">还款方式</label>
                    <div class="col-5">
                        <select class="form-control liabilities-repaymentType" name="liabilitiesRLQnaireLs[x].repaymentType">
                            <option value="1">等额本息</option>
                            <option value="2">等额本金</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">贷款初始金额</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control liabilities-loanMoney" name="liabilitiesRLQnaireLs[x].loanMoney"><span>万</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">贷款初始年限</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control liabilities-years" name="liabilitiesRLQnaireLs[x].years"><span>年</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">剩余还款期</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control liabilities-interestRate" name="liabilitiesRLQnaireLs[x].interestRate"><span>期</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20">贷款利率</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control liabilities-surplus" name="liabilitiesRLQnaireLs[x].surplus"><span>%</span>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col_20 unit">月供</label>
                    <div class="col-5 unit">
                        <input type="number" class="form-control liabilities-supply" name="liabilitiesRLQnaireLs[x].supply"><span>元</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/questionnaire/editQuestionnaire.js"></script>