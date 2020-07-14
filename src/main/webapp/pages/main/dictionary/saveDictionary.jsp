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
.title-block span:nth-child(1) {
	display: inline-block;
    width: 600px;
    border-radius: 4px;
    background-color: grey;
    color: white;
    text-align: center;
    margin-right: 9px;
	
}
.title-block span:nth-child(2) {
	display: inline-block;
    width: 200px;
    border-radius: 4px;
    background-color: grey;
    color: white;
    text-align: center;
	
}
.form-block input:nth-child(1){
    width: 600px;
}
.form-block input:nth-child(2){
    width: 200px;
}
</style>
<div class="content">
	<div class="ui_addUser">
		<form id="addform" enctype="multipart/form-data" autocomplete="off">
			<div class="policy_added">
				<ul>
					<li>
						<label>*字段名称：</label>
						<span class="sel_span">
							<input placeholder="请输入字段名称" type="text" name="name" id="name" />
						</span>
					</li>
		          
		          <li>
						<label>字段內容:</label>
						<span class="sel_span">
							<table>
								<tr>
									<td id="fwphtd">
										<!-- 其他要求   -->
										<div style="width:100%;overflow:hidden;">
											<input id="addstand" onclick="addother()" type="button" value="增加" class="city_airport_button_s01" />
										</div>
										<br>
										<div  class="qtdiv"  style="width:100%;overflow:hidden;margin-bottom: 8px;">
											<span class="title-block">
												<span class="table-title">字段内容</span>
												<span class="table-title">排序</span>
											</span>
										</div>      
										<div  id='qtdiv0' class="qtdiv" name="qtdiv" index='0' style="width:100%;overflow:hidden;">
											<span class="form-block" >
												<input name="contentLs[0].content" type="text" /> : <input name="contentLs[0].sort"  type="number">
											</span>
											&nbsp&nbsp
											<a onclick="delqt('0')" style="color:#2A9CD5">删除</a>
										</div>&nbsp
										
									</td>
								</tr>
							</table>
						</span>
					</li>
					
					
					<li style="border-bottom:0">
						<div style="text-align:center; margin-top:30px;">
							<input id="addBtn" type="button" value="提交" class="city_airport_button_s01" />
							&nbsp;&nbsp;
							<input id="backBtn" type="button" value="返回" style="background-color:#cccccc;color:#ffffff;" class="city_airport_button_s01" />
							&nbsp;&nbsp;
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>





<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/dictionary/AddDictionary_jquery.js"></script>





