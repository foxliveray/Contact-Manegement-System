<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Role"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>修改角色</title>
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
			
		<script>
			function check(){
				var name=document.getElementById('roleName');
				if(name.value==""){
					alert("角色名不能为空！");
					name.focus();
					return false;
				}
			}
		</script>
	</head>

	<body>
		<%
			Role editRole=(Role)request.getAttribute("editRole"); 
		%>
		<div class="mtitle">
			修改角色
		</div>

		<br />
		<form action="EditRoleServlet" method="post">
			<input type="hidden" name="roleId" id="roleId" value=<%=editRole.getId() %>>
			<table class="update" style="width:700px;">
				<tr height="28">
					<td width="100">角色名：</td>
					<td><input type="text" name="roleName" id="roleName" value="<%=editRole.getName() %>"/></td>
				</tr>
				<tr height="28">
					<td>描述：</td>
					<td>
						<textarea rows="4" cols="40" name="description" id="description" style="width:400px;height:100px;"><%=editRole.getDescription() %></textarea>
					</td>
				</tr>
				<tr>
					<td>权限配置：</td>	
					<td>
						<div>
							合同管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("001")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="001" checked="checked"/>起草合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="001" />起草合同
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("002")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="002" checked="checked"/>定稿合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="002"/>定稿合同
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("003")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="003" checked="checked"/>查询合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="003"/>查询合同
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("004")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="004" checked="checked"/>删除合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="004"/>删除合同
							<%} %>
							<br /><br />
							流程管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("005")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="005" checked="checked"/>会签合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="005"/>会签合同
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("006")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="006" checked="checked"/>审批合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="006"/>审批合同
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("007")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="007" checked="checked"/>签订合同
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="007"/>签订合同
							<%} %>
							<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("008")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="008" checked="checked"/>分配会签
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="008"/>分配会签
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("009")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="009" checked="checked"/>分配审批
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="009"/>分配审批
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("010")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="010" checked="checked"/>分配签订
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="010"/>分配签订
							<%} %>
							<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("011")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="011" checked="checked"/>流程查询
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="011"/>流程查询
							<%} %>
							<br /><br />
							用户管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("012")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="012" checked="checked"/>添加用户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="012"/>添加用户
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("013")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="013" checked="checked"/>编辑用户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="013"/>编辑用户
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("014")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="014" checked="checked"/>查询用户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="014"/>查询用户
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("015")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="015" checked="checked"/>删除用户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="015"/>删除用户
							<%} %>
							<br /><br />
							角色管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("016")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="016" checked="checked"/>添加角色
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="016"/>添加角色
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("017")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="017" checked="checked"/>编辑角色
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="017"/>编辑角色
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("018")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="018" checked="checked"/>查询角色
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="018"/>查询角色
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("019")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="019" checked="checked"/>删除角色
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="019"/>删除角色
							<%} %>
							<br /><br />
							功能操作：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("020")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="020" checked="checked"/>添加功能
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="020"/>添加功能
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("021")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="021" checked="checked"/>编辑功能
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="021"/>编辑功能
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("022")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="022" checked="checked"/>查询功能
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="022"/>查询功能
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("023")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="023" checked="checked"/>删除功能
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="023"/>删除功能
							<%} %>
							<br /><br />
							授权管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("024")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="024" checked="checked"/>配置权限
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="024"/>配置权限
							<%} %>
							<br /><br />
							客户管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("025")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="025" checked="checked"/>添加客户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="025"/>添加客户
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("026")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="026" checked="checked"/>编辑客户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="026"/>编辑客户
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("027")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="027" checked="checked"/>查询客户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="027"/>查询客户
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("028")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="028" checked="checked"/>删除客户
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="028"/>删除客户
							<%} %>
							<br /><br />
							日志管理：<br />
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("029")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="029" checked="checked"/>查询日志
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="029"/>查询日志
							<%} %>
							<%if(editRole.getFuncIds()!=null && editRole.getFuncIds().contains("030")) {%>
							<input name="ConfigPermi[]" type="checkbox" value="030" checked="checked"/>删除日志
							<%}else{ %>
							<input name="ConfigPermi[]" type="checkbox" value="030"/>删除日志
							<%} %>
						</div>
					</td>
				</tr>

				<tr height="28">
					<td align="center" colspan="2">
						<input type="submit" value="提交" class="button" onclick="return check()"> &nbsp; &nbsp; &nbsp;
						<input type="reset" value="重置" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
