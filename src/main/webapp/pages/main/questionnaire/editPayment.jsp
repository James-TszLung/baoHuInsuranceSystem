<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<style>
    ul {
        overflow: hidden;
    }
    .ui_addUser ul li {
        width: 100%;
    }
    .ui_addUser ul li label {
        width: 110px;
        position: static;
    }

    input {
        border: 1px solid #bbb;
    }

    .sel-pic {
        border: 1px solid #e5e5e5;
        border-radius: 5px;
        padding: 6px 15px 6px 15px;
        background: #118eea;
    }
    .sel-pic .btn-pic{
        color: #fff;
        cursor: pointer;
    }
   .sel-pic input[type=file] {
       position: absolute;
       opacity: 0;
       width: 60px;
       left: 105px;
       cursor: pointer;
    }
    .ViewQistyle {
        　　position: fixed;
        　　top: 0;
        　　left: 0;
        　　background: rgba(0, 0, 0, 0.7);
        　　z-index: 2;
        　　width: 100%;
        　　height: 100%;
        　　display: none;
    }

</style>
<div class="content">
    <div class="ui_addUser">
        <form id="addform" enctype="multipart/form-data" method="POST" autocomplete="off">
            <input type="hidden" name="questionnaireId" value="${questionnaireId}" />
            <div class="policy_added">
                <ul>
                    <li><label>*收款交易单号：</label>
                        <span class="sel_span">
                            <input type="text" name="orderId" id="orderId" value="${empty sc ? '' : sc.orderId}" />
                        </span>
                    </li>
                    <li><label>付款时间:</label>
                        <span class="sel_span">
                            <input name="payTime" type="text" class="Wdate form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:220px;height: 25px;" value="<fmt:formatDate value="${empty sc ? '' : sc.payTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
                        </span>
                    </li>
                    <li><label>收款金额:</label>
                        <span class="sel_span">
                            <input type="number" name="payMoney" id="payMoney" value="${empty sc ? '' : sc.payMoney}">
                        </span>
                    </li>
                    <li>
                        <label id="picfc">*收款证明:</label>
                        <span class="sel_span sel-pic">
                            <span class="btn-pic">${empty sc ? '上传' : '替换'}</span>
                            <input type="file" id="upload_file_input">
                            <input type="hidden" name="payImgId" id="payImgId" value="${empty sc ? '' : sc.payImgId}" />
                            <input type="hidden" name="image" id="image">
                            <input type="hidden" name="fileName" id="fileName">
                            <input type="hidden" name="fileType" id="fileType">
                            <input type="hidden" name="dir" id="dir" value="quest">
                        </span>
                    </li>
                    <li><label>&nbsp;</label>
                        <span class="sel_span" id="showImg">
                            <c:if test="${not empty sc}">
                                <img style="width:120px;height:120px;" src="${ctxPath}/uploadFile/pictureView.htm?id=${sc.payImgId}" />
                            </c:if>
                        </span>
                    </li>
                    <li style="border-bottom:0">
                        <div style="text-align:center; margin-top:30px;">
                            <input id="addBtn" type="button" value="提交" class="city_airport_button_s01" /> &nbsp;&nbsp;
                            <input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" /> &nbsp;&nbsp;
                        </div>
                    </li>
                </ul>
            </div>
        </form>
    </div>
    <div id="ViewQi" class="ViewQistyle">
        　　<div id="showdiv" style="position: absolute;">
        　　　　<img id="imgsrc" style="border: 5px solid #fff;" src="" runat="server" />
        　　</div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/questionnaire/editPayment.js"></script>




