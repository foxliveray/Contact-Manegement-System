<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.ConBusiModel"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<title>待审批合同列表</title>
		<!-- Use JavaScript script to open a new window display information when preview-->
		<script>
			function preview(url) {
				window.open(url,'Preview','toolbar=no,scrollbars=yes,width=720,height=800,top=50,left=100');
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
	</head>

	<body>
		<div class="mtitle">
			待审批合同
		</div>
		
		<div class="search">
			<form>
				在待审批合同中搜索：
				<input type="text" id="select" value="请输入相关搜索条件..." />
				&nbsp;&nbsp;
				<input type="submit" value="search" class="search-submit"/> <br />
			</form>
		</div>
		
		<div class="list">
		  <table id="tbl">
			<tr>
				<th>
					合同名称
				</th>
				<th class="th1">
					起草时间
				</th>  
				<th class="th1">
					操作
				</th>
			</tr>
			<%
				List<ConBusiModel> contractList = (List<ConBusiModel>)request.getAttribute("contractList");  
		        for (ConBusiModel cbm : contractList) {
       	 	%>
			<tr>
				<td class="tdname" lang="<%=cbm.getConName()%>">
					<a href="javascript:preview('ToSeeContract2Servlet?conId=<%=cbm.getConId()%>')"><%=cbm.getConName()%></a>
				</td>
				<td>
					<%=cbm.getDrafTime()%>
				</td>
				<td>
					<a href="ToAddApprovedOpinionServlet?conId=<%=cbm.getConId()%>">
						<img src="images/icon-edit.png"  alt="Approve" />
						审批
					</a>
				</td>
			</tr>
			<%} %>
			
			<tr>
				<td colspan="3"> </td>
			</tr>
		  </table>
		</div>

		<div align="right" class="pagelist">					
			<a href="#"><img src="images/page/first.png"  alt="" /></a> &nbsp;
			<a href="#"><img src="images/page/pre.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/next.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/last.png"  alt="" /></a>&nbsp;
					
			<span class="pageinfo">
				总计&nbsp;<strong>2</strong>&nbsp;页&nbsp;<strong>13</strong>&nbsp;条记录
			</span>	
		</div>
	</body>
</html>
