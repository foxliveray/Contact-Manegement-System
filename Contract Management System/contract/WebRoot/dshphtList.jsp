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
				window.open(url,'Preview','toolbar=no,scrollbars=yes,width=720,height=560,top=50,left=100');
			}
			
			function pageTurning(toPage,trPerPage,maxPage){
				if(toPage>=0&&toPage<maxPage){
					window.location.assign("toApproved?toPage="+toPage+"&trPerPage="+trPerPage);	
				}
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
				<input value="请输入相关搜索条件..." />
				&nbsp;&nbsp;
				<input type="submit" value="search" class="search-submit"/> <br />
			</form>
		</div>
		
		<div class="list">
		  <table id="table1">
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
			    int toPage=(int)request.getAttribute("toPage");
			    int trPerPage=(int)request.getAttribute("trPerPage");
				List<ConBusiModel> contractList = (List<ConBusiModel>)request.getAttribute("contractList");  
				int maxPage;
				if((contractList.size()%trPerPage)==0)
					maxPage=contractList.size()/trPerPage;
				else
					maxPage=contractList.size()/trPerPage+1;
		        for (int trNo=(toPage*trPerPage);trNo<(trPerPage*(toPage+1));trNo++) {
		        	ConBusiModel cbm=contractList.get(trNo);
       	 	%>
			<tr>
				<td class="tdname">
					<a href="javascript:preview('showConDetails?conId=<%=cbm.getConId()%>')"><%=cbm.getConName()%></a>
				</td>
				<td>
					<%=cbm.getDrafTime()%>
				</td>
				<td>
					<a href="approving?conId=<%=cbm.getConId()%>">
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
			<a href="javascript:pageTurning(0,<%=trPerPage%>,<%=maxPage %>)"><img src="images/page/first.png"  alt="" /></a> &nbsp;
			<a href="javascript:pageTurning(<%=toPage-1%>,<%=trPerPage%>,<%=maxPage %>)"><img src="images/page/pre.png"  alt="" /></a>&nbsp;
			<a href="javascript:pageTurning(<%=toPage+1%>,<%=trPerPage%>,<%=maxPage %>)"><img src="images/page/next.png"  alt="" /></a>&nbsp;
			<a href="javascript:pageTurning(<%=maxPage-1 %>,<%=trPerPage%>,<%=maxPage %>)"><img src="images/page/last.png"  alt="" /></a>&nbsp;
			<span class="pageinfo">
				总计&nbsp;<strong><%=maxPage %></strong>&nbsp;页&nbsp;<strong><%=contractList.size() %></strong>&nbsp;条记录
			</span>	
		</div>
	</body>
</html>
