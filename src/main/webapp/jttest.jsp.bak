<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="pages/commons/decorators/default.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
<title>jTemplates</title>  
</head>  
<body>  
    <div id="result"></div>  
    <div id="foreachResult"></div>  
</body>    
<!-- 这里使用一个 script标签内容来存储显示模板，这也现在大多数js模板的做法 foreach的用法--><br><script type="text/template" id="foreach">  
    <table>  
    <thead>  
        <tr>  
            <td>Index</td>  
            <td>Iterator</td>  
            <td>Name</td>  
            <td>Age</td>  
            <td>First?</td>  
            <td>Last?</td>  
        </tr>  
    </thread>  
    <tbody>  
        {#foreach $T.table as record begin=1}  
        <tr>  
            <td>{$T.record$index}</td>  
            <td>{$T.record$iteration}</td>  
            <td>{$T.record.name}</td>  
            <td>{$T.record.age}</td>  
            <td>{$T.record$first}</td>  
            <td>{$T.record$last}</td>  
        </tr>  
        {#/for}  
    </tbody>  
</table>  
</script>  
<script>  
//$("#result").setTemplate("Template by {$T.bold()} version <em>{$Q.version}</em>.");  
//$("#result").processTemplate("jTemplates");  
$(function($){  
    var data = {  
     name: 'User list',  
     list_id: 6,  
     table: [  
      {id: 1, name: 'Anne', age: 22, mail: 'anne@domain.com'},  
      {id: 2, name: 'Amelie', age: 24, mail: 'amelie@domain.com'},  
      {id: 3, name: 'Polly', age: 18, mail: 'polly@domain.com'},  
      {id: 4, name: 'Alice', age: 26, mail: 'alice@domain.com'},  
      {id: 5, name: 'Martha', age: 25, mail: 'martha@domain.com'}  
     ]  
    };  
    var mydata = { name: "Anne", age: "20" };  
      
    $("#result").setTemplate("{#if $T.list_id == 4} good {#elseif $T.list_id == 5} normal {#else} bad {#/if}");  
      
    $("#result").processTemplate(data);  
      
    $('#foreachResult').setTemplate($('#foreach').html()).processTemplate(data);  
    $('#foreachResult').delegate('td','click',function(){  
        alert($(this).text());  
    });  
});  
</script>  
</html>