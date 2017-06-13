<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">

<title>修改用户</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="description" content="">

<meta name="author" content="">
<!-- CSS -->

<link rel="stylesheet" href="assets/css/reset.css">

<link rel="stylesheet" href="assets/css/supersized.css">

<link rel="stylesheet" href="assets/css/style.css">



<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->

<!--[if lt IE 9]>

            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>

        <![endif]-->
<script type="text/javascript">
	function check() {
		var name = document.getElementById('username');
		var password = document.getElementById('password');
		var password2 = document.getElementById('password2');
		if (name.value == "") {
			alert("用户名不能为空！");
			name.focus();
			return false;
		}
		if (password.value == "") {
			alert("密码不能为空！");
			password.focus();
			return false;
		}
		if (password2.value != password.value) {
			alert("重复输入的密码需要保持一致！");
			password2.focus();
			return false;
		}
	}
</script>
</head>

<body>
	<%
	User editUser=(User)request.getAttribute("editUser"); 
	%>
	<div class="page-container">
		<h1>修改用户</h1>
		<form action="EditUserServlet" method="post">
			<input type="hidden" name="userId" id="userId" value=<%=editUser.getId() %>>
			<input type="text" name="username" class="username" id="username" value=<%=editUser.getName() %>
				placeholder="Username"> 
			<input type="password"
				name="password" class="password" id="password" value=<%=editUser.getPassword() %>
				placeholder="Password"> 
			<input type="password"
				name="password2" class="password" id="password2" value=<%=editUser.getPassword() %>
				placeholder="Repeat password">
			<button type="submit" onclick="check()">修改</button>
			<div class="error">
				<span>+</span>
			</div>
			<%request.setAttribute("userId", editUser.getId()); %>
		</form>
		<a href="ToUserListServlet" style="color: #fff; text-decoration: none"><button
				type="submit">返回</button></a>
		<div class="connect">
			<p>合同信息管理系统</p>
			<!--
                <p>

                    <a class="facebook" href=""></a>

                    <a class="twitter" href=""></a>

                </p>
                -->
		</div>

		<div class="info">
			<p>Copyright © 疯狂的拖延症 Copyright Reserved</p>
		</div>
		<!-- Javascript -->

		<script src="assets/js/jquery-1.8.2.min.js"></script>

		<script src="assets/js/supersized.3.2.7.min.js"></script>

		<script src="assets/js/supersized-init.js"></script>

		<script src="assets/js/scripts.js"></script>
</body>



</html>
