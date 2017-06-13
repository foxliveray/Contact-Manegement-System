<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Contract Management System - Administrator menu bar</title>
	<link href="css/frame.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
	<div class="menu">
		
		<dl>
		    <dt>
				系统管理
			</dt>
			<dd>
				<a href="ToUserListServlet">用户管理</a>
			</dd>
			<dd>
				<a href="#">角色管理</a>
			</dd>		
			<dd>
				<a href="toYhqxList" target="main">权限配置</a>
			</dd>
			<dd>
				<a href="#">日志管理</a>
			</dd>	
		</dl>
		<dl>
			<dt>
				合同管理
			</dt>
			<dd>
				<a href="toDfphtList" target="main">合同分配</a>
			</dd>
			<dd>
				<a href="#">Assigned Contract</a>
			</dd>
			<dd>
				<a href="#">Query Process</a>
			</dd>
			<dd>
				<a href="#">Contract Info</a>
			</dd>
			<dd>
				<a href="#">Customer Info</a>
			</dd>
		</dl>
	</div>
  </body>
</html>
