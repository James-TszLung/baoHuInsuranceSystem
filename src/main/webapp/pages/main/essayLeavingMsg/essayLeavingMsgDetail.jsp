<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/cropper.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/selector.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newStyle/style.css" type="text/css"></link>



<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/cropper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newScript/Selector.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/plugins/editor/icomoon/style.css" type="text/css"></link>


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

select {
	width: 247px;
}

.skill-cls input {
	width: 13px;
}
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<input type="hidden" name="id" id="id" value="${sc.id}" />
			<div class="policy_added">
				<ul>
					<li><label>用户姓名：</label> <span class="sel_span">${sc.user.name}
					</span></li>
					
					<li><label>留言内容：</label> <span class="sel_span">${sc.content}
					</span></li>
						<li><label>提交时间：</label> <span class="sel_span"> <fmt:formatDate value="${sc.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</span></li>
					<c:if test="${sc.status==2}">
						<li>
							<label>*回复内容：</label>
							<span class="sel_span">
								<textarea placeholder="请输入回复内容" name="reply" id="reply" 
									style="margin-left:0px;height:112px;"></textarea>
							</span>
						</li>
					</c:if>
						<c:if test="${sc.status==4}">
						<li>
							<label>回复内容：</label>
							<span class="sel_span">
								${sc.reply}
							</span>
						</li>
					</c:if>
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
								<c:if test="${sc.status==1}">
					      <input  onclick="audit('${sc.id}','2');" type="button" value="通过" class="city_airport_button_s01" />
							&nbsp;&nbsp; <input  onclick="audit('${sc.id}','3');" type="button" value="不通过" class="city_airport_button_s01" />
							&nbsp;&nbsp;
							</c:if>
								<c:if test="${sc.status==2}">
					      <input  onclick="audit('${sc.id}','4');" type="button" value="保存" class="city_airport_button_s01" />
							&nbsp;&nbsp; 
							</c:if>
							<input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" />
							&nbsp;&nbsp;
						</div></li>
				</ul>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/essayLeavingMsg/replyEssayLeavingMsg.js"></script>







