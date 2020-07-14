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
		if(productType.equals("A")){
			typeName.append("机票");
		}else if(productType.equals("B")){
			typeName.append("基础信息");
		}else if(productType.equals("C")){
			typeName.append("租车");
		}else if(productType.equals("F")){
			typeName.append("机场服务");
		}else if(productType.equals("H")){
			typeName.append("酒店");
		}else if(productType.equals("I")){
			typeName.append("保险");
		}else if(productType.equals("S")){
			typeName.append("系统管理");
		}else if(productType.equals("M")){
			typeName.append("充值短信");
		}else if(productType.equals("X")){
			typeName.append("退废票关联");
		}else if(productType.equals("Z")){
			typeName.append("其它");
		}
	}
	out.print(typeName);
%>