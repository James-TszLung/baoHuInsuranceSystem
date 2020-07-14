<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/product/product_jquery.js"></script>



<%--页面组件部分--%>
<form id="productForm" method="post" onkeydown="if(event.keyCode==13){return false;}" >
	<div id="tgySteward_search_from" class="query_condition_body">
		<table width="98%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="22%"><label>产品名称：</label><input name="name" type="text" class="condition"/></td>
				<td width="18%"><label>所属类别：</label>
					<select name="insuranceTypeId" class="validatebox-text">
						<option value="">全部</option>
						<c:forEach var="item" items="${typeLs}">
							<option value="${item.id}">${item.content}</option>
						</c:forEach>
					</select>
				</td>
				<td width="18%"><label>销售渠道：</label>
					<select name="recommendChannelId" class="validatebox-text">
						<option value="">全部</option>
						<c:forEach var="item" items="${saleChannellist}">
							<option value="${item.id}">${item.content}</option>
						</c:forEach>
					</select>
				</td>
				<td width="18%"><label>承保公司：</label>
					<select name="insuranceCompanyId" class="validatebox-text">
						<option value="">全部</option>
						<c:forEach var="item" items="${compayls}">
							<option value="${item.id}">${item.companyName}</option>
						</c:forEach>
					</select>
				</td>
				<td width="18%"><label>状态：</label>
					<select name="status" class="validatebox-text">
						<option value="">全部</option>
						<option value="-1">未发布</option>
						<option value="1">已发布</option>
						<option value="2">已下线</option>
					</select>
				</td>

				<td width="20%" align="center"><input type="button" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="btn_tool_bar" >
	<input  type="button" class="btn" value="添加" onclick="addView()" style="width:75px"/>
<%--	<input  type="button" class="btn" value="导出" onclick="exportProduct()" style="width:75px"/>--%>
</div>
<div id="productDown" style="display:none"></div>
<div id="resultList"></div>
<div class="display_div"></div>
