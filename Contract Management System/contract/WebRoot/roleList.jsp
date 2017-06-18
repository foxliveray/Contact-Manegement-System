<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Role" %>
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
    
		<script>
			function showRoleDescription(roleDescription){
				alert(roleDescription);
			}
			
			function deleteCheck(url){
				var result=confirm("确认删除？");
				if(result){
					window.location.assign(url);
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
                <a class="navbar-brand" href="Frame2.jsp">合同管理系统</a>

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
                            <a href="http://localhost:8080/contract/LogServlet"><i class="fa fa-dashboard fa-fw"></i> 日志管理</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 权限管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                    
                                <li><a href="ToPermissionListServlet">权限配置</a></li>
								<li><a href="ToUserListServlet">用户管理</a></li>
								<li><a href="ToRoleListServlet">角色管理</a></li>
                            </ul>
                            </li>
                            
                          <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 合同管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="http://localhost:8080/contract/DstributeContract">分配合同</a>
                                </li>
                                <li>
                                	<a href="http://localhost:8080/contract/ConSateQuery">合同管理</a>
                                </li>
                                <li>
                                	<a href="http://localhost:8080/contract/AllDhqhtDoneListServlet">所有已会签合同</a>
                                </li>  

                            </ul>
                            </li>
                            <!-- /.nav-second-level -->
                       
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">用户列表</h1>
                </div>
            </div>
	
		<br />

		<div style="text-align:right;width:480px;">
		   <a href="addRole.jsp">
				<img src="images/add.png"  alt="Add Role" />
				添加角色
			</a>
		</div>

		<div class="panel-body">
		<div class="table-responsive">
		  <table width="500" border="1">
			<tr>
				<th style="width:100px;">
					角色名
				</th>
				<th>
					描述
				</th>
				<th style="width:100px;">
					操作
				</th>
			</tr>
			<%
				List<Role> roleList=(List<Role>)request.getAttribute("roleList");
				for(int i=0;i<roleList.size();++i){
					Role oneRole=roleList.get(i);
			%>
			<tr>
				<td class="tdname">
					<%=oneRole.getName() %>
				</td>
				<td class="tdname">
					<a href="javascript:showRoleDescription('<%=oneRole.getDescription() %>')">
					    <%if (oneRole.getDescription().length()<=20){ %>
							<%=oneRole.getDescription() %>
						<%} else {%>
							<%=oneRole.getDescription().substring(0,20).concat("...") %>
						<%} %>
					</a>
					
				</td>
				<td>
					<a href="ToEditRoleServlet?selectRoleId=<%=oneRole.getId() %>">
						<img src="images/icon-edit.png"  alt="Edit" />
						修改
					</a>
						｜
					<a href="javascript:deleteCheck('DeleteRoleServlet?selectRoleId=<%=oneRole.getId()%>')">
						<img src="images/delete.png"  alt="Delete" />
						删除
					</a>
				</td>
			</tr>
			<%} %>
			<tr>
				<td colspan="5"> </td>
			</tr>
		  </table>
		</div>
		</div>
		</div>
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
