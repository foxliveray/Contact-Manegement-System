<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="model.ConProcess"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.btn1{
position:absolute;
 top:470px; 
 right:450px;
 width:70px;
 height:40px;
 background:white;
}

</style>
</head>

<body>

<h1 style="font-family:arial;color:white;font-size:60px;background-color:black;">会签意见查看</h1>

     <%
     ConProcess conProcess = (ConProcess)request.getAttribute("conProcess");
     %>

<p >合同编号:
<%=conProcess.getConId()%>
</p>

<div align="center">会签意见：
<br> 
<!-- 会签意见填写区 -->
<textarea name="text"cols="79"rows="10"id="text" readonly="readonly"><%=conProcess.getContent()%></textarea>
</div>
<p>
<input type="button" id="turnIn" value="确定" class="btn1" onclick="window.close()"/>
</p>

</body>
</html>