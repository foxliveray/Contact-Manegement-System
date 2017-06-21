<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String ConId=(String)request.getAttribute("conId");
String Name=(String)request.getAttribute("name1");
String Customer=(String)request.getAttribute("customer");
String begin=(String)request.getAttribute("beginTime");%>
<%String end=(String)request.getAttribute("endTime");
String Content=(String)request.getAttribute("content");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>show contract</title>
<style>
#header {
    background-color:DarkTurquoise;
    color:black;
    text-align:left;
    padding:5px;
}

#section {
	height:20px;
    text-align:center;
    padding:20px;	 	 
}
#footer {
    background-color:DarkTurquoise;
    color:black;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}

</style>
<script type="text/javascript">
function onModify()
{
	document.forms[0].action="ToModifyServlet";
	document.forms[0].submit();
}
</script>
</head>
<body>
<div id="header">
<h1>查看合同</h1>
</div>

<form action="" method="Post">
<p>
<input type="text" id="conId" name="conId" value=<%=ConId%> style="visibility:hidden"onfocus=this.blur()>
</p>
<hr/>
<p>合同名称：  
<input type="text"id="name1"name="name1"value=<%=Name%> size="40"style="border:0px;color:black;font-size:14px;"onfocus=this.blur()/>
</p>
<hr/>
<p>客户名称：  
<input type="text"id="customer"name="customer"value=<%=Customer%> size="40" style="border:0px;color:black;font-size:14px;"onfocus=this.blur()/>
</p>
<hr/>
<p>开始时间：
<input type="text"id="beginTime"name="beginTime"value=<%=begin%> size="40"style="border:0px;color:black;font-size:14px;"onfocus=this.blur() />
</p>
<hr/>
<p>结束时间：
<input type="text"id="endTime"name="endTime"value=<%=end%> size="40"style="border:0px;color:black;font-size:14px;"onfocus=this.blur() />
</p>
<hr/>

<p>合同内容：</p>
<textarea id="content"name="content"style="width:800px;height:200px;font-size:14px;"onfocus=this.blur()>
<%=Content%>
</textarea>
<hr/>
<input type="button" value="确认"onClick="window.close()"/>
<input type="button" value="修改"onClick="onModify()"/>
</form>
</body>
</html>