<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="service.database_operation" %>
<%@page import="model.Log" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
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

<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<% List<Log> loglist = (List<Log>)request.getAttribute("lists");%>
<%!public static final int PAGESIZE = 20;  
int pageCount;  
int curPage = 1;%>
<% 
int size=loglist.size();
pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);
String tmp = request.getParameter("curPage");  
if(tmp==null){  
    tmp="1";  
}  
curPage = Integer.parseInt(tmp);  
if(curPage>=pageCount) curPage = pageCount;  
if(curPage<=1) curPage = 1;  
int count = 0;  
List<Log> list = new ArrayList<Log>();
int j=0;
for(Log lo:loglist){
	if((j<curPage*PAGESIZE)&&(j>=(curPage-1)*PAGESIZE)){
		list.add(lo);
		System.out.println(lo.getContent());
	}
	j++;
}
 %>

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
                    <h1 class="page-header">日志管理</h1>
                </div>
<form class="form-inline" id="searchForm" name="searchForm" action="LogServlet" >
     <input type="text" class="form-control" name="searchwords" id="searchwords" value=""  style="display:inline; width:200px" >
     <button type="submit" class="btn btn-primary" style="display:inline;" onclick="searchForm.submit()" type="button" >搜索</button>
</form>
<br></br>
<form action="" method="post">
 <table id="example2" cellpadding="0" cellspacing="0" border="1" width="800">
                        <thead>
                            <tr>
                                
                                <td align="center">日志ID</td>
                                <td align="center">用户ID</td>
                                <td align="center">内容</td>
                                <td align="center">操作时间</td>                               
                          </tr>
                        </thead>
                        <tbody>             
                        <% for (Log log : list) { %> 
                        <tr>
					    	<td align="center"><%=log.getId()%></td>
                            <td align="center"><%=log.getUserId()%></td>
                            <td align="center"><%=log.getContent()%></td>
                            <td align="center"><%=log.getTime()%></td>
                        </tr>
                            <%} %> 
                        </tbody>
                    </table>
</form>
<form method="post" action=""> 
<a href = "LogServlet?curPage=1" >首页</a>  
<a href = "LogServlet?curPage=<%=curPage-1%>" >上一页</a>  
<a href = "LogServlet?curPage=<%=curPage+1%>" >下一页</a>  
<a href = "LogServlet?curPage=<%=pageCount%>" >尾页</a>  
第<%=curPage%>页/共<%=pageCount%>页  </form>
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