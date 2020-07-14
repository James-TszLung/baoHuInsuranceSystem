<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.commons.lang.math.NumberUtils"%>
<%@page contentType="text/html" session="false" pageEncoding="UTF-8" %>
<%@include  file="/WEB-INF/jspf/import.jspf" %>
<% 
String callback = StringUtils.defaultIfBlank(request.getParameter("callback"),"");
out.clearBuffer();
if (callback!=null && callback.trim().length()>0){
	out.write(callback);
	out.write("(");
}
%><%--
--%><%
long parent_id      = NumberUtils.toLong(request.getParameter("parent_id"));
int pageNo          = NumberUtils.toInt(request.getParameter("page_num"),1);
int pageSize        = NumberUtils.toInt(request.getParameter("per_page"),25);
String q_word       = StringUtils.defaultIfBlank(request.getParameter("q_word"),"").trim();
int forData		    = NumberUtils.toInt(request.getParameter("forData"),0);
Object[] params 	= q_word.length()>0?new Object[]{("%"+q_word+"%").toLowerCase(),(q_word+"%").toLowerCase()}:null;
boolean multiple    = "true".equalsIgnoreCase(request.getParameter("multiple"));
//System.out.println("ajaxComboBox.jsp q_word="+q_word);
//查组
String sql = "SELECT departmentId as id,name,1 as level FROM gl_department where parent_departmentId>0 "
		+(q_word.length()>0?" and LOWER(name) LIKE ?":"");     	
if (forData==1){ //查组员
	sql="SELECT userId as id,name from gl_user where departmentId=? "
		 +(q_word.length()>0?" and LOWER(name) LIKE ?":"");     	
	params=q_word.length()>0?new Object[]{new Long(parent_id),("%"+q_word+"%").toLowerCase(),(q_word+"%").toLowerCase()}
		:new Object[]{new Long(parent_id)};
}
/* System.out.println("ajaxComboBox.jsp callback="+JSON.toJSONString(params));
System.out.println("ajaxComboBox.jsp sql2="+sql); */
Map pager=search(sql, pageNo, pageSize, params);

out.write(JSON.toJSONString(pager));
%><%
if (callback!=null && callback.trim().length()>0){
out.write(");");
} 
%>
<%!
public Map search(String sql,int pageNo,int pageSize,Object[] params) throws Exception{
        Map pager =new HashMap();                
        String sqlCount="select count(*) from ("+sql+") c ";
        int total = GeliUtils.getDao().count(sqlCount,params);       
        List list = GeliUtils.getDao().page(sql,pageNo,pageSize,params);       
        pager.put("cnt", total);
        pager.put("result",list);
        return pager;
    }
%>