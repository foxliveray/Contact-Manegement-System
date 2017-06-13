<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

    <%@page import="service.database_operation" %>
    <%@page import="model.ConProcess" %>
    <%@page import="java.util.List" %>
    <%@page import="model.Contract" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同会签</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<% List<Contract> contractList = (List<Contract>)request.getAttribute("lists");%>
<%!public static final int PAGESIZE = 20;  
int pageCount;  
int curPage = 1;%>
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
int j=0;
for(Contract lo:contractList){
	if((j<curPage*PAGESIZE)&&(j>=(curPage-1)*PAGESIZE)){
		list.add(lo);
	}
	j++;
}
 %>
<form class="form-inline" id="searchForm" name="searchForm" action="Sign">
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
                                <td align="center">意见</td>
                                <td align="center">开始时间</td>
                                <td align="center">结束时间</td>
                                <td align="center">状态</td>
                          </tr>
                        </thead>
                        <tbody>             
                        <% for (Contract contract : list) { %> 
                        <tr>
					    	<td align="center"><%=contract.getName()%></td>
                            <td align="center"><%=contract.getCustomer()%></td>
                            <td align="center"><a href="getContent()">查看</a></td>
                            <td align="center"><%=contract.getBeginTime()%></td>
                            <td align="center"><%=contract.getEndTime()%></td>
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
<a href = "Sign?curPage=1" >首页</a>  
<a href = "Sign?curPage=<%=curPage-1%>" >上一页</a>  
<a href = "Sign?curPage=<%=curPage+1%>" >下一页</a>  
<a href = "Sign?curPage=<%=pageCount%>" >尾页</a>  
第<%=curPage%>页/共<%=pageCount%>页  </form>

<script>
function getContent(content){
	alert("hh");
}
</script>
</body>
</html>