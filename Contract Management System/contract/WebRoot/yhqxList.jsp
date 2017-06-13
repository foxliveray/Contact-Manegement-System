<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.PermissionBusiModel"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<title>用户权限列表</title>
	</head>

	<body>
		<div class="mtitle">
			用户权限列表
		</div>

		<div class="search">
			<form>
				在用户中搜索：
				<input value="请输入相关搜索条件..." />
				&nbsp;&nbsp;
				<input type="submit" value="搜索" class="search-submit" />
				<br />
			</form>
		</div>

		<div class="list">
			<table>
				<tr>
					<th>
						用户名
					</th>
					<th class="th2">
						角色名
					</th>
					<th class="th2">
						操作
					</th>
				</tr>
				<%
					List<PermissionBusiModel> permissionList = (List<PermissionBusiModel>) request
							.getAttribute("permissionList");
					for (PermissionBusiModel pbm : permissionList) {
				%>
				<tr>
					<td class="tdname">
						<%=pbm.getUserName()%>
					</td>
					<td>
						<%=pbm.getRoleName()%>
					</td>
					<td>
						<a
							href="toAssignPerm?userId=<%=pbm.getUserId()%>&uName=<%=pbm.getUserName()%>&roleId=<%=pbm.getRoleId()%>">
							<img src="images/cog_edit.png" alt="Authorize" /> 授权 </a>
					</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="3">
					</td>
				</tr>
			</table>
		</div>

		<div align="right" class="pagelist">					
			<a href="#"><img src="images/page/first.png"  alt="" /></a> &nbsp;
			<a href="#"><img src="images/page/pre.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/next.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/last.png"  alt="" /></a>&nbsp;
					
			<span class="pageinfo">
				总计&nbsp;<strong>2</strong>&nbsp;页&nbsp;<strong>13</strong>&nbsp;条记录
			</span>	
		</div>
	</body>
</html>
