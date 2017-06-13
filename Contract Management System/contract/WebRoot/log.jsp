<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="service.database_operation" %>
<%@page import="model.Log" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Log</title>
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
	}
	j++;
}
 %>

<body>
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
</body>

</html>