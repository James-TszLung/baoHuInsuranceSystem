<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<div class="content">
<div class="orders_tile">
		<p class="orders_tile_s01">
			<span>个人信息修改</span>
		</p>
	</div>
<form id='userDataForm' method='post'>
	<table cellspacing='10' cellpadding='0' border='0' width='100%'>
		<tr>
			<td width='60px' align='right'>人员姓名：</td>
			<td width='100px' align='left'>${userInfo.lastName }  ${userInfo.firstName }</td>
			<td width='50px' align='right'>性别：</td>
			<td width='100px' align='left'>
			<select name='sex' disabled>
			<option value='M' <c:if test='${userInfo.sex eq "M" }'>selected="selected"</c:if>  >男</option>
			<option value='F' <c:if test='${userInfo.sex eq "F" }'>selected="selected"</c:if>  >女</option>
			</select>
			</td>
			<td width='50px' align='right'>职务：</td>
			<td width='150px'><span><span style='width:100px;'>${userInfo.position }</span>
			</span>
			</td>
		</tr>
		<tr>
			<td width='50px' align='right'>工号：</td>
			<td width='100px' align='left'>${userInfo.work_no }</td>
			<td width='50px' align='right'>状态：</td>
			<td width='100px' align='left'>
			<select name='status' disabled>
			<option value='M' <c:if test='${userInfo.status eq "Y" }'>selected="selected"</c:if>  >可用</option>
			<option value='F' <c:if test='${userInfo.status eq "N" }'>selected="selected"</c:if>  >不可用</option>
			</select>
			</td>
			<td width='50px' align='right'>手机号码：</td>
			<td width='150px' align='left'><span>
			<span style='width:100px;'>${userInfo.mobile }</span> 
			</span>
			</td>
		</tr>
		<tr>
			<%--<td width='50px' align='right'>用户类型：</td> 
			<td width='100px' align='left'>
				<select name='userType' disabled>
					<option value='M' <c:if test='${userInfo.userType eq "O" }'>selected="selected"</c:if>  >操作员</option>
					<option value='F' <c:if test='${userInfo.userType eq "M" }'>selected="selected"</c:if>  >会员</option>
				</select>
			</td>--%>
			<td width='50px' align='right'>用户组名：</td>
			<td width='100px' align='left'>${userInfo.groupName }</td>
			<td width=' 0px' align='right'>注册日期：</td>
			<td width='150px' align='left'> <fmt:formatDate value='${userInfo.initDate}' pattern='yyyy-MM-dd'/></td>
			<td width='50px' align='right'>固定电话：</td>
			<td width='100px' align='left'>${userInfo.tel }</td>
		</tr>
		<%--
		<tr>
			<td width='50px' align='right'>操作员：</td> 
			<td width='100px' align='left' colspan='3'>${userInfo.operator }</td>
			<td width='50px' align='right'>中心：</td> 
			<td width='150px' align='left'>${userInfo.corpNum }</td>
		</tr>--%>
		
		<tr id='changePwd_tr'>
			<td width='50px' align='right'>旧密码：</td>
			<td width='100px' align='left'><input type='password' name='oldword' value='' />
			</td>
			<td width='50px' align='right'>新密码：</td>
			<td width='100px' align='left'><input type='password' name='password' value='' />
			</td>
			<td width='50px' align='right'>确认密码：</td>
			<td width='100px' align='left'><input type='password' name='checknewpwd' value='' />
			</td>
		</tr>
		<tr>
			<td width='50px' align='right'>联系人：</td>
			<td width='100px' align='left'>${corpInfo.contact }</td>
			<td width='50px' align='right'>电话：</td>
			<td width='100px' align='left'>${corpInfo.tel }</td>
			<td width='50px' align='right'>公司全称：</td>
			<td width='150px' align='left'>${corpInfo.text }</td>
		</tr>
		<tr>
			<td width='50px' align='right'>公司地址：</td>
			<td width='100px' align='left' colspan='3'>${corpInfo.address }</td>
		</tr>
		<tr>
			<td colspan='3' align='right' width='50%'><input type='button' onclick="saveUserdataInfo()" value='保存' class='airlines_button' />&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td colspan='3' width='50%'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='reset' value='重置' class='airlines_button' />
			</td>
		</tr>
	</table>
	 <input type='hidden' name='userID' value="${userInfo.userID  }" style='width:100px;' />
	 <input type='hidden' name='groupID' value="${userInfo.groupID  }" style='width:100px;' />
	 <input type='hidden' name='corpID' value="${userInfo.corpID  }" style='width:100px;' />
</form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/unique/user/user/editUserMessage.js"></script>