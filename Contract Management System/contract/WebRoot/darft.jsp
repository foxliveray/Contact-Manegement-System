<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>draft content</title>
<style>
#header {
    background-color:DarkTurquoise;
    color:black;
    text-align:left;
    padding:5px;
}

#section {
	height:20px;
    text-align:center;
    padding:20px;	 	 
}
#footer {
    background-color:DarkTurquoise;
    color:black;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}

</style>
<script type="text/javascript">
function bottonOK()
{
	var contactname=document.getElementById("name1").value;
	var customername=document.getElementById("customer").value;	
	var begin=document.getElementById("beginTime").value;
	var end=document.getElementById("endTime").value;
	var contactcontent=document.getElementById("content").value;
	var apos1=begin.indexOf("-");
	var apos2=begin.lastIndexOf("-");
	var apos3=end.indexOf("-");
	var apos4=end.lastIndexOf("-");
	if(contactname==""){
		alert("合同名称不能为空");
		document.getElementById("state").value="合同起草失败";
	}
	else if(customername==""){
		alert("客户名称不能为空");
		document.getElementById("state").value="合同起草失败";
	}
	else if(begin==""||apos1!=4||apos2!=7||end==""||apos3!=4||apos4!=7){
		alert("日期格式有误，请重新输入");
		document.getElementById("state").value="合同起草失败";
	}
	else if(contactcontent==""){
		alert("合同内容不能为空");
		document.getElementById("state").value="合同起草失败";	
	}
	else{
		document.getElementById("state").value="合同起草成功";	
		document.forms[0].submit();
	}
}

</script>
</head>
<body>
<div id="header">
<h1>起草合同</h1>
</div>
<div id="section">
<input type="text" id="state" style="border:0px;color:black;font-size:18px;"value=""onfocus=this.blur()>
</div>
<hr/>
<form action="DraftServelt" method="Post">
<p>合同名称：  
<input type="text"id="name1"name="name1"value="" size="40"style="font-size:14px;" />
</p>
<hr/>
<p>客户名称：  
<input type="text"id="customer"name="customer"value=""size="40" style="font-size:14px;"/>
</p>
<hr/>
<p>开始时间：
<input type="text"id="beginTime"name="beginTime"value=""size="40"style="font-size:14px;" />
<input type="text" name="tishi1" size="40"style="border:0px;color:red;"value="*时间格式yyyy-mm-dd (如 2000-01-01)"onfocus=this.blur()>
</p>
<hr/>
<p>结束时间：
<input type="text"id="endTime"name="endTime"value=""size="40"style="font-size:14px;" />
<input type="text" name="tishi2" style="border:0px;color:red;"value="*时间格式yyyy-mm-dd"onfocus=this.blur()>
</p>
<hr/>

<p>合同内容：</p>
<textarea id="content"name="content"style="width:800px;height:200px;font-size:14px;"></textarea>
<p>附件：
<input type="file" value="test" >
</p>
<hr/>
<input type="button" value="提交"onClick="bottonOK()" />
<input type="reset" value="重置" />
</form>
</body>
</html>