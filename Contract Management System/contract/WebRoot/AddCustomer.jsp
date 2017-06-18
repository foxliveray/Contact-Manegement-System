<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>合同管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
	<script type="text/javascript">
		function check(){
			var name = document.getElementById("customername");
			var address = document.getElementById("address");
			var telephone = document.getElementById("telephone");
			var fax = document.getElementById("fax");
			var code = document.getElementById("code");
			var bank = document.getElementById("bank");
			var account = document.getElementById("account");
			
			if(name.value == ""){
				alert("新客户姓名不能为空！");
				name.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else if(address.value == ""){
				alert("客户的地址不能为空！");
				address.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else if (telephone.value == ""){
				alert("客户的电话不能为空！");
				telephone.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else if (fax.value == ""){
				alert("客户的传真不能为空！");
				fax.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else if (code.value == ""){
				alert("客户的邮编不能为空！");
				code.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else if (bank.value == ""){
				alert("客户的银行不能为空！");
				bank.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else if (account.value == ""){
				alert("客户的银行账户不能为空！");
				account.focus();
				document.getElementById("state").value="添加新客户失败";
			}
			else{
				document.getElementById("state").value="添加新客户成功";
				document.forms[0].submit();
			}
		}
	</script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Frame1.jsp">合同管理系统</a>

            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
            	<%
					String userName = (String) session.getAttribute("userName");
				%>
            	<p class="navbar-brand">Hello,<%=userName%></p>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="Login.jsp"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
            
                        <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        
                        <li>
                            <a href="AddCustomer.jsp"><i class="fa fa-dashboard fa-fw"></i> 客户管理</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 我的合同<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="darft.jsp">起草合同</a>
                                </li>
                             
                                  <li>
                                    <a href="http://localhost:8080/contract/ContractListForUser" >定稿合同</a>
                                </li>
								
                            </ul>
                         </li>
                         <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 管理合同<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                               
                                <li>
                                    <a href="http://localhost:8080/contract/DhqhtListServlet">待会签合同</a>
                                </li>
                                 <li>
                                    <a href="http://localhost:8080/contract/DhqhtDoneListServlet">已会签合同</a>
                                </li>

                                <li>
                                    <a href="ToBeApprovedServlet?toPage=0&trPerPage=1" target="_self">待审批合同</a>
                                </li>
                                  <li>
                                    <a href="http://localhost:8080/contract/ApprovedConListServlet">已审批合同</a>
                                </li>
                                <li>
                                 	<a href="http://localhost:8080/contract/Sign">待签订合同</a>
                                </li> 
       	 						<li>
       	 							<a href="http://localhost:8080/contract/Signed">已签订合同</a>
       	 						</li>
								
                            </ul>
                         </li>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">添加新客户</h1>
                </div>
             <input type="text" id="state" style="border:0px;color:black;font-size:18px;"value=""onfocus=this.blur()>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="AddCustomer" method="post">
                                        <div class="form-group">
                                            <input class="form-control" placeholder="客户姓名" name="customername" id="customername">
                                            </br>
                                            <input class="form-control" placeholder="地址" name="address" id="address">
                                            </br>
                                            <input class="form-control" placeholder="联系电话" name="telephone" id="telephone">
                                            </br>
                                            <input class="form-control" placeholder="传真" name="fax" id="fax">
                                            </br>
                                            <input class="form-control" placeholder="邮政编码" name="code" id="code">
                                            </br>
                                            <input class="form-control" placeholder="银行" name="bank" id="bank">
                                            </br>
                                            <input class="form-control" placeholder="银行账户" name="account" id="account">
                                            </br>
                                            <button type="submit" class="btn btn-info" onClick="check()">submit</button>
                                            <button type="reset" class="btn btn-info">reset</button>
                                        </div>
                                    </form>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

</body>

</html>
