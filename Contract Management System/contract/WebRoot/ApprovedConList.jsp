<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.ConBusiModel"%>
<%@page import="model.ConProcess"%>
<%@page import="java.util.*"%>
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

		<!-- Use JavaScript script to open a new window display information when preview-->
		<script>
			function preview(url) {
				window.open(url,'Preview','toolbar=no,scrollbars=yes,width=720,height=560,top=50,left=100');
			}
			function showSHPOpinion(shpOpinion){
				alert(shpOpinion);
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
                            <a href="#"><i class="fa fa-dashboard fa-fw"></i> 客户管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="AddCustomer.jsp">添加客户</a>
                                </li>
                             
                                  <li>
                                    <a href="http://localhost:8080/contract/ToCustomerListServlet" >客户信息管理</a>
                                </li>
								
                            </ul>
                         </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 我的合同<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="http://localhost:8080/contract/ToDraftServlet">起草合同</a>
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
                    <h1 class="page-header">已审批合同</h1>
                </div>
	</div>
		
		<div>
			<form>
				在已审批合同中搜索：
				<input value="请输入相关搜索条件..." />
				&nbsp;&nbsp;
				<input type="submit" value="Search" class="search-submit"/> <br />
			</form>
		</div>
		
		<div class="panel-body">
		<div class="table-responsive">
		  <table width="500" border="1" id="tbl">
			<tr>
				<th>
					合同名称
				</th>
				<th>
					审批意见
				</th>
				<th class="th1">
					起草时间
				</th>
			</tr>
			<%
				List<ConBusiModel> contractList = (List<ConBusiModel>)request.getAttribute("contractList");  
				List<ConProcess> shpOpinionList=(List<ConProcess>)request.getAttribute("shpOpinionList");
		        for (int i=0;i<contractList.size();i++) {
		        	ConBusiModel cbm=contractList.get(i);
		        	ConProcess cp=shpOpinionList.get(i);
       	 	%>
			<tr>
				<td class="tdname">
					<a href="javascript:preview('ToSeeContract1Servlet?conId=<%=cbm.getConId()%>')"><%=cbm.getConName()%></a>
				</td>
				<td>
					<a href="javascript:showSHPOpinion('<%=cp.getContent() %>')">
					<%=cp.getContent() %>
					</a>
				</td>
				<td>
					<%=cbm.getDrafTime()%>
				</td>
			</tr>
			<%} %>
			
			<tr>
				<td colspan="2"> </td>
			</tr>
		  </table>
		</div>

		<script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
	</body>
</html>
