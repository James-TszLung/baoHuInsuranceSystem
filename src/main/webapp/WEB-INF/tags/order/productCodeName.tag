<%--
功能: 显示map里动态key值数据。
--%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<%@ attribute name="productType" type="java.lang.String" required="true" description="产品类型"%>
<%
	StringBuffer typeName = new StringBuffer();
	if(null!=productType){
		if(productType.equals("1")){
			typeName.append("国内机票");
		}else if(productType.equals("2")){
			typeName.append("国际机票");
		}else if(productType.equals("3")){
			typeName.append("酒店");
		}else if(productType.equals("4")){
			typeName.append("保险");
		}else if(productType.equals("5")){
			typeName.append("租车");
		}else if(productType.equals("6")){
			typeName.append("其他产品");
		}else if(productType.equals("7")){
			typeName.append("组合产品");
		}else if(productType.equals("8")){
			typeName.append("机加酒");
		}else if(productType.equals("9")){
			typeName.append("关联订单");
		}
	}
	out.print(typeName);
%>