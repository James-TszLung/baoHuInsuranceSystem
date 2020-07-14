<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--css/js文件导入--%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/pushEssay/pushEssay_jquery.js"></script>


<%--页面组件部分--%>

<div id="tgySteward_search_from" class="query_condition_body">
	<!-- <input type="hidden" name="typeID" value="20"> -->
	<table width="98%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20%"><label>文章标题：</label><input placeholder="输入文章标题" name="title" type="text" class="condition" /></td>
	        <td width="20%"><label>内容简介：</label><input placeholder="输入内容简介" name="introduction" type="text" class="condition" /></td>
			<td width="15%"><label>所属类别：</label>
				<select name="typeDictionaryId" class="validatebox-text">
					<option value="">全部</option>
					<c:forEach items="${typeLs}" var="s">
						<option  value="${s.id}">${s.content}</option>
					</c:forEach>
				</select>
			</td>
			<td width="15%"><label>推送：</label>
				<select name="pushFlag" class="validatebox-text">
					<option value="">全部</option>
					<option  value="1">小程序首页</option>
					<option  value="2">小程序栏目精选</option>
					<option  value="3">网站首页</option>
				</select>
			</td>
			<td width="20%" align="center"><input name="" type="submit" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
			</td>
		</tr>
	</table>
</div>
<div class="btn_tool_bar" >
		<input id="" name="" type="button" class="btn" value="添加" onclick="addView()" style="width:75px"/>
	<input type="button" class="btn" value="保存排序" onclick="saveSort()" style="width:75px"/>
</div>
<div id="resultList"></div>
<div class="display_div"></div>
 