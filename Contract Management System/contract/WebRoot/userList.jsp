<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Role list</title>
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<script>
			function deleteCheck(url){
				var result=confirm("确认删除？");
				if(result){
					window.location.assign(url);
				}
			}
		</script>
	</head>

	<body>
		<div class="mtitle">
			用户列表
		</div>
		<br />

		<div style="text-align:right;width:480px;">
		   <a href="addUser.jsp">
				<img src="images/add.png"  alt="Add Role" />
				添加用户
			</a>
		</div>

		<div class="list">
		  <table id="userList">
			<tr>
				<th style="width:100px;">
					用户名
				</th>
				<th>
					密码
				</th>
				<th style="width:100px;">
					修改
				</th>
				<th>
					删除
				</th>
			</tr>
			<% List<User> userList = (List<User>)request.getAttribute("userList");
			  for (int i=0;i<userList.size();i++) {
		        	User oneUser=userList.get(i);
		     %>
			<tr>
				<td class="tdname">
					<%=oneUser.getName() %>
				</td>
				<td class="tdname">
					<%=oneUser.getPassword() %>
				</td>
				<td>
					<a href="ToEditUserServlet?selectUserId=<%=oneUser.getId()%>">
						<img src="images/icon-edit.png"  alt="Edit" />
						修改
					</a>
				</td>
				<td>
					<input type="submit" value="删除" class="button" onclick="deleteCheck('DeleteUserServlet?selectUserId=<%=oneUser.getId()%>')">
				</td>
			</tr>
			<%} %>
			<tr>
				<td colspan="4"> </td>
			</tr>
		  </table>
		</div>

		<div align="right" class="pagelist">					
			<a href="#"><img src="images/page/first.png"  alt="" /></a> &nbsp;
			<a href="#"><img src="images/page/pre.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/next.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/last.png"  alt="" /></a>&nbsp;
					
			<span class="pageinfo">
				Total&nbsp;<strong>2</strong>&nbsp;pages&nbsp;<strong>13</strong>&nbsp;records
			</span>	
		</div>
	</body>
</html>
