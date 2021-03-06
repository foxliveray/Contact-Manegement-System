<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

    <%@page import="service.database_operation" %>
    <%@page import="model.ConProcess" %>
    <%@page import="java.util.*" %>
    <%@page import="model.Contract" %>
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
                         </li>                        <li>
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

<% List<Contract> contractList = (List<Contract>)request.getAttribute("lists");
List<ConProcess> processList = (List<ConProcess>)request.getAttribute("conlist");
%>
<%!public static final int PAGESIZE = 20;  
int pageCount;  
int curPage = 1;
database_operation d_o = new database_operation();%>
<% 
int size=contractList.size();
pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);
String tmp = request.getParameter("curPage");  
if(tmp==null){  
    tmp="1";  
}  
curPage = Integer.parseInt(tmp);  
if(curPage>=pageCount) curPage = pageCount;  
if(curPage<=1) curPage = 1;  
int count = 0;  
List<Contract> list = new ArrayList<Contract>();
List<ConProcess> cplist = new ArrayList<ConProcess>();
int j=0,k=0;
for(Contract lo:contractList){
	if((j<curPage*PAGESIZE)&&(j>=(curPage-1)*PAGESIZE)){
		list.add(lo);
		System.out.println(lo.getName()+" "+lo.getId());
	}
	j++;
}
for(ConProcess cp:processList){
	if((j<curPage*PAGESIZE)&&(j>=(curPage-1)*PAGESIZE)){
		cplist.add(cp);
		System.out.println(cp.getId()+" "+cp.getConId());
	}
	k++;
}
 %>
 <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">已签订合同</h1>
                </div>
<form class="form-inline" id="searchForm" name="searchForm" action="Signed">
     <input type="hidden" value="1" name="flag" id="flag"/>
     <input type="text" class="form-control" id="searchwords" name="searchwords" value=""  style="display:inline; width:200px" >
     <button type="submit" class="btn btn-primary" style="display:inline;" onclick="searchForm.submit()" type="button" >搜索</button>
</form>
<br></br>
<form action="" method="post">
 <table id="example2" cellpadding="0" cellspacing="0" border="1" width="800">
                        <thead>
                            <tr>
                                
                                <td align="center">名称</td>
                                <td align="center">客户</td>
                                <td align="center">开始时间</td>
                                <td align="center">结束时间</td>
                                <td align="center">操作</td>
                                <td align="center">状态</td>
                          </tr>
                        </thead>
                        <tbody> 
                        <%int o=0; %>            
                        <% for (Contract contract : list) { %> 
                        <tr>
					    	<td align="center"><%=contract.getName()%></td>
                            <td align="center"><%=contract.getCustomer()%></td>
                            <td align="center"><%=contract.getBeginTime()%></td>
                            <td align="center"><%=contract.getEndTime()%></td>
                            <%
                            o++;
                            int p=0;
                            for(ConProcess c:cplist){
                            	p++;                       
                            	 if(p==o)
                            	{%>
                            		<td align="center"><a href="javascript:show_detail('<%=c.getContent()%>','<%=c.getTime() %>')" id="">详细信息</a></td>
                            	<%
                            	break;
                            	}
                            }
                            %>
                            <%if(contract.getDel()==0){%>
                              <td align="center">已通过签订</td>
                            <%}else {%>
                              <td align="center">未通过签订</td>
                              <%} %>
                        </tr>
                            <%} %> 
                        </tbody>
                    </table>
</form>
<form method="post" action=""> 
<a href = "Signed?curPage=1" >首页</a>  
<a href = "Signed?curPage=<%=curPage-1%>" >上一页</a>  
<a href = "Signed?curPage=<%=curPage+1%>" >下一页</a>  
<a href = "Signed?curPage=<%=pageCount%>" >尾页</a>  
第<%=curPage%>页/共<%=pageCount%>页  </form>

<SCRIPT type="text/javascript">  
function show_detail(content,time) {
	//document.fileForm.remark.focus();
	//ConProcess cc = new ConProcess();
	//cc=d_o.select_detail_by_conid(id);
    $("#time").val(time);
    $("#remark").val(content); 
    $('#myModal').modal({
        keyboard: true
    })
}
</SCRIPT>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">           
       <div class="modal-content">
       <div class="modal-header" >
       <h3>签订信息</h3><h5 id="name" name="name" value=""></h5>
       </div>
        <div class="modal-body">
          <form class="form-horizontal" name="fileForm" role="form" method="post" action="" >
                <div style="margin-left:70px;margin-right:70px;">
                <div class="form-group">
                      <label for="remark" class="col-sm-2 control-label">意见:</label>
                        <div class="col-sm-10">
                            <textarea  id="remark" name="remark" rows="5" cols="10" style="width:400px;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;" value=""></textarea>
                        </div>     
                      <lable for="time" class="col-sm-3 contrl-label">操作时间：</lable>
                        <div class="col-sm-10">                            
                            <textarea  id="time" name="time" value="" rows="2" cols="10" style="width:400px;BORDER-BOTTOM: 0px solid; BORDER-LEFT: 0px solid; BORDER-RIGHT: 0px solid; BORDER-TOP: 0px solid;"></textarea>
                        </div>
                </div>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"> 关闭 </button>
          </div>
       </div>
   </div>
</div>
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