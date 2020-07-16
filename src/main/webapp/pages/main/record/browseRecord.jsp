<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/unique/user/function.css" type="text/css"></link>
<%--页面组件部分--%>
<form id="customizePlanForm" method="post" onkeydown="if(event.keyCode==13){return false;}" >
	<div id="tgySteward_search_from" class="query_condition_body">
		<table width="98%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="22%"><label>浏览内容：</label><input name="content" type="text" class="condition"/></td>
				<td width="22%"><label>所属类别：</label>
					<select name="type" class="validatebox-text">
						<option value="">全部</option>
						<option value="1">产品</option>
						<option value="2">文章</option>
						<option value="3">视频</option>
					</select>
				</td>
				<td width="30%"><label>浏览时间：&nbsp;</label> <input
						name="startTime" placeholder="输入开始时间" id="startTime" type="text"
						class="Wdate" onclick="WdatePicker()" value="" style="width:105px;" />
					- <input name="endTime" placeholder="输入结束时间" id="endTime"
							 type="text" class="Wdate" onclick="WdatePicker()" value=""
							 style="width:105px;" />
				</td>
				<td width="20%" align="center"><input type="button" class="airlines_button" value="搜索" onclick="javascript:doSearch(true);" />
				</td>
			</tr>
		</table>
	</div>
</form>
<div class="btn_tool_bar" >
<%--	<input  type="button" class="btn" value="添加" onclick="addView()" style="width:75px"/>--%>
</div>
<div id="productDown" style="display:none"></div>
<div id="resultList"></div>
<div class="display_div"></div>

<script>
	/**
	 * 页面初始化
	 */
	function initPage() {
		if (nowLoc.length == 2)
			$("#page_loc_title").html(nowLoc[1]);
		else
			$("#page_loc_title").html(nowLoc[2]);

		doSearch(true);

	}
	/**
	 * 分页查询方法true为第一次查询分页查询，false为下一页等操作用
	 *
	 * @param
	 * @returns
	 */
	var lastPostData;
	function doSearch(f) {
		var searchURL = "/record/serachBrowseRecord.htm";
		if (f) {
			var url = ctxPath + searchURL;
			lastPostData = $('#tgySteward_search_from :input').serializeArray();
			$("#resultList").load(url, lastPostData);
		} else {
			var url = ctxPath + searchURL;
			var postData = lastPostData.concat($('#resultList :input').serializeArray());
			$("#resultList").load(url, postData);
		}
	}
</script>
