<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contract Management System - Operator menu bar</title>
<link href="css/frame.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="menu">
		<dl>
			<dt>合同起草</dt>
			<dd>
				<a href="addContract.htm" target="main">起草合同</a>
			</dd>
			<dd>
				<a href="ddghtList.htm" target="main">待定稿合同</a>
			</dd>
			<dd>
				<a href="ydghtList.htm" target="main">已定稿合同</a>
			</dd>
			<dd>
				<a href="#">流程查询</a>
			</dd>
		</dl>
		<dl>
			<dt>合同会签</dt>
			<dd>
				<a href="dhqhtList.htm" target="main">待会签合同</a>
			</dd>
			<dd>
				<a href="yhqhtList.htm" target="main">已会签合同</a>
			</dd>
		</dl>
		<dl>
			<dt>合同审批</dt>
			<dd>
				<a href="toApproved" target="_self">待审批合同</a>
			</dd>
			<dd>
				<a href="yshphtList.htm" target="_self">已审批合同</a>
			</dd>
		</dl>
		<dl>
			<dt>合同签订</dt>
			<dd>
				<a href="dqdhtList.htm" target="main">待签订合同</a>
			</dd>
			<dd>
				<a href="yqdhtList.htm" target="main">已签订合同</a>
			</dd>
		</dl>
	</div>
</body>
</html>
