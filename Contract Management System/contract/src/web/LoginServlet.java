package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Right;
import model.Role;
import service.UserService;
import util.AppException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String name = request.getParameter("username");
		String password = request.getParameter("password");

		String message = "";
		if (name == "" || password == "") {
			System.out.println("---Entered incorrectly!---");
			System.out.println("User name and password can not be empty!");
			message = "User name and password can not be empty!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/ToLogin").forward(request, response);
		} else {
			int userID = -1;
			try {
				UserService userService = new UserService();
				userID = userService.login(name, password);

				if (userID > 0) {
					HttpSession session = null;
					session = request.getSession();
					session.setAttribute("userId", userID);
					session.setAttribute("userName", name);
					Right right = null;
					Role role = null;
					right = userService.getRightByUserID(userID);
					int roleId = right.getRoleId();
					role = userService.getRoleByRoleId(roleId);
					session.setAttribute("role", role);
					//session.setAttribute("Role", role);

					if (role.getName().equals("none")) {
						response.sendRedirect("ToNewUser.jsp");
					} else if (role.getName().equals("admin")){
						response.sendRedirect("Frame2.jsp");
					} else if (role.getName().equals("operator")){
						response.sendRedirect("Frame1.jsp");
					}
				} else {
					message = "用户名或密码不正确！";
					request.setAttribute("message", message);
					request.setAttribute("userName", name);
					request.getRequestDispatcher("/ToLogin").forward(request, response);
				}
			} catch (AppException e) {
				e.printStackTrace();
				response.sendRedirect("/ToError");
			}
		}
	}

}
