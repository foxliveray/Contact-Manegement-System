<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*" 
    pageEncoding="UTF-8"%>
<%@page import="model.ConBusiModel;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<head>
<style type="text/css">

.btn{
    width:100%;
    height:100%;
}

</style>
</head>

<!-- 标题 -->
  <h1 style="font-family:arial;color:white;font-size:60px;background-color:black;">待会签合同</h1>

<!-- 查找栏 -->
<p >查找待会签合同:
<input type="text"/>
<input type="button" id="search" value="search" style="padding-right:30px;" />
</p>

<!-- 合同列表 -->
<table width="980" border="1" id="tbl">
 <tr bgcolor="gray">
   <td align="center" width="580">合同名称</td>
    <td align="center" width="200">起草时间</td>
     <td align="center" width="200">操作</td>
  </tr>
  
  <%
	List<ConBusiModel> contractList = (List<ConBusiModel>)request.getAttribute("contractList");  
    for (ConBusiModel cbm : contractList) {
  %>
  
<!-- 表中的行 -->
  <tr>
    <td>
    <%=cbm.getConName()%>
    </td>
    <td><%=cbm.getDrafTime()%></td>
    <!-- 添加按钮，点击即可会签 -->
    <td><a href="toAddHQOpinion?conId=<%=cbm.getConId()%>"></td>
  </tr>

   <%
        }  
   %>
  
</table>

<!-- 最下栏的翻页按钮 -->
<p style = "text-align:right">
<input type="button" id="first" value="first" style="padding-right:30px;" />
<input type="button" id="<pre" value="<pre" style="padding-right:30px;" />
<input type="button" id="next>" value="next>" style="padding-right:30px;" />
<input type="button" id="last" value="last" style="padding-right:30px;" />
<!-- 标注第几页，共多少项 -->
<input type="text" readOnly value="第1页共1条" />
</p>
</body>
</html>