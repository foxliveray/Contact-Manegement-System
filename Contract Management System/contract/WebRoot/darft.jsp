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
	<title>draft content</title>
<style>
#header {
    background-color:DarkTurquoise;
    color:black;
    text-align:left;
    padding:5px;
}

#section {
	height:20px;
    text-align:center;
    padding:20px;	 	 
}
#footer {
    background-color:DarkTurquoise;
    color:black;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}

</style>
<script type="text/javascript">
function bottonOK()
{
	var contactname=document.getElementById("name1").value;
	var customername=document.getElementById("customer").value;	
	var begin=document.getElementById("beginTime").value;
	var end=document.getElementById("endTime").value;
	var contactcontent=document.getElementById("content").value;
	var apos1=begin.indexOf("-");
	var apos2=begin.lastIndexOf("-");
	var apos3=end.indexOf("-");
	var apos4=end.lastIndexOf("-");
	if(contactname==""){
		alert("合同名称不能为空");
		document.getElementById("state").value="合同起草失败";
	}
	else if(customername==""){
		alert("客户名称不能为空");
		document.getElementById("state").value="合同起草失败";
	}
	else if(begin==""||apos1!=4||apos2!=7||end==""||apos3!=4||apos4!=7){
		alert("日期格式有误，请重新输入");
		document.getElementById("state").value="合同起草失败";
	}
	else if(contactcontent==""){
		alert("合同内容不能为空");
		document.getElementById("state").value="合同起草失败";	
	}
	else{
		document.getElementById("state").value="合同起草成功";	
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
                    <h1 class="page-header">起草合同</h1>
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
                                    <form role="form" action="DraftServelt" method="post">
                                        <div class="form-group">
                                            <input class="form-control" placeholder="合同名称" name="name1" id="name1">
                                            </br>
                                            <input class="form-control" placeholder="客户名称" name="customer" id="customer">
                                            </br>
                                            <input class="form-control" placeholder="开始时间" name="beginTime" id="beginTime">
                                            <input type="text" name="tishi1" size="40"style="border:0px;color:red;"value="*时间格式yyyy-mm-dd (如 2000-01-01)"onfocus=this.blur()>
                                            </br>
                                            <input class="form-control" placeholder="结束时间" name="endTime" id="endTime">
                                            <input type="text" name="tishi2" style="border:0px;color:red;"value="*时间格式yyyy-mm-dd"onfocus=this.blur()>
                                            </br>
                                            <textarea class="form-control" placeholder="合同内容" name="content" id="content" rows="3"></textarea>
                                            </br>
                                            <label>附件:</label>
                                            <input type="file">
                                           	</br>
                                            <button type="submit" class="btn btn-info" onClick="bottonOK()">submit</button>
                                            <button type="reset" class="btn btn-info">reset</button>
                                        </div>
                                    </form>
<!-- 
<div id="header">
<h1>起草合同</h1>
</div>
<div id="section">
<input type="text" id="state" style="border:0px;color:black;font-size:18px;"value=""onfocus=this.blur()>
</div>
<hr/>
<form action="DraftServelt" method="Post">
<p>合同名称：  
<input type="text"id="name1"name="name1"value="" size="40"style="font-size:14px;" />
</p>
<hr/>
<p>客户名称：  
<input type="text"id="customer"name="customer"value=""size="40" style="font-size:14px;"/>
</p>
<hr/>
<p>开始时间：
<input type="text"id="beginTime"name="beginTime"value=""size="40"style="font-size:14px;" />
<input type="text" name="tishi1" size="40"style="border:0px;color:red;"value="*时间格式yyyy-mm-dd (如 2000-01-01)"onfocus=this.blur()>
</p>
<hr/>
<p>结束时间：
<input type="text"id="endTime"name="endTime"value=""size="40"style="font-size:14px;" />
<input type="text" name="tishi2" style="border:0px;color:red;"value="*时间格式yyyy-mm-dd"onfocus=this.blur()>
</p>
<hr/>

<p>合同内容：</p>
<textarea id="content"name="content"style="width:800px;height:200px;font-size:14px;"></textarea>
<p>附件：
<input type="file" value="test" >
</p>
<hr/>
<input type="button" value="提交"onClick="bottonOK()" />
<input type="reset" value="重置" />
</form>
 -->
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