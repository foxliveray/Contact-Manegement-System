<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Contract"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>合同审批</title>
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<script type="text/javascript">
			function check(){
				var content = document.getElementById('content');
				if(content.value == ""){
					alert("审批信息不能为空(°Д°)");
					content.focus();
					return false;
				}
			}
			
			function reset(){
				document.getElementById("content").value="";
			}
		</script>		
	</head>

	<body>
		<div class="mtitle">
			合同审批
		</div>
		<br />
		<form action="AddApprovedOpinionServlet" method="post">
			<%
				Contract contract = (Contract)request.getAttribute("contract");
			%>
			<input type="hidden" name="conId" value="<%=contract.getId()%>">
			<table class="update" style="width:600px;">	
				<tr height="28">
					<td width="140px">合同名称：</td>
					<td><%=contract.getName()%></td>
				</tr>
				<tr>
					<td>
						&nbsp;<input name="standpoint" type="radio" value="true" checked="checked"/>
						赞成
						<br /><br />
						&nbsp;<input name="standpoint" type="radio" value="false" />
					           反对
					</td>
					<td>
						<textarea rows="10" cols="40" id="content" name="content"
							style="width: 400px; height: 100px; resize: none;"></textarea>
					</td>
				</tr>
				<tr height="28">
					<td align="center" colspan="2">
						<input type="submit" value="提交" class="button" onclick="return check()">
						 &nbsp; &nbsp; &nbsp;
						<input type="reset" value="重置" class="button" onclick="reset()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
