package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class AddRoleServlet
 */
@WebServlet("/AddRoleServlet")
public class AddRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRoleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		// get values of every parameter
		String roleName = request.getParameter("roleName");
		String description = request.getParameter("description");
		String function = "";
		String[] ConfigPermi = request.getParameterValues("ConfigPermi[]");
		if (ConfigPermi != null) {
			for (int i = 0; i < ConfigPermi.length; ++i) {
				function += (ConfigPermi[i] + ",");
			}
		}
		if (function.length() > 0) {
			function=function.substring(0, function.length() - 1);
		}

		boolean flag = false;
		String message = "";
		Role newRole = new Role();
		UserService userService = new UserService();

		if (roleName == "") {
			message = "Role Name CANNOT BE EMPTY!";
		} else {
			newRole.setName(roleName);
			newRole.setDescription(description);
			newRole.setFuncIds(function);
			try {
				flag = userService.addRole(newRole);
				if (flag) {
					message = "Add Role Successful!";
				} else {
					message = "Add Role failed!";
				}
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "System exception!";
				response.sendRedirect("/ToError");
			}
			;
			request.setAttribute("message", message);
			request.getRequestDispatcher("ToRoleListServlet").forward(request, response);

		}
	}

}
