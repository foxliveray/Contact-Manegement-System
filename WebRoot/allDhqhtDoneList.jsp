<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.*,model.ConProModel"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
<style type="text/css">
.btn{
    width:100%;
    height:100%;
}
</style>
		
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
<!-- Use JavaScript script to open a new window display information when preview-->
		<script>
			function preview(url) {
				window.open(url,'Preview','resizable=no,toolbar=no,width=620,height=700,top=50,left=200');
			}
			
			function Check(){   
	            var x=document.getElementById("tbl");
	            var z=document.getElementById("select").value;
	            var y=x.rows.length;
	            //x.style.visibility='hidden';
	            for(var i = 1; i <y; i++){  
	            	var temp=x.rows[i].cells[0].lang;
	            	if(temp.indexOf(z)==-1){
	            		x.rows[i].style.display='none'; 
	            		}else{
	            	    x.rows[i].style.display='table-row';
	            		}
	            }
	            //x.style.display='none';  
	        }  
		</script>

<!-- 标题 -->
  <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">所有已会签合同</h1>
                </div>
	</div>

<!-- 查找栏 -->
<p >查找已会签合同:
<input type="text" id="select" name="select"/>
<input type="button" id="search" value="search" style="padding-right:30px" onclick="Check()" />
</p>

<!-- 合同列表 -->
<div class="panel-body">
<div class="table-responsive">
<table width="980" border="1" id="tbl">
	<thead>
	<tr>
   <th>合同名称</th>
    <th>起草时间</th>
     <th>操作</th>
  </tr>
  </thead>
  
          <%
                List<ConProModel> contractList = (List<ConProModel>)request.getAttribute("contractList");
                for(int i=0;  i<contractList.size(); i++){
                	ConProModel cbm = contractList.get(i);
          %>
  
<!-- 表中的行 -->
  <tr>
    <td lang="<%=cbm.getConName()%>">
    <a href="javascript:preview('ToSeeContract1Servlet?conId=<%=cbm.getConId()%>')"><%=cbm.getConName()%></a>
    </td>
    <td>
    <%=cbm.getDrafTime()%>
    </td>
    <!-- 添加按钮，点击即可会签 -->
    <td style="color:black">
    <a href="javascript:preview('NewFile?conId=<%=cbm.getConId()%>&userId=<%=cbm.getUserId()%>')">会签意见</a>
    </a>
    </td>
  </tr>
  
  <%} %>
   
  
</table>

<div style="height:px;margin:px ;">
<span id="spanFirst">第一页</span>
<span id="spanPre">上一页</span>
<span id="spanNext">下一页</span>
<span id="spanLast">最后一页</span>
第<span id="spanPageNum"></span>页/共
<span id="spanTotalPage"></span>页
</div>

<script type="text/javascript">
//关于界面翻页的function控制

var theTable = document.getElementById("tbl");
var totalPage = document.getElementById("spanTotalPage");
var pageNum = document.getElementById("spanPageNum");
var spanPre = document.getElementById("spanPre");
var spanNext = document.getElementById("spanNext");
var spanFirst = document.getElementById("spanFirst");
var spanLast = document.getElementById("spanLast");
var numberRowsInTable = theTable.rows.length;
var pageSize = 3;//每页的显示数据条数
var page = pageCount();//页数
var pageNow=1;//现在的页码 初始化为1
var currentRow=1;//现在的起始行 由于表头是第一行 初始化起始行为1
//下一页
function next() {
hideTable();
currentRow = currentRow+pageSize;
pageNow++;
maxRow = currentRow + pageSize;
if ( maxRow > numberRowsInTable )
maxRow = numberRowsInTable;
theTable.rows[0].style.display = 'table-row';
for ( var i = currentRow; i< maxRow; i++ ) {
theTable.rows[i].style.display = 'table-row';
}
if ( maxRow == numberRowsInTable ){
nextText();
lastText();
}
showPage();
preLink();
firstLink();
}
//上一页
function pre() {
hideTable();
pageNow--;
currentRow = currentRow-pageSize;
maxRow = currentRow + pageSize;
if ( currentRow > numberRowsInTable )
currentRow = numberRowsInTable;
if(pageNow!="1") {
theTable.rows[0].style.display = 'table-row';
for ( var i = currentRow; i< maxRow; i++ ) {
theTable.rows[i].style.display = 'table-row';
 }
}else first();

if ( pageNow == 1) {
preText();
firstText();
}
showPage();
nextLink();
lastLink();
}
//第一页
function first() {
hideTable();
pageNow = 1;
for ( var i = 0; i<pageSize+1; i++ ) {
theTable.rows[i].style.display = 'table-row';
}
currentRow=1;
showPage();
preText();
nextLink();
lastLink();
}
//最后一页
function last() {
hideTable();
pageNow = pageCount();
currentRow = pageSize * (page - 1);
theTable.rows[0].style.display = 'table-row';
for ( var i = currentRow+1; i<numberRowsInTable; i++ ) {
theTable.rows[i].style.display = 'table-row';
}
showPage();
preLink();
nextText();
firstLink();
}

//隐藏不必要的部分表格
function hideTable() {
for ( var i = 0; i<numberRowsInTable; i++ ) {
theTable.rows[i].style.display = 'none';
}
}

//修改并显示现在的页码
function showPage() {
pageNum.innerHTML = pageNow;
}
//总共页数
function pageCount() {
var count = 0;
if ( numberRowsInTable%pageSize != 0) count = 1; 
return parseInt(numberRowsInTable/pageSize) + count;
}
//显示链接
function preLink() { spanPre.innerHTML = "<a href='javascript:pre();'>上一页</a>"; }
function preText() { spanPre.innerHTML = "上一页"; }
function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>下一页</a>"; }
function nextText() { spanNext.innerHTML = "下一页"; }
function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>第一页</a>"; }
function firstText() { spanFirst.innerHTML = "第一页"; }
function lastLink() { spanLast.innerHTML = "<a href='javascript:last();'>最后一页</a>"; }
function lastText() { spanLast.innerHTML = "最后一页"; }
//开始隐藏表格
function hide() {
for ( var i = pageSize+1; i<numberRowsInTable; i++ ) {
theTable.rows[i].style.display = 'none';
}
totalPage.innerHTML = pageCount();
pageNum.innerHTML = 1;
nextLink();
lastLink();
}
hide();//初始化
</script>
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