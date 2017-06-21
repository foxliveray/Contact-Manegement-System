<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Customer" %>
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

	<script type="text/javascript">
	function DeleteClient(id){
		if(confirm("删除后不会删除其他合同信息，确定要删除此客户吗？")){
			var deleteRequest=null;
			if(window.XMLHttpRequest){
				deleteRequest=new XMLHttpRequest();
			}else if(window.ActiveXObject){
				deleteRequest=new ActiveXObject("Microsoft.XMLHttp");
			}
			
			if(deleteRequest){
				deleteRequest.open("get","CustomerInfoManage?type=delete&id="+id,true);
				deleteRequest.onreadystatechange=function(){
					if(deleteRequest.readyState==4&&deleteRequest.status==200){
						alert(deleteRequest.responseText);
						document.location = "CustomerInfoManage";
					}
				};
				deleteRequest.send();
			}
		}
	}
	
	function  ModifyClient(){
		if(document.getElementById("NewTel").value==""||document.getElementById("NewClientName").value==""
				||document.getElementById("NewFax").value==""){
			alert("输入格式错误！！");				
			return false;
		}else{
			
			 var modify=null;
	          if(window.XMLHttpRequest){
	        	 modify=new XMLHttpRequest();
	  		  }else if(window.ActiveXObject){
	  			 modify=new ActiveXObject("Microsoft.XMLHttp");
	  	      }
	  			
	  			if(modify){
	  				var pre="CustomerInfoManage?type=modify&id="+document.getElementById("MID").value+
	  						"&NewClientName="+document.getElementById("NewClientName").value+
	  						"&NewAddr="+document.getElementById("NewAddr").value+
	  						"&NewTel="+document.getElementById("NewTel").value+
	  						"&NewFax="+document.getElementById("NewFax").value+
	  						"&NewPostCode="+document.getElementById("NewPostCode").value+
	  						"&NewBankName="+document.getElementById("NewBankName").value
	  						+"&NewBankAccount="+document.getElementById("NewBankAccount").value;
	  				
	  				modify.open("get",pre,true);
	  				modify.send();
	  				modify.onreadystatechange=function(){
						if(modify.readyState==4&&modify.status==200){
							//判断ajax返回结果
							alert(modify.responseText);
							document.location = "CustomerInfoManage";
						}else{
							alert("ReadyStatus: "+modify.readyState+"  Status: "+modify.status);
						}
	  				};
	  				
	  			}
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
                    <h1 class="page-header">客户管理</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            	客户信息列表
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>客户ID</th>
                                        <th>客户姓名</th>
                                        <th>地址</th>
                                        <th>联系电话</th>
                                        <th>邮编</th>
                                        <th>银行</th>
                                        <th>银行账户</th>
                                        <th>修改用户信息</th>
                                        <th>删除用户</th>
                                    </tr>
                                </thead>
                                <tbody>
                        			<%
                                    	ArrayList<Customer> cusList = new ArrayList<Customer>();
                                        cusList = (ArrayList<Customer>)request.getAttribute("cusList");
                                        for(int i = 0;i<cusList.size();i++){
                                        	out.println("<tr>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getId()+"</td>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getName()+"</td>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getAddress()+"</td>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getTel()+"</td>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getCode()+"</td>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getBank()+"</td>");
                                            out.println("<td>"+((Customer)cusList.get(i)).getAccout()+"</td>");%>
                                            <td><a onclick="document.getElementById('MID').value='<%=cusList.get(i).getId()%>';
                          			  						document.getElementById('NewClientName').value='<%=cusList.get(i).getName()%>';
                          			  						document.getElementById('NewAddr').value='<%=cusList.get(i).getAddress()%>';
                          			  						document.getElementById('NewTel').value='<%=cusList.get(i).getTel()%>';
                          			  						document.getElementById('NewFax').value='<%=cusList.get(i).getFax()%>';
                          			  						document.getElementById('NewPostCode').value='<%=cusList.get(i).getCode()%>';
                          			  						document.getElementById('NewBankName').value='<%=cusList.get(i).getBank()%>';
                          			  						document.getElementById('NewBankAccount').value='<%=cusList.get(i).getAccout()%>';
                          			 						" data-toggle="modal" data-target="#myModal2">修改</a>&nbsp;&nbsp;</td>
                                            <td><a onclick="DeleteClient('<%=((Customer)cusList.get(i)).getId()%>')">删除</a></td>
                                            
                                            
                                            <%
                                            out.println("</tr>");
                                        }%>
                                    
                                </tbody>
                            </table>
                            <!-- Modal2 -->
      <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">                
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改客户信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                	<input  id="MID" name="MID" type="text" style="display:none;">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">客户名称：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input id="NewClientName" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">地址：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input id="NewAddr" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电话：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input id="NewTel" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">传真：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input  id="NewFax" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮编：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input  id="NewPostCode" type="text" class="form-control" >
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">银行名称：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input id="NewBankName" type="text" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">银行账户：</label>
                        <div class="col-xs-3 col-sm-6">
                          <input id="NewBankAccount" type="text" class="form-control" >
                        </div>
                    </div>                                                                                
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info" onclick="return ModifyClient()">修改</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>                   
                    </div>
                   </form>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div>
         </div><!-- /.modal -->   
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>

</body>

</html>
