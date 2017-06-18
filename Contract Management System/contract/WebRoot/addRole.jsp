<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加角色</title>
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
		<div class="mtitle">
			添加角色
		</div>

		<br />
		<form action="AddRoleServlet" method="post">
			<table class="update" style="width:700px;">
				<tr height="28">
					<td width="100">角色名：</td>
					<td><input type="text" name="roleName" id="roleName" placeholder="RoleName"/></td>
				</tr>
				<tr height="28">
					<td>描述：</td>
					<td>
						<textarea rows="4" cols="40" name="description" style="width:400px;height:100px;"
							id="description" placeholder="Description of Role"></textarea>
					</td>
				</tr>
				<tr>
					<td>权限配置：</td>	
					<td>
						<div>
							合同管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="001"/>起草合同
							<input name="ConfigPermi[]" type="checkbox" value="002"/>定稿合同
							<input name="ConfigPermi[]" type="checkbox" value="003"/>查询合同
							<input name="ConfigPermi[]" type="checkbox" value="004"/>删除合同
							<br /><br />
							流程管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="005" />会签合同
							<input name="ConfigPermi[]" type="checkbox" value="006" />审批合同
							<input name="ConfigPermi[]" type="checkbox" value="007" />签订合同
							<br />
							<input name="ConfigPermi[]" type="checkbox" value="008" />分配会签
							<input name="ConfigPermi[]" type="checkbox" value="009" />分配审批
							<input name="ConfigPermi[]" type="checkbox" value="010" />分配签订
							<br />
							<input name="ConfigPermi[]" type="checkbox" value="011" />流程查询
							<br /><br />
							用户管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="012" />添加用户
							<input name="ConfigPermi[]" type="checkbox" value="013" />编辑用户
							<input name="ConfigPermi[]" type="checkbox" value="014" />查询用户
							<input name="ConfigPermi[]" type="checkbox" value="015" />删除用户
							<br /><br />
							角色管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="016" />添加角色
							<input name="ConfigPermi[]" type="checkbox" value="017" />编辑角色
							<input name="ConfigPermi[]" type="checkbox" value="018"/>查询角色
							<input name="ConfigPermi[]" type="checkbox" value="019" />删除角色
							<br /><br />
							功能操作：<br />
							<input name="ConfigPermi[]" type="checkbox" value="020" />添加功能
							<input name="ConfigPermi[]" type="checkbox" value="021" />编辑功能
							<input name="ConfigPermi[]" type="checkbox" value="022" />查询功能
							<input name="ConfigPermi[]" type="checkbox" value="023" />删除功能
							<br /><br />
							授权管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="024" />配置权限
							<br /><br />
							客户管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="025" />添加客户
							<input name="ConfigPermi[]" type="checkbox" value="026" />编辑客户
							<input name="ConfigPermi[]" type="checkbox" value="027" />查询客户
							<input name="ConfigPermi[]" type="checkbox" value="028" />删除客户
							<br /><br />
							日志管理：<br />
							<input name="ConfigPermi[]" type="checkbox" value="029" />查询日志
							<input name="ConfigPermi[]" type="checkbox" value="030" />删除日志
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
