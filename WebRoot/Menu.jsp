<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style>
<!--
.nav ul{
width:980px;/*设置元素宽度为980px*/
border:1px solid #000;/*设置一个颜色为#000，宽度为1px的边框*/
margin:0px auto 0px auto;/*也可以简写为margin:0 auto*/
}
.nav ul li{
float:left;/*让li元素左浮动*/
}
.nav ul li a{
width:80px;/*设置元素宽为80px*/
height:28px;/*设置高度为28px*/
line-height:28px;/*设置行距为28px，让文字在每行的中间位置*/
background:blue;/*设置元素的背景为红色*/
color:#FFF;/*文字颜色是白色*/
margin:5px 10px;
font-size:12px;/*用12号字*/
display:block;/*这个比较关键，因为a本身就是联级元素，本身不具有宽高，用这个把它变成块级元素，这样前面设置的宽和高就能起作用了*/
text-align:center;/*让文本居中*/
text-decoration:none; /*去掉下划线*/
}
.nav ul li a:hover{ /*这个大概的意思就是当鼠标放到这个a元素的上面时，这个a元素的样式就按下面的代码执行*/
width:78px;
height:26px;
line-height:28px;
border:1px solid blue;
color:blue;
background:#FFF;
}
-->
</style>
</head>
<body>
<div class="nav"> 
    <ul> 
        <li><a href="">首页</a></li> 
        <li><a href="http://localhost:8080/contract/darft.jsp">起草合同</a></li> 
		<li><a href="http://localhost:8080/contract/DstributeContract">待分配合同</a></li> 
        <li><a href="http://localhost:8080/contract/DhqhtListServlet">待会签合同</a></li> 
        <li><a href="http://localhost:8080/contract/DhqhtDoneListServlet">已会签合同</a></li>  
        <li><a href="http://localhost:8080/contract/AllDhqhtDoneListServlet">所有已会签合同</a></li>  
        
        <li><a href="ToBeApprovedServlet?toPage=0&trPerPage=1" target="_self">待审批合同</a></li> 
        <li><a href="http://localhost:8080/contract/DshphtList">已审批合同</a></li> 
        <li><a href="http://localhost:8080/contract/Sign">待签订合同</a></li> 
        <li><a href="http://localhost:8080/contract/Signed">已签订合同</a></li>
        <li><a href="http://localhost:8080/contract/LogServlet">日志管理</a></li>
        <li><a href="http://localhost:8080/contract/ConSateQuery">合同管理</a></li>
        <li><a href="">权限分配</a></li>
        <li><a href="http://www.divcss5.com/cssrumen/">客户管理</a></li>
        <li><a href="http://localhost:8080/contract/ToUserListServlet">用户管理</a></li>
         
</ul> 
</div> 
</body>
</html>