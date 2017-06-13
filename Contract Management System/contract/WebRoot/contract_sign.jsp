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
                                <td align="center">开始时间</td>
                                <td align="center">结束时间</td>
                                <td align="center">操作</td>
                          </tr>
                        </thead>
                        <tbody>             
                        <% for (Contract contract : list) { %> 
                        <tr>
					    	<td align="center"><%=contract.getName()%></td>
                            <td align="center"><%=contract.getCustomer()%></td>
                            <td align="center"><%=contract.getBeginTime()%></td>
                            <td align="center"><%=contract.getEndTime()%></td>
                            <td align="center"><a href="javascript:sign('<%=contract.getName()%>','<%=contract.getId()%>')"  id="">签订</a></td>
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
<SCRIPT type="text/javascript">  
function sign(name,id) {
	document.fileForm.remark.focus();
    $("#con_id").val(id);
    $("#name").val(name); 
    $('#myModal').modal({
        keyboard: true
    })
}
</SCRIPT>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">           
       <div class="modal-content">
       <div class="modal-header" >
       <h3>签订</h3><h5 id="name" name="name" value=""></h5>
       </div>
        <div class="modal-body">
          <form class="form-horizontal" name="fileForm" role="form" method="post" action="Sign" >
                <div style="margin-left:60px;margin-right:60px;">
                <input type="hidden" name="con_id" id="con_id" value="" />
                <input type="hidden" value="3" name="flag" id="flag"/></div>
                 <div class="form-group">
                      <label for="remark" class="col-sm-2 control-label">附入信息</label>
                        <div class="col-sm-10">
                            <textarea  id="remark" name="remark" rows="5" cols="10" style="width:400px" ></textarea>
                            </div>
                        </div>     
                        <lable for="if_ok" class="col-sm-2 contrl-label">是否通过</lable>
                        <div class="col-sm-10">
                        <select id="if_ok" name="if_ok">
                             <option value ="0" selected >是</option>
                             <option value ="1">否</option>                    
                             </select>
                        </div>                             
                        </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"data-dismiss="modal"> 关闭 </button>
                <button type="button" class="btn btn-primary" name="submit" value="0" onclick="fileForm.submit()">确认</button>
          </div>
       </div>
   </div>
</div>
</body>
</html>