<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.*,model.ConBusiModel"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.btn{
    width:100%;
    height:100%;
}
</style>
		
</head>

<body>

<!-- Use JavaScript script to open a new window display information when preview-->
		<script>
			function preview(url) {
				window.open(url,'Preview','resizable=no,toolbar=no,width=700,height=800,top=50,left=200');
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
  <h1 style="font-family:arial;color:white;font-size:60px;background-color:black;">待会签合同</h1>

<!-- 查找栏 -->
<p >查找待会签合同:
<input type="text" id="select" name="select"/>
<input type="button" id="search" value="search" style="padding-right:30px" onclick="Check()" />
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
                for(int i=0;  i<contractList.size(); i++){
                ConBusiModel cbm = contractList.get(i);
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
    <a href="ToAddHQOpinionServlet?conId=<%=cbm.getConId()%>">会签
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

</body>
</html>