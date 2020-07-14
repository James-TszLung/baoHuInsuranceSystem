<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- 标签引入 --%>
<%--<%@	taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--
Note: to use this file, include this JSP inside a form.
Then in the controller, use getPagingBean() to pass to the BO and then DAO.
--%>
<script type="text/javascript" defer>
var fnOnGoToPage${pagingId};
function gotoPage${pagingId}(pageNo) {
	if(isNaN(pageNo) || pageNo <= 0){
		alert("页码，请填写数字！");
		return false;
	}
	$("#pageNo").val(pageNo);
	//当页面存在fnOnGoToPage时，不自动提交表单，调用fnOnGoToPage
	if(fnOnGoToPage${pagingId}){
		fnOnGoToPage${pagingId}(pageNo);
	}else{
		document.getElementById("pageNo").form.submit();
	}
	return false;
}
</script>

<c:if test="${sc.totalPageCount>0}">
	<div class=page_list>
		<div class="left">
			<span>
				<fmt:message key="list.paging.total">
					<fmt:param>${sc.totalPageCount}</fmt:param>
					<fmt:param>${sc.totalCount}</fmt:param>
				</fmt:message>
			</span>
			<select class="inputtext" name="pageSize" onchange="gotoPage${pagingId}(1)">
				<option value="10" <c:if test="${sc.pageSize eq 10}">selected="true"</c:if>>每页10条</option>
				<option value="20" <c:if test="${sc.pageSize eq 20}">selected="true"</c:if>>每页20条</option>
				<option value="30" <c:if test="${sc.pageSize eq 30}">selected="true"</c:if>>每页30条</option>
				<option value="50" <c:if test="${sc.pageSize eq 50}">selected="true"</c:if>>每页50条</option>
				<option value="100" <c:if test="${sc.pageSize eq 100}">selected="true"</c:if>>每页100条</option>
			</select>
		</div>
		<div style="margin-left: 2em" class="right">
			<c:if test="${sc.totalPageCount>1}">
				<c:choose>
					<c:when test="${sc.pageNo<=1}">
						<a class="no_first" title='<fmt:message key="list.paging.goto1"/>'>
							<fmt:message key="list.paging.goto1" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)" class="first" title='<fmt:message key="list.paging.goto1"/>'
							onclick='gotoPage${pagingId}(1);return false;'>
							<fmt:message key="list.paging.goto1" />
						</a>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${sc.pageNo<=1}">
						<a class="no_prev1" title='<fmt:message key="list.paging.prev"/>'>
							<fmt:message key="list.paging.prev" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)" class="prev1" title='<fmt:message key="list.paging.prev"/>'
							onclick='gotoPage${pagingId}(<c:choose><c:when test="${(sc.pageNo-1) lt 1}">1</c:when><c:otherwise>${sc.pageNo-1}</c:otherwise></c:choose>);return false;'>
							<fmt:message key="list.paging.prev" />
						</a>
					</c:otherwise>
				</c:choose>

				<c:set var="minPager"
					value="${(sc.totalPageCount-sc.pageNo>=4)?(sc.pageNo >= 5 ? sc.pageNo - 4
                      : 1):(sc.pageNo >= (5 + 4 - sc.totalPageCount + sc.pageNo) ? (sc.pageNo
                              - 8 + sc.totalPageCount - sc.pageNo) : 1)}" />
				<c:set var="maxPager" value="${(minPager + 8 > sc.totalPageCount) ? sc.totalPageCount : (minPager + 8)}" />
				<c:forEach var="idx" begin="${minPager}" end="${maxPager}" step="1">
					<c:if test="${sc.pageNo!=idx}">
						<a href="javascript:gotoPage${pagingId}(${idx});void(0)"
							title="<fmt:message key="list.paging.gotoN"><fmt:param>${idx}</fmt:param></fmt:message>">${idx} </a>
					</c:if>
					<c:if test="${sc.pageNo==idx}">
						<a class="up"> ${idx} </a>
					</c:if>
				</c:forEach>

				<c:choose>
					<c:when test="${sc.pageNo>=sc.totalPageCount}">
						<a class="no_next2" title='<fmt:message key="list.paging.next"/>'>
							<fmt:message key="list.paging.next" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)" class="next2" title='<fmt:message key="list.paging.next"/>'
							onclick='gotoPage${pagingId}(<c:choose><c:when test="${(sc.pageNo+1) gt sc.totalPageCount}">${sc.totalPageCount}</c:when><c:otherwise>${sc.pageNo+1}</c:otherwise></c:choose>);return false;'>
							<fmt:message key="list.paging.next" />
						</a>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${sc.pageNo>=sc.totalPageCount}">
						<a class="no_last" title='<fmt:message key="list.paging.gotoEnd"/>'>
							<fmt:message key="list.paging.gotoEnd" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)" class="last" title='<fmt:message key="list.paging.gotoEnd"/>'
							onclick='gotoPage${pagingId}(${sc.totalPageCount});return false;'>
							<fmt:message key="list.paging.gotoEnd" />
						</a>
					</c:otherwise>
				</c:choose>

				<input type="text" style="width:40px" class="inputtxt" value="${sc.pageNo}" id="pager_input" onkeyup="value=value.replace(/[^\d]/g,'');" />
				<a href="javascript:void(0)" class="goto" onclick="gotoPage${pagingId}(document.getElementById('pager_input').value);return false;"
					title="<fmt:message key="list.paging.go"/>">
					<fmt:message key="list.paging.go" />
				</a>

			</c:if>
		</div>
	</div>
	<input type="hidden" id="pageNo" name="pageNo" value="${sc.pageNo}">
</c:if>