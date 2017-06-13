<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.*,model.ConProModel"
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
				window.open(url,'Preview','resizable=no,toolbar=no,width=620,height=500,top=50,left=200');
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
  <h1 style="font-family:arial;color:white;font-size:60px;background-color:black;">待分配合同</h1>

<!-- 查找栏 -->
<p >查找待分配合同:
<input type="text" id="select" name="select"/>
<input type="button" id="search" value="search" style="padding-right:30px" onclick="Check()" />
</p>

<!-- 合同列表 -->
<table width="980" border="1" id="tbl">
 <tr bgcolor="gray">
   <td align="center" width="400">合同名称</td>
   <td align="center" width="100">合同编号</td>
    <td align="center" width="100">起草用户</td>
    <td align="center" width="180">起草时间</td>
     <td align="center" width="200">分配合同</td>
  </tr>
  
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
    <%=cbm.getConId()%>
    </td>
    <td>
    <%=cbm.getUserId()%>
    </td>
    <td>
    <%=cbm.getDrafTime()%>
    </td>
    <!-- 添加标签，显示合同状态 -->
    <td>
    <a href="ToAssignOperServlet?conId=<%=cbm.getConId()%>">分配
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
if(pageCount()==1){
	nextText();
}else{
	nextLink();
}
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
if(pageCount()==1){
	preText();
}else{
	preLink();
}
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
if ( (numberRowsInTable-1)%pageSize != 0) count = 1; 
return parseInt((numberRowsInTable-1)/pageSize) + count;
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

</body>
</html>