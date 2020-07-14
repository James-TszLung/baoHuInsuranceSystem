<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ attribute name="typeId" type="java.lang.Integer" required="true" description="操作类型id"%>
<%
	StringBuffer resOpTypeName = new StringBuffer();
	if(null!=typeId){
		switch(typeId){
			case 1:resOpTypeName.append("生成订单");break;
			case 10:resOpTypeName.append("生成PNR");break;
			case 20:resOpTypeName.append("修改订单");break;
			case 30:resOpTypeName.append("订单支付");break;
			case 40:resOpTypeName.append("取消订单");break;
			case 50:resOpTypeName.append("确认出票");break;
			case 60:resOpTypeName.append("订单锁定");break;
			case 61:resOpTypeName.append("订单解锁");break;
			case 70:resOpTypeName.append("转单");break;
			case 71:resOpTypeName.append("接口出票");break;
			case 72:resOpTypeName.append("平台支付代扣");break;
			case 73:resOpTypeName.append("BSP自动出票");break;
			case 80:resOpTypeName.append("财务收银");break;
			case 90:resOpTypeName.append("升舱改期");break;
			case 100:resOpTypeName.append("订单调账");break;
			case 110:resOpTypeName.append("申请退废");break;
			case 120:resOpTypeName.append("关联订单");break;
			case 130:resOpTypeName.append("发送短信");break;
			case 140:resOpTypeName.append("打印配送单");break;
			case 150:resOpTypeName.append("拒绝出票");break;
			case 160:resOpTypeName.append("订单挂起");break;
			case 165:resOpTypeName.append("订单解挂");break;
			case 184:resOpTypeName.append("发送订单");break;
			case 185:resOpTypeName.append("安排配送");break;
	 
	        case 212:resOpTypeName.append("增加保险");break;
			case 241:resOpTypeName.append("手工生成退废单");break;
			case 242:resOpTypeName.append("接口申请退废");break;
			case 243:resOpTypeName.append("修改退废");break;
			case 244:resOpTypeName.append("回传退票费");break;
			case 245:resOpTypeName.append("审核接口订单");break;
			case 246:resOpTypeName.append("审核退废单");break;
			case 247:resOpTypeName.append("自动作废");break;
			case 248:resOpTypeName.append("拒绝退废");break;
			case 249:resOpTypeName.append("退废处理");break;
			case 250:resOpTypeName.append("网点经理审核");break;
			case 251:resOpTypeName.append("结算审核");break;
			case 252:resOpTypeName.append("出纳审核");break;
			case 253:resOpTypeName.append("上传退票附件");break;
			case 254:resOpTypeName.append("取消退废单");break;
			case 258:resOpTypeName.append("自动符合");break;
			case 259:resOpTypeName.append("立即审核");break;
			case 269:resOpTypeName.append("财务收银");break;
			case 270:resOpTypeName.append("退废处理");break;
			case 271:resOpTypeName.append("释放VT");break;
			default:resOpTypeName.append("未知类型");break;
		}
		
	}
	out.print(resOpTypeName);
%>