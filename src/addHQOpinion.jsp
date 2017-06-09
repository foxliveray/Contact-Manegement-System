<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.btn1{
position:absolute;
 top:470px; 
 right:550px;
 width:70px;
 height:40px;
 background:white;
}

.btn2{
position:absolute;
 top:470px; 
 right:400px;
 width:70px;
 height:40px;
 background:white;
}


</style>
</head>

<body>
<form action="AddHQOpinionServlet" method="POST"> 

<script>
//提交点击事件
function OnTurnIn()
{
var x=document.getElementById("text").value;
if(x == ""){
	alert("Countersign opinion can not be empty!");
}else{
	document.forms[0].submit();
}
}
//重置点击事件
function OnReset()
{
x=document.getElementById("text");  // 找到元素
x.innerHTML="";    // 改变内容
}

</script>
			
<h1 style="font-family:arial;color:white;font-size:60px;background-color:black;">会签合同</h1>

     <%
				String contract = (String)request.getAttribute("contract");
     %>
<p >合同名称:
<%=contract%>
</p>

<div align="center">会签意见：
<br> 
<!-- 会签意见填写区 -->
<textarea name="text"cols="79"rows="10"id="text"></textarea>
</div>
<p>
<input type="button" id="turnIn" value="提交" class="btn1" onclick="OnTurnIn()"/>
<input type="button" id="reset" value="重置" class="btn2" onclick="OnReset()"/>
</p>

 </form>

</body>
</html>